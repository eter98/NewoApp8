import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITipoRecurso } from 'app/shared/model/tipo-recurso.model';
import { AccountService } from 'app/core';
import { TipoRecursoService } from './tipo-recurso.service';

@Component({
  selector: 'jhi-tipo-recurso',
  templateUrl: './tipo-recurso.component.html'
})
export class TipoRecursoComponent implements OnInit, OnDestroy {
  tipoRecursos: ITipoRecurso[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected tipoRecursoService: TipoRecursoService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.tipoRecursoService
      .query()
      .pipe(
        filter((res: HttpResponse<ITipoRecurso[]>) => res.ok),
        map((res: HttpResponse<ITipoRecurso[]>) => res.body)
      )
      .subscribe(
        (res: ITipoRecurso[]) => {
          this.tipoRecursos = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInTipoRecursos();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ITipoRecurso) {
    return item.id;
  }

  registerChangeInTipoRecursos() {
    this.eventSubscriber = this.eventManager.subscribe('tipoRecursoListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
