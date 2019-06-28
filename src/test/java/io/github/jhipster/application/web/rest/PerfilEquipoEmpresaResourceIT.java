package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.PerfilEquipoEmpresa;
import io.github.jhipster.application.repository.PerfilEquipoEmpresaRepository;
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
 * Integration tests for the {@Link PerfilEquipoEmpresaResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class PerfilEquipoEmpresaResourceIT {

    private static final String DEFAULT_BIOGRAFIA = "AAAAAAAAAA";
    private static final String UPDATED_BIOGRAFIA = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FOTO_1 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO_1 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_1_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_1_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_FOTO_2 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO_2 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_2_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_2_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_FOT_3 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOT_3 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOT_3_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOT_3_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_CONOCIMIENTOS_QUE_DOMINA = "AAAAAAAAAA";
    private static final String UPDATED_CONOCIMIENTOS_QUE_DOMINA = "BBBBBBBBBB";

    private static final String DEFAULT_TEMAS_DE_INTERES = "AAAAAAAAAA";
    private static final String UPDATED_TEMAS_DE_INTERES = "BBBBBBBBBB";

    private static final String DEFAULT_FACEBOOK = "AAAAAAAAAA";
    private static final String UPDATED_FACEBOOK = "BBBBBBBBBB";

    private static final String DEFAULT_INSTAGRAM = "AAAAAAAAAA";
    private static final String UPDATED_INSTAGRAM = "BBBBBBBBBB";

    private static final String DEFAULT_ID_GOOGLE = "AAAAAAAAAA";
    private static final String UPDATED_ID_GOOGLE = "BBBBBBBBBB";

    private static final String DEFAULT_TWITER = "AAAAAAAAAA";
    private static final String UPDATED_TWITER = "BBBBBBBBBB";

    @Autowired
    private PerfilEquipoEmpresaRepository perfilEquipoEmpresaRepository;

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

    private MockMvc restPerfilEquipoEmpresaMockMvc;

    private PerfilEquipoEmpresa perfilEquipoEmpresa;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PerfilEquipoEmpresaResource perfilEquipoEmpresaResource = new PerfilEquipoEmpresaResource(perfilEquipoEmpresaRepository);
        this.restPerfilEquipoEmpresaMockMvc = MockMvcBuilders.standaloneSetup(perfilEquipoEmpresaResource)
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
    public static PerfilEquipoEmpresa createEntity(EntityManager em) {
        PerfilEquipoEmpresa perfilEquipoEmpresa = new PerfilEquipoEmpresa()
            .biografia(DEFAULT_BIOGRAFIA)
            .foto1(DEFAULT_FOTO_1)
            .foto1ContentType(DEFAULT_FOTO_1_CONTENT_TYPE)
            .foto2(DEFAULT_FOTO_2)
            .foto2ContentType(DEFAULT_FOTO_2_CONTENT_TYPE)
            .fot3(DEFAULT_FOT_3)
            .fot3ContentType(DEFAULT_FOT_3_CONTENT_TYPE)
            .conocimientosQueDomina(DEFAULT_CONOCIMIENTOS_QUE_DOMINA)
            .temasDeInteres(DEFAULT_TEMAS_DE_INTERES)
            .facebook(DEFAULT_FACEBOOK)
            .instagram(DEFAULT_INSTAGRAM)
            .idGoogle(DEFAULT_ID_GOOGLE)
            .twiter(DEFAULT_TWITER);
        return perfilEquipoEmpresa;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PerfilEquipoEmpresa createUpdatedEntity(EntityManager em) {
        PerfilEquipoEmpresa perfilEquipoEmpresa = new PerfilEquipoEmpresa()
            .biografia(UPDATED_BIOGRAFIA)
            .foto1(UPDATED_FOTO_1)
            .foto1ContentType(UPDATED_FOTO_1_CONTENT_TYPE)
            .foto2(UPDATED_FOTO_2)
            .foto2ContentType(UPDATED_FOTO_2_CONTENT_TYPE)
            .fot3(UPDATED_FOT_3)
            .fot3ContentType(UPDATED_FOT_3_CONTENT_TYPE)
            .conocimientosQueDomina(UPDATED_CONOCIMIENTOS_QUE_DOMINA)
            .temasDeInteres(UPDATED_TEMAS_DE_INTERES)
            .facebook(UPDATED_FACEBOOK)
            .instagram(UPDATED_INSTAGRAM)
            .idGoogle(UPDATED_ID_GOOGLE)
            .twiter(UPDATED_TWITER);
        return perfilEquipoEmpresa;
    }

    @BeforeEach
    public void initTest() {
        perfilEquipoEmpresa = createEntity(em);
    }

    @Test
    @Transactional
    public void createPerfilEquipoEmpresa() throws Exception {
        int databaseSizeBeforeCreate = perfilEquipoEmpresaRepository.findAll().size();

        // Create the PerfilEquipoEmpresa
        restPerfilEquipoEmpresaMockMvc.perform(post("/api/perfil-equipo-empresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perfilEquipoEmpresa)))
            .andExpect(status().isCreated());

        // Validate the PerfilEquipoEmpresa in the database
        List<PerfilEquipoEmpresa> perfilEquipoEmpresaList = perfilEquipoEmpresaRepository.findAll();
        assertThat(perfilEquipoEmpresaList).hasSize(databaseSizeBeforeCreate + 1);
        PerfilEquipoEmpresa testPerfilEquipoEmpresa = perfilEquipoEmpresaList.get(perfilEquipoEmpresaList.size() - 1);
        assertThat(testPerfilEquipoEmpresa.getBiografia()).isEqualTo(DEFAULT_BIOGRAFIA);
        assertThat(testPerfilEquipoEmpresa.getFoto1()).isEqualTo(DEFAULT_FOTO_1);
        assertThat(testPerfilEquipoEmpresa.getFoto1ContentType()).isEqualTo(DEFAULT_FOTO_1_CONTENT_TYPE);
        assertThat(testPerfilEquipoEmpresa.getFoto2()).isEqualTo(DEFAULT_FOTO_2);
        assertThat(testPerfilEquipoEmpresa.getFoto2ContentType()).isEqualTo(DEFAULT_FOTO_2_CONTENT_TYPE);
        assertThat(testPerfilEquipoEmpresa.getFot3()).isEqualTo(DEFAULT_FOT_3);
        assertThat(testPerfilEquipoEmpresa.getFot3ContentType()).isEqualTo(DEFAULT_FOT_3_CONTENT_TYPE);
        assertThat(testPerfilEquipoEmpresa.getConocimientosQueDomina()).isEqualTo(DEFAULT_CONOCIMIENTOS_QUE_DOMINA);
        assertThat(testPerfilEquipoEmpresa.getTemasDeInteres()).isEqualTo(DEFAULT_TEMAS_DE_INTERES);
        assertThat(testPerfilEquipoEmpresa.getFacebook()).isEqualTo(DEFAULT_FACEBOOK);
        assertThat(testPerfilEquipoEmpresa.getInstagram()).isEqualTo(DEFAULT_INSTAGRAM);
        assertThat(testPerfilEquipoEmpresa.getIdGoogle()).isEqualTo(DEFAULT_ID_GOOGLE);
        assertThat(testPerfilEquipoEmpresa.getTwiter()).isEqualTo(DEFAULT_TWITER);
    }

    @Test
    @Transactional
    public void createPerfilEquipoEmpresaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = perfilEquipoEmpresaRepository.findAll().size();

        // Create the PerfilEquipoEmpresa with an existing ID
        perfilEquipoEmpresa.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPerfilEquipoEmpresaMockMvc.perform(post("/api/perfil-equipo-empresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perfilEquipoEmpresa)))
            .andExpect(status().isBadRequest());

        // Validate the PerfilEquipoEmpresa in the database
        List<PerfilEquipoEmpresa> perfilEquipoEmpresaList = perfilEquipoEmpresaRepository.findAll();
        assertThat(perfilEquipoEmpresaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPerfilEquipoEmpresas() throws Exception {
        // Initialize the database
        perfilEquipoEmpresaRepository.saveAndFlush(perfilEquipoEmpresa);

        // Get all the perfilEquipoEmpresaList
        restPerfilEquipoEmpresaMockMvc.perform(get("/api/perfil-equipo-empresas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(perfilEquipoEmpresa.getId().intValue())))
            .andExpect(jsonPath("$.[*].biografia").value(hasItem(DEFAULT_BIOGRAFIA.toString())))
            .andExpect(jsonPath("$.[*].foto1ContentType").value(hasItem(DEFAULT_FOTO_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto1").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_1))))
            .andExpect(jsonPath("$.[*].foto2ContentType").value(hasItem(DEFAULT_FOTO_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto2").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_2))))
            .andExpect(jsonPath("$.[*].fot3ContentType").value(hasItem(DEFAULT_FOT_3_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fot3").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOT_3))))
            .andExpect(jsonPath("$.[*].conocimientosQueDomina").value(hasItem(DEFAULT_CONOCIMIENTOS_QUE_DOMINA.toString())))
            .andExpect(jsonPath("$.[*].temasDeInteres").value(hasItem(DEFAULT_TEMAS_DE_INTERES.toString())))
            .andExpect(jsonPath("$.[*].facebook").value(hasItem(DEFAULT_FACEBOOK.toString())))
            .andExpect(jsonPath("$.[*].instagram").value(hasItem(DEFAULT_INSTAGRAM.toString())))
            .andExpect(jsonPath("$.[*].idGoogle").value(hasItem(DEFAULT_ID_GOOGLE.toString())))
            .andExpect(jsonPath("$.[*].twiter").value(hasItem(DEFAULT_TWITER.toString())));
    }
    
    @Test
    @Transactional
    public void getPerfilEquipoEmpresa() throws Exception {
        // Initialize the database
        perfilEquipoEmpresaRepository.saveAndFlush(perfilEquipoEmpresa);

        // Get the perfilEquipoEmpresa
        restPerfilEquipoEmpresaMockMvc.perform(get("/api/perfil-equipo-empresas/{id}", perfilEquipoEmpresa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(perfilEquipoEmpresa.getId().intValue()))
            .andExpect(jsonPath("$.biografia").value(DEFAULT_BIOGRAFIA.toString()))
            .andExpect(jsonPath("$.foto1ContentType").value(DEFAULT_FOTO_1_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto1").value(Base64Utils.encodeToString(DEFAULT_FOTO_1)))
            .andExpect(jsonPath("$.foto2ContentType").value(DEFAULT_FOTO_2_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto2").value(Base64Utils.encodeToString(DEFAULT_FOTO_2)))
            .andExpect(jsonPath("$.fot3ContentType").value(DEFAULT_FOT_3_CONTENT_TYPE))
            .andExpect(jsonPath("$.fot3").value(Base64Utils.encodeToString(DEFAULT_FOT_3)))
            .andExpect(jsonPath("$.conocimientosQueDomina").value(DEFAULT_CONOCIMIENTOS_QUE_DOMINA.toString()))
            .andExpect(jsonPath("$.temasDeInteres").value(DEFAULT_TEMAS_DE_INTERES.toString()))
            .andExpect(jsonPath("$.facebook").value(DEFAULT_FACEBOOK.toString()))
            .andExpect(jsonPath("$.instagram").value(DEFAULT_INSTAGRAM.toString()))
            .andExpect(jsonPath("$.idGoogle").value(DEFAULT_ID_GOOGLE.toString()))
            .andExpect(jsonPath("$.twiter").value(DEFAULT_TWITER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPerfilEquipoEmpresa() throws Exception {
        // Get the perfilEquipoEmpresa
        restPerfilEquipoEmpresaMockMvc.perform(get("/api/perfil-equipo-empresas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePerfilEquipoEmpresa() throws Exception {
        // Initialize the database
        perfilEquipoEmpresaRepository.saveAndFlush(perfilEquipoEmpresa);

        int databaseSizeBeforeUpdate = perfilEquipoEmpresaRepository.findAll().size();

        // Update the perfilEquipoEmpresa
        PerfilEquipoEmpresa updatedPerfilEquipoEmpresa = perfilEquipoEmpresaRepository.findById(perfilEquipoEmpresa.getId()).get();
        // Disconnect from session so that the updates on updatedPerfilEquipoEmpresa are not directly saved in db
        em.detach(updatedPerfilEquipoEmpresa);
        updatedPerfilEquipoEmpresa
            .biografia(UPDATED_BIOGRAFIA)
            .foto1(UPDATED_FOTO_1)
            .foto1ContentType(UPDATED_FOTO_1_CONTENT_TYPE)
            .foto2(UPDATED_FOTO_2)
            .foto2ContentType(UPDATED_FOTO_2_CONTENT_TYPE)
            .fot3(UPDATED_FOT_3)
            .fot3ContentType(UPDATED_FOT_3_CONTENT_TYPE)
            .conocimientosQueDomina(UPDATED_CONOCIMIENTOS_QUE_DOMINA)
            .temasDeInteres(UPDATED_TEMAS_DE_INTERES)
            .facebook(UPDATED_FACEBOOK)
            .instagram(UPDATED_INSTAGRAM)
            .idGoogle(UPDATED_ID_GOOGLE)
            .twiter(UPDATED_TWITER);

        restPerfilEquipoEmpresaMockMvc.perform(put("/api/perfil-equipo-empresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPerfilEquipoEmpresa)))
            .andExpect(status().isOk());

        // Validate the PerfilEquipoEmpresa in the database
        List<PerfilEquipoEmpresa> perfilEquipoEmpresaList = perfilEquipoEmpresaRepository.findAll();
        assertThat(perfilEquipoEmpresaList).hasSize(databaseSizeBeforeUpdate);
        PerfilEquipoEmpresa testPerfilEquipoEmpresa = perfilEquipoEmpresaList.get(perfilEquipoEmpresaList.size() - 1);
        assertThat(testPerfilEquipoEmpresa.getBiografia()).isEqualTo(UPDATED_BIOGRAFIA);
        assertThat(testPerfilEquipoEmpresa.getFoto1()).isEqualTo(UPDATED_FOTO_1);
        assertThat(testPerfilEquipoEmpresa.getFoto1ContentType()).isEqualTo(UPDATED_FOTO_1_CONTENT_TYPE);
        assertThat(testPerfilEquipoEmpresa.getFoto2()).isEqualTo(UPDATED_FOTO_2);
        assertThat(testPerfilEquipoEmpresa.getFoto2ContentType()).isEqualTo(UPDATED_FOTO_2_CONTENT_TYPE);
        assertThat(testPerfilEquipoEmpresa.getFot3()).isEqualTo(UPDATED_FOT_3);
        assertThat(testPerfilEquipoEmpresa.getFot3ContentType()).isEqualTo(UPDATED_FOT_3_CONTENT_TYPE);
        assertThat(testPerfilEquipoEmpresa.getConocimientosQueDomina()).isEqualTo(UPDATED_CONOCIMIENTOS_QUE_DOMINA);
        assertThat(testPerfilEquipoEmpresa.getTemasDeInteres()).isEqualTo(UPDATED_TEMAS_DE_INTERES);
        assertThat(testPerfilEquipoEmpresa.getFacebook()).isEqualTo(UPDATED_FACEBOOK);
        assertThat(testPerfilEquipoEmpresa.getInstagram()).isEqualTo(UPDATED_INSTAGRAM);
        assertThat(testPerfilEquipoEmpresa.getIdGoogle()).isEqualTo(UPDATED_ID_GOOGLE);
        assertThat(testPerfilEquipoEmpresa.getTwiter()).isEqualTo(UPDATED_TWITER);
    }

    @Test
    @Transactional
    public void updateNonExistingPerfilEquipoEmpresa() throws Exception {
        int databaseSizeBeforeUpdate = perfilEquipoEmpresaRepository.findAll().size();

        // Create the PerfilEquipoEmpresa

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPerfilEquipoEmpresaMockMvc.perform(put("/api/perfil-equipo-empresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perfilEquipoEmpresa)))
            .andExpect(status().isBadRequest());

        // Validate the PerfilEquipoEmpresa in the database
        List<PerfilEquipoEmpresa> perfilEquipoEmpresaList = perfilEquipoEmpresaRepository.findAll();
        assertThat(perfilEquipoEmpresaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePerfilEquipoEmpresa() throws Exception {
        // Initialize the database
        perfilEquipoEmpresaRepository.saveAndFlush(perfilEquipoEmpresa);

        int databaseSizeBeforeDelete = perfilEquipoEmpresaRepository.findAll().size();

        // Delete the perfilEquipoEmpresa
        restPerfilEquipoEmpresaMockMvc.perform(delete("/api/perfil-equipo-empresas/{id}", perfilEquipoEmpresa.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PerfilEquipoEmpresa> perfilEquipoEmpresaList = perfilEquipoEmpresaRepository.findAll();
        assertThat(perfilEquipoEmpresaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PerfilEquipoEmpresa.class);
        PerfilEquipoEmpresa perfilEquipoEmpresa1 = new PerfilEquipoEmpresa();
        perfilEquipoEmpresa1.setId(1L);
        PerfilEquipoEmpresa perfilEquipoEmpresa2 = new PerfilEquipoEmpresa();
        perfilEquipoEmpresa2.setId(perfilEquipoEmpresa1.getId());
        assertThat(perfilEquipoEmpresa1).isEqualTo(perfilEquipoEmpresa2);
        perfilEquipoEmpresa2.setId(2L);
        assertThat(perfilEquipoEmpresa1).isNotEqualTo(perfilEquipoEmpresa2);
        perfilEquipoEmpresa1.setId(null);
        assertThat(perfilEquipoEmpresa1).isNotEqualTo(perfilEquipoEmpresa2);
    }
}
