/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewoApp8TestModule } from '../../../test.module';
import { MargenNewoEventosComponent } from 'app/entities/margen-newo-eventos/margen-newo-eventos.component';
import { MargenNewoEventosService } from 'app/entities/margen-newo-eventos/margen-newo-eventos.service';
import { MargenNewoEventos } from 'app/shared/model/margen-newo-eventos.model';

describe('Component Tests', () => {
  describe('MargenNewoEventos Management Component', () => {
    let comp: MargenNewoEventosComponent;
    let fixture: ComponentFixture<MargenNewoEventosComponent>;
    let service: MargenNewoEventosService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MargenNewoEventosComponent],
        providers: []
      })
        .overrideTemplate(MargenNewoEventosComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MargenNewoEventosComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MargenNewoEventosService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MargenNewoEventos(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.margenNewoEventos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
