package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.CuentaAsociada;
import io.github.jhipster.application.repository.CuentaAsociadaRepository;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link CuentaAsociadaResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class CuentaAsociadaResourceIT {

    private static final String DEFAULT_IDENTIFICACIONTITULAR = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFICACIONTITULAR = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE_TITULAR = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_TITULAR = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDO_TITULAR = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDO_TITULAR = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_CUENTA = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_CUENTA = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO_CUENTA = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_CUENTA = "BBBBBBBBBB";

    private static final String DEFAULT_CODIGO_SEGURIDAD = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_SEGURIDAD = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_VENCIMIENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_VENCIMIENTO = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private CuentaAsociadaRepository cuentaAsociadaRepository;

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

    private MockMvc restCuentaAsociadaMockMvc;

    private CuentaAsociada cuentaAsociada;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CuentaAsociadaResource cuentaAsociadaResource = new CuentaAsociadaResource(cuentaAsociadaRepository);
        this.restCuentaAsociadaMockMvc = MockMvcBuilders.standaloneSetup(cuentaAsociadaResource)
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
    public static CuentaAsociada createEntity(EntityManager em) {
        CuentaAsociada cuentaAsociada = new CuentaAsociada()
            .identificaciontitular(DEFAULT_IDENTIFICACIONTITULAR)
            .nombreTitular(DEFAULT_NOMBRE_TITULAR)
            .apellidoTitular(DEFAULT_APELLIDO_TITULAR)
            .numeroCuenta(DEFAULT_NUMERO_CUENTA)
            .tipoCuenta(DEFAULT_TIPO_CUENTA)
            .codigoSeguridad(DEFAULT_CODIGO_SEGURIDAD)
            .fechaVencimiento(DEFAULT_FECHA_VENCIMIENTO);
        return cuentaAsociada;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CuentaAsociada createUpdatedEntity(EntityManager em) {
        CuentaAsociada cuentaAsociada = new CuentaAsociada()
            .identificaciontitular(UPDATED_IDENTIFICACIONTITULAR)
            .nombreTitular(UPDATED_NOMBRE_TITULAR)
            .apellidoTitular(UPDATED_APELLIDO_TITULAR)
            .numeroCuenta(UPDATED_NUMERO_CUENTA)
            .tipoCuenta(UPDATED_TIPO_CUENTA)
            .codigoSeguridad(UPDATED_CODIGO_SEGURIDAD)
            .fechaVencimiento(UPDATED_FECHA_VENCIMIENTO);
        return cuentaAsociada;
    }

    @BeforeEach
    public void initTest() {
        cuentaAsociada = createEntity(em);
    }

    @Test
    @Transactional
    public void createCuentaAsociada() throws Exception {
        int databaseSizeBeforeCreate = cuentaAsociadaRepository.findAll().size();

        // Create the CuentaAsociada
        restCuentaAsociadaMockMvc.perform(post("/api/cuenta-asociadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cuentaAsociada)))
            .andExpect(status().isCreated());

        // Validate the CuentaAsociada in the database
        List<CuentaAsociada> cuentaAsociadaList = cuentaAsociadaRepository.findAll();
        assertThat(cuentaAsociadaList).hasSize(databaseSizeBeforeCreate + 1);
        CuentaAsociada testCuentaAsociada = cuentaAsociadaList.get(cuentaAsociadaList.size() - 1);
        assertThat(testCuentaAsociada.getIdentificaciontitular()).isEqualTo(DEFAULT_IDENTIFICACIONTITULAR);
        assertThat(testCuentaAsociada.getNombreTitular()).isEqualTo(DEFAULT_NOMBRE_TITULAR);
        assertThat(testCuentaAsociada.getApellidoTitular()).isEqualTo(DEFAULT_APELLIDO_TITULAR);
        assertThat(testCuentaAsociada.getNumeroCuenta()).isEqualTo(DEFAULT_NUMERO_CUENTA);
        assertThat(testCuentaAsociada.getTipoCuenta()).isEqualTo(DEFAULT_TIPO_CUENTA);
        assertThat(testCuentaAsociada.getCodigoSeguridad()).isEqualTo(DEFAULT_CODIGO_SEGURIDAD);
        assertThat(testCuentaAsociada.getFechaVencimiento()).isEqualTo(DEFAULT_FECHA_VENCIMIENTO);
    }

    @Test
    @Transactional
    public void createCuentaAsociadaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cuentaAsociadaRepository.findAll().size();

        // Create the CuentaAsociada with an existing ID
        cuentaAsociada.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCuentaAsociadaMockMvc.perform(post("/api/cuenta-asociadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cuentaAsociada)))
            .andExpect(status().isBadRequest());

        // Validate the CuentaAsociada in the database
        List<CuentaAsociada> cuentaAsociadaList = cuentaAsociadaRepository.findAll();
        assertThat(cuentaAsociadaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCuentaAsociadas() throws Exception {
        // Initialize the database
        cuentaAsociadaRepository.saveAndFlush(cuentaAsociada);

        // Get all the cuentaAsociadaList
        restCuentaAsociadaMockMvc.perform(get("/api/cuenta-asociadas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cuentaAsociada.getId().intValue())))
            .andExpect(jsonPath("$.[*].identificaciontitular").value(hasItem(DEFAULT_IDENTIFICACIONTITULAR.toString())))
            .andExpect(jsonPath("$.[*].nombreTitular").value(hasItem(DEFAULT_NOMBRE_TITULAR.toString())))
            .andExpect(jsonPath("$.[*].apellidoTitular").value(hasItem(DEFAULT_APELLIDO_TITULAR.toString())))
            .andExpect(jsonPath("$.[*].numeroCuenta").value(hasItem(DEFAULT_NUMERO_CUENTA.toString())))
            .andExpect(jsonPath("$.[*].tipoCuenta").value(hasItem(DEFAULT_TIPO_CUENTA.toString())))
            .andExpect(jsonPath("$.[*].codigoSeguridad").value(hasItem(DEFAULT_CODIGO_SEGURIDAD.toString())))
            .andExpect(jsonPath("$.[*].fechaVencimiento").value(hasItem(DEFAULT_FECHA_VENCIMIENTO.toString())));
    }
    
    @Test
    @Transactional
    public void getCuentaAsociada() throws Exception {
        // Initialize the database
        cuentaAsociadaRepository.saveAndFlush(cuentaAsociada);

        // Get the cuentaAsociada
        restCuentaAsociadaMockMvc.perform(get("/api/cuenta-asociadas/{id}", cuentaAsociada.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cuentaAsociada.getId().intValue()))
            .andExpect(jsonPath("$.identificaciontitular").value(DEFAULT_IDENTIFICACIONTITULAR.toString()))
            .andExpect(jsonPath("$.nombreTitular").value(DEFAULT_NOMBRE_TITULAR.toString()))
            .andExpect(jsonPath("$.apellidoTitular").value(DEFAULT_APELLIDO_TITULAR.toString()))
            .andExpect(jsonPath("$.numeroCuenta").value(DEFAULT_NUMERO_CUENTA.toString()))
            .andExpect(jsonPath("$.tipoCuenta").value(DEFAULT_TIPO_CUENTA.toString()))
            .andExpect(jsonPath("$.codigoSeguridad").value(DEFAULT_CODIGO_SEGURIDAD.toString()))
            .andExpect(jsonPath("$.fechaVencimiento").value(DEFAULT_FECHA_VENCIMIENTO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCuentaAsociada() throws Exception {
        // Get the cuentaAsociada
        restCuentaAsociadaMockMvc.perform(get("/api/cuenta-asociadas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCuentaAsociada() throws Exception {
        // Initialize the database
        cuentaAsociadaRepository.saveAndFlush(cuentaAsociada);

        int databaseSizeBeforeUpdate = cuentaAsociadaRepository.findAll().size();

        // Update the cuentaAsociada
        CuentaAsociada updatedCuentaAsociada = cuentaAsociadaRepository.findById(cuentaAsociada.getId()).get();
        // Disconnect from session so that the updates on updatedCuentaAsociada are not directly saved in db
        em.detach(updatedCuentaAsociada);
        updatedCuentaAsociada
            .identificaciontitular(UPDATED_IDENTIFICACIONTITULAR)
            .nombreTitular(UPDATED_NOMBRE_TITULAR)
            .apellidoTitular(UPDATED_APELLIDO_TITULAR)
            .numeroCuenta(UPDATED_NUMERO_CUENTA)
            .tipoCuenta(UPDATED_TIPO_CUENTA)
            .codigoSeguridad(UPDATED_CODIGO_SEGURIDAD)
            .fechaVencimiento(UPDATED_FECHA_VENCIMIENTO);

        restCuentaAsociadaMockMvc.perform(put("/api/cuenta-asociadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCuentaAsociada)))
            .andExpect(status().isOk());

        // Validate the CuentaAsociada in the database
        List<CuentaAsociada> cuentaAsociadaList = cuentaAsociadaRepository.findAll();
        assertThat(cuentaAsociadaList).hasSize(databaseSizeBeforeUpdate);
        CuentaAsociada testCuentaAsociada = cuentaAsociadaList.get(cuentaAsociadaList.size() - 1);
        assertThat(testCuentaAsociada.getIdentificaciontitular()).isEqualTo(UPDATED_IDENTIFICACIONTITULAR);
        assertThat(testCuentaAsociada.getNombreTitular()).isEqualTo(UPDATED_NOMBRE_TITULAR);
        assertThat(testCuentaAsociada.getApellidoTitular()).isEqualTo(UPDATED_APELLIDO_TITULAR);
        assertThat(testCuentaAsociada.getNumeroCuenta()).isEqualTo(UPDATED_NUMERO_CUENTA);
        assertThat(testCuentaAsociada.getTipoCuenta()).isEqualTo(UPDATED_TIPO_CUENTA);
        assertThat(testCuentaAsociada.getCodigoSeguridad()).isEqualTo(UPDATED_CODIGO_SEGURIDAD);
        assertThat(testCuentaAsociada.getFechaVencimiento()).isEqualTo(UPDATED_FECHA_VENCIMIENTO);
    }

    @Test
    @Transactional
    public void updateNonExistingCuentaAsociada() throws Exception {
        int databaseSizeBeforeUpdate = cuentaAsociadaRepository.findAll().size();

        // Create the CuentaAsociada

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCuentaAsociadaMockMvc.perform(put("/api/cuenta-asociadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cuentaAsociada)))
            .andExpect(status().isBadRequest());

        // Validate the CuentaAsociada in the database
        List<CuentaAsociada> cuentaAsociadaList = cuentaAsociadaRepository.findAll();
        assertThat(cuentaAsociadaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCuentaAsociada() throws Exception {
        // Initialize the database
        cuentaAsociadaRepository.saveAndFlush(cuentaAsociada);

        int databaseSizeBeforeDelete = cuentaAsociadaRepository.findAll().size();

        // Delete the cuentaAsociada
        restCuentaAsociadaMockMvc.perform(delete("/api/cuenta-asociadas/{id}", cuentaAsociada.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CuentaAsociada> cuentaAsociadaList = cuentaAsociadaRepository.findAll();
        assertThat(cuentaAsociadaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CuentaAsociada.class);
        CuentaAsociada cuentaAsociada1 = new CuentaAsociada();
        cuentaAsociada1.setId(1L);
        CuentaAsociada cuentaAsociada2 = new CuentaAsociada();
        cuentaAsociada2.setId(cuentaAsociada1.getId());
        assertThat(cuentaAsociada1).isEqualTo(cuentaAsociada2);
        cuentaAsociada2.setId(2L);
        assertThat(cuentaAsociada1).isNotEqualTo(cuentaAsociada2);
        cuentaAsociada1.setId(null);
        assertThat(cuentaAsociada1).isNotEqualTo(cuentaAsociada2);
    }
}
