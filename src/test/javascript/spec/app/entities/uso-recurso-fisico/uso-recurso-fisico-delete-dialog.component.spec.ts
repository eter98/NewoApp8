/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NewoApp8TestModule } from '../../../test.module';
import { UsoRecursoFisicoDeleteDialogComponent } from 'app/entities/uso-recurso-fisico/uso-recurso-fisico-delete-dialog.component';
import { UsoRecursoFisicoService } from 'app/entities/uso-recurso-fisico/uso-recurso-fisico.service';

describe('Component Tests', () => {
  describe('UsoRecursoFisico Management Delete Component', () => {
    let comp: UsoRecursoFisicoDeleteDialogComponent;
    let fixture: ComponentFixture<UsoRecursoFisicoDeleteDialogComponent>;
    let service: UsoRecursoFisicoService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [UsoRecursoFisicoDeleteDialogComponent]
      })
        .overrideTemplate(UsoRecursoFisicoDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UsoRecursoFisicoDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UsoRecursoFisicoService);
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
