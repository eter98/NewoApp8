import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEntradaMiembros } from 'app/shared/model/entrada-miembros.model';
import { AccountService } from 'app/core';
import { EntradaMiembrosService } from './entrada-miembros.service';

@Component({
  selector: 'jhi-entrada-miembros',
  templateUrl: './entrada-miembros.component.html'
})
export class EntradaMiembrosComponent implements OnInit, OnDestroy {
  entradaMiembros: IEntradaMiembros[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected entradaMiembrosService: EntradaMiembrosService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.entradaMiembrosService
      .query()
      .pipe(
        filter((res: HttpResponse<IEntradaMiembros[]>) => res.ok),
        map((res: HttpResponse<IEntradaMiembros[]>) => res.body)
      )
      .subscribe(
        (res: IEntradaMiembros[]) => {
          this.entradaMiembros = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInEntradaMiembros();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IEntradaMiembros) {
    return item.id;
  }

  registerChangeInEntradaMiembros() {
    this.eventSubscriber = this.eventManager.subscribe('entradaMiembrosListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
