/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { EspacioLibreUpdateComponent } from 'app/entities/espacio-libre/espacio-libre-update.component';
import { EspacioLibreService } from 'app/entities/espacio-libre/espacio-libre.service';
import { EspacioLibre } from 'app/shared/model/espacio-libre.model';

describe('Component Tests', () => {
  describe('EspacioLibre Management Update Component', () => {
    let comp: EspacioLibreUpdateComponent;
    let fixture: ComponentFixture<EspacioLibreUpdateComponent>;
    let service: EspacioLibreService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [EspacioLibreUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EspacioLibreUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EspacioLibreUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EspacioLibreService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EspacioLibre(123);
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
        const entity = new EspacioLibre();
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
