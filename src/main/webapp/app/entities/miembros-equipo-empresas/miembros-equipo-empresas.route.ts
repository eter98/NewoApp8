import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MiembrosEquipoEmpresas } from 'app/shared/model/miembros-equipo-empresas.model';
import { MiembrosEquipoEmpresasService } from './miembros-equipo-empresas.service';
import { MiembrosEquipoEmpresasComponent } from './miembros-equipo-empresas.component';
import { MiembrosEquipoEmpresasDetailComponent } from './miembros-equipo-empresas-detail.component';
import { MiembrosEquipoEmpresasUpdateComponent } from './miembros-equipo-empresas-update.component';
import { MiembrosEquipoEmpresasDeletePopupComponent } from './miembros-equipo-empresas-delete-dialog.component';
import { IMiembrosEquipoEmpresas } from 'app/shared/model/miembros-equipo-empresas.model';

@Injectable({ providedIn: 'root' })
export class MiembrosEquipoEmpresasResolve implements Resolve<IMiembrosEquipoEmpresas> {
  constructor(private service: MiembrosEquipoEmpresasService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMiembrosEquipoEmpresas> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MiembrosEquipoEmpresas>) => response.ok),
        map((miembrosEquipoEmpresas: HttpResponse<MiembrosEquipoEmpresas>) => miembrosEquipoEmpresas.body)
      );
    }
    return of(new MiembrosEquipoEmpresas());
  }
}

export const miembrosEquipoEmpresasRoute: Routes = [
  {
    path: '',
    component: MiembrosEquipoEmpresasComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.miembrosEquipoEmpresas.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MiembrosEquipoEmpresasDetailComponent,
    resolve: {
      miembrosEquipoEmpresas: MiembrosEquipoEmpresasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.miembrosEquipoEmpresas.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MiembrosEquipoEmpresasUpdateComponent,
    resolve: {
      miembrosEquipoEmpresas: MiembrosEquipoEmpresasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.miembrosEquipoEmpresas.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MiembrosEquipoEmpresasUpdateComponent,
    resolve: {
      miembrosEquipoEmpresas: MiembrosEquipoEmpresasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.miembrosEquipoEmpresas.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const miembrosEquipoEmpresasPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MiembrosEquipoEmpresasDeletePopupComponent,
    resolve: {
      miembrosEquipoEmpresas: MiembrosEquipoEmpresasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.miembrosEquipoEmpresas.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
