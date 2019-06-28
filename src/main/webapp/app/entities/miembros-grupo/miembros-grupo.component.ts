import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IMiembrosGrupo } from 'app/shared/model/miembros-grupo.model';
import { AccountService } from 'app/core';
import { MiembrosGrupoService } from './miembros-grupo.service';

@Component({
  selector: 'jhi-miembros-grupo',
  templateUrl: './miembros-grupo.component.html'
})
export class MiembrosGrupoComponent implements OnInit, OnDestroy {
  miembrosGrupos: IMiembrosGrupo[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected miembrosGrupoService: MiembrosGrupoService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.miembrosGrupoService
      .query()
      .pipe(
        filter((res: HttpResponse<IMiembrosGrupo[]>) => res.ok),
        map((res: HttpResponse<IMiembrosGrupo[]>) => res.body)
      )
      .subscribe(
        (res: IMiembrosGrupo[]) => {
          this.miembrosGrupos = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInMiembrosGrupos();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IMiembrosGrupo) {
    return item.id;
  }

  registerChangeInMiembrosGrupos() {
    this.eventSubscriber = this.eventManager.subscribe('miembrosGrupoListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
