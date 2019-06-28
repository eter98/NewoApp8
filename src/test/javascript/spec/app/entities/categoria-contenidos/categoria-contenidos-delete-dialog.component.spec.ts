/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NewoApp8TestModule } from '../../../test.module';
import { CategoriaContenidosDeleteDialogComponent } from 'app/entities/categoria-contenidos/categoria-contenidos-delete-dialog.component';
import { CategoriaContenidosService } from 'app/entities/categoria-contenidos/categoria-contenidos.service';

describe('Component Tests', () => {
  describe('CategoriaContenidos Management Delete Component', () => {
    let comp: CategoriaContenidosDeleteDialogComponent;
    let fixture: ComponentFixture<CategoriaContenidosDeleteDialogComponent>;
    let service: CategoriaContenidosService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [CategoriaContenidosDeleteDialogComponent]
      })
        .overrideTemplate(CategoriaContenidosDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CategoriaContenidosDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CategoriaContenidosService);
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
