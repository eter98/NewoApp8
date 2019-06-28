package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.MargenNewoProductos;
import io.github.jhipster.application.repository.MargenNewoProductosRepository;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link MargenNewoProductosResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class MargenNewoProductosResourceIT {

    private static final Integer DEFAULT_PORCENTAJE_MARGEN = 1;
    private static final Integer UPDATED_PORCENTAJE_MARGEN = 2;

    @Autowired
    private MargenNewoProductosRepository margenNewoProductosRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restMargenNewoProductosMockMvc;

    private MargenNewoProductos margenNewoProductos;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MargenNewoProductosResource margenNewoProductosResource = new MargenNewoProductosResource(margenNewoProductosRepository);
        this.restMargenNewoProductosMockMvc = MockMvcBuilders.standaloneSetup(margenNewoProductosResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MargenNewoProductos createEntity(EntityManager em) {
        MargenNewoProductos margenNewoProductos = new MargenNewoProductos()
            .porcentajeMargen(DEFAULT_PORCENTAJE_MARGEN);
        return margenNewoProductos;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MargenNewoProductos createUpdatedEntity(EntityManager em) {
        MargenNewoProductos margenNewoProductos = new MargenNewoProductos()
            .porcentajeMargen(UPDATED_PORCENTAJE_MARGEN);
        return margenNewoProductos;
    }

    @BeforeEach
    public void initTest() {
        margenNewoProductos = createEntity(em);
    }

    @Test
    @Transactional
    public void createMargenNewoProductos() throws Exception {
        int databaseSizeBeforeCreate = margenNewoProductosRepository.findAll().size();

        // Create the MargenNewoProductos
        restMargenNewoProductosMockMvc.perform(post("/api/margen-newo-productos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(margenNewoProductos)))
            .andExpect(status().isCreated());

        // Validate the MargenNewoProductos in the database
        List<MargenNewoProductos> margenNewoProductosList = margenNewoProductosRepository.findAll();
        assertThat(margenNewoProductosList).hasSize(databaseSizeBeforeCreate + 1);
        MargenNewoProductos testMargenNewoProductos = margenNewoProductosList.get(margenNewoProductosList.size() - 1);
        assertThat(testMargenNewoProductos.getPorcentajeMargen()).isEqualTo(DEFAULT_PORCENTAJE_MARGEN);
    }

    @Test
    @Transactional
    public void createMargenNewoProductosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = margenNewoProductosRepository.findAll().size();

        // Create the MargenNewoProductos with an existing ID
        margenNewoProductos.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMargenNewoProductosMockMvc.perform(post("/api/margen-newo-productos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(margenNewoProductos)))
            .andExpect(status().isBadRequest());

        // Validate the MargenNewoProductos in the database
        List<MargenNewoProductos> margenNewoProductosList = margenNewoProductosRepository.findAll();
        assertThat(margenNewoProductosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMargenNewoProductos() throws Exception {
        // Initialize the database
        margenNewoProductosRepository.saveAndFlush(margenNewoProductos);

        // Get all the margenNewoProductosList
        restMargenNewoProductosMockMvc.perform(get("/api/margen-newo-productos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(margenNewoProductos.getId().intValue())))
            .andExpect(jsonPath("$.[*].porcentajeMargen").value(hasItem(DEFAULT_PORCENTAJE_MARGEN)));
    }
    
    @Test
    @Transactional
    public void getMargenNewoProductos() throws Exception {
        // Initialize the database
        margenNewoProductosRepository.saveAndFlush(margenNewoProductos);

        // Get the margenNewoProductos
        restMargenNewoProductosMockMvc.perform(get("/api/margen-newo-productos/{id}", margenNewoProductos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(margenNewoProductos.getId().intValue()))
            .andExpect(jsonPath("$.porcentajeMargen").value(DEFAULT_PORCENTAJE_MARGEN));
    }

    @Test
    @Transactional
    public void getNonExistingMargenNewoProductos() throws Exception {
        // Get the margenNewoProductos
        restMargenNewoProductosMockMvc.perform(get("/api/margen-newo-productos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMargenNewoProductos() throws Exception {
        // Initialize the database
        margenNewoProductosRepository.saveAndFlush(margenNewoProductos);

        int databaseSizeBeforeUpdate = margenNewoProductosRepository.findAll().size();

        // Update the margenNewoProductos
        MargenNewoProductos updatedMargenNewoProductos = margenNewoProductosRepository.findById(margenNewoProductos.getId()).get();
        // Disconnect from session so that the updates on updatedMargenNewoProductos are not directly saved in db
        em.detach(updatedMargenNewoProductos);
        updatedMargenNewoProductos
            .porcentajeMargen(UPDATED_PORCENTAJE_MARGEN);

        restMargenNewoProductosMockMvc.perform(put("/api/margen-newo-productos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMargenNewoProductos)))
            .andExpect(status().isOk());

        // Validate the MargenNewoProductos in the database
        List<MargenNewoProductos> margenNewoProductosList = margenNewoProductosRepository.findAll();
        assertThat(margenNewoProductosList).hasSize(databaseSizeBeforeUpdate);
        MargenNewoProductos testMargenNewoProductos = margenNewoProductosList.get(margenNewoProductosList.size() - 1);
        assertThat(testMargenNewoProductos.getPorcentajeMargen()).isEqualTo(UPDATED_PORCENTAJE_MARGEN);
    }

    @Test
    @Transactional
    public void updateNonExistingMargenNewoProductos() throws Exception {
        int databaseSizeBeforeUpdate = margenNewoProductosRepository.findAll().size();

        // Create the MargenNewoProductos

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMargenNewoProductosMockMvc.perform(put("/api/margen-newo-productos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(margenNewoProductos)))
            .andExpect(status().isBadRequest());

        // Validate the MargenNewoProductos in the database
        List<MargenNewoProductos> margenNewoProductosList = margenNewoProductosRepository.findAll();
        assertThat(margenNewoProductosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMargenNewoProductos() throws Exception {
        // Initialize the database
        margenNewoProductosRepository.saveAndFlush(margenNewoProductos);

        int databaseSizeBeforeDelete = margenNewoProductosRepository.findAll().size();

        // Delete the margenNewoProductos
        restMargenNewoProductosMockMvc.perform(delete("/api/margen-newo-productos/{id}", margenNewoProductos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MargenNewoProductos> margenNewoProductosList = margenNewoProductosRepository.findAll();
        assertThat(margenNewoProductosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MargenNewoProductos.class);
        MargenNewoProductos margenNewoProductos1 = new MargenNewoProductos();
        margenNewoProductos1.setId(1L);
        MargenNewoProductos margenNewoProductos2 = new MargenNewoProductos();
        margenNewoProductos2.setId(margenNewoProductos1.getId());
        assertThat(margenNewoProductos1).isEqualTo(margenNewoProductos2);
        margenNewoProductos2.setId(2L);
        assertThat(margenNewoProductos1).isNotEqualTo(margenNewoProductos2);
        margenNewoProductos1.setId(null);
        assertThat(margenNewoProductos1).isNotEqualTo(margenNewoProductos2);
    }
}
