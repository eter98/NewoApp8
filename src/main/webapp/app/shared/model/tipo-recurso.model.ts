export interface ITipoRecurso {
  id?: number;
  nombre?: string;
  descripcion?: string;
}

export class TipoRecurso implements ITipoRecurso {
  constructor(public id?: number, public nombre?: string, public descripcion?: string) {}
}
