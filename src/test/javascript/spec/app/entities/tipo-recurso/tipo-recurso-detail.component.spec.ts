/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { TipoRecursoDetailComponent } from 'app/entities/tipo-recurso/tipo-recurso-detail.component';
import { TipoRecurso } from 'app/shared/model/tipo-recurso.model';

describe('Component Tests', () => {
  describe('TipoRecurso Management Detail Component', () => {
    let comp: TipoRecursoDetailComponent;
    let fixture: ComponentFixture<TipoRecursoDetailComponent>;
    const route = ({ data: of({ tipoRecurso: new TipoRecurso(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [TipoRecursoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TipoRecursoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TipoRecursoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tipoRecurso).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
