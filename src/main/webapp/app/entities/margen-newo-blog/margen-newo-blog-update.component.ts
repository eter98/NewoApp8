import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMargenNewoBlog, MargenNewoBlog } from 'app/shared/model/margen-newo-blog.model';
import { MargenNewoBlogService } from './margen-newo-blog.service';
import { IBlog } from 'app/shared/model/blog.model';
import { BlogService } from 'app/entities/blog';

@Component({
  selector: 'jhi-margen-newo-blog-update',
  templateUrl: './margen-newo-blog-update.component.html'
})
export class MargenNewoBlogUpdateComponent implements OnInit {
  isSaving: boolean;

  blogs: IBlog[];

  editForm = this.fb.group({
    id: [],
    porcentajeMargen: [],
    blog: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected margenNewoBlogService: MargenNewoBlogService,
    protected blogService: BlogService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ margenNewoBlog }) => {
      this.updateForm(margenNewoBlog);
    });
    this.blogService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IBlog[]>) => mayBeOk.ok),
        map((response: HttpResponse<IBlog[]>) => response.body)
      )
      .subscribe((res: IBlog[]) => (this.blogs = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(margenNewoBlog: IMargenNewoBlog) {
    this.editForm.patchValue({
      id: margenNewoBlog.id,
      porcentajeMargen: margenNewoBlog.porcentajeMargen,
      blog: margenNewoBlog.blog
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const margenNewoBlog = this.createFromForm();
    if (margenNewoBlog.id !== undefined) {
      this.subscribeToSaveResponse(this.margenNewoBlogService.update(margenNewoBlog));
    } else {
      this.subscribeToSaveResponse(this.margenNewoBlogService.create(margenNewoBlog));
    }
  }

  private createFromForm(): IMargenNewoBlog {
    return {
      ...new MargenNewoBlog(),
      id: this.editForm.get(['id']).value,
      porcentajeMargen: this.editForm.get(['porcentajeMargen']).value,
      blog: this.editForm.get(['blog']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMargenNewoBlog>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackBlogById(index: number, item: IBlog) {
    return item.id;
  }
}
