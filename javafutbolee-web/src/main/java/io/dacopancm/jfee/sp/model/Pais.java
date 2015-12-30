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
@Table(name = "pais")
public class Pais implements java.io.Serializable {

    private Integer paiId;
    private String paiNombre;
    //private Set<Ciudad> ciudads = new HashSet<Ciudad>(0);

    public Pais() {
    }

    public Pais(Integer paiId, String paiNombre) {
        this.paiId = paiId;
        this.paiNombre = paiNombre;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "PAI_ID", unique = true, nullable = false)
    public Integer getPaiId() {
        return this.paiId;
    }

    public void setPaiId(Integer paiId) {
        this.paiId = paiId;
    }

    @Column(name = "PAI_NOMBRE", length = 65535)
    public String getPaiNombre() {
        return this.paiNombre;
    }

    public void setPaiNombre(String paiNombre) {
        this.paiNombre = paiNombre;
    }

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pais")
//    public Set<Ciudad> getCiudads() {
//        return this.ciudads;
//    }
//
//    public void setCiudads(Set<Ciudad> ciudads) {
//        this.ciudads = ciudads;
//    }
}
