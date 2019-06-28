package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.UsoRecursoFisico;
import io.github.jhipster.application.repository.UsoRecursoFisicoRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.UsoRecursoFisico}.
 */
@RestController
@RequestMapping("/api")
public class UsoRecursoFisicoResource {

    private final Logger log = LoggerFactory.getLogger(UsoRecursoFisicoResource.class);

    private static final String ENTITY_NAME = "usoRecursoFisico";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UsoRecursoFisicoRepository usoRecursoFisicoRepository;

    public UsoRecursoFisicoResource(UsoRecursoFisicoRepository usoRecursoFisicoRepository) {
        this.usoRecursoFisicoRepository = usoRecursoFisicoRepository;
    }

    /**
     * {@code POST  /uso-recurso-fisicos} : Create a new usoRecursoFisico.
     *
     * @param usoRecursoFisico the usoRecursoFisico to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new usoRecursoFisico, or with status {@code 400 (Bad Request)} if the usoRecursoFisico has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/uso-recurso-fisicos")
    public ResponseEntity<UsoRecursoFisico> createUsoRecursoFisico(@RequestBody UsoRecursoFisico usoRecursoFisico) throws URISyntaxException {
        log.debug("REST request to save UsoRecursoFisico : {}", usoRecursoFisico);
        if (usoRecursoFisico.getId() != null) {
            throw new BadRequestAlertException("A new usoRecursoFisico cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UsoRecursoFisico result = usoRecursoFisicoRepository.save(usoRecursoFisico);
        return ResponseEntity.created(new URI("/api/uso-recurso-fisicos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /uso-recurso-fisicos} : Updates an existing usoRecursoFisico.
     *
     * @param usoRecursoFisico the usoRecursoFisico to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated usoRecursoFisico,
     * or with status {@code 400 (Bad Request)} if the usoRecursoFisico is not valid,
     * or with status {@code 500 (Internal Server Error)} if the usoRecursoFisico couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/uso-recurso-fisicos")
    public ResponseEntity<UsoRecursoFisico> updateUsoRecursoFisico(@RequestBody UsoRecursoFisico usoRecursoFisico) throws URISyntaxException {
        log.debug("REST request to update UsoRecursoFisico : {}", usoRecursoFisico);
        if (usoRecursoFisico.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UsoRecursoFisico result = usoRecursoFisicoRepository.save(usoRecursoFisico);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, usoRecursoFisico.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /uso-recurso-fisicos} : get all the usoRecursoFisicos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of usoRecursoFisicos in body.
     */
    @GetMapping("/uso-recurso-fisicos")
    public List<UsoRecursoFisico> getAllUsoRecursoFisicos() {
        log.debug("REST request to get all UsoRecursoFisicos");
        return usoRecursoFisicoRepository.findAll();
    }

    /**
     * {@code GET  /uso-recurso-fisicos/:id} : get the "id" usoRecursoFisico.
     *
     * @param id the id of the usoRecursoFisico to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the usoRecursoFisico, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/uso-recurso-fisicos/{id}")
    public ResponseEntity<UsoRecursoFisico> getUsoRecursoFisico(@PathVariable Long id) {
        log.debug("REST request to get UsoRecursoFisico : {}", id);
        Optional<UsoRecursoFisico> usoRecursoFisico = usoRecursoFisicoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(usoRecursoFisico);
    }

    /**
     * {@code DELETE  /uso-recurso-fisicos/:id} : delete the "id" usoRecursoFisico.
     *
     * @param id the id of the usoRecursoFisico to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/uso-recurso-fisicos/{id}")
    public ResponseEntity<Void> deleteUsoRecursoFisico(@PathVariable Long id) {
        log.debug("REST request to delete UsoRecursoFisico : {}", id);
        usoRecursoFisicoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
