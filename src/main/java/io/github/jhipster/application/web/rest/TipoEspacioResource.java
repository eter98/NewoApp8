package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.TipoEspacio;
import io.github.jhipster.application.repository.TipoEspacioRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.TipoEspacio}.
 */
@RestController
@RequestMapping("/api")
public class TipoEspacioResource {

    private final Logger log = LoggerFactory.getLogger(TipoEspacioResource.class);

    private static final String ENTITY_NAME = "tipoEspacio";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TipoEspacioRepository tipoEspacioRepository;

    public TipoEspacioResource(TipoEspacioRepository tipoEspacioRepository) {
        this.tipoEspacioRepository = tipoEspacioRepository;
    }

    /**
     * {@code POST  /tipo-espacios} : Create a new tipoEspacio.
     *
     * @param tipoEspacio the tipoEspacio to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipoEspacio, or with status {@code 400 (Bad Request)} if the tipoEspacio has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tipo-espacios")
    public ResponseEntity<TipoEspacio> createTipoEspacio(@RequestBody TipoEspacio tipoEspacio) throws URISyntaxException {
        log.debug("REST request to save TipoEspacio : {}", tipoEspacio);
        if (tipoEspacio.getId() != null) {
            throw new BadRequestAlertException("A new tipoEspacio cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoEspacio result = tipoEspacioRepository.save(tipoEspacio);
        return ResponseEntity.created(new URI("/api/tipo-espacios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tipo-espacios} : Updates an existing tipoEspacio.
     *
     * @param tipoEspacio the tipoEspacio to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipoEspacio,
     * or with status {@code 400 (Bad Request)} if the tipoEspacio is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tipoEspacio couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tipo-espacios")
    public ResponseEntity<TipoEspacio> updateTipoEspacio(@RequestBody TipoEspacio tipoEspacio) throws URISyntaxException {
        log.debug("REST request to update TipoEspacio : {}", tipoEspacio);
        if (tipoEspacio.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoEspacio result = tipoEspacioRepository.save(tipoEspacio);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tipoEspacio.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tipo-espacios} : get all the tipoEspacios.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tipoEspacios in body.
     */
    @GetMapping("/tipo-espacios")
    public List<TipoEspacio> getAllTipoEspacios() {
        log.debug("REST request to get all TipoEspacios");
        return tipoEspacioRepository.findAll();
    }

    /**
     * {@code GET  /tipo-espacios/:id} : get the "id" tipoEspacio.
     *
     * @param id the id of the tipoEspacio to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tipoEspacio, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tipo-espacios/{id}")
    public ResponseEntity<TipoEspacio> getTipoEspacio(@PathVariable Long id) {
        log.debug("REST request to get TipoEspacio : {}", id);
        Optional<TipoEspacio> tipoEspacio = tipoEspacioRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tipoEspacio);
    }

    /**
     * {@code DELETE  /tipo-espacios/:id} : delete the "id" tipoEspacio.
     *
     * @param id the id of the tipoEspacio to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tipo-espacios/{id}")
    public ResponseEntity<Void> deleteTipoEspacio(@PathVariable Long id) {
        log.debug("REST request to delete TipoEspacio : {}", id);
        tipoEspacioRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
