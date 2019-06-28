import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPrepagoConsumo } from 'app/shared/model/prepago-consumo.model';

@Component({
  selector: 'jhi-prepago-consumo-detail',
  templateUrl: './prepago-consumo-detail.component.html'
})
export class PrepagoConsumoDetailComponent implements OnInit {
  prepagoConsumo: IPrepagoConsumo;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ prepagoConsumo }) => {
      this.prepagoConsumo = prepagoConsumo;
    });
  }

  previousState() {
    window.history.back();
  }
}
