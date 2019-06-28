package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.Facturacion;
import io.github.jhipster.application.repository.FacturacionRepository;
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

import io.github.jhipster.application.domain.enumeration.TipoPersonad;
import io.github.jhipster.application.domain.enumeration.PeriodicidadFacturaciond;
/**
 * Integration tests for the {@Link FacturacionResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class FacturacionResourceIT {

    private static final String DEFAULT_TITULAR_FACTURA = "AAAAAAAAAA";
    private static final String UPDATED_TITULAR_FACTURA = "BBBBBBBBBB";

    private static final TipoPersonad DEFAULT_TIPO_PERSONA = TipoPersonad.NATURAL;
    private static final TipoPersonad UPDATED_TIPO_PERSONA = TipoPersonad.JURIDICA;

    private static final PeriodicidadFacturaciond DEFAULT_PERIODICIDAD_FACTURACION = PeriodicidadFacturaciond.SEMANAL;
    private static final PeriodicidadFacturaciond UPDATED_PERIODICIDAD_FACTURACION = PeriodicidadFacturaciond.QUINCENAL;

    private static final Integer DEFAULT_MAXIMO_MONTO = 1;
    private static final Integer UPDATED_MAXIMO_MONTO = 2;

    @Autowired
    private FacturacionRepository facturacionRepository;

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

    private MockMvc restFacturacionMockMvc;

    private Facturacion facturacion;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FacturacionResource facturacionResource = new FacturacionResource(facturacionRepository);
        this.restFacturacionMockMvc = MockMvcBuilders.standaloneSetup(facturacionResource)
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
    public static Facturacion createEntity(EntityManager em) {
        Facturacion facturacion = new Facturacion()
            .titularFactura(DEFAULT_TITULAR_FACTURA)
            .tipoPersona(DEFAULT_TIPO_PERSONA)
            .periodicidadFacturacion(DEFAULT_PERIODICIDAD_FACTURACION)
            .maximoMonto(DEFAULT_MAXIMO_MONTO);
        return facturacion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Facturacion createUpdatedEntity(EntityManager em) {
        Facturacion facturacion = new Facturacion()
            .titularFactura(UPDATED_TITULAR_FACTURA)
            .tipoPersona(UPDATED_TIPO_PERSONA)
            .periodicidadFacturacion(UPDATED_PERIODICIDAD_FACTURACION)
            .maximoMonto(UPDATED_MAXIMO_MONTO);
        return facturacion;
    }

    @BeforeEach
    public void initTest() {
        facturacion = createEntity(em);
    }

    @Test
    @Transactional
    public void createFacturacion() throws Exception {
        int databaseSizeBeforeCreate = facturacionRepository.findAll().size();

        // Create the Facturacion
        restFacturacionMockMvc.perform(post("/api/facturacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facturacion)))
            .andExpect(status().isCreated());

        // Validate the Facturacion in the database
        List<Facturacion> facturacionList = facturacionRepository.findAll();
        assertThat(facturacionList).hasSize(databaseSizeBeforeCreate + 1);
        Facturacion testFacturacion = facturacionList.get(facturacionList.size() - 1);
        assertThat(testFacturacion.getTitularFactura()).isEqualTo(DEFAULT_TITULAR_FACTURA);
        assertThat(testFacturacion.getTipoPersona()).isEqualTo(DEFAULT_TIPO_PERSONA);
        assertThat(testFacturacion.getPeriodicidadFacturacion()).isEqualTo(DEFAULT_PERIODICIDAD_FACTURACION);
        assertThat(testFacturacion.getMaximoMonto()).isEqualTo(DEFAULT_MAXIMO_MONTO);
    }

    @Test
    @Transactional
    public void createFacturacionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = facturacionRepository.findAll().size();

        // Create the Facturacion with an existing ID
        facturacion.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFacturacionMockMvc.perform(post("/api/facturacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facturacion)))
            .andExpect(status().isBadRequest());

        // Validate the Facturacion in the database
        List<Facturacion> facturacionList = facturacionRepository.findAll();
        assertThat(facturacionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFacturacions() throws Exception {
        // Initialize the database
        facturacionRepository.saveAndFlush(facturacion);

        // Get all the facturacionList
        restFacturacionMockMvc.perform(get("/api/facturacions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(facturacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].titularFactura").value(hasItem(DEFAULT_TITULAR_FACTURA.toString())))
            .andExpect(jsonPath("$.[*].tipoPersona").value(hasItem(DEFAULT_TIPO_PERSONA.toString())))
            .andExpect(jsonPath("$.[*].periodicidadFacturacion").value(hasItem(DEFAULT_PERIODICIDAD_FACTURACION.toString())))
            .andExpect(jsonPath("$.[*].maximoMonto").value(hasItem(DEFAULT_MAXIMO_MONTO)));
    }
    
    @Test
    @Transactional
    public void getFacturacion() throws Exception {
        // Initialize the database
        facturacionRepository.saveAndFlush(facturacion);

        // Get the facturacion
        restFacturacionMockMvc.perform(get("/api/facturacions/{id}", facturacion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(facturacion.getId().intValue()))
            .andExpect(jsonPath("$.titularFactura").value(DEFAULT_TITULAR_FACTURA.toString()))
            .andExpect(jsonPath("$.tipoPersona").value(DEFAULT_TIPO_PERSONA.toString()))
            .andExpect(jsonPath("$.periodicidadFacturacion").value(DEFAULT_PERIODICIDAD_FACTURACION.toString()))
            .andExpect(jsonPath("$.maximoMonto").value(DEFAULT_MAXIMO_MONTO));
    }

    @Test
    @Transactional
    public void getNonExistingFacturacion() throws Exception {
        // Get the facturacion
        restFacturacionMockMvc.perform(get("/api/facturacions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFacturacion() throws Exception {
        // Initialize the database
        facturacionRepository.saveAndFlush(facturacion);

        int databaseSizeBeforeUpdate = facturacionRepository.findAll().size();

        // Update the facturacion
        Facturacion updatedFacturacion = facturacionRepository.findById(facturacion.getId()).get();
        // Disconnect from session so that the updates on updatedFacturacion are not directly saved in db
        em.detach(updatedFacturacion);
        updatedFacturacion
            .titularFactura(UPDATED_TITULAR_FACTURA)
            .tipoPersona(UPDATED_TIPO_PERSONA)
            .periodicidadFacturacion(UPDATED_PERIODICIDAD_FACTURACION)
            .maximoMonto(UPDATED_MAXIMO_MONTO);

        restFacturacionMockMvc.perform(put("/api/facturacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFacturacion)))
            .andExpect(status().isOk());

        // Validate the Facturacion in the database
        List<Facturacion> facturacionList = facturacionRepository.findAll();
        assertThat(facturacionList).hasSize(databaseSizeBeforeUpdate);
        Facturacion testFacturacion = facturacionList.get(facturacionList.size() - 1);
        assertThat(testFacturacion.getTitularFactura()).isEqualTo(UPDATED_TITULAR_FACTURA);
        assertThat(testFacturacion.getTipoPersona()).isEqualTo(UPDATED_TIPO_PERSONA);
        assertThat(testFacturacion.getPeriodicidadFacturacion()).isEqualTo(UPDATED_PERIODICIDAD_FACTURACION);
        assertThat(testFacturacion.getMaximoMonto()).isEqualTo(UPDATED_MAXIMO_MONTO);
    }

    @Test
    @Transactional
    public void updateNonExistingFacturacion() throws Exception {
        int databaseSizeBeforeUpdate = facturacionRepository.findAll().size();

        // Create the Facturacion

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFacturacionMockMvc.perform(put("/api/facturacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facturacion)))
            .andExpect(status().isBadRequest());

        // Validate the Facturacion in the database
        List<Facturacion> facturacionList = facturacionRepository.findAll();
        assertThat(facturacionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFacturacion() throws Exception {
        // Initialize the database
        facturacionRepository.saveAndFlush(facturacion);

        int databaseSizeBeforeDelete = facturacionRepository.findAll().size();

        // Delete the facturacion
        restFacturacionMockMvc.perform(delete("/api/facturacions/{id}", facturacion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Facturacion> facturacionList = facturacionRepository.findAll();
        assertThat(facturacionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Facturacion.class);
        Facturacion facturacion1 = new Facturacion();
        facturacion1.setId(1L);
        Facturacion facturacion2 = new Facturacion();
        facturacion2.setId(facturacion1.getId());
        assertThat(facturacion1).isEqualTo(facturacion2);
        facturacion2.setId(2L);
        assertThat(facturacion1).isNotEqualTo(facturacion2);
        facturacion1.setId(null);
        assertThat(facturacion1).isNotEqualTo(facturacion2);
    }
}
