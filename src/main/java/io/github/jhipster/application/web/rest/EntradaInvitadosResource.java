package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.EntradaInvitados;
import io.github.jhipster.application.repository.EntradaInvitadosRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.EntradaInvitados}.
 */
@RestController
@RequestMapping("/api")
public class EntradaInvitadosResource {

    private final Logger log = LoggerFactory.getLogger(EntradaInvitadosResource.class);

    private static final String ENTITY_NAME = "entradaInvitados";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EntradaInvitadosRepository entradaInvitadosRepository;

    public EntradaInvitadosResource(EntradaInvitadosRepository entradaInvitadosRepository) {
        this.entradaInvitadosRepository = entradaInvitadosRepository;
    }

    /**
     * {@code POST  /entrada-invitados} : Create a new entradaInvitados.
     *
     * @param entradaInvitados the entradaInvitados to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entradaInvitados, or with status {@code 400 (Bad Request)} if the entradaInvitados has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/entrada-invitados")
    public ResponseEntity<EntradaInvitados> createEntradaInvitados(@RequestBody EntradaInvitados entradaInvitados) throws URISyntaxException {
        log.debug("REST request to save EntradaInvitados : {}", entradaInvitados);
        if (entradaInvitados.getId() != null) {
            throw new BadRequestAlertException("A new entradaInvitados cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EntradaInvitados result = entradaInvitadosRepository.save(entradaInvitados);
        return ResponseEntity.created(new URI("/api/entrada-invitados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /entrada-invitados} : Updates an existing entradaInvitados.
     *
     * @param entradaInvitados the entradaInvitados to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entradaInvitados,
     * or with status {@code 400 (Bad Request)} if the entradaInvitados is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entradaInvitados couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/entrada-invitados")
    public ResponseEntity<EntradaInvitados> updateEntradaInvitados(@RequestBody EntradaInvitados entradaInvitados) throws URISyntaxException {
        log.debug("REST request to update EntradaInvitados : {}", entradaInvitados);
        if (entradaInvitados.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EntradaInvitados result = entradaInvitadosRepository.save(entradaInvitados);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entradaInvitados.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /entrada-invitados} : get all the entradaInvitados.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entradaInvitados in body.
     */
    @GetMapping("/entrada-invitados")
    public List<EntradaInvitados> getAllEntradaInvitados() {
        log.debug("REST request to get all EntradaInvitados");
        return entradaInvitadosRepository.findAll();
    }

    /**
     * {@code GET  /entrada-invitados/:id} : get the "id" entradaInvitados.
     *
     * @param id the id of the entradaInvitados to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entradaInvitados, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/entrada-invitados/{id}")
    public ResponseEntity<EntradaInvitados> getEntradaInvitados(@PathVariable Long id) {
        log.debug("REST request to get EntradaInvitados : {}", id);
        Optional<EntradaInvitados> entradaInvitados = entradaInvitadosRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(entradaInvitados);
    }

    /**
     * {@code DELETE  /entrada-invitados/:id} : delete the "id" entradaInvitados.
     *
     * @param id the id of the entradaInvitados to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/entrada-invitados/{id}")
    public ResponseEntity<Void> deleteEntradaInvitados(@PathVariable Long id) {
        log.debug("REST request to delete EntradaInvitados : {}", id);
        entradaInvitadosRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
