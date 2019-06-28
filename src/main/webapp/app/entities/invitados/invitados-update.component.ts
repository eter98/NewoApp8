import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IInvitados, Invitados } from 'app/shared/model/invitados.model';
import { InvitadosService } from './invitados.service';
import { IMiembros } from 'app/shared/model/miembros.model';
import { MiembrosService } from 'app/entities/miembros';

@Component({
  selector: 'jhi-invitados-update',
  templateUrl: './invitados-update.component.html'
})
export class InvitadosUpdateComponent implements OnInit {
  isSaving: boolean;

  miembros: IMiembros[];

  editForm = this.fb.group({
    id: [],
    nombre: [],
    apellido: [],
    tipoDocumento: [],
    identificacion: [],
    correo: [],
    telefono: [],
    miembro: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected invitadosService: InvitadosService,
    protected miembrosService: MiembrosService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ invitados }) => {
      this.updateForm(invitados);
    });
    this.miembrosService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMiembros[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMiembros[]>) => response.body)
      )
      .subscribe((res: IMiembros[]) => (this.miembros = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(invitados: IInvitados) {
    this.editForm.patchValue({
      id: invitados.id,
      nombre: invitados.nombre,
      apellido: invitados.apellido,
      tipoDocumento: invitados.tipoDocumento,
      identificacion: invitados.identificacion,
      correo: invitados.correo,
      telefono: invitados.telefono,
      miembro: invitados.miembro
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const invitados = this.createFromForm();
    if (invitados.id !== undefined) {
      this.subscribeToSaveResponse(this.invitadosService.update(invitados));
    } else {
      this.subscribeToSaveResponse(this.invitadosService.create(invitados));
    }
  }

  private createFromForm(): IInvitados {
    return {
      ...new Invitados(),
      id: this.editForm.get(['id']).value,
      nombre: this.editForm.get(['nombre']).value,
      apellido: this.editForm.get(['apellido']).value,
      tipoDocumento: this.editForm.get(['tipoDocumento']).value,
      identificacion: this.editForm.get(['identificacion']).value,
      correo: this.editForm.get(['correo']).value,
      telefono: this.editForm.get(['telefono']).value,
      miembro: this.editForm.get(['miembro']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInvitados>>) {
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
}
