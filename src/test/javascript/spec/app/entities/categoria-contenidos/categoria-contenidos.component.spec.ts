/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewoApp8TestModule } from '../../../test.module';
import { CategoriaContenidosComponent } from 'app/entities/categoria-contenidos/categoria-contenidos.component';
import { CategoriaContenidosService } from 'app/entities/categoria-contenidos/categoria-contenidos.service';
import { CategoriaContenidos } from 'app/shared/model/categoria-contenidos.model';

describe('Component Tests', () => {
  describe('CategoriaContenidos Management Component', () => {
    let comp: CategoriaContenidosComponent;
    let fixture: ComponentFixture<CategoriaContenidosComponent>;
    let service: CategoriaContenidosService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [CategoriaContenidosComponent],
        providers: []
      })
        .overrideTemplate(CategoriaContenidosComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CategoriaContenidosComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CategoriaContenidosService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CategoriaContenidos(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.categoriaContenidos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
