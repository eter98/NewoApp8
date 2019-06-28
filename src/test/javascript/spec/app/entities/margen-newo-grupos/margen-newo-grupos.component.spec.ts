/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewoApp8TestModule } from '../../../test.module';
import { MargenNewoGruposComponent } from 'app/entities/margen-newo-grupos/margen-newo-grupos.component';
import { MargenNewoGruposService } from 'app/entities/margen-newo-grupos/margen-newo-grupos.service';
import { MargenNewoGrupos } from 'app/shared/model/margen-newo-grupos.model';

describe('Component Tests', () => {
  describe('MargenNewoGrupos Management Component', () => {
    let comp: MargenNewoGruposComponent;
    let fixture: ComponentFixture<MargenNewoGruposComponent>;
    let service: MargenNewoGruposService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MargenNewoGruposComponent],
        providers: []
      })
        .overrideTemplate(MargenNewoGruposComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MargenNewoGruposComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MargenNewoGruposService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MargenNewoGrupos(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.margenNewoGrupos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
