/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { RecursosFisicosService } from 'app/entities/recursos-fisicos/recursos-fisicos.service';
import { IRecursosFisicos, RecursosFisicos, TipoRecursod, Impuestod } from 'app/shared/model/recursos-fisicos.model';

describe('Service Tests', () => {
  describe('RecursosFisicos Service', () => {
    let injector: TestBed;
    let service: RecursosFisicosService;
    let httpMock: HttpTestingController;
    let elemDefault: IRecursosFisicos;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(RecursosFisicosService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new RecursosFisicos(
        0,
        'AAAAAAA',
        TipoRecursod.Tiempo,
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
        Impuestod.IVA19,
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

      it('should create a RecursosFisicos', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new RecursosFisicos(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a RecursosFisicos', async () => {
        const returnedFromService = Object.assign(
          {
            recurso: 'BBBBBB',
            tipo: 'BBBBBB',
            unidadMedida: 'BBBBBB',
            valorUnitario: 1,
            valor1H: 1,
            valor2H: 1,
            valor3H: 1,
            valor4H: 1,
            valor5H: 1,
            valor6H: 1,
            valor7H: 1,
            valor8H: 1,
            valor9H: 1,
            valor10H: 1,
            valor11H: 1,
            valor12H: 1,
            impuesto: 'BBBBBB',
            foto: 'BBBBBB',
            video: 'BBBBBB'
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

      it('should return a list of RecursosFisicos', async () => {
        const returnedFromService = Object.assign(
          {
            recurso: 'BBBBBB',
            tipo: 'BBBBBB',
            unidadMedida: 'BBBBBB',
            valorUnitario: 1,
            valor1H: 1,
            valor2H: 1,
            valor3H: 1,
            valor4H: 1,
            valor5H: 1,
            valor6H: 1,
            valor7H: 1,
            valor8H: 1,
            valor9H: 1,
            valor10H: 1,
            valor11H: 1,
            valor12H: 1,
            impuesto: 'BBBBBB',
            foto: 'BBBBBB',
            video: 'BBBBBB'
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

      it('should delete a RecursosFisicos', async () => {
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
