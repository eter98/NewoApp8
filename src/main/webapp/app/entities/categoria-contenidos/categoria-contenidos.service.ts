import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICategoriaContenidos } from 'app/shared/model/categoria-contenidos.model';

type EntityResponseType = HttpResponse<ICategoriaContenidos>;
type EntityArrayResponseType = HttpResponse<ICategoriaContenidos[]>;

@Injectable({ providedIn: 'root' })
export class CategoriaContenidosService {
  public resourceUrl = SERVER_API_URL + 'api/categoria-contenidos';

  constructor(protected http: HttpClient) {}

  create(categoriaContenidos: ICategoriaContenidos): Observable<EntityResponseType> {
    return this.http.post<ICategoriaContenidos>(this.resourceUrl, categoriaContenidos, { observe: 'response' });
  }

  update(categoriaContenidos: ICategoriaContenidos): Observable<EntityResponseType> {
    return this.http.put<ICategoriaContenidos>(this.resourceUrl, categoriaContenidos, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICategoriaContenidos>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICategoriaContenidos[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
