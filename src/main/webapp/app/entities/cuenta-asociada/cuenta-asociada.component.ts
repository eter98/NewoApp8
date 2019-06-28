import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICuentaAsociada } from 'app/shared/model/cuenta-asociada.model';
import { AccountService } from 'app/core';
import { CuentaAsociadaService } from './cuenta-asociada.service';

@Component({
  selector: 'jhi-cuenta-asociada',
  templateUrl: './cuenta-asociada.component.html'
})
export class CuentaAsociadaComponent implements OnInit, OnDestroy {
  cuentaAsociadas: ICuentaAsociada[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected cuentaAsociadaService: CuentaAsociadaService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.cuentaAsociadaService
      .query()
      .pipe(
        filter((res: HttpResponse<ICuentaAsociada[]>) => res.ok),
        map((res: HttpResponse<ICuentaAsociada[]>) => res.body)
      )
      .subscribe(
        (res: ICuentaAsociada[]) => {
          this.cuentaAsociadas = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInCuentaAsociadas();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ICuentaAsociada) {
    return item.id;
  }

  registerChangeInCuentaAsociadas() {
    this.eventSubscriber = this.eventManager.subscribe('cuentaAsociadaListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
