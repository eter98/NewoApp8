package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.EspaciosReserva;
import io.github.jhipster.application.repository.EspaciosReservaRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.EspaciosReserva}.
 */
@RestController
@RequestMapping("/api")
public class EspaciosReservaResource {

    private final Logger log = LoggerFactory.getLogger(EspaciosReservaResource.class);

    private static final String ENTITY_NAME = "espaciosReserva";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EspaciosReservaRepository espaciosReservaRepository;

    public EspaciosReservaResource(EspaciosReservaRepository espaciosReservaRepository) {
        this.espaciosReservaRepository = espaciosReservaRepository;
    }

    /**
     * {@code POST  /espacios-reservas} : Create a new espaciosReserva.
     *
     * @param espaciosReserva the espaciosReserva to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new espaciosReserva, or with status {@code 400 (Bad Request)} if the espaciosReserva has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/espacios-reservas")
    public ResponseEntity<EspaciosReserva> createEspaciosReserva(@RequestBody EspaciosReserva espaciosReserva) throws URISyntaxException {
        log.debug("REST request to save EspaciosReserva : {}", espaciosReserva);
        if (espaciosReserva.getId() != null) {
            throw new BadRequestAlertException("A new espaciosReserva cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EspaciosReserva result = espaciosReservaRepository.save(espaciosReserva);
        return ResponseEntity.created(new URI("/api/espacios-reservas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /espacios-reservas} : Updates an existing espaciosReserva.
     *
     * @param espaciosReserva the espaciosReserva to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated espaciosReserva,
     * or with status {@code 400 (Bad Request)} if the espaciosReserva is not valid,
     * or with status {@code 500 (Internal Server Error)} if the espaciosReserva couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/espacios-reservas")
    public ResponseEntity<EspaciosReserva> updateEspaciosReserva(@RequestBody EspaciosReserva espaciosReserva) throws URISyntaxException {
        log.debug("REST request to update EspaciosReserva : {}", espaciosReserva);
        if (espaciosReserva.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EspaciosReserva result = espaciosReservaRepository.save(espaciosReserva);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, espaciosReserva.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /espacios-reservas} : get all the espaciosReservas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of espaciosReservas in body.
     */
    @GetMapping("/espacios-reservas")
    public List<EspaciosReserva> getAllEspaciosReservas() {
        log.debug("REST request to get all EspaciosReservas");
        return espaciosReservaRepository.findAll();
    }

    /**
     * {@code GET  /espacios-reservas/:id} : get the "id" espaciosReserva.
     *
     * @param id the id of the espaciosReserva to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the espaciosReserva, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/espacios-reservas/{id}")
    public ResponseEntity<EspaciosReserva> getEspaciosReserva(@PathVariable Long id) {
        log.debug("REST request to get EspaciosReserva : {}", id);
        Optional<EspaciosReserva> espaciosReserva = espaciosReservaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(espaciosReserva);
    }

    /**
     * {@code DELETE  /espacios-reservas/:id} : delete the "id" espaciosReserva.
     *
     * @param id the id of the espaciosReserva to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/espacios-reservas/{id}")
    public ResponseEntity<Void> deleteEspaciosReserva(@PathVariable Long id) {
        log.debug("REST request to delete EspaciosReserva : {}", id);
        espaciosReservaRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
