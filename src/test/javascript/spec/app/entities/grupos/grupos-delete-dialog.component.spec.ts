/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NewoApp8TestModule } from '../../../test.module';
import { GruposDeleteDialogComponent } from 'app/entities/grupos/grupos-delete-dialog.component';
import { GruposService } from 'app/entities/grupos/grupos.service';

describe('Component Tests', () => {
  describe('Grupos Management Delete Component', () => {
    let comp: GruposDeleteDialogComponent;
    let fixture: ComponentFixture<GruposDeleteDialogComponent>;
    let service: GruposService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [GruposDeleteDialogComponent]
      })
        .overrideTemplate(GruposDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GruposDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GruposService);
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
