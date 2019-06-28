/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { EventoService } from 'app/entities/evento/evento.service';
import { IEvento, Evento, TipoConsumod, Impuestod, Categoriad } from 'app/shared/model/evento.model';

describe('Service Tests', () => {
  describe('Evento Service', () => {
    let injector: TestBed;
    let service: EventoService;
    let httpMock: HttpTestingController;
    let elemDefault: IEvento;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(EventoService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Evento(
        0,
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
        TipoConsumod.GRATIS,
        0,
        Impuestod.IVA19,
        Categoriad.GENERAL
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

      it('should create a Evento', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new Evento(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Evento', async () => {
        const returnedFromService = Object.assign(
          {
            nombreEvento: 'BBBBBB',
            descripcion: 'BBBBBB',
            audio: 'BBBBBB',
            video: 'BBBBBB',
            foto1: 'BBBBBB',
            foto2: 'BBBBBB',
            banner: 'BBBBBB',
            tipoConsumo: 'BBBBBB',
            valor: 1,
            impuesto: 'BBBBBB',
            tipoEvento: 'BBBBBB'
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

      it('should return a list of Evento', async () => {
        const returnedFromService = Object.assign(
          {
            nombreEvento: 'BBBBBB',
            descripcion: 'BBBBBB',
            audio: 'BBBBBB',
            video: 'BBBBBB',
            foto1: 'BBBBBB',
            foto2: 'BBBBBB',
            banner: 'BBBBBB',
            tipoConsumo: 'BBBBBB',
            valor: 1,
            impuesto: 'BBBBBB',
            tipoEvento: 'BBBBBB'
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

      it('should delete a Evento', async () => {
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
