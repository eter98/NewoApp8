package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.Sedes;
import io.github.jhipster.application.repository.SedesRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.Sedes}.
 */
@RestController
@RequestMapping("/api")
public class SedesResource {

    private final Logger log = LoggerFactory.getLogger(SedesResource.class);

    private static final String ENTITY_NAME = "sedes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SedesRepository sedesRepository;

    public SedesResource(SedesRepository sedesRepository) {
        this.sedesRepository = sedesRepository;
    }

    /**
     * {@code POST  /sedes} : Create a new sedes.
     *
     * @param sedes the sedes to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sedes, or with status {@code 400 (Bad Request)} if the sedes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sedes")
    public ResponseEntity<Sedes> createSedes(@RequestBody Sedes sedes) throws URISyntaxException {
        log.debug("REST request to save Sedes : {}", sedes);
        if (sedes.getId() != null) {
            throw new BadRequestAlertException("A new sedes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Sedes result = sedesRepository.save(sedes);
        return ResponseEntity.created(new URI("/api/sedes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sedes} : Updates an existing sedes.
     *
     * @param sedes the sedes to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sedes,
     * or with status {@code 400 (Bad Request)} if the sedes is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sedes couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sedes")
    public ResponseEntity<Sedes> updateSedes(@RequestBody Sedes sedes) throws URISyntaxException {
        log.debug("REST request to update Sedes : {}", sedes);
        if (sedes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Sedes result = sedesRepository.save(sedes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sedes.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sedes} : get all the sedes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sedes in body.
     */
    @GetMapping("/sedes")
    public List<Sedes> getAllSedes() {
        log.debug("REST request to get all Sedes");
        return sedesRepository.findAll();
    }

    /**
     * {@code GET  /sedes/:id} : get the "id" sedes.
     *
     * @param id the id of the sedes to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sedes, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sedes/{id}")
    public ResponseEntity<Sedes> getSedes(@PathVariable Long id) {
        log.debug("REST request to get Sedes : {}", id);
        Optional<Sedes> sedes = sedesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sedes);
    }

    /**
     * {@code DELETE  /sedes/:id} : delete the "id" sedes.
     *
     * @param id the id of the sedes to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sedes/{id}")
    public ResponseEntity<Void> deleteSedes(@PathVariable Long id) {
        log.debug("REST request to delete Sedes : {}", id);
        sedesRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
