/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NewoApp8TestModule } from '../../../test.module';
import { MargenNewoBlogDeleteDialogComponent } from 'app/entities/margen-newo-blog/margen-newo-blog-delete-dialog.component';
import { MargenNewoBlogService } from 'app/entities/margen-newo-blog/margen-newo-blog.service';

describe('Component Tests', () => {
  describe('MargenNewoBlog Management Delete Component', () => {
    let comp: MargenNewoBlogDeleteDialogComponent;
    let fixture: ComponentFixture<MargenNewoBlogDeleteDialogComponent>;
    let service: MargenNewoBlogService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MargenNewoBlogDeleteDialogComponent]
      })
        .overrideTemplate(MargenNewoBlogDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MargenNewoBlogDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MargenNewoBlogService);
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
