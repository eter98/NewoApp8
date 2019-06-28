/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NewoApp8TestModule } from '../../../test.module';
import { PrepagoConsumoDeleteDialogComponent } from 'app/entities/prepago-consumo/prepago-consumo-delete-dialog.component';
import { PrepagoConsumoService } from 'app/entities/prepago-consumo/prepago-consumo.service';

describe('Component Tests', () => {
  describe('PrepagoConsumo Management Delete Component', () => {
    let comp: PrepagoConsumoDeleteDialogComponent;
    let fixture: ComponentFixture<PrepagoConsumoDeleteDialogComponent>;
    let service: PrepagoConsumoService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [PrepagoConsumoDeleteDialogComponent]
      })
        .overrideTemplate(PrepagoConsumoDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PrepagoConsumoDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PrepagoConsumoService);
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
