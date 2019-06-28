import { Moment } from 'moment';
import { IRegistroCompra } from 'app/shared/model/registro-compra.model';
import { IEspacioLibre } from 'app/shared/model/espacio-libre.model';
import { IEspaciosReserva } from 'app/shared/model/espacios-reserva.model';
import { IInvitados } from 'app/shared/model/invitados.model';
import { IMiembros } from 'app/shared/model/miembros.model';

export const enum TipoEntradad {
  INGRESO = 'INGRESO',
  SALIDA = 'SALIDA'
}

export const enum TipoIngresod {
  Espacio_Libre = 'Espacio_Libre',
  Reserva = 'Reserva',
  Oficina = 'Oficina'
}

export interface IEntradaInvitados {
  id?: number;
  registroFecha?: Moment;
  tipoEntrada?: TipoEntradad;
  tipoIngreso?: TipoIngresod;
  tiempoMaximo?: boolean;
  registroCompras?: IRegistroCompra[];
  espacio?: IEspacioLibre;
  espacioReserva?: IEspaciosReserva;
  invitado?: IInvitados;
  miembro?: IMiembros;
}

export class EntradaInvitados implements IEntradaInvitados {
  constructor(
    public id?: number,
    public registroFecha?: Moment,
    public tipoEntrada?: TipoEntradad,
    public tipoIngreso?: TipoIngresod,
    public tiempoMaximo?: boolean,
    public registroCompras?: IRegistroCompra[],
    public espacio?: IEspacioLibre,
    public espacioReserva?: IEspaciosReserva,
    public invitado?: IInvitados,
    public miembro?: IMiembros
  ) {
    this.tiempoMaximo = this.tiempoMaximo || false;
  }
}
