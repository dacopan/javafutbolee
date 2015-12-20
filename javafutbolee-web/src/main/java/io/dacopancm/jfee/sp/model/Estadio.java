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
@Table(name = "estadio")
public class Estadio implements java.io.Serializable {

    private Integer estId;
    private String estNombre;
    //private Set partidos = new HashSet(0);

    public Estadio() {
    }

    public Estadio(Integer estId, String estNombre) {
        this.estId = estId;
        this.estNombre = estNombre;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "EST_ID", unique = true, nullable = false)
    public Integer getEstId() {
        return this.estId;
    }

    public void setEstId(Integer estId) {
        this.estId = estId;
    }

    @Column(name = "EST_NOMBRE", length = 65535)
    public String getEstNombre() {
        return this.estNombre;
    }

    public void setEstNombre(String estNombre) {
        this.estNombre = estNombre;
    }

    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "estadio")
//    public Set getPartidos() {
//        return this.partidos;
//    }
//
//    public void setPartidos(Set partidos) {
//        this.partidos = partidos;
//    }
}
