/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { MargenNewoBlogDetailComponent } from 'app/entities/margen-newo-blog/margen-newo-blog-detail.component';
import { MargenNewoBlog } from 'app/shared/model/margen-newo-blog.model';

describe('Component Tests', () => {
  describe('MargenNewoBlog Management Detail Component', () => {
    let comp: MargenNewoBlogDetailComponent;
    let fixture: ComponentFixture<MargenNewoBlogDetailComponent>;
    const route = ({ data: of({ margenNewoBlog: new MargenNewoBlog(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MargenNewoBlogDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MargenNewoBlogDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MargenNewoBlogDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.margenNewoBlog).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
