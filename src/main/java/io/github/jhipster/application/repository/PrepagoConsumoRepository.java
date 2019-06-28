package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.PrepagoConsumo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PrepagoConsumo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrepagoConsumoRepository extends JpaRepository<PrepagoConsumo, Long> {

}
