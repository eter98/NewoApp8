/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewoApp8TestModule } from '../../../test.module';
import { UsoRecursoFisicoDetailComponent } from 'app/entities/uso-recurso-fisico/uso-recurso-fisico-detail.component';
import { UsoRecursoFisico } from 'app/shared/model/uso-recurso-fisico.model';

describe('Component Tests', () => {
  describe('UsoRecursoFisico Management Detail Component', () => {
    let comp: UsoRecursoFisicoDetailComponent;
    let fixture: ComponentFixture<UsoRecursoFisicoDetailComponent>;
    const route = ({ data: of({ usoRecursoFisico: new UsoRecursoFisico(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [UsoRecursoFisicoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(UsoRecursoFisicoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UsoRecursoFisicoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.usoRecursoFisico).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
