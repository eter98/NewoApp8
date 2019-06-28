import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMargenNewoProductos } from 'app/shared/model/margen-newo-productos.model';
import { MargenNewoProductosService } from './margen-newo-productos.service';

@Component({
  selector: 'jhi-margen-newo-productos-delete-dialog',
  templateUrl: './margen-newo-productos-delete-dialog.component.html'
})
export class MargenNewoProductosDeleteDialogComponent {
  margenNewoProductos: IMargenNewoProductos;

  constructor(
    protected margenNewoProductosService: MargenNewoProductosService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.margenNewoProductosService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'margenNewoProductosListModification',
        content: 'Deleted an margenNewoProductos'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-margen-newo-productos-delete-popup',
  template: ''
})
export class MargenNewoProductosDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ margenNewoProductos }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MargenNewoProductosDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.margenNewoProductos = margenNewoProductos;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/margen-newo-productos', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/margen-newo-productos', { outlets: { popup: null } }]);
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
