import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRecursosFisicos } from 'app/shared/model/recursos-fisicos.model';
import { RecursosFisicosService } from './recursos-fisicos.service';

@Component({
  selector: 'jhi-recursos-fisicos-delete-dialog',
  templateUrl: './recursos-fisicos-delete-dialog.component.html'
})
export class RecursosFisicosDeleteDialogComponent {
  recursosFisicos: IRecursosFisicos;

  constructor(
    protected recursosFisicosService: RecursosFisicosService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.recursosFisicosService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'recursosFisicosListModification',
        content: 'Deleted an recursosFisicos'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-recursos-fisicos-delete-popup',
  template: ''
})
export class RecursosFisicosDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ recursosFisicos }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(RecursosFisicosDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.recursosFisicos = recursosFisicos;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/recursos-fisicos', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/recursos-fisicos', { outlets: { popup: null } }]);
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
