import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEntradaInvitados } from 'app/shared/model/entrada-invitados.model';
import { AccountService } from 'app/core';
import { EntradaInvitadosService } from './entrada-invitados.service';

@Component({
  selector: 'jhi-entrada-invitados',
  templateUrl: './entrada-invitados.component.html'
})
export class EntradaInvitadosComponent implements OnInit, OnDestroy {
  entradaInvitados: IEntradaInvitados[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected entradaInvitadosService: EntradaInvitadosService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.entradaInvitadosService
      .query()
      .pipe(
        filter((res: HttpResponse<IEntradaInvitados[]>) => res.ok),
        map((res: HttpResponse<IEntradaInvitados[]>) => res.body)
      )
      .subscribe(
        (res: IEntradaInvitados[]) => {
          this.entradaInvitados = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInEntradaInvitados();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IEntradaInvitados) {
    return item.id;
  }

  registerChangeInEntradaInvitados() {
    this.eventSubscriber = this.eventManager.subscribe('entradaInvitadosListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
