/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { TipoPrepagoConsumoUpdateComponent } from 'app/entities/tipo-prepago-consumo/tipo-prepago-consumo-update.component';
import { TipoPrepagoConsumoService } from 'app/entities/tipo-prepago-consumo/tipo-prepago-consumo.service';
import { TipoPrepagoConsumo } from 'app/shared/model/tipo-prepago-consumo.model';

describe('Component Tests', () => {
  describe('TipoPrepagoConsumo Management Update Component', () => {
    let comp: TipoPrepagoConsumoUpdateComponent;
    let fixture: ComponentFixture<TipoPrepagoConsumoUpdateComponent>;
    let service: TipoPrepagoConsumoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [TipoPrepagoConsumoUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TipoPrepagoConsumoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TipoPrepagoConsumoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TipoPrepagoConsumoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TipoPrepagoConsumo(123);
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
        const entity = new TipoPrepagoConsumo();
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
