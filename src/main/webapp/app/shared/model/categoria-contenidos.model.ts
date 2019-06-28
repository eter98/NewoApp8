export interface ICategoriaContenidos {
  id?: number;
  categoria?: string;
}

export class CategoriaContenidos implements ICategoriaContenidos {
  constructor(public id?: number, public categoria?: string) {}
}
