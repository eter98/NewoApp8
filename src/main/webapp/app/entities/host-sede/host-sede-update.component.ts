import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IHostSede, HostSede } from 'app/shared/model/host-sede.model';
import { HostSedeService } from './host-sede.service';
import { ISedes } from 'app/shared/model/sedes.model';
import { SedesService } from 'app/entities/sedes';
import { IMiembros } from 'app/shared/model/miembros.model';
import { MiembrosService } from 'app/entities/miembros';

@Component({
  selector: 'jhi-host-sede-update',
  templateUrl: './host-sede-update.component.html'
})
export class HostSedeUpdateComponent implements OnInit {
  isSaving: boolean;

  sedes: ISedes[];

  miembros: IMiembros[];

  editForm = this.fb.group({
    id: [],
    sede: [],
    miembro: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected hostSedeService: HostSedeService,
    protected sedesService: SedesService,
    protected miembrosService: MiembrosService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ hostSede }) => {
      this.updateForm(hostSede);
    });
    this.sedesService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ISedes[]>) => mayBeOk.ok),
        map((response: HttpResponse<ISedes[]>) => response.body)
      )
      .subscribe((res: ISedes[]) => (this.sedes = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.miembrosService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMiembros[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMiembros[]>) => response.body)
      )
      .subscribe((res: IMiembros[]) => (this.miembros = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(hostSede: IHostSede) {
    this.editForm.patchValue({
      id: hostSede.id,
      sede: hostSede.sede,
      miembro: hostSede.miembro
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const hostSede = this.createFromForm();
    if (hostSede.id !== undefined) {
      this.subscribeToSaveResponse(this.hostSedeService.update(hostSede));
    } else {
      this.subscribeToSaveResponse(this.hostSedeService.create(hostSede));
    }
  }

  private createFromForm(): IHostSede {
    return {
      ...new HostSede(),
      id: this.editForm.get(['id']).value,
      sede: this.editForm.get(['sede']).value,
      miembro: this.editForm.get(['miembro']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHostSede>>) {
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

  trackMiembrosById(index: number, item: IMiembros) {
    return item.id;
  }
}
