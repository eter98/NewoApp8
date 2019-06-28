package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.Sedes;
import io.github.jhipster.application.repository.SedesRepository;
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

/**
 * Integration tests for the {@Link SedesResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class SedesResourceIT {

    private static final String DEFAULT_NOMBRE_SEDE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_SEDE = "BBBBBBBBBB";

    private static final Double DEFAULT_COORDENADA_X = 1D;
    private static final Double UPDATED_COORDENADA_X = 2D;

    private static final Double DEFAULT_COORDENADA_Y = 1D;
    private static final Double UPDATED_COORDENADA_Y = 2D;

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO_COMUNIDAD = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO_COMUNIDAD = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO_NEGOCIO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO_NEGOCIO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION_SEDE = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION_SEDE = "BBBBBBBBBB";

    private static final String DEFAULT_HORARIO = "AAAAAAAAAA";
    private static final String UPDATED_HORARIO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_VIDEO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_VIDEO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_VIDEO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_VIDEO_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGEN_1 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN_1 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_1_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_1_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGEN_2 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN_2 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_2_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_2_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGEN_3 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN_3 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_3_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_3_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGEN_4 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN_4 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_4_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_4_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGEN_5 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN_5 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_5_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_5_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGEN_6 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN_6 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_6_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_6_CONTENT_TYPE = "image/png";

    @Autowired
    private SedesRepository sedesRepository;

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

    private MockMvc restSedesMockMvc;

    private Sedes sedes;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SedesResource sedesResource = new SedesResource(sedesRepository);
        this.restSedesMockMvc = MockMvcBuilders.standaloneSetup(sedesResource)
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
    public static Sedes createEntity(EntityManager em) {
        Sedes sedes = new Sedes()
            .nombreSede(DEFAULT_NOMBRE_SEDE)
            .coordenadaX(DEFAULT_COORDENADA_X)
            .coordenadaY(DEFAULT_COORDENADA_Y)
            .direccion(DEFAULT_DIRECCION)
            .telefonoComunidad(DEFAULT_TELEFONO_COMUNIDAD)
            .telefonoNegocio(DEFAULT_TELEFONO_NEGOCIO)
            .descripcionSede(DEFAULT_DESCRIPCION_SEDE)
            .horario(DEFAULT_HORARIO)
            .video(DEFAULT_VIDEO)
            .videoContentType(DEFAULT_VIDEO_CONTENT_TYPE)
            .imagen1(DEFAULT_IMAGEN_1)
            .imagen1ContentType(DEFAULT_IMAGEN_1_CONTENT_TYPE)
            .imagen2(DEFAULT_IMAGEN_2)
            .imagen2ContentType(DEFAULT_IMAGEN_2_CONTENT_TYPE)
            .imagen3(DEFAULT_IMAGEN_3)
            .imagen3ContentType(DEFAULT_IMAGEN_3_CONTENT_TYPE)
            .imagen4(DEFAULT_IMAGEN_4)
            .imagen4ContentType(DEFAULT_IMAGEN_4_CONTENT_TYPE)
            .imagen5(DEFAULT_IMAGEN_5)
            .imagen5ContentType(DEFAULT_IMAGEN_5_CONTENT_TYPE)
            .imagen6(DEFAULT_IMAGEN_6)
            .imagen6ContentType(DEFAULT_IMAGEN_6_CONTENT_TYPE);
        return sedes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sedes createUpdatedEntity(EntityManager em) {
        Sedes sedes = new Sedes()
            .nombreSede(UPDATED_NOMBRE_SEDE)
            .coordenadaX(UPDATED_COORDENADA_X)
            .coordenadaY(UPDATED_COORDENADA_Y)
            .direccion(UPDATED_DIRECCION)
            .telefonoComunidad(UPDATED_TELEFONO_COMUNIDAD)
            .telefonoNegocio(UPDATED_TELEFONO_NEGOCIO)
            .descripcionSede(UPDATED_DESCRIPCION_SEDE)
            .horario(UPDATED_HORARIO)
            .video(UPDATED_VIDEO)
            .videoContentType(UPDATED_VIDEO_CONTENT_TYPE)
            .imagen1(UPDATED_IMAGEN_1)
            .imagen1ContentType(UPDATED_IMAGEN_1_CONTENT_TYPE)
            .imagen2(UPDATED_IMAGEN_2)
            .imagen2ContentType(UPDATED_IMAGEN_2_CONTENT_TYPE)
            .imagen3(UPDATED_IMAGEN_3)
            .imagen3ContentType(UPDATED_IMAGEN_3_CONTENT_TYPE)
            .imagen4(UPDATED_IMAGEN_4)
            .imagen4ContentType(UPDATED_IMAGEN_4_CONTENT_TYPE)
            .imagen5(UPDATED_IMAGEN_5)
            .imagen5ContentType(UPDATED_IMAGEN_5_CONTENT_TYPE)
            .imagen6(UPDATED_IMAGEN_6)
            .imagen6ContentType(UPDATED_IMAGEN_6_CONTENT_TYPE);
        return sedes;
    }

    @BeforeEach
    public void initTest() {
        sedes = createEntity(em);
    }

    @Test
    @Transactional
    public void createSedes() throws Exception {
        int databaseSizeBeforeCreate = sedesRepository.findAll().size();

        // Create the Sedes
        restSedesMockMvc.perform(post("/api/sedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sedes)))
            .andExpect(status().isCreated());

        // Validate the Sedes in the database
        List<Sedes> sedesList = sedesRepository.findAll();
        assertThat(sedesList).hasSize(databaseSizeBeforeCreate + 1);
        Sedes testSedes = sedesList.get(sedesList.size() - 1);
        assertThat(testSedes.getNombreSede()).isEqualTo(DEFAULT_NOMBRE_SEDE);
        assertThat(testSedes.getCoordenadaX()).isEqualTo(DEFAULT_COORDENADA_X);
        assertThat(testSedes.getCoordenadaY()).isEqualTo(DEFAULT_COORDENADA_Y);
        assertThat(testSedes.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testSedes.getTelefonoComunidad()).isEqualTo(DEFAULT_TELEFONO_COMUNIDAD);
        assertThat(testSedes.getTelefonoNegocio()).isEqualTo(DEFAULT_TELEFONO_NEGOCIO);
        assertThat(testSedes.getDescripcionSede()).isEqualTo(DEFAULT_DESCRIPCION_SEDE);
        assertThat(testSedes.getHorario()).isEqualTo(DEFAULT_HORARIO);
        assertThat(testSedes.getVideo()).isEqualTo(DEFAULT_VIDEO);
        assertThat(testSedes.getVideoContentType()).isEqualTo(DEFAULT_VIDEO_CONTENT_TYPE);
        assertThat(testSedes.getImagen1()).isEqualTo(DEFAULT_IMAGEN_1);
        assertThat(testSedes.getImagen1ContentType()).isEqualTo(DEFAULT_IMAGEN_1_CONTENT_TYPE);
        assertThat(testSedes.getImagen2()).isEqualTo(DEFAULT_IMAGEN_2);
        assertThat(testSedes.getImagen2ContentType()).isEqualTo(DEFAULT_IMAGEN_2_CONTENT_TYPE);
        assertThat(testSedes.getImagen3()).isEqualTo(DEFAULT_IMAGEN_3);
        assertThat(testSedes.getImagen3ContentType()).isEqualTo(DEFAULT_IMAGEN_3_CONTENT_TYPE);
        assertThat(testSedes.getImagen4()).isEqualTo(DEFAULT_IMAGEN_4);
        assertThat(testSedes.getImagen4ContentType()).isEqualTo(DEFAULT_IMAGEN_4_CONTENT_TYPE);
        assertThat(testSedes.getImagen5()).isEqualTo(DEFAULT_IMAGEN_5);
        assertThat(testSedes.getImagen5ContentType()).isEqualTo(DEFAULT_IMAGEN_5_CONTENT_TYPE);
        assertThat(testSedes.getImagen6()).isEqualTo(DEFAULT_IMAGEN_6);
        assertThat(testSedes.getImagen6ContentType()).isEqualTo(DEFAULT_IMAGEN_6_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createSedesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sedesRepository.findAll().size();

        // Create the Sedes with an existing ID
        sedes.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSedesMockMvc.perform(post("/api/sedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sedes)))
            .andExpect(status().isBadRequest());

        // Validate the Sedes in the database
        List<Sedes> sedesList = sedesRepository.findAll();
        assertThat(sedesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSedes() throws Exception {
        // Initialize the database
        sedesRepository.saveAndFlush(sedes);

        // Get all the sedesList
        restSedesMockMvc.perform(get("/api/sedes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sedes.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreSede").value(hasItem(DEFAULT_NOMBRE_SEDE.toString())))
            .andExpect(jsonPath("$.[*].coordenadaX").value(hasItem(DEFAULT_COORDENADA_X.doubleValue())))
            .andExpect(jsonPath("$.[*].coordenadaY").value(hasItem(DEFAULT_COORDENADA_Y.doubleValue())))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION.toString())))
            .andExpect(jsonPath("$.[*].telefonoComunidad").value(hasItem(DEFAULT_TELEFONO_COMUNIDAD.toString())))
            .andExpect(jsonPath("$.[*].telefonoNegocio").value(hasItem(DEFAULT_TELEFONO_NEGOCIO.toString())))
            .andExpect(jsonPath("$.[*].descripcionSede").value(hasItem(DEFAULT_DESCRIPCION_SEDE.toString())))
            .andExpect(jsonPath("$.[*].horario").value(hasItem(DEFAULT_HORARIO.toString())))
            .andExpect(jsonPath("$.[*].videoContentType").value(hasItem(DEFAULT_VIDEO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].video").value(hasItem(Base64Utils.encodeToString(DEFAULT_VIDEO))))
            .andExpect(jsonPath("$.[*].imagen1ContentType").value(hasItem(DEFAULT_IMAGEN_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen1").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_1))))
            .andExpect(jsonPath("$.[*].imagen2ContentType").value(hasItem(DEFAULT_IMAGEN_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen2").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_2))))
            .andExpect(jsonPath("$.[*].imagen3ContentType").value(hasItem(DEFAULT_IMAGEN_3_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen3").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_3))))
            .andExpect(jsonPath("$.[*].imagen4ContentType").value(hasItem(DEFAULT_IMAGEN_4_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen4").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_4))))
            .andExpect(jsonPath("$.[*].imagen5ContentType").value(hasItem(DEFAULT_IMAGEN_5_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen5").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_5))))
            .andExpect(jsonPath("$.[*].imagen6ContentType").value(hasItem(DEFAULT_IMAGEN_6_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen6").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_6))));
    }
    
    @Test
    @Transactional
    public void getSedes() throws Exception {
        // Initialize the database
        sedesRepository.saveAndFlush(sedes);

        // Get the sedes
        restSedesMockMvc.perform(get("/api/sedes/{id}", sedes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sedes.getId().intValue()))
            .andExpect(jsonPath("$.nombreSede").value(DEFAULT_NOMBRE_SEDE.toString()))
            .andExpect(jsonPath("$.coordenadaX").value(DEFAULT_COORDENADA_X.doubleValue()))
            .andExpect(jsonPath("$.coordenadaY").value(DEFAULT_COORDENADA_Y.doubleValue()))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION.toString()))
            .andExpect(jsonPath("$.telefonoComunidad").value(DEFAULT_TELEFONO_COMUNIDAD.toString()))
            .andExpect(jsonPath("$.telefonoNegocio").value(DEFAULT_TELEFONO_NEGOCIO.toString()))
            .andExpect(jsonPath("$.descripcionSede").value(DEFAULT_DESCRIPCION_SEDE.toString()))
            .andExpect(jsonPath("$.horario").value(DEFAULT_HORARIO.toString()))
            .andExpect(jsonPath("$.videoContentType").value(DEFAULT_VIDEO_CONTENT_TYPE))
            .andExpect(jsonPath("$.video").value(Base64Utils.encodeToString(DEFAULT_VIDEO)))
            .andExpect(jsonPath("$.imagen1ContentType").value(DEFAULT_IMAGEN_1_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen1").value(Base64Utils.encodeToString(DEFAULT_IMAGEN_1)))
            .andExpect(jsonPath("$.imagen2ContentType").value(DEFAULT_IMAGEN_2_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen2").value(Base64Utils.encodeToString(DEFAULT_IMAGEN_2)))
            .andExpect(jsonPath("$.imagen3ContentType").value(DEFAULT_IMAGEN_3_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen3").value(Base64Utils.encodeToString(DEFAULT_IMAGEN_3)))
            .andExpect(jsonPath("$.imagen4ContentType").value(DEFAULT_IMAGEN_4_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen4").value(Base64Utils.encodeToString(DEFAULT_IMAGEN_4)))
            .andExpect(jsonPath("$.imagen5ContentType").value(DEFAULT_IMAGEN_5_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen5").value(Base64Utils.encodeToString(DEFAULT_IMAGEN_5)))
            .andExpect(jsonPath("$.imagen6ContentType").value(DEFAULT_IMAGEN_6_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen6").value(Base64Utils.encodeToString(DEFAULT_IMAGEN_6)));
    }

    @Test
    @Transactional
    public void getNonExistingSedes() throws Exception {
        // Get the sedes
        restSedesMockMvc.perform(get("/api/sedes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSedes() throws Exception {
        // Initialize the database
        sedesRepository.saveAndFlush(sedes);

        int databaseSizeBeforeUpdate = sedesRepository.findAll().size();

        // Update the sedes
        Sedes updatedSedes = sedesRepository.findById(sedes.getId()).get();
        // Disconnect from session so that the updates on updatedSedes are not directly saved in db
        em.detach(updatedSedes);
        updatedSedes
            .nombreSede(UPDATED_NOMBRE_SEDE)
            .coordenadaX(UPDATED_COORDENADA_X)
            .coordenadaY(UPDATED_COORDENADA_Y)
            .direccion(UPDATED_DIRECCION)
            .telefonoComunidad(UPDATED_TELEFONO_COMUNIDAD)
            .telefonoNegocio(UPDATED_TELEFONO_NEGOCIO)
            .descripcionSede(UPDATED_DESCRIPCION_SEDE)
            .horario(UPDATED_HORARIO)
            .video(UPDATED_VIDEO)
            .videoContentType(UPDATED_VIDEO_CONTENT_TYPE)
            .imagen1(UPDATED_IMAGEN_1)
            .imagen1ContentType(UPDATED_IMAGEN_1_CONTENT_TYPE)
            .imagen2(UPDATED_IMAGEN_2)
            .imagen2ContentType(UPDATED_IMAGEN_2_CONTENT_TYPE)
            .imagen3(UPDATED_IMAGEN_3)
            .imagen3ContentType(UPDATED_IMAGEN_3_CONTENT_TYPE)
            .imagen4(UPDATED_IMAGEN_4)
            .imagen4ContentType(UPDATED_IMAGEN_4_CONTENT_TYPE)
            .imagen5(UPDATED_IMAGEN_5)
            .imagen5ContentType(UPDATED_IMAGEN_5_CONTENT_TYPE)
            .imagen6(UPDATED_IMAGEN_6)
            .imagen6ContentType(UPDATED_IMAGEN_6_CONTENT_TYPE);

        restSedesMockMvc.perform(put("/api/sedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSedes)))
            .andExpect(status().isOk());

        // Validate the Sedes in the database
        List<Sedes> sedesList = sedesRepository.findAll();
        assertThat(sedesList).hasSize(databaseSizeBeforeUpdate);
        Sedes testSedes = sedesList.get(sedesList.size() - 1);
        assertThat(testSedes.getNombreSede()).isEqualTo(UPDATED_NOMBRE_SEDE);
        assertThat(testSedes.getCoordenadaX()).isEqualTo(UPDATED_COORDENADA_X);
        assertThat(testSedes.getCoordenadaY()).isEqualTo(UPDATED_COORDENADA_Y);
        assertThat(testSedes.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testSedes.getTelefonoComunidad()).isEqualTo(UPDATED_TELEFONO_COMUNIDAD);
        assertThat(testSedes.getTelefonoNegocio()).isEqualTo(UPDATED_TELEFONO_NEGOCIO);
        assertThat(testSedes.getDescripcionSede()).isEqualTo(UPDATED_DESCRIPCION_SEDE);
        assertThat(testSedes.getHorario()).isEqualTo(UPDATED_HORARIO);
        assertThat(testSedes.getVideo()).isEqualTo(UPDATED_VIDEO);
        assertThat(testSedes.getVideoContentType()).isEqualTo(UPDATED_VIDEO_CONTENT_TYPE);
        assertThat(testSedes.getImagen1()).isEqualTo(UPDATED_IMAGEN_1);
        assertThat(testSedes.getImagen1ContentType()).isEqualTo(UPDATED_IMAGEN_1_CONTENT_TYPE);
        assertThat(testSedes.getImagen2()).isEqualTo(UPDATED_IMAGEN_2);
        assertThat(testSedes.getImagen2ContentType()).isEqualTo(UPDATED_IMAGEN_2_CONTENT_TYPE);
        assertThat(testSedes.getImagen3()).isEqualTo(UPDATED_IMAGEN_3);
        assertThat(testSedes.getImagen3ContentType()).isEqualTo(UPDATED_IMAGEN_3_CONTENT_TYPE);
        assertThat(testSedes.getImagen4()).isEqualTo(UPDATED_IMAGEN_4);
        assertThat(testSedes.getImagen4ContentType()).isEqualTo(UPDATED_IMAGEN_4_CONTENT_TYPE);
        assertThat(testSedes.getImagen5()).isEqualTo(UPDATED_IMAGEN_5);
        assertThat(testSedes.getImagen5ContentType()).isEqualTo(UPDATED_IMAGEN_5_CONTENT_TYPE);
        assertThat(testSedes.getImagen6()).isEqualTo(UPDATED_IMAGEN_6);
        assertThat(testSedes.getImagen6ContentType()).isEqualTo(UPDATED_IMAGEN_6_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingSedes() throws Exception {
        int databaseSizeBeforeUpdate = sedesRepository.findAll().size();

        // Create the Sedes

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSedesMockMvc.perform(put("/api/sedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sedes)))
            .andExpect(status().isBadRequest());

        // Validate the Sedes in the database
        List<Sedes> sedesList = sedesRepository.findAll();
        assertThat(sedesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSedes() throws Exception {
        // Initialize the database
        sedesRepository.saveAndFlush(sedes);

        int databaseSizeBeforeDelete = sedesRepository.findAll().size();

        // Delete the sedes
        restSedesMockMvc.perform(delete("/api/sedes/{id}", sedes.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Sedes> sedesList = sedesRepository.findAll();
        assertThat(sedesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sedes.class);
        Sedes sedes1 = new Sedes();
        sedes1.setId(1L);
        Sedes sedes2 = new Sedes();
        sedes2.setId(sedes1.getId());
        assertThat(sedes1).isEqualTo(sedes2);
        sedes2.setId(2L);
        assertThat(sedes1).isNotEqualTo(sedes2);
        sedes1.setId(null);
        assertThat(sedes1).isNotEqualTo(sedes2);
    }
}
