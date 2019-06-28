import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMiembrosGrupo } from 'app/shared/model/miembros-grupo.model';

type EntityResponseType = HttpResponse<IMiembrosGrupo>;
type EntityArrayResponseType = HttpResponse<IMiembrosGrupo[]>;

@Injectable({ providedIn: 'root' })
export class MiembrosGrupoService {
  public resourceUrl = SERVER_API_URL + 'api/miembros-grupos';

  constructor(protected http: HttpClient) {}

  create(miembrosGrupo: IMiembrosGrupo): Observable<EntityResponseType> {
    return this.http.post<IMiembrosGrupo>(this.resourceUrl, miembrosGrupo, { observe: 'response' });
  }

  update(miembrosGrupo: IMiembrosGrupo): Observable<EntityResponseType> {
    return this.http.put<IMiembrosGrupo>(this.resourceUrl, miembrosGrupo, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMiembrosGrupo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMiembrosGrupo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
