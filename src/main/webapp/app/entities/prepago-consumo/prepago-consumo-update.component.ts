import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IPrepagoConsumo, PrepagoConsumo } from 'app/shared/model/prepago-consumo.model';
import { PrepagoConsumoService } from './prepago-consumo.service';
import { IMiembros } from 'app/shared/model/miembros.model';
import { MiembrosService } from 'app/entities/miembros';
import { ITipoPrepagoConsumo } from 'app/shared/model/tipo-prepago-consumo.model';
import { TipoPrepagoConsumoService } from 'app/entities/tipo-prepago-consumo';

@Component({
  selector: 'jhi-prepago-consumo-update',
  templateUrl: './prepago-consumo-update.component.html'
})
export class PrepagoConsumoUpdateComponent implements OnInit {
  isSaving: boolean;

  miembros: IMiembros[];

  tipoprepagoconsumos: ITipoPrepagoConsumo[];
  fechaRegistroDp: any;
  fechaSaldoActualDp: any;

  editForm = this.fb.group({
    id: [],
    aporte: [],
    saldoActual: [],
    fechaRegistro: [],
    fechaSaldoActual: [],
    miembro: [],
    tipoPrepago: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected prepagoConsumoService: PrepagoConsumoService,
    protected miembrosService: MiembrosService,
    protected tipoPrepagoConsumoService: TipoPrepagoConsumoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ prepagoConsumo }) => {
      this.updateForm(prepagoConsumo);
    });
    this.miembrosService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMiembros[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMiembros[]>) => response.body)
      )
      .subscribe((res: IMiembros[]) => (this.miembros = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.tipoPrepagoConsumoService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ITipoPrepagoConsumo[]>) => mayBeOk.ok),
        map((response: HttpResponse<ITipoPrepagoConsumo[]>) => response.body)
      )
      .subscribe((res: ITipoPrepagoConsumo[]) => (this.tipoprepagoconsumos = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(prepagoConsumo: IPrepagoConsumo) {
    this.editForm.patchValue({
      id: prepagoConsumo.id,
      aporte: prepagoConsumo.aporte,
      saldoActual: prepagoConsumo.saldoActual,
      fechaRegistro: prepagoConsumo.fechaRegistro,
      fechaSaldoActual: prepagoConsumo.fechaSaldoActual,
      miembro: prepagoConsumo.miembro,
      tipoPrepago: prepagoConsumo.tipoPrepago
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const prepagoConsumo = this.createFromForm();
    if (prepagoConsumo.id !== undefined) {
      this.subscribeToSaveResponse(this.prepagoConsumoService.update(prepagoConsumo));
    } else {
      this.subscribeToSaveResponse(this.prepagoConsumoService.create(prepagoConsumo));
    }
  }

  private createFromForm(): IPrepagoConsumo {
    return {
      ...new PrepagoConsumo(),
      id: this.editForm.get(['id']).value,
      aporte: this.editForm.get(['aporte']).value,
      saldoActual: this.editForm.get(['saldoActual']).value,
      fechaRegistro: this.editForm.get(['fechaRegistro']).value,
      fechaSaldoActual: this.editForm.get(['fechaSaldoActual']).value,
      miembro: this.editForm.get(['miembro']).value,
      tipoPrepago: this.editForm.get(['tipoPrepago']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPrepagoConsumo>>) {
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

  trackMiembrosById(index: number, item: IMiembros) {
    return item.id;
  }

  trackTipoPrepagoConsumoById(index: number, item: ITipoPrepagoConsumo) {
    return item.id;
  }
}
