import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGrupos } from 'app/shared/model/grupos.model';
import { GruposService } from './grupos.service';

@Component({
  selector: 'jhi-grupos-delete-dialog',
  templateUrl: './grupos-delete-dialog.component.html'
})
export class GruposDeleteDialogComponent {
  grupos: IGrupos;

  constructor(protected gruposService: GruposService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.gruposService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'gruposListModification',
        content: 'Deleted an grupos'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-grupos-delete-popup',
  template: ''
})
export class GruposDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ grupos }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(GruposDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.grupos = grupos;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/grupos', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/grupos', { outlets: { popup: null } }]);
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
