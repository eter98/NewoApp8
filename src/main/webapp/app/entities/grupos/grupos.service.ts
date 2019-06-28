import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGrupos } from 'app/shared/model/grupos.model';

type EntityResponseType = HttpResponse<IGrupos>;
type EntityArrayResponseType = HttpResponse<IGrupos[]>;

@Injectable({ providedIn: 'root' })
export class GruposService {
  public resourceUrl = SERVER_API_URL + 'api/grupos';

  constructor(protected http: HttpClient) {}

  create(grupos: IGrupos): Observable<EntityResponseType> {
    return this.http.post<IGrupos>(this.resourceUrl, grupos, { observe: 'response' });
  }

  update(grupos: IGrupos): Observable<EntityResponseType> {
    return this.http.put<IGrupos>(this.resourceUrl, grupos, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGrupos>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGrupos[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
