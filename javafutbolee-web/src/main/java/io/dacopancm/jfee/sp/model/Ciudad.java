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
@Table(name = "ciudad")
public class Ciudad implements java.io.Serializable {

    private Integer ciuId;
    private Pais pais;
    private String ciuNombre;

    public Ciudad() {
    }

    public Ciudad(Integer ciuId, Pais pais, String ciuNombre) {
        this.ciuId = ciuId;
        this.pais = pais;
        this.ciuNombre = ciuNombre;
    }
    
    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "CIU_ID", unique = true, nullable = false)
    public Integer getCiuId() {
        return this.ciuId;
    }

    public void setCiuId(Integer ciuId) {
        this.ciuId = ciuId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAI_ID")
    public Pais getPais() {
        return this.pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Column(name = "CIU_NOMBRE", length = 65535)
    public String getCiuNombre() {
        return this.ciuNombre;
    }

    public void setCiuNombre(String ciuNombre) {
        this.ciuNombre = ciuNombre;
    }

}
