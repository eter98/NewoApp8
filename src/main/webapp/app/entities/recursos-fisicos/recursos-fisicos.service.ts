import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRecursosFisicos } from 'app/shared/model/recursos-fisicos.model';

type EntityResponseType = HttpResponse<IRecursosFisicos>;
type EntityArrayResponseType = HttpResponse<IRecursosFisicos[]>;

@Injectable({ providedIn: 'root' })
export class RecursosFisicosService {
  public resourceUrl = SERVER_API_URL + 'api/recursos-fisicos';

  constructor(protected http: HttpClient) {}

  create(recursosFisicos: IRecursosFisicos): Observable<EntityResponseType> {
    return this.http.post<IRecursosFisicos>(this.resourceUrl, recursosFisicos, { observe: 'response' });
  }

  update(recursosFisicos: IRecursosFisicos): Observable<EntityResponseType> {
    return this.http.put<IRecursosFisicos>(this.resourceUrl, recursosFisicos, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRecursosFisicos>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRecursosFisicos[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
