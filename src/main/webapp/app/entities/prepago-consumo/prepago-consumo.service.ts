import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPrepagoConsumo } from 'app/shared/model/prepago-consumo.model';

type EntityResponseType = HttpResponse<IPrepagoConsumo>;
type EntityArrayResponseType = HttpResponse<IPrepagoConsumo[]>;

@Injectable({ providedIn: 'root' })
export class PrepagoConsumoService {
  public resourceUrl = SERVER_API_URL + 'api/prepago-consumos';

  constructor(protected http: HttpClient) {}

  create(prepagoConsumo: IPrepagoConsumo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(prepagoConsumo);
    return this.http
      .post<IPrepagoConsumo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(prepagoConsumo: IPrepagoConsumo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(prepagoConsumo);
    return this.http
      .put<IPrepagoConsumo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPrepagoConsumo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPrepagoConsumo[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(prepagoConsumo: IPrepagoConsumo): IPrepagoConsumo {
    const copy: IPrepagoConsumo = Object.assign({}, prepagoConsumo, {
      fechaRegistro:
        prepagoConsumo.fechaRegistro != null && prepagoConsumo.fechaRegistro.isValid()
          ? prepagoConsumo.fechaRegistro.format(DATE_FORMAT)
          : null,
      fechaSaldoActual:
        prepagoConsumo.fechaSaldoActual != null && prepagoConsumo.fechaSaldoActual.isValid()
          ? prepagoConsumo.fechaSaldoActual.format(DATE_FORMAT)
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fechaRegistro = res.body.fechaRegistro != null ? moment(res.body.fechaRegistro) : null;
      res.body.fechaSaldoActual = res.body.fechaSaldoActual != null ? moment(res.body.fechaSaldoActual) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((prepagoConsumo: IPrepagoConsumo) => {
        prepagoConsumo.fechaRegistro = prepagoConsumo.fechaRegistro != null ? moment(prepagoConsumo.fechaRegistro) : null;
        prepagoConsumo.fechaSaldoActual = prepagoConsumo.fechaSaldoActual != null ? moment(prepagoConsumo.fechaSaldoActual) : null;
      });
    }
    return res;
  }
}
