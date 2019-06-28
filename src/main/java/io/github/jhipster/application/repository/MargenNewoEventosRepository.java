package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.MargenNewoEventos;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MargenNewoEventos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MargenNewoEventosRepository extends JpaRepository<MargenNewoEventos, Long> {

}
