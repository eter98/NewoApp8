import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMiembrosEquipoEmpresas } from 'app/shared/model/miembros-equipo-empresas.model';

type EntityResponseType = HttpResponse<IMiembrosEquipoEmpresas>;
type EntityArrayResponseType = HttpResponse<IMiembrosEquipoEmpresas[]>;

@Injectable({ providedIn: 'root' })
export class MiembrosEquipoEmpresasService {
  public resourceUrl = SERVER_API_URL + 'api/miembros-equipo-empresas';

  constructor(protected http: HttpClient) {}

  create(miembrosEquipoEmpresas: IMiembrosEquipoEmpresas): Observable<EntityResponseType> {
    return this.http.post<IMiembrosEquipoEmpresas>(this.resourceUrl, miembrosEquipoEmpresas, { observe: 'response' });
  }

  update(miembrosEquipoEmpresas: IMiembrosEquipoEmpresas): Observable<EntityResponseType> {
    return this.http.put<IMiembrosEquipoEmpresas>(this.resourceUrl, miembrosEquipoEmpresas, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMiembrosEquipoEmpresas>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMiembrosEquipoEmpresas[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
