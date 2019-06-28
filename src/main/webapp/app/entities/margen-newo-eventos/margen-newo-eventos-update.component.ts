import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMargenNewoEventos, MargenNewoEventos } from 'app/shared/model/margen-newo-eventos.model';
import { MargenNewoEventosService } from './margen-newo-eventos.service';
import { IEvento } from 'app/shared/model/evento.model';
import { EventoService } from 'app/entities/evento';

@Component({
  selector: 'jhi-margen-newo-eventos-update',
  templateUrl: './margen-newo-eventos-update.component.html'
})
export class MargenNewoEventosUpdateComponent implements OnInit {
  isSaving: boolean;

  eventos: IEvento[];

  editForm = this.fb.group({
    id: [],
    porcentajeMargen: [],
    evento: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected margenNewoEventosService: MargenNewoEventosService,
    protected eventoService: EventoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ margenNewoEventos }) => {
      this.updateForm(margenNewoEventos);
    });
    this.eventoService
      .query({ filter: 'margennewoeventos-is-null' })
      .pipe(
        filter((mayBeOk: HttpResponse<IEvento[]>) => mayBeOk.ok),
        map((response: HttpResponse<IEvento[]>) => response.body)
      )
      .subscribe(
        (res: IEvento[]) => {
          if (!this.editForm.get('evento').value || !this.editForm.get('evento').value.id) {
            this.eventos = res;
          } else {
            this.eventoService
              .find(this.editForm.get('evento').value.id)
              .pipe(
                filter((subResMayBeOk: HttpResponse<IEvento>) => subResMayBeOk.ok),
                map((subResponse: HttpResponse<IEvento>) => subResponse.body)
              )
              .subscribe(
                (subRes: IEvento) => (this.eventos = [subRes].concat(res)),
                (subRes: HttpErrorResponse) => this.onError(subRes.message)
              );
          }
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(margenNewoEventos: IMargenNewoEventos) {
    this.editForm.patchValue({
      id: margenNewoEventos.id,
      porcentajeMargen: margenNewoEventos.porcentajeMargen,
      evento: margenNewoEventos.evento
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const margenNewoEventos = this.createFromForm();
    if (margenNewoEventos.id !== undefined) {
      this.subscribeToSaveResponse(this.margenNewoEventosService.update(margenNewoEventos));
    } else {
      this.subscribeToSaveResponse(this.margenNewoEventosService.create(margenNewoEventos));
    }
  }

  private createFromForm(): IMargenNewoEventos {
    return {
      ...new MargenNewoEventos(),
      id: this.editForm.get(['id']).value,
      porcentajeMargen: this.editForm.get(['porcentajeMargen']).value,
      evento: this.editForm.get(['evento']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMargenNewoEventos>>) {
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

  trackEventoById(index: number, item: IEvento) {
    return item.id;
  }
}
