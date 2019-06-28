import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICategoriaContenidos } from 'app/shared/model/categoria-contenidos.model';
import { AccountService } from 'app/core';
import { CategoriaContenidosService } from './categoria-contenidos.service';

@Component({
  selector: 'jhi-categoria-contenidos',
  templateUrl: './categoria-contenidos.component.html'
})
export class CategoriaContenidosComponent implements OnInit, OnDestroy {
  categoriaContenidos: ICategoriaContenidos[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected categoriaContenidosService: CategoriaContenidosService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.categoriaContenidosService
      .query()
      .pipe(
        filter((res: HttpResponse<ICategoriaContenidos[]>) => res.ok),
        map((res: HttpResponse<ICategoriaContenidos[]>) => res.body)
      )
      .subscribe(
        (res: ICategoriaContenidos[]) => {
          this.categoriaContenidos = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInCategoriaContenidos();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ICategoriaContenidos) {
    return item.id;
  }

  registerChangeInCategoriaContenidos() {
    this.eventSubscriber = this.eventManager.subscribe('categoriaContenidosListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
