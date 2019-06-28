import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { ILanding, Landing } from 'app/shared/model/landing.model';
import { LandingService } from './landing.service';
import { ISedes } from 'app/shared/model/sedes.model';
import { SedesService } from 'app/entities/sedes';

@Component({
  selector: 'jhi-landing-update',
  templateUrl: './landing-update.component.html'
})
export class LandingUpdateComponent implements OnInit {
  isSaving: boolean;

  sedes: ISedes[];

  editForm = this.fb.group({
    id: [],
    nombre: [],
    descripcion: [],
    telefonoNegocio: [],
    numeroPuestos: [],
    tarifa: [],
    fotografia: [],
    fotografiaContentType: [],
    mpuesto: [],
    sede: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected landingService: LandingService,
    protected sedesService: SedesService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ landing }) => {
      this.updateForm(landing);
    });
    this.sedesService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ISedes[]>) => mayBeOk.ok),
        map((response: HttpResponse<ISedes[]>) => response.body)
      )
      .subscribe((res: ISedes[]) => (this.sedes = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(landing: ILanding) {
    this.editForm.patchValue({
      id: landing.id,
      nombre: landing.nombre,
      descripcion: landing.descripcion,
      telefonoNegocio: landing.telefonoNegocio,
      numeroPuestos: landing.numeroPuestos,
      tarifa: landing.tarifa,
      fotografia: landing.fotografia,
      fotografiaContentType: landing.fotografiaContentType,
      mpuesto: landing.mpuesto,
      sede: landing.sede
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
    const landing = this.createFromForm();
    if (landing.id !== undefined) {
      this.subscribeToSaveResponse(this.landingService.update(landing));
    } else {
      this.subscribeToSaveResponse(this.landingService.create(landing));
    }
  }

  private createFromForm(): ILanding {
    return {
      ...new Landing(),
      id: this.editForm.get(['id']).value,
      nombre: this.editForm.get(['nombre']).value,
      descripcion: this.editForm.get(['descripcion']).value,
      telefonoNegocio: this.editForm.get(['telefonoNegocio']).value,
      numeroPuestos: this.editForm.get(['numeroPuestos']).value,
      tarifa: this.editForm.get(['tarifa']).value,
      fotografiaContentType: this.editForm.get(['fotografiaContentType']).value,
      fotografia: this.editForm.get(['fotografia']).value,
      mpuesto: this.editForm.get(['mpuesto']).value,
      sede: this.editForm.get(['sede']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILanding>>) {
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
