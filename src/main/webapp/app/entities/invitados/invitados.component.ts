import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IInvitados } from 'app/shared/model/invitados.model';
import { AccountService } from 'app/core';
import { InvitadosService } from './invitados.service';

@Component({
  selector: 'jhi-invitados',
  templateUrl: './invitados.component.html'
})
export class InvitadosComponent implements OnInit, OnDestroy {
  invitados: IInvitados[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected invitadosService: InvitadosService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.invitadosService
      .query()
      .pipe(
        filter((res: HttpResponse<IInvitados[]>) => res.ok),
        map((res: HttpResponse<IInvitados[]>) => res.body)
      )
      .subscribe(
        (res: IInvitados[]) => {
          this.invitados = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInInvitados();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IInvitados) {
    return item.id;
  }

  registerChangeInInvitados() {
    this.eventSubscriber = this.eventManager.subscribe('invitadosListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
