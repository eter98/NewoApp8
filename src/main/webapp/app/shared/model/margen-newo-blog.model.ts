import { IBlog } from 'app/shared/model/blog.model';

export interface IMargenNewoBlog {
  id?: number;
  porcentajeMargen?: number;
  blog?: IBlog;
}

export class MargenNewoBlog implements IMargenNewoBlog {
  constructor(public id?: number, public porcentajeMargen?: number, public blog?: IBlog) {}
}
