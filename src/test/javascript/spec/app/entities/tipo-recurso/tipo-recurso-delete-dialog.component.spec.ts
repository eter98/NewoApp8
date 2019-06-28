/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NewoApp8TestModule } from '../../../test.module';
import { TipoRecursoDeleteDialogComponent } from 'app/entities/tipo-recurso/tipo-recurso-delete-dialog.component';
import { TipoRecursoService } from 'app/entities/tipo-recurso/tipo-recurso.service';

describe('Component Tests', () => {
  describe('TipoRecurso Management Delete Component', () => {
    let comp: TipoRecursoDeleteDialogComponent;
    let fixture: ComponentFixture<TipoRecursoDeleteDialogComponent>;
    let service: TipoRecursoService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [TipoRecursoDeleteDialogComponent]
      })
        .overrideTemplate(TipoRecursoDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TipoRecursoDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TipoRecursoService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
