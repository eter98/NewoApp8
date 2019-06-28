import { IMiembros } from 'app/shared/model/miembros.model';

export interface IMiembrosGrupo {
  id?: number;
  miembro?: IMiembros;
}

export class MiembrosGrupo implements IMiembrosGrupo {
  constructor(public id?: number, public miembro?: IMiembros) {}
}
