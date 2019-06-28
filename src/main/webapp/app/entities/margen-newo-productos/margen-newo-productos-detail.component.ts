import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMargenNewoProductos } from 'app/shared/model/margen-newo-productos.model';

@Component({
  selector: 'jhi-margen-newo-productos-detail',
  templateUrl: './margen-newo-productos-detail.component.html'
})
export class MargenNewoProductosDetailComponent implements OnInit {
  margenNewoProductos: IMargenNewoProductos;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ margenNewoProductos }) => {
      this.margenNewoProductos = margenNewoProductos;
    });
  }

  previousState() {
    window.history.back();
  }
}
