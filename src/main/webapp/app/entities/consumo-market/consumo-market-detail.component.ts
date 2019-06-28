import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IConsumoMarket } from 'app/shared/model/consumo-market.model';

@Component({
  selector: 'jhi-consumo-market-detail',
  templateUrl: './consumo-market-detail.component.html'
})
export class ConsumoMarketDetailComponent implements OnInit {
  consumoMarket: IConsumoMarket;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ consumoMarket }) => {
      this.consumoMarket = consumoMarket;
    });
  }

  previousState() {
    window.history.back();
  }
}
