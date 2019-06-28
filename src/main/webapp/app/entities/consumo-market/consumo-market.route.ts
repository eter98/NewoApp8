import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ConsumoMarket } from 'app/shared/model/consumo-market.model';
import { ConsumoMarketService } from './consumo-market.service';
import { ConsumoMarketComponent } from './consumo-market.component';
import { ConsumoMarketDetailComponent } from './consumo-market-detail.component';
import { ConsumoMarketUpdateComponent } from './consumo-market-update.component';
import { ConsumoMarketDeletePopupComponent } from './consumo-market-delete-dialog.component';
import { IConsumoMarket } from 'app/shared/model/consumo-market.model';

@Injectable({ providedIn: 'root' })
export class ConsumoMarketResolve implements Resolve<IConsumoMarket> {
  constructor(private service: ConsumoMarketService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IConsumoMarket> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ConsumoMarket>) => response.ok),
        map((consumoMarket: HttpResponse<ConsumoMarket>) => consumoMarket.body)
      );
    }
    return of(new ConsumoMarket());
  }
}

export const consumoMarketRoute: Routes = [
  {
    path: '',
    component: ConsumoMarketComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.consumoMarket.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ConsumoMarketDetailComponent,
    resolve: {
      consumoMarket: ConsumoMarketResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.consumoMarket.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ConsumoMarketUpdateComponent,
    resolve: {
      consumoMarket: ConsumoMarketResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.consumoMarket.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ConsumoMarketUpdateComponent,
    resolve: {
      consumoMarket: ConsumoMarketResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.consumoMarket.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const consumoMarketPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ConsumoMarketDeletePopupComponent,
    resolve: {
      consumoMarket: ConsumoMarketResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp8App.consumoMarket.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
