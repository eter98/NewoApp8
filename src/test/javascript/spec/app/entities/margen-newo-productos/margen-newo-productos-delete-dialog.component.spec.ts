/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NewoApp8TestModule } from '../../../test.module';
import { MargenNewoProductosDeleteDialogComponent } from 'app/entities/margen-newo-productos/margen-newo-productos-delete-dialog.component';
import { MargenNewoProductosService } from 'app/entities/margen-newo-productos/margen-newo-productos.service';

describe('Component Tests', () => {
  describe('MargenNewoProductos Management Delete Component', () => {
    let comp: MargenNewoProductosDeleteDialogComponent;
    let fixture: ComponentFixture<MargenNewoProductosDeleteDialogComponent>;
    let service: MargenNewoProductosService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MargenNewoProductosDeleteDialogComponent]
      })
        .overrideTemplate(MargenNewoProductosDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MargenNewoProductosDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MargenNewoProductosService);
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
