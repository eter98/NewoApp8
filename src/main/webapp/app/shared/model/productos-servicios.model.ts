export const enum Impuestod {
  IVA19 = 'IVA19',
  IVA6 = 'IVA6',
  IVA0 = 'IVA0',
  ICO = 'ICO',
  IPOCONSUMO8 = 'IPOCONSUMO8'
}

export interface IProductosServicios {
  id?: number;
  nombreProducto?: string;
  fotoContentType?: string;
  foto?: any;
  descripcion?: string;
  inventariables?: boolean;
  valor?: number;
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
}

export class ProductosServicios implements IProductosServicios {
  constructor(
    public id?: number,
    public nombreProducto?: string,
    public fotoContentType?: string,
    public foto?: any,
    public descripcion?: string,
    public inventariables?: boolean,
    public valor?: number,
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
    public imagen6?: any
  ) {
    this.inventariables = this.inventariables || false;
  }
}
