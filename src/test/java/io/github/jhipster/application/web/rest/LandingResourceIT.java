package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.Landing;
import io.github.jhipster.application.repository.LandingRepository;
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
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.domain.enumeration.Impuestod;
/**
 * Integration tests for the {@Link LandingResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class LandingResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO_NEGOCIO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO_NEGOCIO = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMERO_PUESTOS = 1;
    private static final Integer UPDATED_NUMERO_PUESTOS = 2;

    private static final Integer DEFAULT_TARIFA = 1;
    private static final Integer UPDATED_TARIFA = 2;

    private static final byte[] DEFAULT_FOTOGRAFIA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTOGRAFIA = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTOGRAFIA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTOGRAFIA_CONTENT_TYPE = "image/png";

    private static final Impuestod DEFAULT_MPUESTO = Impuestod.IVA19;
    private static final Impuestod UPDATED_MPUESTO = Impuestod.IVA6;

    @Autowired
    private LandingRepository landingRepository;

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

    private MockMvc restLandingMockMvc;

    private Landing landing;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LandingResource landingResource = new LandingResource(landingRepository);
        this.restLandingMockMvc = MockMvcBuilders.standaloneSetup(landingResource)
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
    public static Landing createEntity(EntityManager em) {
        Landing landing = new Landing()
            .nombre(DEFAULT_NOMBRE)
            .descripcion(DEFAULT_DESCRIPCION)
            .telefonoNegocio(DEFAULT_TELEFONO_NEGOCIO)
            .numeroPuestos(DEFAULT_NUMERO_PUESTOS)
            .tarifa(DEFAULT_TARIFA)
            .fotografia(DEFAULT_FOTOGRAFIA)
            .fotografiaContentType(DEFAULT_FOTOGRAFIA_CONTENT_TYPE)
            .mpuesto(DEFAULT_MPUESTO);
        return landing;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Landing createUpdatedEntity(EntityManager em) {
        Landing landing = new Landing()
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .telefonoNegocio(UPDATED_TELEFONO_NEGOCIO)
            .numeroPuestos(UPDATED_NUMERO_PUESTOS)
            .tarifa(UPDATED_TARIFA)
            .fotografia(UPDATED_FOTOGRAFIA)
            .fotografiaContentType(UPDATED_FOTOGRAFIA_CONTENT_TYPE)
            .mpuesto(UPDATED_MPUESTO);
        return landing;
    }

    @BeforeEach
    public void initTest() {
        landing = createEntity(em);
    }

    @Test
    @Transactional
    public void createLanding() throws Exception {
        int databaseSizeBeforeCreate = landingRepository.findAll().size();

        // Create the Landing
        restLandingMockMvc.perform(post("/api/landings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(landing)))
            .andExpect(status().isCreated());

        // Validate the Landing in the database
        List<Landing> landingList = landingRepository.findAll();
        assertThat(landingList).hasSize(databaseSizeBeforeCreate + 1);
        Landing testLanding = landingList.get(landingList.size() - 1);
        assertThat(testLanding.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testLanding.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testLanding.getTelefonoNegocio()).isEqualTo(DEFAULT_TELEFONO_NEGOCIO);
        assertThat(testLanding.getNumeroPuestos()).isEqualTo(DEFAULT_NUMERO_PUESTOS);
        assertThat(testLanding.getTarifa()).isEqualTo(DEFAULT_TARIFA);
        assertThat(testLanding.getFotografia()).isEqualTo(DEFAULT_FOTOGRAFIA);
        assertThat(testLanding.getFotografiaContentType()).isEqualTo(DEFAULT_FOTOGRAFIA_CONTENT_TYPE);
        assertThat(testLanding.getMpuesto()).isEqualTo(DEFAULT_MPUESTO);
    }

    @Test
    @Transactional
    public void createLandingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = landingRepository.findAll().size();

        // Create the Landing with an existing ID
        landing.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLandingMockMvc.perform(post("/api/landings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(landing)))
            .andExpect(status().isBadRequest());

        // Validate the Landing in the database
        List<Landing> landingList = landingRepository.findAll();
        assertThat(landingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLandings() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get all the landingList
        restLandingMockMvc.perform(get("/api/landings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(landing.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].telefonoNegocio").value(hasItem(DEFAULT_TELEFONO_NEGOCIO.toString())))
            .andExpect(jsonPath("$.[*].numeroPuestos").value(hasItem(DEFAULT_NUMERO_PUESTOS)))
            .andExpect(jsonPath("$.[*].tarifa").value(hasItem(DEFAULT_TARIFA)))
            .andExpect(jsonPath("$.[*].fotografiaContentType").value(hasItem(DEFAULT_FOTOGRAFIA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fotografia").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTOGRAFIA))))
            .andExpect(jsonPath("$.[*].mpuesto").value(hasItem(DEFAULT_MPUESTO.toString())));
    }
    
    @Test
    @Transactional
    public void getLanding() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        // Get the landing
        restLandingMockMvc.perform(get("/api/landings/{id}", landing.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(landing.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.telefonoNegocio").value(DEFAULT_TELEFONO_NEGOCIO.toString()))
            .andExpect(jsonPath("$.numeroPuestos").value(DEFAULT_NUMERO_PUESTOS))
            .andExpect(jsonPath("$.tarifa").value(DEFAULT_TARIFA))
            .andExpect(jsonPath("$.fotografiaContentType").value(DEFAULT_FOTOGRAFIA_CONTENT_TYPE))
            .andExpect(jsonPath("$.fotografia").value(Base64Utils.encodeToString(DEFAULT_FOTOGRAFIA)))
            .andExpect(jsonPath("$.mpuesto").value(DEFAULT_MPUESTO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLanding() throws Exception {
        // Get the landing
        restLandingMockMvc.perform(get("/api/landings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLanding() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        int databaseSizeBeforeUpdate = landingRepository.findAll().size();

        // Update the landing
        Landing updatedLanding = landingRepository.findById(landing.getId()).get();
        // Disconnect from session so that the updates on updatedLanding are not directly saved in db
        em.detach(updatedLanding);
        updatedLanding
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .telefonoNegocio(UPDATED_TELEFONO_NEGOCIO)
            .numeroPuestos(UPDATED_NUMERO_PUESTOS)
            .tarifa(UPDATED_TARIFA)
            .fotografia(UPDATED_FOTOGRAFIA)
            .fotografiaContentType(UPDATED_FOTOGRAFIA_CONTENT_TYPE)
            .mpuesto(UPDATED_MPUESTO);

        restLandingMockMvc.perform(put("/api/landings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLanding)))
            .andExpect(status().isOk());

        // Validate the Landing in the database
        List<Landing> landingList = landingRepository.findAll();
        assertThat(landingList).hasSize(databaseSizeBeforeUpdate);
        Landing testLanding = landingList.get(landingList.size() - 1);
        assertThat(testLanding.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testLanding.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testLanding.getTelefonoNegocio()).isEqualTo(UPDATED_TELEFONO_NEGOCIO);
        assertThat(testLanding.getNumeroPuestos()).isEqualTo(UPDATED_NUMERO_PUESTOS);
        assertThat(testLanding.getTarifa()).isEqualTo(UPDATED_TARIFA);
        assertThat(testLanding.getFotografia()).isEqualTo(UPDATED_FOTOGRAFIA);
        assertThat(testLanding.getFotografiaContentType()).isEqualTo(UPDATED_FOTOGRAFIA_CONTENT_TYPE);
        assertThat(testLanding.getMpuesto()).isEqualTo(UPDATED_MPUESTO);
    }

    @Test
    @Transactional
    public void updateNonExistingLanding() throws Exception {
        int databaseSizeBeforeUpdate = landingRepository.findAll().size();

        // Create the Landing

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLandingMockMvc.perform(put("/api/landings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(landing)))
            .andExpect(status().isBadRequest());

        // Validate the Landing in the database
        List<Landing> landingList = landingRepository.findAll();
        assertThat(landingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLanding() throws Exception {
        // Initialize the database
        landingRepository.saveAndFlush(landing);

        int databaseSizeBeforeDelete = landingRepository.findAll().size();

        // Delete the landing
        restLandingMockMvc.perform(delete("/api/landings/{id}", landing.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Landing> landingList = landingRepository.findAll();
        assertThat(landingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Landing.class);
        Landing landing1 = new Landing();
        landing1.setId(1L);
        Landing landing2 = new Landing();
        landing2.setId(landing1.getId());
        assertThat(landing1).isEqualTo(landing2);
        landing2.setId(2L);
        assertThat(landing1).isNotEqualTo(landing2);
        landing1.setId(null);
        assertThat(landing1).isNotEqualTo(landing2);
    }
}
