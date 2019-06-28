package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.EspacioLibre;
import io.github.jhipster.application.repository.EspacioLibreRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.EspacioLibre}.
 */
@RestController
@RequestMapping("/api")
public class EspacioLibreResource {

    private final Logger log = LoggerFactory.getLogger(EspacioLibreResource.class);

    private static final String ENTITY_NAME = "espacioLibre";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EspacioLibreRepository espacioLibreRepository;

    public EspacioLibreResource(EspacioLibreRepository espacioLibreRepository) {
        this.espacioLibreRepository = espacioLibreRepository;
    }

    /**
     * {@code POST  /espacio-libres} : Create a new espacioLibre.
     *
     * @param espacioLibre the espacioLibre to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new espacioLibre, or with status {@code 400 (Bad Request)} if the espacioLibre has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/espacio-libres")
    public ResponseEntity<EspacioLibre> createEspacioLibre(@RequestBody EspacioLibre espacioLibre) throws URISyntaxException {
        log.debug("REST request to save EspacioLibre : {}", espacioLibre);
        if (espacioLibre.getId() != null) {
            throw new BadRequestAlertException("A new espacioLibre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EspacioLibre result = espacioLibreRepository.save(espacioLibre);
        return ResponseEntity.created(new URI("/api/espacio-libres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /espacio-libres} : Updates an existing espacioLibre.
     *
     * @param espacioLibre the espacioLibre to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated espacioLibre,
     * or with status {@code 400 (Bad Request)} if the espacioLibre is not valid,
     * or with status {@code 500 (Internal Server Error)} if the espacioLibre couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/espacio-libres")
    public ResponseEntity<EspacioLibre> updateEspacioLibre(@RequestBody EspacioLibre espacioLibre) throws URISyntaxException {
        log.debug("REST request to update EspacioLibre : {}", espacioLibre);
        if (espacioLibre.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EspacioLibre result = espacioLibreRepository.save(espacioLibre);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, espacioLibre.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /espacio-libres} : get all the espacioLibres.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of espacioLibres in body.
     */
    @GetMapping("/espacio-libres")
    public List<EspacioLibre> getAllEspacioLibres() {
        log.debug("REST request to get all EspacioLibres");
        return espacioLibreRepository.findAll();
    }

    /**
     * {@code GET  /espacio-libres/:id} : get the "id" espacioLibre.
     *
     * @param id the id of the espacioLibre to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the espacioLibre, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/espacio-libres/{id}")
    public ResponseEntity<EspacioLibre> getEspacioLibre(@PathVariable Long id) {
        log.debug("REST request to get EspacioLibre : {}", id);
        Optional<EspacioLibre> espacioLibre = espacioLibreRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(espacioLibre);
    }

    /**
     * {@code DELETE  /espacio-libres/:id} : delete the "id" espacioLibre.
     *
     * @param id the id of the espacioLibre to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/espacio-libres/{id}")
    public ResponseEntity<Void> deleteEspacioLibre(@PathVariable Long id) {
        log.debug("REST request to delete EspacioLibre : {}", id);
        espacioLibreRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
