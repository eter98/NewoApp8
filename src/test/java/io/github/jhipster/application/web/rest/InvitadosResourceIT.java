package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.Invitados;
import io.github.jhipster.application.repository.InvitadosRepository;
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
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.domain.enumeration.TipoDocumentod;
/**
 * Integration tests for the {@Link InvitadosResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class InvitadosResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDO = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDO = "BBBBBBBBBB";

    private static final TipoDocumentod DEFAULT_TIPO_DOCUMENTO = TipoDocumentod.Cedula;
    private static final TipoDocumentod UPDATED_TIPO_DOCUMENTO = TipoDocumentod.Cedula_Extranjeria;

    private static final String DEFAULT_IDENTIFICACION = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFICACION = "BBBBBBBBBB";

    private static final String DEFAULT_CORREO = "AAAAAAAAAA";
    private static final String UPDATED_CORREO = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    @Autowired
    private InvitadosRepository invitadosRepository;

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

    private MockMvc restInvitadosMockMvc;

    private Invitados invitados;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InvitadosResource invitadosResource = new InvitadosResource(invitadosRepository);
        this.restInvitadosMockMvc = MockMvcBuilders.standaloneSetup(invitadosResource)
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
    public static Invitados createEntity(EntityManager em) {
        Invitados invitados = new Invitados()
            .nombre(DEFAULT_NOMBRE)
            .apellido(DEFAULT_APELLIDO)
            .tipoDocumento(DEFAULT_TIPO_DOCUMENTO)
            .identificacion(DEFAULT_IDENTIFICACION)
            .correo(DEFAULT_CORREO)
            .telefono(DEFAULT_TELEFONO);
        return invitados;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Invitados createUpdatedEntity(EntityManager em) {
        Invitados invitados = new Invitados()
            .nombre(UPDATED_NOMBRE)
            .apellido(UPDATED_APELLIDO)
            .tipoDocumento(UPDATED_TIPO_DOCUMENTO)
            .identificacion(UPDATED_IDENTIFICACION)
            .correo(UPDATED_CORREO)
            .telefono(UPDATED_TELEFONO);
        return invitados;
    }

    @BeforeEach
    public void initTest() {
        invitados = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvitados() throws Exception {
        int databaseSizeBeforeCreate = invitadosRepository.findAll().size();

        // Create the Invitados
        restInvitadosMockMvc.perform(post("/api/invitados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(invitados)))
            .andExpect(status().isCreated());

        // Validate the Invitados in the database
        List<Invitados> invitadosList = invitadosRepository.findAll();
        assertThat(invitadosList).hasSize(databaseSizeBeforeCreate + 1);
        Invitados testInvitados = invitadosList.get(invitadosList.size() - 1);
        assertThat(testInvitados.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testInvitados.getApellido()).isEqualTo(DEFAULT_APELLIDO);
        assertThat(testInvitados.getTipoDocumento()).isEqualTo(DEFAULT_TIPO_DOCUMENTO);
        assertThat(testInvitados.getIdentificacion()).isEqualTo(DEFAULT_IDENTIFICACION);
        assertThat(testInvitados.getCorreo()).isEqualTo(DEFAULT_CORREO);
        assertThat(testInvitados.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
    }

    @Test
    @Transactional
    public void createInvitadosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = invitadosRepository.findAll().size();

        // Create the Invitados with an existing ID
        invitados.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvitadosMockMvc.perform(post("/api/invitados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(invitados)))
            .andExpect(status().isBadRequest());

        // Validate the Invitados in the database
        List<Invitados> invitadosList = invitadosRepository.findAll();
        assertThat(invitadosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInvitados() throws Exception {
        // Initialize the database
        invitadosRepository.saveAndFlush(invitados);

        // Get all the invitadosList
        restInvitadosMockMvc.perform(get("/api/invitados?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invitados.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].apellido").value(hasItem(DEFAULT_APELLIDO.toString())))
            .andExpect(jsonPath("$.[*].tipoDocumento").value(hasItem(DEFAULT_TIPO_DOCUMENTO.toString())))
            .andExpect(jsonPath("$.[*].identificacion").value(hasItem(DEFAULT_IDENTIFICACION.toString())))
            .andExpect(jsonPath("$.[*].correo").value(hasItem(DEFAULT_CORREO.toString())))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO.toString())));
    }
    
    @Test
    @Transactional
    public void getInvitados() throws Exception {
        // Initialize the database
        invitadosRepository.saveAndFlush(invitados);

        // Get the invitados
        restInvitadosMockMvc.perform(get("/api/invitados/{id}", invitados.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(invitados.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.apellido").value(DEFAULT_APELLIDO.toString()))
            .andExpect(jsonPath("$.tipoDocumento").value(DEFAULT_TIPO_DOCUMENTO.toString()))
            .andExpect(jsonPath("$.identificacion").value(DEFAULT_IDENTIFICACION.toString()))
            .andExpect(jsonPath("$.correo").value(DEFAULT_CORREO.toString()))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInvitados() throws Exception {
        // Get the invitados
        restInvitadosMockMvc.perform(get("/api/invitados/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvitados() throws Exception {
        // Initialize the database
        invitadosRepository.saveAndFlush(invitados);

        int databaseSizeBeforeUpdate = invitadosRepository.findAll().size();

        // Update the invitados
        Invitados updatedInvitados = invitadosRepository.findById(invitados.getId()).get();
        // Disconnect from session so that the updates on updatedInvitados are not directly saved in db
        em.detach(updatedInvitados);
        updatedInvitados
            .nombre(UPDATED_NOMBRE)
            .apellido(UPDATED_APELLIDO)
            .tipoDocumento(UPDATED_TIPO_DOCUMENTO)
            .identificacion(UPDATED_IDENTIFICACION)
            .correo(UPDATED_CORREO)
            .telefono(UPDATED_TELEFONO);

        restInvitadosMockMvc.perform(put("/api/invitados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedInvitados)))
            .andExpect(status().isOk());

        // Validate the Invitados in the database
        List<Invitados> invitadosList = invitadosRepository.findAll();
        assertThat(invitadosList).hasSize(databaseSizeBeforeUpdate);
        Invitados testInvitados = invitadosList.get(invitadosList.size() - 1);
        assertThat(testInvitados.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testInvitados.getApellido()).isEqualTo(UPDATED_APELLIDO);
        assertThat(testInvitados.getTipoDocumento()).isEqualTo(UPDATED_TIPO_DOCUMENTO);
        assertThat(testInvitados.getIdentificacion()).isEqualTo(UPDATED_IDENTIFICACION);
        assertThat(testInvitados.getCorreo()).isEqualTo(UPDATED_CORREO);
        assertThat(testInvitados.getTelefono()).isEqualTo(UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void updateNonExistingInvitados() throws Exception {
        int databaseSizeBeforeUpdate = invitadosRepository.findAll().size();

        // Create the Invitados

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvitadosMockMvc.perform(put("/api/invitados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(invitados)))
            .andExpect(status().isBadRequest());

        // Validate the Invitados in the database
        List<Invitados> invitadosList = invitadosRepository.findAll();
        assertThat(invitadosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInvitados() throws Exception {
        // Initialize the database
        invitadosRepository.saveAndFlush(invitados);

        int databaseSizeBeforeDelete = invitadosRepository.findAll().size();

        // Delete the invitados
        restInvitadosMockMvc.perform(delete("/api/invitados/{id}", invitados.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Invitados> invitadosList = invitadosRepository.findAll();
        assertThat(invitadosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Invitados.class);
        Invitados invitados1 = new Invitados();
        invitados1.setId(1L);
        Invitados invitados2 = new Invitados();
        invitados2.setId(invitados1.getId());
        assertThat(invitados1).isEqualTo(invitados2);
        invitados2.setId(2L);
        assertThat(invitados1).isNotEqualTo(invitados2);
        invitados1.setId(null);
        assertThat(invitados1).isNotEqualTo(invitados2);
    }
}
