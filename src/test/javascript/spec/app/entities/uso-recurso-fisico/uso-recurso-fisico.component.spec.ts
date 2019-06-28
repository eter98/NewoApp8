/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewoApp8TestModule } from '../../../test.module';
import { UsoRecursoFisicoComponent } from 'app/entities/uso-recurso-fisico/uso-recurso-fisico.component';
import { UsoRecursoFisicoService } from 'app/entities/uso-recurso-fisico/uso-recurso-fisico.service';
import { UsoRecursoFisico } from 'app/shared/model/uso-recurso-fisico.model';

describe('Component Tests', () => {
  describe('UsoRecursoFisico Management Component', () => {
    let comp: UsoRecursoFisicoComponent;
    let fixture: ComponentFixture<UsoRecursoFisicoComponent>;
    let service: UsoRecursoFisicoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [UsoRecursoFisicoComponent],
        providers: []
      })
        .overrideTemplate(UsoRecursoFisicoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UsoRecursoFisicoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UsoRecursoFisicoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UsoRecursoFisico(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.usoRecursoFisicos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
