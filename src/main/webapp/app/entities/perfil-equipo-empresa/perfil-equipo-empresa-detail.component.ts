import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IPerfilEquipoEmpresa } from 'app/shared/model/perfil-equipo-empresa.model';

@Component({
  selector: 'jhi-perfil-equipo-empresa-detail',
  templateUrl: './perfil-equipo-empresa-detail.component.html'
})
export class PerfilEquipoEmpresaDetailComponent implements OnInit {
  perfilEquipoEmpresa: IPerfilEquipoEmpresa;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ perfilEquipoEmpresa }) => {
      this.perfilEquipoEmpresa = perfilEquipoEmpresa;
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }
  previousState() {
    window.history.back();
  }
}
