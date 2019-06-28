import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPrepagoConsumo } from 'app/shared/model/prepago-consumo.model';
import { AccountService } from 'app/core';
import { PrepagoConsumoService } from './prepago-consumo.service';

@Component({
  selector: 'jhi-prepago-consumo',
  templateUrl: './prepago-consumo.component.html'
})
export class PrepagoConsumoComponent implements OnInit, OnDestroy {
  prepagoConsumos: IPrepagoConsumo[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected prepagoConsumoService: PrepagoConsumoService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.prepagoConsumoService
      .query()
      .pipe(
        filter((res: HttpResponse<IPrepagoConsumo[]>) => res.ok),
        map((res: HttpResponse<IPrepagoConsumo[]>) => res.body)
      )
      .subscribe(
        (res: IPrepagoConsumo[]) => {
          this.prepagoConsumos = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInPrepagoConsumos();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IPrepagoConsumo) {
    return item.id;
  }

  registerChangeInPrepagoConsumos() {
    this.eventSubscriber = this.eventManager.subscribe('prepagoConsumoListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
