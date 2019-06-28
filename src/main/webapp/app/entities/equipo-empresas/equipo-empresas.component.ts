import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IEquipoEmpresas } from 'app/shared/model/equipo-empresas.model';
import { AccountService } from 'app/core';
import { EquipoEmpresasService } from './equipo-empresas.service';

@Component({
  selector: 'jhi-equipo-empresas',
  templateUrl: './equipo-empresas.component.html'
})
export class EquipoEmpresasComponent implements OnInit, OnDestroy {
  equipoEmpresas: IEquipoEmpresas[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected equipoEmpresasService: EquipoEmpresasService,
    protected jhiAlertService: JhiAlertService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.equipoEmpresasService
      .query()
      .pipe(
        filter((res: HttpResponse<IEquipoEmpresas[]>) => res.ok),
        map((res: HttpResponse<IEquipoEmpresas[]>) => res.body)
      )
      .subscribe(
        (res: IEquipoEmpresas[]) => {
          this.equipoEmpresas = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInEquipoEmpresas();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IEquipoEmpresas) {
    return item.id;
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  registerChangeInEquipoEmpresas() {
    this.eventSubscriber = this.eventManager.subscribe('equipoEmpresasListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
