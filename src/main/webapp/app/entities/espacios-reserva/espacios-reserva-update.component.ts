import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IEspaciosReserva, EspaciosReserva } from 'app/shared/model/espacios-reserva.model';
import { EspaciosReservaService } from './espacios-reserva.service';
import { ISedes } from 'app/shared/model/sedes.model';
import { SedesService } from 'app/entities/sedes';

@Component({
  selector: 'jhi-espacios-reserva-update',
  templateUrl: './espacios-reserva-update.component.html'
})
export class EspaciosReservaUpdateComponent implements OnInit {
  isSaving: boolean;

  sedes: ISedes[];

  editForm = this.fb.group({
    id: [],
    nombre: [],
    descripcion: [],
    facilidades: [],
    capacidad: [],
    apertura: [],
    cierre: [],
    tarifa1Hora: [],
    tarifa2Hora: [],
    tarifa3Hora: [],
    tarifa4Hora: [],
    tarifa5Hora: [],
    tarifa6Hora: [],
    tarifa7Hora: [],
    tarifa8Hora: [],
    impuesto: [],
    wifi: [],
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
    sede: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected espaciosReservaService: EspaciosReservaService,
    protected sedesService: SedesService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ espaciosReserva }) => {
      this.updateForm(espaciosReserva);
    });
    this.sedesService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ISedes[]>) => mayBeOk.ok),
        map((response: HttpResponse<ISedes[]>) => response.body)
      )
      .subscribe((res: ISedes[]) => (this.sedes = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(espaciosReserva: IEspaciosReserva) {
    this.editForm.patchValue({
      id: espaciosReserva.id,
      nombre: espaciosReserva.nombre,
      descripcion: espaciosReserva.descripcion,
      facilidades: espaciosReserva.facilidades,
      capacidad: espaciosReserva.capacidad,
      apertura: espaciosReserva.apertura,
      cierre: espaciosReserva.cierre,
      tarifa1Hora: espaciosReserva.tarifa1Hora,
      tarifa2Hora: espaciosReserva.tarifa2Hora,
      tarifa3Hora: espaciosReserva.tarifa3Hora,
      tarifa4Hora: espaciosReserva.tarifa4Hora,
      tarifa5Hora: espaciosReserva.tarifa5Hora,
      tarifa6Hora: espaciosReserva.tarifa6Hora,
      tarifa7Hora: espaciosReserva.tarifa7Hora,
      tarifa8Hora: espaciosReserva.tarifa8Hora,
      impuesto: espaciosReserva.impuesto,
      wifi: espaciosReserva.wifi,
      video: espaciosReserva.video,
      videoContentType: espaciosReserva.videoContentType,
      imagen1: espaciosReserva.imagen1,
      imagen1ContentType: espaciosReserva.imagen1ContentType,
      imagen2: espaciosReserva.imagen2,
      imagen2ContentType: espaciosReserva.imagen2ContentType,
      imagen3: espaciosReserva.imagen3,
      imagen3ContentType: espaciosReserva.imagen3ContentType,
      imagen4: espaciosReserva.imagen4,
      imagen4ContentType: espaciosReserva.imagen4ContentType,
      imagen5: espaciosReserva.imagen5,
      imagen5ContentType: espaciosReserva.imagen5ContentType,
      imagen6: espaciosReserva.imagen6,
      imagen6ContentType: espaciosReserva.imagen6ContentType,
      sede: espaciosReserva.sede
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
    const espaciosReserva = this.createFromForm();
    if (espaciosReserva.id !== undefined) {
      this.subscribeToSaveResponse(this.espaciosReservaService.update(espaciosReserva));
    } else {
      this.subscribeToSaveResponse(this.espaciosReservaService.create(espaciosReserva));
    }
  }

  private createFromForm(): IEspaciosReserva {
    return {
      ...new EspaciosReserva(),
      id: this.editForm.get(['id']).value,
      nombre: this.editForm.get(['nombre']).value,
      descripcion: this.editForm.get(['descripcion']).value,
      facilidades: this.editForm.get(['facilidades']).value,
      capacidad: this.editForm.get(['capacidad']).value,
      apertura: this.editForm.get(['apertura']).value,
      cierre: this.editForm.get(['cierre']).value,
      tarifa1Hora: this.editForm.get(['tarifa1Hora']).value,
      tarifa2Hora: this.editForm.get(['tarifa2Hora']).value,
      tarifa3Hora: this.editForm.get(['tarifa3Hora']).value,
      tarifa4Hora: this.editForm.get(['tarifa4Hora']).value,
      tarifa5Hora: this.editForm.get(['tarifa5Hora']).value,
      tarifa6Hora: this.editForm.get(['tarifa6Hora']).value,
      tarifa7Hora: this.editForm.get(['tarifa7Hora']).value,
      tarifa8Hora: this.editForm.get(['tarifa8Hora']).value,
      impuesto: this.editForm.get(['impuesto']).value,
      wifi: this.editForm.get(['wifi']).value,
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
      sede: this.editForm.get(['sede']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEspaciosReserva>>) {
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

  trackSedesById(index: number, item: ISedes) {
    return item.id;
  }
}
