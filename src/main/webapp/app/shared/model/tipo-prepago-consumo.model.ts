import { IBeneficio } from 'app/shared/model/beneficio.model';

export interface ITipoPrepagoConsumo {
  id?: number;
  nombre?: string;
  descripcion?: string;
  valorMinimo?: number;
  valorMaximo?: number;
  tipoBeneficio?: IBeneficio;
}

export class TipoPrepagoConsumo implements ITipoPrepagoConsumo {
  constructor(
    public id?: number,
    public nombre?: string,
    public descripcion?: string,
    public valorMinimo?: number,
    public valorMaximo?: number,
    public tipoBeneficio?: IBeneficio
  ) {}
}
