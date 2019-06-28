import { IGrupos } from 'app/shared/model/grupos.model';

export interface IMargenNewoGrupos {
  id?: number;
  porcentajeMargen?: number;
  grupo?: IGrupos;
}

export class MargenNewoGrupos implements IMargenNewoGrupos {
  constructor(public id?: number, public porcentajeMargen?: number, public grupo?: IGrupos) {}
}
