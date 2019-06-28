import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IUsoRecursoFisico } from 'app/shared/model/uso-recurso-fisico.model';
import { AccountService } from 'app/core';
import { UsoRecursoFisicoService } from './uso-recurso-fisico.service';

@Component({
  selector: 'jhi-uso-recurso-fisico',
  templateUrl: './uso-recurso-fisico.component.html'
})
export class UsoRecursoFisicoComponent implements OnInit, OnDestroy {
  usoRecursoFisicos: IUsoRecursoFisico[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected usoRecursoFisicoService: UsoRecursoFisicoService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.usoRecursoFisicoService
      .query()
      .pipe(
        filter((res: HttpResponse<IUsoRecursoFisico[]>) => res.ok),
        map((res: HttpResponse<IUsoRecursoFisico[]>) => res.body)
      )
      .subscribe(
        (res: IUsoRecursoFisico[]) => {
          this.usoRecursoFisicos = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInUsoRecursoFisicos();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IUsoRecursoFisico) {
    return item.id;
  }

  registerChangeInUsoRecursoFisicos() {
    this.eventSubscriber = this.eventManager.subscribe('usoRecursoFisicoListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
