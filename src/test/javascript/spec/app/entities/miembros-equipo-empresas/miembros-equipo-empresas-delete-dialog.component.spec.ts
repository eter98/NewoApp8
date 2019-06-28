/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NewoApp8TestModule } from '../../../test.module';
import { MiembrosEquipoEmpresasDeleteDialogComponent } from 'app/entities/miembros-equipo-empresas/miembros-equipo-empresas-delete-dialog.component';
import { MiembrosEquipoEmpresasService } from 'app/entities/miembros-equipo-empresas/miembros-equipo-empresas.service';

describe('Component Tests', () => {
  describe('MiembrosEquipoEmpresas Management Delete Component', () => {
    let comp: MiembrosEquipoEmpresasDeleteDialogComponent;
    let fixture: ComponentFixture<MiembrosEquipoEmpresasDeleteDialogComponent>;
    let service: MiembrosEquipoEmpresasService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp8TestModule],
        declarations: [MiembrosEquipoEmpresasDeleteDialogComponent]
      })
        .overrideTemplate(MiembrosEquipoEmpresasDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MiembrosEquipoEmpresasDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MiembrosEquipoEmpresasService);
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
