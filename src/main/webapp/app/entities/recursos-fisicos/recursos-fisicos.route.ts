import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RecursosFisicos } from 'app/shared/model/recursos-fisicos.model';
import { RecursosFisicosService } from './recursos-fisicos.service';
import { RecursosFisicosComponent } from './recursos-fisicos.component';
import { RecursosFisicosDetailComponent } from './recursos-fisicos-detail.component';
import { RecursosFisicosUpdateComponent } from './recursos-fisicos-update.component';
import { RecursosFisicosDeletePopupComponent } from './recursos-fisicos-delete-dialog.component';
import { IRecursosFisicos } from 'app/shared/model/recursos-fisicos.model';

@Injectable({ providedIn: 'root' })
export class RecursosFisicosResolve implements Resolve<IRecursosFisicos> {
  constructor(private service: RecursosFisicosService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IRecursosFisicos> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<RecursosFisicos>) => response.ok),
        map((recursosFisicos: HttpResponse<RecursosFisicos>) => recursosFisicos.body)
      );
    }
    return of(new RecursosFisicos());
  }
}

export const recursosFisicosRoute: Routes = [
  {
    path: '',
    component: RecursosFisicosComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.recursosFisicos.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: RecursosFisicosDetailComponent,
    resolve: {
      recursosFisicos: RecursosFisicosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.recursosFisicos.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: RecursosFisicosUpdateComponent,
    resolve: {
      recursosFisicos: RecursosFisicosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.recursosFisicos.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: RecursosFisicosUpdateComponent,
    resolve: {
      recursosFisicos: RecursosFisicosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.recursosFisicos.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const recursosFisicosPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: RecursosFisicosDeletePopupComponent,
    resolve: {
      recursosFisicos: RecursosFisicosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.recursosFisicos.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
