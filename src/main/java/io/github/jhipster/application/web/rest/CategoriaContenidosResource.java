package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.CategoriaContenidos;
import io.github.jhipster.application.repository.CategoriaContenidosRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.CategoriaContenidos}.
 */
@RestController
@RequestMapping("/api")
public class CategoriaContenidosResource {

    private final Logger log = LoggerFactory.getLogger(CategoriaContenidosResource.class);

    private static final String ENTITY_NAME = "categoriaContenidos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategoriaContenidosRepository categoriaContenidosRepository;

    public CategoriaContenidosResource(CategoriaContenidosRepository categoriaContenidosRepository) {
        this.categoriaContenidosRepository = categoriaContenidosRepository;
    }

    /**
     * {@code POST  /categoria-contenidos} : Create a new categoriaContenidos.
     *
     * @param categoriaContenidos the categoriaContenidos to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categoriaContenidos, or with status {@code 400 (Bad Request)} if the categoriaContenidos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/categoria-contenidos")
    public ResponseEntity<CategoriaContenidos> createCategoriaContenidos(@RequestBody CategoriaContenidos categoriaContenidos) throws URISyntaxException {
        log.debug("REST request to save CategoriaContenidos : {}", categoriaContenidos);
        if (categoriaContenidos.getId() != null) {
            throw new BadRequestAlertException("A new categoriaContenidos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategoriaContenidos result = categoriaContenidosRepository.save(categoriaContenidos);
        return ResponseEntity.created(new URI("/api/categoria-contenidos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /categoria-contenidos} : Updates an existing categoriaContenidos.
     *
     * @param categoriaContenidos the categoriaContenidos to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoriaContenidos,
     * or with status {@code 400 (Bad Request)} if the categoriaContenidos is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categoriaContenidos couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/categoria-contenidos")
    public ResponseEntity<CategoriaContenidos> updateCategoriaContenidos(@RequestBody CategoriaContenidos categoriaContenidos) throws URISyntaxException {
        log.debug("REST request to update CategoriaContenidos : {}", categoriaContenidos);
        if (categoriaContenidos.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CategoriaContenidos result = categoriaContenidosRepository.save(categoriaContenidos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categoriaContenidos.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /categoria-contenidos} : get all the categoriaContenidos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categoriaContenidos in body.
     */
    @GetMapping("/categoria-contenidos")
    public List<CategoriaContenidos> getAllCategoriaContenidos() {
        log.debug("REST request to get all CategoriaContenidos");
        return categoriaContenidosRepository.findAll();
    }

    /**
     * {@code GET  /categoria-contenidos/:id} : get the "id" categoriaContenidos.
     *
     * @param id the id of the categoriaContenidos to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categoriaContenidos, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/categoria-contenidos/{id}")
    public ResponseEntity<CategoriaContenidos> getCategoriaContenidos(@PathVariable Long id) {
        log.debug("REST request to get CategoriaContenidos : {}", id);
        Optional<CategoriaContenidos> categoriaContenidos = categoriaContenidosRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(categoriaContenidos);
    }

    /**
     * {@code DELETE  /categoria-contenidos/:id} : delete the "id" categoriaContenidos.
     *
     * @param id the id of the categoriaContenidos to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/categoria-contenidos/{id}")
    public ResponseEntity<Void> deleteCategoriaContenidos(@PathVariable Long id) {
        log.debug("REST request to delete CategoriaContenidos : {}", id);
        categoriaContenidosRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
