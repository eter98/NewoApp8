package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.MargenNewoBlog;
import io.github.jhipster.application.repository.MargenNewoBlogRepository;
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
 * Integration tests for the {@Link MargenNewoBlogResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class MargenNewoBlogResourceIT {

    private static final Integer DEFAULT_PORCENTAJE_MARGEN = 1;
    private static final Integer UPDATED_PORCENTAJE_MARGEN = 2;

    @Autowired
    private MargenNewoBlogRepository margenNewoBlogRepository;

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

    private MockMvc restMargenNewoBlogMockMvc;

    private MargenNewoBlog margenNewoBlog;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MargenNewoBlogResource margenNewoBlogResource = new MargenNewoBlogResource(margenNewoBlogRepository);
        this.restMargenNewoBlogMockMvc = MockMvcBuilders.standaloneSetup(margenNewoBlogResource)
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
    public static MargenNewoBlog createEntity(EntityManager em) {
        MargenNewoBlog margenNewoBlog = new MargenNewoBlog()
            .porcentajeMargen(DEFAULT_PORCENTAJE_MARGEN);
        return margenNewoBlog;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MargenNewoBlog createUpdatedEntity(EntityManager em) {
        MargenNewoBlog margenNewoBlog = new MargenNewoBlog()
            .porcentajeMargen(UPDATED_PORCENTAJE_MARGEN);
        return margenNewoBlog;
    }

    @BeforeEach
    public void initTest() {
        margenNewoBlog = createEntity(em);
    }

    @Test
    @Transactional
    public void createMargenNewoBlog() throws Exception {
        int databaseSizeBeforeCreate = margenNewoBlogRepository.findAll().size();

        // Create the MargenNewoBlog
        restMargenNewoBlogMockMvc.perform(post("/api/margen-newo-blogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(margenNewoBlog)))
            .andExpect(status().isCreated());

        // Validate the MargenNewoBlog in the database
        List<MargenNewoBlog> margenNewoBlogList = margenNewoBlogRepository.findAll();
        assertThat(margenNewoBlogList).hasSize(databaseSizeBeforeCreate + 1);
        MargenNewoBlog testMargenNewoBlog = margenNewoBlogList.get(margenNewoBlogList.size() - 1);
        assertThat(testMargenNewoBlog.getPorcentajeMargen()).isEqualTo(DEFAULT_PORCENTAJE_MARGEN);
    }

    @Test
    @Transactional
    public void createMargenNewoBlogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = margenNewoBlogRepository.findAll().size();

        // Create the MargenNewoBlog with an existing ID
        margenNewoBlog.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMargenNewoBlogMockMvc.perform(post("/api/margen-newo-blogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(margenNewoBlog)))
            .andExpect(status().isBadRequest());

        // Validate the MargenNewoBlog in the database
        List<MargenNewoBlog> margenNewoBlogList = margenNewoBlogRepository.findAll();
        assertThat(margenNewoBlogList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMargenNewoBlogs() throws Exception {
        // Initialize the database
        margenNewoBlogRepository.saveAndFlush(margenNewoBlog);

        // Get all the margenNewoBlogList
        restMargenNewoBlogMockMvc.perform(get("/api/margen-newo-blogs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(margenNewoBlog.getId().intValue())))
            .andExpect(jsonPath("$.[*].porcentajeMargen").value(hasItem(DEFAULT_PORCENTAJE_MARGEN)));
    }
    
    @Test
    @Transactional
    public void getMargenNewoBlog() throws Exception {
        // Initialize the database
        margenNewoBlogRepository.saveAndFlush(margenNewoBlog);

        // Get the margenNewoBlog
        restMargenNewoBlogMockMvc.perform(get("/api/margen-newo-blogs/{id}", margenNewoBlog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(margenNewoBlog.getId().intValue()))
            .andExpect(jsonPath("$.porcentajeMargen").value(DEFAULT_PORCENTAJE_MARGEN));
    }

    @Test
    @Transactional
    public void getNonExistingMargenNewoBlog() throws Exception {
        // Get the margenNewoBlog
        restMargenNewoBlogMockMvc.perform(get("/api/margen-newo-blogs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMargenNewoBlog() throws Exception {
        // Initialize the database
        margenNewoBlogRepository.saveAndFlush(margenNewoBlog);

        int databaseSizeBeforeUpdate = margenNewoBlogRepository.findAll().size();

        // Update the margenNewoBlog
        MargenNewoBlog updatedMargenNewoBlog = margenNewoBlogRepository.findById(margenNewoBlog.getId()).get();
        // Disconnect from session so that the updates on updatedMargenNewoBlog are not directly saved in db
        em.detach(updatedMargenNewoBlog);
        updatedMargenNewoBlog
            .porcentajeMargen(UPDATED_PORCENTAJE_MARGEN);

        restMargenNewoBlogMockMvc.perform(put("/api/margen-newo-blogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMargenNewoBlog)))
            .andExpect(status().isOk());

        // Validate the MargenNewoBlog in the database
        List<MargenNewoBlog> margenNewoBlogList = margenNewoBlogRepository.findAll();
        assertThat(margenNewoBlogList).hasSize(databaseSizeBeforeUpdate);
        MargenNewoBlog testMargenNewoBlog = margenNewoBlogList.get(margenNewoBlogList.size() - 1);
        assertThat(testMargenNewoBlog.getPorcentajeMargen()).isEqualTo(UPDATED_PORCENTAJE_MARGEN);
    }

    @Test
    @Transactional
    public void updateNonExistingMargenNewoBlog() throws Exception {
        int databaseSizeBeforeUpdate = margenNewoBlogRepository.findAll().size();

        // Create the MargenNewoBlog

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMargenNewoBlogMockMvc.perform(put("/api/margen-newo-blogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(margenNewoBlog)))
            .andExpect(status().isBadRequest());

        // Validate the MargenNewoBlog in the database
        List<MargenNewoBlog> margenNewoBlogList = margenNewoBlogRepository.findAll();
        assertThat(margenNewoBlogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMargenNewoBlog() throws Exception {
        // Initialize the database
        margenNewoBlogRepository.saveAndFlush(margenNewoBlog);

        int databaseSizeBeforeDelete = margenNewoBlogRepository.findAll().size();

        // Delete the margenNewoBlog
        restMargenNewoBlogMockMvc.perform(delete("/api/margen-newo-blogs/{id}", margenNewoBlog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MargenNewoBlog> margenNewoBlogList = margenNewoBlogRepository.findAll();
        assertThat(margenNewoBlogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MargenNewoBlog.class);
        MargenNewoBlog margenNewoBlog1 = new MargenNewoBlog();
        margenNewoBlog1.setId(1L);
        MargenNewoBlog margenNewoBlog2 = new MargenNewoBlog();
        margenNewoBlog2.setId(margenNewoBlog1.getId());
        assertThat(margenNewoBlog1).isEqualTo(margenNewoBlog2);
        margenNewoBlog2.setId(2L);
        assertThat(margenNewoBlog1).isNotEqualTo(margenNewoBlog2);
        margenNewoBlog1.setId(null);
        assertThat(margenNewoBlog1).isNotEqualTo(margenNewoBlog2);
    }
}
