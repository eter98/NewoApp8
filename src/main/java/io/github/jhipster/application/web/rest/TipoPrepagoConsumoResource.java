package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.TipoPrepagoConsumo;
import io.github.jhipster.application.repository.TipoPrepagoConsumoRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.TipoPrepagoConsumo}.
 */
@RestController
@RequestMapping("/api")
public class TipoPrepagoConsumoResource {

    private final Logger log = LoggerFactory.getLogger(TipoPrepagoConsumoResource.class);

    private static final String ENTITY_NAME = "tipoPrepagoConsumo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TipoPrepagoConsumoRepository tipoPrepagoConsumoRepository;

    public TipoPrepagoConsumoResource(TipoPrepagoConsumoRepository tipoPrepagoConsumoRepository) {
        this.tipoPrepagoConsumoRepository = tipoPrepagoConsumoRepository;
    }

    /**
     * {@code POST  /tipo-prepago-consumos} : Create a new tipoPrepagoConsumo.
     *
     * @param tipoPrepagoConsumo the tipoPrepagoConsumo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipoPrepagoConsumo, or with status {@code 400 (Bad Request)} if the tipoPrepagoConsumo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tipo-prepago-consumos")
    public ResponseEntity<TipoPrepagoConsumo> createTipoPrepagoConsumo(@RequestBody TipoPrepagoConsumo tipoPrepagoConsumo) throws URISyntaxException {
        log.debug("REST request to save TipoPrepagoConsumo : {}", tipoPrepagoConsumo);
        if (tipoPrepagoConsumo.getId() != null) {
            throw new BadRequestAlertException("A new tipoPrepagoConsumo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoPrepagoConsumo result = tipoPrepagoConsumoRepository.save(tipoPrepagoConsumo);
        return ResponseEntity.created(new URI("/api/tipo-prepago-consumos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tipo-prepago-consumos} : Updates an existing tipoPrepagoConsumo.
     *
     * @param tipoPrepagoConsumo the tipoPrepagoConsumo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipoPrepagoConsumo,
     * or with status {@code 400 (Bad Request)} if the tipoPrepagoConsumo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tipoPrepagoConsumo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tipo-prepago-consumos")
    public ResponseEntity<TipoPrepagoConsumo> updateTipoPrepagoConsumo(@RequestBody TipoPrepagoConsumo tipoPrepagoConsumo) throws URISyntaxException {
        log.debug("REST request to update TipoPrepagoConsumo : {}", tipoPrepagoConsumo);
        if (tipoPrepagoConsumo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoPrepagoConsumo result = tipoPrepagoConsumoRepository.save(tipoPrepagoConsumo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tipoPrepagoConsumo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tipo-prepago-consumos} : get all the tipoPrepagoConsumos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tipoPrepagoConsumos in body.
     */
    @GetMapping("/tipo-prepago-consumos")
    public List<TipoPrepagoConsumo> getAllTipoPrepagoConsumos() {
        log.debug("REST request to get all TipoPrepagoConsumos");
        return tipoPrepagoConsumoRepository.findAll();
    }

    /**
     * {@code GET  /tipo-prepago-consumos/:id} : get the "id" tipoPrepagoConsumo.
     *
     * @param id the id of the tipoPrepagoConsumo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tipoPrepagoConsumo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tipo-prepago-consumos/{id}")
    public ResponseEntity<TipoPrepagoConsumo> getTipoPrepagoConsumo(@PathVariable Long id) {
        log.debug("REST request to get TipoPrepagoConsumo : {}", id);
        Optional<TipoPrepagoConsumo> tipoPrepagoConsumo = tipoPrepagoConsumoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tipoPrepagoConsumo);
    }

    /**
     * {@code DELETE  /tipo-prepago-consumos/:id} : delete the "id" tipoPrepagoConsumo.
     *
     * @param id the id of the tipoPrepagoConsumo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tipo-prepago-consumos/{id}")
    public ResponseEntity<Void> deleteTipoPrepagoConsumo(@PathVariable Long id) {
        log.debug("REST request to delete TipoPrepagoConsumo : {}", id);
        tipoPrepagoConsumoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
