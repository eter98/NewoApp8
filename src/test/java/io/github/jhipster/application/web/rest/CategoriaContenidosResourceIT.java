package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.CategoriaContenidos;
import io.github.jhipster.application.repository.CategoriaContenidosRepository;
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
 * Integration tests for the {@Link CategoriaContenidosResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class CategoriaContenidosResourceIT {

    private static final String DEFAULT_CATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIA = "BBBBBBBBBB";

    @Autowired
    private CategoriaContenidosRepository categoriaContenidosRepository;

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

    private MockMvc restCategoriaContenidosMockMvc;

    private CategoriaContenidos categoriaContenidos;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CategoriaContenidosResource categoriaContenidosResource = new CategoriaContenidosResource(categoriaContenidosRepository);
        this.restCategoriaContenidosMockMvc = MockMvcBuilders.standaloneSetup(categoriaContenidosResource)
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
    public static CategoriaContenidos createEntity(EntityManager em) {
        CategoriaContenidos categoriaContenidos = new CategoriaContenidos()
            .categoria(DEFAULT_CATEGORIA);
        return categoriaContenidos;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoriaContenidos createUpdatedEntity(EntityManager em) {
        CategoriaContenidos categoriaContenidos = new CategoriaContenidos()
            .categoria(UPDATED_CATEGORIA);
        return categoriaContenidos;
    }

    @BeforeEach
    public void initTest() {
        categoriaContenidos = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategoriaContenidos() throws Exception {
        int databaseSizeBeforeCreate = categoriaContenidosRepository.findAll().size();

        // Create the CategoriaContenidos
        restCategoriaContenidosMockMvc.perform(post("/api/categoria-contenidos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoriaContenidos)))
            .andExpect(status().isCreated());

        // Validate the CategoriaContenidos in the database
        List<CategoriaContenidos> categoriaContenidosList = categoriaContenidosRepository.findAll();
        assertThat(categoriaContenidosList).hasSize(databaseSizeBeforeCreate + 1);
        CategoriaContenidos testCategoriaContenidos = categoriaContenidosList.get(categoriaContenidosList.size() - 1);
        assertThat(testCategoriaContenidos.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
    }

    @Test
    @Transactional
    public void createCategoriaContenidosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categoriaContenidosRepository.findAll().size();

        // Create the CategoriaContenidos with an existing ID
        categoriaContenidos.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategoriaContenidosMockMvc.perform(post("/api/categoria-contenidos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoriaContenidos)))
            .andExpect(status().isBadRequest());

        // Validate the CategoriaContenidos in the database
        List<CategoriaContenidos> categoriaContenidosList = categoriaContenidosRepository.findAll();
        assertThat(categoriaContenidosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCategoriaContenidos() throws Exception {
        // Initialize the database
        categoriaContenidosRepository.saveAndFlush(categoriaContenidos);

        // Get all the categoriaContenidosList
        restCategoriaContenidosMockMvc.perform(get("/api/categoria-contenidos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categoriaContenidos.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoria").value(hasItem(DEFAULT_CATEGORIA.toString())));
    }
    
    @Test
    @Transactional
    public void getCategoriaContenidos() throws Exception {
        // Initialize the database
        categoriaContenidosRepository.saveAndFlush(categoriaContenidos);

        // Get the categoriaContenidos
        restCategoriaContenidosMockMvc.perform(get("/api/categoria-contenidos/{id}", categoriaContenidos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(categoriaContenidos.getId().intValue()))
            .andExpect(jsonPath("$.categoria").value(DEFAULT_CATEGORIA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCategoriaContenidos() throws Exception {
        // Get the categoriaContenidos
        restCategoriaContenidosMockMvc.perform(get("/api/categoria-contenidos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategoriaContenidos() throws Exception {
        // Initialize the database
        categoriaContenidosRepository.saveAndFlush(categoriaContenidos);

        int databaseSizeBeforeUpdate = categoriaContenidosRepository.findAll().size();

        // Update the categoriaContenidos
        CategoriaContenidos updatedCategoriaContenidos = categoriaContenidosRepository.findById(categoriaContenidos.getId()).get();
        // Disconnect from session so that the updates on updatedCategoriaContenidos are not directly saved in db
        em.detach(updatedCategoriaContenidos);
        updatedCategoriaContenidos
            .categoria(UPDATED_CATEGORIA);

        restCategoriaContenidosMockMvc.perform(put("/api/categoria-contenidos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCategoriaContenidos)))
            .andExpect(status().isOk());

        // Validate the CategoriaContenidos in the database
        List<CategoriaContenidos> categoriaContenidosList = categoriaContenidosRepository.findAll();
        assertThat(categoriaContenidosList).hasSize(databaseSizeBeforeUpdate);
        CategoriaContenidos testCategoriaContenidos = categoriaContenidosList.get(categoriaContenidosList.size() - 1);
        assertThat(testCategoriaContenidos.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
    }

    @Test
    @Transactional
    public void updateNonExistingCategoriaContenidos() throws Exception {
        int databaseSizeBeforeUpdate = categoriaContenidosRepository.findAll().size();

        // Create the CategoriaContenidos

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategoriaContenidosMockMvc.perform(put("/api/categoria-contenidos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoriaContenidos)))
            .andExpect(status().isBadRequest());

        // Validate the CategoriaContenidos in the database
        List<CategoriaContenidos> categoriaContenidosList = categoriaContenidosRepository.findAll();
        assertThat(categoriaContenidosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategoriaContenidos() throws Exception {
        // Initialize the database
        categoriaContenidosRepository.saveAndFlush(categoriaContenidos);

        int databaseSizeBeforeDelete = categoriaContenidosRepository.findAll().size();

        // Delete the categoriaContenidos
        restCategoriaContenidosMockMvc.perform(delete("/api/categoria-contenidos/{id}", categoriaContenidos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CategoriaContenidos> categoriaContenidosList = categoriaContenidosRepository.findAll();
        assertThat(categoriaContenidosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoriaContenidos.class);
        CategoriaContenidos categoriaContenidos1 = new CategoriaContenidos();
        categoriaContenidos1.setId(1L);
        CategoriaContenidos categoriaContenidos2 = new CategoriaContenidos();
        categoriaContenidos2.setId(categoriaContenidos1.getId());
        assertThat(categoriaContenidos1).isEqualTo(categoriaContenidos2);
        categoriaContenidos2.setId(2L);
        assertThat(categoriaContenidos1).isNotEqualTo(categoriaContenidos2);
        categoriaContenidos1.setId(null);
        assertThat(categoriaContenidos1).isNotEqualTo(categoriaContenidos2);
    }
}
