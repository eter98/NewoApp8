import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMargenNewoGrupos, MargenNewoGrupos } from 'app/shared/model/margen-newo-grupos.model';
import { MargenNewoGruposService } from './margen-newo-grupos.service';
import { IGrupos } from 'app/shared/model/grupos.model';
import { GruposService } from 'app/entities/grupos';

@Component({
  selector: 'jhi-margen-newo-grupos-update',
  templateUrl: './margen-newo-grupos-update.component.html'
})
export class MargenNewoGruposUpdateComponent implements OnInit {
  isSaving: boolean;

  grupos: IGrupos[];

  editForm = this.fb.group({
    id: [],
    porcentajeMargen: [],
    grupo: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected margenNewoGruposService: MargenNewoGruposService,
    protected gruposService: GruposService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ margenNewoGrupos }) => {
      this.updateForm(margenNewoGrupos);
    });
    this.gruposService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IGrupos[]>) => mayBeOk.ok),
        map((response: HttpResponse<IGrupos[]>) => response.body)
      )
      .subscribe((res: IGrupos[]) => (this.grupos = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(margenNewoGrupos: IMargenNewoGrupos) {
    this.editForm.patchValue({
      id: margenNewoGrupos.id,
      porcentajeMargen: margenNewoGrupos.porcentajeMargen,
      grupo: margenNewoGrupos.grupo
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const margenNewoGrupos = this.createFromForm();
    if (margenNewoGrupos.id !== undefined) {
      this.subscribeToSaveResponse(this.margenNewoGruposService.update(margenNewoGrupos));
    } else {
      this.subscribeToSaveResponse(this.margenNewoGruposService.create(margenNewoGrupos));
    }
  }

  private createFromForm(): IMargenNewoGrupos {
    return {
      ...new MargenNewoGrupos(),
      id: this.editForm.get(['id']).value,
      porcentajeMargen: this.editForm.get(['porcentajeMargen']).value,
      grupo: this.editForm.get(['grupo']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMargenNewoGrupos>>) {
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

  trackGruposById(index: number, item: IGrupos) {
    return item.id;
  }
}
