import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ITipoEspacio, TipoEspacio } from 'app/shared/model/tipo-espacio.model';
import { TipoEspacioService } from './tipo-espacio.service';

@Component({
  selector: 'jhi-tipo-espacio-update',
  templateUrl: './tipo-espacio-update.component.html'
})
export class TipoEspacioUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    tipoEspacio: []
  });

  constructor(protected tipoEspacioService: TipoEspacioService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ tipoEspacio }) => {
      this.updateForm(tipoEspacio);
    });
  }

  updateForm(tipoEspacio: ITipoEspacio) {
    this.editForm.patchValue({
      id: tipoEspacio.id,
      tipoEspacio: tipoEspacio.tipoEspacio
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const tipoEspacio = this.createFromForm();
    if (tipoEspacio.id !== undefined) {
      this.subscribeToSaveResponse(this.tipoEspacioService.update(tipoEspacio));
    } else {
      this.subscribeToSaveResponse(this.tipoEspacioService.create(tipoEspacio));
    }
  }

  private createFromForm(): ITipoEspacio {
    return {
      ...new TipoEspacio(),
      id: this.editForm.get(['id']).value,
      tipoEspacio: this.editForm.get(['tipoEspacio']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITipoEspacio>>) {
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
