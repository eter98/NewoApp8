import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CategoriaContenidos } from 'app/shared/model/categoria-contenidos.model';
import { CategoriaContenidosService } from './categoria-contenidos.service';
import { CategoriaContenidosComponent } from './categoria-contenidos.component';
import { CategoriaContenidosDetailComponent } from './categoria-contenidos-detail.component';
import { CategoriaContenidosUpdateComponent } from './categoria-contenidos-update.component';
import { CategoriaContenidosDeletePopupComponent } from './categoria-contenidos-delete-dialog.component';
import { ICategoriaContenidos } from 'app/shared/model/categoria-contenidos.model';

@Injectable({ providedIn: 'root' })
export class CategoriaContenidosResolve implements Resolve<ICategoriaContenidos> {
  constructor(private service: CategoriaContenidosService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICategoriaContenidos> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CategoriaContenidos>) => response.ok),
        map((categoriaContenidos: HttpResponse<CategoriaContenidos>) => categoriaContenidos.body)
      );
    }
    return of(new CategoriaContenidos());
  }
}

export const categoriaContenidosRoute: Routes = [
  {
    path: '',
    component: CategoriaContenidosComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.categoriaContenidos.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CategoriaContenidosDetailComponent,
    resolve: {
      categoriaContenidos: CategoriaContenidosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.categoriaContenidos.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CategoriaContenidosUpdateComponent,
    resolve: {
      categoriaContenidos: CategoriaContenidosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.categoriaContenidos.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CategoriaContenidosUpdateComponent,
    resolve: {
      categoriaContenidos: CategoriaContenidosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.categoriaContenidos.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const categoriaContenidosPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CategoriaContenidosDeletePopupComponent,
    resolve: {
      categoriaContenidos: CategoriaContenidosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.categoriaContenidos.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
