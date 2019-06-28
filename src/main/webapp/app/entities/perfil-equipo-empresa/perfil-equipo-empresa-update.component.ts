import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IPerfilEquipoEmpresa, PerfilEquipoEmpresa } from 'app/shared/model/perfil-equipo-empresa.model';
import { PerfilEquipoEmpresaService } from './perfil-equipo-empresa.service';
import { IEmpresa } from 'app/shared/model/empresa.model';
import { EmpresaService } from 'app/entities/empresa';

@Component({
  selector: 'jhi-perfil-equipo-empresa-update',
  templateUrl: './perfil-equipo-empresa-update.component.html'
})
export class PerfilEquipoEmpresaUpdateComponent implements OnInit {
  isSaving: boolean;

  empresas: IEmpresa[];

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
    empresa: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected perfilEquipoEmpresaService: PerfilEquipoEmpresaService,
    protected empresaService: EmpresaService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ perfilEquipoEmpresa }) => {
      this.updateForm(perfilEquipoEmpresa);
    });
    this.empresaService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IEmpresa[]>) => mayBeOk.ok),
        map((response: HttpResponse<IEmpresa[]>) => response.body)
      )
      .subscribe((res: IEmpresa[]) => (this.empresas = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(perfilEquipoEmpresa: IPerfilEquipoEmpresa) {
    this.editForm.patchValue({
      id: perfilEquipoEmpresa.id,
      biografia: perfilEquipoEmpresa.biografia,
      foto1: perfilEquipoEmpresa.foto1,
      foto1ContentType: perfilEquipoEmpresa.foto1ContentType,
      foto2: perfilEquipoEmpresa.foto2,
      foto2ContentType: perfilEquipoEmpresa.foto2ContentType,
      fot3: perfilEquipoEmpresa.fot3,
      fot3ContentType: perfilEquipoEmpresa.fot3ContentType,
      conocimientosQueDomina: perfilEquipoEmpresa.conocimientosQueDomina,
      temasDeInteres: perfilEquipoEmpresa.temasDeInteres,
      facebook: perfilEquipoEmpresa.facebook,
      instagram: perfilEquipoEmpresa.instagram,
      idGoogle: perfilEquipoEmpresa.idGoogle,
      twiter: perfilEquipoEmpresa.twiter,
      empresa: perfilEquipoEmpresa.empresa
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
    const perfilEquipoEmpresa = this.createFromForm();
    if (perfilEquipoEmpresa.id !== undefined) {
      this.subscribeToSaveResponse(this.perfilEquipoEmpresaService.update(perfilEquipoEmpresa));
    } else {
      this.subscribeToSaveResponse(this.perfilEquipoEmpresaService.create(perfilEquipoEmpresa));
    }
  }

  private createFromForm(): IPerfilEquipoEmpresa {
    return {
      ...new PerfilEquipoEmpresa(),
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
      empresa: this.editForm.get(['empresa']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPerfilEquipoEmpresa>>) {
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
}
