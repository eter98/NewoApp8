/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewoApp8TestModule } from '../../../test.module';
import { GruposComponent } from 'app/entities/grupos/grupos.component';
import { GruposService } from 'app/entities/grupos/grupos.service';
import { Grupos } from 'app/shared/model/grupos.model';

describe('Component Tests', () => {
  describe('Grupos Management Component', () => {
    let comp: GruposComponent;
    let fixture: ComponentFixture<GruposComponent>;
    let service: GruposService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [GruposComponent],
        providers: []
      })
        .overrideTemplate(GruposComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GruposComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GruposService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Grupos(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.grupos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
