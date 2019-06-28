import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IRecursosFisicos, RecursosFisicos } from 'app/shared/model/recursos-fisicos.model';
import { RecursosFisicosService } from './recursos-fisicos.service';
import { ISedes } from 'app/shared/model/sedes.model';
import { SedesService } from 'app/entities/sedes';
import { ITipoRecurso } from 'app/shared/model/tipo-recurso.model';
import { TipoRecursoService } from 'app/entities/tipo-recurso';

@Component({
  selector: 'jhi-recursos-fisicos-update',
  templateUrl: './recursos-fisicos-update.component.html'
})
export class RecursosFisicosUpdateComponent implements OnInit {
  isSaving: boolean;

  sedes: ISedes[];

  tiporecursos: ITipoRecurso[];

  editForm = this.fb.group({
    id: [],
    recurso: [],
    tipo: [],
    unidadMedida: [],
    valorUnitario: [],
    valor1H: [],
    valor2H: [],
    valor3H: [],
    valor4H: [],
    valor5H: [],
    valor6H: [],
    valor7H: [],
    valor8H: [],
    valor9H: [],
    valor10H: [],
    valor11H: [],
    valor12H: [],
    impuesto: [],
    foto: [],
    fotoContentType: [],
    video: [],
    videoContentType: [],
    sede: [],
    tipoRecurso: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected recursosFisicosService: RecursosFisicosService,
    protected sedesService: SedesService,
    protected tipoRecursoService: TipoRecursoService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ recursosFisicos }) => {
      this.updateForm(recursosFisicos);
    });
    this.sedesService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ISedes[]>) => mayBeOk.ok),
        map((response: HttpResponse<ISedes[]>) => response.body)
      )
      .subscribe((res: ISedes[]) => (this.sedes = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.tipoRecursoService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ITipoRecurso[]>) => mayBeOk.ok),
        map((response: HttpResponse<ITipoRecurso[]>) => response.body)
      )
      .subscribe((res: ITipoRecurso[]) => (this.tiporecursos = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(recursosFisicos: IRecursosFisicos) {
    this.editForm.patchValue({
      id: recursosFisicos.id,
      recurso: recursosFisicos.recurso,
      tipo: recursosFisicos.tipo,
      unidadMedida: recursosFisicos.unidadMedida,
      valorUnitario: recursosFisicos.valorUnitario,
      valor1H: recursosFisicos.valor1H,
      valor2H: recursosFisicos.valor2H,
      valor3H: recursosFisicos.valor3H,
      valor4H: recursosFisicos.valor4H,
      valor5H: recursosFisicos.valor5H,
      valor6H: recursosFisicos.valor6H,
      valor7H: recursosFisicos.valor7H,
      valor8H: recursosFisicos.valor8H,
      valor9H: recursosFisicos.valor9H,
      valor10H: recursosFisicos.valor10H,
      valor11H: recursosFisicos.valor11H,
      valor12H: recursosFisicos.valor12H,
      impuesto: recursosFisicos.impuesto,
      foto: recursosFisicos.foto,
      fotoContentType: recursosFisicos.fotoContentType,
      video: recursosFisicos.video,
      videoContentType: recursosFisicos.videoContentType,
      sede: recursosFisicos.sede,
      tipoRecurso: recursosFisicos.tipoRecurso
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
    const recursosFisicos = this.createFromForm();
    if (recursosFisicos.id !== undefined) {
      this.subscribeToSaveResponse(this.recursosFisicosService.update(recursosFisicos));
    } else {
      this.subscribeToSaveResponse(this.recursosFisicosService.create(recursosFisicos));
    }
  }

  private createFromForm(): IRecursosFisicos {
    return {
      ...new RecursosFisicos(),
      id: this.editForm.get(['id']).value,
      recurso: this.editForm.get(['recurso']).value,
      tipo: this.editForm.get(['tipo']).value,
      unidadMedida: this.editForm.get(['unidadMedida']).value,
      valorUnitario: this.editForm.get(['valorUnitario']).value,
      valor1H: this.editForm.get(['valor1H']).value,
      valor2H: this.editForm.get(['valor2H']).value,
      valor3H: this.editForm.get(['valor3H']).value,
      valor4H: this.editForm.get(['valor4H']).value,
      valor5H: this.editForm.get(['valor5H']).value,
      valor6H: this.editForm.get(['valor6H']).value,
      valor7H: this.editForm.get(['valor7H']).value,
      valor8H: this.editForm.get(['valor8H']).value,
      valor9H: this.editForm.get(['valor9H']).value,
      valor10H: this.editForm.get(['valor10H']).value,
      valor11H: this.editForm.get(['valor11H']).value,
      valor12H: this.editForm.get(['valor12H']).value,
      impuesto: this.editForm.get(['impuesto']).value,
      fotoContentType: this.editForm.get(['fotoContentType']).value,
      foto: this.editForm.get(['foto']).value,
      videoContentType: this.editForm.get(['videoContentType']).value,
      video: this.editForm.get(['video']).value,
      sede: this.editForm.get(['sede']).value,
      tipoRecurso: this.editForm.get(['tipoRecurso']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRecursosFisicos>>) {
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

  trackTipoRecursoById(index: number, item: ITipoRecurso) {
    return item.id;
  }
}
