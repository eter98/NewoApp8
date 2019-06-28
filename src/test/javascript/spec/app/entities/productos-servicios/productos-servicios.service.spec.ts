/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { ProductosServiciosService } from 'app/entities/productos-servicios/productos-servicios.service';
import { IProductosServicios, ProductosServicios, Impuestod } from 'app/shared/model/productos-servicios.model';

describe('Service Tests', () => {
  describe('ProductosServicios Service', () => {
    let injector: TestBed;
    let service: ProductosServiciosService;
    let httpMock: HttpTestingController;
    let elemDefault: IProductosServicios;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ProductosServiciosService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ProductosServicios(
        0,
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'AAAAAAA',
        false,
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

      it('should create a ProductosServicios', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new ProductosServicios(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a ProductosServicios', async () => {
        const returnedFromService = Object.assign(
          {
            nombreProducto: 'BBBBBB',
            foto: 'BBBBBB',
            descripcion: 'BBBBBB',
            inventariables: true,
            valor: 1,
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

      it('should return a list of ProductosServicios', async () => {
        const returnedFromService = Object.assign(
          {
            nombreProducto: 'BBBBBB',
            foto: 'BBBBBB',
            descripcion: 'BBBBBB',
            inventariables: true,
            valor: 1,
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

      it('should delete a ProductosServicios', async () => {
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
