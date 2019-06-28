import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { ICuentaAsociada, CuentaAsociada } from 'app/shared/model/cuenta-asociada.model';
import { CuentaAsociadaService } from './cuenta-asociada.service';

@Component({
  selector: 'jhi-cuenta-asociada-update',
  templateUrl: './cuenta-asociada-update.component.html'
})
export class CuentaAsociadaUpdateComponent implements OnInit {
  isSaving: boolean;
  fechaVencimientoDp: any;

  editForm = this.fb.group({
    id: [],
    identificaciontitular: [],
    nombreTitular: [],
    apellidoTitular: [],
    numeroCuenta: [],
    tipoCuenta: [],
    codigoSeguridad: [],
    fechaVencimiento: []
  });

  constructor(protected cuentaAsociadaService: CuentaAsociadaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ cuentaAsociada }) => {
      this.updateForm(cuentaAsociada);
    });
  }

  updateForm(cuentaAsociada: ICuentaAsociada) {
    this.editForm.patchValue({
      id: cuentaAsociada.id,
      identificaciontitular: cuentaAsociada.identificaciontitular,
      nombreTitular: cuentaAsociada.nombreTitular,
      apellidoTitular: cuentaAsociada.apellidoTitular,
      numeroCuenta: cuentaAsociada.numeroCuenta,
      tipoCuenta: cuentaAsociada.tipoCuenta,
      codigoSeguridad: cuentaAsociada.codigoSeguridad,
      fechaVencimiento: cuentaAsociada.fechaVencimiento
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const cuentaAsociada = this.createFromForm();
    if (cuentaAsociada.id !== undefined) {
      this.subscribeToSaveResponse(this.cuentaAsociadaService.update(cuentaAsociada));
    } else {
      this.subscribeToSaveResponse(this.cuentaAsociadaService.create(cuentaAsociada));
    }
  }

  private createFromForm(): ICuentaAsociada {
    return {
      ...new CuentaAsociada(),
      id: this.editForm.get(['id']).value,
      identificaciontitular: this.editForm.get(['identificaciontitular']).value,
      nombreTitular: this.editForm.get(['nombreTitular']).value,
      apellidoTitular: this.editForm.get(['apellidoTitular']).value,
      numeroCuenta: this.editForm.get(['numeroCuenta']).value,
      tipoCuenta: this.editForm.get(['tipoCuenta']).value,
      codigoSeguridad: this.editForm.get(['codigoSeguridad']).value,
      fechaVencimiento: this.editForm.get(['fechaVencimiento']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICuentaAsociada>>) {
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
