/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LEONDAVID
 */
@Entity
@Table(name = "personal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personal.findAll", query = "SELECT p FROM Personal p"),
    @NamedQuery(name = "Personal.findByTipoDocumento", query = "SELECT p FROM Personal p WHERE p.personalPK.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "Personal.findByNumeroDocumento", query = "SELECT p FROM Personal p WHERE p.personalPK.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "Personal.findByNombres", query = "SELECT p FROM Personal p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "Personal.findByApellidos", query = "SELECT p FROM Personal p WHERE p.apellidos = :apellidos"),
    @NamedQuery(name = "Personal.findByTelefono", query = "SELECT p FROM Personal p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Personal.findByCorreoElectronico", query = "SELECT p FROM Personal p WHERE p.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "Personal.findByContrase\u00f1a", query = "SELECT p FROM Personal p WHERE p.contrase\u00f1a = :contrase\u00f1a")})
public class Personal implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonalPK personalPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "correoElectronico")
    private String correoElectronico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "contrase\u00f1a")
    private String contraseña;
    @JoinColumn(name = "codigoDepartamento", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Departamento codigoDepartamento;

    public Personal() {
    }

    public Personal(PersonalPK personalPK) {
        this.personalPK = personalPK;
    }

    public Personal(PersonalPK personalPK, String nombres, String apellidos, String telefono, String correoElectronico, String contraseña) {
        this.personalPK = personalPK;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
    }

    public Personal(String tipoDocumento, String numeroDocumento) {
        this.personalPK = new PersonalPK(tipoDocumento, numeroDocumento);
    }

    public PersonalPK getPersonalPK() {
        return personalPK;
    }

    public void setPersonalPK(PersonalPK personalPK) {
        this.personalPK = personalPK;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Departamento getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(Departamento codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personalPK != null ? personalPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personal)) {
            return false;
        }
        Personal other = (Personal) object;
        if ((this.personalPK == null && other.personalPK != null) || (this.personalPK != null && !this.personalPK.equals(other.personalPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.udea.model.Personal[ personalPK=" + personalPK + " ]";
    }
    
}
