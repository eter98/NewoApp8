/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { TipoEspacioDetailComponent } from 'app/entities/tipo-espacio/tipo-espacio-detail.component';
import { TipoEspacio } from 'app/shared/model/tipo-espacio.model';

describe('Component Tests', () => {
  describe('TipoEspacio Management Detail Component', () => {
    let comp: TipoEspacioDetailComponent;
    let fixture: ComponentFixture<TipoEspacioDetailComponent>;
    const route = ({ data: of({ tipoEspacio: new TipoEspacio(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [TipoEspacioDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TipoEspacioDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TipoEspacioDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tipoEspacio).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
