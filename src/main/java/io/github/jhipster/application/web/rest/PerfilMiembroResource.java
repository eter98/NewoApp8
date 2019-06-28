package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.PerfilMiembro;
import io.github.jhipster.application.repository.PerfilMiembroRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.PerfilMiembro}.
 */
@RestController
@RequestMapping("/api")
public class PerfilMiembroResource {

    private final Logger log = LoggerFactory.getLogger(PerfilMiembroResource.class);

    private static final String ENTITY_NAME = "perfilMiembro";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PerfilMiembroRepository perfilMiembroRepository;

    public PerfilMiembroResource(PerfilMiembroRepository perfilMiembroRepository) {
        this.perfilMiembroRepository = perfilMiembroRepository;
    }

    /**
     * {@code POST  /perfil-miembros} : Create a new perfilMiembro.
     *
     * @param perfilMiembro the perfilMiembro to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new perfilMiembro, or with status {@code 400 (Bad Request)} if the perfilMiembro has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/perfil-miembros")
    public ResponseEntity<PerfilMiembro> createPerfilMiembro(@RequestBody PerfilMiembro perfilMiembro) throws URISyntaxException {
        log.debug("REST request to save PerfilMiembro : {}", perfilMiembro);
        if (perfilMiembro.getId() != null) {
            throw new BadRequestAlertException("A new perfilMiembro cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PerfilMiembro result = perfilMiembroRepository.save(perfilMiembro);
        return ResponseEntity.created(new URI("/api/perfil-miembros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /perfil-miembros} : Updates an existing perfilMiembro.
     *
     * @param perfilMiembro the perfilMiembro to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated perfilMiembro,
     * or with status {@code 400 (Bad Request)} if the perfilMiembro is not valid,
     * or with status {@code 500 (Internal Server Error)} if the perfilMiembro couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/perfil-miembros")
    public ResponseEntity<PerfilMiembro> updatePerfilMiembro(@RequestBody PerfilMiembro perfilMiembro) throws URISyntaxException {
        log.debug("REST request to update PerfilMiembro : {}", perfilMiembro);
        if (perfilMiembro.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PerfilMiembro result = perfilMiembroRepository.save(perfilMiembro);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, perfilMiembro.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /perfil-miembros} : get all the perfilMiembros.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of perfilMiembros in body.
     */
    @GetMapping("/perfil-miembros")
    public List<PerfilMiembro> getAllPerfilMiembros() {
        log.debug("REST request to get all PerfilMiembros");
        return perfilMiembroRepository.findAll();
    }

    /**
     * {@code GET  /perfil-miembros/:id} : get the "id" perfilMiembro.
     *
     * @param id the id of the perfilMiembro to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the perfilMiembro, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/perfil-miembros/{id}")
    public ResponseEntity<PerfilMiembro> getPerfilMiembro(@PathVariable Long id) {
        log.debug("REST request to get PerfilMiembro : {}", id);
        Optional<PerfilMiembro> perfilMiembro = perfilMiembroRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(perfilMiembro);
    }

    /**
     * {@code DELETE  /perfil-miembros/:id} : delete the "id" perfilMiembro.
     *
     * @param id the id of the perfilMiembro to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/perfil-miembros/{id}")
    public ResponseEntity<Void> deletePerfilMiembro(@PathVariable Long id) {
        log.debug("REST request to delete PerfilMiembro : {}", id);
        perfilMiembroRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
