package io.github.jhipster.application.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

import io.github.jhipster.application.domain.enumeration.Impuestod;

/**
 * A ProductosServicios.
 */
@Entity
@Table(name = "productos_servicios")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProductosServicios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Lob
    @Column(name = "foto")
    private byte[] foto;

    @Column(name = "foto_content_type")
    private String fotoContentType;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "inventariables")
    private Boolean inventariables;

    @Column(name = "valor")
    private Integer valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "impuesto")
    private Impuestod impuesto;

    @Lob
    @Column(name = "video")
    private byte[] video;

    @Column(name = "video_content_type")
    private String videoContentType;

    @Lob
    @Column(name = "imagen_1")
    private byte[] imagen1;

    @Column(name = "imagen_1_content_type")
    private String imagen1ContentType;

    @Lob
    @Column(name = "imagen_2")
    private byte[] imagen2;

    @Column(name = "imagen_2_content_type")
    private String imagen2ContentType;

    @Lob
    @Column(name = "imagen_3")
    private byte[] imagen3;

    @Column(name = "imagen_3_content_type")
    private String imagen3ContentType;

    @Lob
    @Column(name = "imagen_4")
    private byte[] imagen4;

    @Column(name = "imagen_4_content_type")
    private String imagen4ContentType;

    @Lob
    @Column(name = "imagen_5")
    private byte[] imagen5;

    @Column(name = "imagen_5_content_type")
    private String imagen5ContentType;

    @Lob
    @Column(name = "imagen_6")
    private byte[] imagen6;

    @Column(name = "imagen_6_content_type")
    private String imagen6ContentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public ProductosServicios nombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
        return this;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public byte[] getFoto() {
        return foto;
    }

    public ProductosServicios foto(byte[] foto) {
        this.foto = foto;
        return this;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getFotoContentType() {
        return fotoContentType;
    }

    public ProductosServicios fotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
        return this;
    }

    public void setFotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ProductosServicios descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean isInventariables() {
        return inventariables;
    }

    public ProductosServicios inventariables(Boolean inventariables) {
        this.inventariables = inventariables;
        return this;
    }

    public void setInventariables(Boolean inventariables) {
        this.inventariables = inventariables;
    }

    public Integer getValor() {
        return valor;
    }

    public ProductosServicios valor(Integer valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Impuestod getImpuesto() {
        return impuesto;
    }

    public ProductosServicios impuesto(Impuestod impuesto) {
        this.impuesto = impuesto;
        return this;
    }

    public void setImpuesto(Impuestod impuesto) {
        this.impuesto = impuesto;
    }

    public byte[] getVideo() {
        return video;
    }

    public ProductosServicios video(byte[] video) {
        this.video = video;
        return this;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    public String getVideoContentType() {
        return videoContentType;
    }

    public ProductosServicios videoContentType(String videoContentType) {
        this.videoContentType = videoContentType;
        return this;
    }

    public void setVideoContentType(String videoContentType) {
        this.videoContentType = videoContentType;
    }

    public byte[] getImagen1() {
        return imagen1;
    }

    public ProductosServicios imagen1(byte[] imagen1) {
        this.imagen1 = imagen1;
        return this;
    }

    public void setImagen1(byte[] imagen1) {
        this.imagen1 = imagen1;
    }

    public String getImagen1ContentType() {
        return imagen1ContentType;
    }

    public ProductosServicios imagen1ContentType(String imagen1ContentType) {
        this.imagen1ContentType = imagen1ContentType;
        return this;
    }

    public void setImagen1ContentType(String imagen1ContentType) {
        this.imagen1ContentType = imagen1ContentType;
    }

    public byte[] getImagen2() {
        return imagen2;
    }

    public ProductosServicios imagen2(byte[] imagen2) {
        this.imagen2 = imagen2;
        return this;
    }

    public void setImagen2(byte[] imagen2) {
        this.imagen2 = imagen2;
    }

    public String getImagen2ContentType() {
        return imagen2ContentType;
    }

    public ProductosServicios imagen2ContentType(String imagen2ContentType) {
        this.imagen2ContentType = imagen2ContentType;
        return this;
    }

    public void setImagen2ContentType(String imagen2ContentType) {
        this.imagen2ContentType = imagen2ContentType;
    }

    public byte[] getImagen3() {
        return imagen3;
    }

    public ProductosServicios imagen3(byte[] imagen3) {
        this.imagen3 = imagen3;
        return this;
    }

    public void setImagen3(byte[] imagen3) {
        this.imagen3 = imagen3;
    }

    public String getImagen3ContentType() {
        return imagen3ContentType;
    }

    public ProductosServicios imagen3ContentType(String imagen3ContentType) {
        this.imagen3ContentType = imagen3ContentType;
        return this;
    }

    public void setImagen3ContentType(String imagen3ContentType) {
        this.imagen3ContentType = imagen3ContentType;
    }

    public byte[] getImagen4() {
        return imagen4;
    }

    public ProductosServicios imagen4(byte[] imagen4) {
        this.imagen4 = imagen4;
        return this;
    }

    public void setImagen4(byte[] imagen4) {
        this.imagen4 = imagen4;
    }

    public String getImagen4ContentType() {
        return imagen4ContentType;
    }

    public ProductosServicios imagen4ContentType(String imagen4ContentType) {
        this.imagen4ContentType = imagen4ContentType;
        return this;
    }

    public void setImagen4ContentType(String imagen4ContentType) {
        this.imagen4ContentType = imagen4ContentType;
    }

    public byte[] getImagen5() {
        return imagen5;
    }

    public ProductosServicios imagen5(byte[] imagen5) {
        this.imagen5 = imagen5;
        return this;
    }

    public void setImagen5(byte[] imagen5) {
        this.imagen5 = imagen5;
    }

    public String getImagen5ContentType() {
        return imagen5ContentType;
    }

    public ProductosServicios imagen5ContentType(String imagen5ContentType) {
        this.imagen5ContentType = imagen5ContentType;
        return this;
    }

    public void setImagen5ContentType(String imagen5ContentType) {
        this.imagen5ContentType = imagen5ContentType;
    }

    public byte[] getImagen6() {
        return imagen6;
    }

    public ProductosServicios imagen6(byte[] imagen6) {
        this.imagen6 = imagen6;
        return this;
    }

    public void setImagen6(byte[] imagen6) {
        this.imagen6 = imagen6;
    }

    public String getImagen6ContentType() {
        return imagen6ContentType;
    }

    public ProductosServicios imagen6ContentType(String imagen6ContentType) {
        this.imagen6ContentType = imagen6ContentType;
        return this;
    }

    public void setImagen6ContentType(String imagen6ContentType) {
        this.imagen6ContentType = imagen6ContentType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductosServicios)) {
            return false;
        }
        return id != null && id.equals(((ProductosServicios) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProductosServicios{" +
            "id=" + getId() +
            ", nombreProducto='" + getNombreProducto() + "'" +
            ", foto='" + getFoto() + "'" +
            ", fotoContentType='" + getFotoContentType() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", inventariables='" + isInventariables() + "'" +
            ", valor=" + getValor() +
            ", impuesto='" + getImpuesto() + "'" +
            ", video='" + getVideo() + "'" +
            ", videoContentType='" + getVideoContentType() + "'" +
            ", imagen1='" + getImagen1() + "'" +
            ", imagen1ContentType='" + getImagen1ContentType() + "'" +
            ", imagen2='" + getImagen2() + "'" +
            ", imagen2ContentType='" + getImagen2ContentType() + "'" +
            ", imagen3='" + getImagen3() + "'" +
            ", imagen3ContentType='" + getImagen3ContentType() + "'" +
            ", imagen4='" + getImagen4() + "'" +
            ", imagen4ContentType='" + getImagen4ContentType() + "'" +
            ", imagen5='" + getImagen5() + "'" +
            ", imagen5ContentType='" + getImagen5ContentType() + "'" +
            ", imagen6='" + getImagen6() + "'" +
            ", imagen6ContentType='" + getImagen6ContentType() + "'" +
            "}";
    }
}
