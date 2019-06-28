import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TipoEspacio } from 'app/shared/model/tipo-espacio.model';
import { TipoEspacioService } from './tipo-espacio.service';
import { TipoEspacioComponent } from './tipo-espacio.component';
import { TipoEspacioDetailComponent } from './tipo-espacio-detail.component';
import { TipoEspacioUpdateComponent } from './tipo-espacio-update.component';
import { TipoEspacioDeletePopupComponent } from './tipo-espacio-delete-dialog.component';
import { ITipoEspacio } from 'app/shared/model/tipo-espacio.model';

@Injectable({ providedIn: 'root' })
export class TipoEspacioResolve implements Resolve<ITipoEspacio> {
  constructor(private service: TipoEspacioService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITipoEspacio> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TipoEspacio>) => response.ok),
        map((tipoEspacio: HttpResponse<TipoEspacio>) => tipoEspacio.body)
      );
    }
    return of(new TipoEspacio());
  }
}

export const tipoEspacioRoute: Routes = [
  {
    path: '',
    component: TipoEspacioComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.tipoEspacio.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TipoEspacioDetailComponent,
    resolve: {
      tipoEspacio: TipoEspacioResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.tipoEspacio.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TipoEspacioUpdateComponent,
    resolve: {
      tipoEspacio: TipoEspacioResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.tipoEspacio.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TipoEspacioUpdateComponent,
    resolve: {
      tipoEspacio: TipoEspacioResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.tipoEspacio.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const tipoEspacioPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TipoEspacioDeletePopupComponent,
    resolve: {
      tipoEspacio: TipoEspacioResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.tipoEspacio.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
