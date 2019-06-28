package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.Beneficio;
import io.github.jhipster.application.repository.BeneficioRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.Beneficio}.
 */
@RestController
@RequestMapping("/api")
public class BeneficioResource {

    private final Logger log = LoggerFactory.getLogger(BeneficioResource.class);

    private static final String ENTITY_NAME = "beneficio";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BeneficioRepository beneficioRepository;

    public BeneficioResource(BeneficioRepository beneficioRepository) {
        this.beneficioRepository = beneficioRepository;
    }

    /**
     * {@code POST  /beneficios} : Create a new beneficio.
     *
     * @param beneficio the beneficio to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new beneficio, or with status {@code 400 (Bad Request)} if the beneficio has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/beneficios")
    public ResponseEntity<Beneficio> createBeneficio(@RequestBody Beneficio beneficio) throws URISyntaxException {
        log.debug("REST request to save Beneficio : {}", beneficio);
        if (beneficio.getId() != null) {
            throw new BadRequestAlertException("A new beneficio cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Beneficio result = beneficioRepository.save(beneficio);
        return ResponseEntity.created(new URI("/api/beneficios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /beneficios} : Updates an existing beneficio.
     *
     * @param beneficio the beneficio to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated beneficio,
     * or with status {@code 400 (Bad Request)} if the beneficio is not valid,
     * or with status {@code 500 (Internal Server Error)} if the beneficio couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/beneficios")
    public ResponseEntity<Beneficio> updateBeneficio(@RequestBody Beneficio beneficio) throws URISyntaxException {
        log.debug("REST request to update Beneficio : {}", beneficio);
        if (beneficio.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Beneficio result = beneficioRepository.save(beneficio);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, beneficio.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /beneficios} : get all the beneficios.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of beneficios in body.
     */
    @GetMapping("/beneficios")
    public List<Beneficio> getAllBeneficios() {
        log.debug("REST request to get all Beneficios");
        return beneficioRepository.findAll();
    }

    /**
     * {@code GET  /beneficios/:id} : get the "id" beneficio.
     *
     * @param id the id of the beneficio to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the beneficio, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/beneficios/{id}")
    public ResponseEntity<Beneficio> getBeneficio(@PathVariable Long id) {
        log.debug("REST request to get Beneficio : {}", id);
        Optional<Beneficio> beneficio = beneficioRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(beneficio);
    }

    /**
     * {@code DELETE  /beneficios/:id} : delete the "id" beneficio.
     *
     * @param id the id of the beneficio to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/beneficios/{id}")
    public ResponseEntity<Void> deleteBeneficio(@PathVariable Long id) {
        log.debug("REST request to delete Beneficio : {}", id);
        beneficioRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
