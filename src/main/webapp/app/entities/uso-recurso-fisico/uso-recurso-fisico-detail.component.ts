import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUsoRecursoFisico } from 'app/shared/model/uso-recurso-fisico.model';

@Component({
  selector: 'jhi-uso-recurso-fisico-detail',
  templateUrl: './uso-recurso-fisico-detail.component.html'
})
export class UsoRecursoFisicoDetailComponent implements OnInit {
  usoRecursoFisico: IUsoRecursoFisico;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ usoRecursoFisico }) => {
      this.usoRecursoFisico = usoRecursoFisico;
    });
  }

  previousState() {
    window.history.back();
  }
}
