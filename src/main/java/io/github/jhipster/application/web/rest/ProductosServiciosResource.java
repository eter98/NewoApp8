package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.ProductosServicios;
import io.github.jhipster.application.repository.ProductosServiciosRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.ProductosServicios}.
 */
@RestController
@RequestMapping("/api")
public class ProductosServiciosResource {

    private final Logger log = LoggerFactory.getLogger(ProductosServiciosResource.class);

    private static final String ENTITY_NAME = "productosServicios";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductosServiciosRepository productosServiciosRepository;

    public ProductosServiciosResource(ProductosServiciosRepository productosServiciosRepository) {
        this.productosServiciosRepository = productosServiciosRepository;
    }

    /**
     * {@code POST  /productos-servicios} : Create a new productosServicios.
     *
     * @param productosServicios the productosServicios to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productosServicios, or with status {@code 400 (Bad Request)} if the productosServicios has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/productos-servicios")
    public ResponseEntity<ProductosServicios> createProductosServicios(@RequestBody ProductosServicios productosServicios) throws URISyntaxException {
        log.debug("REST request to save ProductosServicios : {}", productosServicios);
        if (productosServicios.getId() != null) {
            throw new BadRequestAlertException("A new productosServicios cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductosServicios result = productosServiciosRepository.save(productosServicios);
        return ResponseEntity.created(new URI("/api/productos-servicios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /productos-servicios} : Updates an existing productosServicios.
     *
     * @param productosServicios the productosServicios to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productosServicios,
     * or with status {@code 400 (Bad Request)} if the productosServicios is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productosServicios couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/productos-servicios")
    public ResponseEntity<ProductosServicios> updateProductosServicios(@RequestBody ProductosServicios productosServicios) throws URISyntaxException {
        log.debug("REST request to update ProductosServicios : {}", productosServicios);
        if (productosServicios.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductosServicios result = productosServiciosRepository.save(productosServicios);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productosServicios.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /productos-servicios} : get all the productosServicios.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productosServicios in body.
     */
    @GetMapping("/productos-servicios")
    public List<ProductosServicios> getAllProductosServicios() {
        log.debug("REST request to get all ProductosServicios");
        return productosServiciosRepository.findAll();
    }

    /**
     * {@code GET  /productos-servicios/:id} : get the "id" productosServicios.
     *
     * @param id the id of the productosServicios to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productosServicios, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/productos-servicios/{id}")
    public ResponseEntity<ProductosServicios> getProductosServicios(@PathVariable Long id) {
        log.debug("REST request to get ProductosServicios : {}", id);
        Optional<ProductosServicios> productosServicios = productosServiciosRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(productosServicios);
    }

    /**
     * {@code DELETE  /productos-servicios/:id} : delete the "id" productosServicios.
     *
     * @param id the id of the productosServicios to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/productos-servicios/{id}")
    public ResponseEntity<Void> deleteProductosServicios(@PathVariable Long id) {
        log.debug("REST request to delete ProductosServicios : {}", id);
        productosServiciosRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
