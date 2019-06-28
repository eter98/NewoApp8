import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ICiudad, Ciudad } from 'app/shared/model/ciudad.model';
import { CiudadService } from './ciudad.service';
import { IPais } from 'app/shared/model/pais.model';
import { PaisService } from 'app/entities/pais';

@Component({
  selector: 'jhi-ciudad-update',
  templateUrl: './ciudad-update.component.html'
})
export class CiudadUpdateComponent implements OnInit {
  isSaving: boolean;

  pais: IPais[];

  editForm = this.fb.group({
    id: [],
    nombreCiudad: [],
    pais: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected ciudadService: CiudadService,
    protected paisService: PaisService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ ciudad }) => {
      this.updateForm(ciudad);
    });
    this.paisService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPais[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPais[]>) => response.body)
      )
      .subscribe((res: IPais[]) => (this.pais = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(ciudad: ICiudad) {
    this.editForm.patchValue({
      id: ciudad.id,
      nombreCiudad: ciudad.nombreCiudad,
      pais: ciudad.pais
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const ciudad = this.createFromForm();
    if (ciudad.id !== undefined) {
      this.subscribeToSaveResponse(this.ciudadService.update(ciudad));
    } else {
      this.subscribeToSaveResponse(this.ciudadService.create(ciudad));
    }
  }

  private createFromForm(): ICiudad {
    return {
      ...new Ciudad(),
      id: this.editForm.get(['id']).value,
      nombreCiudad: this.editForm.get(['nombreCiudad']).value,
      pais: this.editForm.get(['pais']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICiudad>>) {
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

  trackPaisById(index: number, item: IPais) {
    return item.id;
  }
}
