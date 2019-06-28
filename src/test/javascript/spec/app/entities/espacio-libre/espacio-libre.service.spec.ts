/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { EspacioLibreService } from 'app/entities/espacio-libre/espacio-libre.service';
import { IEspacioLibre, EspacioLibre, Impuestod } from 'app/shared/model/espacio-libre.model';

describe('Service Tests', () => {
  describe('EspacioLibre Service', () => {
    let injector: TestBed;
    let service: EspacioLibreService;
    let httpMock: HttpTestingController;
    let elemDefault: IEspacioLibre;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(EspacioLibreService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new EspacioLibre(
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        Impuestod.IVA19,
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

      it('should create a EspacioLibre', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new EspacioLibre(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a EspacioLibre', async () => {
        const returnedFromService = Object.assign(
          {
            nombre: 'BBBBBB',
            capacidadInstalada: 1,
            wifi: 'BBBBBB',
            tarifa1hMiembro: 1,
            tarifa2hMiembro: 1,
            tarifa3hMiembro: 1,
            tarifa4hMiembro: 1,
            tarifa5hMiembro: 1,
            tarifa6hMiembro: 1,
            tarifa7hMiembro: 1,
            tarifa8hMiembro: 1,
            tarifa1hInvitado: 1,
            tarifa2hInvitado: 1,
            tarifa3hInvitado: 1,
            tarifa4hInvitado: 1,
            tarifa5hInvitado: 1,
            tarifa6hInvitado: 1,
            tarifa7hInvitado: 1,
            tarifa8hInvitado: 1,
            impuesto: 'BBBBBB',
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

      it('should return a list of EspacioLibre', async () => {
        const returnedFromService = Object.assign(
          {
            nombre: 'BBBBBB',
            capacidadInstalada: 1,
            wifi: 'BBBBBB',
            tarifa1hMiembro: 1,
            tarifa2hMiembro: 1,
            tarifa3hMiembro: 1,
            tarifa4hMiembro: 1,
            tarifa5hMiembro: 1,
            tarifa6hMiembro: 1,
            tarifa7hMiembro: 1,
            tarifa8hMiembro: 1,
            tarifa1hInvitado: 1,
            tarifa2hInvitado: 1,
            tarifa3hInvitado: 1,
            tarifa4hInvitado: 1,
            tarifa5hInvitado: 1,
            tarifa6hInvitado: 1,
            tarifa7hInvitado: 1,
            tarifa8hInvitado: 1,
            impuesto: 'BBBBBB',
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

      it('should delete a EspacioLibre', async () => {
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
