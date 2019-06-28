import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMargenNewoEventos } from 'app/shared/model/margen-newo-eventos.model';

@Component({
  selector: 'jhi-margen-newo-eventos-detail',
  templateUrl: './margen-newo-eventos-detail.component.html'
})
export class MargenNewoEventosDetailComponent implements OnInit {
  margenNewoEventos: IMargenNewoEventos;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ margenNewoEventos }) => {
      this.margenNewoEventos = margenNewoEventos;
    });
  }

  previousState() {
    window.history.back();
  }
}
