import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMargenNewoBlog } from 'app/shared/model/margen-newo-blog.model';

type EntityResponseType = HttpResponse<IMargenNewoBlog>;
type EntityArrayResponseType = HttpResponse<IMargenNewoBlog[]>;

@Injectable({ providedIn: 'root' })
export class MargenNewoBlogService {
  public resourceUrl = SERVER_API_URL + 'api/margen-newo-blogs';

  constructor(protected http: HttpClient) {}

  create(margenNewoBlog: IMargenNewoBlog): Observable<EntityResponseType> {
    return this.http.post<IMargenNewoBlog>(this.resourceUrl, margenNewoBlog, { observe: 'response' });
  }

  update(margenNewoBlog: IMargenNewoBlog): Observable<EntityResponseType> {
    return this.http.put<IMargenNewoBlog>(this.resourceUrl, margenNewoBlog, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMargenNewoBlog>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMargenNewoBlog[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
