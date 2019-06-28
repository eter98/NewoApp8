import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IProductosServicios, ProductosServicios } from 'app/shared/model/productos-servicios.model';
import { ProductosServiciosService } from './productos-servicios.service';

@Component({
  selector: 'jhi-productos-servicios-update',
  templateUrl: './productos-servicios-update.component.html'
})
export class ProductosServiciosUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    nombreProducto: [],
    foto: [],
    fotoContentType: [],
    descripcion: [],
    inventariables: [],
    valor: [],
    impuesto: [],
    video: [],
    videoContentType: [],
    imagen1: [],
    imagen1ContentType: [],
    imagen2: [],
    imagen2ContentType: [],
    imagen3: [],
    imagen3ContentType: [],
    imagen4: [],
    imagen4ContentType: [],
    imagen5: [],
    imagen5ContentType: [],
    imagen6: [],
    imagen6ContentType: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected productosServiciosService: ProductosServiciosService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ productosServicios }) => {
      this.updateForm(productosServicios);
    });
  }

  updateForm(productosServicios: IProductosServicios) {
    this.editForm.patchValue({
      id: productosServicios.id,
      nombreProducto: productosServicios.nombreProducto,
      foto: productosServicios.foto,
      fotoContentType: productosServicios.fotoContentType,
      descripcion: productosServicios.descripcion,
      inventariables: productosServicios.inventariables,
      valor: productosServicios.valor,
      impuesto: productosServicios.impuesto,
      video: productosServicios.video,
      videoContentType: productosServicios.videoContentType,
      imagen1: productosServicios.imagen1,
      imagen1ContentType: productosServicios.imagen1ContentType,
      imagen2: productosServicios.imagen2,
      imagen2ContentType: productosServicios.imagen2ContentType,
      imagen3: productosServicios.imagen3,
      imagen3ContentType: productosServicios.imagen3ContentType,
      imagen4: productosServicios.imagen4,
      imagen4ContentType: productosServicios.imagen4ContentType,
      imagen5: productosServicios.imagen5,
      imagen5ContentType: productosServicios.imagen5ContentType,
      imagen6: productosServicios.imagen6,
      imagen6ContentType: productosServicios.imagen6ContentType
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, field: string, isImage) {
    return new Promise((resolve, reject) => {
      if (event && event.target && event.target.files && event.target.files[0]) {
        const file = event.target.files[0];
        if (isImage && !/^image\//.test(file.type)) {
          reject(`File was expected to be an image but was found to be ${file.type}`);
        } else {
          const filedContentType: string = field + 'ContentType';
          this.dataUtils.toBase64(file, base64Data => {
            this.editForm.patchValue({
              [field]: base64Data,
              [filedContentType]: file.type
            });
          });
        }
      } else {
        reject(`Base64 data was not set as file could not be extracted from passed parameter: ${event}`);
      }
    }).then(
      () => console.log('blob added'), // sucess
      this.onError
    );
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string) {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const productosServicios = this.createFromForm();
    if (productosServicios.id !== undefined) {
      this.subscribeToSaveResponse(this.productosServiciosService.update(productosServicios));
    } else {
      this.subscribeToSaveResponse(this.productosServiciosService.create(productosServicios));
    }
  }

  private createFromForm(): IProductosServicios {
    return {
      ...new ProductosServicios(),
      id: this.editForm.get(['id']).value,
      nombreProducto: this.editForm.get(['nombreProducto']).value,
      fotoContentType: this.editForm.get(['fotoContentType']).value,
      foto: this.editForm.get(['foto']).value,
      descripcion: this.editForm.get(['descripcion']).value,
      inventariables: this.editForm.get(['inventariables']).value,
      valor: this.editForm.get(['valor']).value,
      impuesto: this.editForm.get(['impuesto']).value,
      videoContentType: this.editForm.get(['videoContentType']).value,
      video: this.editForm.get(['video']).value,
      imagen1ContentType: this.editForm.get(['imagen1ContentType']).value,
      imagen1: this.editForm.get(['imagen1']).value,
      imagen2ContentType: this.editForm.get(['imagen2ContentType']).value,
      imagen2: this.editForm.get(['imagen2']).value,
      imagen3ContentType: this.editForm.get(['imagen3ContentType']).value,
      imagen3: this.editForm.get(['imagen3']).value,
      imagen4ContentType: this.editForm.get(['imagen4ContentType']).value,
      imagen4: this.editForm.get(['imagen4']).value,
      imagen5ContentType: this.editForm.get(['imagen5ContentType']).value,
      imagen5: this.editForm.get(['imagen5']).value,
      imagen6ContentType: this.editForm.get(['imagen6ContentType']).value,
      imagen6: this.editForm.get(['imagen6']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductosServicios>>) {
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
}
