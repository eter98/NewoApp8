import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IMargenNewoBlog } from 'app/shared/model/margen-newo-blog.model';
import { AccountService } from 'app/core';
import { MargenNewoBlogService } from './margen-newo-blog.service';

@Component({
  selector: 'jhi-margen-newo-blog',
  templateUrl: './margen-newo-blog.component.html'
})
export class MargenNewoBlogComponent implements OnInit, OnDestroy {
  margenNewoBlogs: IMargenNewoBlog[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected margenNewoBlogService: MargenNewoBlogService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.margenNewoBlogService
      .query()
      .pipe(
        filter((res: HttpResponse<IMargenNewoBlog[]>) => res.ok),
        map((res: HttpResponse<IMargenNewoBlog[]>) => res.body)
      )
      .subscribe(
        (res: IMargenNewoBlog[]) => {
          this.margenNewoBlogs = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInMargenNewoBlogs();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IMargenNewoBlog) {
    return item.id;
  }

  registerChangeInMargenNewoBlogs() {
    this.eventSubscriber = this.eventManager.subscribe('margenNewoBlogListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
