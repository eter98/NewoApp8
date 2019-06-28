package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.MargenNewoEventos;
import io.github.jhipster.application.repository.MargenNewoEventosRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.MargenNewoEventos}.
 */
@RestController
@RequestMapping("/api")
public class MargenNewoEventosResource {

    private final Logger log = LoggerFactory.getLogger(MargenNewoEventosResource.class);

    private static final String ENTITY_NAME = "margenNewoEventos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MargenNewoEventosRepository margenNewoEventosRepository;

    public MargenNewoEventosResource(MargenNewoEventosRepository margenNewoEventosRepository) {
        this.margenNewoEventosRepository = margenNewoEventosRepository;
    }

    /**
     * {@code POST  /margen-newo-eventos} : Create a new margenNewoEventos.
     *
     * @param margenNewoEventos the margenNewoEventos to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new margenNewoEventos, or with status {@code 400 (Bad Request)} if the margenNewoEventos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/margen-newo-eventos")
    public ResponseEntity<MargenNewoEventos> createMargenNewoEventos(@RequestBody MargenNewoEventos margenNewoEventos) throws URISyntaxException {
        log.debug("REST request to save MargenNewoEventos : {}", margenNewoEventos);
        if (margenNewoEventos.getId() != null) {
            throw new BadRequestAlertException("A new margenNewoEventos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MargenNewoEventos result = margenNewoEventosRepository.save(margenNewoEventos);
        return ResponseEntity.created(new URI("/api/margen-newo-eventos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /margen-newo-eventos} : Updates an existing margenNewoEventos.
     *
     * @param margenNewoEventos the margenNewoEventos to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated margenNewoEventos,
     * or with status {@code 400 (Bad Request)} if the margenNewoEventos is not valid,
     * or with status {@code 500 (Internal Server Error)} if the margenNewoEventos couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/margen-newo-eventos")
    public ResponseEntity<MargenNewoEventos> updateMargenNewoEventos(@RequestBody MargenNewoEventos margenNewoEventos) throws URISyntaxException {
        log.debug("REST request to update MargenNewoEventos : {}", margenNewoEventos);
        if (margenNewoEventos.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MargenNewoEventos result = margenNewoEventosRepository.save(margenNewoEventos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, margenNewoEventos.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /margen-newo-eventos} : get all the margenNewoEventos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of margenNewoEventos in body.
     */
    @GetMapping("/margen-newo-eventos")
    public List<MargenNewoEventos> getAllMargenNewoEventos() {
        log.debug("REST request to get all MargenNewoEventos");
        return margenNewoEventosRepository.findAll();
    }

    /**
     * {@code GET  /margen-newo-eventos/:id} : get the "id" margenNewoEventos.
     *
     * @param id the id of the margenNewoEventos to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the margenNewoEventos, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/margen-newo-eventos/{id}")
    public ResponseEntity<MargenNewoEventos> getMargenNewoEventos(@PathVariable Long id) {
        log.debug("REST request to get MargenNewoEventos : {}", id);
        Optional<MargenNewoEventos> margenNewoEventos = margenNewoEventosRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(margenNewoEventos);
    }

    /**
     * {@code DELETE  /margen-newo-eventos/:id} : delete the "id" margenNewoEventos.
     *
     * @param id the id of the margenNewoEventos to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/margen-newo-eventos/{id}")
    public ResponseEntity<Void> deleteMargenNewoEventos(@PathVariable Long id) {
        log.debug("REST request to delete MargenNewoEventos : {}", id);
        margenNewoEventosRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
