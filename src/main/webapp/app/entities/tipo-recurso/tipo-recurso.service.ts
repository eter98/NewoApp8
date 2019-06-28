import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITipoRecurso } from 'app/shared/model/tipo-recurso.model';

type EntityResponseType = HttpResponse<ITipoRecurso>;
type EntityArrayResponseType = HttpResponse<ITipoRecurso[]>;

@Injectable({ providedIn: 'root' })
export class TipoRecursoService {
  public resourceUrl = SERVER_API_URL + 'api/tipo-recursos';

  constructor(protected http: HttpClient) {}

  create(tipoRecurso: ITipoRecurso): Observable<EntityResponseType> {
    return this.http.post<ITipoRecurso>(this.resourceUrl, tipoRecurso, { observe: 'response' });
  }

  update(tipoRecurso: ITipoRecurso): Observable<EntityResponseType> {
    return this.http.put<ITipoRecurso>(this.resourceUrl, tipoRecurso, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITipoRecurso>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITipoRecurso[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
