/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewoApp8TestModule } from '../../../test.module';
import { TipoPrepagoConsumoComponent } from 'app/entities/tipo-prepago-consumo/tipo-prepago-consumo.component';
import { TipoPrepagoConsumoService } from 'app/entities/tipo-prepago-consumo/tipo-prepago-consumo.service';
import { TipoPrepagoConsumo } from 'app/shared/model/tipo-prepago-consumo.model';

describe('Component Tests', () => {
  describe('TipoPrepagoConsumo Management Component', () => {
    let comp: TipoPrepagoConsumoComponent;
    let fixture: ComponentFixture<TipoPrepagoConsumoComponent>;
    let service: TipoPrepagoConsumoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [TipoPrepagoConsumoComponent],
        providers: []
      })
        .overrideTemplate(TipoPrepagoConsumoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TipoPrepagoConsumoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TipoPrepagoConsumoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TipoPrepagoConsumo(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tipoPrepagoConsumos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
