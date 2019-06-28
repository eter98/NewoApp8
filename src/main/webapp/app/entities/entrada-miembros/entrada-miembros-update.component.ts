import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IEntradaMiembros, EntradaMiembros } from 'app/shared/model/entrada-miembros.model';
import { EntradaMiembrosService } from './entrada-miembros.service';
import { IMiembros } from 'app/shared/model/miembros.model';
import { MiembrosService } from 'app/entities/miembros';
import { IEspacioLibre } from 'app/shared/model/espacio-libre.model';
import { EspacioLibreService } from 'app/entities/espacio-libre';
import { IEspaciosReserva } from 'app/shared/model/espacios-reserva.model';
import { EspaciosReservaService } from 'app/entities/espacios-reserva';

@Component({
  selector: 'jhi-entrada-miembros-update',
  templateUrl: './entrada-miembros-update.component.html'
})
export class EntradaMiembrosUpdateComponent implements OnInit {
  isSaving: boolean;

  miembros: IMiembros[];

  espaciolibres: IEspacioLibre[];

  espaciosreservas: IEspaciosReserva[];

  editForm = this.fb.group({
    id: [],
    registroFecha: [],
    tipoEntrada: [],
    tipoIngreso: [],
    tiempoMaximo: [],
    miembro: [],
    espacio: [],
    espacioReserva: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected entradaMiembrosService: EntradaMiembrosService,
    protected miembrosService: MiembrosService,
    protected espacioLibreService: EspacioLibreService,
    protected espaciosReservaService: EspaciosReservaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ entradaMiembros }) => {
      this.updateForm(entradaMiembros);
    });
    this.miembrosService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMiembros[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMiembros[]>) => response.body)
      )
      .subscribe((res: IMiembros[]) => (this.miembros = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.espacioLibreService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IEspacioLibre[]>) => mayBeOk.ok),
        map((response: HttpResponse<IEspacioLibre[]>) => response.body)
      )
      .subscribe((res: IEspacioLibre[]) => (this.espaciolibres = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.espaciosReservaService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IEspaciosReserva[]>) => mayBeOk.ok),
        map((response: HttpResponse<IEspaciosReserva[]>) => response.body)
      )
      .subscribe((res: IEspaciosReserva[]) => (this.espaciosreservas = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(entradaMiembros: IEntradaMiembros) {
    this.editForm.patchValue({
      id: entradaMiembros.id,
      registroFecha: entradaMiembros.registroFecha != null ? entradaMiembros.registroFecha.format(DATE_TIME_FORMAT) : null,
      tipoEntrada: entradaMiembros.tipoEntrada,
      tipoIngreso: entradaMiembros.tipoIngreso,
      tiempoMaximo: entradaMiembros.tiempoMaximo,
      miembro: entradaMiembros.miembro,
      espacio: entradaMiembros.espacio,
      espacioReserva: entradaMiembros.espacioReserva
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const entradaMiembros = this.createFromForm();
    if (entradaMiembros.id !== undefined) {
      this.subscribeToSaveResponse(this.entradaMiembrosService.update(entradaMiembros));
    } else {
      this.subscribeToSaveResponse(this.entradaMiembrosService.create(entradaMiembros));
    }
  }

  private createFromForm(): IEntradaMiembros {
    return {
      ...new EntradaMiembros(),
      id: this.editForm.get(['id']).value,
      registroFecha:
        this.editForm.get(['registroFecha']).value != null
          ? moment(this.editForm.get(['registroFecha']).value, DATE_TIME_FORMAT)
          : undefined,
      tipoEntrada: this.editForm.get(['tipoEntrada']).value,
      tipoIngreso: this.editForm.get(['tipoIngreso']).value,
      tiempoMaximo: this.editForm.get(['tiempoMaximo']).value,
      miembro: this.editForm.get(['miembro']).value,
      espacio: this.editForm.get(['espacio']).value,
      espacioReserva: this.editForm.get(['espacioReserva']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEntradaMiembros>>) {
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

  trackEspacioLibreById(index: number, item: IEspacioLibre) {
    return item.id;
  }

  trackEspaciosReservaById(index: number, item: IEspaciosReserva) {
    return item.id;
  }
}
