/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { UsoRecursoFisicoUpdateComponent } from 'app/entities/uso-recurso-fisico/uso-recurso-fisico-update.component';
import { UsoRecursoFisicoService } from 'app/entities/uso-recurso-fisico/uso-recurso-fisico.service';
import { UsoRecursoFisico } from 'app/shared/model/uso-recurso-fisico.model';

describe('Component Tests', () => {
  describe('UsoRecursoFisico Management Update Component', () => {
    let comp: UsoRecursoFisicoUpdateComponent;
    let fixture: ComponentFixture<UsoRecursoFisicoUpdateComponent>;
    let service: UsoRecursoFisicoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [UsoRecursoFisicoUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(UsoRecursoFisicoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UsoRecursoFisicoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UsoRecursoFisicoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UsoRecursoFisico(123);
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
        const entity = new UsoRecursoFisico();
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
