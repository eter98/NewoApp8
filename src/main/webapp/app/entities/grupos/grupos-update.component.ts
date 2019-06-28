import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IGrupos, Grupos } from 'app/shared/model/grupos.model';
import { GruposService } from './grupos.service';
import { ICategoriaContenidos } from 'app/shared/model/categoria-contenidos.model';
import { CategoriaContenidosService } from 'app/entities/categoria-contenidos';
import { IEvento } from 'app/shared/model/evento.model';
import { EventoService } from 'app/entities/evento';

@Component({
  selector: 'jhi-grupos-update',
  templateUrl: './grupos-update.component.html'
})
export class GruposUpdateComponent implements OnInit {
  isSaving: boolean;

  categoriacontenidos: ICategoriaContenidos[];

  eventos: IEvento[];

  editForm = this.fb.group({
    id: [],
    nombreGrupo: [],
    instagram: [],
    facebook: [],
    twiter: [],
    linkedIn: [],
    tipoGrupo: [],
    tipoConsumo: [],
    valorSuscripcion: [],
    impuesto: [],
    reglasGrupo: [],
    audio: [],
    audioContentType: [],
    video: [],
    videoContentType: [],
    foto1: [],
    foto1ContentType: [],
    foto2: [],
    foto2ContentType: [],
    banner: [],
    bannerContentType: [],
    categoriaGrupo: [],
    evento: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected gruposService: GruposService,
    protected categoriaContenidosService: CategoriaContenidosService,
    protected eventoService: EventoService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ grupos }) => {
      this.updateForm(grupos);
    });
    this.categoriaContenidosService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICategoriaContenidos[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICategoriaContenidos[]>) => response.body)
      )
      .subscribe((res: ICategoriaContenidos[]) => (this.categoriacontenidos = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.eventoService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IEvento[]>) => mayBeOk.ok),
        map((response: HttpResponse<IEvento[]>) => response.body)
      )
      .subscribe((res: IEvento[]) => (this.eventos = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(grupos: IGrupos) {
    this.editForm.patchValue({
      id: grupos.id,
      nombreGrupo: grupos.nombreGrupo,
      instagram: grupos.instagram,
      facebook: grupos.facebook,
      twiter: grupos.twiter,
      linkedIn: grupos.linkedIn,
      tipoGrupo: grupos.tipoGrupo,
      tipoConsumo: grupos.tipoConsumo,
      valorSuscripcion: grupos.valorSuscripcion,
      impuesto: grupos.impuesto,
      reglasGrupo: grupos.reglasGrupo,
      audio: grupos.audio,
      audioContentType: grupos.audioContentType,
      video: grupos.video,
      videoContentType: grupos.videoContentType,
      foto1: grupos.foto1,
      foto1ContentType: grupos.foto1ContentType,
      foto2: grupos.foto2,
      foto2ContentType: grupos.foto2ContentType,
      banner: grupos.banner,
      bannerContentType: grupos.bannerContentType,
      categoriaGrupo: grupos.categoriaGrupo,
      evento: grupos.evento
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
    const grupos = this.createFromForm();
    if (grupos.id !== undefined) {
      this.subscribeToSaveResponse(this.gruposService.update(grupos));
    } else {
      this.subscribeToSaveResponse(this.gruposService.create(grupos));
    }
  }

  private createFromForm(): IGrupos {
    return {
      ...new Grupos(),
      id: this.editForm.get(['id']).value,
      nombreGrupo: this.editForm.get(['nombreGrupo']).value,
      instagram: this.editForm.get(['instagram']).value,
      facebook: this.editForm.get(['facebook']).value,
      twiter: this.editForm.get(['twiter']).value,
      linkedIn: this.editForm.get(['linkedIn']).value,
      tipoGrupo: this.editForm.get(['tipoGrupo']).value,
      tipoConsumo: this.editForm.get(['tipoConsumo']).value,
      valorSuscripcion: this.editForm.get(['valorSuscripcion']).value,
      impuesto: this.editForm.get(['impuesto']).value,
      reglasGrupo: this.editForm.get(['reglasGrupo']).value,
      audioContentType: this.editForm.get(['audioContentType']).value,
      audio: this.editForm.get(['audio']).value,
      videoContentType: this.editForm.get(['videoContentType']).value,
      video: this.editForm.get(['video']).value,
      foto1ContentType: this.editForm.get(['foto1ContentType']).value,
      foto1: this.editForm.get(['foto1']).value,
      foto2ContentType: this.editForm.get(['foto2ContentType']).value,
      foto2: this.editForm.get(['foto2']).value,
      bannerContentType: this.editForm.get(['bannerContentType']).value,
      banner: this.editForm.get(['banner']).value,
      categoriaGrupo: this.editForm.get(['categoriaGrupo']).value,
      evento: this.editForm.get(['evento']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGrupos>>) {
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

  trackCategoriaContenidosById(index: number, item: ICategoriaContenidos) {
    return item.id;
  }

  trackEventoById(index: number, item: IEvento) {
    return item.id;
  }
}
