import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoEspacio } from 'app/shared/model/tipo-espacio.model';

@Component({
  selector: 'jhi-tipo-espacio-detail',
  templateUrl: './tipo-espacio-detail.component.html'
})
export class TipoEspacioDetailComponent implements OnInit {
  tipoEspacio: ITipoEspacio;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ tipoEspacio }) => {
      this.tipoEspacio = tipoEspacio;
    });
  }

  previousState() {
    window.history.back();
  }
}
