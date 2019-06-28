package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp8App;
import io.github.jhipster.application.domain.Blog;
import io.github.jhipster.application.repository.BlogRepository;
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

import io.github.jhipster.application.domain.enumeration.Categoriad;
import io.github.jhipster.application.domain.enumeration.TipoConsumod;
import io.github.jhipster.application.domain.enumeration.Impuestod;
import io.github.jhipster.application.domain.enumeration.EstadoPublicaciond;
/**
 * Integration tests for the {@Link BlogResource} REST controller.
 */
@SpringBootTest(classes = NewoApp8App.class)
public class BlogResourceIT {

    private static final String DEFAULT_TITULO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final Categoriad DEFAULT_TIPO_CONTENIDO = Categoriad.GENERAL;
    private static final Categoriad UPDATED_TIPO_CONTENIDO = Categoriad.DE_GRUPO;

    private static final String DEFAULT_CONTENIDOS = "AAAAAAAAAA";
    private static final String UPDATED_CONTENIDOS = "BBBBBBBBBB";

    private static final byte[] DEFAULT_AUDIO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_AUDIO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_AUDIO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_AUDIO_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_VIDEO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_VIDEO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_VIDEO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_VIDEO_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_FOTO_1 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO_1 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_1_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_1_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_FOTO_2 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO_2 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_2_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_2_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BANNER = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BANNER = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BANNER_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BANNER_CONTENT_TYPE = "image/png";

    private static final TipoConsumod DEFAULT_TIPO_CONSUMO = TipoConsumod.GRATIS;
    private static final TipoConsumod UPDATED_TIPO_CONSUMO = TipoConsumod.PAGO;

    private static final Float DEFAULT_VALOR = 1F;
    private static final Float UPDATED_VALOR = 2F;

    private static final Impuestod DEFAULT_IMPUESTO = Impuestod.IVA19;
    private static final Impuestod UPDATED_IMPUESTO = Impuestod.IVA6;

    private static final EstadoPublicaciond DEFAULT_ESTADO_PUBLICACION = EstadoPublicaciond.BORRADOR;
    private static final EstadoPublicaciond UPDATED_ESTADO_PUBLICACION = EstadoPublicaciond.EN_REVISION;

    @Autowired
    private BlogRepository blogRepository;

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

    private MockMvc restBlogMockMvc;

    private Blog blog;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BlogResource blogResource = new BlogResource(blogRepository);
        this.restBlogMockMvc = MockMvcBuilders.standaloneSetup(blogResource)
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
    public static Blog createEntity(EntityManager em) {
        Blog blog = new Blog()
            .titulo(DEFAULT_TITULO)
            .descripcion(DEFAULT_DESCRIPCION)
            .tipoContenido(DEFAULT_TIPO_CONTENIDO)
            .contenidos(DEFAULT_CONTENIDOS)
            .audio(DEFAULT_AUDIO)
            .audioContentType(DEFAULT_AUDIO_CONTENT_TYPE)
            .video(DEFAULT_VIDEO)
            .videoContentType(DEFAULT_VIDEO_CONTENT_TYPE)
            .foto1(DEFAULT_FOTO_1)
            .foto1ContentType(DEFAULT_FOTO_1_CONTENT_TYPE)
            .foto2(DEFAULT_FOTO_2)
            .foto2ContentType(DEFAULT_FOTO_2_CONTENT_TYPE)
            .banner(DEFAULT_BANNER)
            .bannerContentType(DEFAULT_BANNER_CONTENT_TYPE)
            .tipoConsumo(DEFAULT_TIPO_CONSUMO)
            .valor(DEFAULT_VALOR)
            .impuesto(DEFAULT_IMPUESTO)
            .estadoPublicacion(DEFAULT_ESTADO_PUBLICACION);
        return blog;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Blog createUpdatedEntity(EntityManager em) {
        Blog blog = new Blog()
            .titulo(UPDATED_TITULO)
            .descripcion(UPDATED_DESCRIPCION)
            .tipoContenido(UPDATED_TIPO_CONTENIDO)
            .contenidos(UPDATED_CONTENIDOS)
            .audio(UPDATED_AUDIO)
            .audioContentType(UPDATED_AUDIO_CONTENT_TYPE)
            .video(UPDATED_VIDEO)
            .videoContentType(UPDATED_VIDEO_CONTENT_TYPE)
            .foto1(UPDATED_FOTO_1)
            .foto1ContentType(UPDATED_FOTO_1_CONTENT_TYPE)
            .foto2(UPDATED_FOTO_2)
            .foto2ContentType(UPDATED_FOTO_2_CONTENT_TYPE)
            .banner(UPDATED_BANNER)
            .bannerContentType(UPDATED_BANNER_CONTENT_TYPE)
            .tipoConsumo(UPDATED_TIPO_CONSUMO)
            .valor(UPDATED_VALOR)
            .impuesto(UPDATED_IMPUESTO)
            .estadoPublicacion(UPDATED_ESTADO_PUBLICACION);
        return blog;
    }

    @BeforeEach
    public void initTest() {
        blog = createEntity(em);
    }

    @Test
    @Transactional
    public void createBlog() throws Exception {
        int databaseSizeBeforeCreate = blogRepository.findAll().size();

        // Create the Blog
        restBlogMockMvc.perform(post("/api/blogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blog)))
            .andExpect(status().isCreated());

        // Validate the Blog in the database
        List<Blog> blogList = blogRepository.findAll();
        assertThat(blogList).hasSize(databaseSizeBeforeCreate + 1);
        Blog testBlog = blogList.get(blogList.size() - 1);
        assertThat(testBlog.getTitulo()).isEqualTo(DEFAULT_TITULO);
        assertThat(testBlog.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testBlog.getTipoContenido()).isEqualTo(DEFAULT_TIPO_CONTENIDO);
        assertThat(testBlog.getContenidos()).isEqualTo(DEFAULT_CONTENIDOS);
        assertThat(testBlog.getAudio()).isEqualTo(DEFAULT_AUDIO);
        assertThat(testBlog.getAudioContentType()).isEqualTo(DEFAULT_AUDIO_CONTENT_TYPE);
        assertThat(testBlog.getVideo()).isEqualTo(DEFAULT_VIDEO);
        assertThat(testBlog.getVideoContentType()).isEqualTo(DEFAULT_VIDEO_CONTENT_TYPE);
        assertThat(testBlog.getFoto1()).isEqualTo(DEFAULT_FOTO_1);
        assertThat(testBlog.getFoto1ContentType()).isEqualTo(DEFAULT_FOTO_1_CONTENT_TYPE);
        assertThat(testBlog.getFoto2()).isEqualTo(DEFAULT_FOTO_2);
        assertThat(testBlog.getFoto2ContentType()).isEqualTo(DEFAULT_FOTO_2_CONTENT_TYPE);
        assertThat(testBlog.getBanner()).isEqualTo(DEFAULT_BANNER);
        assertThat(testBlog.getBannerContentType()).isEqualTo(DEFAULT_BANNER_CONTENT_TYPE);
        assertThat(testBlog.getTipoConsumo()).isEqualTo(DEFAULT_TIPO_CONSUMO);
        assertThat(testBlog.getValor()).isEqualTo(DEFAULT_VALOR);
        assertThat(testBlog.getImpuesto()).isEqualTo(DEFAULT_IMPUESTO);
        assertThat(testBlog.getEstadoPublicacion()).isEqualTo(DEFAULT_ESTADO_PUBLICACION);
    }

    @Test
    @Transactional
    public void createBlogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = blogRepository.findAll().size();

        // Create the Blog with an existing ID
        blog.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBlogMockMvc.perform(post("/api/blogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blog)))
            .andExpect(status().isBadRequest());

        // Validate the Blog in the database
        List<Blog> blogList = blogRepository.findAll();
        assertThat(blogList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBlogs() throws Exception {
        // Initialize the database
        blogRepository.saveAndFlush(blog);

        // Get all the blogList
        restBlogMockMvc.perform(get("/api/blogs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blog.getId().intValue())))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].tipoContenido").value(hasItem(DEFAULT_TIPO_CONTENIDO.toString())))
            .andExpect(jsonPath("$.[*].contenidos").value(hasItem(DEFAULT_CONTENIDOS.toString())))
            .andExpect(jsonPath("$.[*].audioContentType").value(hasItem(DEFAULT_AUDIO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].audio").value(hasItem(Base64Utils.encodeToString(DEFAULT_AUDIO))))
            .andExpect(jsonPath("$.[*].videoContentType").value(hasItem(DEFAULT_VIDEO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].video").value(hasItem(Base64Utils.encodeToString(DEFAULT_VIDEO))))
            .andExpect(jsonPath("$.[*].foto1ContentType").value(hasItem(DEFAULT_FOTO_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto1").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_1))))
            .andExpect(jsonPath("$.[*].foto2ContentType").value(hasItem(DEFAULT_FOTO_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto2").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_2))))
            .andExpect(jsonPath("$.[*].bannerContentType").value(hasItem(DEFAULT_BANNER_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].banner").value(hasItem(Base64Utils.encodeToString(DEFAULT_BANNER))))
            .andExpect(jsonPath("$.[*].tipoConsumo").value(hasItem(DEFAULT_TIPO_CONSUMO.toString())))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR.doubleValue())))
            .andExpect(jsonPath("$.[*].impuesto").value(hasItem(DEFAULT_IMPUESTO.toString())))
            .andExpect(jsonPath("$.[*].estadoPublicacion").value(hasItem(DEFAULT_ESTADO_PUBLICACION.toString())));
    }
    
    @Test
    @Transactional
    public void getBlog() throws Exception {
        // Initialize the database
        blogRepository.saveAndFlush(blog);

        // Get the blog
        restBlogMockMvc.perform(get("/api/blogs/{id}", blog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(blog.getId().intValue()))
            .andExpect(jsonPath("$.titulo").value(DEFAULT_TITULO.toString()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.tipoContenido").value(DEFAULT_TIPO_CONTENIDO.toString()))
            .andExpect(jsonPath("$.contenidos").value(DEFAULT_CONTENIDOS.toString()))
            .andExpect(jsonPath("$.audioContentType").value(DEFAULT_AUDIO_CONTENT_TYPE))
            .andExpect(jsonPath("$.audio").value(Base64Utils.encodeToString(DEFAULT_AUDIO)))
            .andExpect(jsonPath("$.videoContentType").value(DEFAULT_VIDEO_CONTENT_TYPE))
            .andExpect(jsonPath("$.video").value(Base64Utils.encodeToString(DEFAULT_VIDEO)))
            .andExpect(jsonPath("$.foto1ContentType").value(DEFAULT_FOTO_1_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto1").value(Base64Utils.encodeToString(DEFAULT_FOTO_1)))
            .andExpect(jsonPath("$.foto2ContentType").value(DEFAULT_FOTO_2_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto2").value(Base64Utils.encodeToString(DEFAULT_FOTO_2)))
            .andExpect(jsonPath("$.bannerContentType").value(DEFAULT_BANNER_CONTENT_TYPE))
            .andExpect(jsonPath("$.banner").value(Base64Utils.encodeToString(DEFAULT_BANNER)))
            .andExpect(jsonPath("$.tipoConsumo").value(DEFAULT_TIPO_CONSUMO.toString()))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR.doubleValue()))
            .andExpect(jsonPath("$.impuesto").value(DEFAULT_IMPUESTO.toString()))
            .andExpect(jsonPath("$.estadoPublicacion").value(DEFAULT_ESTADO_PUBLICACION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBlog() throws Exception {
        // Get the blog
        restBlogMockMvc.perform(get("/api/blogs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBlog() throws Exception {
        // Initialize the database
        blogRepository.saveAndFlush(blog);

        int databaseSizeBeforeUpdate = blogRepository.findAll().size();

        // Update the blog
        Blog updatedBlog = blogRepository.findById(blog.getId()).get();
        // Disconnect from session so that the updates on updatedBlog are not directly saved in db
        em.detach(updatedBlog);
        updatedBlog
            .titulo(UPDATED_TITULO)
            .descripcion(UPDATED_DESCRIPCION)
            .tipoContenido(UPDATED_TIPO_CONTENIDO)
            .contenidos(UPDATED_CONTENIDOS)
            .audio(UPDATED_AUDIO)
            .audioContentType(UPDATED_AUDIO_CONTENT_TYPE)
            .video(UPDATED_VIDEO)
            .videoContentType(UPDATED_VIDEO_CONTENT_TYPE)
            .foto1(UPDATED_FOTO_1)
            .foto1ContentType(UPDATED_FOTO_1_CONTENT_TYPE)
            .foto2(UPDATED_FOTO_2)
            .foto2ContentType(UPDATED_FOTO_2_CONTENT_TYPE)
            .banner(UPDATED_BANNER)
            .bannerContentType(UPDATED_BANNER_CONTENT_TYPE)
            .tipoConsumo(UPDATED_TIPO_CONSUMO)
            .valor(UPDATED_VALOR)
            .impuesto(UPDATED_IMPUESTO)
            .estadoPublicacion(UPDATED_ESTADO_PUBLICACION);

        restBlogMockMvc.perform(put("/api/blogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBlog)))
            .andExpect(status().isOk());

        // Validate the Blog in the database
        List<Blog> blogList = blogRepository.findAll();
        assertThat(blogList).hasSize(databaseSizeBeforeUpdate);
        Blog testBlog = blogList.get(blogList.size() - 1);
        assertThat(testBlog.getTitulo()).isEqualTo(UPDATED_TITULO);
        assertThat(testBlog.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testBlog.getTipoContenido()).isEqualTo(UPDATED_TIPO_CONTENIDO);
        assertThat(testBlog.getContenidos()).isEqualTo(UPDATED_CONTENIDOS);
        assertThat(testBlog.getAudio()).isEqualTo(UPDATED_AUDIO);
        assertThat(testBlog.getAudioContentType()).isEqualTo(UPDATED_AUDIO_CONTENT_TYPE);
        assertThat(testBlog.getVideo()).isEqualTo(UPDATED_VIDEO);
        assertThat(testBlog.getVideoContentType()).isEqualTo(UPDATED_VIDEO_CONTENT_TYPE);
        assertThat(testBlog.getFoto1()).isEqualTo(UPDATED_FOTO_1);
        assertThat(testBlog.getFoto1ContentType()).isEqualTo(UPDATED_FOTO_1_CONTENT_TYPE);
        assertThat(testBlog.getFoto2()).isEqualTo(UPDATED_FOTO_2);
        assertThat(testBlog.getFoto2ContentType()).isEqualTo(UPDATED_FOTO_2_CONTENT_TYPE);
        assertThat(testBlog.getBanner()).isEqualTo(UPDATED_BANNER);
        assertThat(testBlog.getBannerContentType()).isEqualTo(UPDATED_BANNER_CONTENT_TYPE);
        assertThat(testBlog.getTipoConsumo()).isEqualTo(UPDATED_TIPO_CONSUMO);
        assertThat(testBlog.getValor()).isEqualTo(UPDATED_VALOR);
        assertThat(testBlog.getImpuesto()).isEqualTo(UPDATED_IMPUESTO);
        assertThat(testBlog.getEstadoPublicacion()).isEqualTo(UPDATED_ESTADO_PUBLICACION);
    }

    @Test
    @Transactional
    public void updateNonExistingBlog() throws Exception {
        int databaseSizeBeforeUpdate = blogRepository.findAll().size();

        // Create the Blog

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBlogMockMvc.perform(put("/api/blogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blog)))
            .andExpect(status().isBadRequest());

        // Validate the Blog in the database
        List<Blog> blogList = blogRepository.findAll();
        assertThat(blogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBlog() throws Exception {
        // Initialize the database
        blogRepository.saveAndFlush(blog);

        int databaseSizeBeforeDelete = blogRepository.findAll().size();

        // Delete the blog
        restBlogMockMvc.perform(delete("/api/blogs/{id}", blog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Blog> blogList = blogRepository.findAll();
        assertThat(blogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Blog.class);
        Blog blog1 = new Blog();
        blog1.setId(1L);
        Blog blog2 = new Blog();
        blog2.setId(blog1.getId());
        assertThat(blog1).isEqualTo(blog2);
        blog2.setId(2L);
        assertThat(blog1).isNotEqualTo(blog2);
        blog1.setId(null);
        assertThat(blog1).isNotEqualTo(blog2);
    }
}
