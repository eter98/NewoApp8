import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITipoPrepagoConsumo } from 'app/shared/model/tipo-prepago-consumo.model';
import { AccountService } from 'app/core';
import { TipoPrepagoConsumoService } from './tipo-prepago-consumo.service';

@Component({
  selector: 'jhi-tipo-prepago-consumo',
  templateUrl: './tipo-prepago-consumo.component.html'
})
export class TipoPrepagoConsumoComponent implements OnInit, OnDestroy {
  tipoPrepagoConsumos: ITipoPrepagoConsumo[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected tipoPrepagoConsumoService: TipoPrepagoConsumoService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.tipoPrepagoConsumoService
      .query()
      .pipe(
        filter((res: HttpResponse<ITipoPrepagoConsumo[]>) => res.ok),
        map((res: HttpResponse<ITipoPrepagoConsumo[]>) => res.body)
      )
      .subscribe(
        (res: ITipoPrepagoConsumo[]) => {
          this.tipoPrepagoConsumos = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInTipoPrepagoConsumos();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ITipoPrepagoConsumo) {
    return item.id;
  }

  registerChangeInTipoPrepagoConsumos() {
    this.eventSubscriber = this.eventManager.subscribe('tipoPrepagoConsumoListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
