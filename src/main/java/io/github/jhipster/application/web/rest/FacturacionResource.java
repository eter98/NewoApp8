package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.Facturacion;
import io.github.jhipster.application.repository.FacturacionRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.Facturacion}.
 */
@RestController
@RequestMapping("/api")
public class FacturacionResource {

    private final Logger log = LoggerFactory.getLogger(FacturacionResource.class);

    private static final String ENTITY_NAME = "facturacion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FacturacionRepository facturacionRepository;

    public FacturacionResource(FacturacionRepository facturacionRepository) {
        this.facturacionRepository = facturacionRepository;
    }

    /**
     * {@code POST  /facturacions} : Create a new facturacion.
     *
     * @param facturacion the facturacion to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new facturacion, or with status {@code 400 (Bad Request)} if the facturacion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/facturacions")
    public ResponseEntity<Facturacion> createFacturacion(@RequestBody Facturacion facturacion) throws URISyntaxException {
        log.debug("REST request to save Facturacion : {}", facturacion);
        if (facturacion.getId() != null) {
            throw new BadRequestAlertException("A new facturacion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Facturacion result = facturacionRepository.save(facturacion);
        return ResponseEntity.created(new URI("/api/facturacions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /facturacions} : Updates an existing facturacion.
     *
     * @param facturacion the facturacion to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated facturacion,
     * or with status {@code 400 (Bad Request)} if the facturacion is not valid,
     * or with status {@code 500 (Internal Server Error)} if the facturacion couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/facturacions")
    public ResponseEntity<Facturacion> updateFacturacion(@RequestBody Facturacion facturacion) throws URISyntaxException {
        log.debug("REST request to update Facturacion : {}", facturacion);
        if (facturacion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Facturacion result = facturacionRepository.save(facturacion);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, facturacion.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /facturacions} : get all the facturacions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of facturacions in body.
     */
    @GetMapping("/facturacions")
    public List<Facturacion> getAllFacturacions() {
        log.debug("REST request to get all Facturacions");
        return facturacionRepository.findAll();
    }

    /**
     * {@code GET  /facturacions/:id} : get the "id" facturacion.
     *
     * @param id the id of the facturacion to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the facturacion, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/facturacions/{id}")
    public ResponseEntity<Facturacion> getFacturacion(@PathVariable Long id) {
        log.debug("REST request to get Facturacion : {}", id);
        Optional<Facturacion> facturacion = facturacionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(facturacion);
    }

    /**
     * {@code DELETE  /facturacions/:id} : delete the "id" facturacion.
     *
     * @param id the id of the facturacion to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/facturacions/{id}")
    public ResponseEntity<Void> deleteFacturacion(@PathVariable Long id) {
        log.debug("REST request to delete Facturacion : {}", id);
        facturacionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
