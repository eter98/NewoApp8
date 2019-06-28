/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { MiembrosService } from 'app/entities/miembros/miembros.service';
import { IMiembros, Miembros, TipoDocumentod, Generod } from 'app/shared/model/miembros.model';

describe('Service Tests', () => {
  describe('Miembros Service', () => {
    let injector: TestBed;
    let service: MiembrosService;
    let httpMock: HttpTestingController;
    let elemDefault: IMiembros;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MiembrosService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Miembros(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        TipoDocumentod.Cedula,
        0,
        currentDate,
        currentDate,
        Generod.Masculino,
        'AAAAAAA',
        'AAAAAAA',
        false,
        false,
        false,
        false
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            fechaNacimiento: currentDate.format(DATE_FORMAT),
            fechaRegistro: currentDate.format(DATE_FORMAT)
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

      it('should create a Miembros', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fechaNacimiento: currentDate.format(DATE_FORMAT),
            fechaRegistro: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaNacimiento: currentDate,
            fechaRegistro: currentDate
          },
          returnedFromService
        );
        service
          .create(new Miembros(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Miembros', async () => {
        const returnedFromService = Object.assign(
          {
            nombre: 'BBBBBB',
            apellido: 'BBBBBB',
            nacionalidad: 'BBBBBB',
            tipoDocumento: 'BBBBBB',
            identificacion: 1,
            fechaNacimiento: currentDate.format(DATE_FORMAT),
            fechaRegistro: currentDate.format(DATE_FORMAT),
            genero: 'BBBBBB',
            correoElectronico: 'BBBBBB',
            celular: 'BBBBBB',
            derechosDeCompra: true,
            accesoIlimitado: true,
            aliado: true,
            host: true
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaNacimiento: currentDate,
            fechaRegistro: currentDate
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

      it('should return a list of Miembros', async () => {
        const returnedFromService = Object.assign(
          {
            nombre: 'BBBBBB',
            apellido: 'BBBBBB',
            nacionalidad: 'BBBBBB',
            tipoDocumento: 'BBBBBB',
            identificacion: 1,
            fechaNacimiento: currentDate.format(DATE_FORMAT),
            fechaRegistro: currentDate.format(DATE_FORMAT),
            genero: 'BBBBBB',
            correoElectronico: 'BBBBBB',
            celular: 'BBBBBB',
            derechosDeCompra: true,
            accesoIlimitado: true,
            aliado: true,
            host: true
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaNacimiento: currentDate,
            fechaRegistro: currentDate
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

      it('should delete a Miembros', async () => {
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
