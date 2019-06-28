import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMargenNewoBlog } from 'app/shared/model/margen-newo-blog.model';
import { MargenNewoBlogService } from './margen-newo-blog.service';

@Component({
  selector: 'jhi-margen-newo-blog-delete-dialog',
  templateUrl: './margen-newo-blog-delete-dialog.component.html'
})
export class MargenNewoBlogDeleteDialogComponent {
  margenNewoBlog: IMargenNewoBlog;

  constructor(
    protected margenNewoBlogService: MargenNewoBlogService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.margenNewoBlogService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'margenNewoBlogListModification',
        content: 'Deleted an margenNewoBlog'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-margen-newo-blog-delete-popup',
  template: ''
})
export class MargenNewoBlogDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ margenNewoBlog }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MargenNewoBlogDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.margenNewoBlog = margenNewoBlog;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/margen-newo-blog', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/margen-newo-blog', { outlets: { popup: null } }]);
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
