package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.ConsumoMarket;
import io.github.jhipster.application.repository.ConsumoMarketRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.ConsumoMarket}.
 */
@RestController
@RequestMapping("/api")
public class ConsumoMarketResource {

    private final Logger log = LoggerFactory.getLogger(ConsumoMarketResource.class);

    private static final String ENTITY_NAME = "consumoMarket";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ConsumoMarketRepository consumoMarketRepository;

    public ConsumoMarketResource(ConsumoMarketRepository consumoMarketRepository) {
        this.consumoMarketRepository = consumoMarketRepository;
    }

    /**
     * {@code POST  /consumo-markets} : Create a new consumoMarket.
     *
     * @param consumoMarket the consumoMarket to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new consumoMarket, or with status {@code 400 (Bad Request)} if the consumoMarket has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/consumo-markets")
    public ResponseEntity<ConsumoMarket> createConsumoMarket(@RequestBody ConsumoMarket consumoMarket) throws URISyntaxException {
        log.debug("REST request to save ConsumoMarket : {}", consumoMarket);
        if (consumoMarket.getId() != null) {
            throw new BadRequestAlertException("A new consumoMarket cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConsumoMarket result = consumoMarketRepository.save(consumoMarket);
        return ResponseEntity.created(new URI("/api/consumo-markets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /consumo-markets} : Updates an existing consumoMarket.
     *
     * @param consumoMarket the consumoMarket to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated consumoMarket,
     * or with status {@code 400 (Bad Request)} if the consumoMarket is not valid,
     * or with status {@code 500 (Internal Server Error)} if the consumoMarket couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/consumo-markets")
    public ResponseEntity<ConsumoMarket> updateConsumoMarket(@RequestBody ConsumoMarket consumoMarket) throws URISyntaxException {
        log.debug("REST request to update ConsumoMarket : {}", consumoMarket);
        if (consumoMarket.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ConsumoMarket result = consumoMarketRepository.save(consumoMarket);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, consumoMarket.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /consumo-markets} : get all the consumoMarkets.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of consumoMarkets in body.
     */
    @GetMapping("/consumo-markets")
    public List<ConsumoMarket> getAllConsumoMarkets() {
        log.debug("REST request to get all ConsumoMarkets");
        return consumoMarketRepository.findAll();
    }

    /**
     * {@code GET  /consumo-markets/:id} : get the "id" consumoMarket.
     *
     * @param id the id of the consumoMarket to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the consumoMarket, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/consumo-markets/{id}")
    public ResponseEntity<ConsumoMarket> getConsumoMarket(@PathVariable Long id) {
        log.debug("REST request to get ConsumoMarket : {}", id);
        Optional<ConsumoMarket> consumoMarket = consumoMarketRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(consumoMarket);
    }

    /**
     * {@code DELETE  /consumo-markets/:id} : delete the "id" consumoMarket.
     *
     * @param id the id of the consumoMarket to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/consumo-markets/{id}")
    public ResponseEntity<Void> deleteConsumoMarket(@PathVariable Long id) {
        log.debug("REST request to delete ConsumoMarket : {}", id);
        consumoMarketRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
