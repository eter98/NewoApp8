import { IMiembros } from 'app/shared/model/miembros.model';

export interface IEmpresa {
  id?: number;
  razonSocial?: string;
  nit?: string;
  direccion?: string;
  telefono?: string;
  correo?: string;
  celular?: string;
  aliado?: IMiembros;
}

export class Empresa implements IEmpresa {
  constructor(
    public id?: number,
    public razonSocial?: string,
    public nit?: string,
    public direccion?: string,
    public telefono?: string,
    public correo?: string,
    public celular?: string,
    public aliado?: IMiembros
  ) {}
}
