package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Sedes.
 */
@Entity
@Table(name = "sedes")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Sedes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nombre_sede")
    private String nombreSede;

    @Column(name = "coordenada_x")
    private Double coordenadaX;

    @Column(name = "coordenada_y")
    private Double coordenadaY;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono_comunidad")
    private String telefonoComunidad;

    @Column(name = "telefono_negocio")
    private String telefonoNegocio;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "descripcion_sede")
    private String descripcionSede;

    @Column(name = "horario")
    private String horario;

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

    @ManyToOne
    @JsonIgnoreProperties("sedes")
    private Ciudad ciudad;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public Sedes nombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
        return this;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public Double getCoordenadaX() {
        return coordenadaX;
    }

    public Sedes coordenadaX(Double coordenadaX) {
        this.coordenadaX = coordenadaX;
        return this;
    }

    public void setCoordenadaX(Double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public Double getCoordenadaY() {
        return coordenadaY;
    }

    public Sedes coordenadaY(Double coordenadaY) {
        this.coordenadaY = coordenadaY;
        return this;
    }

    public void setCoordenadaY(Double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public String getDireccion() {
        return direccion;
    }

    public Sedes direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonoComunidad() {
        return telefonoComunidad;
    }

    public Sedes telefonoComunidad(String telefonoComunidad) {
        this.telefonoComunidad = telefonoComunidad;
        return this;
    }

    public void setTelefonoComunidad(String telefonoComunidad) {
        this.telefonoComunidad = telefonoComunidad;
    }

    public String getTelefonoNegocio() {
        return telefonoNegocio;
    }

    public Sedes telefonoNegocio(String telefonoNegocio) {
        this.telefonoNegocio = telefonoNegocio;
        return this;
    }

    public void setTelefonoNegocio(String telefonoNegocio) {
        this.telefonoNegocio = telefonoNegocio;
    }

    public String getDescripcionSede() {
        return descripcionSede;
    }

    public Sedes descripcionSede(String descripcionSede) {
        this.descripcionSede = descripcionSede;
        return this;
    }

    public void setDescripcionSede(String descripcionSede) {
        this.descripcionSede = descripcionSede;
    }

    public String getHorario() {
        return horario;
    }

    public Sedes horario(String horario) {
        this.horario = horario;
        return this;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public byte[] getVideo() {
        return video;
    }

    public Sedes video(byte[] video) {
        this.video = video;
        return this;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    public String getVideoContentType() {
        return videoContentType;
    }

    public Sedes videoContentType(String videoContentType) {
        this.videoContentType = videoContentType;
        return this;
    }

    public void setVideoContentType(String videoContentType) {
        this.videoContentType = videoContentType;
    }

    public byte[] getImagen1() {
        return imagen1;
    }

    public Sedes imagen1(byte[] imagen1) {
        this.imagen1 = imagen1;
        return this;
    }

    public void setImagen1(byte[] imagen1) {
        this.imagen1 = imagen1;
    }

    public String getImagen1ContentType() {
        return imagen1ContentType;
    }

    public Sedes imagen1ContentType(String imagen1ContentType) {
        this.imagen1ContentType = imagen1ContentType;
        return this;
    }

    public void setImagen1ContentType(String imagen1ContentType) {
        this.imagen1ContentType = imagen1ContentType;
    }

    public byte[] getImagen2() {
        return imagen2;
    }

    public Sedes imagen2(byte[] imagen2) {
        this.imagen2 = imagen2;
        return this;
    }

    public void setImagen2(byte[] imagen2) {
        this.imagen2 = imagen2;
    }

    public String getImagen2ContentType() {
        return imagen2ContentType;
    }

    public Sedes imagen2ContentType(String imagen2ContentType) {
        this.imagen2ContentType = imagen2ContentType;
        return this;
    }

    public void setImagen2ContentType(String imagen2ContentType) {
        this.imagen2ContentType = imagen2ContentType;
    }

    public byte[] getImagen3() {
        return imagen3;
    }

    public Sedes imagen3(byte[] imagen3) {
        this.imagen3 = imagen3;
        return this;
    }

    public void setImagen3(byte[] imagen3) {
        this.imagen3 = imagen3;
    }

    public String getImagen3ContentType() {
        return imagen3ContentType;
    }

    public Sedes imagen3ContentType(String imagen3ContentType) {
        this.imagen3ContentType = imagen3ContentType;
        return this;
    }

    public void setImagen3ContentType(String imagen3ContentType) {
        this.imagen3ContentType = imagen3ContentType;
    }

    public byte[] getImagen4() {
        return imagen4;
    }

    public Sedes imagen4(byte[] imagen4) {
        this.imagen4 = imagen4;
        return this;
    }

    public void setImagen4(byte[] imagen4) {
        this.imagen4 = imagen4;
    }

    public String getImagen4ContentType() {
        return imagen4ContentType;
    }

    public Sedes imagen4ContentType(String imagen4ContentType) {
        this.imagen4ContentType = imagen4ContentType;
        return this;
    }

    public void setImagen4ContentType(String imagen4ContentType) {
        this.imagen4ContentType = imagen4ContentType;
    }

    public byte[] getImagen5() {
        return imagen5;
    }

    public Sedes imagen5(byte[] imagen5) {
        this.imagen5 = imagen5;
        return this;
    }

    public void setImagen5(byte[] imagen5) {
        this.imagen5 = imagen5;
    }

    public String getImagen5ContentType() {
        return imagen5ContentType;
    }

    public Sedes imagen5ContentType(String imagen5ContentType) {
        this.imagen5ContentType = imagen5ContentType;
        return this;
    }

    public void setImagen5ContentType(String imagen5ContentType) {
        this.imagen5ContentType = imagen5ContentType;
    }

    public byte[] getImagen6() {
        return imagen6;
    }

    public Sedes imagen6(byte[] imagen6) {
        this.imagen6 = imagen6;
        return this;
    }

    public void setImagen6(byte[] imagen6) {
        this.imagen6 = imagen6;
    }

    public String getImagen6ContentType() {
        return imagen6ContentType;
    }

    public Sedes imagen6ContentType(String imagen6ContentType) {
        this.imagen6ContentType = imagen6ContentType;
        return this;
    }

    public void setImagen6ContentType(String imagen6ContentType) {
        this.imagen6ContentType = imagen6ContentType;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public Sedes ciudad(Ciudad ciudad) {
        this.ciudad = ciudad;
        return this;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sedes)) {
            return false;
        }
        return id != null && id.equals(((Sedes) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Sedes{" +
            "id=" + getId() +
            ", nombreSede='" + getNombreSede() + "'" +
            ", coordenadaX=" + getCoordenadaX() +
            ", coordenadaY=" + getCoordenadaY() +
            ", direccion='" + getDireccion() + "'" +
            ", telefonoComunidad='" + getTelefonoComunidad() + "'" +
            ", telefonoNegocio='" + getTelefonoNegocio() + "'" +
            ", descripcionSede='" + getDescripcionSede() + "'" +
            ", horario='" + getHorario() + "'" +
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
