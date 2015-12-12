/*
 * Copyright (C) 2015 dacopan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.dacopancm.jfee.sp.model;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dacopan
 */
@Entity
@Table(name = "personal", catalog = "jfee"
)
public class Personal implements java.io.Serializable {

    private Integer psnId;
    private Usuario usuario;
    private String psnNombre;
    private String psnApellido;
    private Integer psnGenero;
    private Date psnFechaNac;
    private String psnTelefono;
    private String psnCelular;
    private String psnDireccion;

    public Personal() {
    }

    public Personal(Usuario usuario, String psnNombre, String psnApellido, Integer psnGenero, Date psnFechaNac, String psnTelefono, String psnCelular, String psnDireccion) {
        this.usuario = usuario;
        this.psnNombre = psnNombre;
        this.psnApellido = psnApellido;
        this.psnGenero = psnGenero;
        this.psnFechaNac = psnFechaNac;
        this.psnTelefono = psnTelefono;
        this.psnCelular = psnCelular;
        this.psnDireccion = psnDireccion;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "PSN_ID", unique = true, nullable = false)
    public Integer getPsnId() {
        return this.psnId;
    }

    public void setPsnId(Integer psnId) {
        this.psnId = psnId;
    }

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "USR_ID")
    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Column(name = "PSN_NOMBRE", length = 65535)
    public String getPsnNombre() {
        return this.psnNombre;
    }

    public void setPsnNombre(String psnNombre) {
        this.psnNombre = psnNombre;
    }

    @Column(name = "PSN_APELLIDO", length = 65535)
    public String getPsnApellido() {
        return this.psnApellido;
    }

    public void setPsnApellido(String psnApellido) {
        this.psnApellido = psnApellido;
    }

    @Column(name = "PSN_GENERO")
    public Integer getPsnGenero() {
        return this.psnGenero;
    }

    public void setPsnGenero(Integer psnGenero) {
        this.psnGenero = psnGenero;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "PSN_FECHA_NAC", length = 10)
    public Date getPsnFechaNac() {
        return this.psnFechaNac;
    }

    public void setPsnFechaNac(Date psnFechaNac) {
        this.psnFechaNac = psnFechaNac;
    }

    @Column(name = "PSN_TELEFONO", length = 65535)
    public String getPsnTelefono() {
        return this.psnTelefono;
    }

    public void setPsnTelefono(String psnTelefono) {
        this.psnTelefono = psnTelefono;
    }

    @Column(name = "PSN_CELULAR", length = 65535)
    public String getPsnCelular() {
        return this.psnCelular;
    }

    public void setPsnCelular(String psnCelular) {
        this.psnCelular = psnCelular;
    }

    @Column(name = "PSN_DIRECCION", length = 65535)
    public String getPsnDireccion() {
        return this.psnDireccion;
    }

    public void setPsnDireccion(String psnDireccion) {
        this.psnDireccion = psnDireccion;
    }

}
