import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IUsoRecursoFisico } from 'app/shared/model/uso-recurso-fisico.model';

type EntityResponseType = HttpResponse<IUsoRecursoFisico>;
type EntityArrayResponseType = HttpResponse<IUsoRecursoFisico[]>;

@Injectable({ providedIn: 'root' })
export class UsoRecursoFisicoService {
  public resourceUrl = SERVER_API_URL + 'api/uso-recurso-fisicos';

  constructor(protected http: HttpClient) {}

  create(usoRecursoFisico: IUsoRecursoFisico): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(usoRecursoFisico);
    return this.http
      .post<IUsoRecursoFisico>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(usoRecursoFisico: IUsoRecursoFisico): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(usoRecursoFisico);
    return this.http
      .put<IUsoRecursoFisico>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IUsoRecursoFisico>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IUsoRecursoFisico[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(usoRecursoFisico: IUsoRecursoFisico): IUsoRecursoFisico {
    const copy: IUsoRecursoFisico = Object.assign({}, usoRecursoFisico, {
      registroFechaInicio:
        usoRecursoFisico.registroFechaInicio != null && usoRecursoFisico.registroFechaInicio.isValid()
          ? usoRecursoFisico.registroFechaInicio.toJSON()
          : null,
      registroFechaFinal:
        usoRecursoFisico.registroFechaFinal != null && usoRecursoFisico.registroFechaFinal.isValid()
          ? usoRecursoFisico.registroFechaFinal.toJSON()
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.registroFechaInicio = res.body.registroFechaInicio != null ? moment(res.body.registroFechaInicio) : null;
      res.body.registroFechaFinal = res.body.registroFechaFinal != null ? moment(res.body.registroFechaFinal) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((usoRecursoFisico: IUsoRecursoFisico) => {
        usoRecursoFisico.registroFechaInicio =
          usoRecursoFisico.registroFechaInicio != null ? moment(usoRecursoFisico.registroFechaInicio) : null;
        usoRecursoFisico.registroFechaFinal =
          usoRecursoFisico.registroFechaFinal != null ? moment(usoRecursoFisico.registroFechaFinal) : null;
      });
    }
    return res;
  }
}
