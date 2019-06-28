import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMargenNewoEventos } from 'app/shared/model/margen-newo-eventos.model';
import { MargenNewoEventosService } from './margen-newo-eventos.service';

@Component({
  selector: 'jhi-margen-newo-eventos-delete-dialog',
  templateUrl: './margen-newo-eventos-delete-dialog.component.html'
})
export class MargenNewoEventosDeleteDialogComponent {
  margenNewoEventos: IMargenNewoEventos;

  constructor(
    protected margenNewoEventosService: MargenNewoEventosService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.margenNewoEventosService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'margenNewoEventosListModification',
        content: 'Deleted an margenNewoEventos'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-margen-newo-eventos-delete-popup',
  template: ''
})
export class MargenNewoEventosDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ margenNewoEventos }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MargenNewoEventosDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.margenNewoEventos = margenNewoEventos;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/margen-newo-eventos', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/margen-newo-eventos', { outlets: { popup: null } }]);
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
