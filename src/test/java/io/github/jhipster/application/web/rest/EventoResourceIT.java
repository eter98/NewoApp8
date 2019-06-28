package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.Evento;
import io.github.jhipster.application.repository.EventoRepository;
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

import io.github.jhipster.application.domain.enumeration.TipoConsumod;
import io.github.jhipster.application.domain.enumeration.Impuestod;
import io.github.jhipster.application.domain.enumeration.Categoriad;
/**
 * Integration tests for the {@Link EventoResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class EventoResourceIT {

    private static final String DEFAULT_NOMBRE_EVENTO = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_EVENTO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_AUDIO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_AUDIO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_AUDIO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_AUDIO_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_VIDEO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_VIDEO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_VIDEO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_VIDEO_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_FOTO_1 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO_1 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_1_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_1_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_FOTO_2 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO_2 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_2_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_2_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BANNER = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BANNER = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BANNER_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BANNER_CONTENT_TYPE = "image/png";

    private static final TipoConsumod DEFAULT_TIPO_CONSUMO = TipoConsumod.GRATIS;
    private static final TipoConsumod UPDATED_TIPO_CONSUMO = TipoConsumod.PAGO;

    private static final Float DEFAULT_VALOR = 1F;
    private static final Float UPDATED_VALOR = 2F;

    private static final Impuestod DEFAULT_IMPUESTO = Impuestod.IVA19;
    private static final Impuestod UPDATED_IMPUESTO = Impuestod.IVA6;

    private static final Categoriad DEFAULT_TIPO_EVENTO = Categoriad.GENERAL;
    private static final Categoriad UPDATED_TIPO_EVENTO = Categoriad.DE_GRUPO;

    @Autowired
    private EventoRepository eventoRepository;

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

    private MockMvc restEventoMockMvc;

    private Evento evento;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EventoResource eventoResource = new EventoResource(eventoRepository);
        this.restEventoMockMvc = MockMvcBuilders.standaloneSetup(eventoResource)
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
    public static Evento createEntity(EntityManager em) {
        Evento evento = new Evento()
            .nombreEvento(DEFAULT_NOMBRE_EVENTO)
            .descripcion(DEFAULT_DESCRIPCION)
            .audio(DEFAULT_AUDIO)
            .audioContentType(DEFAULT_AUDIO_CONTENT_TYPE)
            .video(DEFAULT_VIDEO)
            .videoContentType(DEFAULT_VIDEO_CONTENT_TYPE)
            .foto1(DEFAULT_FOTO_1)
            .foto1ContentType(DEFAULT_FOTO_1_CONTENT_TYPE)
            .foto2(DEFAULT_FOTO_2)
            .foto2ContentType(DEFAULT_FOTO_2_CONTENT_TYPE)
            .banner(DEFAULT_BANNER)
            .bannerContentType(DEFAULT_BANNER_CONTENT_TYPE)
            .tipoConsumo(DEFAULT_TIPO_CONSUMO)
            .valor(DEFAULT_VALOR)
            .impuesto(DEFAULT_IMPUESTO)
            .tipoEvento(DEFAULT_TIPO_EVENTO);
        return evento;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Evento createUpdatedEntity(EntityManager em) {
        Evento evento = new Evento()
            .nombreEvento(UPDATED_NOMBRE_EVENTO)
            .descripcion(UPDATED_DESCRIPCION)
            .audio(UPDATED_AUDIO)
            .audioContentType(UPDATED_AUDIO_CONTENT_TYPE)
            .video(UPDATED_VIDEO)
            .videoContentType(UPDATED_VIDEO_CONTENT_TYPE)
            .foto1(UPDATED_FOTO_1)
            .foto1ContentType(UPDATED_FOTO_1_CONTENT_TYPE)
            .foto2(UPDATED_FOTO_2)
            .foto2ContentType(UPDATED_FOTO_2_CONTENT_TYPE)
            .banner(UPDATED_BANNER)
            .bannerContentType(UPDATED_BANNER_CONTENT_TYPE)
            .tipoConsumo(UPDATED_TIPO_CONSUMO)
            .valor(UPDATED_VALOR)
            .impuesto(UPDATED_IMPUESTO)
            .tipoEvento(UPDATED_TIPO_EVENTO);
        return evento;
    }

    @BeforeEach
    public void initTest() {
        evento = createEntity(em);
    }

    @Test
    @Transactional
    public void createEvento() throws Exception {
        int databaseSizeBeforeCreate = eventoRepository.findAll().size();

        // Create the Evento
        restEventoMockMvc.perform(post("/api/eventos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(evento)))
            .andExpect(status().isCreated());

        // Validate the Evento in the database
        List<Evento> eventoList = eventoRepository.findAll();
        assertThat(eventoList).hasSize(databaseSizeBeforeCreate + 1);
        Evento testEvento = eventoList.get(eventoList.size() - 1);
        assertThat(testEvento.getNombreEvento()).isEqualTo(DEFAULT_NOMBRE_EVENTO);
        assertThat(testEvento.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testEvento.getAudio()).isEqualTo(DEFAULT_AUDIO);
        assertThat(testEvento.getAudioContentType()).isEqualTo(DEFAULT_AUDIO_CONTENT_TYPE);
        assertThat(testEvento.getVideo()).isEqualTo(DEFAULT_VIDEO);
        assertThat(testEvento.getVideoContentType()).isEqualTo(DEFAULT_VIDEO_CONTENT_TYPE);
        assertThat(testEvento.getFoto1()).isEqualTo(DEFAULT_FOTO_1);
        assertThat(testEvento.getFoto1ContentType()).isEqualTo(DEFAULT_FOTO_1_CONTENT_TYPE);
        assertThat(testEvento.getFoto2()).isEqualTo(DEFAULT_FOTO_2);
        assertThat(testEvento.getFoto2ContentType()).isEqualTo(DEFAULT_FOTO_2_CONTENT_TYPE);
        assertThat(testEvento.getBanner()).isEqualTo(DEFAULT_BANNER);
        assertThat(testEvento.getBannerContentType()).isEqualTo(DEFAULT_BANNER_CONTENT_TYPE);
        assertThat(testEvento.getTipoConsumo()).isEqualTo(DEFAULT_TIPO_CONSUMO);
        assertThat(testEvento.getValor()).isEqualTo(DEFAULT_VALOR);
        assertThat(testEvento.getImpuesto()).isEqualTo(DEFAULT_IMPUESTO);
        assertThat(testEvento.getTipoEvento()).isEqualTo(DEFAULT_TIPO_EVENTO);
    }

    @Test
    @Transactional
    public void createEventoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eventoRepository.findAll().size();

        // Create the Evento with an existing ID
        evento.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEventoMockMvc.perform(post("/api/eventos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(evento)))
            .andExpect(status().isBadRequest());

        // Validate the Evento in the database
        List<Evento> eventoList = eventoRepository.findAll();
        assertThat(eventoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEventos() throws Exception {
        // Initialize the database
        eventoRepository.saveAndFlush(evento);

        // Get all the eventoList
        restEventoMockMvc.perform(get("/api/eventos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(evento.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreEvento").value(hasItem(DEFAULT_NOMBRE_EVENTO.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].audioContentType").value(hasItem(DEFAULT_AUDIO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].audio").value(hasItem(Base64Utils.encodeToString(DEFAULT_AUDIO))))
            .andExpect(jsonPath("$.[*].videoContentType").value(hasItem(DEFAULT_VIDEO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].video").value(hasItem(Base64Utils.encodeToString(DEFAULT_VIDEO))))
            .andExpect(jsonPath("$.[*].foto1ContentType").value(hasItem(DEFAULT_FOTO_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto1").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_1))))
            .andExpect(jsonPath("$.[*].foto2ContentType").value(hasItem(DEFAULT_FOTO_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto2").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_2))))
            .andExpect(jsonPath("$.[*].bannerContentType").value(hasItem(DEFAULT_BANNER_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].banner").value(hasItem(Base64Utils.encodeToString(DEFAULT_BANNER))))
            .andExpect(jsonPath("$.[*].tipoConsumo").value(hasItem(DEFAULT_TIPO_CONSUMO.toString())))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR.doubleValue())))
            .andExpect(jsonPath("$.[*].impuesto").value(hasItem(DEFAULT_IMPUESTO.toString())))
            .andExpect(jsonPath("$.[*].tipoEvento").value(hasItem(DEFAULT_TIPO_EVENTO.toString())));
    }
    
    @Test
    @Transactional
    public void getEvento() throws Exception {
        // Initialize the database
        eventoRepository.saveAndFlush(evento);

        // Get the evento
        restEventoMockMvc.perform(get("/api/eventos/{id}", evento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(evento.getId().intValue()))
            .andExpect(jsonPath("$.nombreEvento").value(DEFAULT_NOMBRE_EVENTO.toString()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.audioContentType").value(DEFAULT_AUDIO_CONTENT_TYPE))
            .andExpect(jsonPath("$.audio").value(Base64Utils.encodeToString(DEFAULT_AUDIO)))
            .andExpect(jsonPath("$.videoContentType").value(DEFAULT_VIDEO_CONTENT_TYPE))
            .andExpect(jsonPath("$.video").value(Base64Utils.encodeToString(DEFAULT_VIDEO)))
            .andExpect(jsonPath("$.foto1ContentType").value(DEFAULT_FOTO_1_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto1").value(Base64Utils.encodeToString(DEFAULT_FOTO_1)))
            .andExpect(jsonPath("$.foto2ContentType").value(DEFAULT_FOTO_2_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto2").value(Base64Utils.encodeToString(DEFAULT_FOTO_2)))
            .andExpect(jsonPath("$.bannerContentType").value(DEFAULT_BANNER_CONTENT_TYPE))
            .andExpect(jsonPath("$.banner").value(Base64Utils.encodeToString(DEFAULT_BANNER)))
            .andExpect(jsonPath("$.tipoConsumo").value(DEFAULT_TIPO_CONSUMO.toString()))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR.doubleValue()))
            .andExpect(jsonPath("$.impuesto").value(DEFAULT_IMPUESTO.toString()))
            .andExpect(jsonPath("$.tipoEvento").value(DEFAULT_TIPO_EVENTO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEvento() throws Exception {
        // Get the evento
        restEventoMockMvc.perform(get("/api/eventos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEvento() throws Exception {
        // Initialize the database
        eventoRepository.saveAndFlush(evento);

        int databaseSizeBeforeUpdate = eventoRepository.findAll().size();

        // Update the evento
        Evento updatedEvento = eventoRepository.findById(evento.getId()).get();
        // Disconnect from session so that the updates on updatedEvento are not directly saved in db
        em.detach(updatedEvento);
        updatedEvento
            .nombreEvento(UPDATED_NOMBRE_EVENTO)
            .descripcion(UPDATED_DESCRIPCION)
            .audio(UPDATED_AUDIO)
            .audioContentType(UPDATED_AUDIO_CONTENT_TYPE)
            .video(UPDATED_VIDEO)
            .videoContentType(UPDATED_VIDEO_CONTENT_TYPE)
            .foto1(UPDATED_FOTO_1)
            .foto1ContentType(UPDATED_FOTO_1_CONTENT_TYPE)
            .foto2(UPDATED_FOTO_2)
            .foto2ContentType(UPDATED_FOTO_2_CONTENT_TYPE)
            .banner(UPDATED_BANNER)
            .bannerContentType(UPDATED_BANNER_CONTENT_TYPE)
            .tipoConsumo(UPDATED_TIPO_CONSUMO)
            .valor(UPDATED_VALOR)
            .impuesto(UPDATED_IMPUESTO)
            .tipoEvento(UPDATED_TIPO_EVENTO);

        restEventoMockMvc.perform(put("/api/eventos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEvento)))
            .andExpect(status().isOk());

        // Validate the Evento in the database
        List<Evento> eventoList = eventoRepository.findAll();
        assertThat(eventoList).hasSize(databaseSizeBeforeUpdate);
        Evento testEvento = eventoList.get(eventoList.size() - 1);
        assertThat(testEvento.getNombreEvento()).isEqualTo(UPDATED_NOMBRE_EVENTO);
        assertThat(testEvento.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testEvento.getAudio()).isEqualTo(UPDATED_AUDIO);
        assertThat(testEvento.getAudioContentType()).isEqualTo(UPDATED_AUDIO_CONTENT_TYPE);
        assertThat(testEvento.getVideo()).isEqualTo(UPDATED_VIDEO);
        assertThat(testEvento.getVideoContentType()).isEqualTo(UPDATED_VIDEO_CONTENT_TYPE);
        assertThat(testEvento.getFoto1()).isEqualTo(UPDATED_FOTO_1);
        assertThat(testEvento.getFoto1ContentType()).isEqualTo(UPDATED_FOTO_1_CONTENT_TYPE);
        assertThat(testEvento.getFoto2()).isEqualTo(UPDATED_FOTO_2);
        assertThat(testEvento.getFoto2ContentType()).isEqualTo(UPDATED_FOTO_2_CONTENT_TYPE);
        assertThat(testEvento.getBanner()).isEqualTo(UPDATED_BANNER);
        assertThat(testEvento.getBannerContentType()).isEqualTo(UPDATED_BANNER_CONTENT_TYPE);
        assertThat(testEvento.getTipoConsumo()).isEqualTo(UPDATED_TIPO_CONSUMO);
        assertThat(testEvento.getValor()).isEqualTo(UPDATED_VALOR);
        assertThat(testEvento.getImpuesto()).isEqualTo(UPDATED_IMPUESTO);
        assertThat(testEvento.getTipoEvento()).isEqualTo(UPDATED_TIPO_EVENTO);
    }

    @Test
    @Transactional
    public void updateNonExistingEvento() throws Exception {
        int databaseSizeBeforeUpdate = eventoRepository.findAll().size();

        // Create the Evento

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEventoMockMvc.perform(put("/api/eventos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(evento)))
            .andExpect(status().isBadRequest());

        // Validate the Evento in the database
        List<Evento> eventoList = eventoRepository.findAll();
        assertThat(eventoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEvento() throws Exception {
        // Initialize the database
        eventoRepository.saveAndFlush(evento);

        int databaseSizeBeforeDelete = eventoRepository.findAll().size();

        // Delete the evento
        restEventoMockMvc.perform(delete("/api/eventos/{id}", evento.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Evento> eventoList = eventoRepository.findAll();
        assertThat(eventoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Evento.class);
        Evento evento1 = new Evento();
        evento1.setId(1L);
        Evento evento2 = new Evento();
        evento2.setId(evento1.getId());
        assertThat(evento1).isEqualTo(evento2);
        evento2.setId(2L);
        assertThat(evento1).isNotEqualTo(evento2);
        evento1.setId(null);
        assertThat(evento1).isNotEqualTo(evento2);
    }
}
