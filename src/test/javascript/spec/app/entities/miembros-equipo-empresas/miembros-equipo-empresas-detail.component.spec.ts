/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { MiembrosEquipoEmpresasDetailComponent } from 'app/entities/miembros-equipo-empresas/miembros-equipo-empresas-detail.component';
import { MiembrosEquipoEmpresas } from 'app/shared/model/miembros-equipo-empresas.model';

describe('Component Tests', () => {
  describe('MiembrosEquipoEmpresas Management Detail Component', () => {
    let comp: MiembrosEquipoEmpresasDetailComponent;
    let fixture: ComponentFixture<MiembrosEquipoEmpresasDetailComponent>;
    const route = ({ data: of({ miembrosEquipoEmpresas: new MiembrosEquipoEmpresas(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MiembrosEquipoEmpresasDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MiembrosEquipoEmpresasDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MiembrosEquipoEmpresasDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.miembrosEquipoEmpresas).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
