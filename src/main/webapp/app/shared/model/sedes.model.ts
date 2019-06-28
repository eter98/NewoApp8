import { ICiudad } from 'app/shared/model/ciudad.model';

export interface ISedes {
  id?: number;
  nombreSede?: string;
  coordenadaX?: number;
  coordenadaY?: number;
  direccion?: string;
  telefonoComunidad?: string;
  telefonoNegocio?: string;
  descripcionSede?: any;
  horario?: string;
  videoContentType?: string;
  video?: any;
  imagen1ContentType?: string;
  imagen1?: any;
  imagen2ContentType?: string;
  imagen2?: any;
  imagen3ContentType?: string;
  imagen3?: any;
  imagen4ContentType?: string;
  imagen4?: any;
  imagen5ContentType?: string;
  imagen5?: any;
  imagen6ContentType?: string;
  imagen6?: any;
  ciudad?: ICiudad;
}

export class Sedes implements ISedes {
  constructor(
    public id?: number,
    public nombreSede?: string,
    public coordenadaX?: number,
    public coordenadaY?: number,
    public direccion?: string,
    public telefonoComunidad?: string,
    public telefonoNegocio?: string,
    public descripcionSede?: any,
    public horario?: string,
    public videoContentType?: string,
    public video?: any,
    public imagen1ContentType?: string,
    public imagen1?: any,
    public imagen2ContentType?: string,
    public imagen2?: any,
    public imagen3ContentType?: string,
    public imagen3?: any,
    public imagen4ContentType?: string,
    public imagen4?: any,
    public imagen5ContentType?: string,
    public imagen5?: any,
    public imagen6ContentType?: string,
    public imagen6?: any,
    public ciudad?: ICiudad
  ) {}
}
