import { IUser } from 'app/core/user/user.model';
import { IMiembros } from 'app/shared/model/miembros.model';
import { IEmpresa } from 'app/shared/model/empresa.model';

export interface IEquipoEmpresas {
  id?: number;
  nombre?: string;
  telefono?: string;
  correo?: string;
  direccion?: string;
  descripcion?: any;
  logosContentType?: string;
  logos?: any;
  paginaWeb?: string;
  usuario?: IUser;
  miembro?: IMiembros;
  empresa?: IEmpresa;
}

export class EquipoEmpresas implements IEquipoEmpresas {
  constructor(
    public id?: number,
    public nombre?: string,
    public telefono?: string,
    public correo?: string,
    public direccion?: string,
    public descripcion?: any,
    public logosContentType?: string,
    public logos?: any,
    public paginaWeb?: string,
    public usuario?: IUser,
    public miembro?: IMiembros,
    public empresa?: IEmpresa
  ) {}
}
