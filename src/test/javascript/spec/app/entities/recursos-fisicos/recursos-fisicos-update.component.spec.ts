/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { RecursosFisicosUpdateComponent } from 'app/entities/recursos-fisicos/recursos-fisicos-update.component';
import { RecursosFisicosService } from 'app/entities/recursos-fisicos/recursos-fisicos.service';
import { RecursosFisicos } from 'app/shared/model/recursos-fisicos.model';

describe('Component Tests', () => {
  describe('RecursosFisicos Management Update Component', () => {
    let comp: RecursosFisicosUpdateComponent;
    let fixture: ComponentFixture<RecursosFisicosUpdateComponent>;
    let service: RecursosFisicosService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [RecursosFisicosUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(RecursosFisicosUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RecursosFisicosUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RecursosFisicosService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RecursosFisicos(123);
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
        const entity = new RecursosFisicos();
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
