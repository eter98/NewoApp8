package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.RecursosFisicos;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RecursosFisicos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecursosFisicosRepository extends JpaRepository<RecursosFisicos, Long> {

}
