import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ITipoRecurso, TipoRecurso } from 'app/shared/model/tipo-recurso.model';
import { TipoRecursoService } from './tipo-recurso.service';

@Component({
  selector: 'jhi-tipo-recurso-update',
  templateUrl: './tipo-recurso-update.component.html'
})
export class TipoRecursoUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    nombre: [],
    descripcion: []
  });

  constructor(protected tipoRecursoService: TipoRecursoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ tipoRecurso }) => {
      this.updateForm(tipoRecurso);
    });
  }

  updateForm(tipoRecurso: ITipoRecurso) {
    this.editForm.patchValue({
      id: tipoRecurso.id,
      nombre: tipoRecurso.nombre,
      descripcion: tipoRecurso.descripcion
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const tipoRecurso = this.createFromForm();
    if (tipoRecurso.id !== undefined) {
      this.subscribeToSaveResponse(this.tipoRecursoService.update(tipoRecurso));
    } else {
      this.subscribeToSaveResponse(this.tipoRecursoService.create(tipoRecurso));
    }
  }

  private createFromForm(): ITipoRecurso {
    return {
      ...new TipoRecurso(),
      id: this.editForm.get(['id']).value,
      nombre: this.editForm.get(['nombre']).value,
      descripcion: this.editForm.get(['descripcion']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITipoRecurso>>) {
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
