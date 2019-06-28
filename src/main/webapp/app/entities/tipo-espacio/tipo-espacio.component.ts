import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITipoEspacio } from 'app/shared/model/tipo-espacio.model';
import { AccountService } from 'app/core';
import { TipoEspacioService } from './tipo-espacio.service';

@Component({
  selector: 'jhi-tipo-espacio',
  templateUrl: './tipo-espacio.component.html'
})
export class TipoEspacioComponent implements OnInit, OnDestroy {
  tipoEspacios: ITipoEspacio[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected tipoEspacioService: TipoEspacioService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.tipoEspacioService
      .query()
      .pipe(
        filter((res: HttpResponse<ITipoEspacio[]>) => res.ok),
        map((res: HttpResponse<ITipoEspacio[]>) => res.body)
      )
      .subscribe(
        (res: ITipoEspacio[]) => {
          this.tipoEspacios = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInTipoEspacios();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ITipoEspacio) {
    return item.id;
  }

  registerChangeInTipoEspacios() {
    this.eventSubscriber = this.eventManager.subscribe('tipoEspacioListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
