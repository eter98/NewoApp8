import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMargenNewoProductos } from 'app/shared/model/margen-newo-productos.model';

type EntityResponseType = HttpResponse<IMargenNewoProductos>;
type EntityArrayResponseType = HttpResponse<IMargenNewoProductos[]>;

@Injectable({ providedIn: 'root' })
export class MargenNewoProductosService {
  public resourceUrl = SERVER_API_URL + 'api/margen-newo-productos';

  constructor(protected http: HttpClient) {}

  create(margenNewoProductos: IMargenNewoProductos): Observable<EntityResponseType> {
    return this.http.post<IMargenNewoProductos>(this.resourceUrl, margenNewoProductos, { observe: 'response' });
  }

  update(margenNewoProductos: IMargenNewoProductos): Observable<EntityResponseType> {
    return this.http.put<IMargenNewoProductos>(this.resourceUrl, margenNewoProductos, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMargenNewoProductos>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMargenNewoProductos[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
