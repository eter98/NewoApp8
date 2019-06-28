/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { CuentaAsociadaService } from 'app/entities/cuenta-asociada/cuenta-asociada.service';
import { ICuentaAsociada, CuentaAsociada } from 'app/shared/model/cuenta-asociada.model';

describe('Service Tests', () => {
  describe('CuentaAsociada Service', () => {
    let injector: TestBed;
    let service: CuentaAsociadaService;
    let httpMock: HttpTestingController;
    let elemDefault: ICuentaAsociada;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(CuentaAsociadaService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new CuentaAsociada(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            fechaVencimiento: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a CuentaAsociada', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fechaVencimiento: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaVencimiento: currentDate
          },
          returnedFromService
        );
        service
          .create(new CuentaAsociada(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a CuentaAsociada', async () => {
        const returnedFromService = Object.assign(
          {
            identificaciontitular: 'BBBBBB',
            nombreTitular: 'BBBBBB',
            apellidoTitular: 'BBBBBB',
            numeroCuenta: 'BBBBBB',
            tipoCuenta: 'BBBBBB',
            codigoSeguridad: 'BBBBBB',
            fechaVencimiento: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaVencimiento: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of CuentaAsociada', async () => {
        const returnedFromService = Object.assign(
          {
            identificaciontitular: 'BBBBBB',
            nombreTitular: 'BBBBBB',
            apellidoTitular: 'BBBBBB',
            numeroCuenta: 'BBBBBB',
            tipoCuenta: 'BBBBBB',
            codigoSeguridad: 'BBBBBB',
            fechaVencimiento: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaVencimiento: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a CuentaAsociada', async () => {
        const rxPromise = service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
