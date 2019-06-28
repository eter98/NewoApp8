package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.Landing;
import io.github.jhipster.application.repository.LandingRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.Landing}.
 */
@RestController
@RequestMapping("/api")
public class LandingResource {

    private final Logger log = LoggerFactory.getLogger(LandingResource.class);

    private static final String ENTITY_NAME = "landing";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LandingRepository landingRepository;

    public LandingResource(LandingRepository landingRepository) {
        this.landingRepository = landingRepository;
    }

    /**
     * {@code POST  /landings} : Create a new landing.
     *
     * @param landing the landing to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new landing, or with status {@code 400 (Bad Request)} if the landing has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/landings")
    public ResponseEntity<Landing> createLanding(@RequestBody Landing landing) throws URISyntaxException {
        log.debug("REST request to save Landing : {}", landing);
        if (landing.getId() != null) {
            throw new BadRequestAlertException("A new landing cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Landing result = landingRepository.save(landing);
        return ResponseEntity.created(new URI("/api/landings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /landings} : Updates an existing landing.
     *
     * @param landing the landing to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated landing,
     * or with status {@code 400 (Bad Request)} if the landing is not valid,
     * or with status {@code 500 (Internal Server Error)} if the landing couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/landings")
    public ResponseEntity<Landing> updateLanding(@RequestBody Landing landing) throws URISyntaxException {
        log.debug("REST request to update Landing : {}", landing);
        if (landing.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Landing result = landingRepository.save(landing);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, landing.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /landings} : get all the landings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of landings in body.
     */
    @GetMapping("/landings")
    public List<Landing> getAllLandings() {
        log.debug("REST request to get all Landings");
        return landingRepository.findAll();
    }

    /**
     * {@code GET  /landings/:id} : get the "id" landing.
     *
     * @param id the id of the landing to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the landing, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/landings/{id}")
    public ResponseEntity<Landing> getLanding(@PathVariable Long id) {
        log.debug("REST request to get Landing : {}", id);
        Optional<Landing> landing = landingRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(landing);
    }

    /**
     * {@code DELETE  /landings/:id} : delete the "id" landing.
     *
     * @param id the id of the landing to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/landings/{id}")
    public ResponseEntity<Void> deleteLanding(@PathVariable Long id) {
        log.debug("REST request to delete Landing : {}", id);
        landingRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
