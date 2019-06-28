import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IUsoRecursoFisico, UsoRecursoFisico } from 'app/shared/model/uso-recurso-fisico.model';
import { UsoRecursoFisicoService } from './uso-recurso-fisico.service';
import { IRecursosFisicos } from 'app/shared/model/recursos-fisicos.model';
import { RecursosFisicosService } from 'app/entities/recursos-fisicos';
import { IMiembros } from 'app/shared/model/miembros.model';
import { MiembrosService } from 'app/entities/miembros';

@Component({
  selector: 'jhi-uso-recurso-fisico-update',
  templateUrl: './uso-recurso-fisico-update.component.html'
})
export class UsoRecursoFisicoUpdateComponent implements OnInit {
  isSaving: boolean;

  recursosfisicos: IRecursosFisicos[];

  miembros: IMiembros[];

  editForm = this.fb.group({
    id: [],
    registroFechaInicio: [],
    registroFechaFinal: [],
    tipoRegistro: [],
    recurso: [],
    miembro: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected usoRecursoFisicoService: UsoRecursoFisicoService,
    protected recursosFisicosService: RecursosFisicosService,
    protected miembrosService: MiembrosService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ usoRecursoFisico }) => {
      this.updateForm(usoRecursoFisico);
    });
    this.recursosFisicosService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IRecursosFisicos[]>) => mayBeOk.ok),
        map((response: HttpResponse<IRecursosFisicos[]>) => response.body)
      )
      .subscribe((res: IRecursosFisicos[]) => (this.recursosfisicos = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.miembrosService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMiembros[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMiembros[]>) => response.body)
      )
      .subscribe((res: IMiembros[]) => (this.miembros = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(usoRecursoFisico: IUsoRecursoFisico) {
    this.editForm.patchValue({
      id: usoRecursoFisico.id,
      registroFechaInicio:
        usoRecursoFisico.registroFechaInicio != null ? usoRecursoFisico.registroFechaInicio.format(DATE_TIME_FORMAT) : null,
      registroFechaFinal: usoRecursoFisico.registroFechaFinal != null ? usoRecursoFisico.registroFechaFinal.format(DATE_TIME_FORMAT) : null,
      tipoRegistro: usoRecursoFisico.tipoRegistro,
      recurso: usoRecursoFisico.recurso,
      miembro: usoRecursoFisico.miembro
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const usoRecursoFisico = this.createFromForm();
    if (usoRecursoFisico.id !== undefined) {
      this.subscribeToSaveResponse(this.usoRecursoFisicoService.update(usoRecursoFisico));
    } else {
      this.subscribeToSaveResponse(this.usoRecursoFisicoService.create(usoRecursoFisico));
    }
  }

  private createFromForm(): IUsoRecursoFisico {
    return {
      ...new UsoRecursoFisico(),
      id: this.editForm.get(['id']).value,
      registroFechaInicio:
        this.editForm.get(['registroFechaInicio']).value != null
          ? moment(this.editForm.get(['registroFechaInicio']).value, DATE_TIME_FORMAT)
          : undefined,
      registroFechaFinal:
        this.editForm.get(['registroFechaFinal']).value != null
          ? moment(this.editForm.get(['registroFechaFinal']).value, DATE_TIME_FORMAT)
          : undefined,
      tipoRegistro: this.editForm.get(['tipoRegistro']).value,
      recurso: this.editForm.get(['recurso']).value,
      miembro: this.editForm.get(['miembro']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUsoRecursoFisico>>) {
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

  trackRecursosFisicosById(index: number, item: IRecursosFisicos) {
    return item.id;
  }

  trackMiembrosById(index: number, item: IMiembros) {
    return item.id;
  }
}
