package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A RegistroCompra.
 */
@Entity
@Table(name = "registro_compra")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RegistroCompra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "consumo_market")
    private Boolean consumoMarket;

    @ManyToOne
    @JsonIgnoreProperties("registroCompras")
    private Miembros miembro;

    @ManyToOne
    @JsonIgnoreProperties("registroCompras")
    private Facturacion facturacion;

    @ManyToOne
    @JsonIgnoreProperties("registroCompras")
    private Reservas reservas;

    @ManyToOne
    @JsonIgnoreProperties("registroCompras")
    private EntradaInvitados entradaInvitados;

    @ManyToOne
    @JsonIgnoreProperties("registroCompras")
    private EntradaMiembros entradaMiembros;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isConsumoMarket() {
        return consumoMarket;
    }

    public RegistroCompra consumoMarket(Boolean consumoMarket) {
        this.consumoMarket = consumoMarket;
        return this;
    }

    public void setConsumoMarket(Boolean consumoMarket) {
        this.consumoMarket = consumoMarket;
    }

    public Miembros getMiembro() {
        return miembro;
    }

    public RegistroCompra miembro(Miembros miembros) {
        this.miembro = miembros;
        return this;
    }

    public void setMiembro(Miembros miembros) {
        this.miembro = miembros;
    }

    public Facturacion getFacturacion() {
        return facturacion;
    }

    public RegistroCompra facturacion(Facturacion facturacion) {
        this.facturacion = facturacion;
        return this;
    }

    public void setFacturacion(Facturacion facturacion) {
        this.facturacion = facturacion;
    }

    public Reservas getReservas() {
        return reservas;
    }

    public RegistroCompra reservas(Reservas reservas) {
        this.reservas = reservas;
        return this;
    }

    public void setReservas(Reservas reservas) {
        this.reservas = reservas;
    }

    public EntradaInvitados getEntradaInvitados() {
        return entradaInvitados;
    }

    public RegistroCompra entradaInvitados(EntradaInvitados entradaInvitados) {
        this.entradaInvitados = entradaInvitados;
        return this;
    }

    public void setEntradaInvitados(EntradaInvitados entradaInvitados) {
        this.entradaInvitados = entradaInvitados;
    }

    public EntradaMiembros getEntradaMiembros() {
        return entradaMiembros;
    }

    public RegistroCompra entradaMiembros(EntradaMiembros entradaMiembros) {
        this.entradaMiembros = entradaMiembros;
        return this;
    }

    public void setEntradaMiembros(EntradaMiembros entradaMiembros) {
        this.entradaMiembros = entradaMiembros;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegistroCompra)) {
            return false;
        }
        return id != null && id.equals(((RegistroCompra) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "RegistroCompra{" +
            "id=" + getId() +
            ", consumoMarket='" + isConsumoMarket() + "'" +
            "}";
    }
}
