/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewoApp8TestModule } from '../../../test.module';
import { MargenNewoBlogComponent } from 'app/entities/margen-newo-blog/margen-newo-blog.component';
import { MargenNewoBlogService } from 'app/entities/margen-newo-blog/margen-newo-blog.service';
import { MargenNewoBlog } from 'app/shared/model/margen-newo-blog.model';

describe('Component Tests', () => {
  describe('MargenNewoBlog Management Component', () => {
    let comp: MargenNewoBlogComponent;
    let fixture: ComponentFixture<MargenNewoBlogComponent>;
    let service: MargenNewoBlogService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MargenNewoBlogComponent],
        providers: []
      })
        .overrideTemplate(MargenNewoBlogComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MargenNewoBlogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MargenNewoBlogService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MargenNewoBlog(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.margenNewoBlogs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
