import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPrepagoConsumo } from 'app/shared/model/prepago-consumo.model';
import { PrepagoConsumoService } from './prepago-consumo.service';

@Component({
  selector: 'jhi-prepago-consumo-delete-dialog',
  templateUrl: './prepago-consumo-delete-dialog.component.html'
})
export class PrepagoConsumoDeleteDialogComponent {
  prepagoConsumo: IPrepagoConsumo;

  constructor(
    protected prepagoConsumoService: PrepagoConsumoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.prepagoConsumoService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'prepagoConsumoListModification',
        content: 'Deleted an prepagoConsumo'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-prepago-consumo-delete-popup',
  template: ''
})
export class PrepagoConsumoDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ prepagoConsumo }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PrepagoConsumoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.prepagoConsumo = prepagoConsumo;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/prepago-consumo', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/prepago-consumo', { outlets: { popup: null } }]);
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
