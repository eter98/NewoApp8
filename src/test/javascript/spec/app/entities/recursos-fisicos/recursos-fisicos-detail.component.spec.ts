/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { RecursosFisicosDetailComponent } from 'app/entities/recursos-fisicos/recursos-fisicos-detail.component';
import { RecursosFisicos } from 'app/shared/model/recursos-fisicos.model';

describe('Component Tests', () => {
  describe('RecursosFisicos Management Detail Component', () => {
    let comp: RecursosFisicosDetailComponent;
    let fixture: ComponentFixture<RecursosFisicosDetailComponent>;
    const route = ({ data: of({ recursosFisicos: new RecursosFisicos(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [RecursosFisicosDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(RecursosFisicosDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RecursosFisicosDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.recursosFisicos).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
