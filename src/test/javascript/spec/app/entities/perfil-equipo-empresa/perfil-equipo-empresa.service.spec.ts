/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { PerfilEquipoEmpresaService } from 'app/entities/perfil-equipo-empresa/perfil-equipo-empresa.service';
import { IPerfilEquipoEmpresa, PerfilEquipoEmpresa } from 'app/shared/model/perfil-equipo-empresa.model';

describe('Service Tests', () => {
  describe('PerfilEquipoEmpresa Service', () => {
    let injector: TestBed;
    let service: PerfilEquipoEmpresaService;
    let httpMock: HttpTestingController;
    let elemDefault: IPerfilEquipoEmpresa;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(PerfilEquipoEmpresaService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new PerfilEquipoEmpresa(
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

      it('should create a PerfilEquipoEmpresa', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new PerfilEquipoEmpresa(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a PerfilEquipoEmpresa', async () => {
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

      it('should return a list of PerfilEquipoEmpresa', async () => {
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

      it('should delete a PerfilEquipoEmpresa', async () => {
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
