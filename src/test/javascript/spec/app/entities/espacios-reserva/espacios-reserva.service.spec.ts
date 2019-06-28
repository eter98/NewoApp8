/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { EspaciosReservaService } from 'app/entities/espacios-reserva/espacios-reserva.service';
import { IEspaciosReserva, EspaciosReserva, Impuestod } from 'app/shared/model/espacios-reserva.model';

describe('Service Tests', () => {
  describe('EspaciosReserva Service', () => {
    let injector: TestBed;
    let service: EspaciosReservaService;
    let httpMock: HttpTestingController;
    let elemDefault: IEspaciosReserva;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(EspaciosReservaService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new EspaciosReserva(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        Impuestod.IVA19,
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

      it('should create a EspaciosReserva', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new EspaciosReserva(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a EspaciosReserva', async () => {
        const returnedFromService = Object.assign(
          {
            nombre: 'BBBBBB',
            descripcion: 'BBBBBB',
            facilidades: 'BBBBBB',
            capacidad: 1,
            apertura: 'BBBBBB',
            cierre: 'BBBBBB',
            tarifa1Hora: 1,
            tarifa2Hora: 1,
            tarifa3Hora: 1,
            tarifa4Hora: 1,
            tarifa5Hora: 1,
            tarifa6Hora: 1,
            tarifa7Hora: 1,
            tarifa8Hora: 1,
            impuesto: 'BBBBBB',
            wifi: 'BBBBBB',
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

      it('should return a list of EspaciosReserva', async () => {
        const returnedFromService = Object.assign(
          {
            nombre: 'BBBBBB',
            descripcion: 'BBBBBB',
            facilidades: 'BBBBBB',
            capacidad: 1,
            apertura: 'BBBBBB',
            cierre: 'BBBBBB',
            tarifa1Hora: 1,
            tarifa2Hora: 1,
            tarifa3Hora: 1,
            tarifa4Hora: 1,
            tarifa5Hora: 1,
            tarifa6Hora: 1,
            tarifa7Hora: 1,
            tarifa8Hora: 1,
            impuesto: 'BBBBBB',
            wifi: 'BBBBBB',
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

      it('should delete a EspaciosReserva', async () => {
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
