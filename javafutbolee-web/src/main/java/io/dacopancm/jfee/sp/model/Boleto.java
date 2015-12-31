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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author dacopan
 */
@Entity
@Table(name = "boleto")
public class Boleto implements java.io.Serializable {

    private Integer bolId;
    private FormaPago formaPago;
    private PartidoPrecio partidoPrecio;
    private Socio socio;
    private Boolean bolEntregado;

    public Boleto() {
    }

    public Boleto(FormaPago formaPago, PartidoPrecio partidoPrecio, Socio socio, Boolean bolEntregado) {
        this.formaPago = formaPago;
        this.partidoPrecio = partidoPrecio;
        this.socio = socio;
        this.bolEntregado = bolEntregado;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "BOL_ID", unique = true, nullable = false)
    public Integer getBolId() {
        return this.bolId;
    }

    public void setBolId(Integer bolId) {
        this.bolId = bolId;
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
    @JoinColumn(name = "PRT_PRE_ID")
    public PartidoPrecio getPartidoPrecio() {
        return this.partidoPrecio;
    }

    public void setPartidoPrecio(PartidoPrecio partidoPrecio) {
        this.partidoPrecio = partidoPrecio;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SOC_ID")
    public Socio getSocio() {
        return this.socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    @Column(name = "BOL_ENTREGADO")
    public Boolean getBolEntregado() {
        return this.bolEntregado;
    }

    public void setBolEntregado(Boolean bolEntregado) {
        this.bolEntregado = bolEntregado;
    }

}
