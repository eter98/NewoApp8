import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PrepagoConsumo } from 'app/shared/model/prepago-consumo.model';
import { PrepagoConsumoService } from './prepago-consumo.service';
import { PrepagoConsumoComponent } from './prepago-consumo.component';
import { PrepagoConsumoDetailComponent } from './prepago-consumo-detail.component';
import { PrepagoConsumoUpdateComponent } from './prepago-consumo-update.component';
import { PrepagoConsumoDeletePopupComponent } from './prepago-consumo-delete-dialog.component';
import { IPrepagoConsumo } from 'app/shared/model/prepago-consumo.model';

@Injectable({ providedIn: 'root' })
export class PrepagoConsumoResolve implements Resolve<IPrepagoConsumo> {
  constructor(private service: PrepagoConsumoService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPrepagoConsumo> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<PrepagoConsumo>) => response.ok),
        map((prepagoConsumo: HttpResponse<PrepagoConsumo>) => prepagoConsumo.body)
      );
    }
    return of(new PrepagoConsumo());
  }
}

export const prepagoConsumoRoute: Routes = [
  {
    path: '',
    component: PrepagoConsumoComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.prepagoConsumo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PrepagoConsumoDetailComponent,
    resolve: {
      prepagoConsumo: PrepagoConsumoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.prepagoConsumo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PrepagoConsumoUpdateComponent,
    resolve: {
      prepagoConsumo: PrepagoConsumoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.prepagoConsumo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PrepagoConsumoUpdateComponent,
    resolve: {
      prepagoConsumo: PrepagoConsumoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.prepagoConsumo.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const prepagoConsumoPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PrepagoConsumoDeletePopupComponent,
    resolve: {
      prepagoConsumo: PrepagoConsumoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.prepagoConsumo.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
