package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.UsoRecursoFisico;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the UsoRecursoFisico entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsoRecursoFisicoRepository extends JpaRepository<UsoRecursoFisico, Long> {

}
