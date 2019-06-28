import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IRecursosFisicos } from 'app/shared/model/recursos-fisicos.model';
import { AccountService } from 'app/core';
import { RecursosFisicosService } from './recursos-fisicos.service';

@Component({
  selector: 'jhi-recursos-fisicos',
  templateUrl: './recursos-fisicos.component.html'
})
export class RecursosFisicosComponent implements OnInit, OnDestroy {
  recursosFisicos: IRecursosFisicos[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected recursosFisicosService: RecursosFisicosService,
    protected jhiAlertService: JhiAlertService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.recursosFisicosService
      .query()
      .pipe(
        filter((res: HttpResponse<IRecursosFisicos[]>) => res.ok),
        map((res: HttpResponse<IRecursosFisicos[]>) => res.body)
      )
      .subscribe(
        (res: IRecursosFisicos[]) => {
          this.recursosFisicos = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInRecursosFisicos();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IRecursosFisicos) {
    return item.id;
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  registerChangeInRecursosFisicos() {
    this.eventSubscriber = this.eventManager.subscribe('recursosFisicosListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
