import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMargenNewoBlog } from 'app/shared/model/margen-newo-blog.model';

@Component({
  selector: 'jhi-margen-newo-blog-detail',
  templateUrl: './margen-newo-blog-detail.component.html'
})
export class MargenNewoBlogDetailComponent implements OnInit {
  margenNewoBlog: IMargenNewoBlog;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ margenNewoBlog }) => {
      this.margenNewoBlog = margenNewoBlog;
    });
  }

  previousState() {
    window.history.back();
  }
}
