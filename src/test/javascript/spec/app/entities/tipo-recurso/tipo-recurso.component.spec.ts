/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewoApp8TestModule } from '../../../test.module';
import { TipoRecursoComponent } from 'app/entities/tipo-recurso/tipo-recurso.component';
import { TipoRecursoService } from 'app/entities/tipo-recurso/tipo-recurso.service';
import { TipoRecurso } from 'app/shared/model/tipo-recurso.model';

describe('Component Tests', () => {
  describe('TipoRecurso Management Component', () => {
    let comp: TipoRecursoComponent;
    let fixture: ComponentFixture<TipoRecursoComponent>;
    let service: TipoRecursoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [TipoRecursoComponent],
        providers: []
      })
        .overrideTemplate(TipoRecursoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TipoRecursoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TipoRecursoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TipoRecurso(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tipoRecursos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
