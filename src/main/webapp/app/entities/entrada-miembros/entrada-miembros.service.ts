import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEntradaMiembros } from 'app/shared/model/entrada-miembros.model';

type EntityResponseType = HttpResponse<IEntradaMiembros>;
type EntityArrayResponseType = HttpResponse<IEntradaMiembros[]>;

@Injectable({ providedIn: 'root' })
export class EntradaMiembrosService {
  public resourceUrl = SERVER_API_URL + 'api/entrada-miembros';

  constructor(protected http: HttpClient) {}

  create(entradaMiembros: IEntradaMiembros): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(entradaMiembros);
    return this.http
      .post<IEntradaMiembros>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(entradaMiembros: IEntradaMiembros): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(entradaMiembros);
    return this.http
      .put<IEntradaMiembros>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEntradaMiembros>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEntradaMiembros[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(entradaMiembros: IEntradaMiembros): IEntradaMiembros {
    const copy: IEntradaMiembros = Object.assign({}, entradaMiembros, {
      registroFecha:
        entradaMiembros.registroFecha != null && entradaMiembros.registroFecha.isValid() ? entradaMiembros.registroFecha.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.registroFecha = res.body.registroFecha != null ? moment(res.body.registroFecha) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((entradaMiembros: IEntradaMiembros) => {
        entradaMiembros.registroFecha = entradaMiembros.registroFecha != null ? moment(entradaMiembros.registroFecha) : null;
      });
    }
    return res;
  }
}
