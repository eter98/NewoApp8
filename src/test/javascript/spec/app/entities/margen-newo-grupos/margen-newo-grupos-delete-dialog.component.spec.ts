/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NewoApp8TestModule } from '../../../test.module';
import { MargenNewoGruposDeleteDialogComponent } from 'app/entities/margen-newo-grupos/margen-newo-grupos-delete-dialog.component';
import { MargenNewoGruposService } from 'app/entities/margen-newo-grupos/margen-newo-grupos.service';

describe('Component Tests', () => {
  describe('MargenNewoGrupos Management Delete Component', () => {
    let comp: MargenNewoGruposDeleteDialogComponent;
    let fixture: ComponentFixture<MargenNewoGruposDeleteDialogComponent>;
    let service: MargenNewoGruposService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MargenNewoGruposDeleteDialogComponent]
      })
        .overrideTemplate(MargenNewoGruposDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MargenNewoGruposDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MargenNewoGruposService);
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
