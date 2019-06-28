/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { MiembrosGrupoUpdateComponent } from 'app/entities/miembros-grupo/miembros-grupo-update.component';
import { MiembrosGrupoService } from 'app/entities/miembros-grupo/miembros-grupo.service';
import { MiembrosGrupo } from 'app/shared/model/miembros-grupo.model';

describe('Component Tests', () => {
  describe('MiembrosGrupo Management Update Component', () => {
    let comp: MiembrosGrupoUpdateComponent;
    let fixture: ComponentFixture<MiembrosGrupoUpdateComponent>;
    let service: MiembrosGrupoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MiembrosGrupoUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MiembrosGrupoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MiembrosGrupoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MiembrosGrupoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MiembrosGrupo(123);
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
        const entity = new MiembrosGrupo();
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
