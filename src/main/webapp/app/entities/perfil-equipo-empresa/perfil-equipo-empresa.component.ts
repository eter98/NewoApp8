import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IPerfilEquipoEmpresa } from 'app/shared/model/perfil-equipo-empresa.model';
import { AccountService } from 'app/core';
import { PerfilEquipoEmpresaService } from './perfil-equipo-empresa.service';

@Component({
  selector: 'jhi-perfil-equipo-empresa',
  templateUrl: './perfil-equipo-empresa.component.html'
})
export class PerfilEquipoEmpresaComponent implements OnInit, OnDestroy {
  perfilEquipoEmpresas: IPerfilEquipoEmpresa[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected perfilEquipoEmpresaService: PerfilEquipoEmpresaService,
    protected jhiAlertService: JhiAlertService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.perfilEquipoEmpresaService
      .query()
      .pipe(
        filter((res: HttpResponse<IPerfilEquipoEmpresa[]>) => res.ok),
        map((res: HttpResponse<IPerfilEquipoEmpresa[]>) => res.body)
      )
      .subscribe(
        (res: IPerfilEquipoEmpresa[]) => {
          this.perfilEquipoEmpresas = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInPerfilEquipoEmpresas();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IPerfilEquipoEmpresa) {
    return item.id;
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  registerChangeInPerfilEquipoEmpresas() {
    this.eventSubscriber = this.eventManager.subscribe('perfilEquipoEmpresaListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
