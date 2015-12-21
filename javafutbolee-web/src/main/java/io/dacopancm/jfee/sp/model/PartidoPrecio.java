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
@Table(name = "partido_precio")
public class PartidoPrecio implements java.io.Serializable {

    private Integer prtPreId;
    private Localidad localidad;
    private Partido partido;
    private Double prtPreMonto;

    public PartidoPrecio() {
    }

    public PartidoPrecio(Integer prtPreId, Localidad localidad, Partido partido, Double prtPreMonto) {
        this.prtPreId = prtPreId;
        this.localidad = localidad;
        this.partido = partido;
        this.prtPreMonto = prtPreMonto;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "PRT_PRE_ID", unique = true, nullable = false)
    public Integer getPrtPreId() {
        return this.prtPreId;
    }

    public void setPrtPreId(Integer prtPreId) {
        this.prtPreId = prtPreId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOC_ID")
    public Localidad getLocalidad() {
        return this.localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRT_ID")
    public Partido getPartido() {
        return this.partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    @Column(name = "PRT_PRE_MONTO", precision = 4, scale = 0)
    public Double getPrtPreMonto() {
        return this.prtPreMonto;
    }

    public void setPrtPreMonto(Double prtPreMonto) {
        this.prtPreMonto = prtPreMonto;
    }

}
