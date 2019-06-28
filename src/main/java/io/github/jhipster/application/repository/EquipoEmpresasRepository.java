package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.EquipoEmpresas;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the EquipoEmpresas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EquipoEmpresasRepository extends JpaRepository<EquipoEmpresas, Long> {

    @Query("select equipoEmpresas from EquipoEmpresas equipoEmpresas where equipoEmpresas.usuario.login = ?#{principal.username}")
    List<EquipoEmpresas> findByUsuarioIsCurrentUser();

}
