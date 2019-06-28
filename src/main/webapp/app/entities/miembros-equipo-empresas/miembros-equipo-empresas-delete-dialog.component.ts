import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMiembrosEquipoEmpresas } from 'app/shared/model/miembros-equipo-empresas.model';
import { MiembrosEquipoEmpresasService } from './miembros-equipo-empresas.service';

@Component({
  selector: 'jhi-miembros-equipo-empresas-delete-dialog',
  templateUrl: './miembros-equipo-empresas-delete-dialog.component.html'
})
export class MiembrosEquipoEmpresasDeleteDialogComponent {
  miembrosEquipoEmpresas: IMiembrosEquipoEmpresas;

  constructor(
    protected miembrosEquipoEmpresasService: MiembrosEquipoEmpresasService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.miembrosEquipoEmpresasService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'miembrosEquipoEmpresasListModification',
        content: 'Deleted an miembrosEquipoEmpresas'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-miembros-equipo-empresas-delete-popup',
  template: ''
})
export class MiembrosEquipoEmpresasDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ miembrosEquipoEmpresas }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MiembrosEquipoEmpresasDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.miembrosEquipoEmpresas = miembrosEquipoEmpresas;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/miembros-equipo-empresas', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/miembros-equipo-empresas', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
