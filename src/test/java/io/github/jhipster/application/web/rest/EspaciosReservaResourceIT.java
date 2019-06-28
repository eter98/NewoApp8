package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.EspaciosReserva;
import io.github.jhipster.application.repository.EspaciosReservaRepository;
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
 * Integration tests for the {@Link EspaciosReservaResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class EspaciosReservaResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_FACILIDADES = "AAAAAAAAAA";
    private static final String UPDATED_FACILIDADES = "BBBBBBBBBB";

    private static final Integer DEFAULT_CAPACIDAD = 1;
    private static final Integer UPDATED_CAPACIDAD = 2;

    private static final String DEFAULT_APERTURA = "AAAAAAAAAA";
    private static final String UPDATED_APERTURA = "BBBBBBBBBB";

    private static final String DEFAULT_CIERRE = "AAAAAAAAAA";
    private static final String UPDATED_CIERRE = "BBBBBBBBBB";

    private static final Integer DEFAULT_TARIFA_1_HORA = 1;
    private static final Integer UPDATED_TARIFA_1_HORA = 2;

    private static final Integer DEFAULT_TARIFA_2_HORA = 1;
    private static final Integer UPDATED_TARIFA_2_HORA = 2;

    private static final Integer DEFAULT_TARIFA_3_HORA = 1;
    private static final Integer UPDATED_TARIFA_3_HORA = 2;

    private static final Integer DEFAULT_TARIFA_4_HORA = 1;
    private static final Integer UPDATED_TARIFA_4_HORA = 2;

    private static final Integer DEFAULT_TARIFA_5_HORA = 1;
    private static final Integer UPDATED_TARIFA_5_HORA = 2;

    private static final Integer DEFAULT_TARIFA_6_HORA = 1;
    private static final Integer UPDATED_TARIFA_6_HORA = 2;

    private static final Integer DEFAULT_TARIFA_7_HORA = 1;
    private static final Integer UPDATED_TARIFA_7_HORA = 2;

    private static final Integer DEFAULT_TARIFA_8_HORA = 1;
    private static final Integer UPDATED_TARIFA_8_HORA = 2;

    private static final Impuestod DEFAULT_IMPUESTO = Impuestod.IVA19;
    private static final Impuestod UPDATED_IMPUESTO = Impuestod.IVA6;

    private static final String DEFAULT_WIFI = "AAAAAAAAAA";
    private static final String UPDATED_WIFI = "BBBBBBBBBB";

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
    private EspaciosReservaRepository espaciosReservaRepository;

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

    private MockMvc restEspaciosReservaMockMvc;

    private EspaciosReserva espaciosReserva;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EspaciosReservaResource espaciosReservaResource = new EspaciosReservaResource(espaciosReservaRepository);
        this.restEspaciosReservaMockMvc = MockMvcBuilders.standaloneSetup(espaciosReservaResource)
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
    public static EspaciosReserva createEntity(EntityManager em) {
        EspaciosReserva espaciosReserva = new EspaciosReserva()
            .nombre(DEFAULT_NOMBRE)
            .descripcion(DEFAULT_DESCRIPCION)
            .facilidades(DEFAULT_FACILIDADES)
            .capacidad(DEFAULT_CAPACIDAD)
            .apertura(DEFAULT_APERTURA)
            .cierre(DEFAULT_CIERRE)
            .tarifa1Hora(DEFAULT_TARIFA_1_HORA)
            .tarifa2Hora(DEFAULT_TARIFA_2_HORA)
            .tarifa3Hora(DEFAULT_TARIFA_3_HORA)
            .tarifa4Hora(DEFAULT_TARIFA_4_HORA)
            .tarifa5Hora(DEFAULT_TARIFA_5_HORA)
            .tarifa6Hora(DEFAULT_TARIFA_6_HORA)
            .tarifa7Hora(DEFAULT_TARIFA_7_HORA)
            .tarifa8Hora(DEFAULT_TARIFA_8_HORA)
            .impuesto(DEFAULT_IMPUESTO)
            .wifi(DEFAULT_WIFI)
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
        return espaciosReserva;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EspaciosReserva createUpdatedEntity(EntityManager em) {
        EspaciosReserva espaciosReserva = new EspaciosReserva()
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .facilidades(UPDATED_FACILIDADES)
            .capacidad(UPDATED_CAPACIDAD)
            .apertura(UPDATED_APERTURA)
            .cierre(UPDATED_CIERRE)
            .tarifa1Hora(UPDATED_TARIFA_1_HORA)
            .tarifa2Hora(UPDATED_TARIFA_2_HORA)
            .tarifa3Hora(UPDATED_TARIFA_3_HORA)
            .tarifa4Hora(UPDATED_TARIFA_4_HORA)
            .tarifa5Hora(UPDATED_TARIFA_5_HORA)
            .tarifa6Hora(UPDATED_TARIFA_6_HORA)
            .tarifa7Hora(UPDATED_TARIFA_7_HORA)
            .tarifa8Hora(UPDATED_TARIFA_8_HORA)
            .impuesto(UPDATED_IMPUESTO)
            .wifi(UPDATED_WIFI)
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
        return espaciosReserva;
    }

    @BeforeEach
    public void initTest() {
        espaciosReserva = createEntity(em);
    }

    @Test
    @Transactional
    public void createEspaciosReserva() throws Exception {
        int databaseSizeBeforeCreate = espaciosReservaRepository.findAll().size();

        // Create the EspaciosReserva
        restEspaciosReservaMockMvc.perform(post("/api/espacios-reservas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(espaciosReserva)))
            .andExpect(status().isCreated());

        // Validate the EspaciosReserva in the database
        List<EspaciosReserva> espaciosReservaList = espaciosReservaRepository.findAll();
        assertThat(espaciosReservaList).hasSize(databaseSizeBeforeCreate + 1);
        EspaciosReserva testEspaciosReserva = espaciosReservaList.get(espaciosReservaList.size() - 1);
        assertThat(testEspaciosReserva.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testEspaciosReserva.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testEspaciosReserva.getFacilidades()).isEqualTo(DEFAULT_FACILIDADES);
        assertThat(testEspaciosReserva.getCapacidad()).isEqualTo(DEFAULT_CAPACIDAD);
        assertThat(testEspaciosReserva.getApertura()).isEqualTo(DEFAULT_APERTURA);
        assertThat(testEspaciosReserva.getCierre()).isEqualTo(DEFAULT_CIERRE);
        assertThat(testEspaciosReserva.getTarifa1Hora()).isEqualTo(DEFAULT_TARIFA_1_HORA);
        assertThat(testEspaciosReserva.getTarifa2Hora()).isEqualTo(DEFAULT_TARIFA_2_HORA);
        assertThat(testEspaciosReserva.getTarifa3Hora()).isEqualTo(DEFAULT_TARIFA_3_HORA);
        assertThat(testEspaciosReserva.getTarifa4Hora()).isEqualTo(DEFAULT_TARIFA_4_HORA);
        assertThat(testEspaciosReserva.getTarifa5Hora()).isEqualTo(DEFAULT_TARIFA_5_HORA);
        assertThat(testEspaciosReserva.getTarifa6Hora()).isEqualTo(DEFAULT_TARIFA_6_HORA);
        assertThat(testEspaciosReserva.getTarifa7Hora()).isEqualTo(DEFAULT_TARIFA_7_HORA);
        assertThat(testEspaciosReserva.getTarifa8Hora()).isEqualTo(DEFAULT_TARIFA_8_HORA);
        assertThat(testEspaciosReserva.getImpuesto()).isEqualTo(DEFAULT_IMPUESTO);
        assertThat(testEspaciosReserva.getWifi()).isEqualTo(DEFAULT_WIFI);
        assertThat(testEspaciosReserva.getVideo()).isEqualTo(DEFAULT_VIDEO);
        assertThat(testEspaciosReserva.getVideoContentType()).isEqualTo(DEFAULT_VIDEO_CONTENT_TYPE);
        assertThat(testEspaciosReserva.getImagen1()).isEqualTo(DEFAULT_IMAGEN_1);
        assertThat(testEspaciosReserva.getImagen1ContentType()).isEqualTo(DEFAULT_IMAGEN_1_CONTENT_TYPE);
        assertThat(testEspaciosReserva.getImagen2()).isEqualTo(DEFAULT_IMAGEN_2);
        assertThat(testEspaciosReserva.getImagen2ContentType()).isEqualTo(DEFAULT_IMAGEN_2_CONTENT_TYPE);
        assertThat(testEspaciosReserva.getImagen3()).isEqualTo(DEFAULT_IMAGEN_3);
        assertThat(testEspaciosReserva.getImagen3ContentType()).isEqualTo(DEFAULT_IMAGEN_3_CONTENT_TYPE);
        assertThat(testEspaciosReserva.getImagen4()).isEqualTo(DEFAULT_IMAGEN_4);
        assertThat(testEspaciosReserva.getImagen4ContentType()).isEqualTo(DEFAULT_IMAGEN_4_CONTENT_TYPE);
        assertThat(testEspaciosReserva.getImagen5()).isEqualTo(DEFAULT_IMAGEN_5);
        assertThat(testEspaciosReserva.getImagen5ContentType()).isEqualTo(DEFAULT_IMAGEN_5_CONTENT_TYPE);
        assertThat(testEspaciosReserva.getImagen6()).isEqualTo(DEFAULT_IMAGEN_6);
        assertThat(testEspaciosReserva.getImagen6ContentType()).isEqualTo(DEFAULT_IMAGEN_6_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createEspaciosReservaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = espaciosReservaRepository.findAll().size();

        // Create the EspaciosReserva with an existing ID
        espaciosReserva.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEspaciosReservaMockMvc.perform(post("/api/espacios-reservas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(espaciosReserva)))
            .andExpect(status().isBadRequest());

        // Validate the EspaciosReserva in the database
        List<EspaciosReserva> espaciosReservaList = espaciosReservaRepository.findAll();
        assertThat(espaciosReservaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEspaciosReservas() throws Exception {
        // Initialize the database
        espaciosReservaRepository.saveAndFlush(espaciosReserva);

        // Get all the espaciosReservaList
        restEspaciosReservaMockMvc.perform(get("/api/espacios-reservas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(espaciosReserva.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].facilidades").value(hasItem(DEFAULT_FACILIDADES.toString())))
            .andExpect(jsonPath("$.[*].capacidad").value(hasItem(DEFAULT_CAPACIDAD)))
            .andExpect(jsonPath("$.[*].apertura").value(hasItem(DEFAULT_APERTURA.toString())))
            .andExpect(jsonPath("$.[*].cierre").value(hasItem(DEFAULT_CIERRE.toString())))
            .andExpect(jsonPath("$.[*].tarifa1Hora").value(hasItem(DEFAULT_TARIFA_1_HORA)))
            .andExpect(jsonPath("$.[*].tarifa2Hora").value(hasItem(DEFAULT_TARIFA_2_HORA)))
            .andExpect(jsonPath("$.[*].tarifa3Hora").value(hasItem(DEFAULT_TARIFA_3_HORA)))
            .andExpect(jsonPath("$.[*].tarifa4Hora").value(hasItem(DEFAULT_TARIFA_4_HORA)))
            .andExpect(jsonPath("$.[*].tarifa5Hora").value(hasItem(DEFAULT_TARIFA_5_HORA)))
            .andExpect(jsonPath("$.[*].tarifa6Hora").value(hasItem(DEFAULT_TARIFA_6_HORA)))
            .andExpect(jsonPath("$.[*].tarifa7Hora").value(hasItem(DEFAULT_TARIFA_7_HORA)))
            .andExpect(jsonPath("$.[*].tarifa8Hora").value(hasItem(DEFAULT_TARIFA_8_HORA)))
            .andExpect(jsonPath("$.[*].impuesto").value(hasItem(DEFAULT_IMPUESTO.toString())))
            .andExpect(jsonPath("$.[*].wifi").value(hasItem(DEFAULT_WIFI.toString())))
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
    public void getEspaciosReserva() throws Exception {
        // Initialize the database
        espaciosReservaRepository.saveAndFlush(espaciosReserva);

        // Get the espaciosReserva
        restEspaciosReservaMockMvc.perform(get("/api/espacios-reservas/{id}", espaciosReserva.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(espaciosReserva.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.facilidades").value(DEFAULT_FACILIDADES.toString()))
            .andExpect(jsonPath("$.capacidad").value(DEFAULT_CAPACIDAD))
            .andExpect(jsonPath("$.apertura").value(DEFAULT_APERTURA.toString()))
            .andExpect(jsonPath("$.cierre").value(DEFAULT_CIERRE.toString()))
            .andExpect(jsonPath("$.tarifa1Hora").value(DEFAULT_TARIFA_1_HORA))
            .andExpect(jsonPath("$.tarifa2Hora").value(DEFAULT_TARIFA_2_HORA))
            .andExpect(jsonPath("$.tarifa3Hora").value(DEFAULT_TARIFA_3_HORA))
            .andExpect(jsonPath("$.tarifa4Hora").value(DEFAULT_TARIFA_4_HORA))
            .andExpect(jsonPath("$.tarifa5Hora").value(DEFAULT_TARIFA_5_HORA))
            .andExpect(jsonPath("$.tarifa6Hora").value(DEFAULT_TARIFA_6_HORA))
            .andExpect(jsonPath("$.tarifa7Hora").value(DEFAULT_TARIFA_7_HORA))
            .andExpect(jsonPath("$.tarifa8Hora").value(DEFAULT_TARIFA_8_HORA))
            .andExpect(jsonPath("$.impuesto").value(DEFAULT_IMPUESTO.toString()))
            .andExpect(jsonPath("$.wifi").value(DEFAULT_WIFI.toString()))
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
    public void getNonExistingEspaciosReserva() throws Exception {
        // Get the espaciosReserva
        restEspaciosReservaMockMvc.perform(get("/api/espacios-reservas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEspaciosReserva() throws Exception {
        // Initialize the database
        espaciosReservaRepository.saveAndFlush(espaciosReserva);

        int databaseSizeBeforeUpdate = espaciosReservaRepository.findAll().size();

        // Update the espaciosReserva
        EspaciosReserva updatedEspaciosReserva = espaciosReservaRepository.findById(espaciosReserva.getId()).get();
        // Disconnect from session so that the updates on updatedEspaciosReserva are not directly saved in db
        em.detach(updatedEspaciosReserva);
        updatedEspaciosReserva
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .facilidades(UPDATED_FACILIDADES)
            .capacidad(UPDATED_CAPACIDAD)
            .apertura(UPDATED_APERTURA)
            .cierre(UPDATED_CIERRE)
            .tarifa1Hora(UPDATED_TARIFA_1_HORA)
            .tarifa2Hora(UPDATED_TARIFA_2_HORA)
            .tarifa3Hora(UPDATED_TARIFA_3_HORA)
            .tarifa4Hora(UPDATED_TARIFA_4_HORA)
            .tarifa5Hora(UPDATED_TARIFA_5_HORA)
            .tarifa6Hora(UPDATED_TARIFA_6_HORA)
            .tarifa7Hora(UPDATED_TARIFA_7_HORA)
            .tarifa8Hora(UPDATED_TARIFA_8_HORA)
            .impuesto(UPDATED_IMPUESTO)
            .wifi(UPDATED_WIFI)
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

        restEspaciosReservaMockMvc.perform(put("/api/espacios-reservas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEspaciosReserva)))
            .andExpect(status().isOk());

        // Validate the EspaciosReserva in the database
        List<EspaciosReserva> espaciosReservaList = espaciosReservaRepository.findAll();
        assertThat(espaciosReservaList).hasSize(databaseSizeBeforeUpdate);
        EspaciosReserva testEspaciosReserva = espaciosReservaList.get(espaciosReservaList.size() - 1);
        assertThat(testEspaciosReserva.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testEspaciosReserva.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testEspaciosReserva.getFacilidades()).isEqualTo(UPDATED_FACILIDADES);
        assertThat(testEspaciosReserva.getCapacidad()).isEqualTo(UPDATED_CAPACIDAD);
        assertThat(testEspaciosReserva.getApertura()).isEqualTo(UPDATED_APERTURA);
        assertThat(testEspaciosReserva.getCierre()).isEqualTo(UPDATED_CIERRE);
        assertThat(testEspaciosReserva.getTarifa1Hora()).isEqualTo(UPDATED_TARIFA_1_HORA);
        assertThat(testEspaciosReserva.getTarifa2Hora()).isEqualTo(UPDATED_TARIFA_2_HORA);
        assertThat(testEspaciosReserva.getTarifa3Hora()).isEqualTo(UPDATED_TARIFA_3_HORA);
        assertThat(testEspaciosReserva.getTarifa4Hora()).isEqualTo(UPDATED_TARIFA_4_HORA);
        assertThat(testEspaciosReserva.getTarifa5Hora()).isEqualTo(UPDATED_TARIFA_5_HORA);
        assertThat(testEspaciosReserva.getTarifa6Hora()).isEqualTo(UPDATED_TARIFA_6_HORA);
        assertThat(testEspaciosReserva.getTarifa7Hora()).isEqualTo(UPDATED_TARIFA_7_HORA);
        assertThat(testEspaciosReserva.getTarifa8Hora()).isEqualTo(UPDATED_TARIFA_8_HORA);
        assertThat(testEspaciosReserva.getImpuesto()).isEqualTo(UPDATED_IMPUESTO);
        assertThat(testEspaciosReserva.getWifi()).isEqualTo(UPDATED_WIFI);
        assertThat(testEspaciosReserva.getVideo()).isEqualTo(UPDATED_VIDEO);
        assertThat(testEspaciosReserva.getVideoContentType()).isEqualTo(UPDATED_VIDEO_CONTENT_TYPE);
        assertThat(testEspaciosReserva.getImagen1()).isEqualTo(UPDATED_IMAGEN_1);
        assertThat(testEspaciosReserva.getImagen1ContentType()).isEqualTo(UPDATED_IMAGEN_1_CONTENT_TYPE);
        assertThat(testEspaciosReserva.getImagen2()).isEqualTo(UPDATED_IMAGEN_2);
        assertThat(testEspaciosReserva.getImagen2ContentType()).isEqualTo(UPDATED_IMAGEN_2_CONTENT_TYPE);
        assertThat(testEspaciosReserva.getImagen3()).isEqualTo(UPDATED_IMAGEN_3);
        assertThat(testEspaciosReserva.getImagen3ContentType()).isEqualTo(UPDATED_IMAGEN_3_CONTENT_TYPE);
        assertThat(testEspaciosReserva.getImagen4()).isEqualTo(UPDATED_IMAGEN_4);
        assertThat(testEspaciosReserva.getImagen4ContentType()).isEqualTo(UPDATED_IMAGEN_4_CONTENT_TYPE);
        assertThat(testEspaciosReserva.getImagen5()).isEqualTo(UPDATED_IMAGEN_5);
        assertThat(testEspaciosReserva.getImagen5ContentType()).isEqualTo(UPDATED_IMAGEN_5_CONTENT_TYPE);
        assertThat(testEspaciosReserva.getImagen6()).isEqualTo(UPDATED_IMAGEN_6);
        assertThat(testEspaciosReserva.getImagen6ContentType()).isEqualTo(UPDATED_IMAGEN_6_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingEspaciosReserva() throws Exception {
        int databaseSizeBeforeUpdate = espaciosReservaRepository.findAll().size();

        // Create the EspaciosReserva

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEspaciosReservaMockMvc.perform(put("/api/espacios-reservas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(espaciosReserva)))
            .andExpect(status().isBadRequest());

        // Validate the EspaciosReserva in the database
        List<EspaciosReserva> espaciosReservaList = espaciosReservaRepository.findAll();
        assertThat(espaciosReservaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEspaciosReserva() throws Exception {
        // Initialize the database
        espaciosReservaRepository.saveAndFlush(espaciosReserva);

        int databaseSizeBeforeDelete = espaciosReservaRepository.findAll().size();

        // Delete the espaciosReserva
        restEspaciosReservaMockMvc.perform(delete("/api/espacios-reservas/{id}", espaciosReserva.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EspaciosReserva> espaciosReservaList = espaciosReservaRepository.findAll();
        assertThat(espaciosReservaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EspaciosReserva.class);
        EspaciosReserva espaciosReserva1 = new EspaciosReserva();
        espaciosReserva1.setId(1L);
        EspaciosReserva espaciosReserva2 = new EspaciosReserva();
        espaciosReserva2.setId(espaciosReserva1.getId());
        assertThat(espaciosReserva1).isEqualTo(espaciosReserva2);
        espaciosReserva2.setId(2L);
        assertThat(espaciosReserva1).isNotEqualTo(espaciosReserva2);
        espaciosReserva1.setId(null);
        assertThat(espaciosReserva1).isNotEqualTo(espaciosReserva2);
    }
}
