import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MargenNewoProductos } from 'app/shared/model/margen-newo-productos.model';
import { MargenNewoProductosService } from './margen-newo-productos.service';
import { MargenNewoProductosComponent } from './margen-newo-productos.component';
import { MargenNewoProductosDetailComponent } from './margen-newo-productos-detail.component';
import { MargenNewoProductosUpdateComponent } from './margen-newo-productos-update.component';
import { MargenNewoProductosDeletePopupComponent } from './margen-newo-productos-delete-dialog.component';
import { IMargenNewoProductos } from 'app/shared/model/margen-newo-productos.model';

@Injectable({ providedIn: 'root' })
export class MargenNewoProductosResolve implements Resolve<IMargenNewoProductos> {
  constructor(private service: MargenNewoProductosService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMargenNewoProductos> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MargenNewoProductos>) => response.ok),
        map((margenNewoProductos: HttpResponse<MargenNewoProductos>) => margenNewoProductos.body)
      );
    }
    return of(new MargenNewoProductos());
  }
}

export const margenNewoProductosRoute: Routes = [
  {
    path: '',
    component: MargenNewoProductosComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.margenNewoProductos.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MargenNewoProductosDetailComponent,
    resolve: {
      margenNewoProductos: MargenNewoProductosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.margenNewoProductos.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MargenNewoProductosUpdateComponent,
    resolve: {
      margenNewoProductos: MargenNewoProductosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.margenNewoProductos.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MargenNewoProductosUpdateComponent,
    resolve: {
      margenNewoProductos: MargenNewoProductosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.margenNewoProductos.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const margenNewoProductosPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MargenNewoProductosDeletePopupComponent,
    resolve: {
      margenNewoProductos: MargenNewoProductosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.margenNewoProductos.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
