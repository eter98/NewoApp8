package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

import io.github.jhipster.application.domain.enumeration.Impuestod;

/**
 * A EspacioLibre.
 */
@Entity
@Table(name = "espacio_libre")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EspacioLibre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "capacidad_instalada")
    private Integer capacidadInstalada;

    @Column(name = "wifi")
    private String wifi;

    @Column(name = "tarifa_1_h_miembro")
    private Integer tarifa1hMiembro;

    @Column(name = "tarifa_2_h_miembro")
    private Integer tarifa2hMiembro;

    @Column(name = "tarifa_3_h_miembro")
    private Integer tarifa3hMiembro;

    @Column(name = "tarifa_4_h_miembro")
    private Integer tarifa4hMiembro;

    @Column(name = "tarifa_5_h_miembro")
    private Integer tarifa5hMiembro;

    @Column(name = "tarifa_6_h_miembro")
    private Integer tarifa6hMiembro;

    @Column(name = "tarifa_7_h_miembro")
    private Integer tarifa7hMiembro;

    @Column(name = "tarifa_8_h_miembro")
    private Integer tarifa8hMiembro;

    @Column(name = "tarifa_1_h_invitado")
    private Integer tarifa1hInvitado;

    @Column(name = "tarifa_2_h_invitado")
    private Integer tarifa2hInvitado;

    @Column(name = "tarifa_3_h_invitado")
    private Integer tarifa3hInvitado;

    @Column(name = "tarifa_4_h_invitado")
    private Integer tarifa4hInvitado;

    @Column(name = "tarifa_5_h_invitado")
    private Integer tarifa5hInvitado;

    @Column(name = "tarifa_6_h_invitado")
    private Integer tarifa6hInvitado;

    @Column(name = "tarifa_7_h_invitado")
    private Integer tarifa7hInvitado;

    @Column(name = "tarifa_8_h_invitado")
    private Integer tarifa8hInvitado;

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

    @ManyToOne
    @JsonIgnoreProperties("espacioLibres")
    private Sedes sede;

    @ManyToOne
    @JsonIgnoreProperties("espacioLibres")
    private TipoEspacio tipoEspacio;

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

    public EspacioLibre nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCapacidadInstalada() {
        return capacidadInstalada;
    }

    public EspacioLibre capacidadInstalada(Integer capacidadInstalada) {
        this.capacidadInstalada = capacidadInstalada;
        return this;
    }

    public void setCapacidadInstalada(Integer capacidadInstalada) {
        this.capacidadInstalada = capacidadInstalada;
    }

    public String getWifi() {
        return wifi;
    }

    public EspacioLibre wifi(String wifi) {
        this.wifi = wifi;
        return this;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public Integer getTarifa1hMiembro() {
        return tarifa1hMiembro;
    }

    public EspacioLibre tarifa1hMiembro(Integer tarifa1hMiembro) {
        this.tarifa1hMiembro = tarifa1hMiembro;
        return this;
    }

    public void setTarifa1hMiembro(Integer tarifa1hMiembro) {
        this.tarifa1hMiembro = tarifa1hMiembro;
    }

    public Integer getTarifa2hMiembro() {
        return tarifa2hMiembro;
    }

    public EspacioLibre tarifa2hMiembro(Integer tarifa2hMiembro) {
        this.tarifa2hMiembro = tarifa2hMiembro;
        return this;
    }

    public void setTarifa2hMiembro(Integer tarifa2hMiembro) {
        this.tarifa2hMiembro = tarifa2hMiembro;
    }

    public Integer getTarifa3hMiembro() {
        return tarifa3hMiembro;
    }

    public EspacioLibre tarifa3hMiembro(Integer tarifa3hMiembro) {
        this.tarifa3hMiembro = tarifa3hMiembro;
        return this;
    }

    public void setTarifa3hMiembro(Integer tarifa3hMiembro) {
        this.tarifa3hMiembro = tarifa3hMiembro;
    }

    public Integer getTarifa4hMiembro() {
        return tarifa4hMiembro;
    }

    public EspacioLibre tarifa4hMiembro(Integer tarifa4hMiembro) {
        this.tarifa4hMiembro = tarifa4hMiembro;
        return this;
    }

    public void setTarifa4hMiembro(Integer tarifa4hMiembro) {
        this.tarifa4hMiembro = tarifa4hMiembro;
    }

    public Integer getTarifa5hMiembro() {
        return tarifa5hMiembro;
    }

    public EspacioLibre tarifa5hMiembro(Integer tarifa5hMiembro) {
        this.tarifa5hMiembro = tarifa5hMiembro;
        return this;
    }

    public void setTarifa5hMiembro(Integer tarifa5hMiembro) {
        this.tarifa5hMiembro = tarifa5hMiembro;
    }

    public Integer getTarifa6hMiembro() {
        return tarifa6hMiembro;
    }

    public EspacioLibre tarifa6hMiembro(Integer tarifa6hMiembro) {
        this.tarifa6hMiembro = tarifa6hMiembro;
        return this;
    }

    public void setTarifa6hMiembro(Integer tarifa6hMiembro) {
        this.tarifa6hMiembro = tarifa6hMiembro;
    }

    public Integer getTarifa7hMiembro() {
        return tarifa7hMiembro;
    }

    public EspacioLibre tarifa7hMiembro(Integer tarifa7hMiembro) {
        this.tarifa7hMiembro = tarifa7hMiembro;
        return this;
    }

    public void setTarifa7hMiembro(Integer tarifa7hMiembro) {
        this.tarifa7hMiembro = tarifa7hMiembro;
    }

    public Integer getTarifa8hMiembro() {
        return tarifa8hMiembro;
    }

    public EspacioLibre tarifa8hMiembro(Integer tarifa8hMiembro) {
        this.tarifa8hMiembro = tarifa8hMiembro;
        return this;
    }

    public void setTarifa8hMiembro(Integer tarifa8hMiembro) {
        this.tarifa8hMiembro = tarifa8hMiembro;
    }

    public Integer getTarifa1hInvitado() {
        return tarifa1hInvitado;
    }

    public EspacioLibre tarifa1hInvitado(Integer tarifa1hInvitado) {
        this.tarifa1hInvitado = tarifa1hInvitado;
        return this;
    }

    public void setTarifa1hInvitado(Integer tarifa1hInvitado) {
        this.tarifa1hInvitado = tarifa1hInvitado;
    }

    public Integer getTarifa2hInvitado() {
        return tarifa2hInvitado;
    }

    public EspacioLibre tarifa2hInvitado(Integer tarifa2hInvitado) {
        this.tarifa2hInvitado = tarifa2hInvitado;
        return this;
    }

    public void setTarifa2hInvitado(Integer tarifa2hInvitado) {
        this.tarifa2hInvitado = tarifa2hInvitado;
    }

    public Integer getTarifa3hInvitado() {
        return tarifa3hInvitado;
    }

    public EspacioLibre tarifa3hInvitado(Integer tarifa3hInvitado) {
        this.tarifa3hInvitado = tarifa3hInvitado;
        return this;
    }

    public void setTarifa3hInvitado(Integer tarifa3hInvitado) {
        this.tarifa3hInvitado = tarifa3hInvitado;
    }

    public Integer getTarifa4hInvitado() {
        return tarifa4hInvitado;
    }

    public EspacioLibre tarifa4hInvitado(Integer tarifa4hInvitado) {
        this.tarifa4hInvitado = tarifa4hInvitado;
        return this;
    }

    public void setTarifa4hInvitado(Integer tarifa4hInvitado) {
        this.tarifa4hInvitado = tarifa4hInvitado;
    }

    public Integer getTarifa5hInvitado() {
        return tarifa5hInvitado;
    }

    public EspacioLibre tarifa5hInvitado(Integer tarifa5hInvitado) {
        this.tarifa5hInvitado = tarifa5hInvitado;
        return this;
    }

    public void setTarifa5hInvitado(Integer tarifa5hInvitado) {
        this.tarifa5hInvitado = tarifa5hInvitado;
    }

    public Integer getTarifa6hInvitado() {
        return tarifa6hInvitado;
    }

    public EspacioLibre tarifa6hInvitado(Integer tarifa6hInvitado) {
        this.tarifa6hInvitado = tarifa6hInvitado;
        return this;
    }

    public void setTarifa6hInvitado(Integer tarifa6hInvitado) {
        this.tarifa6hInvitado = tarifa6hInvitado;
    }

    public Integer getTarifa7hInvitado() {
        return tarifa7hInvitado;
    }

    public EspacioLibre tarifa7hInvitado(Integer tarifa7hInvitado) {
        this.tarifa7hInvitado = tarifa7hInvitado;
        return this;
    }

    public void setTarifa7hInvitado(Integer tarifa7hInvitado) {
        this.tarifa7hInvitado = tarifa7hInvitado;
    }

    public Integer getTarifa8hInvitado() {
        return tarifa8hInvitado;
    }

    public EspacioLibre tarifa8hInvitado(Integer tarifa8hInvitado) {
        this.tarifa8hInvitado = tarifa8hInvitado;
        return this;
    }

    public void setTarifa8hInvitado(Integer tarifa8hInvitado) {
        this.tarifa8hInvitado = tarifa8hInvitado;
    }

    public Impuestod getImpuesto() {
        return impuesto;
    }

    public EspacioLibre impuesto(Impuestod impuesto) {
        this.impuesto = impuesto;
        return this;
    }

    public void setImpuesto(Impuestod impuesto) {
        this.impuesto = impuesto;
    }

    public byte[] getVideo() {
        return video;
    }

    public EspacioLibre video(byte[] video) {
        this.video = video;
        return this;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    public String getVideoContentType() {
        return videoContentType;
    }

    public EspacioLibre videoContentType(String videoContentType) {
        this.videoContentType = videoContentType;
        return this;
    }

    public void setVideoContentType(String videoContentType) {
        this.videoContentType = videoContentType;
    }

    public byte[] getImagen1() {
        return imagen1;
    }

    public EspacioLibre imagen1(byte[] imagen1) {
        this.imagen1 = imagen1;
        return this;
    }

    public void setImagen1(byte[] imagen1) {
        this.imagen1 = imagen1;
    }

    public String getImagen1ContentType() {
        return imagen1ContentType;
    }

    public EspacioLibre imagen1ContentType(String imagen1ContentType) {
        this.imagen1ContentType = imagen1ContentType;
        return this;
    }

    public void setImagen1ContentType(String imagen1ContentType) {
        this.imagen1ContentType = imagen1ContentType;
    }

    public byte[] getImagen2() {
        return imagen2;
    }

    public EspacioLibre imagen2(byte[] imagen2) {
        this.imagen2 = imagen2;
        return this;
    }

    public void setImagen2(byte[] imagen2) {
        this.imagen2 = imagen2;
    }

    public String getImagen2ContentType() {
        return imagen2ContentType;
    }

    public EspacioLibre imagen2ContentType(String imagen2ContentType) {
        this.imagen2ContentType = imagen2ContentType;
        return this;
    }

    public void setImagen2ContentType(String imagen2ContentType) {
        this.imagen2ContentType = imagen2ContentType;
    }

    public byte[] getImagen3() {
        return imagen3;
    }

    public EspacioLibre imagen3(byte[] imagen3) {
        this.imagen3 = imagen3;
        return this;
    }

    public void setImagen3(byte[] imagen3) {
        this.imagen3 = imagen3;
    }

    public String getImagen3ContentType() {
        return imagen3ContentType;
    }

    public EspacioLibre imagen3ContentType(String imagen3ContentType) {
        this.imagen3ContentType = imagen3ContentType;
        return this;
    }

    public void setImagen3ContentType(String imagen3ContentType) {
        this.imagen3ContentType = imagen3ContentType;
    }

    public byte[] getImagen4() {
        return imagen4;
    }

    public EspacioLibre imagen4(byte[] imagen4) {
        this.imagen4 = imagen4;
        return this;
    }

    public void setImagen4(byte[] imagen4) {
        this.imagen4 = imagen4;
    }

    public String getImagen4ContentType() {
        return imagen4ContentType;
    }

    public EspacioLibre imagen4ContentType(String imagen4ContentType) {
        this.imagen4ContentType = imagen4ContentType;
        return this;
    }

    public void setImagen4ContentType(String imagen4ContentType) {
        this.imagen4ContentType = imagen4ContentType;
    }

    public byte[] getImagen5() {
        return imagen5;
    }

    public EspacioLibre imagen5(byte[] imagen5) {
        this.imagen5 = imagen5;
        return this;
    }

    public void setImagen5(byte[] imagen5) {
        this.imagen5 = imagen5;
    }

    public String getImagen5ContentType() {
        return imagen5ContentType;
    }

    public EspacioLibre imagen5ContentType(String imagen5ContentType) {
        this.imagen5ContentType = imagen5ContentType;
        return this;
    }

    public void setImagen5ContentType(String imagen5ContentType) {
        this.imagen5ContentType = imagen5ContentType;
    }

    public byte[] getImagen6() {
        return imagen6;
    }

    public EspacioLibre imagen6(byte[] imagen6) {
        this.imagen6 = imagen6;
        return this;
    }

    public void setImagen6(byte[] imagen6) {
        this.imagen6 = imagen6;
    }

    public String getImagen6ContentType() {
        return imagen6ContentType;
    }

    public EspacioLibre imagen6ContentType(String imagen6ContentType) {
        this.imagen6ContentType = imagen6ContentType;
        return this;
    }

    public void setImagen6ContentType(String imagen6ContentType) {
        this.imagen6ContentType = imagen6ContentType;
    }

    public Sedes getSede() {
        return sede;
    }

    public EspacioLibre sede(Sedes sedes) {
        this.sede = sedes;
        return this;
    }

    public void setSede(Sedes sedes) {
        this.sede = sedes;
    }

    public TipoEspacio getTipoEspacio() {
        return tipoEspacio;
    }

    public EspacioLibre tipoEspacio(TipoEspacio tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
        return this;
    }

    public void setTipoEspacio(TipoEspacio tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EspacioLibre)) {
            return false;
        }
        return id != null && id.equals(((EspacioLibre) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EspacioLibre{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", capacidadInstalada=" + getCapacidadInstalada() +
            ", wifi='" + getWifi() + "'" +
            ", tarifa1hMiembro=" + getTarifa1hMiembro() +
            ", tarifa2hMiembro=" + getTarifa2hMiembro() +
            ", tarifa3hMiembro=" + getTarifa3hMiembro() +
            ", tarifa4hMiembro=" + getTarifa4hMiembro() +
            ", tarifa5hMiembro=" + getTarifa5hMiembro() +
            ", tarifa6hMiembro=" + getTarifa6hMiembro() +
            ", tarifa7hMiembro=" + getTarifa7hMiembro() +
            ", tarifa8hMiembro=" + getTarifa8hMiembro() +
            ", tarifa1hInvitado=" + getTarifa1hInvitado() +
            ", tarifa2hInvitado=" + getTarifa2hInvitado() +
            ", tarifa3hInvitado=" + getTarifa3hInvitado() +
            ", tarifa4hInvitado=" + getTarifa4hInvitado() +
            ", tarifa5hInvitado=" + getTarifa5hInvitado() +
            ", tarifa6hInvitado=" + getTarifa6hInvitado() +
            ", tarifa7hInvitado=" + getTarifa7hInvitado() +
            ", tarifa8hInvitado=" + getTarifa8hInvitado() +
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
