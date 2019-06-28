import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MargenNewoBlog } from 'app/shared/model/margen-newo-blog.model';
import { MargenNewoBlogService } from './margen-newo-blog.service';
import { MargenNewoBlogComponent } from './margen-newo-blog.component';
import { MargenNewoBlogDetailComponent } from './margen-newo-blog-detail.component';
import { MargenNewoBlogUpdateComponent } from './margen-newo-blog-update.component';
import { MargenNewoBlogDeletePopupComponent } from './margen-newo-blog-delete-dialog.component';
import { IMargenNewoBlog } from 'app/shared/model/margen-newo-blog.model';

@Injectable({ providedIn: 'root' })
export class MargenNewoBlogResolve implements Resolve<IMargenNewoBlog> {
  constructor(private service: MargenNewoBlogService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMargenNewoBlog> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MargenNewoBlog>) => response.ok),
        map((margenNewoBlog: HttpResponse<MargenNewoBlog>) => margenNewoBlog.body)
      );
    }
    return of(new MargenNewoBlog());
  }
}

export const margenNewoBlogRoute: Routes = [
  {
    path: '',
    component: MargenNewoBlogComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.margenNewoBlog.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MargenNewoBlogDetailComponent,
    resolve: {
      margenNewoBlog: MargenNewoBlogResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.margenNewoBlog.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MargenNewoBlogUpdateComponent,
    resolve: {
      margenNewoBlog: MargenNewoBlogResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.margenNewoBlog.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MargenNewoBlogUpdateComponent,
    resolve: {
      margenNewoBlog: MargenNewoBlogResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.margenNewoBlog.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const margenNewoBlogPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MargenNewoBlogDeletePopupComponent,
    resolve: {
      margenNewoBlog: MargenNewoBlogResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.margenNewoBlog.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
