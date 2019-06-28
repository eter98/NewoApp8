import { IEmpresa } from 'app/shared/model/empresa.model';
import { IEquipoEmpresas } from 'app/shared/model/equipo-empresas.model';
import { IMiembros } from 'app/shared/model/miembros.model';

export interface IMiembrosEquipoEmpresas {
  id?: number;
  empresa?: IEmpresa;
  equipo?: IEquipoEmpresas;
  miembro?: IMiembros;
}

export class MiembrosEquipoEmpresas implements IMiembrosEquipoEmpresas {
  constructor(public id?: number, public empresa?: IEmpresa, public equipo?: IEquipoEmpresas, public miembro?: IMiembros) {}
}
