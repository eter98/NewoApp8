package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.Miembros;
import io.github.jhipster.application.repository.MiembrosRepository;
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

import io.github.jhipster.application.domain.enumeration.TipoDocumentod;
import io.github.jhipster.application.domain.enumeration.Generod;
/**
 * Integration tests for the {@Link MiembrosResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class MiembrosResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDO = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDO = "BBBBBBBBBB";

    private static final String DEFAULT_NACIONALIDAD = "AAAAAAAAAA";
    private static final String UPDATED_NACIONALIDAD = "BBBBBBBBBB";

    private static final TipoDocumentod DEFAULT_TIPO_DOCUMENTO = TipoDocumentod.Cedula;
    private static final TipoDocumentod UPDATED_TIPO_DOCUMENTO = TipoDocumentod.Cedula_Extranjeria;

    private static final Integer DEFAULT_IDENTIFICACION = 1;
    private static final Integer UPDATED_IDENTIFICACION = 2;

    private static final LocalDate DEFAULT_FECHA_NACIMIENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_NACIMIENTO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FECHA_REGISTRO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_REGISTRO = LocalDate.now(ZoneId.systemDefault());

    private static final Generod DEFAULT_GENERO = Generod.Masculino;
    private static final Generod UPDATED_GENERO = Generod.Femenino;

    private static final String DEFAULT_CORREO_ELECTRONICO = "AAAAAAAAAA";
    private static final String UPDATED_CORREO_ELECTRONICO = "BBBBBBBBBB";

    private static final String DEFAULT_CELULAR = "AAAAAAAAAA";
    private static final String UPDATED_CELULAR = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DERECHOS_DE_COMPRA = false;
    private static final Boolean UPDATED_DERECHOS_DE_COMPRA = true;

    private static final Boolean DEFAULT_ACCESO_ILIMITADO = false;
    private static final Boolean UPDATED_ACCESO_ILIMITADO = true;

    private static final Boolean DEFAULT_ALIADO = false;
    private static final Boolean UPDATED_ALIADO = true;

    private static final Boolean DEFAULT_HOST = false;
    private static final Boolean UPDATED_HOST = true;

    @Autowired
    private MiembrosRepository miembrosRepository;

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

    private MockMvc restMiembrosMockMvc;

    private Miembros miembros;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MiembrosResource miembrosResource = new MiembrosResource(miembrosRepository);
        this.restMiembrosMockMvc = MockMvcBuilders.standaloneSetup(miembrosResource)
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
    public static Miembros createEntity(EntityManager em) {
        Miembros miembros = new Miembros()
            .nombre(DEFAULT_NOMBRE)
            .apellido(DEFAULT_APELLIDO)
            .nacionalidad(DEFAULT_NACIONALIDAD)
            .tipoDocumento(DEFAULT_TIPO_DOCUMENTO)
            .identificacion(DEFAULT_IDENTIFICACION)
            .fechaNacimiento(DEFAULT_FECHA_NACIMIENTO)
            .fechaRegistro(DEFAULT_FECHA_REGISTRO)
            .genero(DEFAULT_GENERO)
            .correoElectronico(DEFAULT_CORREO_ELECTRONICO)
            .celular(DEFAULT_CELULAR)
            .derechosDeCompra(DEFAULT_DERECHOS_DE_COMPRA)
            .accesoIlimitado(DEFAULT_ACCESO_ILIMITADO)
            .aliado(DEFAULT_ALIADO)
            .host(DEFAULT_HOST);
        return miembros;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Miembros createUpdatedEntity(EntityManager em) {
        Miembros miembros = new Miembros()
            .nombre(UPDATED_NOMBRE)
            .apellido(UPDATED_APELLIDO)
            .nacionalidad(UPDATED_NACIONALIDAD)
            .tipoDocumento(UPDATED_TIPO_DOCUMENTO)
            .identificacion(UPDATED_IDENTIFICACION)
            .fechaNacimiento(UPDATED_FECHA_NACIMIENTO)
            .fechaRegistro(UPDATED_FECHA_REGISTRO)
            .genero(UPDATED_GENERO)
            .correoElectronico(UPDATED_CORREO_ELECTRONICO)
            .celular(UPDATED_CELULAR)
            .derechosDeCompra(UPDATED_DERECHOS_DE_COMPRA)
            .accesoIlimitado(UPDATED_ACCESO_ILIMITADO)
            .aliado(UPDATED_ALIADO)
            .host(UPDATED_HOST);
        return miembros;
    }

    @BeforeEach
    public void initTest() {
        miembros = createEntity(em);
    }

    @Test
    @Transactional
    public void createMiembros() throws Exception {
        int databaseSizeBeforeCreate = miembrosRepository.findAll().size();

        // Create the Miembros
        restMiembrosMockMvc.perform(post("/api/miembros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(miembros)))
            .andExpect(status().isCreated());

        // Validate the Miembros in the database
        List<Miembros> miembrosList = miembrosRepository.findAll();
        assertThat(miembrosList).hasSize(databaseSizeBeforeCreate + 1);
        Miembros testMiembros = miembrosList.get(miembrosList.size() - 1);
        assertThat(testMiembros.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testMiembros.getApellido()).isEqualTo(DEFAULT_APELLIDO);
        assertThat(testMiembros.getNacionalidad()).isEqualTo(DEFAULT_NACIONALIDAD);
        assertThat(testMiembros.getTipoDocumento()).isEqualTo(DEFAULT_TIPO_DOCUMENTO);
        assertThat(testMiembros.getIdentificacion()).isEqualTo(DEFAULT_IDENTIFICACION);
        assertThat(testMiembros.getFechaNacimiento()).isEqualTo(DEFAULT_FECHA_NACIMIENTO);
        assertThat(testMiembros.getFechaRegistro()).isEqualTo(DEFAULT_FECHA_REGISTRO);
        assertThat(testMiembros.getGenero()).isEqualTo(DEFAULT_GENERO);
        assertThat(testMiembros.getCorreoElectronico()).isEqualTo(DEFAULT_CORREO_ELECTRONICO);
        assertThat(testMiembros.getCelular()).isEqualTo(DEFAULT_CELULAR);
        assertThat(testMiembros.isDerechosDeCompra()).isEqualTo(DEFAULT_DERECHOS_DE_COMPRA);
        assertThat(testMiembros.isAccesoIlimitado()).isEqualTo(DEFAULT_ACCESO_ILIMITADO);
        assertThat(testMiembros.isAliado()).isEqualTo(DEFAULT_ALIADO);
        assertThat(testMiembros.isHost()).isEqualTo(DEFAULT_HOST);
    }

    @Test
    @Transactional
    public void createMiembrosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = miembrosRepository.findAll().size();

        // Create the Miembros with an existing ID
        miembros.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMiembrosMockMvc.perform(post("/api/miembros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(miembros)))
            .andExpect(status().isBadRequest());

        // Validate the Miembros in the database
        List<Miembros> miembrosList = miembrosRepository.findAll();
        assertThat(miembrosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMiembros() throws Exception {
        // Initialize the database
        miembrosRepository.saveAndFlush(miembros);

        // Get all the miembrosList
        restMiembrosMockMvc.perform(get("/api/miembros?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(miembros.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].apellido").value(hasItem(DEFAULT_APELLIDO.toString())))
            .andExpect(jsonPath("$.[*].nacionalidad").value(hasItem(DEFAULT_NACIONALIDAD.toString())))
            .andExpect(jsonPath("$.[*].tipoDocumento").value(hasItem(DEFAULT_TIPO_DOCUMENTO.toString())))
            .andExpect(jsonPath("$.[*].identificacion").value(hasItem(DEFAULT_IDENTIFICACION)))
            .andExpect(jsonPath("$.[*].fechaNacimiento").value(hasItem(DEFAULT_FECHA_NACIMIENTO.toString())))
            .andExpect(jsonPath("$.[*].fechaRegistro").value(hasItem(DEFAULT_FECHA_REGISTRO.toString())))
            .andExpect(jsonPath("$.[*].genero").value(hasItem(DEFAULT_GENERO.toString())))
            .andExpect(jsonPath("$.[*].correoElectronico").value(hasItem(DEFAULT_CORREO_ELECTRONICO.toString())))
            .andExpect(jsonPath("$.[*].celular").value(hasItem(DEFAULT_CELULAR.toString())))
            .andExpect(jsonPath("$.[*].derechosDeCompra").value(hasItem(DEFAULT_DERECHOS_DE_COMPRA.booleanValue())))
            .andExpect(jsonPath("$.[*].accesoIlimitado").value(hasItem(DEFAULT_ACCESO_ILIMITADO.booleanValue())))
            .andExpect(jsonPath("$.[*].aliado").value(hasItem(DEFAULT_ALIADO.booleanValue())))
            .andExpect(jsonPath("$.[*].host").value(hasItem(DEFAULT_HOST.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getMiembros() throws Exception {
        // Initialize the database
        miembrosRepository.saveAndFlush(miembros);

        // Get the miembros
        restMiembrosMockMvc.perform(get("/api/miembros/{id}", miembros.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(miembros.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.apellido").value(DEFAULT_APELLIDO.toString()))
            .andExpect(jsonPath("$.nacionalidad").value(DEFAULT_NACIONALIDAD.toString()))
            .andExpect(jsonPath("$.tipoDocumento").value(DEFAULT_TIPO_DOCUMENTO.toString()))
            .andExpect(jsonPath("$.identificacion").value(DEFAULT_IDENTIFICACION))
            .andExpect(jsonPath("$.fechaNacimiento").value(DEFAULT_FECHA_NACIMIENTO.toString()))
            .andExpect(jsonPath("$.fechaRegistro").value(DEFAULT_FECHA_REGISTRO.toString()))
            .andExpect(jsonPath("$.genero").value(DEFAULT_GENERO.toString()))
            .andExpect(jsonPath("$.correoElectronico").value(DEFAULT_CORREO_ELECTRONICO.toString()))
            .andExpect(jsonPath("$.celular").value(DEFAULT_CELULAR.toString()))
            .andExpect(jsonPath("$.derechosDeCompra").value(DEFAULT_DERECHOS_DE_COMPRA.booleanValue()))
            .andExpect(jsonPath("$.accesoIlimitado").value(DEFAULT_ACCESO_ILIMITADO.booleanValue()))
            .andExpect(jsonPath("$.aliado").value(DEFAULT_ALIADO.booleanValue()))
            .andExpect(jsonPath("$.host").value(DEFAULT_HOST.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMiembros() throws Exception {
        // Get the miembros
        restMiembrosMockMvc.perform(get("/api/miembros/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMiembros() throws Exception {
        // Initialize the database
        miembrosRepository.saveAndFlush(miembros);

        int databaseSizeBeforeUpdate = miembrosRepository.findAll().size();

        // Update the miembros
        Miembros updatedMiembros = miembrosRepository.findById(miembros.getId()).get();
        // Disconnect from session so that the updates on updatedMiembros are not directly saved in db
        em.detach(updatedMiembros);
        updatedMiembros
            .nombre(UPDATED_NOMBRE)
            .apellido(UPDATED_APELLIDO)
            .nacionalidad(UPDATED_NACIONALIDAD)
            .tipoDocumento(UPDATED_TIPO_DOCUMENTO)
            .identificacion(UPDATED_IDENTIFICACION)
            .fechaNacimiento(UPDATED_FECHA_NACIMIENTO)
            .fechaRegistro(UPDATED_FECHA_REGISTRO)
            .genero(UPDATED_GENERO)
            .correoElectronico(UPDATED_CORREO_ELECTRONICO)
            .celular(UPDATED_CELULAR)
            .derechosDeCompra(UPDATED_DERECHOS_DE_COMPRA)
            .accesoIlimitado(UPDATED_ACCESO_ILIMITADO)
            .aliado(UPDATED_ALIADO)
            .host(UPDATED_HOST);

        restMiembrosMockMvc.perform(put("/api/miembros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMiembros)))
            .andExpect(status().isOk());

        // Validate the Miembros in the database
        List<Miembros> miembrosList = miembrosRepository.findAll();
        assertThat(miembrosList).hasSize(databaseSizeBeforeUpdate);
        Miembros testMiembros = miembrosList.get(miembrosList.size() - 1);
        assertThat(testMiembros.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testMiembros.getApellido()).isEqualTo(UPDATED_APELLIDO);
        assertThat(testMiembros.getNacionalidad()).isEqualTo(UPDATED_NACIONALIDAD);
        assertThat(testMiembros.getTipoDocumento()).isEqualTo(UPDATED_TIPO_DOCUMENTO);
        assertThat(testMiembros.getIdentificacion()).isEqualTo(UPDATED_IDENTIFICACION);
        assertThat(testMiembros.getFechaNacimiento()).isEqualTo(UPDATED_FECHA_NACIMIENTO);
        assertThat(testMiembros.getFechaRegistro()).isEqualTo(UPDATED_FECHA_REGISTRO);
        assertThat(testMiembros.getGenero()).isEqualTo(UPDATED_GENERO);
        assertThat(testMiembros.getCorreoElectronico()).isEqualTo(UPDATED_CORREO_ELECTRONICO);
        assertThat(testMiembros.getCelular()).isEqualTo(UPDATED_CELULAR);
        assertThat(testMiembros.isDerechosDeCompra()).isEqualTo(UPDATED_DERECHOS_DE_COMPRA);
        assertThat(testMiembros.isAccesoIlimitado()).isEqualTo(UPDATED_ACCESO_ILIMITADO);
        assertThat(testMiembros.isAliado()).isEqualTo(UPDATED_ALIADO);
        assertThat(testMiembros.isHost()).isEqualTo(UPDATED_HOST);
    }

    @Test
    @Transactional
    public void updateNonExistingMiembros() throws Exception {
        int databaseSizeBeforeUpdate = miembrosRepository.findAll().size();

        // Create the Miembros

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMiembrosMockMvc.perform(put("/api/miembros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(miembros)))
            .andExpect(status().isBadRequest());

        // Validate the Miembros in the database
        List<Miembros> miembrosList = miembrosRepository.findAll();
        assertThat(miembrosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMiembros() throws Exception {
        // Initialize the database
        miembrosRepository.saveAndFlush(miembros);

        int databaseSizeBeforeDelete = miembrosRepository.findAll().size();

        // Delete the miembros
        restMiembrosMockMvc.perform(delete("/api/miembros/{id}", miembros.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Miembros> miembrosList = miembrosRepository.findAll();
        assertThat(miembrosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Miembros.class);
        Miembros miembros1 = new Miembros();
        miembros1.setId(1L);
        Miembros miembros2 = new Miembros();
        miembros2.setId(miembros1.getId());
        assertThat(miembros1).isEqualTo(miembros2);
        miembros2.setId(2L);
        assertThat(miembros1).isNotEqualTo(miembros2);
        miembros1.setId(null);
        assertThat(miembros1).isNotEqualTo(miembros2);
    }
}
