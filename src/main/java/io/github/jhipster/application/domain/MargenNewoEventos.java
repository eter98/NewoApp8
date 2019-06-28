package io.github.jhipster.application.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A MargenNewoEventos.
 */
@Entity
@Table(name = "margen_newo_eventos")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MargenNewoEventos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "porcentaje_margen")
    private Integer porcentajeMargen;

    @OneToOne
    @JoinColumn(unique = true)
    private Evento evento;

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

    public MargenNewoEventos porcentajeMargen(Integer porcentajeMargen) {
        this.porcentajeMargen = porcentajeMargen;
        return this;
    }

    public void setPorcentajeMargen(Integer porcentajeMargen) {
        this.porcentajeMargen = porcentajeMargen;
    }

    public Evento getEvento() {
        return evento;
    }

    public MargenNewoEventos evento(Evento evento) {
        this.evento = evento;
        return this;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MargenNewoEventos)) {
            return false;
        }
        return id != null && id.equals(((MargenNewoEventos) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MargenNewoEventos{" +
            "id=" + getId() +
            ", porcentajeMargen=" + getPorcentajeMargen() +
            "}";
    }
}
