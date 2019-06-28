import { IProductosServicios } from 'app/shared/model/productos-servicios.model';

export interface IMargenNewoProductos {
  id?: number;
  porcentajeMargen?: number;
  producto?: IProductosServicios;
}

export class MargenNewoProductos implements IMargenNewoProductos {
  constructor(public id?: number, public porcentajeMargen?: number, public producto?: IProductosServicios) {}
}
