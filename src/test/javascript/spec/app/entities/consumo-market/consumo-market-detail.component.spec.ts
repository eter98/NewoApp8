/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { ConsumoMarketDetailComponent } from 'app/entities/consumo-market/consumo-market-detail.component';
import { ConsumoMarket } from 'app/shared/model/consumo-market.model';

describe('Component Tests', () => {
  describe('ConsumoMarket Management Detail Component', () => {
    let comp: ConsumoMarketDetailComponent;
    let fixture: ComponentFixture<ConsumoMarketDetailComponent>;
    const route = ({ data: of({ consumoMarket: new ConsumoMarket(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [ConsumoMarketDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ConsumoMarketDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ConsumoMarketDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.consumoMarket).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
