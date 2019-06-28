import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IMargenNewoProductos } from 'app/shared/model/margen-newo-productos.model';
import { AccountService } from 'app/core';
import { MargenNewoProductosService } from './margen-newo-productos.service';

@Component({
  selector: 'jhi-margen-newo-productos',
  templateUrl: './margen-newo-productos.component.html'
})
export class MargenNewoProductosComponent implements OnInit, OnDestroy {
  margenNewoProductos: IMargenNewoProductos[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected margenNewoProductosService: MargenNewoProductosService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.margenNewoProductosService
      .query()
      .pipe(
        filter((res: HttpResponse<IMargenNewoProductos[]>) => res.ok),
        map((res: HttpResponse<IMargenNewoProductos[]>) => res.body)
      )
      .subscribe(
        (res: IMargenNewoProductos[]) => {
          this.margenNewoProductos = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInMargenNewoProductos();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IMargenNewoProductos) {
    return item.id;
  }

  registerChangeInMargenNewoProductos() {
    this.eventSubscriber = this.eventManager.subscribe('margenNewoProductosListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
