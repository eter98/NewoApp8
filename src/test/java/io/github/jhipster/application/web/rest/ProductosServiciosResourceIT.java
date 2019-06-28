package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.ProductosServicios;
import io.github.jhipster.application.repository.ProductosServiciosRepository;
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

import io.github.jhipster.application.domain.enumeration.Impuestod;
/**
 * Integration tests for the {@Link ProductosServiciosResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class ProductosServiciosResourceIT {

    private static final String DEFAULT_NOMBRE_PRODUCTO = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_PRODUCTO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_INVENTARIABLES = false;
    private static final Boolean UPDATED_INVENTARIABLES = true;

    private static final Integer DEFAULT_VALOR = 1;
    private static final Integer UPDATED_VALOR = 2;

    private static final Impuestod DEFAULT_IMPUESTO = Impuestod.IVA19;
    private static final Impuestod UPDATED_IMPUESTO = Impuestod.IVA6;

    private static final byte[] DEFAULT_VIDEO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_VIDEO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_VIDEO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_VIDEO_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGEN_1 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN_1 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_1_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_1_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGEN_2 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN_2 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_2_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_2_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGEN_3 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN_3 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_3_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_3_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGEN_4 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN_4 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_4_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_4_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGEN_5 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN_5 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_5_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_5_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGEN_6 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN_6 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_6_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_6_CONTENT_TYPE = "image/png";

    @Autowired
    private ProductosServiciosRepository productosServiciosRepository;

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

    private MockMvc restProductosServiciosMockMvc;

    private ProductosServicios productosServicios;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductosServiciosResource productosServiciosResource = new ProductosServiciosResource(productosServiciosRepository);
        this.restProductosServiciosMockMvc = MockMvcBuilders.standaloneSetup(productosServiciosResource)
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
    public static ProductosServicios createEntity(EntityManager em) {
        ProductosServicios productosServicios = new ProductosServicios()
            .nombreProducto(DEFAULT_NOMBRE_PRODUCTO)
            .foto(DEFAULT_FOTO)
            .fotoContentType(DEFAULT_FOTO_CONTENT_TYPE)
            .descripcion(DEFAULT_DESCRIPCION)
            .inventariables(DEFAULT_INVENTARIABLES)
            .valor(DEFAULT_VALOR)
            .impuesto(DEFAULT_IMPUESTO)
            .video(DEFAULT_VIDEO)
            .videoContentType(DEFAULT_VIDEO_CONTENT_TYPE)
            .imagen1(DEFAULT_IMAGEN_1)
            .imagen1ContentType(DEFAULT_IMAGEN_1_CONTENT_TYPE)
            .imagen2(DEFAULT_IMAGEN_2)
            .imagen2ContentType(DEFAULT_IMAGEN_2_CONTENT_TYPE)
            .imagen3(DEFAULT_IMAGEN_3)
            .imagen3ContentType(DEFAULT_IMAGEN_3_CONTENT_TYPE)
            .imagen4(DEFAULT_IMAGEN_4)
            .imagen4ContentType(DEFAULT_IMAGEN_4_CONTENT_TYPE)
            .imagen5(DEFAULT_IMAGEN_5)
            .imagen5ContentType(DEFAULT_IMAGEN_5_CONTENT_TYPE)
            .imagen6(DEFAULT_IMAGEN_6)
            .imagen6ContentType(DEFAULT_IMAGEN_6_CONTENT_TYPE);
        return productosServicios;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductosServicios createUpdatedEntity(EntityManager em) {
        ProductosServicios productosServicios = new ProductosServicios()
            .nombreProducto(UPDATED_NOMBRE_PRODUCTO)
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE)
            .descripcion(UPDATED_DESCRIPCION)
            .inventariables(UPDATED_INVENTARIABLES)
            .valor(UPDATED_VALOR)
            .impuesto(UPDATED_IMPUESTO)
            .video(UPDATED_VIDEO)
            .videoContentType(UPDATED_VIDEO_CONTENT_TYPE)
            .imagen1(UPDATED_IMAGEN_1)
            .imagen1ContentType(UPDATED_IMAGEN_1_CONTENT_TYPE)
            .imagen2(UPDATED_IMAGEN_2)
            .imagen2ContentType(UPDATED_IMAGEN_2_CONTENT_TYPE)
            .imagen3(UPDATED_IMAGEN_3)
            .imagen3ContentType(UPDATED_IMAGEN_3_CONTENT_TYPE)
            .imagen4(UPDATED_IMAGEN_4)
            .imagen4ContentType(UPDATED_IMAGEN_4_CONTENT_TYPE)
            .imagen5(UPDATED_IMAGEN_5)
            .imagen5ContentType(UPDATED_IMAGEN_5_CONTENT_TYPE)
            .imagen6(UPDATED_IMAGEN_6)
            .imagen6ContentType(UPDATED_IMAGEN_6_CONTENT_TYPE);
        return productosServicios;
    }

    @BeforeEach
    public void initTest() {
        productosServicios = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductosServicios() throws Exception {
        int databaseSizeBeforeCreate = productosServiciosRepository.findAll().size();

        // Create the ProductosServicios
        restProductosServiciosMockMvc.perform(post("/api/productos-servicios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productosServicios)))
            .andExpect(status().isCreated());

        // Validate the ProductosServicios in the database
        List<ProductosServicios> productosServiciosList = productosServiciosRepository.findAll();
        assertThat(productosServiciosList).hasSize(databaseSizeBeforeCreate + 1);
        ProductosServicios testProductosServicios = productosServiciosList.get(productosServiciosList.size() - 1);
        assertThat(testProductosServicios.getNombreProducto()).isEqualTo(DEFAULT_NOMBRE_PRODUCTO);
        assertThat(testProductosServicios.getFoto()).isEqualTo(DEFAULT_FOTO);
        assertThat(testProductosServicios.getFotoContentType()).isEqualTo(DEFAULT_FOTO_CONTENT_TYPE);
        assertThat(testProductosServicios.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testProductosServicios.isInventariables()).isEqualTo(DEFAULT_INVENTARIABLES);
        assertThat(testProductosServicios.getValor()).isEqualTo(DEFAULT_VALOR);
        assertThat(testProductosServicios.getImpuesto()).isEqualTo(DEFAULT_IMPUESTO);
        assertThat(testProductosServicios.getVideo()).isEqualTo(DEFAULT_VIDEO);
        assertThat(testProductosServicios.getVideoContentType()).isEqualTo(DEFAULT_VIDEO_CONTENT_TYPE);
        assertThat(testProductosServicios.getImagen1()).isEqualTo(DEFAULT_IMAGEN_1);
        assertThat(testProductosServicios.getImagen1ContentType()).isEqualTo(DEFAULT_IMAGEN_1_CONTENT_TYPE);
        assertThat(testProductosServicios.getImagen2()).isEqualTo(DEFAULT_IMAGEN_2);
        assertThat(testProductosServicios.getImagen2ContentType()).isEqualTo(DEFAULT_IMAGEN_2_CONTENT_TYPE);
        assertThat(testProductosServicios.getImagen3()).isEqualTo(DEFAULT_IMAGEN_3);
        assertThat(testProductosServicios.getImagen3ContentType()).isEqualTo(DEFAULT_IMAGEN_3_CONTENT_TYPE);
        assertThat(testProductosServicios.getImagen4()).isEqualTo(DEFAULT_IMAGEN_4);
        assertThat(testProductosServicios.getImagen4ContentType()).isEqualTo(DEFAULT_IMAGEN_4_CONTENT_TYPE);
        assertThat(testProductosServicios.getImagen5()).isEqualTo(DEFAULT_IMAGEN_5);
        assertThat(testProductosServicios.getImagen5ContentType()).isEqualTo(DEFAULT_IMAGEN_5_CONTENT_TYPE);
        assertThat(testProductosServicios.getImagen6()).isEqualTo(DEFAULT_IMAGEN_6);
        assertThat(testProductosServicios.getImagen6ContentType()).isEqualTo(DEFAULT_IMAGEN_6_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createProductosServiciosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productosServiciosRepository.findAll().size();

        // Create the ProductosServicios with an existing ID
        productosServicios.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductosServiciosMockMvc.perform(post("/api/productos-servicios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productosServicios)))
            .andExpect(status().isBadRequest());

        // Validate the ProductosServicios in the database
        List<ProductosServicios> productosServiciosList = productosServiciosRepository.findAll();
        assertThat(productosServiciosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProductosServicios() throws Exception {
        // Initialize the database
        productosServiciosRepository.saveAndFlush(productosServicios);

        // Get all the productosServiciosList
        restProductosServiciosMockMvc.perform(get("/api/productos-servicios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productosServicios.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreProducto").value(hasItem(DEFAULT_NOMBRE_PRODUCTO.toString())))
            .andExpect(jsonPath("$.[*].fotoContentType").value(hasItem(DEFAULT_FOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO))))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].inventariables").value(hasItem(DEFAULT_INVENTARIABLES.booleanValue())))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR)))
            .andExpect(jsonPath("$.[*].impuesto").value(hasItem(DEFAULT_IMPUESTO.toString())))
            .andExpect(jsonPath("$.[*].videoContentType").value(hasItem(DEFAULT_VIDEO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].video").value(hasItem(Base64Utils.encodeToString(DEFAULT_VIDEO))))
            .andExpect(jsonPath("$.[*].imagen1ContentType").value(hasItem(DEFAULT_IMAGEN_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen1").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_1))))
            .andExpect(jsonPath("$.[*].imagen2ContentType").value(hasItem(DEFAULT_IMAGEN_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen2").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_2))))
            .andExpect(jsonPath("$.[*].imagen3ContentType").value(hasItem(DEFAULT_IMAGEN_3_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen3").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_3))))
            .andExpect(jsonPath("$.[*].imagen4ContentType").value(hasItem(DEFAULT_IMAGEN_4_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen4").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_4))))
            .andExpect(jsonPath("$.[*].imagen5ContentType").value(hasItem(DEFAULT_IMAGEN_5_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen5").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_5))))
            .andExpect(jsonPath("$.[*].imagen6ContentType").value(hasItem(DEFAULT_IMAGEN_6_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen6").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_6))));
    }
    
    @Test
    @Transactional
    public void getProductosServicios() throws Exception {
        // Initialize the database
        productosServiciosRepository.saveAndFlush(productosServicios);

        // Get the productosServicios
        restProductosServiciosMockMvc.perform(get("/api/productos-servicios/{id}", productosServicios.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(productosServicios.getId().intValue()))
            .andExpect(jsonPath("$.nombreProducto").value(DEFAULT_NOMBRE_PRODUCTO.toString()))
            .andExpect(jsonPath("$.fotoContentType").value(DEFAULT_FOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto").value(Base64Utils.encodeToString(DEFAULT_FOTO)))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.inventariables").value(DEFAULT_INVENTARIABLES.booleanValue()))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR))
            .andExpect(jsonPath("$.impuesto").value(DEFAULT_IMPUESTO.toString()))
            .andExpect(jsonPath("$.videoContentType").value(DEFAULT_VIDEO_CONTENT_TYPE))
            .andExpect(jsonPath("$.video").value(Base64Utils.encodeToString(DEFAULT_VIDEO)))
            .andExpect(jsonPath("$.imagen1ContentType").value(DEFAULT_IMAGEN_1_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen1").value(Base64Utils.encodeToString(DEFAULT_IMAGEN_1)))
            .andExpect(jsonPath("$.imagen2ContentType").value(DEFAULT_IMAGEN_2_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen2").value(Base64Utils.encodeToString(DEFAULT_IMAGEN_2)))
            .andExpect(jsonPath("$.imagen3ContentType").value(DEFAULT_IMAGEN_3_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen3").value(Base64Utils.encodeToString(DEFAULT_IMAGEN_3)))
            .andExpect(jsonPath("$.imagen4ContentType").value(DEFAULT_IMAGEN_4_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen4").value(Base64Utils.encodeToString(DEFAULT_IMAGEN_4)))
            .andExpect(jsonPath("$.imagen5ContentType").value(DEFAULT_IMAGEN_5_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen5").value(Base64Utils.encodeToString(DEFAULT_IMAGEN_5)))
            .andExpect(jsonPath("$.imagen6ContentType").value(DEFAULT_IMAGEN_6_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen6").value(Base64Utils.encodeToString(DEFAULT_IMAGEN_6)));
    }

    @Test
    @Transactional
    public void getNonExistingProductosServicios() throws Exception {
        // Get the productosServicios
        restProductosServiciosMockMvc.perform(get("/api/productos-servicios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductosServicios() throws Exception {
        // Initialize the database
        productosServiciosRepository.saveAndFlush(productosServicios);

        int databaseSizeBeforeUpdate = productosServiciosRepository.findAll().size();

        // Update the productosServicios
        ProductosServicios updatedProductosServicios = productosServiciosRepository.findById(productosServicios.getId()).get();
        // Disconnect from session so that the updates on updatedProductosServicios are not directly saved in db
        em.detach(updatedProductosServicios);
        updatedProductosServicios
            .nombreProducto(UPDATED_NOMBRE_PRODUCTO)
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE)
            .descripcion(UPDATED_DESCRIPCION)
            .inventariables(UPDATED_INVENTARIABLES)
            .valor(UPDATED_VALOR)
            .impuesto(UPDATED_IMPUESTO)
            .video(UPDATED_VIDEO)
            .videoContentType(UPDATED_VIDEO_CONTENT_TYPE)
            .imagen1(UPDATED_IMAGEN_1)
            .imagen1ContentType(UPDATED_IMAGEN_1_CONTENT_TYPE)
            .imagen2(UPDATED_IMAGEN_2)
            .imagen2ContentType(UPDATED_IMAGEN_2_CONTENT_TYPE)
            .imagen3(UPDATED_IMAGEN_3)
            .imagen3ContentType(UPDATED_IMAGEN_3_CONTENT_TYPE)
            .imagen4(UPDATED_IMAGEN_4)
            .imagen4ContentType(UPDATED_IMAGEN_4_CONTENT_TYPE)
            .imagen5(UPDATED_IMAGEN_5)
            .imagen5ContentType(UPDATED_IMAGEN_5_CONTENT_TYPE)
            .imagen6(UPDATED_IMAGEN_6)
            .imagen6ContentType(UPDATED_IMAGEN_6_CONTENT_TYPE);

        restProductosServiciosMockMvc.perform(put("/api/productos-servicios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProductosServicios)))
            .andExpect(status().isOk());

        // Validate the ProductosServicios in the database
        List<ProductosServicios> productosServiciosList = productosServiciosRepository.findAll();
        assertThat(productosServiciosList).hasSize(databaseSizeBeforeUpdate);
        ProductosServicios testProductosServicios = productosServiciosList.get(productosServiciosList.size() - 1);
        assertThat(testProductosServicios.getNombreProducto()).isEqualTo(UPDATED_NOMBRE_PRODUCTO);
        assertThat(testProductosServicios.getFoto()).isEqualTo(UPDATED_FOTO);
        assertThat(testProductosServicios.getFotoContentType()).isEqualTo(UPDATED_FOTO_CONTENT_TYPE);
        assertThat(testProductosServicios.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testProductosServicios.isInventariables()).isEqualTo(UPDATED_INVENTARIABLES);
        assertThat(testProductosServicios.getValor()).isEqualTo(UPDATED_VALOR);
        assertThat(testProductosServicios.getImpuesto()).isEqualTo(UPDATED_IMPUESTO);
        assertThat(testProductosServicios.getVideo()).isEqualTo(UPDATED_VIDEO);
        assertThat(testProductosServicios.getVideoContentType()).isEqualTo(UPDATED_VIDEO_CONTENT_TYPE);
        assertThat(testProductosServicios.getImagen1()).isEqualTo(UPDATED_IMAGEN_1);
        assertThat(testProductosServicios.getImagen1ContentType()).isEqualTo(UPDATED_IMAGEN_1_CONTENT_TYPE);
        assertThat(testProductosServicios.getImagen2()).isEqualTo(UPDATED_IMAGEN_2);
        assertThat(testProductosServicios.getImagen2ContentType()).isEqualTo(UPDATED_IMAGEN_2_CONTENT_TYPE);
        assertThat(testProductosServicios.getImagen3()).isEqualTo(UPDATED_IMAGEN_3);
        assertThat(testProductosServicios.getImagen3ContentType()).isEqualTo(UPDATED_IMAGEN_3_CONTENT_TYPE);
        assertThat(testProductosServicios.getImagen4()).isEqualTo(UPDATED_IMAGEN_4);
        assertThat(testProductosServicios.getImagen4ContentType()).isEqualTo(UPDATED_IMAGEN_4_CONTENT_TYPE);
        assertThat(testProductosServicios.getImagen5()).isEqualTo(UPDATED_IMAGEN_5);
        assertThat(testProductosServicios.getImagen5ContentType()).isEqualTo(UPDATED_IMAGEN_5_CONTENT_TYPE);
        assertThat(testProductosServicios.getImagen6()).isEqualTo(UPDATED_IMAGEN_6);
        assertThat(testProductosServicios.getImagen6ContentType()).isEqualTo(UPDATED_IMAGEN_6_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingProductosServicios() throws Exception {
        int databaseSizeBeforeUpdate = productosServiciosRepository.findAll().size();

        // Create the ProductosServicios

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductosServiciosMockMvc.perform(put("/api/productos-servicios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productosServicios)))
            .andExpect(status().isBadRequest());

        // Validate the ProductosServicios in the database
        List<ProductosServicios> productosServiciosList = productosServiciosRepository.findAll();
        assertThat(productosServiciosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProductosServicios() throws Exception {
        // Initialize the database
        productosServiciosRepository.saveAndFlush(productosServicios);

        int databaseSizeBeforeDelete = productosServiciosRepository.findAll().size();

        // Delete the productosServicios
        restProductosServiciosMockMvc.perform(delete("/api/productos-servicios/{id}", productosServicios.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductosServicios> productosServiciosList = productosServiciosRepository.findAll();
        assertThat(productosServiciosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductosServicios.class);
        ProductosServicios productosServicios1 = new ProductosServicios();
        productosServicios1.setId(1L);
        ProductosServicios productosServicios2 = new ProductosServicios();
        productosServicios2.setId(productosServicios1.getId());
        assertThat(productosServicios1).isEqualTo(productosServicios2);
        productosServicios2.setId(2L);
        assertThat(productosServicios1).isNotEqualTo(productosServicios2);
        productosServicios1.setId(null);
        assertThat(productosServicios1).isNotEqualTo(productosServicios2);
    }
}
