import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoRecurso } from 'app/shared/model/tipo-recurso.model';

@Component({
  selector: 'jhi-tipo-recurso-detail',
  templateUrl: './tipo-recurso-detail.component.html'
})
export class TipoRecursoDetailComponent implements OnInit {
  tipoRecurso: ITipoRecurso;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ tipoRecurso }) => {
      this.tipoRecurso = tipoRecurso;
    });
  }

  previousState() {
    window.history.back();
  }
}
