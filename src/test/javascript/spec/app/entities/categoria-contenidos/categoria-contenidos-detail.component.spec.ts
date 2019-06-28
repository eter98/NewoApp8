/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { CategoriaContenidosDetailComponent } from 'app/entities/categoria-contenidos/categoria-contenidos-detail.component';
import { CategoriaContenidos } from 'app/shared/model/categoria-contenidos.model';

describe('Component Tests', () => {
  describe('CategoriaContenidos Management Detail Component', () => {
    let comp: CategoriaContenidosDetailComponent;
    let fixture: ComponentFixture<CategoriaContenidosDetailComponent>;
    const route = ({ data: of({ categoriaContenidos: new CategoriaContenidos(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [CategoriaContenidosDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CategoriaContenidosDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CategoriaContenidosDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.categoriaContenidos).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
