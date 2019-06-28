import { Moment } from 'moment';
import { IRegistroCompra } from 'app/shared/model/registro-compra.model';
import { IMiembros } from 'app/shared/model/miembros.model';
import { IEspacioLibre } from 'app/shared/model/espacio-libre.model';
import { IEspaciosReserva } from 'app/shared/model/espacios-reserva.model';

export const enum TipoEntradad {
  INGRESO = 'INGRESO',
  SALIDA = 'SALIDA'
}

export const enum TipoIngresod {
  Espacio_Libre = 'Espacio_Libre',
  Reserva = 'Reserva',
  Oficina = 'Oficina'
}

export interface IEntradaMiembros {
  id?: number;
  registroFecha?: Moment;
  tipoEntrada?: TipoEntradad;
  tipoIngreso?: TipoIngresod;
  tiempoMaximo?: boolean;
  registroCompras?: IRegistroCompra[];
  miembro?: IMiembros;
  espacio?: IEspacioLibre;
  espacioReserva?: IEspaciosReserva;
}

export class EntradaMiembros implements IEntradaMiembros {
  constructor(
    public id?: number,
    public registroFecha?: Moment,
    public tipoEntrada?: TipoEntradad,
    public tipoIngreso?: TipoIngresod,
    public tiempoMaximo?: boolean,
    public registroCompras?: IRegistroCompra[],
    public miembro?: IMiembros,
    public espacio?: IEspacioLibre,
    public espacioReserva?: IEspaciosReserva
  ) {
    this.tiempoMaximo = this.tiempoMaximo || false;
  }
}
