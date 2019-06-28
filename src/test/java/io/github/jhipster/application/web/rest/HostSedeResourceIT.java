package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.HostSede;
import io.github.jhipster.application.repository.HostSedeRepository;
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
 * Integration tests for the {@Link HostSedeResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class HostSedeResourceIT {

    @Autowired
    private HostSedeRepository hostSedeRepository;

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

    private MockMvc restHostSedeMockMvc;

    private HostSede hostSede;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HostSedeResource hostSedeResource = new HostSedeResource(hostSedeRepository);
        this.restHostSedeMockMvc = MockMvcBuilders.standaloneSetup(hostSedeResource)
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
    public static HostSede createEntity(EntityManager em) {
        HostSede hostSede = new HostSede();
        return hostSede;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HostSede createUpdatedEntity(EntityManager em) {
        HostSede hostSede = new HostSede();
        return hostSede;
    }

    @BeforeEach
    public void initTest() {
        hostSede = createEntity(em);
    }

    @Test
    @Transactional
    public void createHostSede() throws Exception {
        int databaseSizeBeforeCreate = hostSedeRepository.findAll().size();

        // Create the HostSede
        restHostSedeMockMvc.perform(post("/api/host-sedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hostSede)))
            .andExpect(status().isCreated());

        // Validate the HostSede in the database
        List<HostSede> hostSedeList = hostSedeRepository.findAll();
        assertThat(hostSedeList).hasSize(databaseSizeBeforeCreate + 1);
        HostSede testHostSede = hostSedeList.get(hostSedeList.size() - 1);
    }

    @Test
    @Transactional
    public void createHostSedeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hostSedeRepository.findAll().size();

        // Create the HostSede with an existing ID
        hostSede.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHostSedeMockMvc.perform(post("/api/host-sedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hostSede)))
            .andExpect(status().isBadRequest());

        // Validate the HostSede in the database
        List<HostSede> hostSedeList = hostSedeRepository.findAll();
        assertThat(hostSedeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllHostSedes() throws Exception {
        // Initialize the database
        hostSedeRepository.saveAndFlush(hostSede);

        // Get all the hostSedeList
        restHostSedeMockMvc.perform(get("/api/host-sedes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hostSede.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getHostSede() throws Exception {
        // Initialize the database
        hostSedeRepository.saveAndFlush(hostSede);

        // Get the hostSede
        restHostSedeMockMvc.perform(get("/api/host-sedes/{id}", hostSede.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(hostSede.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingHostSede() throws Exception {
        // Get the hostSede
        restHostSedeMockMvc.perform(get("/api/host-sedes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHostSede() throws Exception {
        // Initialize the database
        hostSedeRepository.saveAndFlush(hostSede);

        int databaseSizeBeforeUpdate = hostSedeRepository.findAll().size();

        // Update the hostSede
        HostSede updatedHostSede = hostSedeRepository.findById(hostSede.getId()).get();
        // Disconnect from session so that the updates on updatedHostSede are not directly saved in db
        em.detach(updatedHostSede);

        restHostSedeMockMvc.perform(put("/api/host-sedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedHostSede)))
            .andExpect(status().isOk());

        // Validate the HostSede in the database
        List<HostSede> hostSedeList = hostSedeRepository.findAll();
        assertThat(hostSedeList).hasSize(databaseSizeBeforeUpdate);
        HostSede testHostSede = hostSedeList.get(hostSedeList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingHostSede() throws Exception {
        int databaseSizeBeforeUpdate = hostSedeRepository.findAll().size();

        // Create the HostSede

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHostSedeMockMvc.perform(put("/api/host-sedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hostSede)))
            .andExpect(status().isBadRequest());

        // Validate the HostSede in the database
        List<HostSede> hostSedeList = hostSedeRepository.findAll();
        assertThat(hostSedeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHostSede() throws Exception {
        // Initialize the database
        hostSedeRepository.saveAndFlush(hostSede);

        int databaseSizeBeforeDelete = hostSedeRepository.findAll().size();

        // Delete the hostSede
        restHostSedeMockMvc.perform(delete("/api/host-sedes/{id}", hostSede.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HostSede> hostSedeList = hostSedeRepository.findAll();
        assertThat(hostSedeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HostSede.class);
        HostSede hostSede1 = new HostSede();
        hostSede1.setId(1L);
        HostSede hostSede2 = new HostSede();
        hostSede2.setId(hostSede1.getId());
        assertThat(hostSede1).isEqualTo(hostSede2);
        hostSede2.setId(2L);
        assertThat(hostSede1).isNotEqualTo(hostSede2);
        hostSede1.setId(null);
        assertThat(hostSede1).isNotEqualTo(hostSede2);
    }
}
