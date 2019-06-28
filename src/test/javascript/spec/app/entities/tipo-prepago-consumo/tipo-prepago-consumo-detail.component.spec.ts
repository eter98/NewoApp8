/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { TipoPrepagoConsumoDetailComponent } from 'app/entities/tipo-prepago-consumo/tipo-prepago-consumo-detail.component';
import { TipoPrepagoConsumo } from 'app/shared/model/tipo-prepago-consumo.model';

describe('Component Tests', () => {
  describe('TipoPrepagoConsumo Management Detail Component', () => {
    let comp: TipoPrepagoConsumoDetailComponent;
    let fixture: ComponentFixture<TipoPrepagoConsumoDetailComponent>;
    const route = ({ data: of({ tipoPrepagoConsumo: new TipoPrepagoConsumo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [TipoPrepagoConsumoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TipoPrepagoConsumoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TipoPrepagoConsumoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tipoPrepagoConsumo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
