import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IEspacioLibre, EspacioLibre } from 'app/shared/model/espacio-libre.model';
import { EspacioLibreService } from './espacio-libre.service';
import { ISedes } from 'app/shared/model/sedes.model';
import { SedesService } from 'app/entities/sedes';
import { ITipoEspacio } from 'app/shared/model/tipo-espacio.model';
import { TipoEspacioService } from 'app/entities/tipo-espacio';

@Component({
  selector: 'jhi-espacio-libre-update',
  templateUrl: './espacio-libre-update.component.html'
})
export class EspacioLibreUpdateComponent implements OnInit {
  isSaving: boolean;

  sedes: ISedes[];

  tipoespacios: ITipoEspacio[];

  editForm = this.fb.group({
    id: [],
    nombre: [],
    capacidadInstalada: [],
    wifi: [],
    tarifa1hMiembro: [],
    tarifa2hMiembro: [],
    tarifa3hMiembro: [],
    tarifa4hMiembro: [],
    tarifa5hMiembro: [],
    tarifa6hMiembro: [],
    tarifa7hMiembro: [],
    tarifa8hMiembro: [],
    tarifa1hInvitado: [],
    tarifa2hInvitado: [],
    tarifa3hInvitado: [],
    tarifa4hInvitado: [],
    tarifa5hInvitado: [],
    tarifa6hInvitado: [],
    tarifa7hInvitado: [],
    tarifa8hInvitado: [],
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
    imagen6ContentType: [],
    sede: [],
    tipoEspacio: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected espacioLibreService: EspacioLibreService,
    protected sedesService: SedesService,
    protected tipoEspacioService: TipoEspacioService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ espacioLibre }) => {
      this.updateForm(espacioLibre);
    });
    this.sedesService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ISedes[]>) => mayBeOk.ok),
        map((response: HttpResponse<ISedes[]>) => response.body)
      )
      .subscribe((res: ISedes[]) => (this.sedes = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.tipoEspacioService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ITipoEspacio[]>) => mayBeOk.ok),
        map((response: HttpResponse<ITipoEspacio[]>) => response.body)
      )
      .subscribe((res: ITipoEspacio[]) => (this.tipoespacios = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(espacioLibre: IEspacioLibre) {
    this.editForm.patchValue({
      id: espacioLibre.id,
      nombre: espacioLibre.nombre,
      capacidadInstalada: espacioLibre.capacidadInstalada,
      wifi: espacioLibre.wifi,
      tarifa1hMiembro: espacioLibre.tarifa1hMiembro,
      tarifa2hMiembro: espacioLibre.tarifa2hMiembro,
      tarifa3hMiembro: espacioLibre.tarifa3hMiembro,
      tarifa4hMiembro: espacioLibre.tarifa4hMiembro,
      tarifa5hMiembro: espacioLibre.tarifa5hMiembro,
      tarifa6hMiembro: espacioLibre.tarifa6hMiembro,
      tarifa7hMiembro: espacioLibre.tarifa7hMiembro,
      tarifa8hMiembro: espacioLibre.tarifa8hMiembro,
      tarifa1hInvitado: espacioLibre.tarifa1hInvitado,
      tarifa2hInvitado: espacioLibre.tarifa2hInvitado,
      tarifa3hInvitado: espacioLibre.tarifa3hInvitado,
      tarifa4hInvitado: espacioLibre.tarifa4hInvitado,
      tarifa5hInvitado: espacioLibre.tarifa5hInvitado,
      tarifa6hInvitado: espacioLibre.tarifa6hInvitado,
      tarifa7hInvitado: espacioLibre.tarifa7hInvitado,
      tarifa8hInvitado: espacioLibre.tarifa8hInvitado,
      impuesto: espacioLibre.impuesto,
      video: espacioLibre.video,
      videoContentType: espacioLibre.videoContentType,
      imagen1: espacioLibre.imagen1,
      imagen1ContentType: espacioLibre.imagen1ContentType,
      imagen2: espacioLibre.imagen2,
      imagen2ContentType: espacioLibre.imagen2ContentType,
      imagen3: espacioLibre.imagen3,
      imagen3ContentType: espacioLibre.imagen3ContentType,
      imagen4: espacioLibre.imagen4,
      imagen4ContentType: espacioLibre.imagen4ContentType,
      imagen5: espacioLibre.imagen5,
      imagen5ContentType: espacioLibre.imagen5ContentType,
      imagen6: espacioLibre.imagen6,
      imagen6ContentType: espacioLibre.imagen6ContentType,
      sede: espacioLibre.sede,
      tipoEspacio: espacioLibre.tipoEspacio
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
    const espacioLibre = this.createFromForm();
    if (espacioLibre.id !== undefined) {
      this.subscribeToSaveResponse(this.espacioLibreService.update(espacioLibre));
    } else {
      this.subscribeToSaveResponse(this.espacioLibreService.create(espacioLibre));
    }
  }

  private createFromForm(): IEspacioLibre {
    return {
      ...new EspacioLibre(),
      id: this.editForm.get(['id']).value,
      nombre: this.editForm.get(['nombre']).value,
      capacidadInstalada: this.editForm.get(['capacidadInstalada']).value,
      wifi: this.editForm.get(['wifi']).value,
      tarifa1hMiembro: this.editForm.get(['tarifa1hMiembro']).value,
      tarifa2hMiembro: this.editForm.get(['tarifa2hMiembro']).value,
      tarifa3hMiembro: this.editForm.get(['tarifa3hMiembro']).value,
      tarifa4hMiembro: this.editForm.get(['tarifa4hMiembro']).value,
      tarifa5hMiembro: this.editForm.get(['tarifa5hMiembro']).value,
      tarifa6hMiembro: this.editForm.get(['tarifa6hMiembro']).value,
      tarifa7hMiembro: this.editForm.get(['tarifa7hMiembro']).value,
      tarifa8hMiembro: this.editForm.get(['tarifa8hMiembro']).value,
      tarifa1hInvitado: this.editForm.get(['tarifa1hInvitado']).value,
      tarifa2hInvitado: this.editForm.get(['tarifa2hInvitado']).value,
      tarifa3hInvitado: this.editForm.get(['tarifa3hInvitado']).value,
      tarifa4hInvitado: this.editForm.get(['tarifa4hInvitado']).value,
      tarifa5hInvitado: this.editForm.get(['tarifa5hInvitado']).value,
      tarifa6hInvitado: this.editForm.get(['tarifa6hInvitado']).value,
      tarifa7hInvitado: this.editForm.get(['tarifa7hInvitado']).value,
      tarifa8hInvitado: this.editForm.get(['tarifa8hInvitado']).value,
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
      imagen6: this.editForm.get(['imagen6']).value,
      sede: this.editForm.get(['sede']).value,
      tipoEspacio: this.editForm.get(['tipoEspacio']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEspacioLibre>>) {
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

  trackTipoEspacioById(index: number, item: ITipoEspacio) {
    return item.id;
  }
}
