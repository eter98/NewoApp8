import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ITipoPrepagoConsumo, TipoPrepagoConsumo } from 'app/shared/model/tipo-prepago-consumo.model';
import { TipoPrepagoConsumoService } from './tipo-prepago-consumo.service';
import { IBeneficio } from 'app/shared/model/beneficio.model';
import { BeneficioService } from 'app/entities/beneficio';

@Component({
  selector: 'jhi-tipo-prepago-consumo-update',
  templateUrl: './tipo-prepago-consumo-update.component.html'
})
export class TipoPrepagoConsumoUpdateComponent implements OnInit {
  isSaving: boolean;

  beneficios: IBeneficio[];

  editForm = this.fb.group({
    id: [],
    nombre: [],
    descripcion: [],
    valorMinimo: [],
    valorMaximo: [],
    tipoBeneficio: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected tipoPrepagoConsumoService: TipoPrepagoConsumoService,
    protected beneficioService: BeneficioService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ tipoPrepagoConsumo }) => {
      this.updateForm(tipoPrepagoConsumo);
    });
    this.beneficioService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IBeneficio[]>) => mayBeOk.ok),
        map((response: HttpResponse<IBeneficio[]>) => response.body)
      )
      .subscribe((res: IBeneficio[]) => (this.beneficios = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(tipoPrepagoConsumo: ITipoPrepagoConsumo) {
    this.editForm.patchValue({
      id: tipoPrepagoConsumo.id,
      nombre: tipoPrepagoConsumo.nombre,
      descripcion: tipoPrepagoConsumo.descripcion,
      valorMinimo: tipoPrepagoConsumo.valorMinimo,
      valorMaximo: tipoPrepagoConsumo.valorMaximo,
      tipoBeneficio: tipoPrepagoConsumo.tipoBeneficio
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const tipoPrepagoConsumo = this.createFromForm();
    if (tipoPrepagoConsumo.id !== undefined) {
      this.subscribeToSaveResponse(this.tipoPrepagoConsumoService.update(tipoPrepagoConsumo));
    } else {
      this.subscribeToSaveResponse(this.tipoPrepagoConsumoService.create(tipoPrepagoConsumo));
    }
  }

  private createFromForm(): ITipoPrepagoConsumo {
    return {
      ...new TipoPrepagoConsumo(),
      id: this.editForm.get(['id']).value,
      nombre: this.editForm.get(['nombre']).value,
      descripcion: this.editForm.get(['descripcion']).value,
      valorMinimo: this.editForm.get(['valorMinimo']).value,
      valorMaximo: this.editForm.get(['valorMaximo']).value,
      tipoBeneficio: this.editForm.get(['tipoBeneficio']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITipoPrepagoConsumo>>) {
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

  trackBeneficioById(index: number, item: IBeneficio) {
    return item.id;
  }
}
