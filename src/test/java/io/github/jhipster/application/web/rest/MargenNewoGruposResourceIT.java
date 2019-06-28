package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.MargenNewoGrupos;
import io.github.jhipster.application.repository.MargenNewoGruposRepository;
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
 * Integration tests for the {@Link MargenNewoGruposResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class MargenNewoGruposResourceIT {

    private static final Integer DEFAULT_PORCENTAJE_MARGEN = 1;
    private static final Integer UPDATED_PORCENTAJE_MARGEN = 2;

    @Autowired
    private MargenNewoGruposRepository margenNewoGruposRepository;

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

    private MockMvc restMargenNewoGruposMockMvc;

    private MargenNewoGrupos margenNewoGrupos;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MargenNewoGruposResource margenNewoGruposResource = new MargenNewoGruposResource(margenNewoGruposRepository);
        this.restMargenNewoGruposMockMvc = MockMvcBuilders.standaloneSetup(margenNewoGruposResource)
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
    public static MargenNewoGrupos createEntity(EntityManager em) {
        MargenNewoGrupos margenNewoGrupos = new MargenNewoGrupos()
            .porcentajeMargen(DEFAULT_PORCENTAJE_MARGEN);
        return margenNewoGrupos;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MargenNewoGrupos createUpdatedEntity(EntityManager em) {
        MargenNewoGrupos margenNewoGrupos = new MargenNewoGrupos()
            .porcentajeMargen(UPDATED_PORCENTAJE_MARGEN);
        return margenNewoGrupos;
    }

    @BeforeEach
    public void initTest() {
        margenNewoGrupos = createEntity(em);
    }

    @Test
    @Transactional
    public void createMargenNewoGrupos() throws Exception {
        int databaseSizeBeforeCreate = margenNewoGruposRepository.findAll().size();

        // Create the MargenNewoGrupos
        restMargenNewoGruposMockMvc.perform(post("/api/margen-newo-grupos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(margenNewoGrupos)))
            .andExpect(status().isCreated());

        // Validate the MargenNewoGrupos in the database
        List<MargenNewoGrupos> margenNewoGruposList = margenNewoGruposRepository.findAll();
        assertThat(margenNewoGruposList).hasSize(databaseSizeBeforeCreate + 1);
        MargenNewoGrupos testMargenNewoGrupos = margenNewoGruposList.get(margenNewoGruposList.size() - 1);
        assertThat(testMargenNewoGrupos.getPorcentajeMargen()).isEqualTo(DEFAULT_PORCENTAJE_MARGEN);
    }

    @Test
    @Transactional
    public void createMargenNewoGruposWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = margenNewoGruposRepository.findAll().size();

        // Create the MargenNewoGrupos with an existing ID
        margenNewoGrupos.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMargenNewoGruposMockMvc.perform(post("/api/margen-newo-grupos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(margenNewoGrupos)))
            .andExpect(status().isBadRequest());

        // Validate the MargenNewoGrupos in the database
        List<MargenNewoGrupos> margenNewoGruposList = margenNewoGruposRepository.findAll();
        assertThat(margenNewoGruposList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMargenNewoGrupos() throws Exception {
        // Initialize the database
        margenNewoGruposRepository.saveAndFlush(margenNewoGrupos);

        // Get all the margenNewoGruposList
        restMargenNewoGruposMockMvc.perform(get("/api/margen-newo-grupos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(margenNewoGrupos.getId().intValue())))
            .andExpect(jsonPath("$.[*].porcentajeMargen").value(hasItem(DEFAULT_PORCENTAJE_MARGEN)));
    }
    
    @Test
    @Transactional
    public void getMargenNewoGrupos() throws Exception {
        // Initialize the database
        margenNewoGruposRepository.saveAndFlush(margenNewoGrupos);

        // Get the margenNewoGrupos
        restMargenNewoGruposMockMvc.perform(get("/api/margen-newo-grupos/{id}", margenNewoGrupos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(margenNewoGrupos.getId().intValue()))
            .andExpect(jsonPath("$.porcentajeMargen").value(DEFAULT_PORCENTAJE_MARGEN));
    }

    @Test
    @Transactional
    public void getNonExistingMargenNewoGrupos() throws Exception {
        // Get the margenNewoGrupos
        restMargenNewoGruposMockMvc.perform(get("/api/margen-newo-grupos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMargenNewoGrupos() throws Exception {
        // Initialize the database
        margenNewoGruposRepository.saveAndFlush(margenNewoGrupos);

        int databaseSizeBeforeUpdate = margenNewoGruposRepository.findAll().size();

        // Update the margenNewoGrupos
        MargenNewoGrupos updatedMargenNewoGrupos = margenNewoGruposRepository.findById(margenNewoGrupos.getId()).get();
        // Disconnect from session so that the updates on updatedMargenNewoGrupos are not directly saved in db
        em.detach(updatedMargenNewoGrupos);
        updatedMargenNewoGrupos
            .porcentajeMargen(UPDATED_PORCENTAJE_MARGEN);

        restMargenNewoGruposMockMvc.perform(put("/api/margen-newo-grupos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMargenNewoGrupos)))
            .andExpect(status().isOk());

        // Validate the MargenNewoGrupos in the database
        List<MargenNewoGrupos> margenNewoGruposList = margenNewoGruposRepository.findAll();
        assertThat(margenNewoGruposList).hasSize(databaseSizeBeforeUpdate);
        MargenNewoGrupos testMargenNewoGrupos = margenNewoGruposList.get(margenNewoGruposList.size() - 1);
        assertThat(testMargenNewoGrupos.getPorcentajeMargen()).isEqualTo(UPDATED_PORCENTAJE_MARGEN);
    }

    @Test
    @Transactional
    public void updateNonExistingMargenNewoGrupos() throws Exception {
        int databaseSizeBeforeUpdate = margenNewoGruposRepository.findAll().size();

        // Create the MargenNewoGrupos

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMargenNewoGruposMockMvc.perform(put("/api/margen-newo-grupos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(margenNewoGrupos)))
            .andExpect(status().isBadRequest());

        // Validate the MargenNewoGrupos in the database
        List<MargenNewoGrupos> margenNewoGruposList = margenNewoGruposRepository.findAll();
        assertThat(margenNewoGruposList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMargenNewoGrupos() throws Exception {
        // Initialize the database
        margenNewoGruposRepository.saveAndFlush(margenNewoGrupos);

        int databaseSizeBeforeDelete = margenNewoGruposRepository.findAll().size();

        // Delete the margenNewoGrupos
        restMargenNewoGruposMockMvc.perform(delete("/api/margen-newo-grupos/{id}", margenNewoGrupos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MargenNewoGrupos> margenNewoGruposList = margenNewoGruposRepository.findAll();
        assertThat(margenNewoGruposList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MargenNewoGrupos.class);
        MargenNewoGrupos margenNewoGrupos1 = new MargenNewoGrupos();
        margenNewoGrupos1.setId(1L);
        MargenNewoGrupos margenNewoGrupos2 = new MargenNewoGrupos();
        margenNewoGrupos2.setId(margenNewoGrupos1.getId());
        assertThat(margenNewoGrupos1).isEqualTo(margenNewoGrupos2);
        margenNewoGrupos2.setId(2L);
        assertThat(margenNewoGrupos1).isNotEqualTo(margenNewoGrupos2);
        margenNewoGrupos1.setId(null);
        assertThat(margenNewoGrupos1).isNotEqualTo(margenNewoGrupos2);
    }
}
