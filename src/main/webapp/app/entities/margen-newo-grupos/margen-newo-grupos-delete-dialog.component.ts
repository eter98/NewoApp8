import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMargenNewoGrupos } from 'app/shared/model/margen-newo-grupos.model';
import { MargenNewoGruposService } from './margen-newo-grupos.service';

@Component({
  selector: 'jhi-margen-newo-grupos-delete-dialog',
  templateUrl: './margen-newo-grupos-delete-dialog.component.html'
})
export class MargenNewoGruposDeleteDialogComponent {
  margenNewoGrupos: IMargenNewoGrupos;

  constructor(
    protected margenNewoGruposService: MargenNewoGruposService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.margenNewoGruposService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'margenNewoGruposListModification',
        content: 'Deleted an margenNewoGrupos'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-margen-newo-grupos-delete-popup',
  template: ''
})
export class MargenNewoGruposDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ margenNewoGrupos }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MargenNewoGruposDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.margenNewoGrupos = margenNewoGrupos;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/margen-newo-grupos', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/margen-newo-grupos', { outlets: { popup: null } }]);
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
