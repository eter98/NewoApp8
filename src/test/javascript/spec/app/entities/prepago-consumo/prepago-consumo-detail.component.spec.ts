/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { PrepagoConsumoDetailComponent } from 'app/entities/prepago-consumo/prepago-consumo-detail.component';
import { PrepagoConsumo } from 'app/shared/model/prepago-consumo.model';

describe('Component Tests', () => {
  describe('PrepagoConsumo Management Detail Component', () => {
    let comp: PrepagoConsumoDetailComponent;
    let fixture: ComponentFixture<PrepagoConsumoDetailComponent>;
    const route = ({ data: of({ prepagoConsumo: new PrepagoConsumo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [PrepagoConsumoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PrepagoConsumoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PrepagoConsumoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.prepagoConsumo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
