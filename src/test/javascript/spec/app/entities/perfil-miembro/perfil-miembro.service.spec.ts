/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { PerfilMiembroService } from 'app/entities/perfil-miembro/perfil-miembro.service';
import { IPerfilMiembro, PerfilMiembro } from 'app/shared/model/perfil-miembro.model';

describe('Service Tests', () => {
  describe('PerfilMiembro Service', () => {
    let injector: TestBed;
    let service: PerfilMiembroService;
    let httpMock: HttpTestingController;
    let elemDefault: IPerfilMiembro;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(PerfilMiembroService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new PerfilMiembro(
        0,
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
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

      it('should create a PerfilMiembro', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new PerfilMiembro(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a PerfilMiembro', async () => {
        const returnedFromService = Object.assign(
          {
            biografia: 'BBBBBB',
            foto1: 'BBBBBB',
            foto2: 'BBBBBB',
            fot3: 'BBBBBB',
            conocimientosQueDomina: 'BBBBBB',
            temasDeInteres: 'BBBBBB',
            facebook: 'BBBBBB',
            instagram: 'BBBBBB',
            idGoogle: 'BBBBBB',
            twiter: 'BBBBBB'
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

      it('should return a list of PerfilMiembro', async () => {
        const returnedFromService = Object.assign(
          {
            biografia: 'BBBBBB',
            foto1: 'BBBBBB',
            foto2: 'BBBBBB',
            fot3: 'BBBBBB',
            conocimientosQueDomina: 'BBBBBB',
            temasDeInteres: 'BBBBBB',
            facebook: 'BBBBBB',
            instagram: 'BBBBBB',
            idGoogle: 'BBBBBB',
            twiter: 'BBBBBB'
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

      it('should delete a PerfilMiembro', async () => {
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
