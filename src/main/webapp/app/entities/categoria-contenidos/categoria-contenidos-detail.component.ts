import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICategoriaContenidos } from 'app/shared/model/categoria-contenidos.model';

@Component({
  selector: 'jhi-categoria-contenidos-detail',
  templateUrl: './categoria-contenidos-detail.component.html'
})
export class CategoriaContenidosDetailComponent implements OnInit {
  categoriaContenidos: ICategoriaContenidos;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ categoriaContenidos }) => {
      this.categoriaContenidos = categoriaContenidos;
    });
  }

  previousState() {
    window.history.back();
  }
}
