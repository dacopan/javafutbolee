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
@Table(name = "plan")
public class Plan implements java.io.Serializable {

    private Integer plnId;
    private Localidad localidad;
    private String plnNombre;
    private Double plnCosto;
    private Boolean plnActivo;
    private Integer plnEdad;

    public Plan() {
    }

    public Plan(Integer plnId, Localidad localidad, String plnNombre, Double plnCosto, Boolean plnActivo, Integer plnEdad) {
        this.plnId = plnId;
        this.localidad = localidad;
        this.plnNombre = plnNombre;
        this.plnCosto = plnCosto;
        this.plnActivo = plnActivo;
        this.plnEdad = plnEdad;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "PLN_ID", unique = true, nullable = false)
    public Integer getPlnId() {
        return this.plnId;
    }

    public void setPlnId(Integer plnId) {
        this.plnId = plnId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOC_ID")
    public Localidad getLocalidad() {
        return this.localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    @Column(name = "PLN_NOMBRE", length = 65535)
    public String getPlnNombre() {
        return this.plnNombre;
    }

    public void setPlnNombre(String plnNombre) {
        this.plnNombre = plnNombre;
    }

    @Column(name = "PLN_COSTO", precision = 4, scale = 0)
    public Double getPlnCosto() {
        return this.plnCosto;
    }

    public void setPlnCosto(Double plnCosto) {
        this.plnCosto = plnCosto;
    }

    @Column(name = "PLN_ACTIVO")
    public Boolean getPlnActivo() {
        return this.plnActivo;
    }

    public void setPlnActivo(Boolean plnActivo) {
        this.plnActivo = plnActivo;
    }

    @Column(name = "PLN_EDAD")
    public Integer getPlnEdad() {
        return this.plnEdad;
    }

    public void setPlnEdad(Integer plnEdad) {
        this.plnEdad = plnEdad;
    }

}
