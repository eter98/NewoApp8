import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ICategoriaContenidos, CategoriaContenidos } from 'app/shared/model/categoria-contenidos.model';
import { CategoriaContenidosService } from './categoria-contenidos.service';

@Component({
  selector: 'jhi-categoria-contenidos-update',
  templateUrl: './categoria-contenidos-update.component.html'
})
export class CategoriaContenidosUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    categoria: []
  });

  constructor(
    protected categoriaContenidosService: CategoriaContenidosService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ categoriaContenidos }) => {
      this.updateForm(categoriaContenidos);
    });
  }

  updateForm(categoriaContenidos: ICategoriaContenidos) {
    this.editForm.patchValue({
      id: categoriaContenidos.id,
      categoria: categoriaContenidos.categoria
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const categoriaContenidos = this.createFromForm();
    if (categoriaContenidos.id !== undefined) {
      this.subscribeToSaveResponse(this.categoriaContenidosService.update(categoriaContenidos));
    } else {
      this.subscribeToSaveResponse(this.categoriaContenidosService.create(categoriaContenidos));
    }
  }

  private createFromForm(): ICategoriaContenidos {
    return {
      ...new CategoriaContenidos(),
      id: this.editForm.get(['id']).value,
      categoria: this.editForm.get(['categoria']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategoriaContenidos>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
