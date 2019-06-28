import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConsumoMarket } from 'app/shared/model/consumo-market.model';
import { ConsumoMarketService } from './consumo-market.service';

@Component({
  selector: 'jhi-consumo-market-delete-dialog',
  templateUrl: './consumo-market-delete-dialog.component.html'
})
export class ConsumoMarketDeleteDialogComponent {
  consumoMarket: IConsumoMarket;

  constructor(
    protected consumoMarketService: ConsumoMarketService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.consumoMarketService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'consumoMarketListModification',
        content: 'Deleted an consumoMarket'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-consumo-market-delete-popup',
  template: ''
})
export class ConsumoMarketDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ consumoMarket }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ConsumoMarketDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.consumoMarket = consumoMarket;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/consumo-market', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/consumo-market', { outlets: { popup: null } }]);
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
