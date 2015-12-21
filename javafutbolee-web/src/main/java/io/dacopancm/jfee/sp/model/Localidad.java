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
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author dacopan
 */
@Entity
@Table(name = "localidad")
public class Localidad implements java.io.Serializable {

    private Integer locId;
    private String locNombre;
    private Integer locaCapacidad;

    public Localidad() {
    }

    public Localidad(Integer locId, String locNombre, Integer locaCapacidad) {
        this.locId = locId;
        this.locNombre = locNombre;
        this.locaCapacidad = locaCapacidad;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "LOC_ID", unique = true, nullable = false)
    public Integer getLocId() {
        return this.locId;
    }

    public void setLocId(Integer locId) {
        this.locId = locId;
    }

    @Column(name = "LOC_NOMBRE", length = 65535)
    public String getLocNombre() {
        return this.locNombre;
    }

    public void setLocNombre(String locNombre) {
        this.locNombre = locNombre;
    }

    @Column(name = "LOCA_CAPACIDAD")
    public Integer getLocaCapacidad() {
        return this.locaCapacidad;
    }

    public void setLocaCapacidad(Integer locaCapacidad) {
        this.locaCapacidad = locaCapacidad;
    }

}
