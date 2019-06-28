import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IGrupos } from 'app/shared/model/grupos.model';

@Component({
  selector: 'jhi-grupos-detail',
  templateUrl: './grupos-detail.component.html'
})
export class GruposDetailComponent implements OnInit {
  grupos: IGrupos;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ grupos }) => {
      this.grupos = grupos;
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
