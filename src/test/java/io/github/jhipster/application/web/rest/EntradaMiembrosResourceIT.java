package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.EntradaMiembros;
import io.github.jhipster.application.repository.EntradaMiembrosRepository;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.sameInstant;
import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.domain.enumeration.TipoEntradad;
import io.github.jhipster.application.domain.enumeration.TipoIngresod;
/**
 * Integration tests for the {@Link EntradaMiembrosResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class EntradaMiembrosResourceIT {

    private static final ZonedDateTime DEFAULT_REGISTRO_FECHA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_REGISTRO_FECHA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final TipoEntradad DEFAULT_TIPO_ENTRADA = TipoEntradad.INGRESO;
    private static final TipoEntradad UPDATED_TIPO_ENTRADA = TipoEntradad.SALIDA;

    private static final TipoIngresod DEFAULT_TIPO_INGRESO = TipoIngresod.Espacio_Libre;
    private static final TipoIngresod UPDATED_TIPO_INGRESO = TipoIngresod.Reserva;

    private static final Boolean DEFAULT_TIEMPO_MAXIMO = false;
    private static final Boolean UPDATED_TIEMPO_MAXIMO = true;

    @Autowired
    private EntradaMiembrosRepository entradaMiembrosRepository;

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

    private MockMvc restEntradaMiembrosMockMvc;

    private EntradaMiembros entradaMiembros;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EntradaMiembrosResource entradaMiembrosResource = new EntradaMiembrosResource(entradaMiembrosRepository);
        this.restEntradaMiembrosMockMvc = MockMvcBuilders.standaloneSetup(entradaMiembrosResource)
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
    public static EntradaMiembros createEntity(EntityManager em) {
        EntradaMiembros entradaMiembros = new EntradaMiembros()
            .registroFecha(DEFAULT_REGISTRO_FECHA)
            .tipoEntrada(DEFAULT_TIPO_ENTRADA)
            .tipoIngreso(DEFAULT_TIPO_INGRESO)
            .tiempoMaximo(DEFAULT_TIEMPO_MAXIMO);
        return entradaMiembros;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntradaMiembros createUpdatedEntity(EntityManager em) {
        EntradaMiembros entradaMiembros = new EntradaMiembros()
            .registroFecha(UPDATED_REGISTRO_FECHA)
            .tipoEntrada(UPDATED_TIPO_ENTRADA)
            .tipoIngreso(UPDATED_TIPO_INGRESO)
            .tiempoMaximo(UPDATED_TIEMPO_MAXIMO);
        return entradaMiembros;
    }

    @BeforeEach
    public void initTest() {
        entradaMiembros = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntradaMiembros() throws Exception {
        int databaseSizeBeforeCreate = entradaMiembrosRepository.findAll().size();

        // Create the EntradaMiembros
        restEntradaMiembrosMockMvc.perform(post("/api/entrada-miembros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entradaMiembros)))
            .andExpect(status().isCreated());

        // Validate the EntradaMiembros in the database
        List<EntradaMiembros> entradaMiembrosList = entradaMiembrosRepository.findAll();
        assertThat(entradaMiembrosList).hasSize(databaseSizeBeforeCreate + 1);
        EntradaMiembros testEntradaMiembros = entradaMiembrosList.get(entradaMiembrosList.size() - 1);
        assertThat(testEntradaMiembros.getRegistroFecha()).isEqualTo(DEFAULT_REGISTRO_FECHA);
        assertThat(testEntradaMiembros.getTipoEntrada()).isEqualTo(DEFAULT_TIPO_ENTRADA);
        assertThat(testEntradaMiembros.getTipoIngreso()).isEqualTo(DEFAULT_TIPO_INGRESO);
        assertThat(testEntradaMiembros.isTiempoMaximo()).isEqualTo(DEFAULT_TIEMPO_MAXIMO);
    }

    @Test
    @Transactional
    public void createEntradaMiembrosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entradaMiembrosRepository.findAll().size();

        // Create the EntradaMiembros with an existing ID
        entradaMiembros.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntradaMiembrosMockMvc.perform(post("/api/entrada-miembros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entradaMiembros)))
            .andExpect(status().isBadRequest());

        // Validate the EntradaMiembros in the database
        List<EntradaMiembros> entradaMiembrosList = entradaMiembrosRepository.findAll();
        assertThat(entradaMiembrosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEntradaMiembros() throws Exception {
        // Initialize the database
        entradaMiembrosRepository.saveAndFlush(entradaMiembros);

        // Get all the entradaMiembrosList
        restEntradaMiembrosMockMvc.perform(get("/api/entrada-miembros?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entradaMiembros.getId().intValue())))
            .andExpect(jsonPath("$.[*].registroFecha").value(hasItem(sameInstant(DEFAULT_REGISTRO_FECHA))))
            .andExpect(jsonPath("$.[*].tipoEntrada").value(hasItem(DEFAULT_TIPO_ENTRADA.toString())))
            .andExpect(jsonPath("$.[*].tipoIngreso").value(hasItem(DEFAULT_TIPO_INGRESO.toString())))
            .andExpect(jsonPath("$.[*].tiempoMaximo").value(hasItem(DEFAULT_TIEMPO_MAXIMO.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getEntradaMiembros() throws Exception {
        // Initialize the database
        entradaMiembrosRepository.saveAndFlush(entradaMiembros);

        // Get the entradaMiembros
        restEntradaMiembrosMockMvc.perform(get("/api/entrada-miembros/{id}", entradaMiembros.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(entradaMiembros.getId().intValue()))
            .andExpect(jsonPath("$.registroFecha").value(sameInstant(DEFAULT_REGISTRO_FECHA)))
            .andExpect(jsonPath("$.tipoEntrada").value(DEFAULT_TIPO_ENTRADA.toString()))
            .andExpect(jsonPath("$.tipoIngreso").value(DEFAULT_TIPO_INGRESO.toString()))
            .andExpect(jsonPath("$.tiempoMaximo").value(DEFAULT_TIEMPO_MAXIMO.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEntradaMiembros() throws Exception {
        // Get the entradaMiembros
        restEntradaMiembrosMockMvc.perform(get("/api/entrada-miembros/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntradaMiembros() throws Exception {
        // Initialize the database
        entradaMiembrosRepository.saveAndFlush(entradaMiembros);

        int databaseSizeBeforeUpdate = entradaMiembrosRepository.findAll().size();

        // Update the entradaMiembros
        EntradaMiembros updatedEntradaMiembros = entradaMiembrosRepository.findById(entradaMiembros.getId()).get();
        // Disconnect from session so that the updates on updatedEntradaMiembros are not directly saved in db
        em.detach(updatedEntradaMiembros);
        updatedEntradaMiembros
            .registroFecha(UPDATED_REGISTRO_FECHA)
            .tipoEntrada(UPDATED_TIPO_ENTRADA)
            .tipoIngreso(UPDATED_TIPO_INGRESO)
            .tiempoMaximo(UPDATED_TIEMPO_MAXIMO);

        restEntradaMiembrosMockMvc.perform(put("/api/entrada-miembros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEntradaMiembros)))
            .andExpect(status().isOk());

        // Validate the EntradaMiembros in the database
        List<EntradaMiembros> entradaMiembrosList = entradaMiembrosRepository.findAll();
        assertThat(entradaMiembrosList).hasSize(databaseSizeBeforeUpdate);
        EntradaMiembros testEntradaMiembros = entradaMiembrosList.get(entradaMiembrosList.size() - 1);
        assertThat(testEntradaMiembros.getRegistroFecha()).isEqualTo(UPDATED_REGISTRO_FECHA);
        assertThat(testEntradaMiembros.getTipoEntrada()).isEqualTo(UPDATED_TIPO_ENTRADA);
        assertThat(testEntradaMiembros.getTipoIngreso()).isEqualTo(UPDATED_TIPO_INGRESO);
        assertThat(testEntradaMiembros.isTiempoMaximo()).isEqualTo(UPDATED_TIEMPO_MAXIMO);
    }

    @Test
    @Transactional
    public void updateNonExistingEntradaMiembros() throws Exception {
        int databaseSizeBeforeUpdate = entradaMiembrosRepository.findAll().size();

        // Create the EntradaMiembros

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntradaMiembrosMockMvc.perform(put("/api/entrada-miembros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entradaMiembros)))
            .andExpect(status().isBadRequest());

        // Validate the EntradaMiembros in the database
        List<EntradaMiembros> entradaMiembrosList = entradaMiembrosRepository.findAll();
        assertThat(entradaMiembrosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEntradaMiembros() throws Exception {
        // Initialize the database
        entradaMiembrosRepository.saveAndFlush(entradaMiembros);

        int databaseSizeBeforeDelete = entradaMiembrosRepository.findAll().size();

        // Delete the entradaMiembros
        restEntradaMiembrosMockMvc.perform(delete("/api/entrada-miembros/{id}", entradaMiembros.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EntradaMiembros> entradaMiembrosList = entradaMiembrosRepository.findAll();
        assertThat(entradaMiembrosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntradaMiembros.class);
        EntradaMiembros entradaMiembros1 = new EntradaMiembros();
        entradaMiembros1.setId(1L);
        EntradaMiembros entradaMiembros2 = new EntradaMiembros();
        entradaMiembros2.setId(entradaMiembros1.getId());
        assertThat(entradaMiembros1).isEqualTo(entradaMiembros2);
        entradaMiembros2.setId(2L);
        assertThat(entradaMiembros1).isNotEqualTo(entradaMiembros2);
        entradaMiembros1.setId(null);
        assertThat(entradaMiembros1).isNotEqualTo(entradaMiembros2);
    }
}
