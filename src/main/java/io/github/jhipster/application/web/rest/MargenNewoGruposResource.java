package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.MargenNewoGrupos;
import io.github.jhipster.application.repository.MargenNewoGruposRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.MargenNewoGrupos}.
 */
@RestController
@RequestMapping("/api")
public class MargenNewoGruposResource {

    private final Logger log = LoggerFactory.getLogger(MargenNewoGruposResource.class);

    private static final String ENTITY_NAME = "margenNewoGrupos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MargenNewoGruposRepository margenNewoGruposRepository;

    public MargenNewoGruposResource(MargenNewoGruposRepository margenNewoGruposRepository) {
        this.margenNewoGruposRepository = margenNewoGruposRepository;
    }

    /**
     * {@code POST  /margen-newo-grupos} : Create a new margenNewoGrupos.
     *
     * @param margenNewoGrupos the margenNewoGrupos to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new margenNewoGrupos, or with status {@code 400 (Bad Request)} if the margenNewoGrupos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/margen-newo-grupos")
    public ResponseEntity<MargenNewoGrupos> createMargenNewoGrupos(@RequestBody MargenNewoGrupos margenNewoGrupos) throws URISyntaxException {
        log.debug("REST request to save MargenNewoGrupos : {}", margenNewoGrupos);
        if (margenNewoGrupos.getId() != null) {
            throw new BadRequestAlertException("A new margenNewoGrupos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MargenNewoGrupos result = margenNewoGruposRepository.save(margenNewoGrupos);
        return ResponseEntity.created(new URI("/api/margen-newo-grupos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /margen-newo-grupos} : Updates an existing margenNewoGrupos.
     *
     * @param margenNewoGrupos the margenNewoGrupos to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated margenNewoGrupos,
     * or with status {@code 400 (Bad Request)} if the margenNewoGrupos is not valid,
     * or with status {@code 500 (Internal Server Error)} if the margenNewoGrupos couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/margen-newo-grupos")
    public ResponseEntity<MargenNewoGrupos> updateMargenNewoGrupos(@RequestBody MargenNewoGrupos margenNewoGrupos) throws URISyntaxException {
        log.debug("REST request to update MargenNewoGrupos : {}", margenNewoGrupos);
        if (margenNewoGrupos.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MargenNewoGrupos result = margenNewoGruposRepository.save(margenNewoGrupos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, margenNewoGrupos.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /margen-newo-grupos} : get all the margenNewoGrupos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of margenNewoGrupos in body.
     */
    @GetMapping("/margen-newo-grupos")
    public List<MargenNewoGrupos> getAllMargenNewoGrupos() {
        log.debug("REST request to get all MargenNewoGrupos");
        return margenNewoGruposRepository.findAll();
    }

    /**
     * {@code GET  /margen-newo-grupos/:id} : get the "id" margenNewoGrupos.
     *
     * @param id the id of the margenNewoGrupos to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the margenNewoGrupos, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/margen-newo-grupos/{id}")
    public ResponseEntity<MargenNewoGrupos> getMargenNewoGrupos(@PathVariable Long id) {
        log.debug("REST request to get MargenNewoGrupos : {}", id);
        Optional<MargenNewoGrupos> margenNewoGrupos = margenNewoGruposRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(margenNewoGrupos);
    }

    /**
     * {@code DELETE  /margen-newo-grupos/:id} : delete the "id" margenNewoGrupos.
     *
     * @param id the id of the margenNewoGrupos to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/margen-newo-grupos/{id}")
    public ResponseEntity<Void> deleteMargenNewoGrupos(@PathVariable Long id) {
        log.debug("REST request to delete MargenNewoGrupos : {}", id);
        margenNewoGruposRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
