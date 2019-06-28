/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewoApp8TestModule } from '../../../test.module';
import { ConsumoMarketComponent } from 'app/entities/consumo-market/consumo-market.component';
import { ConsumoMarketService } from 'app/entities/consumo-market/consumo-market.service';
import { ConsumoMarket } from 'app/shared/model/consumo-market.model';

describe('Component Tests', () => {
  describe('ConsumoMarket Management Component', () => {
    let comp: ConsumoMarketComponent;
    let fixture: ComponentFixture<ConsumoMarketComponent>;
    let service: ConsumoMarketService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [ConsumoMarketComponent],
        providers: []
      })
        .overrideTemplate(ConsumoMarketComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ConsumoMarketComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConsumoMarketService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ConsumoMarket(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.consumoMarkets[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
