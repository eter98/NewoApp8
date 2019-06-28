/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { MiembrosEquipoEmpresasUpdateComponent } from 'app/entities/miembros-equipo-empresas/miembros-equipo-empresas-update.component';
import { MiembrosEquipoEmpresasService } from 'app/entities/miembros-equipo-empresas/miembros-equipo-empresas.service';
import { MiembrosEquipoEmpresas } from 'app/shared/model/miembros-equipo-empresas.model';

describe('Component Tests', () => {
  describe('MiembrosEquipoEmpresas Management Update Component', () => {
    let comp: MiembrosEquipoEmpresasUpdateComponent;
    let fixture: ComponentFixture<MiembrosEquipoEmpresasUpdateComponent>;
    let service: MiembrosEquipoEmpresasService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MiembrosEquipoEmpresasUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MiembrosEquipoEmpresasUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MiembrosEquipoEmpresasUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MiembrosEquipoEmpresasService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MiembrosEquipoEmpresas(123);
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
        const entity = new MiembrosEquipoEmpresas();
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
