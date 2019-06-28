import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoRecurso } from 'app/shared/model/tipo-recurso.model';
import { TipoRecursoService } from './tipo-recurso.service';

@Component({
  selector: 'jhi-tipo-recurso-delete-dialog',
  templateUrl: './tipo-recurso-delete-dialog.component.html'
})
export class TipoRecursoDeleteDialogComponent {
  tipoRecurso: ITipoRecurso;

  constructor(
    protected tipoRecursoService: TipoRecursoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.tipoRecursoService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'tipoRecursoListModification',
        content: 'Deleted an tipoRecurso'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-tipo-recurso-delete-popup',
  template: ''
})
export class TipoRecursoDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ tipoRecurso }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TipoRecursoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.tipoRecurso = tipoRecurso;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/tipo-recurso', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/tipo-recurso', { outlets: { popup: null } }]);
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
