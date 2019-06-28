import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';
import { ISedes } from 'app/shared/model/sedes.model';

export const enum TipoDocumentod {
  Cedula = 'Cedula',
  Cedula_Extranjeria = 'Cedula_Extranjeria',
  Pasaporte = 'Pasaporte',
  Otro = 'Otro'
}

export const enum Generod {
  Masculino = 'Masculino',
  Femenino = 'Femenino',
  Otro = 'Otro'
}

export interface IMiembros {
  id?: number;
  nombre?: string;
  apellido?: string;
  nacionalidad?: string;
  tipoDocumento?: TipoDocumentod;
  identificacion?: number;
  fechaNacimiento?: Moment;
  fechaRegistro?: Moment;
  genero?: Generod;
  correoElectronico?: string;
  celular?: string;
  derechosDeCompra?: boolean;
  accesoIlimitado?: boolean;
  aliado?: boolean;
  host?: boolean;
  miembro?: IUser;
  sede?: ISedes;
}

export class Miembros implements IMiembros {
  constructor(
    public id?: number,
    public nombre?: string,
    public apellido?: string,
    public nacionalidad?: string,
    public tipoDocumento?: TipoDocumentod,
    public identificacion?: number,
    public fechaNacimiento?: Moment,
    public fechaRegistro?: Moment,
    public genero?: Generod,
    public correoElectronico?: string,
    public celular?: string,
    public derechosDeCompra?: boolean,
    public accesoIlimitado?: boolean,
    public aliado?: boolean,
    public host?: boolean,
    public miembro?: IUser,
    public sede?: ISedes
  ) {
    this.derechosDeCompra = this.derechosDeCompra || false;
    this.accesoIlimitado = this.accesoIlimitado || false;
    this.aliado = this.aliado || false;
    this.host = this.host || false;
  }
}
