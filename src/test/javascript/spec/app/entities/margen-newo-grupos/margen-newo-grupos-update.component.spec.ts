/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { MargenNewoGruposUpdateComponent } from 'app/entities/margen-newo-grupos/margen-newo-grupos-update.component';
import { MargenNewoGruposService } from 'app/entities/margen-newo-grupos/margen-newo-grupos.service';
import { MargenNewoGrupos } from 'app/shared/model/margen-newo-grupos.model';

describe('Component Tests', () => {
  describe('MargenNewoGrupos Management Update Component', () => {
    let comp: MargenNewoGruposUpdateComponent;
    let fixture: ComponentFixture<MargenNewoGruposUpdateComponent>;
    let service: MargenNewoGruposService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MargenNewoGruposUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MargenNewoGruposUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MargenNewoGruposUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MargenNewoGruposService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MargenNewoGrupos(123);
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
        const entity = new MargenNewoGrupos();
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
