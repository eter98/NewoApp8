/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { ConsumoMarketUpdateComponent } from 'app/entities/consumo-market/consumo-market-update.component';
import { ConsumoMarketService } from 'app/entities/consumo-market/consumo-market.service';
import { ConsumoMarket } from 'app/shared/model/consumo-market.model';

describe('Component Tests', () => {
  describe('ConsumoMarket Management Update Component', () => {
    let comp: ConsumoMarketUpdateComponent;
    let fixture: ComponentFixture<ConsumoMarketUpdateComponent>;
    let service: ConsumoMarketService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [ConsumoMarketUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ConsumoMarketUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ConsumoMarketUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConsumoMarketService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ConsumoMarket(123);
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
        const entity = new ConsumoMarket();
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
