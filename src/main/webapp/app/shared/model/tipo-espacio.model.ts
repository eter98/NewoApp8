export interface ITipoEspacio {
  id?: number;
  tipoEspacio?: string;
}

export class TipoEspacio implements ITipoEspacio {
  constructor(public id?: number, public tipoEspacio?: string) {}
}
