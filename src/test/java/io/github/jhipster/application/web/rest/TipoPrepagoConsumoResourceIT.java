package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.TipoPrepagoConsumo;
import io.github.jhipster.application.repository.TipoPrepagoConsumoRepository;
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
 * Integration tests for the {@Link TipoPrepagoConsumoResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class TipoPrepagoConsumoResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final Integer DEFAULT_VALOR_MINIMO = 1;
    private static final Integer UPDATED_VALOR_MINIMO = 2;

    private static final Integer DEFAULT_VALOR_MAXIMO = 1;
    private static final Integer UPDATED_VALOR_MAXIMO = 2;

    @Autowired
    private TipoPrepagoConsumoRepository tipoPrepagoConsumoRepository;

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

    private MockMvc restTipoPrepagoConsumoMockMvc;

    private TipoPrepagoConsumo tipoPrepagoConsumo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoPrepagoConsumoResource tipoPrepagoConsumoResource = new TipoPrepagoConsumoResource(tipoPrepagoConsumoRepository);
        this.restTipoPrepagoConsumoMockMvc = MockMvcBuilders.standaloneSetup(tipoPrepagoConsumoResource)
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
    public static TipoPrepagoConsumo createEntity(EntityManager em) {
        TipoPrepagoConsumo tipoPrepagoConsumo = new TipoPrepagoConsumo()
            .nombre(DEFAULT_NOMBRE)
            .descripcion(DEFAULT_DESCRIPCION)
            .valorMinimo(DEFAULT_VALOR_MINIMO)
            .valorMaximo(DEFAULT_VALOR_MAXIMO);
        return tipoPrepagoConsumo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoPrepagoConsumo createUpdatedEntity(EntityManager em) {
        TipoPrepagoConsumo tipoPrepagoConsumo = new TipoPrepagoConsumo()
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .valorMinimo(UPDATED_VALOR_MINIMO)
            .valorMaximo(UPDATED_VALOR_MAXIMO);
        return tipoPrepagoConsumo;
    }

    @BeforeEach
    public void initTest() {
        tipoPrepagoConsumo = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoPrepagoConsumo() throws Exception {
        int databaseSizeBeforeCreate = tipoPrepagoConsumoRepository.findAll().size();

        // Create the TipoPrepagoConsumo
        restTipoPrepagoConsumoMockMvc.perform(post("/api/tipo-prepago-consumos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoPrepagoConsumo)))
            .andExpect(status().isCreated());

        // Validate the TipoPrepagoConsumo in the database
        List<TipoPrepagoConsumo> tipoPrepagoConsumoList = tipoPrepagoConsumoRepository.findAll();
        assertThat(tipoPrepagoConsumoList).hasSize(databaseSizeBeforeCreate + 1);
        TipoPrepagoConsumo testTipoPrepagoConsumo = tipoPrepagoConsumoList.get(tipoPrepagoConsumoList.size() - 1);
        assertThat(testTipoPrepagoConsumo.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testTipoPrepagoConsumo.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testTipoPrepagoConsumo.getValorMinimo()).isEqualTo(DEFAULT_VALOR_MINIMO);
        assertThat(testTipoPrepagoConsumo.getValorMaximo()).isEqualTo(DEFAULT_VALOR_MAXIMO);
    }

    @Test
    @Transactional
    public void createTipoPrepagoConsumoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoPrepagoConsumoRepository.findAll().size();

        // Create the TipoPrepagoConsumo with an existing ID
        tipoPrepagoConsumo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoPrepagoConsumoMockMvc.perform(post("/api/tipo-prepago-consumos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoPrepagoConsumo)))
            .andExpect(status().isBadRequest());

        // Validate the TipoPrepagoConsumo in the database
        List<TipoPrepagoConsumo> tipoPrepagoConsumoList = tipoPrepagoConsumoRepository.findAll();
        assertThat(tipoPrepagoConsumoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTipoPrepagoConsumos() throws Exception {
        // Initialize the database
        tipoPrepagoConsumoRepository.saveAndFlush(tipoPrepagoConsumo);

        // Get all the tipoPrepagoConsumoList
        restTipoPrepagoConsumoMockMvc.perform(get("/api/tipo-prepago-consumos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoPrepagoConsumo.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].valorMinimo").value(hasItem(DEFAULT_VALOR_MINIMO)))
            .andExpect(jsonPath("$.[*].valorMaximo").value(hasItem(DEFAULT_VALOR_MAXIMO)));
    }
    
    @Test
    @Transactional
    public void getTipoPrepagoConsumo() throws Exception {
        // Initialize the database
        tipoPrepagoConsumoRepository.saveAndFlush(tipoPrepagoConsumo);

        // Get the tipoPrepagoConsumo
        restTipoPrepagoConsumoMockMvc.perform(get("/api/tipo-prepago-consumos/{id}", tipoPrepagoConsumo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoPrepagoConsumo.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.valorMinimo").value(DEFAULT_VALOR_MINIMO))
            .andExpect(jsonPath("$.valorMaximo").value(DEFAULT_VALOR_MAXIMO));
    }

    @Test
    @Transactional
    public void getNonExistingTipoPrepagoConsumo() throws Exception {
        // Get the tipoPrepagoConsumo
        restTipoPrepagoConsumoMockMvc.perform(get("/api/tipo-prepago-consumos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoPrepagoConsumo() throws Exception {
        // Initialize the database
        tipoPrepagoConsumoRepository.saveAndFlush(tipoPrepagoConsumo);

        int databaseSizeBeforeUpdate = tipoPrepagoConsumoRepository.findAll().size();

        // Update the tipoPrepagoConsumo
        TipoPrepagoConsumo updatedTipoPrepagoConsumo = tipoPrepagoConsumoRepository.findById(tipoPrepagoConsumo.getId()).get();
        // Disconnect from session so that the updates on updatedTipoPrepagoConsumo are not directly saved in db
        em.detach(updatedTipoPrepagoConsumo);
        updatedTipoPrepagoConsumo
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .valorMinimo(UPDATED_VALOR_MINIMO)
            .valorMaximo(UPDATED_VALOR_MAXIMO);

        restTipoPrepagoConsumoMockMvc.perform(put("/api/tipo-prepago-consumos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoPrepagoConsumo)))
            .andExpect(status().isOk());

        // Validate the TipoPrepagoConsumo in the database
        List<TipoPrepagoConsumo> tipoPrepagoConsumoList = tipoPrepagoConsumoRepository.findAll();
        assertThat(tipoPrepagoConsumoList).hasSize(databaseSizeBeforeUpdate);
        TipoPrepagoConsumo testTipoPrepagoConsumo = tipoPrepagoConsumoList.get(tipoPrepagoConsumoList.size() - 1);
        assertThat(testTipoPrepagoConsumo.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testTipoPrepagoConsumo.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testTipoPrepagoConsumo.getValorMinimo()).isEqualTo(UPDATED_VALOR_MINIMO);
        assertThat(testTipoPrepagoConsumo.getValorMaximo()).isEqualTo(UPDATED_VALOR_MAXIMO);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoPrepagoConsumo() throws Exception {
        int databaseSizeBeforeUpdate = tipoPrepagoConsumoRepository.findAll().size();

        // Create the TipoPrepagoConsumo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoPrepagoConsumoMockMvc.perform(put("/api/tipo-prepago-consumos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoPrepagoConsumo)))
            .andExpect(status().isBadRequest());

        // Validate the TipoPrepagoConsumo in the database
        List<TipoPrepagoConsumo> tipoPrepagoConsumoList = tipoPrepagoConsumoRepository.findAll();
        assertThat(tipoPrepagoConsumoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoPrepagoConsumo() throws Exception {
        // Initialize the database
        tipoPrepagoConsumoRepository.saveAndFlush(tipoPrepagoConsumo);

        int databaseSizeBeforeDelete = tipoPrepagoConsumoRepository.findAll().size();

        // Delete the tipoPrepagoConsumo
        restTipoPrepagoConsumoMockMvc.perform(delete("/api/tipo-prepago-consumos/{id}", tipoPrepagoConsumo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TipoPrepagoConsumo> tipoPrepagoConsumoList = tipoPrepagoConsumoRepository.findAll();
        assertThat(tipoPrepagoConsumoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoPrepagoConsumo.class);
        TipoPrepagoConsumo tipoPrepagoConsumo1 = new TipoPrepagoConsumo();
        tipoPrepagoConsumo1.setId(1L);
        TipoPrepagoConsumo tipoPrepagoConsumo2 = new TipoPrepagoConsumo();
        tipoPrepagoConsumo2.setId(tipoPrepagoConsumo1.getId());
        assertThat(tipoPrepagoConsumo1).isEqualTo(tipoPrepagoConsumo2);
        tipoPrepagoConsumo2.setId(2L);
        assertThat(tipoPrepagoConsumo1).isNotEqualTo(tipoPrepagoConsumo2);
        tipoPrepagoConsumo1.setId(null);
        assertThat(tipoPrepagoConsumo1).isNotEqualTo(tipoPrepagoConsumo2);
    }
}
