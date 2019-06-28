package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.io.Serializable;

import io.github.jhipster.application.domain.enumeration.Categoriad;

import io.github.jhipster.application.domain.enumeration.TipoConsumod;

import io.github.jhipster.application.domain.enumeration.Impuestod;

import io.github.jhipster.application.domain.enumeration.EstadoPublicaciond;

/**
 * A Blog.
 */
@Entity
@Table(name = "blog")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_contenido")
    private Categoriad tipoContenido;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "contenidos")
    private String contenidos;

    @Lob
    @Column(name = "audio")
    private byte[] audio;

    @Column(name = "audio_content_type")
    private String audioContentType;

    @Lob
    @Column(name = "video")
    private byte[] video;

    @Column(name = "video_content_type")
    private String videoContentType;

    @Lob
    @Column(name = "foto_1")
    private byte[] foto1;

    @Column(name = "foto_1_content_type")
    private String foto1ContentType;

    @Lob
    @Column(name = "foto_2")
    private byte[] foto2;

    @Column(name = "foto_2_content_type")
    private String foto2ContentType;

    @Lob
    @Column(name = "banner")
    private byte[] banner;

    @Column(name = "banner_content_type")
    private String bannerContentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_consumo")
    private TipoConsumod tipoConsumo;

    @Column(name = "valor")
    private Float valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "impuesto")
    private Impuestod impuesto;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_publicacion")
    private EstadoPublicaciond estadoPublicacion;

    @ManyToOne
    @JsonIgnoreProperties("blogs")
    private CategoriaContenidos categoriaBlog;

    @ManyToOne
    @JsonIgnoreProperties("blogs")
    private Grupos grupos;

    @ManyToOne
    @JsonIgnoreProperties("blogs")
    private Miembros miembro;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Blog titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Blog descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoriad getTipoContenido() {
        return tipoContenido;
    }

    public Blog tipoContenido(Categoriad tipoContenido) {
        this.tipoContenido = tipoContenido;
        return this;
    }

    public void setTipoContenido(Categoriad tipoContenido) {
        this.tipoContenido = tipoContenido;
    }

    public String getContenidos() {
        return contenidos;
    }

    public Blog contenidos(String contenidos) {
        this.contenidos = contenidos;
        return this;
    }

    public void setContenidos(String contenidos) {
        this.contenidos = contenidos;
    }

    public byte[] getAudio() {
        return audio;
    }

    public Blog audio(byte[] audio) {
        this.audio = audio;
        return this;
    }

    public void setAudio(byte[] audio) {
        this.audio = audio;
    }

    public String getAudioContentType() {
        return audioContentType;
    }

    public Blog audioContentType(String audioContentType) {
        this.audioContentType = audioContentType;
        return this;
    }

    public void setAudioContentType(String audioContentType) {
        this.audioContentType = audioContentType;
    }

    public byte[] getVideo() {
        return video;
    }

    public Blog video(byte[] video) {
        this.video = video;
        return this;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    public String getVideoContentType() {
        return videoContentType;
    }

    public Blog videoContentType(String videoContentType) {
        this.videoContentType = videoContentType;
        return this;
    }

    public void setVideoContentType(String videoContentType) {
        this.videoContentType = videoContentType;
    }

    public byte[] getFoto1() {
        return foto1;
    }

    public Blog foto1(byte[] foto1) {
        this.foto1 = foto1;
        return this;
    }

    public void setFoto1(byte[] foto1) {
        this.foto1 = foto1;
    }

    public String getFoto1ContentType() {
        return foto1ContentType;
    }

    public Blog foto1ContentType(String foto1ContentType) {
        this.foto1ContentType = foto1ContentType;
        return this;
    }

    public void setFoto1ContentType(String foto1ContentType) {
        this.foto1ContentType = foto1ContentType;
    }

    public byte[] getFoto2() {
        return foto2;
    }

    public Blog foto2(byte[] foto2) {
        this.foto2 = foto2;
        return this;
    }

    public void setFoto2(byte[] foto2) {
        this.foto2 = foto2;
    }

    public String getFoto2ContentType() {
        return foto2ContentType;
    }

    public Blog foto2ContentType(String foto2ContentType) {
        this.foto2ContentType = foto2ContentType;
        return this;
    }

    public void setFoto2ContentType(String foto2ContentType) {
        this.foto2ContentType = foto2ContentType;
    }

    public byte[] getBanner() {
        return banner;
    }

    public Blog banner(byte[] banner) {
        this.banner = banner;
        return this;
    }

    public void setBanner(byte[] banner) {
        this.banner = banner;
    }

    public String getBannerContentType() {
        return bannerContentType;
    }

    public Blog bannerContentType(String bannerContentType) {
        this.bannerContentType = bannerContentType;
        return this;
    }

    public void setBannerContentType(String bannerContentType) {
        this.bannerContentType = bannerContentType;
    }

    public TipoConsumod getTipoConsumo() {
        return tipoConsumo;
    }

    public Blog tipoConsumo(TipoConsumod tipoConsumo) {
        this.tipoConsumo = tipoConsumo;
        return this;
    }

    public void setTipoConsumo(TipoConsumod tipoConsumo) {
        this.tipoConsumo = tipoConsumo;
    }

    public Float getValor() {
        return valor;
    }

    public Blog valor(Float valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Impuestod getImpuesto() {
        return impuesto;
    }

    public Blog impuesto(Impuestod impuesto) {
        this.impuesto = impuesto;
        return this;
    }

    public void setImpuesto(Impuestod impuesto) {
        this.impuesto = impuesto;
    }

    public EstadoPublicaciond getEstadoPublicacion() {
        return estadoPublicacion;
    }

    public Blog estadoPublicacion(EstadoPublicaciond estadoPublicacion) {
        this.estadoPublicacion = estadoPublicacion;
        return this;
    }

    public void setEstadoPublicacion(EstadoPublicaciond estadoPublicacion) {
        this.estadoPublicacion = estadoPublicacion;
    }

    public CategoriaContenidos getCategoriaBlog() {
        return categoriaBlog;
    }

    public Blog categoriaBlog(CategoriaContenidos categoriaContenidos) {
        this.categoriaBlog = categoriaContenidos;
        return this;
    }

    public void setCategoriaBlog(CategoriaContenidos categoriaContenidos) {
        this.categoriaBlog = categoriaContenidos;
    }

    public Grupos getGrupos() {
        return grupos;
    }

    public Blog grupos(Grupos grupos) {
        this.grupos = grupos;
        return this;
    }

    public void setGrupos(Grupos grupos) {
        this.grupos = grupos;
    }

    public Miembros getMiembro() {
        return miembro;
    }

    public Blog miembro(Miembros miembros) {
        this.miembro = miembros;
        return this;
    }

    public void setMiembro(Miembros miembros) {
        this.miembro = miembros;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Blog)) {
            return false;
        }
        return id != null && id.equals(((Blog) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Blog{" +
            "id=" + getId() +
            ", titulo='" + getTitulo() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", tipoContenido='" + getTipoContenido() + "'" +
            ", contenidos='" + getContenidos() + "'" +
            ", audio='" + getAudio() + "'" +
            ", audioContentType='" + getAudioContentType() + "'" +
            ", video='" + getVideo() + "'" +
            ", videoContentType='" + getVideoContentType() + "'" +
            ", foto1='" + getFoto1() + "'" +
            ", foto1ContentType='" + getFoto1ContentType() + "'" +
            ", foto2='" + getFoto2() + "'" +
            ", foto2ContentType='" + getFoto2ContentType() + "'" +
            ", banner='" + getBanner() + "'" +
            ", bannerContentType='" + getBannerContentType() + "'" +
            ", tipoConsumo='" + getTipoConsumo() + "'" +
            ", valor=" + getValor() +
            ", impuesto='" + getImpuesto() + "'" +
            ", estadoPublicacion='" + getEstadoPublicacion() + "'" +
            "}";
    }
}
