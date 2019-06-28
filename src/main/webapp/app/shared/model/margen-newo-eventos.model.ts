import { IEvento } from 'app/shared/model/evento.model';

export interface IMargenNewoEventos {
  id?: number;
  porcentajeMargen?: number;
  evento?: IEvento;
}

export class MargenNewoEventos implements IMargenNewoEventos {
  constructor(public id?: number, public porcentajeMargen?: number, public evento?: IEvento) {}
}
