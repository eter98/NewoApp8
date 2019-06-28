package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import io.github.jhipster.application.domain.enumeration.TipoDocumentod;

import io.github.jhipster.application.domain.enumeration.Generod;

/**
 * A Miembros.
 */
@Entity
@Table(name = "miembros")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Miembros implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "nacionalidad")
    private String nacionalidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento")
    private TipoDocumentod tipoDocumento;

    @Column(name = "identificacion")
    private Integer identificacion;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero")
    private Generod genero;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    @Column(name = "celular")
    private String celular;

    @Column(name = "derechos_de_compra")
    private Boolean derechosDeCompra;

    @Column(name = "acceso_ilimitado")
    private Boolean accesoIlimitado;

    @Column(name = "aliado")
    private Boolean aliado;

    @Column(name = "host")
    private Boolean host;

    @ManyToOne
    @JsonIgnoreProperties("miembros")
    private User miembro;

    @ManyToOne
    @JsonIgnoreProperties("miembros")
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

    public Miembros nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Miembros apellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public Miembros nacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
        return this;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public TipoDocumentod getTipoDocumento() {
        return tipoDocumento;
    }

    public Miembros tipoDocumento(TipoDocumentod tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
        return this;
    }

    public void setTipoDocumento(TipoDocumentod tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Integer getIdentificacion() {
        return identificacion;
    }

    public Miembros identificacion(Integer identificacion) {
        this.identificacion = identificacion;
        return this;
    }

    public void setIdentificacion(Integer identificacion) {
        this.identificacion = identificacion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Miembros fechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public Miembros fechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
        return this;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Generod getGenero() {
        return genero;
    }

    public Miembros genero(Generod genero) {
        this.genero = genero;
        return this;
    }

    public void setGenero(Generod genero) {
        this.genero = genero;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public Miembros correoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
        return this;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getCelular() {
        return celular;
    }

    public Miembros celular(String celular) {
        this.celular = celular;
        return this;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Boolean isDerechosDeCompra() {
        return derechosDeCompra;
    }

    public Miembros derechosDeCompra(Boolean derechosDeCompra) {
        this.derechosDeCompra = derechosDeCompra;
        return this;
    }

    public void setDerechosDeCompra(Boolean derechosDeCompra) {
        this.derechosDeCompra = derechosDeCompra;
    }

    public Boolean isAccesoIlimitado() {
        return accesoIlimitado;
    }

    public Miembros accesoIlimitado(Boolean accesoIlimitado) {
        this.accesoIlimitado = accesoIlimitado;
        return this;
    }

    public void setAccesoIlimitado(Boolean accesoIlimitado) {
        this.accesoIlimitado = accesoIlimitado;
    }

    public Boolean isAliado() {
        return aliado;
    }

    public Miembros aliado(Boolean aliado) {
        this.aliado = aliado;
        return this;
    }

    public void setAliado(Boolean aliado) {
        this.aliado = aliado;
    }

    public Boolean isHost() {
        return host;
    }

    public Miembros host(Boolean host) {
        this.host = host;
        return this;
    }

    public void setHost(Boolean host) {
        this.host = host;
    }

    public User getMiembro() {
        return miembro;
    }

    public Miembros miembro(User user) {
        this.miembro = user;
        return this;
    }

    public void setMiembro(User user) {
        this.miembro = user;
    }

    public Sedes getSede() {
        return sede;
    }

    public Miembros sede(Sedes sedes) {
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
        if (!(o instanceof Miembros)) {
            return false;
        }
        return id != null && id.equals(((Miembros) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Miembros{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", nacionalidad='" + getNacionalidad() + "'" +
            ", tipoDocumento='" + getTipoDocumento() + "'" +
            ", identificacion=" + getIdentificacion() +
            ", fechaNacimiento='" + getFechaNacimiento() + "'" +
            ", fechaRegistro='" + getFechaRegistro() + "'" +
            ", genero='" + getGenero() + "'" +
            ", correoElectronico='" + getCorreoElectronico() + "'" +
            ", celular='" + getCelular() + "'" +
            ", derechosDeCompra='" + isDerechosDeCompra() + "'" +
            ", accesoIlimitado='" + isAccesoIlimitado() + "'" +
            ", aliado='" + isAliado() + "'" +
            ", host='" + isHost() + "'" +
            "}";
    }
}
