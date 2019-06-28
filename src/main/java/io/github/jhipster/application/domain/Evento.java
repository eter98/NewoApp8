package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.io.Serializable;

import io.github.jhipster.application.domain.enumeration.TipoConsumod;

import io.github.jhipster.application.domain.enumeration.Impuestod;

import io.github.jhipster.application.domain.enumeration.Categoriad;

/**
 * A Evento.
 */
@Entity
@Table(name = "evento")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nombre_evento")
    private String nombreEvento;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "descripcion")
    private String descripcion;

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
    @Column(name = "tipo_evento")
    private Categoriad tipoEvento;

    @ManyToOne
    @JsonIgnoreProperties("eventos")
    private CategoriaContenidos categoriaEvento;

    @ManyToOne
    @JsonIgnoreProperties("eventos")
    private Grupos grupos;

    @ManyToOne
    @JsonIgnoreProperties("eventos")
    private Miembros miebro;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public Evento nombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
        return this;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Evento descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getAudio() {
        return audio;
    }

    public Evento audio(byte[] audio) {
        this.audio = audio;
        return this;
    }

    public void setAudio(byte[] audio) {
        this.audio = audio;
    }

    public String getAudioContentType() {
        return audioContentType;
    }

    public Evento audioContentType(String audioContentType) {
        this.audioContentType = audioContentType;
        return this;
    }

    public void setAudioContentType(String audioContentType) {
        this.audioContentType = audioContentType;
    }

    public byte[] getVideo() {
        return video;
    }

    public Evento video(byte[] video) {
        this.video = video;
        return this;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    public String getVideoContentType() {
        return videoContentType;
    }

    public Evento videoContentType(String videoContentType) {
        this.videoContentType = videoContentType;
        return this;
    }

    public void setVideoContentType(String videoContentType) {
        this.videoContentType = videoContentType;
    }

    public byte[] getFoto1() {
        return foto1;
    }

    public Evento foto1(byte[] foto1) {
        this.foto1 = foto1;
        return this;
    }

    public void setFoto1(byte[] foto1) {
        this.foto1 = foto1;
    }

    public String getFoto1ContentType() {
        return foto1ContentType;
    }

    public Evento foto1ContentType(String foto1ContentType) {
        this.foto1ContentType = foto1ContentType;
        return this;
    }

    public void setFoto1ContentType(String foto1ContentType) {
        this.foto1ContentType = foto1ContentType;
    }

    public byte[] getFoto2() {
        return foto2;
    }

    public Evento foto2(byte[] foto2) {
        this.foto2 = foto2;
        return this;
    }

    public void setFoto2(byte[] foto2) {
        this.foto2 = foto2;
    }

    public String getFoto2ContentType() {
        return foto2ContentType;
    }

    public Evento foto2ContentType(String foto2ContentType) {
        this.foto2ContentType = foto2ContentType;
        return this;
    }

    public void setFoto2ContentType(String foto2ContentType) {
        this.foto2ContentType = foto2ContentType;
    }

    public byte[] getBanner() {
        return banner;
    }

    public Evento banner(byte[] banner) {
        this.banner = banner;
        return this;
    }

    public void setBanner(byte[] banner) {
        this.banner = banner;
    }

    public String getBannerContentType() {
        return bannerContentType;
    }

    public Evento bannerContentType(String bannerContentType) {
        this.bannerContentType = bannerContentType;
        return this;
    }

    public void setBannerContentType(String bannerContentType) {
        this.bannerContentType = bannerContentType;
    }

    public TipoConsumod getTipoConsumo() {
        return tipoConsumo;
    }

    public Evento tipoConsumo(TipoConsumod tipoConsumo) {
        this.tipoConsumo = tipoConsumo;
        return this;
    }

    public void setTipoConsumo(TipoConsumod tipoConsumo) {
        this.tipoConsumo = tipoConsumo;
    }

    public Float getValor() {
        return valor;
    }

    public Evento valor(Float valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Impuestod getImpuesto() {
        return impuesto;
    }

    public Evento impuesto(Impuestod impuesto) {
        this.impuesto = impuesto;
        return this;
    }

    public void setImpuesto(Impuestod impuesto) {
        this.impuesto = impuesto;
    }

    public Categoriad getTipoEvento() {
        return tipoEvento;
    }

    public Evento tipoEvento(Categoriad tipoEvento) {
        this.tipoEvento = tipoEvento;
        return this;
    }

    public void setTipoEvento(Categoriad tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public CategoriaContenidos getCategoriaEvento() {
        return categoriaEvento;
    }

    public Evento categoriaEvento(CategoriaContenidos categoriaContenidos) {
        this.categoriaEvento = categoriaContenidos;
        return this;
    }

    public void setCategoriaEvento(CategoriaContenidos categoriaContenidos) {
        this.categoriaEvento = categoriaContenidos;
    }

    public Grupos getGrupos() {
        return grupos;
    }

    public Evento grupos(Grupos grupos) {
        this.grupos = grupos;
        return this;
    }

    public void setGrupos(Grupos grupos) {
        this.grupos = grupos;
    }

    public Miembros getMiebro() {
        return miebro;
    }

    public Evento miebro(Miembros miembros) {
        this.miebro = miembros;
        return this;
    }

    public void setMiebro(Miembros miembros) {
        this.miebro = miembros;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Evento)) {
            return false;
        }
        return id != null && id.equals(((Evento) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Evento{" +
            "id=" + getId() +
            ", nombreEvento='" + getNombreEvento() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
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
            ", tipoEvento='" + getTipoEvento() + "'" +
            "}";
    }
}
