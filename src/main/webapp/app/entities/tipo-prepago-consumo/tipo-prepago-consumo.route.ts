import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TipoPrepagoConsumo } from 'app/shared/model/tipo-prepago-consumo.model';
import { TipoPrepagoConsumoService } from './tipo-prepago-consumo.service';
import { TipoPrepagoConsumoComponent } from './tipo-prepago-consumo.component';
import { TipoPrepagoConsumoDetailComponent } from './tipo-prepago-consumo-detail.component';
import { TipoPrepagoConsumoUpdateComponent } from './tipo-prepago-consumo-update.component';
import { TipoPrepagoConsumoDeletePopupComponent } from './tipo-prepago-consumo-delete-dialog.component';
import { ITipoPrepagoConsumo } from 'app/shared/model/tipo-prepago-consumo.model';

@Injectable({ providedIn: 'root' })
export class TipoPrepagoConsumoResolve implements Resolve<ITipoPrepagoConsumo> {
  constructor(private service: TipoPrepagoConsumoService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITipoPrepagoConsumo> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TipoPrepagoConsumo>) => response.ok),
        map((tipoPrepagoConsumo: HttpResponse<TipoPrepagoConsumo>) => tipoPrepagoConsumo.body)
      );
    }
    return of(new TipoPrepagoConsumo());
  }
}

export const tipoPrepagoConsumoRoute: Routes = [
  {
    path: '',
    component: TipoPrepagoConsumoComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.tipoPrepagoConsumo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TipoPrepagoConsumoDetailComponent,
    resolve: {
      tipoPrepagoConsumo: TipoPrepagoConsumoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.tipoPrepagoConsumo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TipoPrepagoConsumoUpdateComponent,
    resolve: {
      tipoPrepagoConsumo: TipoPrepagoConsumoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.tipoPrepagoConsumo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TipoPrepagoConsumoUpdateComponent,
    resolve: {
      tipoPrepagoConsumo: TipoPrepagoConsumoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.tipoPrepagoConsumo.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const tipoPrepagoConsumoPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TipoPrepagoConsumoDeletePopupComponent,
    resolve: {
      tipoPrepagoConsumo: TipoPrepagoConsumoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.tipoPrepagoConsumo.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
