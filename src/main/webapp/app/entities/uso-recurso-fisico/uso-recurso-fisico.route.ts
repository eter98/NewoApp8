import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { UsoRecursoFisico } from 'app/shared/model/uso-recurso-fisico.model';
import { UsoRecursoFisicoService } from './uso-recurso-fisico.service';
import { UsoRecursoFisicoComponent } from './uso-recurso-fisico.component';
import { UsoRecursoFisicoDetailComponent } from './uso-recurso-fisico-detail.component';
import { UsoRecursoFisicoUpdateComponent } from './uso-recurso-fisico-update.component';
import { UsoRecursoFisicoDeletePopupComponent } from './uso-recurso-fisico-delete-dialog.component';
import { IUsoRecursoFisico } from 'app/shared/model/uso-recurso-fisico.model';

@Injectable({ providedIn: 'root' })
export class UsoRecursoFisicoResolve implements Resolve<IUsoRecursoFisico> {
  constructor(private service: UsoRecursoFisicoService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IUsoRecursoFisico> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<UsoRecursoFisico>) => response.ok),
        map((usoRecursoFisico: HttpResponse<UsoRecursoFisico>) => usoRecursoFisico.body)
      );
    }
    return of(new UsoRecursoFisico());
  }
}

export const usoRecursoFisicoRoute: Routes = [
  {
    path: '',
    component: UsoRecursoFisicoComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.usoRecursoFisico.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: UsoRecursoFisicoDetailComponent,
    resolve: {
      usoRecursoFisico: UsoRecursoFisicoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.usoRecursoFisico.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: UsoRecursoFisicoUpdateComponent,
    resolve: {
      usoRecursoFisico: UsoRecursoFisicoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.usoRecursoFisico.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: UsoRecursoFisicoUpdateComponent,
    resolve: {
      usoRecursoFisico: UsoRecursoFisicoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.usoRecursoFisico.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const usoRecursoFisicoPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: UsoRecursoFisicoDeletePopupComponent,
    resolve: {
      usoRecursoFisico: UsoRecursoFisicoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.usoRecursoFisico.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
