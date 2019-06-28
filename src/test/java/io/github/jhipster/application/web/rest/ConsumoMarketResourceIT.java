package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.ConsumoMarket;
import io.github.jhipster.application.repository.ConsumoMarketRepository;
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

import io.github.jhipster.application.domain.enumeration.Impuestod;
/**
 * Integration tests for the {@Link ConsumoMarketResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class ConsumoMarketResourceIT {

    private static final Integer DEFAULT_TOTAL_CONSUMIDO = 1;
    private static final Integer UPDATED_TOTAL_CONSUMIDO = 2;

    private static final LocalDate DEFAULT_REGISTRO_FECHA_INICIAL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REGISTRO_FECHA_INICIAL = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_REGISTRO_FECHA_FINAL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REGISTRO_FECHA_FINAL = LocalDate.now(ZoneId.systemDefault());

    private static final Impuestod DEFAULT_IMPUESTO = Impuestod.IVA19;
    private static final Impuestod UPDATED_IMPUESTO = Impuestod.IVA6;

    @Autowired
    private ConsumoMarketRepository consumoMarketRepository;

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

    private MockMvc restConsumoMarketMockMvc;

    private ConsumoMarket consumoMarket;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConsumoMarketResource consumoMarketResource = new ConsumoMarketResource(consumoMarketRepository);
        this.restConsumoMarketMockMvc = MockMvcBuilders.standaloneSetup(consumoMarketResource)
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
    public static ConsumoMarket createEntity(EntityManager em) {
        ConsumoMarket consumoMarket = new ConsumoMarket()
            .totalConsumido(DEFAULT_TOTAL_CONSUMIDO)
            .registroFechaInicial(DEFAULT_REGISTRO_FECHA_INICIAL)
            .registroFechaFinal(DEFAULT_REGISTRO_FECHA_FINAL)
            .impuesto(DEFAULT_IMPUESTO);
        return consumoMarket;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ConsumoMarket createUpdatedEntity(EntityManager em) {
        ConsumoMarket consumoMarket = new ConsumoMarket()
            .totalConsumido(UPDATED_TOTAL_CONSUMIDO)
            .registroFechaInicial(UPDATED_REGISTRO_FECHA_INICIAL)
            .registroFechaFinal(UPDATED_REGISTRO_FECHA_FINAL)
            .impuesto(UPDATED_IMPUESTO);
        return consumoMarket;
    }

    @BeforeEach
    public void initTest() {
        consumoMarket = createEntity(em);
    }

    @Test
    @Transactional
    public void createConsumoMarket() throws Exception {
        int databaseSizeBeforeCreate = consumoMarketRepository.findAll().size();

        // Create the ConsumoMarket
        restConsumoMarketMockMvc.perform(post("/api/consumo-markets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumoMarket)))
            .andExpect(status().isCreated());

        // Validate the ConsumoMarket in the database
        List<ConsumoMarket> consumoMarketList = consumoMarketRepository.findAll();
        assertThat(consumoMarketList).hasSize(databaseSizeBeforeCreate + 1);
        ConsumoMarket testConsumoMarket = consumoMarketList.get(consumoMarketList.size() - 1);
        assertThat(testConsumoMarket.getTotalConsumido()).isEqualTo(DEFAULT_TOTAL_CONSUMIDO);
        assertThat(testConsumoMarket.getRegistroFechaInicial()).isEqualTo(DEFAULT_REGISTRO_FECHA_INICIAL);
        assertThat(testConsumoMarket.getRegistroFechaFinal()).isEqualTo(DEFAULT_REGISTRO_FECHA_FINAL);
        assertThat(testConsumoMarket.getImpuesto()).isEqualTo(DEFAULT_IMPUESTO);
    }

    @Test
    @Transactional
    public void createConsumoMarketWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = consumoMarketRepository.findAll().size();

        // Create the ConsumoMarket with an existing ID
        consumoMarket.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConsumoMarketMockMvc.perform(post("/api/consumo-markets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumoMarket)))
            .andExpect(status().isBadRequest());

        // Validate the ConsumoMarket in the database
        List<ConsumoMarket> consumoMarketList = consumoMarketRepository.findAll();
        assertThat(consumoMarketList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllConsumoMarkets() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        // Get all the consumoMarketList
        restConsumoMarketMockMvc.perform(get("/api/consumo-markets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(consumoMarket.getId().intValue())))
            .andExpect(jsonPath("$.[*].totalConsumido").value(hasItem(DEFAULT_TOTAL_CONSUMIDO)))
            .andExpect(jsonPath("$.[*].registroFechaInicial").value(hasItem(DEFAULT_REGISTRO_FECHA_INICIAL.toString())))
            .andExpect(jsonPath("$.[*].registroFechaFinal").value(hasItem(DEFAULT_REGISTRO_FECHA_FINAL.toString())))
            .andExpect(jsonPath("$.[*].impuesto").value(hasItem(DEFAULT_IMPUESTO.toString())));
    }
    
    @Test
    @Transactional
    public void getConsumoMarket() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        // Get the consumoMarket
        restConsumoMarketMockMvc.perform(get("/api/consumo-markets/{id}", consumoMarket.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(consumoMarket.getId().intValue()))
            .andExpect(jsonPath("$.totalConsumido").value(DEFAULT_TOTAL_CONSUMIDO))
            .andExpect(jsonPath("$.registroFechaInicial").value(DEFAULT_REGISTRO_FECHA_INICIAL.toString()))
            .andExpect(jsonPath("$.registroFechaFinal").value(DEFAULT_REGISTRO_FECHA_FINAL.toString()))
            .andExpect(jsonPath("$.impuesto").value(DEFAULT_IMPUESTO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingConsumoMarket() throws Exception {
        // Get the consumoMarket
        restConsumoMarketMockMvc.perform(get("/api/consumo-markets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConsumoMarket() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        int databaseSizeBeforeUpdate = consumoMarketRepository.findAll().size();

        // Update the consumoMarket
        ConsumoMarket updatedConsumoMarket = consumoMarketRepository.findById(consumoMarket.getId()).get();
        // Disconnect from session so that the updates on updatedConsumoMarket are not directly saved in db
        em.detach(updatedConsumoMarket);
        updatedConsumoMarket
            .totalConsumido(UPDATED_TOTAL_CONSUMIDO)
            .registroFechaInicial(UPDATED_REGISTRO_FECHA_INICIAL)
            .registroFechaFinal(UPDATED_REGISTRO_FECHA_FINAL)
            .impuesto(UPDATED_IMPUESTO);

        restConsumoMarketMockMvc.perform(put("/api/consumo-markets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedConsumoMarket)))
            .andExpect(status().isOk());

        // Validate the ConsumoMarket in the database
        List<ConsumoMarket> consumoMarketList = consumoMarketRepository.findAll();
        assertThat(consumoMarketList).hasSize(databaseSizeBeforeUpdate);
        ConsumoMarket testConsumoMarket = consumoMarketList.get(consumoMarketList.size() - 1);
        assertThat(testConsumoMarket.getTotalConsumido()).isEqualTo(UPDATED_TOTAL_CONSUMIDO);
        assertThat(testConsumoMarket.getRegistroFechaInicial()).isEqualTo(UPDATED_REGISTRO_FECHA_INICIAL);
        assertThat(testConsumoMarket.getRegistroFechaFinal()).isEqualTo(UPDATED_REGISTRO_FECHA_FINAL);
        assertThat(testConsumoMarket.getImpuesto()).isEqualTo(UPDATED_IMPUESTO);
    }

    @Test
    @Transactional
    public void updateNonExistingConsumoMarket() throws Exception {
        int databaseSizeBeforeUpdate = consumoMarketRepository.findAll().size();

        // Create the ConsumoMarket

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConsumoMarketMockMvc.perform(put("/api/consumo-markets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumoMarket)))
            .andExpect(status().isBadRequest());

        // Validate the ConsumoMarket in the database
        List<ConsumoMarket> consumoMarketList = consumoMarketRepository.findAll();
        assertThat(consumoMarketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConsumoMarket() throws Exception {
        // Initialize the database
        consumoMarketRepository.saveAndFlush(consumoMarket);

        int databaseSizeBeforeDelete = consumoMarketRepository.findAll().size();

        // Delete the consumoMarket
        restConsumoMarketMockMvc.perform(delete("/api/consumo-markets/{id}", consumoMarket.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ConsumoMarket> consumoMarketList = consumoMarketRepository.findAll();
        assertThat(consumoMarketList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConsumoMarket.class);
        ConsumoMarket consumoMarket1 = new ConsumoMarket();
        consumoMarket1.setId(1L);
        ConsumoMarket consumoMarket2 = new ConsumoMarket();
        consumoMarket2.setId(consumoMarket1.getId());
        assertThat(consumoMarket1).isEqualTo(consumoMarket2);
        consumoMarket2.setId(2L);
        assertThat(consumoMarket1).isNotEqualTo(consumoMarket2);
        consumoMarket1.setId(null);
        assertThat(consumoMarket1).isNotEqualTo(consumoMarket2);
    }
}
