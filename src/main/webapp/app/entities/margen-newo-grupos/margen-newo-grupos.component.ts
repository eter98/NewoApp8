import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IMargenNewoGrupos } from 'app/shared/model/margen-newo-grupos.model';
import { AccountService } from 'app/core';
import { MargenNewoGruposService } from './margen-newo-grupos.service';

@Component({
  selector: 'jhi-margen-newo-grupos',
  templateUrl: './margen-newo-grupos.component.html'
})
export class MargenNewoGruposComponent implements OnInit, OnDestroy {
  margenNewoGrupos: IMargenNewoGrupos[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected margenNewoGruposService: MargenNewoGruposService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.margenNewoGruposService
      .query()
      .pipe(
        filter((res: HttpResponse<IMargenNewoGrupos[]>) => res.ok),
        map((res: HttpResponse<IMargenNewoGrupos[]>) => res.body)
      )
      .subscribe(
        (res: IMargenNewoGrupos[]) => {
          this.margenNewoGrupos = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInMargenNewoGrupos();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IMargenNewoGrupos) {
    return item.id;
  }

  registerChangeInMargenNewoGrupos() {
    this.eventSubscriber = this.eventManager.subscribe('margenNewoGruposListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
