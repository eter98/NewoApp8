package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.PrepagoConsumo;
import io.github.jhipster.application.repository.PrepagoConsumoRepository;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link PrepagoConsumoResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class PrepagoConsumoResourceIT {

    private static final Integer DEFAULT_APORTE = 1;
    private static final Integer UPDATED_APORTE = 2;

    private static final Integer DEFAULT_SALDO_ACTUAL = 1;
    private static final Integer UPDATED_SALDO_ACTUAL = 2;

    private static final LocalDate DEFAULT_FECHA_REGISTRO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_REGISTRO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FECHA_SALDO_ACTUAL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_SALDO_ACTUAL = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private PrepagoConsumoRepository prepagoConsumoRepository;

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

    private MockMvc restPrepagoConsumoMockMvc;

    private PrepagoConsumo prepagoConsumo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PrepagoConsumoResource prepagoConsumoResource = new PrepagoConsumoResource(prepagoConsumoRepository);
        this.restPrepagoConsumoMockMvc = MockMvcBuilders.standaloneSetup(prepagoConsumoResource)
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
    public static PrepagoConsumo createEntity(EntityManager em) {
        PrepagoConsumo prepagoConsumo = new PrepagoConsumo()
            .aporte(DEFAULT_APORTE)
            .saldoActual(DEFAULT_SALDO_ACTUAL)
            .fechaRegistro(DEFAULT_FECHA_REGISTRO)
            .fechaSaldoActual(DEFAULT_FECHA_SALDO_ACTUAL);
        return prepagoConsumo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrepagoConsumo createUpdatedEntity(EntityManager em) {
        PrepagoConsumo prepagoConsumo = new PrepagoConsumo()
            .aporte(UPDATED_APORTE)
            .saldoActual(UPDATED_SALDO_ACTUAL)
            .fechaRegistro(UPDATED_FECHA_REGISTRO)
            .fechaSaldoActual(UPDATED_FECHA_SALDO_ACTUAL);
        return prepagoConsumo;
    }

    @BeforeEach
    public void initTest() {
        prepagoConsumo = createEntity(em);
    }

    @Test
    @Transactional
    public void createPrepagoConsumo() throws Exception {
        int databaseSizeBeforeCreate = prepagoConsumoRepository.findAll().size();

        // Create the PrepagoConsumo
        restPrepagoConsumoMockMvc.perform(post("/api/prepago-consumos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prepagoConsumo)))
            .andExpect(status().isCreated());

        // Validate the PrepagoConsumo in the database
        List<PrepagoConsumo> prepagoConsumoList = prepagoConsumoRepository.findAll();
        assertThat(prepagoConsumoList).hasSize(databaseSizeBeforeCreate + 1);
        PrepagoConsumo testPrepagoConsumo = prepagoConsumoList.get(prepagoConsumoList.size() - 1);
        assertThat(testPrepagoConsumo.getAporte()).isEqualTo(DEFAULT_APORTE);
        assertThat(testPrepagoConsumo.getSaldoActual()).isEqualTo(DEFAULT_SALDO_ACTUAL);
        assertThat(testPrepagoConsumo.getFechaRegistro()).isEqualTo(DEFAULT_FECHA_REGISTRO);
        assertThat(testPrepagoConsumo.getFechaSaldoActual()).isEqualTo(DEFAULT_FECHA_SALDO_ACTUAL);
    }

    @Test
    @Transactional
    public void createPrepagoConsumoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = prepagoConsumoRepository.findAll().size();

        // Create the PrepagoConsumo with an existing ID
        prepagoConsumo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrepagoConsumoMockMvc.perform(post("/api/prepago-consumos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prepagoConsumo)))
            .andExpect(status().isBadRequest());

        // Validate the PrepagoConsumo in the database
        List<PrepagoConsumo> prepagoConsumoList = prepagoConsumoRepository.findAll();
        assertThat(prepagoConsumoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPrepagoConsumos() throws Exception {
        // Initialize the database
        prepagoConsumoRepository.saveAndFlush(prepagoConsumo);

        // Get all the prepagoConsumoList
        restPrepagoConsumoMockMvc.perform(get("/api/prepago-consumos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(prepagoConsumo.getId().intValue())))
            .andExpect(jsonPath("$.[*].aporte").value(hasItem(DEFAULT_APORTE)))
            .andExpect(jsonPath("$.[*].saldoActual").value(hasItem(DEFAULT_SALDO_ACTUAL)))
            .andExpect(jsonPath("$.[*].fechaRegistro").value(hasItem(DEFAULT_FECHA_REGISTRO.toString())))
            .andExpect(jsonPath("$.[*].fechaSaldoActual").value(hasItem(DEFAULT_FECHA_SALDO_ACTUAL.toString())));
    }
    
    @Test
    @Transactional
    public void getPrepagoConsumo() throws Exception {
        // Initialize the database
        prepagoConsumoRepository.saveAndFlush(prepagoConsumo);

        // Get the prepagoConsumo
        restPrepagoConsumoMockMvc.perform(get("/api/prepago-consumos/{id}", prepagoConsumo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(prepagoConsumo.getId().intValue()))
            .andExpect(jsonPath("$.aporte").value(DEFAULT_APORTE))
            .andExpect(jsonPath("$.saldoActual").value(DEFAULT_SALDO_ACTUAL))
            .andExpect(jsonPath("$.fechaRegistro").value(DEFAULT_FECHA_REGISTRO.toString()))
            .andExpect(jsonPath("$.fechaSaldoActual").value(DEFAULT_FECHA_SALDO_ACTUAL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPrepagoConsumo() throws Exception {
        // Get the prepagoConsumo
        restPrepagoConsumoMockMvc.perform(get("/api/prepago-consumos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrepagoConsumo() throws Exception {
        // Initialize the database
        prepagoConsumoRepository.saveAndFlush(prepagoConsumo);

        int databaseSizeBeforeUpdate = prepagoConsumoRepository.findAll().size();

        // Update the prepagoConsumo
        PrepagoConsumo updatedPrepagoConsumo = prepagoConsumoRepository.findById(prepagoConsumo.getId()).get();
        // Disconnect from session so that the updates on updatedPrepagoConsumo are not directly saved in db
        em.detach(updatedPrepagoConsumo);
        updatedPrepagoConsumo
            .aporte(UPDATED_APORTE)
            .saldoActual(UPDATED_SALDO_ACTUAL)
            .fechaRegistro(UPDATED_FECHA_REGISTRO)
            .fechaSaldoActual(UPDATED_FECHA_SALDO_ACTUAL);

        restPrepagoConsumoMockMvc.perform(put("/api/prepago-consumos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPrepagoConsumo)))
            .andExpect(status().isOk());

        // Validate the PrepagoConsumo in the database
        List<PrepagoConsumo> prepagoConsumoList = prepagoConsumoRepository.findAll();
        assertThat(prepagoConsumoList).hasSize(databaseSizeBeforeUpdate);
        PrepagoConsumo testPrepagoConsumo = prepagoConsumoList.get(prepagoConsumoList.size() - 1);
        assertThat(testPrepagoConsumo.getAporte()).isEqualTo(UPDATED_APORTE);
        assertThat(testPrepagoConsumo.getSaldoActual()).isEqualTo(UPDATED_SALDO_ACTUAL);
        assertThat(testPrepagoConsumo.getFechaRegistro()).isEqualTo(UPDATED_FECHA_REGISTRO);
        assertThat(testPrepagoConsumo.getFechaSaldoActual()).isEqualTo(UPDATED_FECHA_SALDO_ACTUAL);
    }

    @Test
    @Transactional
    public void updateNonExistingPrepagoConsumo() throws Exception {
        int databaseSizeBeforeUpdate = prepagoConsumoRepository.findAll().size();

        // Create the PrepagoConsumo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrepagoConsumoMockMvc.perform(put("/api/prepago-consumos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prepagoConsumo)))
            .andExpect(status().isBadRequest());

        // Validate the PrepagoConsumo in the database
        List<PrepagoConsumo> prepagoConsumoList = prepagoConsumoRepository.findAll();
        assertThat(prepagoConsumoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePrepagoConsumo() throws Exception {
        // Initialize the database
        prepagoConsumoRepository.saveAndFlush(prepagoConsumo);

        int databaseSizeBeforeDelete = prepagoConsumoRepository.findAll().size();

        // Delete the prepagoConsumo
        restPrepagoConsumoMockMvc.perform(delete("/api/prepago-consumos/{id}", prepagoConsumo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PrepagoConsumo> prepagoConsumoList = prepagoConsumoRepository.findAll();
        assertThat(prepagoConsumoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrepagoConsumo.class);
        PrepagoConsumo prepagoConsumo1 = new PrepagoConsumo();
        prepagoConsumo1.setId(1L);
        PrepagoConsumo prepagoConsumo2 = new PrepagoConsumo();
        prepagoConsumo2.setId(prepagoConsumo1.getId());
        assertThat(prepagoConsumo1).isEqualTo(prepagoConsumo2);
        prepagoConsumo2.setId(2L);
        assertThat(prepagoConsumo1).isNotEqualTo(prepagoConsumo2);
        prepagoConsumo1.setId(null);
        assertThat(prepagoConsumo1).isNotEqualTo(prepagoConsumo2);
    }
}
