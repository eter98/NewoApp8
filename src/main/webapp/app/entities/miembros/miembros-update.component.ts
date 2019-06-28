import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IMiembros, Miembros } from 'app/shared/model/miembros.model';
import { MiembrosService } from './miembros.service';
import { IUser, UserService } from 'app/core';
import { ISedes } from 'app/shared/model/sedes.model';
import { SedesService } from 'app/entities/sedes';

@Component({
  selector: 'jhi-miembros-update',
  templateUrl: './miembros-update.component.html'
})
export class MiembrosUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  sedes: ISedes[];
  fechaNacimientoDp: any;
  fechaRegistroDp: any;

  editForm = this.fb.group({
    id: [],
    nombre: [],
    apellido: [],
    nacionalidad: [],
    tipoDocumento: [],
    identificacion: [],
    fechaNacimiento: [],
    fechaRegistro: [],
    genero: [],
    correoElectronico: [],
    celular: [],
    derechosDeCompra: [],
    accesoIlimitado: [],
    aliado: [],
    host: [],
    miembro: [],
    sede: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected miembrosService: MiembrosService,
    protected userService: UserService,
    protected sedesService: SedesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ miembros }) => {
      this.updateForm(miembros);
    });
    this.userService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUser[]>) => response.body)
      )
      .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.sedesService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ISedes[]>) => mayBeOk.ok),
        map((response: HttpResponse<ISedes[]>) => response.body)
      )
      .subscribe((res: ISedes[]) => (this.sedes = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(miembros: IMiembros) {
    this.editForm.patchValue({
      id: miembros.id,
      nombre: miembros.nombre,
      apellido: miembros.apellido,
      nacionalidad: miembros.nacionalidad,
      tipoDocumento: miembros.tipoDocumento,
      identificacion: miembros.identificacion,
      fechaNacimiento: miembros.fechaNacimiento,
      fechaRegistro: miembros.fechaRegistro,
      genero: miembros.genero,
      correoElectronico: miembros.correoElectronico,
      celular: miembros.celular,
      derechosDeCompra: miembros.derechosDeCompra,
      accesoIlimitado: miembros.accesoIlimitado,
      aliado: miembros.aliado,
      host: miembros.host,
      miembro: miembros.miembro,
      sede: miembros.sede
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const miembros = this.createFromForm();
    if (miembros.id !== undefined) {
      this.subscribeToSaveResponse(this.miembrosService.update(miembros));
    } else {
      this.subscribeToSaveResponse(this.miembrosService.create(miembros));
    }
  }

  private createFromForm(): IMiembros {
    return {
      ...new Miembros(),
      id: this.editForm.get(['id']).value,
      nombre: this.editForm.get(['nombre']).value,
      apellido: this.editForm.get(['apellido']).value,
      nacionalidad: this.editForm.get(['nacionalidad']).value,
      tipoDocumento: this.editForm.get(['tipoDocumento']).value,
      identificacion: this.editForm.get(['identificacion']).value,
      fechaNacimiento: this.editForm.get(['fechaNacimiento']).value,
      fechaRegistro: this.editForm.get(['fechaRegistro']).value,
      genero: this.editForm.get(['genero']).value,
      correoElectronico: this.editForm.get(['correoElectronico']).value,
      celular: this.editForm.get(['celular']).value,
      derechosDeCompra: this.editForm.get(['derechosDeCompra']).value,
      accesoIlimitado: this.editForm.get(['accesoIlimitado']).value,
      aliado: this.editForm.get(['aliado']).value,
      host: this.editForm.get(['host']).value,
      miembro: this.editForm.get(['miembro']).value,
      sede: this.editForm.get(['sede']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMiembros>>) {
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

  trackUserById(index: number, item: IUser) {
    return item.id;
  }

  trackSedesById(index: number, item: ISedes) {
    return item.id;
  }
}
