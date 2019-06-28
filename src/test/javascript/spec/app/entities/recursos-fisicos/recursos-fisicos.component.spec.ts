/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewoApp8TestModule } from '../../../test.module';
import { RecursosFisicosComponent } from 'app/entities/recursos-fisicos/recursos-fisicos.component';
import { RecursosFisicosService } from 'app/entities/recursos-fisicos/recursos-fisicos.service';
import { RecursosFisicos } from 'app/shared/model/recursos-fisicos.model';

describe('Component Tests', () => {
  describe('RecursosFisicos Management Component', () => {
    let comp: RecursosFisicosComponent;
    let fixture: ComponentFixture<RecursosFisicosComponent>;
    let service: RecursosFisicosService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [RecursosFisicosComponent],
        providers: []
      })
        .overrideTemplate(RecursosFisicosComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RecursosFisicosComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RecursosFisicosService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new RecursosFisicos(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.recursosFisicos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
