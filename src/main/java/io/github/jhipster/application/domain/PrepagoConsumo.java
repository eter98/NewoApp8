package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A PrepagoConsumo.
 */
@Entity
@Table(name = "prepago_consumo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PrepagoConsumo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "aporte")
    private Integer aporte;

    @Column(name = "saldo_actual")
    private Integer saldoActual;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @Column(name = "fecha_saldo_actual")
    private LocalDate fechaSaldoActual;

    @ManyToOne
    @JsonIgnoreProperties("prepagoConsumos")
    private Miembros miembro;

    @ManyToOne
    @JsonIgnoreProperties("prepagoConsumos")
    private TipoPrepagoConsumo tipoPrepago;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAporte() {
        return aporte;
    }

    public PrepagoConsumo aporte(Integer aporte) {
        this.aporte = aporte;
        return this;
    }

    public void setAporte(Integer aporte) {
        this.aporte = aporte;
    }

    public Integer getSaldoActual() {
        return saldoActual;
    }

    public PrepagoConsumo saldoActual(Integer saldoActual) {
        this.saldoActual = saldoActual;
        return this;
    }

    public void setSaldoActual(Integer saldoActual) {
        this.saldoActual = saldoActual;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public PrepagoConsumo fechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
        return this;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDate getFechaSaldoActual() {
        return fechaSaldoActual;
    }

    public PrepagoConsumo fechaSaldoActual(LocalDate fechaSaldoActual) {
        this.fechaSaldoActual = fechaSaldoActual;
        return this;
    }

    public void setFechaSaldoActual(LocalDate fechaSaldoActual) {
        this.fechaSaldoActual = fechaSaldoActual;
    }

    public Miembros getMiembro() {
        return miembro;
    }

    public PrepagoConsumo miembro(Miembros miembros) {
        this.miembro = miembros;
        return this;
    }

    public void setMiembro(Miembros miembros) {
        this.miembro = miembros;
    }

    public TipoPrepagoConsumo getTipoPrepago() {
        return tipoPrepago;
    }

    public PrepagoConsumo tipoPrepago(TipoPrepagoConsumo tipoPrepagoConsumo) {
        this.tipoPrepago = tipoPrepagoConsumo;
        return this;
    }

    public void setTipoPrepago(TipoPrepagoConsumo tipoPrepagoConsumo) {
        this.tipoPrepago = tipoPrepagoConsumo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrepagoConsumo)) {
            return false;
        }
        return id != null && id.equals(((PrepagoConsumo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PrepagoConsumo{" +
            "id=" + getId() +
            ", aporte=" + getAporte() +
            ", saldoActual=" + getSaldoActual() +
            ", fechaRegistro='" + getFechaRegistro() + "'" +
            ", fechaSaldoActual='" + getFechaSaldoActual() + "'" +
            "}";
    }
}
