package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.RecursosFisicos;
import io.github.jhipster.application.repository.RecursosFisicosRepository;
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

import io.github.jhipster.application.domain.enumeration.TipoRecursod;
import io.github.jhipster.application.domain.enumeration.Impuestod;
/**
 * Integration tests for the {@Link RecursosFisicosResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class RecursosFisicosResourceIT {

    private static final String DEFAULT_RECURSO = "AAAAAAAAAA";
    private static final String UPDATED_RECURSO = "BBBBBBBBBB";

    private static final TipoRecursod DEFAULT_TIPO = TipoRecursod.Tiempo;
    private static final TipoRecursod UPDATED_TIPO = TipoRecursod.Cantidad;

    private static final String DEFAULT_UNIDAD_MEDIDA = "AAAAAAAAAA";
    private static final String UPDATED_UNIDAD_MEDIDA = "BBBBBBBBBB";

    private static final Integer DEFAULT_VALOR_UNITARIO = 1;
    private static final Integer UPDATED_VALOR_UNITARIO = 2;

    private static final Integer DEFAULT_VALOR_1_H = 1;
    private static final Integer UPDATED_VALOR_1_H = 2;

    private static final Integer DEFAULT_VALOR_2_H = 1;
    private static final Integer UPDATED_VALOR_2_H = 2;

    private static final Integer DEFAULT_VALOR_3_H = 1;
    private static final Integer UPDATED_VALOR_3_H = 2;

    private static final Integer DEFAULT_VALOR_4_H = 1;
    private static final Integer UPDATED_VALOR_4_H = 2;

    private static final Integer DEFAULT_VALOR_5_H = 1;
    private static final Integer UPDATED_VALOR_5_H = 2;

    private static final Integer DEFAULT_VALOR_6_H = 1;
    private static final Integer UPDATED_VALOR_6_H = 2;

    private static final Integer DEFAULT_VALOR_7_H = 1;
    private static final Integer UPDATED_VALOR_7_H = 2;

    private static final Integer DEFAULT_VALOR_8_H = 1;
    private static final Integer UPDATED_VALOR_8_H = 2;

    private static final Integer DEFAULT_VALOR_9_H = 1;
    private static final Integer UPDATED_VALOR_9_H = 2;

    private static final Integer DEFAULT_VALOR_10_H = 1;
    private static final Integer UPDATED_VALOR_10_H = 2;

    private static final Integer DEFAULT_VALOR_11_H = 1;
    private static final Integer UPDATED_VALOR_11_H = 2;

    private static final Integer DEFAULT_VALOR_12_H = 1;
    private static final Integer UPDATED_VALOR_12_H = 2;

    private static final Impuestod DEFAULT_IMPUESTO = Impuestod.IVA19;
    private static final Impuestod UPDATED_IMPUESTO = Impuestod.IVA6;

    private static final byte[] DEFAULT_FOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_VIDEO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_VIDEO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_VIDEO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_VIDEO_CONTENT_TYPE = "image/png";

    @Autowired
    private RecursosFisicosRepository recursosFisicosRepository;

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

    private MockMvc restRecursosFisicosMockMvc;

    private RecursosFisicos recursosFisicos;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RecursosFisicosResource recursosFisicosResource = new RecursosFisicosResource(recursosFisicosRepository);
        this.restRecursosFisicosMockMvc = MockMvcBuilders.standaloneSetup(recursosFisicosResource)
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
    public static RecursosFisicos createEntity(EntityManager em) {
        RecursosFisicos recursosFisicos = new RecursosFisicos()
            .recurso(DEFAULT_RECURSO)
            .tipo(DEFAULT_TIPO)
            .unidadMedida(DEFAULT_UNIDAD_MEDIDA)
            .valorUnitario(DEFAULT_VALOR_UNITARIO)
            .valor1H(DEFAULT_VALOR_1_H)
            .valor2H(DEFAULT_VALOR_2_H)
            .valor3H(DEFAULT_VALOR_3_H)
            .valor4H(DEFAULT_VALOR_4_H)
            .valor5H(DEFAULT_VALOR_5_H)
            .valor6H(DEFAULT_VALOR_6_H)
            .valor7H(DEFAULT_VALOR_7_H)
            .valor8H(DEFAULT_VALOR_8_H)
            .valor9H(DEFAULT_VALOR_9_H)
            .valor10H(DEFAULT_VALOR_10_H)
            .valor11H(DEFAULT_VALOR_11_H)
            .valor12H(DEFAULT_VALOR_12_H)
            .impuesto(DEFAULT_IMPUESTO)
            .foto(DEFAULT_FOTO)
            .fotoContentType(DEFAULT_FOTO_CONTENT_TYPE)
            .video(DEFAULT_VIDEO)
            .videoContentType(DEFAULT_VIDEO_CONTENT_TYPE);
        return recursosFisicos;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RecursosFisicos createUpdatedEntity(EntityManager em) {
        RecursosFisicos recursosFisicos = new RecursosFisicos()
            .recurso(UPDATED_RECURSO)
            .tipo(UPDATED_TIPO)
            .unidadMedida(UPDATED_UNIDAD_MEDIDA)
            .valorUnitario(UPDATED_VALOR_UNITARIO)
            .valor1H(UPDATED_VALOR_1_H)
            .valor2H(UPDATED_VALOR_2_H)
            .valor3H(UPDATED_VALOR_3_H)
            .valor4H(UPDATED_VALOR_4_H)
            .valor5H(UPDATED_VALOR_5_H)
            .valor6H(UPDATED_VALOR_6_H)
            .valor7H(UPDATED_VALOR_7_H)
            .valor8H(UPDATED_VALOR_8_H)
            .valor9H(UPDATED_VALOR_9_H)
            .valor10H(UPDATED_VALOR_10_H)
            .valor11H(UPDATED_VALOR_11_H)
            .valor12H(UPDATED_VALOR_12_H)
            .impuesto(UPDATED_IMPUESTO)
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE)
            .video(UPDATED_VIDEO)
            .videoContentType(UPDATED_VIDEO_CONTENT_TYPE);
        return recursosFisicos;
    }

    @BeforeEach
    public void initTest() {
        recursosFisicos = createEntity(em);
    }

    @Test
    @Transactional
    public void createRecursosFisicos() throws Exception {
        int databaseSizeBeforeCreate = recursosFisicosRepository.findAll().size();

        // Create the RecursosFisicos
        restRecursosFisicosMockMvc.perform(post("/api/recursos-fisicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recursosFisicos)))
            .andExpect(status().isCreated());

        // Validate the RecursosFisicos in the database
        List<RecursosFisicos> recursosFisicosList = recursosFisicosRepository.findAll();
        assertThat(recursosFisicosList).hasSize(databaseSizeBeforeCreate + 1);
        RecursosFisicos testRecursosFisicos = recursosFisicosList.get(recursosFisicosList.size() - 1);
        assertThat(testRecursosFisicos.getRecurso()).isEqualTo(DEFAULT_RECURSO);
        assertThat(testRecursosFisicos.getTipo()).isEqualTo(DEFAULT_TIPO);
        assertThat(testRecursosFisicos.getUnidadMedida()).isEqualTo(DEFAULT_UNIDAD_MEDIDA);
        assertThat(testRecursosFisicos.getValorUnitario()).isEqualTo(DEFAULT_VALOR_UNITARIO);
        assertThat(testRecursosFisicos.getValor1H()).isEqualTo(DEFAULT_VALOR_1_H);
        assertThat(testRecursosFisicos.getValor2H()).isEqualTo(DEFAULT_VALOR_2_H);
        assertThat(testRecursosFisicos.getValor3H()).isEqualTo(DEFAULT_VALOR_3_H);
        assertThat(testRecursosFisicos.getValor4H()).isEqualTo(DEFAULT_VALOR_4_H);
        assertThat(testRecursosFisicos.getValor5H()).isEqualTo(DEFAULT_VALOR_5_H);
        assertThat(testRecursosFisicos.getValor6H()).isEqualTo(DEFAULT_VALOR_6_H);
        assertThat(testRecursosFisicos.getValor7H()).isEqualTo(DEFAULT_VALOR_7_H);
        assertThat(testRecursosFisicos.getValor8H()).isEqualTo(DEFAULT_VALOR_8_H);
        assertThat(testRecursosFisicos.getValor9H()).isEqualTo(DEFAULT_VALOR_9_H);
        assertThat(testRecursosFisicos.getValor10H()).isEqualTo(DEFAULT_VALOR_10_H);
        assertThat(testRecursosFisicos.getValor11H()).isEqualTo(DEFAULT_VALOR_11_H);
        assertThat(testRecursosFisicos.getValor12H()).isEqualTo(DEFAULT_VALOR_12_H);
        assertThat(testRecursosFisicos.getImpuesto()).isEqualTo(DEFAULT_IMPUESTO);
        assertThat(testRecursosFisicos.getFoto()).isEqualTo(DEFAULT_FOTO);
        assertThat(testRecursosFisicos.getFotoContentType()).isEqualTo(DEFAULT_FOTO_CONTENT_TYPE);
        assertThat(testRecursosFisicos.getVideo()).isEqualTo(DEFAULT_VIDEO);
        assertThat(testRecursosFisicos.getVideoContentType()).isEqualTo(DEFAULT_VIDEO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createRecursosFisicosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = recursosFisicosRepository.findAll().size();

        // Create the RecursosFisicos with an existing ID
        recursosFisicos.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRecursosFisicosMockMvc.perform(post("/api/recursos-fisicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recursosFisicos)))
            .andExpect(status().isBadRequest());

        // Validate the RecursosFisicos in the database
        List<RecursosFisicos> recursosFisicosList = recursosFisicosRepository.findAll();
        assertThat(recursosFisicosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRecursosFisicos() throws Exception {
        // Initialize the database
        recursosFisicosRepository.saveAndFlush(recursosFisicos);

        // Get all the recursosFisicosList
        restRecursosFisicosMockMvc.perform(get("/api/recursos-fisicos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(recursosFisicos.getId().intValue())))
            .andExpect(jsonPath("$.[*].recurso").value(hasItem(DEFAULT_RECURSO.toString())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.toString())))
            .andExpect(jsonPath("$.[*].unidadMedida").value(hasItem(DEFAULT_UNIDAD_MEDIDA.toString())))
            .andExpect(jsonPath("$.[*].valorUnitario").value(hasItem(DEFAULT_VALOR_UNITARIO)))
            .andExpect(jsonPath("$.[*].valor1H").value(hasItem(DEFAULT_VALOR_1_H)))
            .andExpect(jsonPath("$.[*].valor2H").value(hasItem(DEFAULT_VALOR_2_H)))
            .andExpect(jsonPath("$.[*].valor3H").value(hasItem(DEFAULT_VALOR_3_H)))
            .andExpect(jsonPath("$.[*].valor4H").value(hasItem(DEFAULT_VALOR_4_H)))
            .andExpect(jsonPath("$.[*].valor5H").value(hasItem(DEFAULT_VALOR_5_H)))
            .andExpect(jsonPath("$.[*].valor6H").value(hasItem(DEFAULT_VALOR_6_H)))
            .andExpect(jsonPath("$.[*].valor7H").value(hasItem(DEFAULT_VALOR_7_H)))
            .andExpect(jsonPath("$.[*].valor8H").value(hasItem(DEFAULT_VALOR_8_H)))
            .andExpect(jsonPath("$.[*].valor9H").value(hasItem(DEFAULT_VALOR_9_H)))
            .andExpect(jsonPath("$.[*].valor10H").value(hasItem(DEFAULT_VALOR_10_H)))
            .andExpect(jsonPath("$.[*].valor11H").value(hasItem(DEFAULT_VALOR_11_H)))
            .andExpect(jsonPath("$.[*].valor12H").value(hasItem(DEFAULT_VALOR_12_H)))
            .andExpect(jsonPath("$.[*].impuesto").value(hasItem(DEFAULT_IMPUESTO.toString())))
            .andExpect(jsonPath("$.[*].fotoContentType").value(hasItem(DEFAULT_FOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO))))
            .andExpect(jsonPath("$.[*].videoContentType").value(hasItem(DEFAULT_VIDEO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].video").value(hasItem(Base64Utils.encodeToString(DEFAULT_VIDEO))));
    }
    
    @Test
    @Transactional
    public void getRecursosFisicos() throws Exception {
        // Initialize the database
        recursosFisicosRepository.saveAndFlush(recursosFisicos);

        // Get the recursosFisicos
        restRecursosFisicosMockMvc.perform(get("/api/recursos-fisicos/{id}", recursosFisicos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(recursosFisicos.getId().intValue()))
            .andExpect(jsonPath("$.recurso").value(DEFAULT_RECURSO.toString()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO.toString()))
            .andExpect(jsonPath("$.unidadMedida").value(DEFAULT_UNIDAD_MEDIDA.toString()))
            .andExpect(jsonPath("$.valorUnitario").value(DEFAULT_VALOR_UNITARIO))
            .andExpect(jsonPath("$.valor1H").value(DEFAULT_VALOR_1_H))
            .andExpect(jsonPath("$.valor2H").value(DEFAULT_VALOR_2_H))
            .andExpect(jsonPath("$.valor3H").value(DEFAULT_VALOR_3_H))
            .andExpect(jsonPath("$.valor4H").value(DEFAULT_VALOR_4_H))
            .andExpect(jsonPath("$.valor5H").value(DEFAULT_VALOR_5_H))
            .andExpect(jsonPath("$.valor6H").value(DEFAULT_VALOR_6_H))
            .andExpect(jsonPath("$.valor7H").value(DEFAULT_VALOR_7_H))
            .andExpect(jsonPath("$.valor8H").value(DEFAULT_VALOR_8_H))
            .andExpect(jsonPath("$.valor9H").value(DEFAULT_VALOR_9_H))
            .andExpect(jsonPath("$.valor10H").value(DEFAULT_VALOR_10_H))
            .andExpect(jsonPath("$.valor11H").value(DEFAULT_VALOR_11_H))
            .andExpect(jsonPath("$.valor12H").value(DEFAULT_VALOR_12_H))
            .andExpect(jsonPath("$.impuesto").value(DEFAULT_IMPUESTO.toString()))
            .andExpect(jsonPath("$.fotoContentType").value(DEFAULT_FOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto").value(Base64Utils.encodeToString(DEFAULT_FOTO)))
            .andExpect(jsonPath("$.videoContentType").value(DEFAULT_VIDEO_CONTENT_TYPE))
            .andExpect(jsonPath("$.video").value(Base64Utils.encodeToString(DEFAULT_VIDEO)));
    }

    @Test
    @Transactional
    public void getNonExistingRecursosFisicos() throws Exception {
        // Get the recursosFisicos
        restRecursosFisicosMockMvc.perform(get("/api/recursos-fisicos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRecursosFisicos() throws Exception {
        // Initialize the database
        recursosFisicosRepository.saveAndFlush(recursosFisicos);

        int databaseSizeBeforeUpdate = recursosFisicosRepository.findAll().size();

        // Update the recursosFisicos
        RecursosFisicos updatedRecursosFisicos = recursosFisicosRepository.findById(recursosFisicos.getId()).get();
        // Disconnect from session so that the updates on updatedRecursosFisicos are not directly saved in db
        em.detach(updatedRecursosFisicos);
        updatedRecursosFisicos
            .recurso(UPDATED_RECURSO)
            .tipo(UPDATED_TIPO)
            .unidadMedida(UPDATED_UNIDAD_MEDIDA)
            .valorUnitario(UPDATED_VALOR_UNITARIO)
            .valor1H(UPDATED_VALOR_1_H)
            .valor2H(UPDATED_VALOR_2_H)
            .valor3H(UPDATED_VALOR_3_H)
            .valor4H(UPDATED_VALOR_4_H)
            .valor5H(UPDATED_VALOR_5_H)
            .valor6H(UPDATED_VALOR_6_H)
            .valor7H(UPDATED_VALOR_7_H)
            .valor8H(UPDATED_VALOR_8_H)
            .valor9H(UPDATED_VALOR_9_H)
            .valor10H(UPDATED_VALOR_10_H)
            .valor11H(UPDATED_VALOR_11_H)
            .valor12H(UPDATED_VALOR_12_H)
            .impuesto(UPDATED_IMPUESTO)
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE)
            .video(UPDATED_VIDEO)
            .videoContentType(UPDATED_VIDEO_CONTENT_TYPE);

        restRecursosFisicosMockMvc.perform(put("/api/recursos-fisicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRecursosFisicos)))
            .andExpect(status().isOk());

        // Validate the RecursosFisicos in the database
        List<RecursosFisicos> recursosFisicosList = recursosFisicosRepository.findAll();
        assertThat(recursosFisicosList).hasSize(databaseSizeBeforeUpdate);
        RecursosFisicos testRecursosFisicos = recursosFisicosList.get(recursosFisicosList.size() - 1);
        assertThat(testRecursosFisicos.getRecurso()).isEqualTo(UPDATED_RECURSO);
        assertThat(testRecursosFisicos.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testRecursosFisicos.getUnidadMedida()).isEqualTo(UPDATED_UNIDAD_MEDIDA);
        assertThat(testRecursosFisicos.getValorUnitario()).isEqualTo(UPDATED_VALOR_UNITARIO);
        assertThat(testRecursosFisicos.getValor1H()).isEqualTo(UPDATED_VALOR_1_H);
        assertThat(testRecursosFisicos.getValor2H()).isEqualTo(UPDATED_VALOR_2_H);
        assertThat(testRecursosFisicos.getValor3H()).isEqualTo(UPDATED_VALOR_3_H);
        assertThat(testRecursosFisicos.getValor4H()).isEqualTo(UPDATED_VALOR_4_H);
        assertThat(testRecursosFisicos.getValor5H()).isEqualTo(UPDATED_VALOR_5_H);
        assertThat(testRecursosFisicos.getValor6H()).isEqualTo(UPDATED_VALOR_6_H);
        assertThat(testRecursosFisicos.getValor7H()).isEqualTo(UPDATED_VALOR_7_H);
        assertThat(testRecursosFisicos.getValor8H()).isEqualTo(UPDATED_VALOR_8_H);
        assertThat(testRecursosFisicos.getValor9H()).isEqualTo(UPDATED_VALOR_9_H);
        assertThat(testRecursosFisicos.getValor10H()).isEqualTo(UPDATED_VALOR_10_H);
        assertThat(testRecursosFisicos.getValor11H()).isEqualTo(UPDATED_VALOR_11_H);
        assertThat(testRecursosFisicos.getValor12H()).isEqualTo(UPDATED_VALOR_12_H);
        assertThat(testRecursosFisicos.getImpuesto()).isEqualTo(UPDATED_IMPUESTO);
        assertThat(testRecursosFisicos.getFoto()).isEqualTo(UPDATED_FOTO);
        assertThat(testRecursosFisicos.getFotoContentType()).isEqualTo(UPDATED_FOTO_CONTENT_TYPE);
        assertThat(testRecursosFisicos.getVideo()).isEqualTo(UPDATED_VIDEO);
        assertThat(testRecursosFisicos.getVideoContentType()).isEqualTo(UPDATED_VIDEO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingRecursosFisicos() throws Exception {
        int databaseSizeBeforeUpdate = recursosFisicosRepository.findAll().size();

        // Create the RecursosFisicos

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRecursosFisicosMockMvc.perform(put("/api/recursos-fisicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recursosFisicos)))
            .andExpect(status().isBadRequest());

        // Validate the RecursosFisicos in the database
        List<RecursosFisicos> recursosFisicosList = recursosFisicosRepository.findAll();
        assertThat(recursosFisicosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRecursosFisicos() throws Exception {
        // Initialize the database
        recursosFisicosRepository.saveAndFlush(recursosFisicos);

        int databaseSizeBeforeDelete = recursosFisicosRepository.findAll().size();

        // Delete the recursosFisicos
        restRecursosFisicosMockMvc.perform(delete("/api/recursos-fisicos/{id}", recursosFisicos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RecursosFisicos> recursosFisicosList = recursosFisicosRepository.findAll();
        assertThat(recursosFisicosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RecursosFisicos.class);
        RecursosFisicos recursosFisicos1 = new RecursosFisicos();
        recursosFisicos1.setId(1L);
        RecursosFisicos recursosFisicos2 = new RecursosFisicos();
        recursosFisicos2.setId(recursosFisicos1.getId());
        assertThat(recursosFisicos1).isEqualTo(recursosFisicos2);
        recursosFisicos2.setId(2L);
        assertThat(recursosFisicos1).isNotEqualTo(recursosFisicos2);
        recursosFisicos1.setId(null);
        assertThat(recursosFisicos1).isNotEqualTo(recursosFisicos2);
    }
}
