import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IPerfilMiembro, PerfilMiembro } from 'app/shared/model/perfil-miembro.model';
import { PerfilMiembroService } from './perfil-miembro.service';
import { IMiembros } from 'app/shared/model/miembros.model';
import { MiembrosService } from 'app/entities/miembros';

@Component({
  selector: 'jhi-perfil-miembro-update',
  templateUrl: './perfil-miembro-update.component.html'
})
export class PerfilMiembroUpdateComponent implements OnInit {
  isSaving: boolean;

  miembros: IMiembros[];

  editForm = this.fb.group({
    id: [],
    biografia: [],
    foto1: [],
    foto1ContentType: [],
    foto2: [],
    foto2ContentType: [],
    fot3: [],
    fot3ContentType: [],
    conocimientosQueDomina: [],
    temasDeInteres: [],
    facebook: [],
    instagram: [],
    idGoogle: [],
    twiter: [],
    miembro: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected perfilMiembroService: PerfilMiembroService,
    protected miembrosService: MiembrosService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ perfilMiembro }) => {
      this.updateForm(perfilMiembro);
    });
    this.miembrosService
      .query({ filter: 'perfilmiembro-is-null' })
      .pipe(
        filter((mayBeOk: HttpResponse<IMiembros[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMiembros[]>) => response.body)
      )
      .subscribe(
        (res: IMiembros[]) => {
          if (!this.editForm.get('miembro').value || !this.editForm.get('miembro').value.id) {
            this.miembros = res;
          } else {
            this.miembrosService
              .find(this.editForm.get('miembro').value.id)
              .pipe(
                filter((subResMayBeOk: HttpResponse<IMiembros>) => subResMayBeOk.ok),
                map((subResponse: HttpResponse<IMiembros>) => subResponse.body)
              )
              .subscribe(
                (subRes: IMiembros) => (this.miembros = [subRes].concat(res)),
                (subRes: HttpErrorResponse) => this.onError(subRes.message)
              );
          }
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(perfilMiembro: IPerfilMiembro) {
    this.editForm.patchValue({
      id: perfilMiembro.id,
      biografia: perfilMiembro.biografia,
      foto1: perfilMiembro.foto1,
      foto1ContentType: perfilMiembro.foto1ContentType,
      foto2: perfilMiembro.foto2,
      foto2ContentType: perfilMiembro.foto2ContentType,
      fot3: perfilMiembro.fot3,
      fot3ContentType: perfilMiembro.fot3ContentType,
      conocimientosQueDomina: perfilMiembro.conocimientosQueDomina,
      temasDeInteres: perfilMiembro.temasDeInteres,
      facebook: perfilMiembro.facebook,
      instagram: perfilMiembro.instagram,
      idGoogle: perfilMiembro.idGoogle,
      twiter: perfilMiembro.twiter,
      miembro: perfilMiembro.miembro
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
    const perfilMiembro = this.createFromForm();
    if (perfilMiembro.id !== undefined) {
      this.subscribeToSaveResponse(this.perfilMiembroService.update(perfilMiembro));
    } else {
      this.subscribeToSaveResponse(this.perfilMiembroService.create(perfilMiembro));
    }
  }

  private createFromForm(): IPerfilMiembro {
    return {
      ...new PerfilMiembro(),
      id: this.editForm.get(['id']).value,
      biografia: this.editForm.get(['biografia']).value,
      foto1ContentType: this.editForm.get(['foto1ContentType']).value,
      foto1: this.editForm.get(['foto1']).value,
      foto2ContentType: this.editForm.get(['foto2ContentType']).value,
      foto2: this.editForm.get(['foto2']).value,
      fot3ContentType: this.editForm.get(['fot3ContentType']).value,
      fot3: this.editForm.get(['fot3']).value,
      conocimientosQueDomina: this.editForm.get(['conocimientosQueDomina']).value,
      temasDeInteres: this.editForm.get(['temasDeInteres']).value,
      facebook: this.editForm.get(['facebook']).value,
      instagram: this.editForm.get(['instagram']).value,
      idGoogle: this.editForm.get(['idGoogle']).value,
      twiter: this.editForm.get(['twiter']).value,
      miembro: this.editForm.get(['miembro']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPerfilMiembro>>) {
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
