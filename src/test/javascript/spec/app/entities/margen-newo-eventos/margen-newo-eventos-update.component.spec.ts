/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { MargenNewoEventosUpdateComponent } from 'app/entities/margen-newo-eventos/margen-newo-eventos-update.component';
import { MargenNewoEventosService } from 'app/entities/margen-newo-eventos/margen-newo-eventos.service';
import { MargenNewoEventos } from 'app/shared/model/margen-newo-eventos.model';

describe('Component Tests', () => {
  describe('MargenNewoEventos Management Update Component', () => {
    let comp: MargenNewoEventosUpdateComponent;
    let fixture: ComponentFixture<MargenNewoEventosUpdateComponent>;
    let service: MargenNewoEventosService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MargenNewoEventosUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MargenNewoEventosUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MargenNewoEventosUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MargenNewoEventosService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MargenNewoEventos(123);
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
        const entity = new MargenNewoEventos();
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
