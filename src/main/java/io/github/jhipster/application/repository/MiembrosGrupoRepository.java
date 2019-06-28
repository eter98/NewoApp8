package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.MiembrosGrupo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MiembrosGrupo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MiembrosGrupoRepository extends JpaRepository<MiembrosGrupo, Long> {

}
