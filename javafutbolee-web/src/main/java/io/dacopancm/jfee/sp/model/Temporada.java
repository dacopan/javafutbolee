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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dacopan
 */
@Entity
@Table(name = "temporada")
public class Temporada implements java.io.Serializable {

    private Integer tpdId;
    private Date tpdInicio;
    private Date tpdFin;
    private String tpdNombre;
    // private Set partidos = new HashSet(0);

    public Temporada() {
    }

    public Temporada(Integer tpdId, Date tpdInicio, Date tpdFin, String tpdNombre) {
        this.tpdId = tpdId;
        this.tpdInicio = tpdInicio;
        this.tpdFin = tpdFin;
        this.tpdNombre = tpdNombre;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "TPD_ID", unique = true, nullable = false)
    public Integer getTpdId() {
        return this.tpdId;
    }

    public void setTpdId(Integer tpdId) {
        this.tpdId = tpdId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "TPD_INICIO", length = 10)
    public Date getTpdInicio() {
        return this.tpdInicio;
    }

    public void setTpdInicio(Date tpdInicio) {
        this.tpdInicio = tpdInicio;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "TPD_FIN", length = 10)
    public Date getTpdFin() {
        return this.tpdFin;
    }

    public void setTpdFin(Date tpdFin) {
        this.tpdFin = tpdFin;
    }

    @Column(name = "TPD_NOMBRE", length = 85)
    public String getTpdNombre() {
        return tpdNombre;
    }

    public void setTpdNombre(String tpdNombre) {
        this.tpdNombre = tpdNombre;
    }

    /* @OneToMany(fetch = FetchType.LAZY, mappedBy = "temporada")
     public Set getPartidos() {
     return this.partidos;
     }

     public void setPartidos(Set partidos) {
     this.partidos = partidos;
     }
     */
}
