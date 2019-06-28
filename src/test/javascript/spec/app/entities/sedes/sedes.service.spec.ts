/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { SedesService } from 'app/entities/sedes/sedes.service';
import { ISedes, Sedes } from 'app/shared/model/sedes.model';

describe('Service Tests', () => {
  describe('Sedes Service', () => {
    let injector: TestBed;
    let service: SedesService;
    let httpMock: HttpTestingController;
    let elemDefault: ISedes;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(SedesService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Sedes(
        0,
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Sedes', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new Sedes(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Sedes', async () => {
        const returnedFromService = Object.assign(
          {
            nombreSede: 'BBBBBB',
            coordenadaX: 1,
            coordenadaY: 1,
            direccion: 'BBBBBB',
            telefonoComunidad: 'BBBBBB',
            telefonoNegocio: 'BBBBBB',
            descripcionSede: 'BBBBBB',
            horario: 'BBBBBB',
            video: 'BBBBBB',
            imagen1: 'BBBBBB',
            imagen2: 'BBBBBB',
            imagen3: 'BBBBBB',
            imagen4: 'BBBBBB',
            imagen5: 'BBBBBB',
            imagen6: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Sedes', async () => {
        const returnedFromService = Object.assign(
          {
            nombreSede: 'BBBBBB',
            coordenadaX: 1,
            coordenadaY: 1,
            direccion: 'BBBBBB',
            telefonoComunidad: 'BBBBBB',
            telefonoNegocio: 'BBBBBB',
            descripcionSede: 'BBBBBB',
            horario: 'BBBBBB',
            video: 'BBBBBB',
            imagen1: 'BBBBBB',
            imagen2: 'BBBBBB',
            imagen3: 'BBBBBB',
            imagen4: 'BBBBBB',
            imagen5: 'BBBBBB',
            imagen6: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
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

      it('should delete a Sedes', async () => {
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
