package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.MiembrosEquipoEmpresas;
import io.github.jhipster.application.repository.MiembrosEquipoEmpresasRepository;
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
 * Integration tests for the {@Link MiembrosEquipoEmpresasResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class MiembrosEquipoEmpresasResourceIT {

    @Autowired
    private MiembrosEquipoEmpresasRepository miembrosEquipoEmpresasRepository;

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

    private MockMvc restMiembrosEquipoEmpresasMockMvc;

    private MiembrosEquipoEmpresas miembrosEquipoEmpresas;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MiembrosEquipoEmpresasResource miembrosEquipoEmpresasResource = new MiembrosEquipoEmpresasResource(miembrosEquipoEmpresasRepository);
        this.restMiembrosEquipoEmpresasMockMvc = MockMvcBuilders.standaloneSetup(miembrosEquipoEmpresasResource)
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
    public static MiembrosEquipoEmpresas createEntity(EntityManager em) {
        MiembrosEquipoEmpresas miembrosEquipoEmpresas = new MiembrosEquipoEmpresas();
        return miembrosEquipoEmpresas;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MiembrosEquipoEmpresas createUpdatedEntity(EntityManager em) {
        MiembrosEquipoEmpresas miembrosEquipoEmpresas = new MiembrosEquipoEmpresas();
        return miembrosEquipoEmpresas;
    }

    @BeforeEach
    public void initTest() {
        miembrosEquipoEmpresas = createEntity(em);
    }

    @Test
    @Transactional
    public void createMiembrosEquipoEmpresas() throws Exception {
        int databaseSizeBeforeCreate = miembrosEquipoEmpresasRepository.findAll().size();

        // Create the MiembrosEquipoEmpresas
        restMiembrosEquipoEmpresasMockMvc.perform(post("/api/miembros-equipo-empresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(miembrosEquipoEmpresas)))
            .andExpect(status().isCreated());

        // Validate the MiembrosEquipoEmpresas in the database
        List<MiembrosEquipoEmpresas> miembrosEquipoEmpresasList = miembrosEquipoEmpresasRepository.findAll();
        assertThat(miembrosEquipoEmpresasList).hasSize(databaseSizeBeforeCreate + 1);
        MiembrosEquipoEmpresas testMiembrosEquipoEmpresas = miembrosEquipoEmpresasList.get(miembrosEquipoEmpresasList.size() - 1);
    }

    @Test
    @Transactional
    public void createMiembrosEquipoEmpresasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = miembrosEquipoEmpresasRepository.findAll().size();

        // Create the MiembrosEquipoEmpresas with an existing ID
        miembrosEquipoEmpresas.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMiembrosEquipoEmpresasMockMvc.perform(post("/api/miembros-equipo-empresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(miembrosEquipoEmpresas)))
            .andExpect(status().isBadRequest());

        // Validate the MiembrosEquipoEmpresas in the database
        List<MiembrosEquipoEmpresas> miembrosEquipoEmpresasList = miembrosEquipoEmpresasRepository.findAll();
        assertThat(miembrosEquipoEmpresasList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMiembrosEquipoEmpresas() throws Exception {
        // Initialize the database
        miembrosEquipoEmpresasRepository.saveAndFlush(miembrosEquipoEmpresas);

        // Get all the miembrosEquipoEmpresasList
        restMiembrosEquipoEmpresasMockMvc.perform(get("/api/miembros-equipo-empresas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(miembrosEquipoEmpresas.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getMiembrosEquipoEmpresas() throws Exception {
        // Initialize the database
        miembrosEquipoEmpresasRepository.saveAndFlush(miembrosEquipoEmpresas);

        // Get the miembrosEquipoEmpresas
        restMiembrosEquipoEmpresasMockMvc.perform(get("/api/miembros-equipo-empresas/{id}", miembrosEquipoEmpresas.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(miembrosEquipoEmpresas.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMiembrosEquipoEmpresas() throws Exception {
        // Get the miembrosEquipoEmpresas
        restMiembrosEquipoEmpresasMockMvc.perform(get("/api/miembros-equipo-empresas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMiembrosEquipoEmpresas() throws Exception {
        // Initialize the database
        miembrosEquipoEmpresasRepository.saveAndFlush(miembrosEquipoEmpresas);

        int databaseSizeBeforeUpdate = miembrosEquipoEmpresasRepository.findAll().size();

        // Update the miembrosEquipoEmpresas
        MiembrosEquipoEmpresas updatedMiembrosEquipoEmpresas = miembrosEquipoEmpresasRepository.findById(miembrosEquipoEmpresas.getId()).get();
        // Disconnect from session so that the updates on updatedMiembrosEquipoEmpresas are not directly saved in db
        em.detach(updatedMiembrosEquipoEmpresas);

        restMiembrosEquipoEmpresasMockMvc.perform(put("/api/miembros-equipo-empresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMiembrosEquipoEmpresas)))
            .andExpect(status().isOk());

        // Validate the MiembrosEquipoEmpresas in the database
        List<MiembrosEquipoEmpresas> miembrosEquipoEmpresasList = miembrosEquipoEmpresasRepository.findAll();
        assertThat(miembrosEquipoEmpresasList).hasSize(databaseSizeBeforeUpdate);
        MiembrosEquipoEmpresas testMiembrosEquipoEmpresas = miembrosEquipoEmpresasList.get(miembrosEquipoEmpresasList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingMiembrosEquipoEmpresas() throws Exception {
        int databaseSizeBeforeUpdate = miembrosEquipoEmpresasRepository.findAll().size();

        // Create the MiembrosEquipoEmpresas

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMiembrosEquipoEmpresasMockMvc.perform(put("/api/miembros-equipo-empresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(miembrosEquipoEmpresas)))
            .andExpect(status().isBadRequest());

        // Validate the MiembrosEquipoEmpresas in the database
        List<MiembrosEquipoEmpresas> miembrosEquipoEmpresasList = miembrosEquipoEmpresasRepository.findAll();
        assertThat(miembrosEquipoEmpresasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMiembrosEquipoEmpresas() throws Exception {
        // Initialize the database
        miembrosEquipoEmpresasRepository.saveAndFlush(miembrosEquipoEmpresas);

        int databaseSizeBeforeDelete = miembrosEquipoEmpresasRepository.findAll().size();

        // Delete the miembrosEquipoEmpresas
        restMiembrosEquipoEmpresasMockMvc.perform(delete("/api/miembros-equipo-empresas/{id}", miembrosEquipoEmpresas.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MiembrosEquipoEmpresas> miembrosEquipoEmpresasList = miembrosEquipoEmpresasRepository.findAll();
        assertThat(miembrosEquipoEmpresasList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MiembrosEquipoEmpresas.class);
        MiembrosEquipoEmpresas miembrosEquipoEmpresas1 = new MiembrosEquipoEmpresas();
        miembrosEquipoEmpresas1.setId(1L);
        MiembrosEquipoEmpresas miembrosEquipoEmpresas2 = new MiembrosEquipoEmpresas();
        miembrosEquipoEmpresas2.setId(miembrosEquipoEmpresas1.getId());
        assertThat(miembrosEquipoEmpresas1).isEqualTo(miembrosEquipoEmpresas2);
        miembrosEquipoEmpresas2.setId(2L);
        assertThat(miembrosEquipoEmpresas1).isNotEqualTo(miembrosEquipoEmpresas2);
        miembrosEquipoEmpresas1.setId(null);
        assertThat(miembrosEquipoEmpresas1).isNotEqualTo(miembrosEquipoEmpresas2);
    }
}
