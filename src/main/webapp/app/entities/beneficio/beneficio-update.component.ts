import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IBeneficio, Beneficio } from 'app/shared/model/beneficio.model';
import { BeneficioService } from './beneficio.service';
import { IMiembros } from 'app/shared/model/miembros.model';
import { MiembrosService } from 'app/entities/miembros';

@Component({
  selector: 'jhi-beneficio-update',
  templateUrl: './beneficio-update.component.html'
})
export class BeneficioUpdateComponent implements OnInit {
  isSaving: boolean;

  miembros: IMiembros[];

  editForm = this.fb.group({
    id: [],
    tipoBeneficio: [],
    descuento: [],
    miembro: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected beneficioService: BeneficioService,
    protected miembrosService: MiembrosService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ beneficio }) => {
      this.updateForm(beneficio);
    });
    this.miembrosService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMiembros[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMiembros[]>) => response.body)
      )
      .subscribe((res: IMiembros[]) => (this.miembros = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(beneficio: IBeneficio) {
    this.editForm.patchValue({
      id: beneficio.id,
      tipoBeneficio: beneficio.tipoBeneficio,
      descuento: beneficio.descuento,
      miembro: beneficio.miembro
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const beneficio = this.createFromForm();
    if (beneficio.id !== undefined) {
      this.subscribeToSaveResponse(this.beneficioService.update(beneficio));
    } else {
      this.subscribeToSaveResponse(this.beneficioService.create(beneficio));
    }
  }

  private createFromForm(): IBeneficio {
    return {
      ...new Beneficio(),
      id: this.editForm.get(['id']).value,
      tipoBeneficio: this.editForm.get(['tipoBeneficio']).value,
      descuento: this.editForm.get(['descuento']).value,
      miembro: this.editForm.get(['miembro']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBeneficio>>) {
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
