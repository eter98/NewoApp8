package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Miembros;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Miembros entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MiembrosRepository extends JpaRepository<Miembros, Long> {

    @Query("select miembros from Miembros miembros where miembros.miembro.login = ?#{principal.username}")
    List<Miembros> findByMiembroIsCurrentUser();

}
