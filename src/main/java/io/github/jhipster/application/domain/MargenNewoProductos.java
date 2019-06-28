package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A MargenNewoProductos.
 */
@Entity
@Table(name = "margen_newo_productos")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MargenNewoProductos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "porcentaje_margen")
    private Integer porcentajeMargen;

    @ManyToOne
    @JsonIgnoreProperties("margenNewoProductos")
    private ProductosServicios producto;

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

    public MargenNewoProductos porcentajeMargen(Integer porcentajeMargen) {
        this.porcentajeMargen = porcentajeMargen;
        return this;
    }

    public void setPorcentajeMargen(Integer porcentajeMargen) {
        this.porcentajeMargen = porcentajeMargen;
    }

    public ProductosServicios getProducto() {
        return producto;
    }

    public MargenNewoProductos producto(ProductosServicios productosServicios) {
        this.producto = productosServicios;
        return this;
    }

    public void setProducto(ProductosServicios productosServicios) {
        this.producto = productosServicios;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MargenNewoProductos)) {
            return false;
        }
        return id != null && id.equals(((MargenNewoProductos) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MargenNewoProductos{" +
            "id=" + getId() +
            ", porcentajeMargen=" + getPorcentajeMargen() +
            "}";
    }
}
