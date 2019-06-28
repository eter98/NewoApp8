import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMiembrosEquipoEmpresas } from 'app/shared/model/miembros-equipo-empresas.model';

@Component({
  selector: 'jhi-miembros-equipo-empresas-detail',
  templateUrl: './miembros-equipo-empresas-detail.component.html'
})
export class MiembrosEquipoEmpresasDetailComponent implements OnInit {
  miembrosEquipoEmpresas: IMiembrosEquipoEmpresas;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ miembrosEquipoEmpresas }) => {
      this.miembrosEquipoEmpresas = miembrosEquipoEmpresas;
    });
  }

  previousState() {
    window.history.back();
  }
}
