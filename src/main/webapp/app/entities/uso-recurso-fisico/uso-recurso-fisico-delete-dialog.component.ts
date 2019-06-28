import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUsoRecursoFisico } from 'app/shared/model/uso-recurso-fisico.model';
import { UsoRecursoFisicoService } from './uso-recurso-fisico.service';

@Component({
  selector: 'jhi-uso-recurso-fisico-delete-dialog',
  templateUrl: './uso-recurso-fisico-delete-dialog.component.html'
})
export class UsoRecursoFisicoDeleteDialogComponent {
  usoRecursoFisico: IUsoRecursoFisico;

  constructor(
    protected usoRecursoFisicoService: UsoRecursoFisicoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.usoRecursoFisicoService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'usoRecursoFisicoListModification',
        content: 'Deleted an usoRecursoFisico'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-uso-recurso-fisico-delete-popup',
  template: ''
})
export class UsoRecursoFisicoDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ usoRecursoFisico }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(UsoRecursoFisicoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.usoRecursoFisico = usoRecursoFisico;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/uso-recurso-fisico', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/uso-recurso-fisico', { outlets: { popup: null } }]);
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
