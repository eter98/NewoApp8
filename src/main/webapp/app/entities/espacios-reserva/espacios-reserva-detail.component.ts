import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IEspaciosReserva } from 'app/shared/model/espacios-reserva.model';

@Component({
  selector: 'jhi-espacios-reserva-detail',
  templateUrl: './espacios-reserva-detail.component.html'
})
export class EspaciosReservaDetailComponent implements OnInit {
  espaciosReserva: IEspaciosReserva;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ espaciosReserva }) => {
      this.espaciosReserva = espaciosReserva;
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
