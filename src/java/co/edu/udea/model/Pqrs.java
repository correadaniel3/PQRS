/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.model;

import co.edu.udea.util.DateUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LEONDAVID
 */
@Entity
@Table(name = "pqrs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pqrs.findAll", query = "SELECT p FROM Pqrs p"),
    @NamedQuery(name = "Pqrs.findByCodigo", query = "SELECT p FROM Pqrs p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Pqrs.findByDescripcion", query = "SELECT p FROM Pqrs p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Pqrs.findByTipo", query = "SELECT p FROM Pqrs p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "Pqrs.findByFechaPQRS", query = "SELECT p FROM Pqrs p WHERE p.fechaPQRS = :fechaPQRS"),
    @NamedQuery(name = "Pqrs.findByFechaHechos", query = "SELECT p FROM Pqrs p WHERE p.fechaHechos = :fechaHechos"),
    @NamedQuery(name = "Pqrs.findByCodigoAeropuerto", query = "SELECT p FROM Pqrs p WHERE p.codigoAeropuerto = :codigoAeropuerto")})
public class Pqrs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaPQRS")
    @Temporal(TemporalType.DATE)
    private Date fechaPQRS;
    @Column(name = "fechaHechos")
    @Temporal(TemporalType.DATE)
    private Date fechaHechos;
    @Size(max = 10)
    @Column(name = "codigoAeropuerto")
    private String codigoAeropuerto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pqrs")
    private List<Estado> estadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pqrs")
    private List<Anexo> anexoList;
    @JoinColumns({
        @JoinColumn(name = "tipoDocCliente", referencedColumnName = "tipoDocumento"),
        @JoinColumn(name = "numeroDocCliente", referencedColumnName = "numeroDocumento")})
    @ManyToOne
    private Cliente cliente;

    public Pqrs() {
    }

    public Pqrs(String codigo) {
        this.codigo = codigo;
    }

    public Pqrs(String codigo, String descripcion, String tipo, Date fechaPQRS) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.fechaPQRS = fechaPQRS;
    }
    public String getDate(){
        if(fechaHechos==null){
            return "No especificada";
        }
        return DateUtil.formatDate(fechaHechos);
    }
    public String getDateP(){
        return DateUtil.formatDate(fechaPQRS);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaPQRS() {
        return fechaPQRS;
    }

    public void setFechaPQRS(Date fechaPQRS) {
        this.fechaPQRS = fechaPQRS;
    }

    public Date getFechaHechos() {
        return fechaHechos;
    }

    public void setFechaHechos(Date fechaHechos) {
        this.fechaHechos = fechaHechos;
    }

    public String getCodigoAeropuerto() {
        return codigoAeropuerto;
    }

    public void setCodigoAeropuerto(String codigoAeropuerto) {
        this.codigoAeropuerto = codigoAeropuerto;
    }

    @XmlTransient
    public List<Estado> getEstadoList() {
        return estadoList;
    }

    public void setEstadoList(List<Estado> estadoList) {
        this.estadoList = estadoList;
    }

    @XmlTransient
    public List<Anexo> getAnexoList() {
        return anexoList;
    }

    public void setAnexoList(List<Anexo> anexoList) {
        this.anexoList = anexoList;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pqrs)) {
            return false;
        }
        Pqrs other = (Pqrs) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.udea.model.Pqrs[ codigo=" + codigo + " ]";
    }
    
}
