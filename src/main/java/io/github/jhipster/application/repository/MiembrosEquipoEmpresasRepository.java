package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.MiembrosEquipoEmpresas;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MiembrosEquipoEmpresas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MiembrosEquipoEmpresasRepository extends JpaRepository<MiembrosEquipoEmpresas, Long> {

}
