/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NewoApp8TestModule } from '../../../test.module';
import { MiembrosGrupoDeleteDialogComponent } from 'app/entities/miembros-grupo/miembros-grupo-delete-dialog.component';
import { MiembrosGrupoService } from 'app/entities/miembros-grupo/miembros-grupo.service';

describe('Component Tests', () => {
  describe('MiembrosGrupo Management Delete Component', () => {
    let comp: MiembrosGrupoDeleteDialogComponent;
    let fixture: ComponentFixture<MiembrosGrupoDeleteDialogComponent>;
    let service: MiembrosGrupoService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MiembrosGrupoDeleteDialogComponent]
      })
        .overrideTemplate(MiembrosGrupoDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MiembrosGrupoDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MiembrosGrupoService);
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
