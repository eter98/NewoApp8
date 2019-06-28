package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.CategoriaContenidos;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CategoriaContenidos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoriaContenidosRepository extends JpaRepository<CategoriaContenidos, Long> {

}
