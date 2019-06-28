import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IEspaciosReserva } from 'app/shared/model/espacios-reserva.model';
import { AccountService } from 'app/core';
import { EspaciosReservaService } from './espacios-reserva.service';

@Component({
  selector: 'jhi-espacios-reserva',
  templateUrl: './espacios-reserva.component.html'
})
export class EspaciosReservaComponent implements OnInit, OnDestroy {
  espaciosReservas: IEspaciosReserva[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected espaciosReservaService: EspaciosReservaService,
    protected jhiAlertService: JhiAlertService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.espaciosReservaService
      .query()
      .pipe(
        filter((res: HttpResponse<IEspaciosReserva[]>) => res.ok),
        map((res: HttpResponse<IEspaciosReserva[]>) => res.body)
      )
      .subscribe(
        (res: IEspaciosReserva[]) => {
          this.espaciosReservas = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInEspaciosReservas();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IEspaciosReserva) {
    return item.id;
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  registerChangeInEspaciosReservas() {
    this.eventSubscriber = this.eventManager.subscribe('espaciosReservaListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
