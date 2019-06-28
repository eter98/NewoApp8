/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NewoApp8TestModule } from '../../../test.module';
import { MargenNewoEventosDeleteDialogComponent } from 'app/entities/margen-newo-eventos/margen-newo-eventos-delete-dialog.component';
import { MargenNewoEventosService } from 'app/entities/margen-newo-eventos/margen-newo-eventos.service';

describe('Component Tests', () => {
  describe('MargenNewoEventos Management Delete Component', () => {
    let comp: MargenNewoEventosDeleteDialogComponent;
    let fixture: ComponentFixture<MargenNewoEventosDeleteDialogComponent>;
    let service: MargenNewoEventosService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MargenNewoEventosDeleteDialogComponent]
      })
        .overrideTemplate(MargenNewoEventosDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MargenNewoEventosDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MargenNewoEventosService);
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
