package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A MiembrosEquipoEmpresas.
 */
@Entity
@Table(name = "miembros_equipo_empresas")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MiembrosEquipoEmpresas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("miembrosEquipoEmpresas")
    private Empresa empresa;

    @ManyToOne
    @JsonIgnoreProperties("miembrosEquipoEmpresas")
    private EquipoEmpresas equipo;

    @ManyToOne
    @JsonIgnoreProperties("miembrosEquipoEmpresas")
    private Miembros miembro;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public MiembrosEquipoEmpresas empresa(Empresa empresa) {
        this.empresa = empresa;
        return this;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public EquipoEmpresas getEquipo() {
        return equipo;
    }

    public MiembrosEquipoEmpresas equipo(EquipoEmpresas equipoEmpresas) {
        this.equipo = equipoEmpresas;
        return this;
    }

    public void setEquipo(EquipoEmpresas equipoEmpresas) {
        this.equipo = equipoEmpresas;
    }

    public Miembros getMiembro() {
        return miembro;
    }

    public MiembrosEquipoEmpresas miembro(Miembros miembros) {
        this.miembro = miembros;
        return this;
    }

    public void setMiembro(Miembros miembros) {
        this.miembro = miembros;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MiembrosEquipoEmpresas)) {
            return false;
        }
        return id != null && id.equals(((MiembrosEquipoEmpresas) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MiembrosEquipoEmpresas{" +
            "id=" + getId() +
            "}";
    }
}
