import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IConsumoMarket } from 'app/shared/model/consumo-market.model';
import { AccountService } from 'app/core';
import { ConsumoMarketService } from './consumo-market.service';

@Component({
  selector: 'jhi-consumo-market',
  templateUrl: './consumo-market.component.html'
})
export class ConsumoMarketComponent implements OnInit, OnDestroy {
  consumoMarkets: IConsumoMarket[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected consumoMarketService: ConsumoMarketService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.consumoMarketService
      .query()
      .pipe(
        filter((res: HttpResponse<IConsumoMarket[]>) => res.ok),
        map((res: HttpResponse<IConsumoMarket[]>) => res.body)
      )
      .subscribe(
        (res: IConsumoMarket[]) => {
          this.consumoMarkets = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInConsumoMarkets();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IConsumoMarket) {
    return item.id;
  }

  registerChangeInConsumoMarkets() {
    this.eventSubscriber = this.eventManager.subscribe('consumoMarketListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
