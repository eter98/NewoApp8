import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IBeneficio } from 'app/shared/model/beneficio.model';
import { AccountService } from 'app/core';
import { BeneficioService } from './beneficio.service';

@Component({
  selector: 'jhi-beneficio',
  templateUrl: './beneficio.component.html'
})
export class BeneficioComponent implements OnInit, OnDestroy {
  beneficios: IBeneficio[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected beneficioService: BeneficioService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.beneficioService
      .query()
      .pipe(
        filter((res: HttpResponse<IBeneficio[]>) => res.ok),
        map((res: HttpResponse<IBeneficio[]>) => res.body)
      )
      .subscribe(
        (res: IBeneficio[]) => {
          this.beneficios = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInBeneficios();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IBeneficio) {
    return item.id;
  }

  registerChangeInBeneficios() {
    this.eventSubscriber = this.eventManager.subscribe('beneficioListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
