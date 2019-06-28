import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IFacturacion, Facturacion } from 'app/shared/model/facturacion.model';
import { FacturacionService } from './facturacion.service';
import { IEmpresa } from 'app/shared/model/empresa.model';
import { EmpresaService } from 'app/entities/empresa';
import { ICuentaAsociada } from 'app/shared/model/cuenta-asociada.model';
import { CuentaAsociadaService } from 'app/entities/cuenta-asociada';

@Component({
  selector: 'jhi-facturacion-update',
  templateUrl: './facturacion-update.component.html'
})
export class FacturacionUpdateComponent implements OnInit {
  isSaving: boolean;

  empresas: IEmpresa[];

  cuentaasociadas: ICuentaAsociada[];

  editForm = this.fb.group({
    id: [],
    titularFactura: [],
    tipoPersona: [],
    periodicidadFacturacion: [],
    maximoMonto: [],
    empresa: [],
    titular: [],
    cuentaAsociada: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected facturacionService: FacturacionService,
    protected empresaService: EmpresaService,
    protected cuentaAsociadaService: CuentaAsociadaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ facturacion }) => {
      this.updateForm(facturacion);
    });
    this.empresaService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IEmpresa[]>) => mayBeOk.ok),
        map((response: HttpResponse<IEmpresa[]>) => response.body)
      )
      .subscribe((res: IEmpresa[]) => (this.empresas = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.cuentaAsociadaService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICuentaAsociada[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICuentaAsociada[]>) => response.body)
      )
      .subscribe((res: ICuentaAsociada[]) => (this.cuentaasociadas = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(facturacion: IFacturacion) {
    this.editForm.patchValue({
      id: facturacion.id,
      titularFactura: facturacion.titularFactura,
      tipoPersona: facturacion.tipoPersona,
      periodicidadFacturacion: facturacion.periodicidadFacturacion,
      maximoMonto: facturacion.maximoMonto,
      empresa: facturacion.empresa,
      titular: facturacion.titular,
      cuentaAsociada: facturacion.cuentaAsociada
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const facturacion = this.createFromForm();
    if (facturacion.id !== undefined) {
      this.subscribeToSaveResponse(this.facturacionService.update(facturacion));
    } else {
      this.subscribeToSaveResponse(this.facturacionService.create(facturacion));
    }
  }

  private createFromForm(): IFacturacion {
    return {
      ...new Facturacion(),
      id: this.editForm.get(['id']).value,
      titularFactura: this.editForm.get(['titularFactura']).value,
      tipoPersona: this.editForm.get(['tipoPersona']).value,
      periodicidadFacturacion: this.editForm.get(['periodicidadFacturacion']).value,
      maximoMonto: this.editForm.get(['maximoMonto']).value,
      empresa: this.editForm.get(['empresa']).value,
      titular: this.editForm.get(['titular']).value,
      cuentaAsociada: this.editForm.get(['cuentaAsociada']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFacturacion>>) {
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

  trackEmpresaById(index: number, item: IEmpresa) {
    return item.id;
  }

  trackCuentaAsociadaById(index: number, item: ICuentaAsociada) {
    return item.id;
  }
}
