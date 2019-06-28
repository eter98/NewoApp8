import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IHostSede } from 'app/shared/model/host-sede.model';
import { AccountService } from 'app/core';
import { HostSedeService } from './host-sede.service';

@Component({
  selector: 'jhi-host-sede',
  templateUrl: './host-sede.component.html'
})
export class HostSedeComponent implements OnInit, OnDestroy {
  hostSedes: IHostSede[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected hostSedeService: HostSedeService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.hostSedeService
      .query()
      .pipe(
        filter((res: HttpResponse<IHostSede[]>) => res.ok),
        map((res: HttpResponse<IHostSede[]>) => res.body)
      )
      .subscribe(
        (res: IHostSede[]) => {
          this.hostSedes = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInHostSedes();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IHostSede) {
    return item.id;
  }

  registerChangeInHostSedes() {
    this.eventSubscriber = this.eventManager.subscribe('hostSedeListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
