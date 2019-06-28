/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { MiembrosGrupoDetailComponent } from 'app/entities/miembros-grupo/miembros-grupo-detail.component';
import { MiembrosGrupo } from 'app/shared/model/miembros-grupo.model';

describe('Component Tests', () => {
  describe('MiembrosGrupo Management Detail Component', () => {
    let comp: MiembrosGrupoDetailComponent;
    let fixture: ComponentFixture<MiembrosGrupoDetailComponent>;
    const route = ({ data: of({ miembrosGrupo: new MiembrosGrupo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MiembrosGrupoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MiembrosGrupoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MiembrosGrupoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.miembrosGrupo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
