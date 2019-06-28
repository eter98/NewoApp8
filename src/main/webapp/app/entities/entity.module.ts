import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'miembros',
        loadChildren: './miembros/miembros.module#NewoApp8MiembrosModule'
      },
      {
        path: 'perfil-miembro',
        loadChildren: './perfil-miembro/perfil-miembro.module#NewoApp8PerfilMiembroModule'
      },
      {
        path: 'entrada-miembros',
        loadChildren: './entrada-miembros/entrada-miembros.module#NewoApp8EntradaMiembrosModule'
      },
      {
        path: 'invitados',
        loadChildren: './invitados/invitados.module#NewoApp8InvitadosModule'
      },
      {
        path: 'entrada-invitados',
        loadChildren: './entrada-invitados/entrada-invitados.module#NewoApp8EntradaInvitadosModule'
      },
      {
        path: 'sedes',
        loadChildren: './sedes/sedes.module#NewoApp8SedesModule'
      },
      {
        path: 'espacio-libre',
        loadChildren: './espacio-libre/espacio-libre.module#NewoApp8EspacioLibreModule'
      },
      {
        path: 'tipo-espacio',
        loadChildren: './tipo-espacio/tipo-espacio.module#NewoApp8TipoEspacioModule'
      },
      {
        path: 'host-sede',
        loadChildren: './host-sede/host-sede.module#NewoApp8HostSedeModule'
      },
      {
        path: 'reservas',
        loadChildren: './reservas/reservas.module#NewoApp8ReservasModule'
      },
      {
        path: 'espacios-reserva',
        loadChildren: './espacios-reserva/espacios-reserva.module#NewoApp8EspaciosReservaModule'
      },
      {
        path: 'registro-compra',
        loadChildren: './registro-compra/registro-compra.module#NewoApp8RegistroCompraModule'
      },
      {
        path: 'facturacion',
        loadChildren: './facturacion/facturacion.module#NewoApp8FacturacionModule'
      },
      {
        path: 'equipo-empresas',
        loadChildren: './equipo-empresas/equipo-empresas.module#NewoApp8EquipoEmpresasModule'
      },
      {
        path: 'miembros-equipo-empresas',
        loadChildren: './miembros-equipo-empresas/miembros-equipo-empresas.module#NewoApp8MiembrosEquipoEmpresasModule'
      },
      {
        path: 'cuenta-asociada',
        loadChildren: './cuenta-asociada/cuenta-asociada.module#NewoApp8CuentaAsociadaModule'
      },
      {
        path: 'beneficio',
        loadChildren: './beneficio/beneficio.module#NewoApp8BeneficioModule'
      },
      {
        path: 'perfil-equipo-empresa',
        loadChildren: './perfil-equipo-empresa/perfil-equipo-empresa.module#NewoApp8PerfilEquipoEmpresaModule'
      },
      {
        path: 'empresa',
        loadChildren: './empresa/empresa.module#NewoApp8EmpresaModule'
      },
      {
        path: 'landing',
        loadChildren: './landing/landing.module#NewoApp8LandingModule'
      },
      {
        path: 'productos-servicios',
        loadChildren: './productos-servicios/productos-servicios.module#NewoApp8ProductosServiciosModule'
      },
      {
        path: 'pais',
        loadChildren: './pais/pais.module#NewoApp8PaisModule'
      },
      {
        path: 'ciudad',
        loadChildren: './ciudad/ciudad.module#NewoApp8CiudadModule'
      },
      {
        path: 'blog',
        loadChildren: './blog/blog.module#NewoApp8BlogModule'
      },
      {
        path: 'evento',
        loadChildren: './evento/evento.module#NewoApp8EventoModule'
      },
      {
        path: 'categoria-contenidos',
        loadChildren: './categoria-contenidos/categoria-contenidos.module#NewoApp8CategoriaContenidosModule'
      },
      {
        path: 'grupos',
        loadChildren: './grupos/grupos.module#NewoApp8GruposModule'
      },
      {
        path: 'miembros-grupo',
        loadChildren: './miembros-grupo/miembros-grupo.module#NewoApp8MiembrosGrupoModule'
      },
      {
        path: 'recursos-fisicos',
        loadChildren: './recursos-fisicos/recursos-fisicos.module#NewoApp8RecursosFisicosModule'
      },
      {
        path: 'uso-recurso-fisico',
        loadChildren: './uso-recurso-fisico/uso-recurso-fisico.module#NewoApp8UsoRecursoFisicoModule'
      },
      {
        path: 'tipo-recurso',
        loadChildren: './tipo-recurso/tipo-recurso.module#NewoApp8TipoRecursoModule'
      },
      {
        path: 'consumo-market',
        loadChildren: './consumo-market/consumo-market.module#NewoApp8ConsumoMarketModule'
      },
      {
        path: 'prepago-consumo',
        loadChildren: './prepago-consumo/prepago-consumo.module#NewoApp8PrepagoConsumoModule'
      },
      {
        path: 'margen-newo-eventos',
        loadChildren: './margen-newo-eventos/margen-newo-eventos.module#NewoApp8MargenNewoEventosModule'
      },
      {
        path: 'margen-newo-grupos',
        loadChildren: './margen-newo-grupos/margen-newo-grupos.module#NewoApp8MargenNewoGruposModule'
      },
      {
        path: 'margen-newo-blog',
        loadChildren: './margen-newo-blog/margen-newo-blog.module#NewoApp8MargenNewoBlogModule'
      },
      {
        path: 'margen-newo-productos',
        loadChildren: './margen-newo-productos/margen-newo-productos.module#NewoApp8MargenNewoProductosModule'
      },
      {
        path: 'tipo-prepago-consumo',
        loadChildren: './tipo-prepago-consumo/tipo-prepago-consumo.module#NewoApp8TipoPrepagoConsumoModule'
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NewoApp8EntityModule {}
