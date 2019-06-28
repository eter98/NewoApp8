package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.TipoRecurso;
import io.github.jhipster.application.repository.TipoRecursoRepository;
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
 * Integration tests for the {@Link TipoRecursoResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class TipoRecursoResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    @Autowired
    private TipoRecursoRepository tipoRecursoRepository;

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

    private MockMvc restTipoRecursoMockMvc;

    private TipoRecurso tipoRecurso;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoRecursoResource tipoRecursoResource = new TipoRecursoResource(tipoRecursoRepository);
        this.restTipoRecursoMockMvc = MockMvcBuilders.standaloneSetup(tipoRecursoResource)
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
    public static TipoRecurso createEntity(EntityManager em) {
        TipoRecurso tipoRecurso = new TipoRecurso()
            .nombre(DEFAULT_NOMBRE)
            .descripcion(DEFAULT_DESCRIPCION);
        return tipoRecurso;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoRecurso createUpdatedEntity(EntityManager em) {
        TipoRecurso tipoRecurso = new TipoRecurso()
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION);
        return tipoRecurso;
    }

    @BeforeEach
    public void initTest() {
        tipoRecurso = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoRecurso() throws Exception {
        int databaseSizeBeforeCreate = tipoRecursoRepository.findAll().size();

        // Create the TipoRecurso
        restTipoRecursoMockMvc.perform(post("/api/tipo-recursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoRecurso)))
            .andExpect(status().isCreated());

        // Validate the TipoRecurso in the database
        List<TipoRecurso> tipoRecursoList = tipoRecursoRepository.findAll();
        assertThat(tipoRecursoList).hasSize(databaseSizeBeforeCreate + 1);
        TipoRecurso testTipoRecurso = tipoRecursoList.get(tipoRecursoList.size() - 1);
        assertThat(testTipoRecurso.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testTipoRecurso.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
    }

    @Test
    @Transactional
    public void createTipoRecursoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoRecursoRepository.findAll().size();

        // Create the TipoRecurso with an existing ID
        tipoRecurso.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoRecursoMockMvc.perform(post("/api/tipo-recursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoRecurso)))
            .andExpect(status().isBadRequest());

        // Validate the TipoRecurso in the database
        List<TipoRecurso> tipoRecursoList = tipoRecursoRepository.findAll();
        assertThat(tipoRecursoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTipoRecursos() throws Exception {
        // Initialize the database
        tipoRecursoRepository.saveAndFlush(tipoRecurso);

        // Get all the tipoRecursoList
        restTipoRecursoMockMvc.perform(get("/api/tipo-recursos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoRecurso.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())));
    }
    
    @Test
    @Transactional
    public void getTipoRecurso() throws Exception {
        // Initialize the database
        tipoRecursoRepository.saveAndFlush(tipoRecurso);

        // Get the tipoRecurso
        restTipoRecursoMockMvc.perform(get("/api/tipo-recursos/{id}", tipoRecurso.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoRecurso.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoRecurso() throws Exception {
        // Get the tipoRecurso
        restTipoRecursoMockMvc.perform(get("/api/tipo-recursos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoRecurso() throws Exception {
        // Initialize the database
        tipoRecursoRepository.saveAndFlush(tipoRecurso);

        int databaseSizeBeforeUpdate = tipoRecursoRepository.findAll().size();

        // Update the tipoRecurso
        TipoRecurso updatedTipoRecurso = tipoRecursoRepository.findById(tipoRecurso.getId()).get();
        // Disconnect from session so that the updates on updatedTipoRecurso are not directly saved in db
        em.detach(updatedTipoRecurso);
        updatedTipoRecurso
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION);

        restTipoRecursoMockMvc.perform(put("/api/tipo-recursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoRecurso)))
            .andExpect(status().isOk());

        // Validate the TipoRecurso in the database
        List<TipoRecurso> tipoRecursoList = tipoRecursoRepository.findAll();
        assertThat(tipoRecursoList).hasSize(databaseSizeBeforeUpdate);
        TipoRecurso testTipoRecurso = tipoRecursoList.get(tipoRecursoList.size() - 1);
        assertThat(testTipoRecurso.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testTipoRecurso.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoRecurso() throws Exception {
        int databaseSizeBeforeUpdate = tipoRecursoRepository.findAll().size();

        // Create the TipoRecurso

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoRecursoMockMvc.perform(put("/api/tipo-recursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoRecurso)))
            .andExpect(status().isBadRequest());

        // Validate the TipoRecurso in the database
        List<TipoRecurso> tipoRecursoList = tipoRecursoRepository.findAll();
        assertThat(tipoRecursoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoRecurso() throws Exception {
        // Initialize the database
        tipoRecursoRepository.saveAndFlush(tipoRecurso);

        int databaseSizeBeforeDelete = tipoRecursoRepository.findAll().size();

        // Delete the tipoRecurso
        restTipoRecursoMockMvc.perform(delete("/api/tipo-recursos/{id}", tipoRecurso.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TipoRecurso> tipoRecursoList = tipoRecursoRepository.findAll();
        assertThat(tipoRecursoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoRecurso.class);
        TipoRecurso tipoRecurso1 = new TipoRecurso();
        tipoRecurso1.setId(1L);
        TipoRecurso tipoRecurso2 = new TipoRecurso();
        tipoRecurso2.setId(tipoRecurso1.getId());
        assertThat(tipoRecurso1).isEqualTo(tipoRecurso2);
        tipoRecurso2.setId(2L);
        assertThat(tipoRecurso1).isNotEqualTo(tipoRecurso2);
        tipoRecurso1.setId(null);
        assertThat(tipoRecurso1).isNotEqualTo(tipoRecurso2);
    }
}
