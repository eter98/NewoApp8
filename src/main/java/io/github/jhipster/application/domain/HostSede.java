package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A HostSede.
 */
@Entity
@Table(name = "host_sede")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HostSede implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("hostSedes")
    private Sedes sede;

    @ManyToOne
    @JsonIgnoreProperties("hostSedes")
    private Miembros miembro;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sedes getSede() {
        return sede;
    }

    public HostSede sede(Sedes sedes) {
        this.sede = sedes;
        return this;
    }

    public void setSede(Sedes sedes) {
        this.sede = sedes;
    }

    public Miembros getMiembro() {
        return miembro;
    }

    public HostSede miembro(Miembros miembros) {
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
        if (!(o instanceof HostSede)) {
            return false;
        }
        return id != null && id.equals(((HostSede) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "HostSede{" +
            "id=" + getId() +
            "}";
    }
}
