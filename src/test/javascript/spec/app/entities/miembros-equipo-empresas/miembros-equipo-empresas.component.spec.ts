/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewoApp8TestModule } from '../../../test.module';
import { MiembrosEquipoEmpresasComponent } from 'app/entities/miembros-equipo-empresas/miembros-equipo-empresas.component';
import { MiembrosEquipoEmpresasService } from 'app/entities/miembros-equipo-empresas/miembros-equipo-empresas.service';
import { MiembrosEquipoEmpresas } from 'app/shared/model/miembros-equipo-empresas.model';

describe('Component Tests', () => {
  describe('MiembrosEquipoEmpresas Management Component', () => {
    let comp: MiembrosEquipoEmpresasComponent;
    let fixture: ComponentFixture<MiembrosEquipoEmpresasComponent>;
    let service: MiembrosEquipoEmpresasService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MiembrosEquipoEmpresasComponent],
        providers: []
      })
        .overrideTemplate(MiembrosEquipoEmpresasComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MiembrosEquipoEmpresasComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MiembrosEquipoEmpresasService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MiembrosEquipoEmpresas(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.miembrosEquipoEmpresas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
