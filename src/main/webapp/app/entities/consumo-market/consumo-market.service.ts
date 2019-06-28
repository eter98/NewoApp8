import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IConsumoMarket } from 'app/shared/model/consumo-market.model';

type EntityResponseType = HttpResponse<IConsumoMarket>;
type EntityArrayResponseType = HttpResponse<IConsumoMarket[]>;

@Injectable({ providedIn: 'root' })
export class ConsumoMarketService {
  public resourceUrl = SERVER_API_URL + 'api/consumo-markets';

  constructor(protected http: HttpClient) {}

  create(consumoMarket: IConsumoMarket): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(consumoMarket);
    return this.http
      .post<IConsumoMarket>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(consumoMarket: IConsumoMarket): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(consumoMarket);
    return this.http
      .put<IConsumoMarket>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IConsumoMarket>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IConsumoMarket[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(consumoMarket: IConsumoMarket): IConsumoMarket {
    const copy: IConsumoMarket = Object.assign({}, consumoMarket, {
      registroFechaInicial:
        consumoMarket.registroFechaInicial != null && consumoMarket.registroFechaInicial.isValid()
          ? consumoMarket.registroFechaInicial.format(DATE_FORMAT)
          : null,
      registroFechaFinal:
        consumoMarket.registroFechaFinal != null && consumoMarket.registroFechaFinal.isValid()
          ? consumoMarket.registroFechaFinal.format(DATE_FORMAT)
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.registroFechaInicial = res.body.registroFechaInicial != null ? moment(res.body.registroFechaInicial) : null;
      res.body.registroFechaFinal = res.body.registroFechaFinal != null ? moment(res.body.registroFechaFinal) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((consumoMarket: IConsumoMarket) => {
        consumoMarket.registroFechaInicial = consumoMarket.registroFechaInicial != null ? moment(consumoMarket.registroFechaInicial) : null;
        consumoMarket.registroFechaFinal = consumoMarket.registroFechaFinal != null ? moment(consumoMarket.registroFechaFinal) : null;
      });
    }
    return res;
  }
}
