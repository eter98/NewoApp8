import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IEspacioLibre } from 'app/shared/model/espacio-libre.model';
import { AccountService } from 'app/core';
import { EspacioLibreService } from './espacio-libre.service';

@Component({
  selector: 'jhi-espacio-libre',
  templateUrl: './espacio-libre.component.html'
})
export class EspacioLibreComponent implements OnInit, OnDestroy {
  espacioLibres: IEspacioLibre[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected espacioLibreService: EspacioLibreService,
    protected jhiAlertService: JhiAlertService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.espacioLibreService
      .query()
      .pipe(
        filter((res: HttpResponse<IEspacioLibre[]>) => res.ok),
        map((res: HttpResponse<IEspacioLibre[]>) => res.body)
      )
      .subscribe(
        (res: IEspacioLibre[]) => {
          this.espacioLibres = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInEspacioLibres();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IEspacioLibre) {
    return item.id;
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  registerChangeInEspacioLibres() {
    this.eventSubscriber = this.eventManager.subscribe('espacioLibreListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
