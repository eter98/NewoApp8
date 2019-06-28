import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMargenNewoGrupos } from 'app/shared/model/margen-newo-grupos.model';

type EntityResponseType = HttpResponse<IMargenNewoGrupos>;
type EntityArrayResponseType = HttpResponse<IMargenNewoGrupos[]>;

@Injectable({ providedIn: 'root' })
export class MargenNewoGruposService {
  public resourceUrl = SERVER_API_URL + 'api/margen-newo-grupos';

  constructor(protected http: HttpClient) {}

  create(margenNewoGrupos: IMargenNewoGrupos): Observable<EntityResponseType> {
    return this.http.post<IMargenNewoGrupos>(this.resourceUrl, margenNewoGrupos, { observe: 'response' });
  }

  update(margenNewoGrupos: IMargenNewoGrupos): Observable<EntityResponseType> {
    return this.http.put<IMargenNewoGrupos>(this.resourceUrl, margenNewoGrupos, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMargenNewoGrupos>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMargenNewoGrupos[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
