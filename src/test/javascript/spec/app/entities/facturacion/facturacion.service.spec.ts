/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { FacturacionService } from 'app/entities/facturacion/facturacion.service';
import { IFacturacion, Facturacion, TipoPersonad, PeriodicidadFacturaciond } from 'app/shared/model/facturacion.model';

describe('Service Tests', () => {
  describe('Facturacion Service', () => {
    let injector: TestBed;
    let service: FacturacionService;
    let httpMock: HttpTestingController;
    let elemDefault: IFacturacion;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(FacturacionService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Facturacion(0, 'AAAAAAA', TipoPersonad.NATURAL, PeriodicidadFacturaciond.SEMANAL, 0);
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

      it('should create a Facturacion', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new Facturacion(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Facturacion', async () => {
        const returnedFromService = Object.assign(
          {
            titularFactura: 'BBBBBB',
            tipoPersona: 'BBBBBB',
            periodicidadFacturacion: 'BBBBBB',
            maximoMonto: 1
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

      it('should return a list of Facturacion', async () => {
        const returnedFromService = Object.assign(
          {
            titularFactura: 'BBBBBB',
            tipoPersona: 'BBBBBB',
            periodicidadFacturacion: 'BBBBBB',
            maximoMonto: 1
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

      it('should delete a Facturacion', async () => {
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
