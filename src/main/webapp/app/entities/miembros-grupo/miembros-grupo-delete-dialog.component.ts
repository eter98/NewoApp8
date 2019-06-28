import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMiembrosGrupo } from 'app/shared/model/miembros-grupo.model';
import { MiembrosGrupoService } from './miembros-grupo.service';

@Component({
  selector: 'jhi-miembros-grupo-delete-dialog',
  templateUrl: './miembros-grupo-delete-dialog.component.html'
})
export class MiembrosGrupoDeleteDialogComponent {
  miembrosGrupo: IMiembrosGrupo;

  constructor(
    protected miembrosGrupoService: MiembrosGrupoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.miembrosGrupoService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'miembrosGrupoListModification',
        content: 'Deleted an miembrosGrupo'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-miembros-grupo-delete-popup',
  template: ''
})
export class MiembrosGrupoDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ miembrosGrupo }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MiembrosGrupoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.miembrosGrupo = miembrosGrupo;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/miembros-grupo', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/miembros-grupo', { outlets: { popup: null } }]);
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
