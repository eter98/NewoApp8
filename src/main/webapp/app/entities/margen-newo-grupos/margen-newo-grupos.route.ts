import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MargenNewoGrupos } from 'app/shared/model/margen-newo-grupos.model';
import { MargenNewoGruposService } from './margen-newo-grupos.service';
import { MargenNewoGruposComponent } from './margen-newo-grupos.component';
import { MargenNewoGruposDetailComponent } from './margen-newo-grupos-detail.component';
import { MargenNewoGruposUpdateComponent } from './margen-newo-grupos-update.component';
import { MargenNewoGruposDeletePopupComponent } from './margen-newo-grupos-delete-dialog.component';
import { IMargenNewoGrupos } from 'app/shared/model/margen-newo-grupos.model';

@Injectable({ providedIn: 'root' })
export class MargenNewoGruposResolve implements Resolve<IMargenNewoGrupos> {
  constructor(private service: MargenNewoGruposService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMargenNewoGrupos> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MargenNewoGrupos>) => response.ok),
        map((margenNewoGrupos: HttpResponse<MargenNewoGrupos>) => margenNewoGrupos.body)
      );
    }
    return of(new MargenNewoGrupos());
  }
}

export const margenNewoGruposRoute: Routes = [
  {
    path: '',
    component: MargenNewoGruposComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.margenNewoGrupos.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MargenNewoGruposDetailComponent,
    resolve: {
      margenNewoGrupos: MargenNewoGruposResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.margenNewoGrupos.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MargenNewoGruposUpdateComponent,
    resolve: {
      margenNewoGrupos: MargenNewoGruposResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.margenNewoGrupos.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MargenNewoGruposUpdateComponent,
    resolve: {
      margenNewoGrupos: MargenNewoGruposResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.margenNewoGrupos.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const margenNewoGruposPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MargenNewoGruposDeletePopupComponent,
    resolve: {
      margenNewoGrupos: MargenNewoGruposResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.margenNewoGrupos.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
