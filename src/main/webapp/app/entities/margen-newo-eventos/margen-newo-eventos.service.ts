import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMargenNewoEventos } from 'app/shared/model/margen-newo-eventos.model';

type EntityResponseType = HttpResponse<IMargenNewoEventos>;
type EntityArrayResponseType = HttpResponse<IMargenNewoEventos[]>;

@Injectable({ providedIn: 'root' })
export class MargenNewoEventosService {
  public resourceUrl = SERVER_API_URL + 'api/margen-newo-eventos';

  constructor(protected http: HttpClient) {}

  create(margenNewoEventos: IMargenNewoEventos): Observable<EntityResponseType> {
    return this.http.post<IMargenNewoEventos>(this.resourceUrl, margenNewoEventos, { observe: 'response' });
  }

  update(margenNewoEventos: IMargenNewoEventos): Observable<EntityResponseType> {
    return this.http.put<IMargenNewoEventos>(this.resourceUrl, margenNewoEventos, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMargenNewoEventos>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMargenNewoEventos[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
