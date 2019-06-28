package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.RecursosFisicos;
import io.github.jhipster.application.repository.RecursosFisicosRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.RecursosFisicos}.
 */
@RestController
@RequestMapping("/api")
public class RecursosFisicosResource {

    private final Logger log = LoggerFactory.getLogger(RecursosFisicosResource.class);

    private static final String ENTITY_NAME = "recursosFisicos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RecursosFisicosRepository recursosFisicosRepository;

    public RecursosFisicosResource(RecursosFisicosRepository recursosFisicosRepository) {
        this.recursosFisicosRepository = recursosFisicosRepository;
    }

    /**
     * {@code POST  /recursos-fisicos} : Create a new recursosFisicos.
     *
     * @param recursosFisicos the recursosFisicos to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new recursosFisicos, or with status {@code 400 (Bad Request)} if the recursosFisicos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/recursos-fisicos")
    public ResponseEntity<RecursosFisicos> createRecursosFisicos(@RequestBody RecursosFisicos recursosFisicos) throws URISyntaxException {
        log.debug("REST request to save RecursosFisicos : {}", recursosFisicos);
        if (recursosFisicos.getId() != null) {
            throw new BadRequestAlertException("A new recursosFisicos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RecursosFisicos result = recursosFisicosRepository.save(recursosFisicos);
        return ResponseEntity.created(new URI("/api/recursos-fisicos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /recursos-fisicos} : Updates an existing recursosFisicos.
     *
     * @param recursosFisicos the recursosFisicos to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recursosFisicos,
     * or with status {@code 400 (Bad Request)} if the recursosFisicos is not valid,
     * or with status {@code 500 (Internal Server Error)} if the recursosFisicos couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/recursos-fisicos")
    public ResponseEntity<RecursosFisicos> updateRecursosFisicos(@RequestBody RecursosFisicos recursosFisicos) throws URISyntaxException {
        log.debug("REST request to update RecursosFisicos : {}", recursosFisicos);
        if (recursosFisicos.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RecursosFisicos result = recursosFisicosRepository.save(recursosFisicos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recursosFisicos.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /recursos-fisicos} : get all the recursosFisicos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of recursosFisicos in body.
     */
    @GetMapping("/recursos-fisicos")
    public List<RecursosFisicos> getAllRecursosFisicos() {
        log.debug("REST request to get all RecursosFisicos");
        return recursosFisicosRepository.findAll();
    }

    /**
     * {@code GET  /recursos-fisicos/:id} : get the "id" recursosFisicos.
     *
     * @param id the id of the recursosFisicos to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the recursosFisicos, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/recursos-fisicos/{id}")
    public ResponseEntity<RecursosFisicos> getRecursosFisicos(@PathVariable Long id) {
        log.debug("REST request to get RecursosFisicos : {}", id);
        Optional<RecursosFisicos> recursosFisicos = recursosFisicosRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(recursosFisicos);
    }

    /**
     * {@code DELETE  /recursos-fisicos/:id} : delete the "id" recursosFisicos.
     *
     * @param id the id of the recursosFisicos to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/recursos-fisicos/{id}")
    public ResponseEntity<Void> deleteRecursosFisicos(@PathVariable Long id) {
        log.debug("REST request to delete RecursosFisicos : {}", id);
        recursosFisicosRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
