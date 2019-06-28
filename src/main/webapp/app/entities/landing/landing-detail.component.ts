import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ILanding } from 'app/shared/model/landing.model';

@Component({
  selector: 'jhi-landing-detail',
  templateUrl: './landing-detail.component.html'
})
export class LandingDetailComponent implements OnInit {
  landing: ILanding;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ landing }) => {
      this.landing = landing;
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
