/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { MargenNewoGruposDetailComponent } from 'app/entities/margen-newo-grupos/margen-newo-grupos-detail.component';
import { MargenNewoGrupos } from 'app/shared/model/margen-newo-grupos.model';

describe('Component Tests', () => {
  describe('MargenNewoGrupos Management Detail Component', () => {
    let comp: MargenNewoGruposDetailComponent;
    let fixture: ComponentFixture<MargenNewoGruposDetailComponent>;
    const route = ({ data: of({ margenNewoGrupos: new MargenNewoGrupos(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MargenNewoGruposDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MargenNewoGruposDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MargenNewoGruposDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.margenNewoGrupos).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
