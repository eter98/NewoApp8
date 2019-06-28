/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewoApp8TestModule } from '../../../test.module';
import { TipoEspacioComponent } from 'app/entities/tipo-espacio/tipo-espacio.component';
import { TipoEspacioService } from 'app/entities/tipo-espacio/tipo-espacio.service';
import { TipoEspacio } from 'app/shared/model/tipo-espacio.model';

describe('Component Tests', () => {
  describe('TipoEspacio Management Component', () => {
    let comp: TipoEspacioComponent;
    let fixture: ComponentFixture<TipoEspacioComponent>;
    let service: TipoEspacioService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [TipoEspacioComponent],
        providers: []
      })
        .overrideTemplate(TipoEspacioComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TipoEspacioComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TipoEspacioService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TipoEspacio(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tipoEspacios[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
