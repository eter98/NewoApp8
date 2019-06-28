import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoPrepagoConsumo } from 'app/shared/model/tipo-prepago-consumo.model';

@Component({
  selector: 'jhi-tipo-prepago-consumo-detail',
  templateUrl: './tipo-prepago-consumo-detail.component.html'
})
export class TipoPrepagoConsumoDetailComponent implements OnInit {
  tipoPrepagoConsumo: ITipoPrepagoConsumo;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ tipoPrepagoConsumo }) => {
      this.tipoPrepagoConsumo = tipoPrepagoConsumo;
    });
  }

  previousState() {
    window.history.back();
  }
}
