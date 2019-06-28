import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IGrupos } from 'app/shared/model/grupos.model';
import { AccountService } from 'app/core';
import { GruposService } from './grupos.service';

@Component({
  selector: 'jhi-grupos',
  templateUrl: './grupos.component.html'
})
export class GruposComponent implements OnInit, OnDestroy {
  grupos: IGrupos[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected gruposService: GruposService,
    protected jhiAlertService: JhiAlertService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.gruposService
      .query()
      .pipe(
        filter((res: HttpResponse<IGrupos[]>) => res.ok),
        map((res: HttpResponse<IGrupos[]>) => res.body)
      )
      .subscribe(
        (res: IGrupos[]) => {
          this.grupos = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInGrupos();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IGrupos) {
    return item.id;
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  registerChangeInGrupos() {
    this.eventSubscriber = this.eventManager.subscribe('gruposListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
