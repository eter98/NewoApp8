package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

import io.github.jhipster.application.domain.enumeration.TipoRecursod;

import io.github.jhipster.application.domain.enumeration.Impuestod;

/**
 * A RecursosFisicos.
 */
@Entity
@Table(name = "recursos_fisicos")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RecursosFisicos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "recurso")
    private String recurso;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoRecursod tipo;

    @Column(name = "unidad_medida")
    private String unidadMedida;

    @Column(name = "valor_unitario")
    private Integer valorUnitario;

    @Column(name = "valor_1_h")
    private Integer valor1H;

    @Column(name = "valor_2_h")
    private Integer valor2H;

    @Column(name = "valor_3_h")
    private Integer valor3H;

    @Column(name = "valor_4_h")
    private Integer valor4H;

    @Column(name = "valor_5_h")
    private Integer valor5H;

    @Column(name = "valor_6_h")
    private Integer valor6H;

    @Column(name = "valor_7_h")
    private Integer valor7H;

    @Column(name = "valor_8_h")
    private Integer valor8H;

    @Column(name = "valor_9_h")
    private Integer valor9H;

    @Column(name = "valor_10_h")
    private Integer valor10H;

    @Column(name = "valor_11_h")
    private Integer valor11H;

    @Column(name = "valor_12_h")
    private Integer valor12H;

    @Enumerated(EnumType.STRING)
    @Column(name = "impuesto")
    private Impuestod impuesto;

    @Lob
    @Column(name = "foto")
    private byte[] foto;

    @Column(name = "foto_content_type")
    private String fotoContentType;

    @Lob
    @Column(name = "video")
    private byte[] video;

    @Column(name = "video_content_type")
    private String videoContentType;

    @ManyToOne
    @JsonIgnoreProperties("recursosFisicos")
    private Sedes sede;

    @ManyToOne
    @JsonIgnoreProperties("recursosFisicos")
    private TipoRecurso tipoRecurso;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecurso() {
        return recurso;
    }

    public RecursosFisicos recurso(String recurso) {
        this.recurso = recurso;
        return this;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public TipoRecursod getTipo() {
        return tipo;
    }

    public RecursosFisicos tipo(TipoRecursod tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(TipoRecursod tipo) {
        this.tipo = tipo;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public RecursosFisicos unidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
        return this;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Integer getValorUnitario() {
        return valorUnitario;
    }

    public RecursosFisicos valorUnitario(Integer valorUnitario) {
        this.valorUnitario = valorUnitario;
        return this;
    }

    public void setValorUnitario(Integer valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getValor1H() {
        return valor1H;
    }

    public RecursosFisicos valor1H(Integer valor1H) {
        this.valor1H = valor1H;
        return this;
    }

    public void setValor1H(Integer valor1H) {
        this.valor1H = valor1H;
    }

    public Integer getValor2H() {
        return valor2H;
    }

    public RecursosFisicos valor2H(Integer valor2H) {
        this.valor2H = valor2H;
        return this;
    }

    public void setValor2H(Integer valor2H) {
        this.valor2H = valor2H;
    }

    public Integer getValor3H() {
        return valor3H;
    }

    public RecursosFisicos valor3H(Integer valor3H) {
        this.valor3H = valor3H;
        return this;
    }

    public void setValor3H(Integer valor3H) {
        this.valor3H = valor3H;
    }

    public Integer getValor4H() {
        return valor4H;
    }

    public RecursosFisicos valor4H(Integer valor4H) {
        this.valor4H = valor4H;
        return this;
    }

    public void setValor4H(Integer valor4H) {
        this.valor4H = valor4H;
    }

    public Integer getValor5H() {
        return valor5H;
    }

    public RecursosFisicos valor5H(Integer valor5H) {
        this.valor5H = valor5H;
        return this;
    }

    public void setValor5H(Integer valor5H) {
        this.valor5H = valor5H;
    }

    public Integer getValor6H() {
        return valor6H;
    }

    public RecursosFisicos valor6H(Integer valor6H) {
        this.valor6H = valor6H;
        return this;
    }

    public void setValor6H(Integer valor6H) {
        this.valor6H = valor6H;
    }

    public Integer getValor7H() {
        return valor7H;
    }

    public RecursosFisicos valor7H(Integer valor7H) {
        this.valor7H = valor7H;
        return this;
    }

    public void setValor7H(Integer valor7H) {
        this.valor7H = valor7H;
    }

    public Integer getValor8H() {
        return valor8H;
    }

    public RecursosFisicos valor8H(Integer valor8H) {
        this.valor8H = valor8H;
        return this;
    }

    public void setValor8H(Integer valor8H) {
        this.valor8H = valor8H;
    }

    public Integer getValor9H() {
        return valor9H;
    }

    public RecursosFisicos valor9H(Integer valor9H) {
        this.valor9H = valor9H;
        return this;
    }

    public void setValor9H(Integer valor9H) {
        this.valor9H = valor9H;
    }

    public Integer getValor10H() {
        return valor10H;
    }

    public RecursosFisicos valor10H(Integer valor10H) {
        this.valor10H = valor10H;
        return this;
    }

    public void setValor10H(Integer valor10H) {
        this.valor10H = valor10H;
    }

    public Integer getValor11H() {
        return valor11H;
    }

    public RecursosFisicos valor11H(Integer valor11H) {
        this.valor11H = valor11H;
        return this;
    }

    public void setValor11H(Integer valor11H) {
        this.valor11H = valor11H;
    }

    public Integer getValor12H() {
        return valor12H;
    }

    public RecursosFisicos valor12H(Integer valor12H) {
        this.valor12H = valor12H;
        return this;
    }

    public void setValor12H(Integer valor12H) {
        this.valor12H = valor12H;
    }

    public Impuestod getImpuesto() {
        return impuesto;
    }

    public RecursosFisicos impuesto(Impuestod impuesto) {
        this.impuesto = impuesto;
        return this;
    }

    public void setImpuesto(Impuestod impuesto) {
        this.impuesto = impuesto;
    }

    public byte[] getFoto() {
        return foto;
    }

    public RecursosFisicos foto(byte[] foto) {
        this.foto = foto;
        return this;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getFotoContentType() {
        return fotoContentType;
    }

    public RecursosFisicos fotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
        return this;
    }

    public void setFotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
    }

    public byte[] getVideo() {
        return video;
    }

    public RecursosFisicos video(byte[] video) {
        this.video = video;
        return this;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    public String getVideoContentType() {
        return videoContentType;
    }

    public RecursosFisicos videoContentType(String videoContentType) {
        this.videoContentType = videoContentType;
        return this;
    }

    public void setVideoContentType(String videoContentType) {
        this.videoContentType = videoContentType;
    }

    public Sedes getSede() {
        return sede;
    }

    public RecursosFisicos sede(Sedes sedes) {
        this.sede = sedes;
        return this;
    }

    public void setSede(Sedes sedes) {
        this.sede = sedes;
    }

    public TipoRecurso getTipoRecurso() {
        return tipoRecurso;
    }

    public RecursosFisicos tipoRecurso(TipoRecurso tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
        return this;
    }

    public void setTipoRecurso(TipoRecurso tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecursosFisicos)) {
            return false;
        }
        return id != null && id.equals(((RecursosFisicos) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "RecursosFisicos{" +
            "id=" + getId() +
            ", recurso='" + getRecurso() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", unidadMedida='" + getUnidadMedida() + "'" +
            ", valorUnitario=" + getValorUnitario() +
            ", valor1H=" + getValor1H() +
            ", valor2H=" + getValor2H() +
            ", valor3H=" + getValor3H() +
            ", valor4H=" + getValor4H() +
            ", valor5H=" + getValor5H() +
            ", valor6H=" + getValor6H() +
            ", valor7H=" + getValor7H() +
            ", valor8H=" + getValor8H() +
            ", valor9H=" + getValor9H() +
            ", valor10H=" + getValor10H() +
            ", valor11H=" + getValor11H() +
            ", valor12H=" + getValor12H() +
            ", impuesto='" + getImpuesto() + "'" +
            ", foto='" + getFoto() + "'" +
            ", fotoContentType='" + getFotoContentType() + "'" +
            ", video='" + getVideo() + "'" +
            ", videoContentType='" + getVideoContentType() + "'" +
            "}";
    }
}
