import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICiudad } from 'app/shared/model/ciudad.model';
import { AccountService } from 'app/core';
import { CiudadService } from './ciudad.service';

@Component({
  selector: 'jhi-ciudad',
  templateUrl: './ciudad.component.html'
})
export class CiudadComponent implements OnInit, OnDestroy {
  ciudads: ICiudad[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected ciudadService: CiudadService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.ciudadService
      .query()
      .pipe(
        filter((res: HttpResponse<ICiudad[]>) => res.ok),
        map((res: HttpResponse<ICiudad[]>) => res.body)
      )
      .subscribe(
        (res: ICiudad[]) => {
          this.ciudads = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInCiudads();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ICiudad) {
    return item.id;
  }

  registerChangeInCiudads() {
    this.eventSubscriber = this.eventManager.subscribe('ciudadListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
