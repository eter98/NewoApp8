package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import io.github.jhipster.application.domain.enumeration.TipoEntradad;

import io.github.jhipster.application.domain.enumeration.TipoIngresod;

/**
 * A EntradaMiembros.
 */
@Entity
@Table(name = "entrada_miembros")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EntradaMiembros implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "registro_fecha")
    private ZonedDateTime registroFecha;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_entrada")
    private TipoEntradad tipoEntrada;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_ingreso")
    private TipoIngresod tipoIngreso;

    @Column(name = "tiempo_maximo")
    private Boolean tiempoMaximo;

    @OneToMany(mappedBy = "entradaMiembros")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RegistroCompra> registroCompras = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("entradaMiembros")
    private Miembros miembro;

    @ManyToOne
    @JsonIgnoreProperties("entradaMiembros")
    private EspacioLibre espacio;

    @ManyToOne
    @JsonIgnoreProperties("entradaMiembros")
    private EspaciosReserva espacioReserva;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getRegistroFecha() {
        return registroFecha;
    }

    public EntradaMiembros registroFecha(ZonedDateTime registroFecha) {
        this.registroFecha = registroFecha;
        return this;
    }

    public void setRegistroFecha(ZonedDateTime registroFecha) {
        this.registroFecha = registroFecha;
    }

    public TipoEntradad getTipoEntrada() {
        return tipoEntrada;
    }

    public EntradaMiembros tipoEntrada(TipoEntradad tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
        return this;
    }

    public void setTipoEntrada(TipoEntradad tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    public TipoIngresod getTipoIngreso() {
        return tipoIngreso;
    }

    public EntradaMiembros tipoIngreso(TipoIngresod tipoIngreso) {
        this.tipoIngreso = tipoIngreso;
        return this;
    }

    public void setTipoIngreso(TipoIngresod tipoIngreso) {
        this.tipoIngreso = tipoIngreso;
    }

    public Boolean isTiempoMaximo() {
        return tiempoMaximo;
    }

    public EntradaMiembros tiempoMaximo(Boolean tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
        return this;
    }

    public void setTiempoMaximo(Boolean tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
    }

    public Set<RegistroCompra> getRegistroCompras() {
        return registroCompras;
    }

    public EntradaMiembros registroCompras(Set<RegistroCompra> registroCompras) {
        this.registroCompras = registroCompras;
        return this;
    }

    public EntradaMiembros addRegistroCompra(RegistroCompra registroCompra) {
        this.registroCompras.add(registroCompra);
        registroCompra.setEntradaMiembros(this);
        return this;
    }

    public EntradaMiembros removeRegistroCompra(RegistroCompra registroCompra) {
        this.registroCompras.remove(registroCompra);
        registroCompra.setEntradaMiembros(null);
        return this;
    }

    public void setRegistroCompras(Set<RegistroCompra> registroCompras) {
        this.registroCompras = registroCompras;
    }

    public Miembros getMiembro() {
        return miembro;
    }

    public EntradaMiembros miembro(Miembros miembros) {
        this.miembro = miembros;
        return this;
    }

    public void setMiembro(Miembros miembros) {
        this.miembro = miembros;
    }

    public EspacioLibre getEspacio() {
        return espacio;
    }

    public EntradaMiembros espacio(EspacioLibre espacioLibre) {
        this.espacio = espacioLibre;
        return this;
    }

    public void setEspacio(EspacioLibre espacioLibre) {
        this.espacio = espacioLibre;
    }

    public EspaciosReserva getEspacioReserva() {
        return espacioReserva;
    }

    public EntradaMiembros espacioReserva(EspaciosReserva espaciosReserva) {
        this.espacioReserva = espaciosReserva;
        return this;
    }

    public void setEspacioReserva(EspaciosReserva espaciosReserva) {
        this.espacioReserva = espaciosReserva;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntradaMiembros)) {
            return false;
        }
        return id != null && id.equals(((EntradaMiembros) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EntradaMiembros{" +
            "id=" + getId() +
            ", registroFecha='" + getRegistroFecha() + "'" +
            ", tipoEntrada='" + getTipoEntrada() + "'" +
            ", tipoIngreso='" + getTipoIngreso() + "'" +
            ", tiempoMaximo='" + isTiempoMaximo() + "'" +
            "}";
    }
}
