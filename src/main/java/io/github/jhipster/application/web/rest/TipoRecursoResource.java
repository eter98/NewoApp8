package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.TipoRecurso;
import io.github.jhipster.application.repository.TipoRecursoRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.TipoRecurso}.
 */
@RestController
@RequestMapping("/api")
public class TipoRecursoResource {

    private final Logger log = LoggerFactory.getLogger(TipoRecursoResource.class);

    private static final String ENTITY_NAME = "tipoRecurso";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TipoRecursoRepository tipoRecursoRepository;

    public TipoRecursoResource(TipoRecursoRepository tipoRecursoRepository) {
        this.tipoRecursoRepository = tipoRecursoRepository;
    }

    /**
     * {@code POST  /tipo-recursos} : Create a new tipoRecurso.
     *
     * @param tipoRecurso the tipoRecurso to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipoRecurso, or with status {@code 400 (Bad Request)} if the tipoRecurso has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tipo-recursos")
    public ResponseEntity<TipoRecurso> createTipoRecurso(@RequestBody TipoRecurso tipoRecurso) throws URISyntaxException {
        log.debug("REST request to save TipoRecurso : {}", tipoRecurso);
        if (tipoRecurso.getId() != null) {
            throw new BadRequestAlertException("A new tipoRecurso cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoRecurso result = tipoRecursoRepository.save(tipoRecurso);
        return ResponseEntity.created(new URI("/api/tipo-recursos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tipo-recursos} : Updates an existing tipoRecurso.
     *
     * @param tipoRecurso the tipoRecurso to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipoRecurso,
     * or with status {@code 400 (Bad Request)} if the tipoRecurso is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tipoRecurso couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tipo-recursos")
    public ResponseEntity<TipoRecurso> updateTipoRecurso(@RequestBody TipoRecurso tipoRecurso) throws URISyntaxException {
        log.debug("REST request to update TipoRecurso : {}", tipoRecurso);
        if (tipoRecurso.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoRecurso result = tipoRecursoRepository.save(tipoRecurso);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tipoRecurso.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tipo-recursos} : get all the tipoRecursos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tipoRecursos in body.
     */
    @GetMapping("/tipo-recursos")
    public List<TipoRecurso> getAllTipoRecursos() {
        log.debug("REST request to get all TipoRecursos");
        return tipoRecursoRepository.findAll();
    }

    /**
     * {@code GET  /tipo-recursos/:id} : get the "id" tipoRecurso.
     *
     * @param id the id of the tipoRecurso to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tipoRecurso, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tipo-recursos/{id}")
    public ResponseEntity<TipoRecurso> getTipoRecurso(@PathVariable Long id) {
        log.debug("REST request to get TipoRecurso : {}", id);
        Optional<TipoRecurso> tipoRecurso = tipoRecursoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tipoRecurso);
    }

    /**
     * {@code DELETE  /tipo-recursos/:id} : delete the "id" tipoRecurso.
     *
     * @param id the id of the tipoRecurso to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tipo-recursos/{id}")
    public ResponseEntity<Void> deleteTipoRecurso(@PathVariable Long id) {
        log.debug("REST request to delete TipoRecurso : {}", id);
        tipoRecursoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
