import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IBlog, Blog } from 'app/shared/model/blog.model';
import { BlogService } from './blog.service';
import { ICategoriaContenidos } from 'app/shared/model/categoria-contenidos.model';
import { CategoriaContenidosService } from 'app/entities/categoria-contenidos';
import { IGrupos } from 'app/shared/model/grupos.model';
import { GruposService } from 'app/entities/grupos';
import { IMiembros } from 'app/shared/model/miembros.model';
import { MiembrosService } from 'app/entities/miembros';

@Component({
  selector: 'jhi-blog-update',
  templateUrl: './blog-update.component.html'
})
export class BlogUpdateComponent implements OnInit {
  isSaving: boolean;

  categoriacontenidos: ICategoriaContenidos[];

  grupos: IGrupos[];

  miembros: IMiembros[];

  editForm = this.fb.group({
    id: [],
    titulo: [],
    descripcion: [],
    tipoContenido: [],
    contenidos: [],
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
    tipoConsumo: [],
    valor: [],
    impuesto: [],
    estadoPublicacion: [],
    categoriaBlog: [],
    grupos: [],
    miembro: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected blogService: BlogService,
    protected categoriaContenidosService: CategoriaContenidosService,
    protected gruposService: GruposService,
    protected miembrosService: MiembrosService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ blog }) => {
      this.updateForm(blog);
    });
    this.categoriaContenidosService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICategoriaContenidos[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICategoriaContenidos[]>) => response.body)
      )
      .subscribe((res: ICategoriaContenidos[]) => (this.categoriacontenidos = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.gruposService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IGrupos[]>) => mayBeOk.ok),
        map((response: HttpResponse<IGrupos[]>) => response.body)
      )
      .subscribe((res: IGrupos[]) => (this.grupos = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.miembrosService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMiembros[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMiembros[]>) => response.body)
      )
      .subscribe((res: IMiembros[]) => (this.miembros = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(blog: IBlog) {
    this.editForm.patchValue({
      id: blog.id,
      titulo: blog.titulo,
      descripcion: blog.descripcion,
      tipoContenido: blog.tipoContenido,
      contenidos: blog.contenidos,
      audio: blog.audio,
      audioContentType: blog.audioContentType,
      video: blog.video,
      videoContentType: blog.videoContentType,
      foto1: blog.foto1,
      foto1ContentType: blog.foto1ContentType,
      foto2: blog.foto2,
      foto2ContentType: blog.foto2ContentType,
      banner: blog.banner,
      bannerContentType: blog.bannerContentType,
      tipoConsumo: blog.tipoConsumo,
      valor: blog.valor,
      impuesto: blog.impuesto,
      estadoPublicacion: blog.estadoPublicacion,
      categoriaBlog: blog.categoriaBlog,
      grupos: blog.grupos,
      miembro: blog.miembro
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
    const blog = this.createFromForm();
    if (blog.id !== undefined) {
      this.subscribeToSaveResponse(this.blogService.update(blog));
    } else {
      this.subscribeToSaveResponse(this.blogService.create(blog));
    }
  }

  private createFromForm(): IBlog {
    return {
      ...new Blog(),
      id: this.editForm.get(['id']).value,
      titulo: this.editForm.get(['titulo']).value,
      descripcion: this.editForm.get(['descripcion']).value,
      tipoContenido: this.editForm.get(['tipoContenido']).value,
      contenidos: this.editForm.get(['contenidos']).value,
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
      tipoConsumo: this.editForm.get(['tipoConsumo']).value,
      valor: this.editForm.get(['valor']).value,
      impuesto: this.editForm.get(['impuesto']).value,
      estadoPublicacion: this.editForm.get(['estadoPublicacion']).value,
      categoriaBlog: this.editForm.get(['categoriaBlog']).value,
      grupos: this.editForm.get(['grupos']).value,
      miembro: this.editForm.get(['miembro']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBlog>>) {
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

  trackGruposById(index: number, item: IGrupos) {
    return item.id;
  }

  trackMiembrosById(index: number, item: IMiembros) {
    return item.id;
  }
}
