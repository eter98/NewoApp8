/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { TipoEspacioUpdateComponent } from 'app/entities/tipo-espacio/tipo-espacio-update.component';
import { TipoEspacioService } from 'app/entities/tipo-espacio/tipo-espacio.service';
import { TipoEspacio } from 'app/shared/model/tipo-espacio.model';

describe('Component Tests', () => {
  describe('TipoEspacio Management Update Component', () => {
    let comp: TipoEspacioUpdateComponent;
    let fixture: ComponentFixture<TipoEspacioUpdateComponent>;
    let service: TipoEspacioService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [TipoEspacioUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TipoEspacioUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TipoEspacioUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TipoEspacioService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TipoEspacio(123);
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
        const entity = new TipoEspacio();
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
