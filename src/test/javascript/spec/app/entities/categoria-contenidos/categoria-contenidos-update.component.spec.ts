/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { CategoriaContenidosUpdateComponent } from 'app/entities/categoria-contenidos/categoria-contenidos-update.component';
import { CategoriaContenidosService } from 'app/entities/categoria-contenidos/categoria-contenidos.service';
import { CategoriaContenidos } from 'app/shared/model/categoria-contenidos.model';

describe('Component Tests', () => {
  describe('CategoriaContenidos Management Update Component', () => {
    let comp: CategoriaContenidosUpdateComponent;
    let fixture: ComponentFixture<CategoriaContenidosUpdateComponent>;
    let service: CategoriaContenidosService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [CategoriaContenidosUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CategoriaContenidosUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CategoriaContenidosUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CategoriaContenidosService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CategoriaContenidos(123);
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
        const entity = new CategoriaContenidos();
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
