import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TipoRecurso } from 'app/shared/model/tipo-recurso.model';
import { TipoRecursoService } from './tipo-recurso.service';
import { TipoRecursoComponent } from './tipo-recurso.component';
import { TipoRecursoDetailComponent } from './tipo-recurso-detail.component';
import { TipoRecursoUpdateComponent } from './tipo-recurso-update.component';
import { TipoRecursoDeletePopupComponent } from './tipo-recurso-delete-dialog.component';
import { ITipoRecurso } from 'app/shared/model/tipo-recurso.model';

@Injectable({ providedIn: 'root' })
export class TipoRecursoResolve implements Resolve<ITipoRecurso> {
  constructor(private service: TipoRecursoService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITipoRecurso> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TipoRecurso>) => response.ok),
        map((tipoRecurso: HttpResponse<TipoRecurso>) => tipoRecurso.body)
      );
    }
    return of(new TipoRecurso());
  }
}

export const tipoRecursoRoute: Routes = [
  {
    path: '',
    component: TipoRecursoComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.tipoRecurso.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TipoRecursoDetailComponent,
    resolve: {
      tipoRecurso: TipoRecursoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.tipoRecurso.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TipoRecursoUpdateComponent,
    resolve: {
      tipoRecurso: TipoRecursoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.tipoRecurso.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TipoRecursoUpdateComponent,
    resolve: {
      tipoRecurso: TipoRecursoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.tipoRecurso.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const tipoRecursoPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TipoRecursoDeletePopupComponent,
    resolve: {
      tipoRecurso: TipoRecursoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.tipoRecurso.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
