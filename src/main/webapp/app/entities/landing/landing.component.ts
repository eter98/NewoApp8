import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ILanding } from 'app/shared/model/landing.model';
import { AccountService } from 'app/core';
import { LandingService } from './landing.service';

@Component({
  selector: 'jhi-landing',
  templateUrl: './landing.component.html'
})
export class LandingComponent implements OnInit, OnDestroy {
  landings: ILanding[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected landingService: LandingService,
    protected jhiAlertService: JhiAlertService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.landingService
      .query()
      .pipe(
        filter((res: HttpResponse<ILanding[]>) => res.ok),
        map((res: HttpResponse<ILanding[]>) => res.body)
      )
      .subscribe(
        (res: ILanding[]) => {
          this.landings = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInLandings();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ILanding) {
    return item.id;
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  registerChangeInLandings() {
    this.eventSubscriber = this.eventManager.subscribe('landingListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
