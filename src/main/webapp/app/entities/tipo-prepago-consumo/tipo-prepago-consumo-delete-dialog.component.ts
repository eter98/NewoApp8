import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoPrepagoConsumo } from 'app/shared/model/tipo-prepago-consumo.model';
import { TipoPrepagoConsumoService } from './tipo-prepago-consumo.service';

@Component({
  selector: 'jhi-tipo-prepago-consumo-delete-dialog',
  templateUrl: './tipo-prepago-consumo-delete-dialog.component.html'
})
export class TipoPrepagoConsumoDeleteDialogComponent {
  tipoPrepagoConsumo: ITipoPrepagoConsumo;

  constructor(
    protected tipoPrepagoConsumoService: TipoPrepagoConsumoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.tipoPrepagoConsumoService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'tipoPrepagoConsumoListModification',
        content: 'Deleted an tipoPrepagoConsumo'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-tipo-prepago-consumo-delete-popup',
  template: ''
})
export class TipoPrepagoConsumoDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ tipoPrepagoConsumo }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TipoPrepagoConsumoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.tipoPrepagoConsumo = tipoPrepagoConsumo;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/tipo-prepago-consumo', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/tipo-prepago-consumo', { outlets: { popup: null } }]);
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
