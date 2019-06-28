package io.github.jhipster.application.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, io.github.jhipster.application.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, io.github.jhipster.application.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, io.github.jhipster.application.domain.User.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Authority.class.getName());
            createCache(cm, io.github.jhipster.application.domain.User.class.getName() + ".authorities");
            createCache(cm, io.github.jhipster.application.domain.Miembros.class.getName());
            createCache(cm, io.github.jhipster.application.domain.PerfilMiembro.class.getName());
            createCache(cm, io.github.jhipster.application.domain.EntradaMiembros.class.getName());
            createCache(cm, io.github.jhipster.application.domain.EntradaMiembros.class.getName() + ".registroCompras");
            createCache(cm, io.github.jhipster.application.domain.Invitados.class.getName());
            createCache(cm, io.github.jhipster.application.domain.EntradaInvitados.class.getName());
            createCache(cm, io.github.jhipster.application.domain.EntradaInvitados.class.getName() + ".registroCompras");
            createCache(cm, io.github.jhipster.application.domain.Sedes.class.getName());
            createCache(cm, io.github.jhipster.application.domain.EspacioLibre.class.getName());
            createCache(cm, io.github.jhipster.application.domain.TipoEspacio.class.getName());
            createCache(cm, io.github.jhipster.application.domain.HostSede.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Reservas.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Reservas.class.getName() + ".registroCompras");
            createCache(cm, io.github.jhipster.application.domain.EspaciosReserva.class.getName());
            createCache(cm, io.github.jhipster.application.domain.RegistroCompra.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Facturacion.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Facturacion.class.getName() + ".registroCompras");
            createCache(cm, io.github.jhipster.application.domain.EquipoEmpresas.class.getName());
            createCache(cm, io.github.jhipster.application.domain.MiembrosEquipoEmpresas.class.getName());
            createCache(cm, io.github.jhipster.application.domain.CuentaAsociada.class.getName());
            createCache(cm, io.github.jhipster.application.domain.CuentaAsociada.class.getName() + ".facturacions");
            createCache(cm, io.github.jhipster.application.domain.Beneficio.class.getName());
            createCache(cm, io.github.jhipster.application.domain.PerfilEquipoEmpresa.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Empresa.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Landing.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ProductosServicios.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Pais.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Ciudad.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Blog.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Evento.class.getName());
            createCache(cm, io.github.jhipster.application.domain.CategoriaContenidos.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Grupos.class.getName());
            createCache(cm, io.github.jhipster.application.domain.MiembrosGrupo.class.getName());
            createCache(cm, io.github.jhipster.application.domain.RecursosFisicos.class.getName());
            createCache(cm, io.github.jhipster.application.domain.UsoRecursoFisico.class.getName());
            createCache(cm, io.github.jhipster.application.domain.TipoRecurso.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ConsumoMarket.class.getName());
            createCache(cm, io.github.jhipster.application.domain.PrepagoConsumo.class.getName());
            createCache(cm, io.github.jhipster.application.domain.MargenNewoEventos.class.getName());
            createCache(cm, io.github.jhipster.application.domain.MargenNewoGrupos.class.getName());
            createCache(cm, io.github.jhipster.application.domain.MargenNewoBlog.class.getName());
            createCache(cm, io.github.jhipster.application.domain.MargenNewoProductos.class.getName());
            createCache(cm, io.github.jhipster.application.domain.TipoPrepagoConsumo.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cm.destroyCache(cacheName);
        }
        cm.createCache(cacheName, jcacheConfiguration);
    }
}
