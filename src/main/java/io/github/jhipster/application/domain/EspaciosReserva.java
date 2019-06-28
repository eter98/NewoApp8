package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

import io.github.jhipster.application.domain.enumeration.Impuestod;

/**
 * A EspaciosReserva.
 */
@Entity
@Table(name = "espacios_reserva")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EspaciosReserva implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "facilidades")
    private String facilidades;

    @Column(name = "capacidad")
    private Integer capacidad;

    @Column(name = "apertura")
    private String apertura;

    @Column(name = "cierre")
    private String cierre;

    @Column(name = "tarifa_1_hora")
    private Integer tarifa1Hora;

    @Column(name = "tarifa_2_hora")
    private Integer tarifa2Hora;

    @Column(name = "tarifa_3_hora")
    private Integer tarifa3Hora;

    @Column(name = "tarifa_4_hora")
    private Integer tarifa4Hora;

    @Column(name = "tarifa_5_hora")
    private Integer tarifa5Hora;

    @Column(name = "tarifa_6_hora")
    private Integer tarifa6Hora;

    @Column(name = "tarifa_7_hora")
    private Integer tarifa7Hora;

    @Column(name = "tarifa_8_hora")
    private Integer tarifa8Hora;

    @Enumerated(EnumType.STRING)
    @Column(name = "impuesto")
    private Impuestod impuesto;

    @Column(name = "wifi")
    private String wifi;

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
    @JsonIgnoreProperties("espaciosReservas")
    private Sedes sede;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public EspaciosReserva nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public EspaciosReserva descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFacilidades() {
        return facilidades;
    }

    public EspaciosReserva facilidades(String facilidades) {
        this.facilidades = facilidades;
        return this;
    }

    public void setFacilidades(String facilidades) {
        this.facilidades = facilidades;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public EspaciosReserva capacidad(Integer capacidad) {
        this.capacidad = capacidad;
        return this;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getApertura() {
        return apertura;
    }

    public EspaciosReserva apertura(String apertura) {
        this.apertura = apertura;
        return this;
    }

    public void setApertura(String apertura) {
        this.apertura = apertura;
    }

    public String getCierre() {
        return cierre;
    }

    public EspaciosReserva cierre(String cierre) {
        this.cierre = cierre;
        return this;
    }

    public void setCierre(String cierre) {
        this.cierre = cierre;
    }

    public Integer getTarifa1Hora() {
        return tarifa1Hora;
    }

    public EspaciosReserva tarifa1Hora(Integer tarifa1Hora) {
        this.tarifa1Hora = tarifa1Hora;
        return this;
    }

    public void setTarifa1Hora(Integer tarifa1Hora) {
        this.tarifa1Hora = tarifa1Hora;
    }

    public Integer getTarifa2Hora() {
        return tarifa2Hora;
    }

    public EspaciosReserva tarifa2Hora(Integer tarifa2Hora) {
        this.tarifa2Hora = tarifa2Hora;
        return this;
    }

    public void setTarifa2Hora(Integer tarifa2Hora) {
        this.tarifa2Hora = tarifa2Hora;
    }

    public Integer getTarifa3Hora() {
        return tarifa3Hora;
    }

    public EspaciosReserva tarifa3Hora(Integer tarifa3Hora) {
        this.tarifa3Hora = tarifa3Hora;
        return this;
    }

    public void setTarifa3Hora(Integer tarifa3Hora) {
        this.tarifa3Hora = tarifa3Hora;
    }

    public Integer getTarifa4Hora() {
        return tarifa4Hora;
    }

    public EspaciosReserva tarifa4Hora(Integer tarifa4Hora) {
        this.tarifa4Hora = tarifa4Hora;
        return this;
    }

    public void setTarifa4Hora(Integer tarifa4Hora) {
        this.tarifa4Hora = tarifa4Hora;
    }

    public Integer getTarifa5Hora() {
        return tarifa5Hora;
    }

    public EspaciosReserva tarifa5Hora(Integer tarifa5Hora) {
        this.tarifa5Hora = tarifa5Hora;
        return this;
    }

    public void setTarifa5Hora(Integer tarifa5Hora) {
        this.tarifa5Hora = tarifa5Hora;
    }

    public Integer getTarifa6Hora() {
        return tarifa6Hora;
    }

    public EspaciosReserva tarifa6Hora(Integer tarifa6Hora) {
        this.tarifa6Hora = tarifa6Hora;
        return this;
    }

    public void setTarifa6Hora(Integer tarifa6Hora) {
        this.tarifa6Hora = tarifa6Hora;
    }

    public Integer getTarifa7Hora() {
        return tarifa7Hora;
    }

    public EspaciosReserva tarifa7Hora(Integer tarifa7Hora) {
        this.tarifa7Hora = tarifa7Hora;
        return this;
    }

    public void setTarifa7Hora(Integer tarifa7Hora) {
        this.tarifa7Hora = tarifa7Hora;
    }

    public Integer getTarifa8Hora() {
        return tarifa8Hora;
    }

    public EspaciosReserva tarifa8Hora(Integer tarifa8Hora) {
        this.tarifa8Hora = tarifa8Hora;
        return this;
    }

    public void setTarifa8Hora(Integer tarifa8Hora) {
        this.tarifa8Hora = tarifa8Hora;
    }

    public Impuestod getImpuesto() {
        return impuesto;
    }

    public EspaciosReserva impuesto(Impuestod impuesto) {
        this.impuesto = impuesto;
        return this;
    }

    public void setImpuesto(Impuestod impuesto) {
        this.impuesto = impuesto;
    }

    public String getWifi() {
        return wifi;
    }

    public EspaciosReserva wifi(String wifi) {
        this.wifi = wifi;
        return this;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public byte[] getVideo() {
        return video;
    }

    public EspaciosReserva video(byte[] video) {
        this.video = video;
        return this;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    public String getVideoContentType() {
        return videoContentType;
    }

    public EspaciosReserva videoContentType(String videoContentType) {
        this.videoContentType = videoContentType;
        return this;
    }

    public void setVideoContentType(String videoContentType) {
        this.videoContentType = videoContentType;
    }

    public byte[] getImagen1() {
        return imagen1;
    }

    public EspaciosReserva imagen1(byte[] imagen1) {
        this.imagen1 = imagen1;
        return this;
    }

    public void setImagen1(byte[] imagen1) {
        this.imagen1 = imagen1;
    }

    public String getImagen1ContentType() {
        return imagen1ContentType;
    }

    public EspaciosReserva imagen1ContentType(String imagen1ContentType) {
        this.imagen1ContentType = imagen1ContentType;
        return this;
    }

    public void setImagen1ContentType(String imagen1ContentType) {
        this.imagen1ContentType = imagen1ContentType;
    }

    public byte[] getImagen2() {
        return imagen2;
    }

    public EspaciosReserva imagen2(byte[] imagen2) {
        this.imagen2 = imagen2;
        return this;
    }

    public void setImagen2(byte[] imagen2) {
        this.imagen2 = imagen2;
    }

    public String getImagen2ContentType() {
        return imagen2ContentType;
    }

    public EspaciosReserva imagen2ContentType(String imagen2ContentType) {
        this.imagen2ContentType = imagen2ContentType;
        return this;
    }

    public void setImagen2ContentType(String imagen2ContentType) {
        this.imagen2ContentType = imagen2ContentType;
    }

    public byte[] getImagen3() {
        return imagen3;
    }

    public EspaciosReserva imagen3(byte[] imagen3) {
        this.imagen3 = imagen3;
        return this;
    }

    public void setImagen3(byte[] imagen3) {
        this.imagen3 = imagen3;
    }

    public String getImagen3ContentType() {
        return imagen3ContentType;
    }

    public EspaciosReserva imagen3ContentType(String imagen3ContentType) {
        this.imagen3ContentType = imagen3ContentType;
        return this;
    }

    public void setImagen3ContentType(String imagen3ContentType) {
        this.imagen3ContentType = imagen3ContentType;
    }

    public byte[] getImagen4() {
        return imagen4;
    }

    public EspaciosReserva imagen4(byte[] imagen4) {
        this.imagen4 = imagen4;
        return this;
    }

    public void setImagen4(byte[] imagen4) {
        this.imagen4 = imagen4;
    }

    public String getImagen4ContentType() {
        return imagen4ContentType;
    }

    public EspaciosReserva imagen4ContentType(String imagen4ContentType) {
        this.imagen4ContentType = imagen4ContentType;
        return this;
    }

    public void setImagen4ContentType(String imagen4ContentType) {
        this.imagen4ContentType = imagen4ContentType;
    }

    public byte[] getImagen5() {
        return imagen5;
    }

    public EspaciosReserva imagen5(byte[] imagen5) {
        this.imagen5 = imagen5;
        return this;
    }

    public void setImagen5(byte[] imagen5) {
        this.imagen5 = imagen5;
    }

    public String getImagen5ContentType() {
        return imagen5ContentType;
    }

    public EspaciosReserva imagen5ContentType(String imagen5ContentType) {
        this.imagen5ContentType = imagen5ContentType;
        return this;
    }

    public void setImagen5ContentType(String imagen5ContentType) {
        this.imagen5ContentType = imagen5ContentType;
    }

    public byte[] getImagen6() {
        return imagen6;
    }

    public EspaciosReserva imagen6(byte[] imagen6) {
        this.imagen6 = imagen6;
        return this;
    }

    public void setImagen6(byte[] imagen6) {
        this.imagen6 = imagen6;
    }

    public String getImagen6ContentType() {
        return imagen6ContentType;
    }

    public EspaciosReserva imagen6ContentType(String imagen6ContentType) {
        this.imagen6ContentType = imagen6ContentType;
        return this;
    }

    public void setImagen6ContentType(String imagen6ContentType) {
        this.imagen6ContentType = imagen6ContentType;
    }

    public Sedes getSede() {
        return sede;
    }

    public EspaciosReserva sede(Sedes sedes) {
        this.sede = sedes;
        return this;
    }

    public void setSede(Sedes sedes) {
        this.sede = sedes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EspaciosReserva)) {
            return false;
        }
        return id != null && id.equals(((EspaciosReserva) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EspaciosReserva{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", facilidades='" + getFacilidades() + "'" +
            ", capacidad=" + getCapacidad() +
            ", apertura='" + getApertura() + "'" +
            ", cierre='" + getCierre() + "'" +
            ", tarifa1Hora=" + getTarifa1Hora() +
            ", tarifa2Hora=" + getTarifa2Hora() +
            ", tarifa3Hora=" + getTarifa3Hora() +
            ", tarifa4Hora=" + getTarifa4Hora() +
            ", tarifa5Hora=" + getTarifa5Hora() +
            ", tarifa6Hora=" + getTarifa6Hora() +
            ", tarifa7Hora=" + getTarifa7Hora() +
            ", tarifa8Hora=" + getTarifa8Hora() +
            ", impuesto='" + getImpuesto() + "'" +
            ", wifi='" + getWifi() + "'" +
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
