/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { TipoRecursoUpdateComponent } from 'app/entities/tipo-recurso/tipo-recurso-update.component';
import { TipoRecursoService } from 'app/entities/tipo-recurso/tipo-recurso.service';
import { TipoRecurso } from 'app/shared/model/tipo-recurso.model';

describe('Component Tests', () => {
  describe('TipoRecurso Management Update Component', () => {
    let comp: TipoRecursoUpdateComponent;
    let fixture: ComponentFixture<TipoRecursoUpdateComponent>;
    let service: TipoRecursoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [TipoRecursoUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TipoRecursoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TipoRecursoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TipoRecursoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TipoRecurso(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new TipoRecurso();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
