import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMiembrosGrupo } from 'app/shared/model/miembros-grupo.model';

@Component({
  selector: 'jhi-miembros-grupo-detail',
  templateUrl: './miembros-grupo-detail.component.html'
})
export class MiembrosGrupoDetailComponent implements OnInit {
  miembrosGrupo: IMiembrosGrupo;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ miembrosGrupo }) => {
      this.miembrosGrupo = miembrosGrupo;
    });
  }

  previousState() {
    window.history.back();
  }
}
