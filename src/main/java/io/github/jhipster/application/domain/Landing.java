package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

import io.github.jhipster.application.domain.enumeration.Impuestod;

/**
 * A Landing.
 */
@Entity
@Table(name = "landing")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Landing implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "telefono_negocio")
    private String telefonoNegocio;

    @Column(name = "numero_puestos")
    private Integer numeroPuestos;

    @Column(name = "tarifa")
    private Integer tarifa;

    @Lob
    @Column(name = "fotografia")
    private byte[] fotografia;

    @Column(name = "fotografia_content_type")
    private String fotografiaContentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "mpuesto")
    private Impuestod mpuesto;

    @ManyToOne
    @JsonIgnoreProperties("landings")
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

    public Landing nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Landing descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTelefonoNegocio() {
        return telefonoNegocio;
    }

    public Landing telefonoNegocio(String telefonoNegocio) {
        this.telefonoNegocio = telefonoNegocio;
        return this;
    }

    public void setTelefonoNegocio(String telefonoNegocio) {
        this.telefonoNegocio = telefonoNegocio;
    }

    public Integer getNumeroPuestos() {
        return numeroPuestos;
    }

    public Landing numeroPuestos(Integer numeroPuestos) {
        this.numeroPuestos = numeroPuestos;
        return this;
    }

    public void setNumeroPuestos(Integer numeroPuestos) {
        this.numeroPuestos = numeroPuestos;
    }

    public Integer getTarifa() {
        return tarifa;
    }

    public Landing tarifa(Integer tarifa) {
        this.tarifa = tarifa;
        return this;
    }

    public void setTarifa(Integer tarifa) {
        this.tarifa = tarifa;
    }

    public byte[] getFotografia() {
        return fotografia;
    }

    public Landing fotografia(byte[] fotografia) {
        this.fotografia = fotografia;
        return this;
    }

    public void setFotografia(byte[] fotografia) {
        this.fotografia = fotografia;
    }

    public String getFotografiaContentType() {
        return fotografiaContentType;
    }

    public Landing fotografiaContentType(String fotografiaContentType) {
        this.fotografiaContentType = fotografiaContentType;
        return this;
    }

    public void setFotografiaContentType(String fotografiaContentType) {
        this.fotografiaContentType = fotografiaContentType;
    }

    public Impuestod getMpuesto() {
        return mpuesto;
    }

    public Landing mpuesto(Impuestod mpuesto) {
        this.mpuesto = mpuesto;
        return this;
    }

    public void setMpuesto(Impuestod mpuesto) {
        this.mpuesto = mpuesto;
    }

    public Sedes getSede() {
        return sede;
    }

    public Landing sede(Sedes sedes) {
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
        if (!(o instanceof Landing)) {
            return false;
        }
        return id != null && id.equals(((Landing) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Landing{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", telefonoNegocio='" + getTelefonoNegocio() + "'" +
            ", numeroPuestos=" + getNumeroPuestos() +
            ", tarifa=" + getTarifa() +
            ", fotografia='" + getFotografia() + "'" +
            ", fotografiaContentType='" + getFotografiaContentType() + "'" +
            ", mpuesto='" + getMpuesto() + "'" +
            "}";
    }
}
