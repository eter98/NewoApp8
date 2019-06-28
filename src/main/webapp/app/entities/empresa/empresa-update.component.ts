import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IEmpresa, Empresa } from 'app/shared/model/empresa.model';
import { EmpresaService } from './empresa.service';
import { IMiembros } from 'app/shared/model/miembros.model';
import { MiembrosService } from 'app/entities/miembros';

@Component({
  selector: 'jhi-empresa-update',
  templateUrl: './empresa-update.component.html'
})
export class EmpresaUpdateComponent implements OnInit {
  isSaving: boolean;

  miembros: IMiembros[];

  editForm = this.fb.group({
    id: [],
    razonSocial: [],
    nit: [],
    direccion: [],
    telefono: [],
    correo: [],
    celular: [],
    aliado: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected empresaService: EmpresaService,
    protected miembrosService: MiembrosService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ empresa }) => {
      this.updateForm(empresa);
    });
    this.miembrosService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMiembros[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMiembros[]>) => response.body)
      )
      .subscribe((res: IMiembros[]) => (this.miembros = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(empresa: IEmpresa) {
    this.editForm.patchValue({
      id: empresa.id,
      razonSocial: empresa.razonSocial,
      nit: empresa.nit,
      direccion: empresa.direccion,
      telefono: empresa.telefono,
      correo: empresa.correo,
      celular: empresa.celular,
      aliado: empresa.aliado
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const empresa = this.createFromForm();
    if (empresa.id !== undefined) {
      this.subscribeToSaveResponse(this.empresaService.update(empresa));
    } else {
      this.subscribeToSaveResponse(this.empresaService.create(empresa));
    }
  }

  private createFromForm(): IEmpresa {
    return {
      ...new Empresa(),
      id: this.editForm.get(['id']).value,
      razonSocial: this.editForm.get(['razonSocial']).value,
      nit: this.editForm.get(['nit']).value,
      direccion: this.editForm.get(['direccion']).value,
      telefono: this.editForm.get(['telefono']).value,
      correo: this.editForm.get(['correo']).value,
      celular: this.editForm.get(['celular']).value,
      aliado: this.editForm.get(['aliado']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmpresa>>) {
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
