import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMargenNewoGrupos } from 'app/shared/model/margen-newo-grupos.model';

@Component({
  selector: 'jhi-margen-newo-grupos-detail',
  templateUrl: './margen-newo-grupos-detail.component.html'
})
export class MargenNewoGruposDetailComponent implements OnInit {
  margenNewoGrupos: IMargenNewoGrupos;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ margenNewoGrupos }) => {
      this.margenNewoGrupos = margenNewoGrupos;
    });
  }

  previousState() {
    window.history.back();
  }
}
