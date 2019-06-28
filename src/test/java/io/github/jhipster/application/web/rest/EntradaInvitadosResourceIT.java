package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.EntradaInvitados;
import io.github.jhipster.application.repository.EntradaInvitadosRepository;
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
 * Integration tests for the {@Link EntradaInvitadosResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class EntradaInvitadosResourceIT {

    private static final ZonedDateTime DEFAULT_REGISTRO_FECHA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_REGISTRO_FECHA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final TipoEntradad DEFAULT_TIPO_ENTRADA = TipoEntradad.INGRESO;
    private static final TipoEntradad UPDATED_TIPO_ENTRADA = TipoEntradad.SALIDA;

    private static final TipoIngresod DEFAULT_TIPO_INGRESO = TipoIngresod.Espacio_Libre;
    private static final TipoIngresod UPDATED_TIPO_INGRESO = TipoIngresod.Reserva;

    private static final Boolean DEFAULT_TIEMPO_MAXIMO = false;
    private static final Boolean UPDATED_TIEMPO_MAXIMO = true;

    @Autowired
    private EntradaInvitadosRepository entradaInvitadosRepository;

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

    private MockMvc restEntradaInvitadosMockMvc;

    private EntradaInvitados entradaInvitados;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EntradaInvitadosResource entradaInvitadosResource = new EntradaInvitadosResource(entradaInvitadosRepository);
        this.restEntradaInvitadosMockMvc = MockMvcBuilders.standaloneSetup(entradaInvitadosResource)
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
    public static EntradaInvitados createEntity(EntityManager em) {
        EntradaInvitados entradaInvitados = new EntradaInvitados()
            .registroFecha(DEFAULT_REGISTRO_FECHA)
            .tipoEntrada(DEFAULT_TIPO_ENTRADA)
            .tipoIngreso(DEFAULT_TIPO_INGRESO)
            .tiempoMaximo(DEFAULT_TIEMPO_MAXIMO);
        return entradaInvitados;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntradaInvitados createUpdatedEntity(EntityManager em) {
        EntradaInvitados entradaInvitados = new EntradaInvitados()
            .registroFecha(UPDATED_REGISTRO_FECHA)
            .tipoEntrada(UPDATED_TIPO_ENTRADA)
            .tipoIngreso(UPDATED_TIPO_INGRESO)
            .tiempoMaximo(UPDATED_TIEMPO_MAXIMO);
        return entradaInvitados;
    }

    @BeforeEach
    public void initTest() {
        entradaInvitados = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntradaInvitados() throws Exception {
        int databaseSizeBeforeCreate = entradaInvitadosRepository.findAll().size();

        // Create the EntradaInvitados
        restEntradaInvitadosMockMvc.perform(post("/api/entrada-invitados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entradaInvitados)))
            .andExpect(status().isCreated());

        // Validate the EntradaInvitados in the database
        List<EntradaInvitados> entradaInvitadosList = entradaInvitadosRepository.findAll();
        assertThat(entradaInvitadosList).hasSize(databaseSizeBeforeCreate + 1);
        EntradaInvitados testEntradaInvitados = entradaInvitadosList.get(entradaInvitadosList.size() - 1);
        assertThat(testEntradaInvitados.getRegistroFecha()).isEqualTo(DEFAULT_REGISTRO_FECHA);
        assertThat(testEntradaInvitados.getTipoEntrada()).isEqualTo(DEFAULT_TIPO_ENTRADA);
        assertThat(testEntradaInvitados.getTipoIngreso()).isEqualTo(DEFAULT_TIPO_INGRESO);
        assertThat(testEntradaInvitados.isTiempoMaximo()).isEqualTo(DEFAULT_TIEMPO_MAXIMO);
    }

    @Test
    @Transactional
    public void createEntradaInvitadosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entradaInvitadosRepository.findAll().size();

        // Create the EntradaInvitados with an existing ID
        entradaInvitados.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntradaInvitadosMockMvc.perform(post("/api/entrada-invitados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entradaInvitados)))
            .andExpect(status().isBadRequest());

        // Validate the EntradaInvitados in the database
        List<EntradaInvitados> entradaInvitadosList = entradaInvitadosRepository.findAll();
        assertThat(entradaInvitadosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEntradaInvitados() throws Exception {
        // Initialize the database
        entradaInvitadosRepository.saveAndFlush(entradaInvitados);

        // Get all the entradaInvitadosList
        restEntradaInvitadosMockMvc.perform(get("/api/entrada-invitados?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entradaInvitados.getId().intValue())))
            .andExpect(jsonPath("$.[*].registroFecha").value(hasItem(sameInstant(DEFAULT_REGISTRO_FECHA))))
            .andExpect(jsonPath("$.[*].tipoEntrada").value(hasItem(DEFAULT_TIPO_ENTRADA.toString())))
            .andExpect(jsonPath("$.[*].tipoIngreso").value(hasItem(DEFAULT_TIPO_INGRESO.toString())))
            .andExpect(jsonPath("$.[*].tiempoMaximo").value(hasItem(DEFAULT_TIEMPO_MAXIMO.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getEntradaInvitados() throws Exception {
        // Initialize the database
        entradaInvitadosRepository.saveAndFlush(entradaInvitados);

        // Get the entradaInvitados
        restEntradaInvitadosMockMvc.perform(get("/api/entrada-invitados/{id}", entradaInvitados.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(entradaInvitados.getId().intValue()))
            .andExpect(jsonPath("$.registroFecha").value(sameInstant(DEFAULT_REGISTRO_FECHA)))
            .andExpect(jsonPath("$.tipoEntrada").value(DEFAULT_TIPO_ENTRADA.toString()))
            .andExpect(jsonPath("$.tipoIngreso").value(DEFAULT_TIPO_INGRESO.toString()))
            .andExpect(jsonPath("$.tiempoMaximo").value(DEFAULT_TIEMPO_MAXIMO.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEntradaInvitados() throws Exception {
        // Get the entradaInvitados
        restEntradaInvitadosMockMvc.perform(get("/api/entrada-invitados/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntradaInvitados() throws Exception {
        // Initialize the database
        entradaInvitadosRepository.saveAndFlush(entradaInvitados);

        int databaseSizeBeforeUpdate = entradaInvitadosRepository.findAll().size();

        // Update the entradaInvitados
        EntradaInvitados updatedEntradaInvitados = entradaInvitadosRepository.findById(entradaInvitados.getId()).get();
        // Disconnect from session so that the updates on updatedEntradaInvitados are not directly saved in db
        em.detach(updatedEntradaInvitados);
        updatedEntradaInvitados
            .registroFecha(UPDATED_REGISTRO_FECHA)
            .tipoEntrada(UPDATED_TIPO_ENTRADA)
            .tipoIngreso(UPDATED_TIPO_INGRESO)
            .tiempoMaximo(UPDATED_TIEMPO_MAXIMO);

        restEntradaInvitadosMockMvc.perform(put("/api/entrada-invitados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEntradaInvitados)))
            .andExpect(status().isOk());

        // Validate the EntradaInvitados in the database
        List<EntradaInvitados> entradaInvitadosList = entradaInvitadosRepository.findAll();
        assertThat(entradaInvitadosList).hasSize(databaseSizeBeforeUpdate);
        EntradaInvitados testEntradaInvitados = entradaInvitadosList.get(entradaInvitadosList.size() - 1);
        assertThat(testEntradaInvitados.getRegistroFecha()).isEqualTo(UPDATED_REGISTRO_FECHA);
        assertThat(testEntradaInvitados.getTipoEntrada()).isEqualTo(UPDATED_TIPO_ENTRADA);
        assertThat(testEntradaInvitados.getTipoIngreso()).isEqualTo(UPDATED_TIPO_INGRESO);
        assertThat(testEntradaInvitados.isTiempoMaximo()).isEqualTo(UPDATED_TIEMPO_MAXIMO);
    }

    @Test
    @Transactional
    public void updateNonExistingEntradaInvitados() throws Exception {
        int databaseSizeBeforeUpdate = entradaInvitadosRepository.findAll().size();

        // Create the EntradaInvitados

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntradaInvitadosMockMvc.perform(put("/api/entrada-invitados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entradaInvitados)))
            .andExpect(status().isBadRequest());

        // Validate the EntradaInvitados in the database
        List<EntradaInvitados> entradaInvitadosList = entradaInvitadosRepository.findAll();
        assertThat(entradaInvitadosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEntradaInvitados() throws Exception {
        // Initialize the database
        entradaInvitadosRepository.saveAndFlush(entradaInvitados);

        int databaseSizeBeforeDelete = entradaInvitadosRepository.findAll().size();

        // Delete the entradaInvitados
        restEntradaInvitadosMockMvc.perform(delete("/api/entrada-invitados/{id}", entradaInvitados.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EntradaInvitados> entradaInvitadosList = entradaInvitadosRepository.findAll();
        assertThat(entradaInvitadosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntradaInvitados.class);
        EntradaInvitados entradaInvitados1 = new EntradaInvitados();
        entradaInvitados1.setId(1L);
        EntradaInvitados entradaInvitados2 = new EntradaInvitados();
        entradaInvitados2.setId(entradaInvitados1.getId());
        assertThat(entradaInvitados1).isEqualTo(entradaInvitados2);
        entradaInvitados2.setId(2L);
        assertThat(entradaInvitados1).isNotEqualTo(entradaInvitados2);
        entradaInvitados1.setId(null);
        assertThat(entradaInvitados1).isNotEqualTo(entradaInvitados2);
    }
}
