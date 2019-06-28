/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { UsoRecursoFisicoService } from 'app/entities/uso-recurso-fisico/uso-recurso-fisico.service';
import { IUsoRecursoFisico, UsoRecursoFisico, TipoIniciod } from 'app/shared/model/uso-recurso-fisico.model';

describe('Service Tests', () => {
  describe('UsoRecursoFisico Service', () => {
    let injector: TestBed;
    let service: UsoRecursoFisicoService;
    let httpMock: HttpTestingController;
    let elemDefault: IUsoRecursoFisico;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(UsoRecursoFisicoService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new UsoRecursoFisico(0, currentDate, currentDate, TipoIniciod.Inicio);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            registroFechaInicio: currentDate.format(DATE_TIME_FORMAT),
            registroFechaFinal: currentDate.format(DATE_TIME_FORMAT)
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

      it('should create a UsoRecursoFisico', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            registroFechaInicio: currentDate.format(DATE_TIME_FORMAT),
            registroFechaFinal: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            registroFechaInicio: currentDate,
            registroFechaFinal: currentDate
          },
          returnedFromService
        );
        service
          .create(new UsoRecursoFisico(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a UsoRecursoFisico', async () => {
        const returnedFromService = Object.assign(
          {
            registroFechaInicio: currentDate.format(DATE_TIME_FORMAT),
            registroFechaFinal: currentDate.format(DATE_TIME_FORMAT),
            tipoRegistro: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            registroFechaInicio: currentDate,
            registroFechaFinal: currentDate
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

      it('should return a list of UsoRecursoFisico', async () => {
        const returnedFromService = Object.assign(
          {
            registroFechaInicio: currentDate.format(DATE_TIME_FORMAT),
            registroFechaFinal: currentDate.format(DATE_TIME_FORMAT),
            tipoRegistro: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            registroFechaInicio: currentDate,
            registroFechaFinal: currentDate
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

      it('should delete a UsoRecursoFisico', async () => {
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
