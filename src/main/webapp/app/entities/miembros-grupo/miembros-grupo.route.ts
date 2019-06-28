import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MiembrosGrupo } from 'app/shared/model/miembros-grupo.model';
import { MiembrosGrupoService } from './miembros-grupo.service';
import { MiembrosGrupoComponent } from './miembros-grupo.component';
import { MiembrosGrupoDetailComponent } from './miembros-grupo-detail.component';
import { MiembrosGrupoUpdateComponent } from './miembros-grupo-update.component';
import { MiembrosGrupoDeletePopupComponent } from './miembros-grupo-delete-dialog.component';
import { IMiembrosGrupo } from 'app/shared/model/miembros-grupo.model';

@Injectable({ providedIn: 'root' })
export class MiembrosGrupoResolve implements Resolve<IMiembrosGrupo> {
  constructor(private service: MiembrosGrupoService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMiembrosGrupo> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MiembrosGrupo>) => response.ok),
        map((miembrosGrupo: HttpResponse<MiembrosGrupo>) => miembrosGrupo.body)
      );
    }
    return of(new MiembrosGrupo());
  }
}

export const miembrosGrupoRoute: Routes = [
  {
    path: '',
    component: MiembrosGrupoComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.miembrosGrupo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MiembrosGrupoDetailComponent,
    resolve: {
      miembrosGrupo: MiembrosGrupoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.miembrosGrupo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MiembrosGrupoUpdateComponent,
    resolve: {
      miembrosGrupo: MiembrosGrupoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.miembrosGrupo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MiembrosGrupoUpdateComponent,
    resolve: {
      miembrosGrupo: MiembrosGrupoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.miembrosGrupo.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const miembrosGrupoPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MiembrosGrupoDeletePopupComponent,
    resolve: {
      miembrosGrupo: MiembrosGrupoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.miembrosGrupo.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
