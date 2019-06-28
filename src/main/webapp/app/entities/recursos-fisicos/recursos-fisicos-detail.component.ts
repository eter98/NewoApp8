import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IRecursosFisicos } from 'app/shared/model/recursos-fisicos.model';

@Component({
  selector: 'jhi-recursos-fisicos-detail',
  templateUrl: './recursos-fisicos-detail.component.html'
})
export class RecursosFisicosDetailComponent implements OnInit {
  recursosFisicos: IRecursosFisicos;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ recursosFisicos }) => {
      this.recursosFisicos = recursosFisicos;
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
