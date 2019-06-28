import { Moment } from 'moment';
import { IRecursosFisicos } from 'app/shared/model/recursos-fisicos.model';
import { IMiembros } from 'app/shared/model/miembros.model';

export const enum TipoIniciod {
  Inicio = 'Inicio',
  Fin = 'Fin'
}

export interface IUsoRecursoFisico {
  id?: number;
  registroFechaInicio?: Moment;
  registroFechaFinal?: Moment;
  tipoRegistro?: TipoIniciod;
  recurso?: IRecursosFisicos;
  miembro?: IMiembros;
}

export class UsoRecursoFisico implements IUsoRecursoFisico {
  constructor(
    public id?: number,
    public registroFechaInicio?: Moment,
    public registroFechaFinal?: Moment,
    public tipoRegistro?: TipoIniciod,
    public recurso?: IRecursosFisicos,
    public miembro?: IMiembros
  ) {}
}
