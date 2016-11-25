/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author LEONDAVID
 */
@Embeddable
public class AnexoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigoRelacion")
    private String codigoRelacion;

    public AnexoPK() {
    }

    public AnexoPK(String codigo, String codigoRelacion) {
        this.codigo = codigo;
        this.codigoRelacion = codigoRelacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoRelacion() {
        return codigoRelacion;
    }

    public void setCodigoRelacion(String codigoRelacion) {
        this.codigoRelacion = codigoRelacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        hash += (codigoRelacion != null ? codigoRelacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnexoPK)) {
            return false;
        }
        AnexoPK other = (AnexoPK) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        if ((this.codigoRelacion == null && other.codigoRelacion != null) || (this.codigoRelacion != null && !this.codigoRelacion.equals(other.codigoRelacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.udea.model.AnexoPK[ codigo=" + codigo + ", codigoRelacion=" + codigoRelacion + " ]";
    }
    
}
