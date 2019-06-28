/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { MargenNewoProductosUpdateComponent } from 'app/entities/margen-newo-productos/margen-newo-productos-update.component';
import { MargenNewoProductosService } from 'app/entities/margen-newo-productos/margen-newo-productos.service';
import { MargenNewoProductos } from 'app/shared/model/margen-newo-productos.model';

describe('Component Tests', () => {
  describe('MargenNewoProductos Management Update Component', () => {
    let comp: MargenNewoProductosUpdateComponent;
    let fixture: ComponentFixture<MargenNewoProductosUpdateComponent>;
    let service: MargenNewoProductosService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MargenNewoProductosUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MargenNewoProductosUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MargenNewoProductosUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MargenNewoProductosService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MargenNewoProductos(123);
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
        const entity = new MargenNewoProductos();
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
