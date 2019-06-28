import { Moment } from 'moment';
import { IFacturacion } from 'app/shared/model/facturacion.model';

export interface ICuentaAsociada {
  id?: number;
  identificaciontitular?: string;
  nombreTitular?: string;
  apellidoTitular?: string;
  numeroCuenta?: string;
  tipoCuenta?: string;
  codigoSeguridad?: string;
  fechaVencimiento?: Moment;
  facturacions?: IFacturacion[];
}

export class CuentaAsociada implements ICuentaAsociada {
  constructor(
    public id?: number,
    public identificaciontitular?: string,
    public nombreTitular?: string,
    public apellidoTitular?: string,
    public numeroCuenta?: string,
    public tipoCuenta?: string,
    public codigoSeguridad?: string,
    public fechaVencimiento?: Moment,
    public facturacions?: IFacturacion[]
  ) {}
}
