package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.TipoPrepagoConsumo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoPrepagoConsumo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoPrepagoConsumoRepository extends JpaRepository<TipoPrepagoConsumo, Long> {

}
