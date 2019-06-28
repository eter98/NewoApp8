import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IEspacioLibre } from 'app/shared/model/espacio-libre.model';

@Component({
  selector: 'jhi-espacio-libre-detail',
  templateUrl: './espacio-libre-detail.component.html'
})
export class EspacioLibreDetailComponent implements OnInit {
  espacioLibre: IEspacioLibre;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ espacioLibre }) => {
      this.espacioLibre = espacioLibre;
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
