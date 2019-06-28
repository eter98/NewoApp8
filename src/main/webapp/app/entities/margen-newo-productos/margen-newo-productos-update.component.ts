import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMargenNewoProductos, MargenNewoProductos } from 'app/shared/model/margen-newo-productos.model';
import { MargenNewoProductosService } from './margen-newo-productos.service';
import { IProductosServicios } from 'app/shared/model/productos-servicios.model';
import { ProductosServiciosService } from 'app/entities/productos-servicios';

@Component({
  selector: 'jhi-margen-newo-productos-update',
  templateUrl: './margen-newo-productos-update.component.html'
})
export class MargenNewoProductosUpdateComponent implements OnInit {
  isSaving: boolean;

  productosservicios: IProductosServicios[];

  editForm = this.fb.group({
    id: [],
    porcentajeMargen: [],
    producto: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected margenNewoProductosService: MargenNewoProductosService,
    protected productosServiciosService: ProductosServiciosService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ margenNewoProductos }) => {
      this.updateForm(margenNewoProductos);
    });
    this.productosServiciosService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IProductosServicios[]>) => mayBeOk.ok),
        map((response: HttpResponse<IProductosServicios[]>) => response.body)
      )
      .subscribe((res: IProductosServicios[]) => (this.productosservicios = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(margenNewoProductos: IMargenNewoProductos) {
    this.editForm.patchValue({
      id: margenNewoProductos.id,
      porcentajeMargen: margenNewoProductos.porcentajeMargen,
      producto: margenNewoProductos.producto
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const margenNewoProductos = this.createFromForm();
    if (margenNewoProductos.id !== undefined) {
      this.subscribeToSaveResponse(this.margenNewoProductosService.update(margenNewoProductos));
    } else {
      this.subscribeToSaveResponse(this.margenNewoProductosService.create(margenNewoProductos));
    }
  }

  private createFromForm(): IMargenNewoProductos {
    return {
      ...new MargenNewoProductos(),
      id: this.editForm.get(['id']).value,
      porcentajeMargen: this.editForm.get(['porcentajeMargen']).value,
      producto: this.editForm.get(['producto']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMargenNewoProductos>>) {
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

  trackProductosServiciosById(index: number, item: IProductosServicios) {
    return item.id;
  }
}
