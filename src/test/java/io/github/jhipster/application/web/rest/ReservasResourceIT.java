package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.Reservas;
import io.github.jhipster.application.repository.ReservasRepository;
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

import io.github.jhipster.application.domain.enumeration.EstadoReservad;
/**
 * Integration tests for the {@Link ReservasResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class ReservasResourceIT {

    private static final ZonedDateTime DEFAULT_REGISTRO_FECHA_ENTRADA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_REGISTRO_FECHA_ENTRADA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_REGISTRO_FECHA_SALIDA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_REGISTRO_FECHA_SALIDA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final EstadoReservad DEFAULT_ESTADO_RESERVA = EstadoReservad.Diseponible;
    private static final EstadoReservad UPDATED_ESTADO_RESERVA = EstadoReservad.Reservado;

    @Autowired
    private ReservasRepository reservasRepository;

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

    private MockMvc restReservasMockMvc;

    private Reservas reservas;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ReservasResource reservasResource = new ReservasResource(reservasRepository);
        this.restReservasMockMvc = MockMvcBuilders.standaloneSetup(reservasResource)
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
    public static Reservas createEntity(EntityManager em) {
        Reservas reservas = new Reservas()
            .registroFechaEntrada(DEFAULT_REGISTRO_FECHA_ENTRADA)
            .registroFechaSalida(DEFAULT_REGISTRO_FECHA_SALIDA)
            .estadoReserva(DEFAULT_ESTADO_RESERVA);
        return reservas;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Reservas createUpdatedEntity(EntityManager em) {
        Reservas reservas = new Reservas()
            .registroFechaEntrada(UPDATED_REGISTRO_FECHA_ENTRADA)
            .registroFechaSalida(UPDATED_REGISTRO_FECHA_SALIDA)
            .estadoReserva(UPDATED_ESTADO_RESERVA);
        return reservas;
    }

    @BeforeEach
    public void initTest() {
        reservas = createEntity(em);
    }

    @Test
    @Transactional
    public void createReservas() throws Exception {
        int databaseSizeBeforeCreate = reservasRepository.findAll().size();

        // Create the Reservas
        restReservasMockMvc.perform(post("/api/reservas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reservas)))
            .andExpect(status().isCreated());

        // Validate the Reservas in the database
        List<Reservas> reservasList = reservasRepository.findAll();
        assertThat(reservasList).hasSize(databaseSizeBeforeCreate + 1);
        Reservas testReservas = reservasList.get(reservasList.size() - 1);
        assertThat(testReservas.getRegistroFechaEntrada()).isEqualTo(DEFAULT_REGISTRO_FECHA_ENTRADA);
        assertThat(testReservas.getRegistroFechaSalida()).isEqualTo(DEFAULT_REGISTRO_FECHA_SALIDA);
        assertThat(testReservas.getEstadoReserva()).isEqualTo(DEFAULT_ESTADO_RESERVA);
    }

    @Test
    @Transactional
    public void createReservasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reservasRepository.findAll().size();

        // Create the Reservas with an existing ID
        reservas.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReservasMockMvc.perform(post("/api/reservas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reservas)))
            .andExpect(status().isBadRequest());

        // Validate the Reservas in the database
        List<Reservas> reservasList = reservasRepository.findAll();
        assertThat(reservasList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllReservas() throws Exception {
        // Initialize the database
        reservasRepository.saveAndFlush(reservas);

        // Get all the reservasList
        restReservasMockMvc.perform(get("/api/reservas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reservas.getId().intValue())))
            .andExpect(jsonPath("$.[*].registroFechaEntrada").value(hasItem(sameInstant(DEFAULT_REGISTRO_FECHA_ENTRADA))))
            .andExpect(jsonPath("$.[*].registroFechaSalida").value(hasItem(sameInstant(DEFAULT_REGISTRO_FECHA_SALIDA))))
            .andExpect(jsonPath("$.[*].estadoReserva").value(hasItem(DEFAULT_ESTADO_RESERVA.toString())));
    }
    
    @Test
    @Transactional
    public void getReservas() throws Exception {
        // Initialize the database
        reservasRepository.saveAndFlush(reservas);

        // Get the reservas
        restReservasMockMvc.perform(get("/api/reservas/{id}", reservas.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(reservas.getId().intValue()))
            .andExpect(jsonPath("$.registroFechaEntrada").value(sameInstant(DEFAULT_REGISTRO_FECHA_ENTRADA)))
            .andExpect(jsonPath("$.registroFechaSalida").value(sameInstant(DEFAULT_REGISTRO_FECHA_SALIDA)))
            .andExpect(jsonPath("$.estadoReserva").value(DEFAULT_ESTADO_RESERVA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingReservas() throws Exception {
        // Get the reservas
        restReservasMockMvc.perform(get("/api/reservas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReservas() throws Exception {
        // Initialize the database
        reservasRepository.saveAndFlush(reservas);

        int databaseSizeBeforeUpdate = reservasRepository.findAll().size();

        // Update the reservas
        Reservas updatedReservas = reservasRepository.findById(reservas.getId()).get();
        // Disconnect from session so that the updates on updatedReservas are not directly saved in db
        em.detach(updatedReservas);
        updatedReservas
            .registroFechaEntrada(UPDATED_REGISTRO_FECHA_ENTRADA)
            .registroFechaSalida(UPDATED_REGISTRO_FECHA_SALIDA)
            .estadoReserva(UPDATED_ESTADO_RESERVA);

        restReservasMockMvc.perform(put("/api/reservas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedReservas)))
            .andExpect(status().isOk());

        // Validate the Reservas in the database
        List<Reservas> reservasList = reservasRepository.findAll();
        assertThat(reservasList).hasSize(databaseSizeBeforeUpdate);
        Reservas testReservas = reservasList.get(reservasList.size() - 1);
        assertThat(testReservas.getRegistroFechaEntrada()).isEqualTo(UPDATED_REGISTRO_FECHA_ENTRADA);
        assertThat(testReservas.getRegistroFechaSalida()).isEqualTo(UPDATED_REGISTRO_FECHA_SALIDA);
        assertThat(testReservas.getEstadoReserva()).isEqualTo(UPDATED_ESTADO_RESERVA);
    }

    @Test
    @Transactional
    public void updateNonExistingReservas() throws Exception {
        int databaseSizeBeforeUpdate = reservasRepository.findAll().size();

        // Create the Reservas

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReservasMockMvc.perform(put("/api/reservas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reservas)))
            .andExpect(status().isBadRequest());

        // Validate the Reservas in the database
        List<Reservas> reservasList = reservasRepository.findAll();
        assertThat(reservasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReservas() throws Exception {
        // Initialize the database
        reservasRepository.saveAndFlush(reservas);

        int databaseSizeBeforeDelete = reservasRepository.findAll().size();

        // Delete the reservas
        restReservasMockMvc.perform(delete("/api/reservas/{id}", reservas.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Reservas> reservasList = reservasRepository.findAll();
        assertThat(reservasList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Reservas.class);
        Reservas reservas1 = new Reservas();
        reservas1.setId(1L);
        Reservas reservas2 = new Reservas();
        reservas2.setId(reservas1.getId());
        assertThat(reservas1).isEqualTo(reservas2);
        reservas2.setId(2L);
        assertThat(reservas1).isNotEqualTo(reservas2);
        reservas1.setId(null);
        assertThat(reservas1).isNotEqualTo(reservas2);
    }
}
