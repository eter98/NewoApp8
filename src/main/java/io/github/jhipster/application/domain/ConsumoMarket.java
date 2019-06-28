package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import io.github.jhipster.application.domain.enumeration.Impuestod;

/**
 * A ConsumoMarket.
 */
@Entity
@Table(name = "consumo_market")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ConsumoMarket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "total_consumido")
    private Integer totalConsumido;

    @Column(name = "registro_fecha_inicial")
    private LocalDate registroFechaInicial;

    @Column(name = "registro_fecha_final")
    private LocalDate registroFechaFinal;

    @Enumerated(EnumType.STRING)
    @Column(name = "impuesto")
    private Impuestod impuesto;

    @ManyToOne
    @JsonIgnoreProperties("consumoMarkets")
    private Miembros miembro;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalConsumido() {
        return totalConsumido;
    }

    public ConsumoMarket totalConsumido(Integer totalConsumido) {
        this.totalConsumido = totalConsumido;
        return this;
    }

    public void setTotalConsumido(Integer totalConsumido) {
        this.totalConsumido = totalConsumido;
    }

    public LocalDate getRegistroFechaInicial() {
        return registroFechaInicial;
    }

    public ConsumoMarket registroFechaInicial(LocalDate registroFechaInicial) {
        this.registroFechaInicial = registroFechaInicial;
        return this;
    }

    public void setRegistroFechaInicial(LocalDate registroFechaInicial) {
        this.registroFechaInicial = registroFechaInicial;
    }

    public LocalDate getRegistroFechaFinal() {
        return registroFechaFinal;
    }

    public ConsumoMarket registroFechaFinal(LocalDate registroFechaFinal) {
        this.registroFechaFinal = registroFechaFinal;
        return this;
    }

    public void setRegistroFechaFinal(LocalDate registroFechaFinal) {
        this.registroFechaFinal = registroFechaFinal;
    }

    public Impuestod getImpuesto() {
        return impuesto;
    }

    public ConsumoMarket impuesto(Impuestod impuesto) {
        this.impuesto = impuesto;
        return this;
    }

    public void setImpuesto(Impuestod impuesto) {
        this.impuesto = impuesto;
    }

    public Miembros getMiembro() {
        return miembro;
    }

    public ConsumoMarket miembro(Miembros miembros) {
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
        if (!(o instanceof ConsumoMarket)) {
            return false;
        }
        return id != null && id.equals(((ConsumoMarket) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ConsumoMarket{" +
            "id=" + getId() +
            ", totalConsumido=" + getTotalConsumido() +
            ", registroFechaInicial='" + getRegistroFechaInicial() + "'" +
            ", registroFechaFinal='" + getRegistroFechaFinal() + "'" +
            ", impuesto='" + getImpuesto() + "'" +
            "}";
    }
}
