/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { MargenNewoEventosDetailComponent } from 'app/entities/margen-newo-eventos/margen-newo-eventos-detail.component';
import { MargenNewoEventos } from 'app/shared/model/margen-newo-eventos.model';

describe('Component Tests', () => {
  describe('MargenNewoEventos Management Detail Component', () => {
    let comp: MargenNewoEventosDetailComponent;
    let fixture: ComponentFixture<MargenNewoEventosDetailComponent>;
    const route = ({ data: of({ margenNewoEventos: new MargenNewoEventos(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MargenNewoEventosDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MargenNewoEventosDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MargenNewoEventosDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.margenNewoEventos).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
