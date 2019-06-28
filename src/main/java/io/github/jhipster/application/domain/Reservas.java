package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import io.github.jhipster.application.domain.enumeration.EstadoReservad;

/**
 * A Reservas.
 */
@Entity
@Table(name = "reservas")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Reservas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "registro_fecha_entrada")
    private ZonedDateTime registroFechaEntrada;

    @Column(name = "registro_fecha_salida")
    private ZonedDateTime registroFechaSalida;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_reserva")
    private EstadoReservad estadoReserva;

    @OneToMany(mappedBy = "reservas")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RegistroCompra> registroCompras = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("reservas")
    private Miembros miembro;

    @ManyToOne
    @JsonIgnoreProperties("reservas")
    private EspaciosReserva espacio;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getRegistroFechaEntrada() {
        return registroFechaEntrada;
    }

    public Reservas registroFechaEntrada(ZonedDateTime registroFechaEntrada) {
        this.registroFechaEntrada = registroFechaEntrada;
        return this;
    }

    public void setRegistroFechaEntrada(ZonedDateTime registroFechaEntrada) {
        this.registroFechaEntrada = registroFechaEntrada;
    }

    public ZonedDateTime getRegistroFechaSalida() {
        return registroFechaSalida;
    }

    public Reservas registroFechaSalida(ZonedDateTime registroFechaSalida) {
        this.registroFechaSalida = registroFechaSalida;
        return this;
    }

    public void setRegistroFechaSalida(ZonedDateTime registroFechaSalida) {
        this.registroFechaSalida = registroFechaSalida;
    }

    public EstadoReservad getEstadoReserva() {
        return estadoReserva;
    }

    public Reservas estadoReserva(EstadoReservad estadoReserva) {
        this.estadoReserva = estadoReserva;
        return this;
    }

    public void setEstadoReserva(EstadoReservad estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public Set<RegistroCompra> getRegistroCompras() {
        return registroCompras;
    }

    public Reservas registroCompras(Set<RegistroCompra> registroCompras) {
        this.registroCompras = registroCompras;
        return this;
    }

    public Reservas addRegistroCompra(RegistroCompra registroCompra) {
        this.registroCompras.add(registroCompra);
        registroCompra.setReservas(this);
        return this;
    }

    public Reservas removeRegistroCompra(RegistroCompra registroCompra) {
        this.registroCompras.remove(registroCompra);
        registroCompra.setReservas(null);
        return this;
    }

    public void setRegistroCompras(Set<RegistroCompra> registroCompras) {
        this.registroCompras = registroCompras;
    }

    public Miembros getMiembro() {
        return miembro;
    }

    public Reservas miembro(Miembros miembros) {
        this.miembro = miembros;
        return this;
    }

    public void setMiembro(Miembros miembros) {
        this.miembro = miembros;
    }

    public EspaciosReserva getEspacio() {
        return espacio;
    }

    public Reservas espacio(EspaciosReserva espaciosReserva) {
        this.espacio = espaciosReserva;
        return this;
    }

    public void setEspacio(EspaciosReserva espaciosReserva) {
        this.espacio = espaciosReserva;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reservas)) {
            return false;
        }
        return id != null && id.equals(((Reservas) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Reservas{" +
            "id=" + getId() +
            ", registroFechaEntrada='" + getRegistroFechaEntrada() + "'" +
            ", registroFechaSalida='" + getRegistroFechaSalida() + "'" +
            ", estadoReserva='" + getEstadoReserva() + "'" +
            "}";
    }
}
