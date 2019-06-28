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
 * A EntradaInvitados.
 */
@Entity
@Table(name = "entrada_invitados")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EntradaInvitados implements Serializable {

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

    @OneToMany(mappedBy = "entradaInvitados")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RegistroCompra> registroCompras = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("entradaInvitados")
    private EspacioLibre espacio;

    @ManyToOne
    @JsonIgnoreProperties("entradaInvitados")
    private EspaciosReserva espacioReserva;

    @ManyToOne
    @JsonIgnoreProperties("entradaInvitados")
    private Invitados invitado;

    @ManyToOne
    @JsonIgnoreProperties("entradaInvitados")
    private Miembros miembro;

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

    public EntradaInvitados registroFecha(ZonedDateTime registroFecha) {
        this.registroFecha = registroFecha;
        return this;
    }

    public void setRegistroFecha(ZonedDateTime registroFecha) {
        this.registroFecha = registroFecha;
    }

    public TipoEntradad getTipoEntrada() {
        return tipoEntrada;
    }

    public EntradaInvitados tipoEntrada(TipoEntradad tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
        return this;
    }

    public void setTipoEntrada(TipoEntradad tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    public TipoIngresod getTipoIngreso() {
        return tipoIngreso;
    }

    public EntradaInvitados tipoIngreso(TipoIngresod tipoIngreso) {
        this.tipoIngreso = tipoIngreso;
        return this;
    }

    public void setTipoIngreso(TipoIngresod tipoIngreso) {
        this.tipoIngreso = tipoIngreso;
    }

    public Boolean isTiempoMaximo() {
        return tiempoMaximo;
    }

    public EntradaInvitados tiempoMaximo(Boolean tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
        return this;
    }

    public void setTiempoMaximo(Boolean tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
    }

    public Set<RegistroCompra> getRegistroCompras() {
        return registroCompras;
    }

    public EntradaInvitados registroCompras(Set<RegistroCompra> registroCompras) {
        this.registroCompras = registroCompras;
        return this;
    }

    public EntradaInvitados addRegistroCompra(RegistroCompra registroCompra) {
        this.registroCompras.add(registroCompra);
        registroCompra.setEntradaInvitados(this);
        return this;
    }

    public EntradaInvitados removeRegistroCompra(RegistroCompra registroCompra) {
        this.registroCompras.remove(registroCompra);
        registroCompra.setEntradaInvitados(null);
        return this;
    }

    public void setRegistroCompras(Set<RegistroCompra> registroCompras) {
        this.registroCompras = registroCompras;
    }

    public EspacioLibre getEspacio() {
        return espacio;
    }

    public EntradaInvitados espacio(EspacioLibre espacioLibre) {
        this.espacio = espacioLibre;
        return this;
    }

    public void setEspacio(EspacioLibre espacioLibre) {
        this.espacio = espacioLibre;
    }

    public EspaciosReserva getEspacioReserva() {
        return espacioReserva;
    }

    public EntradaInvitados espacioReserva(EspaciosReserva espaciosReserva) {
        this.espacioReserva = espaciosReserva;
        return this;
    }

    public void setEspacioReserva(EspaciosReserva espaciosReserva) {
        this.espacioReserva = espaciosReserva;
    }

    public Invitados getInvitado() {
        return invitado;
    }

    public EntradaInvitados invitado(Invitados invitados) {
        this.invitado = invitados;
        return this;
    }

    public void setInvitado(Invitados invitados) {
        this.invitado = invitados;
    }

    public Miembros getMiembro() {
        return miembro;
    }

    public EntradaInvitados miembro(Miembros miembros) {
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
        if (!(o instanceof EntradaInvitados)) {
            return false;
        }
        return id != null && id.equals(((EntradaInvitados) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EntradaInvitados{" +
            "id=" + getId() +
            ", registroFecha='" + getRegistroFecha() + "'" +
            ", tipoEntrada='" + getTipoEntrada() + "'" +
            ", tipoIngreso='" + getTipoIngreso() + "'" +
            ", tiempoMaximo='" + isTiempoMaximo() + "'" +
            "}";
    }
}
