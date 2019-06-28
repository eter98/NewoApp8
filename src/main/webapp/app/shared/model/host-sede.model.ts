import { ISedes } from 'app/shared/model/sedes.model';
import { IMiembros } from 'app/shared/model/miembros.model';

export interface IHostSede {
  id?: number;
  sede?: ISedes;
  miembro?: IMiembros;
}

export class HostSede implements IHostSede {
  constructor(public id?: number, public sede?: ISedes, public miembro?: IMiembros) {}
}
