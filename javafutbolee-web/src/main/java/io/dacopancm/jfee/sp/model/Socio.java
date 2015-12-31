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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dacopan
 */
@Entity
@Table(name = "socio")
public class Socio implements java.io.Serializable {

    private Integer socId;
    private Ciudad ciudad;
    private FormaPago formaPago;
    private Plan plan;
    private Usuario usuario;
    private String socNombre;
    private String socApellido;
    private Date socFechaNac;
    private Integer socGenero;
    private String socLugarNac;
    private String socTipoSangre;
    private Integer socNumHijos;
    private Boolean socTrabaja;
    private String socDireccion;
    private String socDireccionRef;
    private String socTelefono;
    private String socCelular;
    private String socNum;
    private Boolean socEstado;

    public Socio() {
    }

    public Socio(Integer socId, Ciudad ciudad, FormaPago formaPago, Plan plan, Usuario usuario, String socNombre, String socApellido, Date socFechaNac, Integer socGenero, String socLugarNac, String socTipoSangre, Integer socNumHijos, Boolean socTrabaja, String socDireccion, String socDireccionRef, String socTelefono, String socCelular, String socNum, Boolean socEstado) {
        this.socId = socId;
        this.ciudad = ciudad;
        this.formaPago = formaPago;
        this.plan = plan;
        this.usuario = usuario;
        this.socNombre = socNombre;
        this.socApellido = socApellido;
        this.socFechaNac = socFechaNac;
        this.socGenero = socGenero;
        this.socLugarNac = socLugarNac;
        this.socTipoSangre = socTipoSangre;
        this.socNumHijos = socNumHijos;
        this.socTrabaja = socTrabaja;
        this.socDireccion = socDireccion;
        this.socDireccionRef = socDireccionRef;
        this.socTelefono = socTelefono;
        this.socCelular = socCelular;
        this.socNum = socNum;
        this.socEstado = socEstado;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "SOC_ID", unique = true, nullable = false)
    public Integer getSocId() {
        return this.socId;
    }

    public void setSocId(Integer socId) {
        this.socId = socId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CIU_ID")
    public Ciudad getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FRM_PAG_ID")
    public FormaPago getFormaPago() {
        return this.formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLN_ID")
    public Plan getPlan() {
        return this.plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USR_ID")
    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Column(name = "SOC_NOMBRE", length = 65535)
    public String getSocNombre() {
        return this.socNombre;
    }

    public void setSocNombre(String socNombre) {
        this.socNombre = socNombre;
    }

    @Column(name = "SOC_APELLIDO", length = 65535)
    public String getSocApellido() {
        return this.socApellido;
    }

    public void setSocApellido(String socApellido) {
        this.socApellido = socApellido;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "SOC_FECHA_NAC", length = 10)
    public Date getSocFechaNac() {
        return this.socFechaNac;
    }

    public void setSocFechaNac(Date socFechaNac) {
        this.socFechaNac = socFechaNac;
    }

    @Column(name = "SOC_GENERO")
    public Integer getSocGenero() {
        return this.socGenero;
    }

    public void setSocGenero(Integer socGenero) {
        this.socGenero = socGenero;
    }

    @Column(name = "SOC_LUGAR_NAC", length = 65535)
    public String getSocLugarNac() {
        return this.socLugarNac;
    }

    public void setSocLugarNac(String socLugarNac) {
        this.socLugarNac = socLugarNac;
    }

    @Column(name = "SOC_TIPO_SANGRE", length = 65535)
    public String getSocTipoSangre() {
        return this.socTipoSangre;
    }

    public void setSocTipoSangre(String socTipoSangre) {
        this.socTipoSangre = socTipoSangre;
    }

    @Column(name = "SOC_NUM_HIJOS")
    public Integer getSocNumHijos() {
        return this.socNumHijos;
    }

    public void setSocNumHijos(Integer socNumHijos) {
        this.socNumHijos = socNumHijos;
    }

    @Column(name = "SOC_TRABAJA")
    public Boolean getSocTrabaja() {
        return this.socTrabaja;
    }

    public void setSocTrabaja(Boolean socTrabaja) {
        this.socTrabaja = socTrabaja;
    }

    @Column(name = "SOC_DIRECCION", length = 65535)
    public String getSocDireccion() {
        return this.socDireccion;
    }

    public void setSocDireccion(String socDireccion) {
        this.socDireccion = socDireccion;
    }

    @Column(name = "SOC_DIRECCION_REF", length = 65535)
    public String getSocDireccionRef() {
        return this.socDireccionRef;
    }

    public void setSocDireccionRef(String socDireccionRef) {
        this.socDireccionRef = socDireccionRef;
    }

    @Column(name = "SOC_TELEFONO", length = 65535)
    public String getSocTelefono() {
        return this.socTelefono;
    }

    public void setSocTelefono(String socTelefono) {
        this.socTelefono = socTelefono;
    }

    @Column(name = "SOC_CELULAR", length = 65535)
    public String getSocCelular() {
        return this.socCelular;
    }

    public void setSocCelular(String socCelular) {
        this.socCelular = socCelular;
    }

    @Column(name = "SOC_NUM", length = 65535)
    public String getSocNum() {
        return this.socNum;
    }

    public void setSocNum(String socNum) {
        this.socNum = socNum;
    }

    @Column(name = "SOC_ESTADO")
    public Boolean getSocEstado() {
        return this.socEstado;
    }

    public void setSocEstado(Boolean socEstado) {
        this.socEstado = socEstado;
    }

}
