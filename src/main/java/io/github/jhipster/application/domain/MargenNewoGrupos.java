package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A MargenNewoGrupos.
 */
@Entity
@Table(name = "margen_newo_grupos")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MargenNewoGrupos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "porcentaje_margen")
    private Integer porcentajeMargen;

    @ManyToOne
    @JsonIgnoreProperties("margenNewoGrupos")
    private Grupos grupo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPorcentajeMargen() {
        return porcentajeMargen;
    }

    public MargenNewoGrupos porcentajeMargen(Integer porcentajeMargen) {
        this.porcentajeMargen = porcentajeMargen;
        return this;
    }

    public void setPorcentajeMargen(Integer porcentajeMargen) {
        this.porcentajeMargen = porcentajeMargen;
    }

    public Grupos getGrupo() {
        return grupo;
    }

    public MargenNewoGrupos grupo(Grupos grupos) {
        this.grupo = grupos;
        return this;
    }

    public void setGrupo(Grupos grupos) {
        this.grupo = grupos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MargenNewoGrupos)) {
            return false;
        }
        return id != null && id.equals(((MargenNewoGrupos) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MargenNewoGrupos{" +
            "id=" + getId() +
            ", porcentajeMargen=" + getPorcentajeMargen() +
            "}";
    }
}
