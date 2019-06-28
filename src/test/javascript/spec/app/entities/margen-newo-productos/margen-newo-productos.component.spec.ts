/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewoApp8TestModule } from '../../../test.module';
import { MargenNewoProductosComponent } from 'app/entities/margen-newo-productos/margen-newo-productos.component';
import { MargenNewoProductosService } from 'app/entities/margen-newo-productos/margen-newo-productos.service';
import { MargenNewoProductos } from 'app/shared/model/margen-newo-productos.model';

describe('Component Tests', () => {
  describe('MargenNewoProductos Management Component', () => {
    let comp: MargenNewoProductosComponent;
    let fixture: ComponentFixture<MargenNewoProductosComponent>;
    let service: MargenNewoProductosService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MargenNewoProductosComponent],
        providers: []
      })
        .overrideTemplate(MargenNewoProductosComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MargenNewoProductosComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MargenNewoProductosService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MargenNewoProductos(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.margenNewoProductos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
