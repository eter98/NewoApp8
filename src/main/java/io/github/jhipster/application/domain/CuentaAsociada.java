package io.github.jhipster.application.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A CuentaAsociada.
 */
@Entity
@Table(name = "cuenta_asociada")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CuentaAsociada implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "identificaciontitular")
    private String identificaciontitular;

    @Column(name = "nombre_titular")
    private String nombreTitular;

    @Column(name = "apellido_titular")
    private String apellidoTitular;

    @Column(name = "numero_cuenta")
    private String numeroCuenta;

    @Column(name = "tipo_cuenta")
    private String tipoCuenta;

    @Column(name = "codigo_seguridad")
    private String codigoSeguridad;

    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    @OneToMany(mappedBy = "cuentaAsociada")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Facturacion> facturacions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificaciontitular() {
        return identificaciontitular;
    }

    public CuentaAsociada identificaciontitular(String identificaciontitular) {
        this.identificaciontitular = identificaciontitular;
        return this;
    }

    public void setIdentificaciontitular(String identificaciontitular) {
        this.identificaciontitular = identificaciontitular;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public CuentaAsociada nombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
        return this;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getApellidoTitular() {
        return apellidoTitular;
    }

    public CuentaAsociada apellidoTitular(String apellidoTitular) {
        this.apellidoTitular = apellidoTitular;
        return this;
    }

    public void setApellidoTitular(String apellidoTitular) {
        this.apellidoTitular = apellidoTitular;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public CuentaAsociada numeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
        return this;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public CuentaAsociada tipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
        return this;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public CuentaAsociada codigoSeguridad(String codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
        return this;
    }

    public void setCodigoSeguridad(String codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public CuentaAsociada fechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
        return this;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Set<Facturacion> getFacturacions() {
        return facturacions;
    }

    public CuentaAsociada facturacions(Set<Facturacion> facturacions) {
        this.facturacions = facturacions;
        return this;
    }

    public CuentaAsociada addFacturacion(Facturacion facturacion) {
        this.facturacions.add(facturacion);
        facturacion.setCuentaAsociada(this);
        return this;
    }

    public CuentaAsociada removeFacturacion(Facturacion facturacion) {
        this.facturacions.remove(facturacion);
        facturacion.setCuentaAsociada(null);
        return this;
    }

    public void setFacturacions(Set<Facturacion> facturacions) {
        this.facturacions = facturacions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CuentaAsociada)) {
            return false;
        }
        return id != null && id.equals(((CuentaAsociada) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CuentaAsociada{" +
            "id=" + getId() +
            ", identificaciontitular='" + getIdentificaciontitular() + "'" +
            ", nombreTitular='" + getNombreTitular() + "'" +
            ", apellidoTitular='" + getApellidoTitular() + "'" +
            ", numeroCuenta='" + getNumeroCuenta() + "'" +
            ", tipoCuenta='" + getTipoCuenta() + "'" +
            ", codigoSeguridad='" + getCodigoSeguridad() + "'" +
            ", fechaVencimiento='" + getFechaVencimiento() + "'" +
            "}";
    }
}
