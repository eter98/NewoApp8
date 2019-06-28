import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IMiembrosEquipoEmpresas } from 'app/shared/model/miembros-equipo-empresas.model';
import { AccountService } from 'app/core';
import { MiembrosEquipoEmpresasService } from './miembros-equipo-empresas.service';

@Component({
  selector: 'jhi-miembros-equipo-empresas',
  templateUrl: './miembros-equipo-empresas.component.html'
})
export class MiembrosEquipoEmpresasComponent implements OnInit, OnDestroy {
  miembrosEquipoEmpresas: IMiembrosEquipoEmpresas[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected miembrosEquipoEmpresasService: MiembrosEquipoEmpresasService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.miembrosEquipoEmpresasService
      .query()
      .pipe(
        filter((res: HttpResponse<IMiembrosEquipoEmpresas[]>) => res.ok),
        map((res: HttpResponse<IMiembrosEquipoEmpresas[]>) => res.body)
      )
      .subscribe(
        (res: IMiembrosEquipoEmpresas[]) => {
          this.miembrosEquipoEmpresas = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInMiembrosEquipoEmpresas();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IMiembrosEquipoEmpresas) {
    return item.id;
  }

  registerChangeInMiembrosEquipoEmpresas() {
    this.eventSubscriber = this.eventManager.subscribe('miembrosEquipoEmpresasListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
