import { ISedes } from 'app/shared/model/sedes.model';
import { ITipoEspacio } from 'app/shared/model/tipo-espacio.model';

export const enum Impuestod {
  IVA19 = 'IVA19',
  IVA6 = 'IVA6',
  IVA0 = 'IVA0',
  ICO = 'ICO',
  IPOCONSUMO8 = 'IPOCONSUMO8'
}

export interface IEspacioLibre {
  id?: number;
  nombre?: string;
  capacidadInstalada?: number;
  wifi?: string;
  tarifa1hMiembro?: number;
  tarifa2hMiembro?: number;
  tarifa3hMiembro?: number;
  tarifa4hMiembro?: number;
  tarifa5hMiembro?: number;
  tarifa6hMiembro?: number;
  tarifa7hMiembro?: number;
  tarifa8hMiembro?: number;
  tarifa1hInvitado?: number;
  tarifa2hInvitado?: number;
  tarifa3hInvitado?: number;
  tarifa4hInvitado?: number;
  tarifa5hInvitado?: number;
  tarifa6hInvitado?: number;
  tarifa7hInvitado?: number;
  tarifa8hInvitado?: number;
  impuesto?: Impuestod;
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
  tipoEspacio?: ITipoEspacio;
}

export class EspacioLibre implements IEspacioLibre {
  constructor(
    public id?: number,
    public nombre?: string,
    public capacidadInstalada?: number,
    public wifi?: string,
    public tarifa1hMiembro?: number,
    public tarifa2hMiembro?: number,
    public tarifa3hMiembro?: number,
    public tarifa4hMiembro?: number,
    public tarifa5hMiembro?: number,
    public tarifa6hMiembro?: number,
    public tarifa7hMiembro?: number,
    public tarifa8hMiembro?: number,
    public tarifa1hInvitado?: number,
    public tarifa2hInvitado?: number,
    public tarifa3hInvitado?: number,
    public tarifa4hInvitado?: number,
    public tarifa5hInvitado?: number,
    public tarifa6hInvitado?: number,
    public tarifa7hInvitado?: number,
    public tarifa8hInvitado?: number,
    public impuesto?: Impuestod,
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
    public sede?: ISedes,
    public tipoEspacio?: ITipoEspacio
  ) {}
}
