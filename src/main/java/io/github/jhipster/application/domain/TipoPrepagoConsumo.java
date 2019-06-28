package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TipoPrepagoConsumo.
 */
@Entity
@Table(name = "tipo_prepago_consumo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TipoPrepagoConsumo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "valor_minimo")
    private Integer valorMinimo;

    @Column(name = "valor_maximo")
    private Integer valorMaximo;

    @ManyToOne
    @JsonIgnoreProperties("tipoPrepagoConsumos")
    private Beneficio tipoBeneficio;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoPrepagoConsumo nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public TipoPrepagoConsumo descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getValorMinimo() {
        return valorMinimo;
    }

    public TipoPrepagoConsumo valorMinimo(Integer valorMinimo) {
        this.valorMinimo = valorMinimo;
        return this;
    }

    public void setValorMinimo(Integer valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public Integer getValorMaximo() {
        return valorMaximo;
    }

    public TipoPrepagoConsumo valorMaximo(Integer valorMaximo) {
        this.valorMaximo = valorMaximo;
        return this;
    }

    public void setValorMaximo(Integer valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public Beneficio getTipoBeneficio() {
        return tipoBeneficio;
    }

    public TipoPrepagoConsumo tipoBeneficio(Beneficio beneficio) {
        this.tipoBeneficio = beneficio;
        return this;
    }

    public void setTipoBeneficio(Beneficio beneficio) {
        this.tipoBeneficio = beneficio;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TipoPrepagoConsumo)) {
            return false;
        }
        return id != null && id.equals(((TipoPrepagoConsumo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TipoPrepagoConsumo{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", valorMinimo=" + getValorMinimo() +
            ", valorMaximo=" + getValorMaximo() +
            "}";
    }
}
