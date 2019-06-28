import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Grupos } from 'app/shared/model/grupos.model';
import { GruposService } from './grupos.service';
import { GruposComponent } from './grupos.component';
import { GruposDetailComponent } from './grupos-detail.component';
import { GruposUpdateComponent } from './grupos-update.component';
import { GruposDeletePopupComponent } from './grupos-delete-dialog.component';
import { IGrupos } from 'app/shared/model/grupos.model';

@Injectable({ providedIn: 'root' })
export class GruposResolve implements Resolve<IGrupos> {
  constructor(private service: GruposService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IGrupos> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Grupos>) => response.ok),
        map((grupos: HttpResponse<Grupos>) => grupos.body)
      );
    }
    return of(new Grupos());
  }
}

export const gruposRoute: Routes = [
  {
    path: '',
    component: GruposComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.grupos.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GruposDetailComponent,
    resolve: {
      grupos: GruposResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.grupos.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GruposUpdateComponent,
    resolve: {
      grupos: GruposResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.grupos.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GruposUpdateComponent,
    resolve: {
      grupos: GruposResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.grupos.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const gruposPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: GruposDeletePopupComponent,
    resolve: {
      grupos: GruposResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.grupos.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
