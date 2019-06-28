package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.PerfilEquipoEmpresa;
import io.github.jhipster.application.repository.PerfilEquipoEmpresaRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.PerfilEquipoEmpresa}.
 */
@RestController
@RequestMapping("/api")
public class PerfilEquipoEmpresaResource {

    private final Logger log = LoggerFactory.getLogger(PerfilEquipoEmpresaResource.class);

    private static final String ENTITY_NAME = "perfilEquipoEmpresa";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PerfilEquipoEmpresaRepository perfilEquipoEmpresaRepository;

    public PerfilEquipoEmpresaResource(PerfilEquipoEmpresaRepository perfilEquipoEmpresaRepository) {
        this.perfilEquipoEmpresaRepository = perfilEquipoEmpresaRepository;
    }

    /**
     * {@code POST  /perfil-equipo-empresas} : Create a new perfilEquipoEmpresa.
     *
     * @param perfilEquipoEmpresa the perfilEquipoEmpresa to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new perfilEquipoEmpresa, or with status {@code 400 (Bad Request)} if the perfilEquipoEmpresa has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/perfil-equipo-empresas")
    public ResponseEntity<PerfilEquipoEmpresa> createPerfilEquipoEmpresa(@RequestBody PerfilEquipoEmpresa perfilEquipoEmpresa) throws URISyntaxException {
        log.debug("REST request to save PerfilEquipoEmpresa : {}", perfilEquipoEmpresa);
        if (perfilEquipoEmpresa.getId() != null) {
            throw new BadRequestAlertException("A new perfilEquipoEmpresa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PerfilEquipoEmpresa result = perfilEquipoEmpresaRepository.save(perfilEquipoEmpresa);
        return ResponseEntity.created(new URI("/api/perfil-equipo-empresas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /perfil-equipo-empresas} : Updates an existing perfilEquipoEmpresa.
     *
     * @param perfilEquipoEmpresa the perfilEquipoEmpresa to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated perfilEquipoEmpresa,
     * or with status {@code 400 (Bad Request)} if the perfilEquipoEmpresa is not valid,
     * or with status {@code 500 (Internal Server Error)} if the perfilEquipoEmpresa couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/perfil-equipo-empresas")
    public ResponseEntity<PerfilEquipoEmpresa> updatePerfilEquipoEmpresa(@RequestBody PerfilEquipoEmpresa perfilEquipoEmpresa) throws URISyntaxException {
        log.debug("REST request to update PerfilEquipoEmpresa : {}", perfilEquipoEmpresa);
        if (perfilEquipoEmpresa.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PerfilEquipoEmpresa result = perfilEquipoEmpresaRepository.save(perfilEquipoEmpresa);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, perfilEquipoEmpresa.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /perfil-equipo-empresas} : get all the perfilEquipoEmpresas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of perfilEquipoEmpresas in body.
     */
    @GetMapping("/perfil-equipo-empresas")
    public List<PerfilEquipoEmpresa> getAllPerfilEquipoEmpresas() {
        log.debug("REST request to get all PerfilEquipoEmpresas");
        return perfilEquipoEmpresaRepository.findAll();
    }

    /**
     * {@code GET  /perfil-equipo-empresas/:id} : get the "id" perfilEquipoEmpresa.
     *
     * @param id the id of the perfilEquipoEmpresa to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the perfilEquipoEmpresa, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/perfil-equipo-empresas/{id}")
    public ResponseEntity<PerfilEquipoEmpresa> getPerfilEquipoEmpresa(@PathVariable Long id) {
        log.debug("REST request to get PerfilEquipoEmpresa : {}", id);
        Optional<PerfilEquipoEmpresa> perfilEquipoEmpresa = perfilEquipoEmpresaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(perfilEquipoEmpresa);
    }

    /**
     * {@code DELETE  /perfil-equipo-empresas/:id} : delete the "id" perfilEquipoEmpresa.
     *
     * @param id the id of the perfilEquipoEmpresa to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/perfil-equipo-empresas/{id}")
    public ResponseEntity<Void> deletePerfilEquipoEmpresa(@PathVariable Long id) {
        log.debug("REST request to delete PerfilEquipoEmpresa : {}", id);
        perfilEquipoEmpresaRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
