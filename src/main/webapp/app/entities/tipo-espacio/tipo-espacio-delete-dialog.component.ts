import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoEspacio } from 'app/shared/model/tipo-espacio.model';
import { TipoEspacioService } from './tipo-espacio.service';

@Component({
  selector: 'jhi-tipo-espacio-delete-dialog',
  templateUrl: './tipo-espacio-delete-dialog.component.html'
})
export class TipoEspacioDeleteDialogComponent {
  tipoEspacio: ITipoEspacio;

  constructor(
    protected tipoEspacioService: TipoEspacioService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.tipoEspacioService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'tipoEspacioListModification',
        content: 'Deleted an tipoEspacio'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-tipo-espacio-delete-popup',
  template: ''
})
export class TipoEspacioDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ tipoEspacio }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TipoEspacioDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.tipoEspacio = tipoEspacio;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/tipo-espacio', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/tipo-espacio', { outlets: { popup: null } }]);
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
