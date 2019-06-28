package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ConsumoMarket;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ConsumoMarket entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConsumoMarketRepository extends JpaRepository<ConsumoMarket, Long> {

}
