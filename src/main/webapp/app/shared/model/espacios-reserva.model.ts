import { ISedes } from 'app/shared/model/sedes.model';

export const enum Impuestod {
  IVA19 = 'IVA19',
  IVA6 = 'IVA6',
  IVA0 = 'IVA0',
  ICO = 'ICO',
  IPOCONSUMO8 = 'IPOCONSUMO8'
}

export interface IEspaciosReserva {
  id?: number;
  nombre?: string;
  descripcion?: string;
  facilidades?: string;
  capacidad?: number;
  apertura?: string;
  cierre?: string;
  tarifa1Hora?: number;
  tarifa2Hora?: number;
  tarifa3Hora?: number;
  tarifa4Hora?: number;
  tarifa5Hora?: number;
  tarifa6Hora?: number;
  tarifa7Hora?: number;
  tarifa8Hora?: number;
  impuesto?: Impuestod;
  wifi?: string;
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
  sede?: ISedes;
}

export class EspaciosReserva implements IEspaciosReserva {
  constructor(
    public id?: number,
    public nombre?: string,
    public descripcion?: string,
    public facilidades?: string,
    public capacidad?: number,
    public apertura?: string,
    public cierre?: string,
    public tarifa1Hora?: number,
    public tarifa2Hora?: number,
    public tarifa3Hora?: number,
    public tarifa4Hora?: number,
    public tarifa5Hora?: number,
    public tarifa6Hora?: number,
    public tarifa7Hora?: number,
    public tarifa8Hora?: number,
    public impuesto?: Impuestod,
    public wifi?: string,
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
    public sede?: ISedes
  ) {}
}
