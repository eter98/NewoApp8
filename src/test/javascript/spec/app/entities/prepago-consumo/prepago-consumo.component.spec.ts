/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewoApp8TestModule } from '../../../test.module';
import { PrepagoConsumoComponent } from 'app/entities/prepago-consumo/prepago-consumo.component';
import { PrepagoConsumoService } from 'app/entities/prepago-consumo/prepago-consumo.service';
import { PrepagoConsumo } from 'app/shared/model/prepago-consumo.model';

describe('Component Tests', () => {
  describe('PrepagoConsumo Management Component', () => {
    let comp: PrepagoConsumoComponent;
    let fixture: ComponentFixture<PrepagoConsumoComponent>;
    let service: PrepagoConsumoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [PrepagoConsumoComponent],
        providers: []
      })
        .overrideTemplate(PrepagoConsumoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PrepagoConsumoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PrepagoConsumoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PrepagoConsumo(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.prepagoConsumos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
