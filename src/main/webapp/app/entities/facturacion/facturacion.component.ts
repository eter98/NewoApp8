import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IFacturacion } from 'app/shared/model/facturacion.model';
import { AccountService } from 'app/core';
import { FacturacionService } from './facturacion.service';

@Component({
  selector: 'jhi-facturacion',
  templateUrl: './facturacion.component.html'
})
export class FacturacionComponent implements OnInit, OnDestroy {
  facturacions: IFacturacion[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected facturacionService: FacturacionService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.facturacionService
      .query()
      .pipe(
        filter((res: HttpResponse<IFacturacion[]>) => res.ok),
        map((res: HttpResponse<IFacturacion[]>) => res.body)
      )
      .subscribe(
        (res: IFacturacion[]) => {
          this.facturacions = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInFacturacions();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IFacturacion) {
    return item.id;
  }

  registerChangeInFacturacions() {
    this.eventSubscriber = this.eventManager.subscribe('facturacionListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
