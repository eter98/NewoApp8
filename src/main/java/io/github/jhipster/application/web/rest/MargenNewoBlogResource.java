package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.MargenNewoBlog;
import io.github.jhipster.application.repository.MargenNewoBlogRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.MargenNewoBlog}.
 */
@RestController
@RequestMapping("/api")
public class MargenNewoBlogResource {

    private final Logger log = LoggerFactory.getLogger(MargenNewoBlogResource.class);

    private static final String ENTITY_NAME = "margenNewoBlog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MargenNewoBlogRepository margenNewoBlogRepository;

    public MargenNewoBlogResource(MargenNewoBlogRepository margenNewoBlogRepository) {
        this.margenNewoBlogRepository = margenNewoBlogRepository;
    }

    /**
     * {@code POST  /margen-newo-blogs} : Create a new margenNewoBlog.
     *
     * @param margenNewoBlog the margenNewoBlog to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new margenNewoBlog, or with status {@code 400 (Bad Request)} if the margenNewoBlog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/margen-newo-blogs")
    public ResponseEntity<MargenNewoBlog> createMargenNewoBlog(@RequestBody MargenNewoBlog margenNewoBlog) throws URISyntaxException {
        log.debug("REST request to save MargenNewoBlog : {}", margenNewoBlog);
        if (margenNewoBlog.getId() != null) {
            throw new BadRequestAlertException("A new margenNewoBlog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MargenNewoBlog result = margenNewoBlogRepository.save(margenNewoBlog);
        return ResponseEntity.created(new URI("/api/margen-newo-blogs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /margen-newo-blogs} : Updates an existing margenNewoBlog.
     *
     * @param margenNewoBlog the margenNewoBlog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated margenNewoBlog,
     * or with status {@code 400 (Bad Request)} if the margenNewoBlog is not valid,
     * or with status {@code 500 (Internal Server Error)} if the margenNewoBlog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/margen-newo-blogs")
    public ResponseEntity<MargenNewoBlog> updateMargenNewoBlog(@RequestBody MargenNewoBlog margenNewoBlog) throws URISyntaxException {
        log.debug("REST request to update MargenNewoBlog : {}", margenNewoBlog);
        if (margenNewoBlog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MargenNewoBlog result = margenNewoBlogRepository.save(margenNewoBlog);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, margenNewoBlog.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /margen-newo-blogs} : get all the margenNewoBlogs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of margenNewoBlogs in body.
     */
    @GetMapping("/margen-newo-blogs")
    public List<MargenNewoBlog> getAllMargenNewoBlogs() {
        log.debug("REST request to get all MargenNewoBlogs");
        return margenNewoBlogRepository.findAll();
    }

    /**
     * {@code GET  /margen-newo-blogs/:id} : get the "id" margenNewoBlog.
     *
     * @param id the id of the margenNewoBlog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the margenNewoBlog, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/margen-newo-blogs/{id}")
    public ResponseEntity<MargenNewoBlog> getMargenNewoBlog(@PathVariable Long id) {
        log.debug("REST request to get MargenNewoBlog : {}", id);
        Optional<MargenNewoBlog> margenNewoBlog = margenNewoBlogRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(margenNewoBlog);
    }

    /**
     * {@code DELETE  /margen-newo-blogs/:id} : delete the "id" margenNewoBlog.
     *
     * @param id the id of the margenNewoBlog to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/margen-newo-blogs/{id}")
    public ResponseEntity<Void> deleteMargenNewoBlog(@PathVariable Long id) {
        log.debug("REST request to delete MargenNewoBlog : {}", id);
        margenNewoBlogRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
