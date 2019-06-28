import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MargenNewoEventos } from 'app/shared/model/margen-newo-eventos.model';
import { MargenNewoEventosService } from './margen-newo-eventos.service';
import { MargenNewoEventosComponent } from './margen-newo-eventos.component';
import { MargenNewoEventosDetailComponent } from './margen-newo-eventos-detail.component';
import { MargenNewoEventosUpdateComponent } from './margen-newo-eventos-update.component';
import { MargenNewoEventosDeletePopupComponent } from './margen-newo-eventos-delete-dialog.component';
import { IMargenNewoEventos } from 'app/shared/model/margen-newo-eventos.model';

@Injectable({ providedIn: 'root' })
export class MargenNewoEventosResolve implements Resolve<IMargenNewoEventos> {
  constructor(private service: MargenNewoEventosService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMargenNewoEventos> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MargenNewoEventos>) => response.ok),
        map((margenNewoEventos: HttpResponse<MargenNewoEventos>) => margenNewoEventos.body)
      );
    }
    return of(new MargenNewoEventos());
  }
}

export const margenNewoEventosRoute: Routes = [
  {
    path: '',
    component: MargenNewoEventosComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.margenNewoEventos.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MargenNewoEventosDetailComponent,
    resolve: {
      margenNewoEventos: MargenNewoEventosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.margenNewoEventos.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MargenNewoEventosUpdateComponent,
    resolve: {
      margenNewoEventos: MargenNewoEventosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.margenNewoEventos.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MargenNewoEventosUpdateComponent,
    resolve: {
      margenNewoEventos: MargenNewoEventosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.margenNewoEventos.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const margenNewoEventosPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MargenNewoEventosDeletePopupComponent,
    resolve: {
      margenNewoEventos: MargenNewoEventosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.margenNewoEventos.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
