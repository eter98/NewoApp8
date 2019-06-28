package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A EquipoEmpresas.
 */
@Entity
@Table(name = "equipo_empresas")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EquipoEmpresas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "correo")
    private String correo;

    @Column(name = "direccion")
    private String direccion;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "descripcion")
    private String descripcion;

    @Lob
    @Column(name = "logos")
    private byte[] logos;

    @Column(name = "logos_content_type")
    private String logosContentType;

    @Column(name = "pagina_web")
    private String paginaWeb;

    @ManyToOne
    @JsonIgnoreProperties("equipoEmpresas")
    private User usuario;

    @ManyToOne
    @JsonIgnoreProperties("equipoEmpresas")
    private Miembros miembro;

    @ManyToOne
    @JsonIgnoreProperties("equipoEmpresas")
    private Empresa empresa;

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

    public EquipoEmpresas nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public EquipoEmpresas telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public EquipoEmpresas correo(String correo) {
        this.correo = correo;
        return this;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public EquipoEmpresas direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public EquipoEmpresas descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getLogos() {
        return logos;
    }

    public EquipoEmpresas logos(byte[] logos) {
        this.logos = logos;
        return this;
    }

    public void setLogos(byte[] logos) {
        this.logos = logos;
    }

    public String getLogosContentType() {
        return logosContentType;
    }

    public EquipoEmpresas logosContentType(String logosContentType) {
        this.logosContentType = logosContentType;
        return this;
    }

    public void setLogosContentType(String logosContentType) {
        this.logosContentType = logosContentType;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public EquipoEmpresas paginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
        return this;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public User getUsuario() {
        return usuario;
    }

    public EquipoEmpresas usuario(User user) {
        this.usuario = user;
        return this;
    }

    public void setUsuario(User user) {
        this.usuario = user;
    }

    public Miembros getMiembro() {
        return miembro;
    }

    public EquipoEmpresas miembro(Miembros miembros) {
        this.miembro = miembros;
        return this;
    }

    public void setMiembro(Miembros miembros) {
        this.miembro = miembros;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public EquipoEmpresas empresa(Empresa empresa) {
        this.empresa = empresa;
        return this;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EquipoEmpresas)) {
            return false;
        }
        return id != null && id.equals(((EquipoEmpresas) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EquipoEmpresas{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", correo='" + getCorreo() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", logos='" + getLogos() + "'" +
            ", logosContentType='" + getLogosContentType() + "'" +
            ", paginaWeb='" + getPaginaWeb() + "'" +
            "}";
    }
}
