import { ISedes } from 'app/shared/model/sedes.model';
import { ITipoRecurso } from 'app/shared/model/tipo-recurso.model';

export const enum TipoRecursod {
  Tiempo = 'Tiempo',
  Cantidad = 'Cantidad'
}

export const enum Impuestod {
  IVA19 = 'IVA19',
  IVA6 = 'IVA6',
  IVA0 = 'IVA0',
  ICO = 'ICO',
  IPOCONSUMO8 = 'IPOCONSUMO8'
}

export interface IRecursosFisicos {
  id?: number;
  recurso?: string;
  tipo?: TipoRecursod;
  unidadMedida?: string;
  valorUnitario?: number;
  valor1H?: number;
  valor2H?: number;
  valor3H?: number;
  valor4H?: number;
  valor5H?: number;
  valor6H?: number;
  valor7H?: number;
  valor8H?: number;
  valor9H?: number;
  valor10H?: number;
  valor11H?: number;
  valor12H?: number;
  impuesto?: Impuestod;
  fotoContentType?: string;
  foto?: any;
  videoContentType?: string;
  video?: any;
  sede?: ISedes;
  tipoRecurso?: ITipoRecurso;
}

export class RecursosFisicos implements IRecursosFisicos {
  constructor(
    public id?: number,
    public recurso?: string,
    public tipo?: TipoRecursod,
    public unidadMedida?: string,
    public valorUnitario?: number,
    public valor1H?: number,
    public valor2H?: number,
    public valor3H?: number,
    public valor4H?: number,
    public valor5H?: number,
    public valor6H?: number,
    public valor7H?: number,
    public valor8H?: number,
    public valor9H?: number,
    public valor10H?: number,
    public valor11H?: number,
    public valor12H?: number,
    public impuesto?: Impuestod,
    public fotoContentType?: string,
    public foto?: any,
    public videoContentType?: string,
    public video?: any,
    public sede?: ISedes,
    public tipoRecurso?: ITipoRecurso
  ) {}
}
