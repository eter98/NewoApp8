/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewoApp8TestModule } from '../../../test.module';
import { MiembrosGrupoComponent } from 'app/entities/miembros-grupo/miembros-grupo.component';
import { MiembrosGrupoService } from 'app/entities/miembros-grupo/miembros-grupo.service';
import { MiembrosGrupo } from 'app/shared/model/miembros-grupo.model';

describe('Component Tests', () => {
  describe('MiembrosGrupo Management Component', () => {
    let comp: MiembrosGrupoComponent;
    let fixture: ComponentFixture<MiembrosGrupoComponent>;
    let service: MiembrosGrupoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MiembrosGrupoComponent],
        providers: []
      })
        .overrideTemplate(MiembrosGrupoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MiembrosGrupoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MiembrosGrupoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MiembrosGrupo(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.miembrosGrupos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
