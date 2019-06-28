/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { EspaciosReservaUpdateComponent } from 'app/entities/espacios-reserva/espacios-reserva-update.component';
import { EspaciosReservaService } from 'app/entities/espacios-reserva/espacios-reserva.service';
import { EspaciosReserva } from 'app/shared/model/espacios-reserva.model';

describe('Component Tests', () => {
  describe('EspaciosReserva Management Update Component', () => {
    let comp: EspaciosReservaUpdateComponent;
    let fixture: ComponentFixture<EspaciosReservaUpdateComponent>;
    let service: EspaciosReservaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [EspaciosReservaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EspaciosReservaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EspaciosReservaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EspaciosReservaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EspaciosReserva(123);
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
        const entity = new EspaciosReserva();
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
