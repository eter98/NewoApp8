import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IMargenNewoEventos } from 'app/shared/model/margen-newo-eventos.model';
import { AccountService } from 'app/core';
import { MargenNewoEventosService } from './margen-newo-eventos.service';

@Component({
  selector: 'jhi-margen-newo-eventos',
  templateUrl: './margen-newo-eventos.component.html'
})
export class MargenNewoEventosComponent implements OnInit, OnDestroy {
  margenNewoEventos: IMargenNewoEventos[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected margenNewoEventosService: MargenNewoEventosService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.margenNewoEventosService
      .query()
      .pipe(
        filter((res: HttpResponse<IMargenNewoEventos[]>) => res.ok),
        map((res: HttpResponse<IMargenNewoEventos[]>) => res.body)
      )
      .subscribe(
        (res: IMargenNewoEventos[]) => {
          this.margenNewoEventos = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInMargenNewoEventos();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IMargenNewoEventos) {
    return item.id;
  }

  registerChangeInMargenNewoEventos() {
    this.eventSubscriber = this.eventManager.subscribe('margenNewoEventosListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
