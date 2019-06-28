import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IEvento } from 'app/shared/model/evento.model';
import { AccountService } from 'app/core';
import { EventoService } from './evento.service';

@Component({
  selector: 'jhi-evento',
  templateUrl: './evento.component.html'
})
export class EventoComponent implements OnInit, OnDestroy {
  eventos: IEvento[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected eventoService: EventoService,
    protected jhiAlertService: JhiAlertService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.eventoService
      .query()
      .pipe(
        filter((res: HttpResponse<IEvento[]>) => res.ok),
        map((res: HttpResponse<IEvento[]>) => res.body)
      )
      .subscribe(
        (res: IEvento[]) => {
          this.eventos = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInEventos();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IEvento) {
    return item.id;
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  registerChangeInEventos() {
    this.eventSubscriber = this.eventManager.subscribe('eventoListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
