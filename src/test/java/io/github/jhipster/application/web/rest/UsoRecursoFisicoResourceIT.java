package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.UsoRecursoFisico;
import io.github.jhipster.application.repository.UsoRecursoFisicoRepository;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.sameInstant;
import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.domain.enumeration.TipoIniciod;
/**
 * Integration tests for the {@Link UsoRecursoFisicoResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class UsoRecursoFisicoResourceIT {

    private static final ZonedDateTime DEFAULT_REGISTRO_FECHA_INICIO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_REGISTRO_FECHA_INICIO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_REGISTRO_FECHA_FINAL = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_REGISTRO_FECHA_FINAL = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final TipoIniciod DEFAULT_TIPO_REGISTRO = TipoIniciod.Inicio;
    private static final TipoIniciod UPDATED_TIPO_REGISTRO = TipoIniciod.Fin;

    @Autowired
    private UsoRecursoFisicoRepository usoRecursoFisicoRepository;

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

    private MockMvc restUsoRecursoFisicoMockMvc;

    private UsoRecursoFisico usoRecursoFisico;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UsoRecursoFisicoResource usoRecursoFisicoResource = new UsoRecursoFisicoResource(usoRecursoFisicoRepository);
        this.restUsoRecursoFisicoMockMvc = MockMvcBuilders.standaloneSetup(usoRecursoFisicoResource)
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
    public static UsoRecursoFisico createEntity(EntityManager em) {
        UsoRecursoFisico usoRecursoFisico = new UsoRecursoFisico()
            .registroFechaInicio(DEFAULT_REGISTRO_FECHA_INICIO)
            .registroFechaFinal(DEFAULT_REGISTRO_FECHA_FINAL)
            .tipoRegistro(DEFAULT_TIPO_REGISTRO);
        return usoRecursoFisico;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UsoRecursoFisico createUpdatedEntity(EntityManager em) {
        UsoRecursoFisico usoRecursoFisico = new UsoRecursoFisico()
            .registroFechaInicio(UPDATED_REGISTRO_FECHA_INICIO)
            .registroFechaFinal(UPDATED_REGISTRO_FECHA_FINAL)
            .tipoRegistro(UPDATED_TIPO_REGISTRO);
        return usoRecursoFisico;
    }

    @BeforeEach
    public void initTest() {
        usoRecursoFisico = createEntity(em);
    }

    @Test
    @Transactional
    public void createUsoRecursoFisico() throws Exception {
        int databaseSizeBeforeCreate = usoRecursoFisicoRepository.findAll().size();

        // Create the UsoRecursoFisico
        restUsoRecursoFisicoMockMvc.perform(post("/api/uso-recurso-fisicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usoRecursoFisico)))
            .andExpect(status().isCreated());

        // Validate the UsoRecursoFisico in the database
        List<UsoRecursoFisico> usoRecursoFisicoList = usoRecursoFisicoRepository.findAll();
        assertThat(usoRecursoFisicoList).hasSize(databaseSizeBeforeCreate + 1);
        UsoRecursoFisico testUsoRecursoFisico = usoRecursoFisicoList.get(usoRecursoFisicoList.size() - 1);
        assertThat(testUsoRecursoFisico.getRegistroFechaInicio()).isEqualTo(DEFAULT_REGISTRO_FECHA_INICIO);
        assertThat(testUsoRecursoFisico.getRegistroFechaFinal()).isEqualTo(DEFAULT_REGISTRO_FECHA_FINAL);
        assertThat(testUsoRecursoFisico.getTipoRegistro()).isEqualTo(DEFAULT_TIPO_REGISTRO);
    }

    @Test
    @Transactional
    public void createUsoRecursoFisicoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = usoRecursoFisicoRepository.findAll().size();

        // Create the UsoRecursoFisico with an existing ID
        usoRecursoFisico.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUsoRecursoFisicoMockMvc.perform(post("/api/uso-recurso-fisicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usoRecursoFisico)))
            .andExpect(status().isBadRequest());

        // Validate the UsoRecursoFisico in the database
        List<UsoRecursoFisico> usoRecursoFisicoList = usoRecursoFisicoRepository.findAll();
        assertThat(usoRecursoFisicoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUsoRecursoFisicos() throws Exception {
        // Initialize the database
        usoRecursoFisicoRepository.saveAndFlush(usoRecursoFisico);

        // Get all the usoRecursoFisicoList
        restUsoRecursoFisicoMockMvc.perform(get("/api/uso-recurso-fisicos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(usoRecursoFisico.getId().intValue())))
            .andExpect(jsonPath("$.[*].registroFechaInicio").value(hasItem(sameInstant(DEFAULT_REGISTRO_FECHA_INICIO))))
            .andExpect(jsonPath("$.[*].registroFechaFinal").value(hasItem(sameInstant(DEFAULT_REGISTRO_FECHA_FINAL))))
            .andExpect(jsonPath("$.[*].tipoRegistro").value(hasItem(DEFAULT_TIPO_REGISTRO.toString())));
    }
    
    @Test
    @Transactional
    public void getUsoRecursoFisico() throws Exception {
        // Initialize the database
        usoRecursoFisicoRepository.saveAndFlush(usoRecursoFisico);

        // Get the usoRecursoFisico
        restUsoRecursoFisicoMockMvc.perform(get("/api/uso-recurso-fisicos/{id}", usoRecursoFisico.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(usoRecursoFisico.getId().intValue()))
            .andExpect(jsonPath("$.registroFechaInicio").value(sameInstant(DEFAULT_REGISTRO_FECHA_INICIO)))
            .andExpect(jsonPath("$.registroFechaFinal").value(sameInstant(DEFAULT_REGISTRO_FECHA_FINAL)))
            .andExpect(jsonPath("$.tipoRegistro").value(DEFAULT_TIPO_REGISTRO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUsoRecursoFisico() throws Exception {
        // Get the usoRecursoFisico
        restUsoRecursoFisicoMockMvc.perform(get("/api/uso-recurso-fisicos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUsoRecursoFisico() throws Exception {
        // Initialize the database
        usoRecursoFisicoRepository.saveAndFlush(usoRecursoFisico);

        int databaseSizeBeforeUpdate = usoRecursoFisicoRepository.findAll().size();

        // Update the usoRecursoFisico
        UsoRecursoFisico updatedUsoRecursoFisico = usoRecursoFisicoRepository.findById(usoRecursoFisico.getId()).get();
        // Disconnect from session so that the updates on updatedUsoRecursoFisico are not directly saved in db
        em.detach(updatedUsoRecursoFisico);
        updatedUsoRecursoFisico
            .registroFechaInicio(UPDATED_REGISTRO_FECHA_INICIO)
            .registroFechaFinal(UPDATED_REGISTRO_FECHA_FINAL)
            .tipoRegistro(UPDATED_TIPO_REGISTRO);

        restUsoRecursoFisicoMockMvc.perform(put("/api/uso-recurso-fisicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUsoRecursoFisico)))
            .andExpect(status().isOk());

        // Validate the UsoRecursoFisico in the database
        List<UsoRecursoFisico> usoRecursoFisicoList = usoRecursoFisicoRepository.findAll();
        assertThat(usoRecursoFisicoList).hasSize(databaseSizeBeforeUpdate);
        UsoRecursoFisico testUsoRecursoFisico = usoRecursoFisicoList.get(usoRecursoFisicoList.size() - 1);
        assertThat(testUsoRecursoFisico.getRegistroFechaInicio()).isEqualTo(UPDATED_REGISTRO_FECHA_INICIO);
        assertThat(testUsoRecursoFisico.getRegistroFechaFinal()).isEqualTo(UPDATED_REGISTRO_FECHA_FINAL);
        assertThat(testUsoRecursoFisico.getTipoRegistro()).isEqualTo(UPDATED_TIPO_REGISTRO);
    }

    @Test
    @Transactional
    public void updateNonExistingUsoRecursoFisico() throws Exception {
        int databaseSizeBeforeUpdate = usoRecursoFisicoRepository.findAll().size();

        // Create the UsoRecursoFisico

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUsoRecursoFisicoMockMvc.perform(put("/api/uso-recurso-fisicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usoRecursoFisico)))
            .andExpect(status().isBadRequest());

        // Validate the UsoRecursoFisico in the database
        List<UsoRecursoFisico> usoRecursoFisicoList = usoRecursoFisicoRepository.findAll();
        assertThat(usoRecursoFisicoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUsoRecursoFisico() throws Exception {
        // Initialize the database
        usoRecursoFisicoRepository.saveAndFlush(usoRecursoFisico);

        int databaseSizeBeforeDelete = usoRecursoFisicoRepository.findAll().size();

        // Delete the usoRecursoFisico
        restUsoRecursoFisicoMockMvc.perform(delete("/api/uso-recurso-fisicos/{id}", usoRecursoFisico.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UsoRecursoFisico> usoRecursoFisicoList = usoRecursoFisicoRepository.findAll();
        assertThat(usoRecursoFisicoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UsoRecursoFisico.class);
        UsoRecursoFisico usoRecursoFisico1 = new UsoRecursoFisico();
        usoRecursoFisico1.setId(1L);
        UsoRecursoFisico usoRecursoFisico2 = new UsoRecursoFisico();
        usoRecursoFisico2.setId(usoRecursoFisico1.getId());
        assertThat(usoRecursoFisico1).isEqualTo(usoRecursoFisico2);
        usoRecursoFisico2.setId(2L);
        assertThat(usoRecursoFisico1).isNotEqualTo(usoRecursoFisico2);
        usoRecursoFisico1.setId(null);
        assertThat(usoRecursoFisico1).isNotEqualTo(usoRecursoFisico2);
    }
}
