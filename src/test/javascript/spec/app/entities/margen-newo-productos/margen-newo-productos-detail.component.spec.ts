/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { MargenNewoProductosDetailComponent } from 'app/entities/margen-newo-productos/margen-newo-productos-detail.component';
import { MargenNewoProductos } from 'app/shared/model/margen-newo-productos.model';

describe('Component Tests', () => {
  describe('MargenNewoProductos Management Detail Component', () => {
    let comp: MargenNewoProductosDetailComponent;
    let fixture: ComponentFixture<MargenNewoProductosDetailComponent>;
    const route = ({ data: of({ margenNewoProductos: new MargenNewoProductos(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MargenNewoProductosDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MargenNewoProductosDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MargenNewoProductosDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.margenNewoProductos).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
