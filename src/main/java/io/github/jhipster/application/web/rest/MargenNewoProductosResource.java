package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.MargenNewoProductos;
import io.github.jhipster.application.repository.MargenNewoProductosRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.MargenNewoProductos}.
 */
@RestController
@RequestMapping("/api")
public class MargenNewoProductosResource {

    private final Logger log = LoggerFactory.getLogger(MargenNewoProductosResource.class);

    private static final String ENTITY_NAME = "margenNewoProductos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MargenNewoProductosRepository margenNewoProductosRepository;

    public MargenNewoProductosResource(MargenNewoProductosRepository margenNewoProductosRepository) {
        this.margenNewoProductosRepository = margenNewoProductosRepository;
    }

    /**
     * {@code POST  /margen-newo-productos} : Create a new margenNewoProductos.
     *
     * @param margenNewoProductos the margenNewoProductos to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new margenNewoProductos, or with status {@code 400 (Bad Request)} if the margenNewoProductos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/margen-newo-productos")
    public ResponseEntity<MargenNewoProductos> createMargenNewoProductos(@RequestBody MargenNewoProductos margenNewoProductos) throws URISyntaxException {
        log.debug("REST request to save MargenNewoProductos : {}", margenNewoProductos);
        if (margenNewoProductos.getId() != null) {
            throw new BadRequestAlertException("A new margenNewoProductos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MargenNewoProductos result = margenNewoProductosRepository.save(margenNewoProductos);
        return ResponseEntity.created(new URI("/api/margen-newo-productos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /margen-newo-productos} : Updates an existing margenNewoProductos.
     *
     * @param margenNewoProductos the margenNewoProductos to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated margenNewoProductos,
     * or with status {@code 400 (Bad Request)} if the margenNewoProductos is not valid,
     * or with status {@code 500 (Internal Server Error)} if the margenNewoProductos couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/margen-newo-productos")
    public ResponseEntity<MargenNewoProductos> updateMargenNewoProductos(@RequestBody MargenNewoProductos margenNewoProductos) throws URISyntaxException {
        log.debug("REST request to update MargenNewoProductos : {}", margenNewoProductos);
        if (margenNewoProductos.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MargenNewoProductos result = margenNewoProductosRepository.save(margenNewoProductos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, margenNewoProductos.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /margen-newo-productos} : get all the margenNewoProductos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of margenNewoProductos in body.
     */
    @GetMapping("/margen-newo-productos")
    public List<MargenNewoProductos> getAllMargenNewoProductos() {
        log.debug("REST request to get all MargenNewoProductos");
        return margenNewoProductosRepository.findAll();
    }

    /**
     * {@code GET  /margen-newo-productos/:id} : get the "id" margenNewoProductos.
     *
     * @param id the id of the margenNewoProductos to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the margenNewoProductos, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/margen-newo-productos/{id}")
    public ResponseEntity<MargenNewoProductos> getMargenNewoProductos(@PathVariable Long id) {
        log.debug("REST request to get MargenNewoProductos : {}", id);
        Optional<MargenNewoProductos> margenNewoProductos = margenNewoProductosRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(margenNewoProductos);
    }

    /**
     * {@code DELETE  /margen-newo-productos/:id} : delete the "id" margenNewoProductos.
     *
     * @param id the id of the margenNewoProductos to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/margen-newo-productos/{id}")
    public ResponseEntity<Void> deleteMargenNewoProductos(@PathVariable Long id) {
        log.debug("REST request to delete MargenNewoProductos : {}", id);
        margenNewoProductosRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
