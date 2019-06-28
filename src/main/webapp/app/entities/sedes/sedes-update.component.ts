import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { ISedes, Sedes } from 'app/shared/model/sedes.model';
import { SedesService } from './sedes.service';
import { ICiudad } from 'app/shared/model/ciudad.model';
import { CiudadService } from 'app/entities/ciudad';

@Component({
  selector: 'jhi-sedes-update',
  templateUrl: './sedes-update.component.html'
})
export class SedesUpdateComponent implements OnInit {
  isSaving: boolean;

  ciudads: ICiudad[];

  editForm = this.fb.group({
    id: [],
    nombreSede: [],
    coordenadaX: [],
    coordenadaY: [],
    direccion: [],
    telefonoComunidad: [],
    telefonoNegocio: [],
    descripcionSede: [],
    horario: [],
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
    imagen6ContentType: [],
    ciudad: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected sedesService: SedesService,
    protected ciudadService: CiudadService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ sedes }) => {
      this.updateForm(sedes);
    });
    this.ciudadService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICiudad[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICiudad[]>) => response.body)
      )
      .subscribe((res: ICiudad[]) => (this.ciudads = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(sedes: ISedes) {
    this.editForm.patchValue({
      id: sedes.id,
      nombreSede: sedes.nombreSede,
      coordenadaX: sedes.coordenadaX,
      coordenadaY: sedes.coordenadaY,
      direccion: sedes.direccion,
      telefonoComunidad: sedes.telefonoComunidad,
      telefonoNegocio: sedes.telefonoNegocio,
      descripcionSede: sedes.descripcionSede,
      horario: sedes.horario,
      video: sedes.video,
      videoContentType: sedes.videoContentType,
      imagen1: sedes.imagen1,
      imagen1ContentType: sedes.imagen1ContentType,
      imagen2: sedes.imagen2,
      imagen2ContentType: sedes.imagen2ContentType,
      imagen3: sedes.imagen3,
      imagen3ContentType: sedes.imagen3ContentType,
      imagen4: sedes.imagen4,
      imagen4ContentType: sedes.imagen4ContentType,
      imagen5: sedes.imagen5,
      imagen5ContentType: sedes.imagen5ContentType,
      imagen6: sedes.imagen6,
      imagen6ContentType: sedes.imagen6ContentType,
      ciudad: sedes.ciudad
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
    const sedes = this.createFromForm();
    if (sedes.id !== undefined) {
      this.subscribeToSaveResponse(this.sedesService.update(sedes));
    } else {
      this.subscribeToSaveResponse(this.sedesService.create(sedes));
    }
  }

  private createFromForm(): ISedes {
    return {
      ...new Sedes(),
      id: this.editForm.get(['id']).value,
      nombreSede: this.editForm.get(['nombreSede']).value,
      coordenadaX: this.editForm.get(['coordenadaX']).value,
      coordenadaY: this.editForm.get(['coordenadaY']).value,
      direccion: this.editForm.get(['direccion']).value,
      telefonoComunidad: this.editForm.get(['telefonoComunidad']).value,
      telefonoNegocio: this.editForm.get(['telefonoNegocio']).value,
      descripcionSede: this.editForm.get(['descripcionSede']).value,
      horario: this.editForm.get(['horario']).value,
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
      imagen6: this.editForm.get(['imagen6']).value,
      ciudad: this.editForm.get(['ciudad']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISedes>>) {
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

  trackCiudadById(index: number, item: ICiudad) {
    return item.id;
  }
}
