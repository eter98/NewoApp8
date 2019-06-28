import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IMiembros } from 'app/shared/model/miembros.model';
import { AccountService } from 'app/core';
import { MiembrosService } from './miembros.service';

@Component({
  selector: 'jhi-miembros',
  templateUrl: './miembros.component.html'
})
export class MiembrosComponent implements OnInit, OnDestroy {
  miembros: IMiembros[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected miembrosService: MiembrosService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.miembrosService
      .query()
      .pipe(
        filter((res: HttpResponse<IMiembros[]>) => res.ok),
        map((res: HttpResponse<IMiembros[]>) => res.body)
      )
      .subscribe(
        (res: IMiembros[]) => {
          this.miembros = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInMiembros();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IMiembros) {
    return item.id;
  }

  registerChangeInMiembros() {
    this.eventSubscriber = this.eventManager.subscribe('miembrosListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
