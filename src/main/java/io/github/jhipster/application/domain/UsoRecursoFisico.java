package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

import io.github.jhipster.application.domain.enumeration.TipoIniciod;

/**
 * A UsoRecursoFisico.
 */
@Entity
@Table(name = "uso_recurso_fisico")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UsoRecursoFisico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "registro_fecha_inicio")
    private ZonedDateTime registroFechaInicio;

    @Column(name = "registro_fecha_final")
    private ZonedDateTime registroFechaFinal;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_registro")
    private TipoIniciod tipoRegistro;

    @ManyToOne
    @JsonIgnoreProperties("usoRecursoFisicos")
    private RecursosFisicos recurso;

    @ManyToOne
    @JsonIgnoreProperties("usoRecursoFisicos")
    private Miembros miembro;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getRegistroFechaInicio() {
        return registroFechaInicio;
    }

    public UsoRecursoFisico registroFechaInicio(ZonedDateTime registroFechaInicio) {
        this.registroFechaInicio = registroFechaInicio;
        return this;
    }

    public void setRegistroFechaInicio(ZonedDateTime registroFechaInicio) {
        this.registroFechaInicio = registroFechaInicio;
    }

    public ZonedDateTime getRegistroFechaFinal() {
        return registroFechaFinal;
    }

    public UsoRecursoFisico registroFechaFinal(ZonedDateTime registroFechaFinal) {
        this.registroFechaFinal = registroFechaFinal;
        return this;
    }

    public void setRegistroFechaFinal(ZonedDateTime registroFechaFinal) {
        this.registroFechaFinal = registroFechaFinal;
    }

    public TipoIniciod getTipoRegistro() {
        return tipoRegistro;
    }

    public UsoRecursoFisico tipoRegistro(TipoIniciod tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
        return this;
    }

    public void setTipoRegistro(TipoIniciod tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public RecursosFisicos getRecurso() {
        return recurso;
    }

    public UsoRecursoFisico recurso(RecursosFisicos recursosFisicos) {
        this.recurso = recursosFisicos;
        return this;
    }

    public void setRecurso(RecursosFisicos recursosFisicos) {
        this.recurso = recursosFisicos;
    }

    public Miembros getMiembro() {
        return miembro;
    }

    public UsoRecursoFisico miembro(Miembros miembros) {
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
        if (!(o instanceof UsoRecursoFisico)) {
            return false;
        }
        return id != null && id.equals(((UsoRecursoFisico) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UsoRecursoFisico{" +
            "id=" + getId() +
            ", registroFechaInicio='" + getRegistroFechaInicio() + "'" +
            ", registroFechaFinal='" + getRegistroFechaFinal() + "'" +
            ", tipoRegistro='" + getTipoRegistro() + "'" +
            "}";
    }
}
