/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { PrepagoConsumoUpdateComponent } from 'app/entities/prepago-consumo/prepago-consumo-update.component';
import { PrepagoConsumoService } from 'app/entities/prepago-consumo/prepago-consumo.service';
import { PrepagoConsumo } from 'app/shared/model/prepago-consumo.model';

describe('Component Tests', () => {
  describe('PrepagoConsumo Management Update Component', () => {
    let comp: PrepagoConsumoUpdateComponent;
    let fixture: ComponentFixture<PrepagoConsumoUpdateComponent>;
    let service: PrepagoConsumoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [PrepagoConsumoUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PrepagoConsumoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PrepagoConsumoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PrepagoConsumoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PrepagoConsumo(123);
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
        const entity = new PrepagoConsumo();
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
