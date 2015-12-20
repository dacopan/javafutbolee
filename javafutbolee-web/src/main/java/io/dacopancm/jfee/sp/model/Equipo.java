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
@Table(name = "equipo")
public class Equipo implements java.io.Serializable {

    private Integer eqpId;
    private String eqpNombre;
    private String eqpAbbr;
    // private Set partidos = new HashSet(0);

    public Equipo() {
    }

    public Equipo(Integer eqpId, String eqpNombre) {
        this.eqpId = eqpId;
        this.eqpNombre = eqpNombre;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "EQP_ID", unique = true, nullable = false)
    public Integer getEqpId() {
        return this.eqpId;
    }

    public void setEqpId(Integer eqpId) {
        this.eqpId = eqpId;
    }

    @Column(name = "EQP_NOMBRE", length = 65535)
    public String getEqpNombre() {
        return this.eqpNombre;
    }

    public void setEqpNombre(String eqpNombre) {
        this.eqpNombre = eqpNombre;
    }

    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "equipo")
//    public Set getPartidos() {
//        return this.partidos;
//    }
//
//    public void setPartidos(Set partidos) {
//        this.partidos = partidos;
//    }
    @Column(name = "EQP_ABBR", length = 5)
    public String getEqpAbbr() {
        return eqpAbbr;
    }

    public void setEqpAbbr(String eqpAbbr) {
        this.eqpAbbr = eqpAbbr;
    }

}
