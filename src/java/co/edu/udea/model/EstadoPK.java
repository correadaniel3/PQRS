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
public class EstadoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "numeroDocPersonal")
    private String numeroDocPersonal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "tipoDocPersonal")
    private String tipoDocPersonal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigoPQRS")
    private String codigoPQRS;

    public EstadoPK() {
    }

    public EstadoPK(String codigo, String numeroDocPersonal, String tipoDocPersonal, String codigoPQRS) {
        this.codigo = codigo;
        this.numeroDocPersonal = numeroDocPersonal;
        this.tipoDocPersonal = tipoDocPersonal;
        this.codigoPQRS = codigoPQRS;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNumeroDocPersonal() {
        return numeroDocPersonal;
    }

    public void setNumeroDocPersonal(String numeroDocPersonal) {
        this.numeroDocPersonal = numeroDocPersonal;
    }

    public String getTipoDocPersonal() {
        return tipoDocPersonal;
    }

    public void setTipoDocPersonal(String tipoDocPersonal) {
        this.tipoDocPersonal = tipoDocPersonal;
    }

    public String getCodigoPQRS() {
        return codigoPQRS;
    }

    public void setCodigoPQRS(String codigoPQRS) {
        this.codigoPQRS = codigoPQRS;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        hash += (numeroDocPersonal != null ? numeroDocPersonal.hashCode() : 0);
        hash += (tipoDocPersonal != null ? tipoDocPersonal.hashCode() : 0);
        hash += (codigoPQRS != null ? codigoPQRS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoPK)) {
            return false;
        }
        EstadoPK other = (EstadoPK) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        if ((this.numeroDocPersonal == null && other.numeroDocPersonal != null) || (this.numeroDocPersonal != null && !this.numeroDocPersonal.equals(other.numeroDocPersonal))) {
            return false;
        }
        if ((this.tipoDocPersonal == null && other.tipoDocPersonal != null) || (this.tipoDocPersonal != null && !this.tipoDocPersonal.equals(other.tipoDocPersonal))) {
            return false;
        }
        if ((this.codigoPQRS == null && other.codigoPQRS != null) || (this.codigoPQRS != null && !this.codigoPQRS.equals(other.codigoPQRS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.udea.model.EstadoPK[ codigo=" + codigo + ", numeroDocPersonal=" + numeroDocPersonal + ", tipoDocPersonal=" + tipoDocPersonal + ", codigoPQRS=" + codigoPQRS + " ]";
    }
    
}
