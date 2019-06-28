import { IRegistroCompra } from 'app/shared/model/registro-compra.model';
import { IEmpresa } from 'app/shared/model/empresa.model';
import { ICuentaAsociada } from 'app/shared/model/cuenta-asociada.model';

export const enum TipoPersonad {
  NATURAL = 'NATURAL',
  JURIDICA = 'JURIDICA'
}

export const enum PeriodicidadFacturaciond {
  SEMANAL = 'SEMANAL',
  QUINCENAL = 'QUINCENAL',
  MENSUAL = 'MENSUAL',
  BIMESTRAL = 'BIMESTRAL',
  TRIMESTRAL = 'TRIMESTRAL'
}

export interface IFacturacion {
  id?: number;
  titularFactura?: string;
  tipoPersona?: TipoPersonad;
  periodicidadFacturacion?: PeriodicidadFacturaciond;
  maximoMonto?: number;
  registroCompras?: IRegistroCompra[];
  empresa?: IEmpresa;
  titular?: ICuentaAsociada;
  cuentaAsociada?: ICuentaAsociada;
}

export class Facturacion implements IFacturacion {
  constructor(
    public id?: number,
    public titularFactura?: string,
    public tipoPersona?: TipoPersonad,
    public periodicidadFacturacion?: PeriodicidadFacturaciond,
    public maximoMonto?: number,
    public registroCompras?: IRegistroCompra[],
    public empresa?: IEmpresa,
    public titular?: ICuentaAsociada,
    public cuentaAsociada?: ICuentaAsociada
  ) {}
}
