package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.Grupos;
import io.github.jhipster.application.repository.GruposRepository;
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

import io.github.jhipster.application.domain.enumeration.TipoGrupod;
import io.github.jhipster.application.domain.enumeration.TipoConsumod;
import io.github.jhipster.application.domain.enumeration.Impuestod;
/**
 * Integration tests for the {@Link GruposResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class GruposResourceIT {

    private static final String DEFAULT_NOMBRE_GRUPO = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_GRUPO = "BBBBBBBBBB";

    private static final String DEFAULT_INSTAGRAM = "AAAAAAAAAA";
    private static final String UPDATED_INSTAGRAM = "BBBBBBBBBB";

    private static final String DEFAULT_FACEBOOK = "AAAAAAAAAA";
    private static final String UPDATED_FACEBOOK = "BBBBBBBBBB";

    private static final String DEFAULT_TWITER = "AAAAAAAAAA";
    private static final String UPDATED_TWITER = "BBBBBBBBBB";

    private static final String DEFAULT_LINKED_IN = "AAAAAAAAAA";
    private static final String UPDATED_LINKED_IN = "BBBBBBBBBB";

    private static final TipoGrupod DEFAULT_TIPO_GRUPO = TipoGrupod.INTERNO;
    private static final TipoGrupod UPDATED_TIPO_GRUPO = TipoGrupod.EXTERNO;

    private static final TipoConsumod DEFAULT_TIPO_CONSUMO = TipoConsumod.GRATIS;
    private static final TipoConsumod UPDATED_TIPO_CONSUMO = TipoConsumod.PAGO;

    private static final Integer DEFAULT_VALOR_SUSCRIPCION = 1;
    private static final Integer UPDATED_VALOR_SUSCRIPCION = 2;

    private static final Impuestod DEFAULT_IMPUESTO = Impuestod.IVA19;
    private static final Impuestod UPDATED_IMPUESTO = Impuestod.IVA6;

    private static final String DEFAULT_REGLAS_GRUPO = "AAAAAAAAAA";
    private static final String UPDATED_REGLAS_GRUPO = "BBBBBBBBBB";

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

    @Autowired
    private GruposRepository gruposRepository;

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

    private MockMvc restGruposMockMvc;

    private Grupos grupos;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GruposResource gruposResource = new GruposResource(gruposRepository);
        this.restGruposMockMvc = MockMvcBuilders.standaloneSetup(gruposResource)
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
    public static Grupos createEntity(EntityManager em) {
        Grupos grupos = new Grupos()
            .nombreGrupo(DEFAULT_NOMBRE_GRUPO)
            .instagram(DEFAULT_INSTAGRAM)
            .facebook(DEFAULT_FACEBOOK)
            .twiter(DEFAULT_TWITER)
            .linkedIn(DEFAULT_LINKED_IN)
            .tipoGrupo(DEFAULT_TIPO_GRUPO)
            .tipoConsumo(DEFAULT_TIPO_CONSUMO)
            .valorSuscripcion(DEFAULT_VALOR_SUSCRIPCION)
            .impuesto(DEFAULT_IMPUESTO)
            .reglasGrupo(DEFAULT_REGLAS_GRUPO)
            .audio(DEFAULT_AUDIO)
            .audioContentType(DEFAULT_AUDIO_CONTENT_TYPE)
            .video(DEFAULT_VIDEO)
            .videoContentType(DEFAULT_VIDEO_CONTENT_TYPE)
            .foto1(DEFAULT_FOTO_1)
            .foto1ContentType(DEFAULT_FOTO_1_CONTENT_TYPE)
            .foto2(DEFAULT_FOTO_2)
            .foto2ContentType(DEFAULT_FOTO_2_CONTENT_TYPE)
            .banner(DEFAULT_BANNER)
            .bannerContentType(DEFAULT_BANNER_CONTENT_TYPE);
        return grupos;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Grupos createUpdatedEntity(EntityManager em) {
        Grupos grupos = new Grupos()
            .nombreGrupo(UPDATED_NOMBRE_GRUPO)
            .instagram(UPDATED_INSTAGRAM)
            .facebook(UPDATED_FACEBOOK)
            .twiter(UPDATED_TWITER)
            .linkedIn(UPDATED_LINKED_IN)
            .tipoGrupo(UPDATED_TIPO_GRUPO)
            .tipoConsumo(UPDATED_TIPO_CONSUMO)
            .valorSuscripcion(UPDATED_VALOR_SUSCRIPCION)
            .impuesto(UPDATED_IMPUESTO)
            .reglasGrupo(UPDATED_REGLAS_GRUPO)
            .audio(UPDATED_AUDIO)
            .audioContentType(UPDATED_AUDIO_CONTENT_TYPE)
            .video(UPDATED_VIDEO)
            .videoContentType(UPDATED_VIDEO_CONTENT_TYPE)
            .foto1(UPDATED_FOTO_1)
            .foto1ContentType(UPDATED_FOTO_1_CONTENT_TYPE)
            .foto2(UPDATED_FOTO_2)
            .foto2ContentType(UPDATED_FOTO_2_CONTENT_TYPE)
            .banner(UPDATED_BANNER)
            .bannerContentType(UPDATED_BANNER_CONTENT_TYPE);
        return grupos;
    }

    @BeforeEach
    public void initTest() {
        grupos = createEntity(em);
    }

    @Test
    @Transactional
    public void createGrupos() throws Exception {
        int databaseSizeBeforeCreate = gruposRepository.findAll().size();

        // Create the Grupos
        restGruposMockMvc.perform(post("/api/grupos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupos)))
            .andExpect(status().isCreated());

        // Validate the Grupos in the database
        List<Grupos> gruposList = gruposRepository.findAll();
        assertThat(gruposList).hasSize(databaseSizeBeforeCreate + 1);
        Grupos testGrupos = gruposList.get(gruposList.size() - 1);
        assertThat(testGrupos.getNombreGrupo()).isEqualTo(DEFAULT_NOMBRE_GRUPO);
        assertThat(testGrupos.getInstagram()).isEqualTo(DEFAULT_INSTAGRAM);
        assertThat(testGrupos.getFacebook()).isEqualTo(DEFAULT_FACEBOOK);
        assertThat(testGrupos.getTwiter()).isEqualTo(DEFAULT_TWITER);
        assertThat(testGrupos.getLinkedIn()).isEqualTo(DEFAULT_LINKED_IN);
        assertThat(testGrupos.getTipoGrupo()).isEqualTo(DEFAULT_TIPO_GRUPO);
        assertThat(testGrupos.getTipoConsumo()).isEqualTo(DEFAULT_TIPO_CONSUMO);
        assertThat(testGrupos.getValorSuscripcion()).isEqualTo(DEFAULT_VALOR_SUSCRIPCION);
        assertThat(testGrupos.getImpuesto()).isEqualTo(DEFAULT_IMPUESTO);
        assertThat(testGrupos.getReglasGrupo()).isEqualTo(DEFAULT_REGLAS_GRUPO);
        assertThat(testGrupos.getAudio()).isEqualTo(DEFAULT_AUDIO);
        assertThat(testGrupos.getAudioContentType()).isEqualTo(DEFAULT_AUDIO_CONTENT_TYPE);
        assertThat(testGrupos.getVideo()).isEqualTo(DEFAULT_VIDEO);
        assertThat(testGrupos.getVideoContentType()).isEqualTo(DEFAULT_VIDEO_CONTENT_TYPE);
        assertThat(testGrupos.getFoto1()).isEqualTo(DEFAULT_FOTO_1);
        assertThat(testGrupos.getFoto1ContentType()).isEqualTo(DEFAULT_FOTO_1_CONTENT_TYPE);
        assertThat(testGrupos.getFoto2()).isEqualTo(DEFAULT_FOTO_2);
        assertThat(testGrupos.getFoto2ContentType()).isEqualTo(DEFAULT_FOTO_2_CONTENT_TYPE);
        assertThat(testGrupos.getBanner()).isEqualTo(DEFAULT_BANNER);
        assertThat(testGrupos.getBannerContentType()).isEqualTo(DEFAULT_BANNER_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createGruposWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gruposRepository.findAll().size();

        // Create the Grupos with an existing ID
        grupos.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGruposMockMvc.perform(post("/api/grupos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupos)))
            .andExpect(status().isBadRequest());

        // Validate the Grupos in the database
        List<Grupos> gruposList = gruposRepository.findAll();
        assertThat(gruposList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGrupos() throws Exception {
        // Initialize the database
        gruposRepository.saveAndFlush(grupos);

        // Get all the gruposList
        restGruposMockMvc.perform(get("/api/grupos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(grupos.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreGrupo").value(hasItem(DEFAULT_NOMBRE_GRUPO.toString())))
            .andExpect(jsonPath("$.[*].instagram").value(hasItem(DEFAULT_INSTAGRAM.toString())))
            .andExpect(jsonPath("$.[*].facebook").value(hasItem(DEFAULT_FACEBOOK.toString())))
            .andExpect(jsonPath("$.[*].twiter").value(hasItem(DEFAULT_TWITER.toString())))
            .andExpect(jsonPath("$.[*].linkedIn").value(hasItem(DEFAULT_LINKED_IN.toString())))
            .andExpect(jsonPath("$.[*].tipoGrupo").value(hasItem(DEFAULT_TIPO_GRUPO.toString())))
            .andExpect(jsonPath("$.[*].tipoConsumo").value(hasItem(DEFAULT_TIPO_CONSUMO.toString())))
            .andExpect(jsonPath("$.[*].valorSuscripcion").value(hasItem(DEFAULT_VALOR_SUSCRIPCION)))
            .andExpect(jsonPath("$.[*].impuesto").value(hasItem(DEFAULT_IMPUESTO.toString())))
            .andExpect(jsonPath("$.[*].reglasGrupo").value(hasItem(DEFAULT_REGLAS_GRUPO.toString())))
            .andExpect(jsonPath("$.[*].audioContentType").value(hasItem(DEFAULT_AUDIO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].audio").value(hasItem(Base64Utils.encodeToString(DEFAULT_AUDIO))))
            .andExpect(jsonPath("$.[*].videoContentType").value(hasItem(DEFAULT_VIDEO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].video").value(hasItem(Base64Utils.encodeToString(DEFAULT_VIDEO))))
            .andExpect(jsonPath("$.[*].foto1ContentType").value(hasItem(DEFAULT_FOTO_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto1").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_1))))
            .andExpect(jsonPath("$.[*].foto2ContentType").value(hasItem(DEFAULT_FOTO_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto2").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_2))))
            .andExpect(jsonPath("$.[*].bannerContentType").value(hasItem(DEFAULT_BANNER_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].banner").value(hasItem(Base64Utils.encodeToString(DEFAULT_BANNER))));
    }
    
    @Test
    @Transactional
    public void getGrupos() throws Exception {
        // Initialize the database
        gruposRepository.saveAndFlush(grupos);

        // Get the grupos
        restGruposMockMvc.perform(get("/api/grupos/{id}", grupos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(grupos.getId().intValue()))
            .andExpect(jsonPath("$.nombreGrupo").value(DEFAULT_NOMBRE_GRUPO.toString()))
            .andExpect(jsonPath("$.instagram").value(DEFAULT_INSTAGRAM.toString()))
            .andExpect(jsonPath("$.facebook").value(DEFAULT_FACEBOOK.toString()))
            .andExpect(jsonPath("$.twiter").value(DEFAULT_TWITER.toString()))
            .andExpect(jsonPath("$.linkedIn").value(DEFAULT_LINKED_IN.toString()))
            .andExpect(jsonPath("$.tipoGrupo").value(DEFAULT_TIPO_GRUPO.toString()))
            .andExpect(jsonPath("$.tipoConsumo").value(DEFAULT_TIPO_CONSUMO.toString()))
            .andExpect(jsonPath("$.valorSuscripcion").value(DEFAULT_VALOR_SUSCRIPCION))
            .andExpect(jsonPath("$.impuesto").value(DEFAULT_IMPUESTO.toString()))
            .andExpect(jsonPath("$.reglasGrupo").value(DEFAULT_REGLAS_GRUPO.toString()))
            .andExpect(jsonPath("$.audioContentType").value(DEFAULT_AUDIO_CONTENT_TYPE))
            .andExpect(jsonPath("$.audio").value(Base64Utils.encodeToString(DEFAULT_AUDIO)))
            .andExpect(jsonPath("$.videoContentType").value(DEFAULT_VIDEO_CONTENT_TYPE))
            .andExpect(jsonPath("$.video").value(Base64Utils.encodeToString(DEFAULT_VIDEO)))
            .andExpect(jsonPath("$.foto1ContentType").value(DEFAULT_FOTO_1_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto1").value(Base64Utils.encodeToString(DEFAULT_FOTO_1)))
            .andExpect(jsonPath("$.foto2ContentType").value(DEFAULT_FOTO_2_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto2").value(Base64Utils.encodeToString(DEFAULT_FOTO_2)))
            .andExpect(jsonPath("$.bannerContentType").value(DEFAULT_BANNER_CONTENT_TYPE))
            .andExpect(jsonPath("$.banner").value(Base64Utils.encodeToString(DEFAULT_BANNER)));
    }

    @Test
    @Transactional
    public void getNonExistingGrupos() throws Exception {
        // Get the grupos
        restGruposMockMvc.perform(get("/api/grupos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGrupos() throws Exception {
        // Initialize the database
        gruposRepository.saveAndFlush(grupos);

        int databaseSizeBeforeUpdate = gruposRepository.findAll().size();

        // Update the grupos
        Grupos updatedGrupos = gruposRepository.findById(grupos.getId()).get();
        // Disconnect from session so that the updates on updatedGrupos are not directly saved in db
        em.detach(updatedGrupos);
        updatedGrupos
            .nombreGrupo(UPDATED_NOMBRE_GRUPO)
            .instagram(UPDATED_INSTAGRAM)
            .facebook(UPDATED_FACEBOOK)
            .twiter(UPDATED_TWITER)
            .linkedIn(UPDATED_LINKED_IN)
            .tipoGrupo(UPDATED_TIPO_GRUPO)
            .tipoConsumo(UPDATED_TIPO_CONSUMO)
            .valorSuscripcion(UPDATED_VALOR_SUSCRIPCION)
            .impuesto(UPDATED_IMPUESTO)
            .reglasGrupo(UPDATED_REGLAS_GRUPO)
            .audio(UPDATED_AUDIO)
            .audioContentType(UPDATED_AUDIO_CONTENT_TYPE)
            .video(UPDATED_VIDEO)
            .videoContentType(UPDATED_VIDEO_CONTENT_TYPE)
            .foto1(UPDATED_FOTO_1)
            .foto1ContentType(UPDATED_FOTO_1_CONTENT_TYPE)
            .foto2(UPDATED_FOTO_2)
            .foto2ContentType(UPDATED_FOTO_2_CONTENT_TYPE)
            .banner(UPDATED_BANNER)
            .bannerContentType(UPDATED_BANNER_CONTENT_TYPE);

        restGruposMockMvc.perform(put("/api/grupos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGrupos)))
            .andExpect(status().isOk());

        // Validate the Grupos in the database
        List<Grupos> gruposList = gruposRepository.findAll();
        assertThat(gruposList).hasSize(databaseSizeBeforeUpdate);
        Grupos testGrupos = gruposList.get(gruposList.size() - 1);
        assertThat(testGrupos.getNombreGrupo()).isEqualTo(UPDATED_NOMBRE_GRUPO);
        assertThat(testGrupos.getInstagram()).isEqualTo(UPDATED_INSTAGRAM);
        assertThat(testGrupos.getFacebook()).isEqualTo(UPDATED_FACEBOOK);
        assertThat(testGrupos.getTwiter()).isEqualTo(UPDATED_TWITER);
        assertThat(testGrupos.getLinkedIn()).isEqualTo(UPDATED_LINKED_IN);
        assertThat(testGrupos.getTipoGrupo()).isEqualTo(UPDATED_TIPO_GRUPO);
        assertThat(testGrupos.getTipoConsumo()).isEqualTo(UPDATED_TIPO_CONSUMO);
        assertThat(testGrupos.getValorSuscripcion()).isEqualTo(UPDATED_VALOR_SUSCRIPCION);
        assertThat(testGrupos.getImpuesto()).isEqualTo(UPDATED_IMPUESTO);
        assertThat(testGrupos.getReglasGrupo()).isEqualTo(UPDATED_REGLAS_GRUPO);
        assertThat(testGrupos.getAudio()).isEqualTo(UPDATED_AUDIO);
        assertThat(testGrupos.getAudioContentType()).isEqualTo(UPDATED_AUDIO_CONTENT_TYPE);
        assertThat(testGrupos.getVideo()).isEqualTo(UPDATED_VIDEO);
        assertThat(testGrupos.getVideoContentType()).isEqualTo(UPDATED_VIDEO_CONTENT_TYPE);
        assertThat(testGrupos.getFoto1()).isEqualTo(UPDATED_FOTO_1);
        assertThat(testGrupos.getFoto1ContentType()).isEqualTo(UPDATED_FOTO_1_CONTENT_TYPE);
        assertThat(testGrupos.getFoto2()).isEqualTo(UPDATED_FOTO_2);
        assertThat(testGrupos.getFoto2ContentType()).isEqualTo(UPDATED_FOTO_2_CONTENT_TYPE);
        assertThat(testGrupos.getBanner()).isEqualTo(UPDATED_BANNER);
        assertThat(testGrupos.getBannerContentType()).isEqualTo(UPDATED_BANNER_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingGrupos() throws Exception {
        int databaseSizeBeforeUpdate = gruposRepository.findAll().size();

        // Create the Grupos

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGruposMockMvc.perform(put("/api/grupos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupos)))
            .andExpect(status().isBadRequest());

        // Validate the Grupos in the database
        List<Grupos> gruposList = gruposRepository.findAll();
        assertThat(gruposList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGrupos() throws Exception {
        // Initialize the database
        gruposRepository.saveAndFlush(grupos);

        int databaseSizeBeforeDelete = gruposRepository.findAll().size();

        // Delete the grupos
        restGruposMockMvc.perform(delete("/api/grupos/{id}", grupos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Grupos> gruposList = gruposRepository.findAll();
        assertThat(gruposList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Grupos.class);
        Grupos grupos1 = new Grupos();
        grupos1.setId(1L);
        Grupos grupos2 = new Grupos();
        grupos2.setId(grupos1.getId());
        assertThat(grupos1).isEqualTo(grupos2);
        grupos2.setId(2L);
        assertThat(grupos1).isNotEqualTo(grupos2);
        grupos1.setId(null);
        assertThat(grupos1).isNotEqualTo(grupos2);
    }
}
