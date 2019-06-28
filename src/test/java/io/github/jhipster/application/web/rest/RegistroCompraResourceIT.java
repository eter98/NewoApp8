package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.RegistroCompra;
import io.github.jhipster.application.repository.RegistroCompraRepository;
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
 * Integration tests for the {@Link RegistroCompraResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class RegistroCompraResourceIT {

    private static final Boolean DEFAULT_CONSUMO_MARKET = false;
    private static final Boolean UPDATED_CONSUMO_MARKET = true;

    @Autowired
    private RegistroCompraRepository registroCompraRepository;

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

    private MockMvc restRegistroCompraMockMvc;

    private RegistroCompra registroCompra;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RegistroCompraResource registroCompraResource = new RegistroCompraResource(registroCompraRepository);
        this.restRegistroCompraMockMvc = MockMvcBuilders.standaloneSetup(registroCompraResource)
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
    public static RegistroCompra createEntity(EntityManager em) {
        RegistroCompra registroCompra = new RegistroCompra()
            .consumoMarket(DEFAULT_CONSUMO_MARKET);
        return registroCompra;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RegistroCompra createUpdatedEntity(EntityManager em) {
        RegistroCompra registroCompra = new RegistroCompra()
            .consumoMarket(UPDATED_CONSUMO_MARKET);
        return registroCompra;
    }

    @BeforeEach
    public void initTest() {
        registroCompra = createEntity(em);
    }

    @Test
    @Transactional
    public void createRegistroCompra() throws Exception {
        int databaseSizeBeforeCreate = registroCompraRepository.findAll().size();

        // Create the RegistroCompra
        restRegistroCompraMockMvc.perform(post("/api/registro-compras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registroCompra)))
            .andExpect(status().isCreated());

        // Validate the RegistroCompra in the database
        List<RegistroCompra> registroCompraList = registroCompraRepository.findAll();
        assertThat(registroCompraList).hasSize(databaseSizeBeforeCreate + 1);
        RegistroCompra testRegistroCompra = registroCompraList.get(registroCompraList.size() - 1);
        assertThat(testRegistroCompra.isConsumoMarket()).isEqualTo(DEFAULT_CONSUMO_MARKET);
    }

    @Test
    @Transactional
    public void createRegistroCompraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = registroCompraRepository.findAll().size();

        // Create the RegistroCompra with an existing ID
        registroCompra.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRegistroCompraMockMvc.perform(post("/api/registro-compras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registroCompra)))
            .andExpect(status().isBadRequest());

        // Validate the RegistroCompra in the database
        List<RegistroCompra> registroCompraList = registroCompraRepository.findAll();
        assertThat(registroCompraList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRegistroCompras() throws Exception {
        // Initialize the database
        registroCompraRepository.saveAndFlush(registroCompra);

        // Get all the registroCompraList
        restRegistroCompraMockMvc.perform(get("/api/registro-compras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(registroCompra.getId().intValue())))
            .andExpect(jsonPath("$.[*].consumoMarket").value(hasItem(DEFAULT_CONSUMO_MARKET.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getRegistroCompra() throws Exception {
        // Initialize the database
        registroCompraRepository.saveAndFlush(registroCompra);

        // Get the registroCompra
        restRegistroCompraMockMvc.perform(get("/api/registro-compras/{id}", registroCompra.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(registroCompra.getId().intValue()))
            .andExpect(jsonPath("$.consumoMarket").value(DEFAULT_CONSUMO_MARKET.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingRegistroCompra() throws Exception {
        // Get the registroCompra
        restRegistroCompraMockMvc.perform(get("/api/registro-compras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRegistroCompra() throws Exception {
        // Initialize the database
        registroCompraRepository.saveAndFlush(registroCompra);

        int databaseSizeBeforeUpdate = registroCompraRepository.findAll().size();

        // Update the registroCompra
        RegistroCompra updatedRegistroCompra = registroCompraRepository.findById(registroCompra.getId()).get();
        // Disconnect from session so that the updates on updatedRegistroCompra are not directly saved in db
        em.detach(updatedRegistroCompra);
        updatedRegistroCompra
            .consumoMarket(UPDATED_CONSUMO_MARKET);

        restRegistroCompraMockMvc.perform(put("/api/registro-compras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRegistroCompra)))
            .andExpect(status().isOk());

        // Validate the RegistroCompra in the database
        List<RegistroCompra> registroCompraList = registroCompraRepository.findAll();
        assertThat(registroCompraList).hasSize(databaseSizeBeforeUpdate);
        RegistroCompra testRegistroCompra = registroCompraList.get(registroCompraList.size() - 1);
        assertThat(testRegistroCompra.isConsumoMarket()).isEqualTo(UPDATED_CONSUMO_MARKET);
    }

    @Test
    @Transactional
    public void updateNonExistingRegistroCompra() throws Exception {
        int databaseSizeBeforeUpdate = registroCompraRepository.findAll().size();

        // Create the RegistroCompra

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRegistroCompraMockMvc.perform(put("/api/registro-compras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registroCompra)))
            .andExpect(status().isBadRequest());

        // Validate the RegistroCompra in the database
        List<RegistroCompra> registroCompraList = registroCompraRepository.findAll();
        assertThat(registroCompraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRegistroCompra() throws Exception {
        // Initialize the database
        registroCompraRepository.saveAndFlush(registroCompra);

        int databaseSizeBeforeDelete = registroCompraRepository.findAll().size();

        // Delete the registroCompra
        restRegistroCompraMockMvc.perform(delete("/api/registro-compras/{id}", registroCompra.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RegistroCompra> registroCompraList = registroCompraRepository.findAll();
        assertThat(registroCompraList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RegistroCompra.class);
        RegistroCompra registroCompra1 = new RegistroCompra();
        registroCompra1.setId(1L);
        RegistroCompra registroCompra2 = new RegistroCompra();
        registroCompra2.setId(registroCompra1.getId());
        assertThat(registroCompra1).isEqualTo(registroCompra2);
        registroCompra2.setId(2L);
        assertThat(registroCompra1).isNotEqualTo(registroCompra2);
        registroCompra1.setId(null);
        assertThat(registroCompra1).isNotEqualTo(registroCompra2);
    }
}
