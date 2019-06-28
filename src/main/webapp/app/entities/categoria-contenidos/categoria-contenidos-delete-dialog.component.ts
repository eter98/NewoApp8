import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICategoriaContenidos } from 'app/shared/model/categoria-contenidos.model';
import { CategoriaContenidosService } from './categoria-contenidos.service';

@Component({
  selector: 'jhi-categoria-contenidos-delete-dialog',
  templateUrl: './categoria-contenidos-delete-dialog.component.html'
})
export class CategoriaContenidosDeleteDialogComponent {
  categoriaContenidos: ICategoriaContenidos;

  constructor(
    protected categoriaContenidosService: CategoriaContenidosService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.categoriaContenidosService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'categoriaContenidosListModification',
        content: 'Deleted an categoriaContenidos'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-categoria-contenidos-delete-popup',
  template: ''
})
export class CategoriaContenidosDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ categoriaContenidos }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CategoriaContenidosDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.categoriaContenidos = categoriaContenidos;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/categoria-contenidos', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/categoria-contenidos', { outlets: { popup: null } }]);
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
