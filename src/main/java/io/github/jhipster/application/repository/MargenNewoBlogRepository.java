package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.MargenNewoBlog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MargenNewoBlog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MargenNewoBlogRepository extends JpaRepository<MargenNewoBlog, Long> {

}
