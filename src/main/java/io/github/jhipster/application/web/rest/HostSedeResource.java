package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.HostSede;
import io.github.jhipster.application.repository.HostSedeRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.HostSede}.
 */
@RestController
@RequestMapping("/api")
public class HostSedeResource {

    private final Logger log = LoggerFactory.getLogger(HostSedeResource.class);

    private static final String ENTITY_NAME = "hostSede";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HostSedeRepository hostSedeRepository;

    public HostSedeResource(HostSedeRepository hostSedeRepository) {
        this.hostSedeRepository = hostSedeRepository;
    }

    /**
     * {@code POST  /host-sedes} : Create a new hostSede.
     *
     * @param hostSede the hostSede to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hostSede, or with status {@code 400 (Bad Request)} if the hostSede has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/host-sedes")
    public ResponseEntity<HostSede> createHostSede(@RequestBody HostSede hostSede) throws URISyntaxException {
        log.debug("REST request to save HostSede : {}", hostSede);
        if (hostSede.getId() != null) {
            throw new BadRequestAlertException("A new hostSede cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HostSede result = hostSedeRepository.save(hostSede);
        return ResponseEntity.created(new URI("/api/host-sedes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /host-sedes} : Updates an existing hostSede.
     *
     * @param hostSede the hostSede to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hostSede,
     * or with status {@code 400 (Bad Request)} if the hostSede is not valid,
     * or with status {@code 500 (Internal Server Error)} if the hostSede couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/host-sedes")
    public ResponseEntity<HostSede> updateHostSede(@RequestBody HostSede hostSede) throws URISyntaxException {
        log.debug("REST request to update HostSede : {}", hostSede);
        if (hostSede.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HostSede result = hostSedeRepository.save(hostSede);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, hostSede.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /host-sedes} : get all the hostSedes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hostSedes in body.
     */
    @GetMapping("/host-sedes")
    public List<HostSede> getAllHostSedes() {
        log.debug("REST request to get all HostSedes");
        return hostSedeRepository.findAll();
    }

    /**
     * {@code GET  /host-sedes/:id} : get the "id" hostSede.
     *
     * @param id the id of the hostSede to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hostSede, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/host-sedes/{id}")
    public ResponseEntity<HostSede> getHostSede(@PathVariable Long id) {
        log.debug("REST request to get HostSede : {}", id);
        Optional<HostSede> hostSede = hostSedeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(hostSede);
    }

    /**
     * {@code DELETE  /host-sedes/:id} : delete the "id" hostSede.
     *
     * @param id the id of the hostSede to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/host-sedes/{id}")
    public ResponseEntity<Void> deleteHostSede(@PathVariable Long id) {
        log.debug("REST request to delete HostSede : {}", id);
        hostSedeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
