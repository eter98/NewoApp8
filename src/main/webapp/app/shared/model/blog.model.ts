import { ICategoriaContenidos } from 'app/shared/model/categoria-contenidos.model';
import { IGrupos } from 'app/shared/model/grupos.model';
import { IMiembros } from 'app/shared/model/miembros.model';

export const enum Categoriad {
  GENERAL = 'GENERAL',
  DE_GRUPO = 'DE_GRUPO',
  CORPORATIVO = 'CORPORATIVO',
  INSTITUCIONAL = 'INSTITUCIONAL',
  INTERNO = 'INTERNO'
}

export const enum TipoConsumod {
  GRATIS = 'GRATIS',
  PAGO = 'PAGO'
}

export const enum Impuestod {
  IVA19 = 'IVA19',
  IVA6 = 'IVA6',
  IVA0 = 'IVA0',
  ICO = 'ICO',
  IPOCONSUMO8 = 'IPOCONSUMO8'
}

export const enum EstadoPublicaciond {
  BORRADOR = 'BORRADOR',
  EN_REVISION = 'EN_REVISION',
  APROBADO = 'APROBADO',
  PUBLICADO = 'PUBLICADO',
  RECHAZADO = 'RECHAZADO'
}

export interface IBlog {
  id?: number;
  titulo?: string;
  descripcion?: string;
  tipoContenido?: Categoriad;
  contenidos?: any;
  audioContentType?: string;
  audio?: any;
  videoContentType?: string;
  video?: any;
  foto1ContentType?: string;
  foto1?: any;
  foto2ContentType?: string;
  foto2?: any;
  bannerContentType?: string;
  banner?: any;
  tipoConsumo?: TipoConsumod;
  valor?: number;
  impuesto?: Impuestod;
  estadoPublicacion?: EstadoPublicaciond;
  categoriaBlog?: ICategoriaContenidos;
  grupos?: IGrupos;
  miembro?: IMiembros;
}

export class Blog implements IBlog {
  constructor(
    public id?: number,
    public titulo?: string,
    public descripcion?: string,
    public tipoContenido?: Categoriad,
    public contenidos?: any,
    public audioContentType?: string,
    public audio?: any,
    public videoContentType?: string,
    public video?: any,
    public foto1ContentType?: string,
    public foto1?: any,
    public foto2ContentType?: string,
    public foto2?: any,
    public bannerContentType?: string,
    public banner?: any,
    public tipoConsumo?: TipoConsumod,
    public valor?: number,
    public impuesto?: Impuestod,
    public estadoPublicacion?: EstadoPublicaciond,
    public categoriaBlog?: ICategoriaContenidos,
    public grupos?: IGrupos,
    public miembro?: IMiembros
  ) {}
}
