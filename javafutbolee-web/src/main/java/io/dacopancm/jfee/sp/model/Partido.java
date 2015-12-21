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
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dacopan
 */
@Entity
@Table(name = "partido")
public class Partido implements java.io.Serializable {

    private Integer prtId;
    private Equipo equipo;
    private Estadio estadio;
    private Temporada temporada;
    private Date prtFecha;
    private Boolean prtLocal;
    private Integer prtGol;
    private Integer prtGolRival;
    private Set<PartidoPrecio> partidoPrecios = new HashSet<>(0);

    public Partido() {
    }

    public Partido(Equipo equipo, Estadio estadio, Temporada temporada, Date prtFecha, Boolean prtLocal, Integer prtGol, Integer prtGolRival, Set<PartidoPrecio> partidoPrecios) {
        this.equipo = equipo;
        this.estadio = estadio;
        this.temporada = temporada;
        this.prtFecha = prtFecha;
        this.prtLocal = prtLocal;
        this.prtGol = prtGol;
        this.prtGolRival = prtGolRival;
        this.partidoPrecios = partidoPrecios;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "PRT_ID", unique = true, nullable = false)
    public Integer getPrtId() {
        return this.prtId;
    }

    public void setPrtId(Integer prtId) {
        this.prtId = prtId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EQP_ID")
    public Equipo getEquipo() {
        return this.equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EST_ID")
    public Estadio getEstadio() {
        return this.estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TPD_ID")
    public Temporada getTemporada() {
        return this.temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PRT_FECHA", length = 19)
    public Date getPrtFecha() {
        return this.prtFecha;
    }

    public void setPrtFecha(Date prtFecha) {
        this.prtFecha = prtFecha;
    }

    @Column(name = "PRT_LOCAL")
    public Boolean getPrtLocal() {
        return this.prtLocal;
    }

    public void setPrtLocal(Boolean prtLocal) {
        this.prtLocal = prtLocal;
    }

    @Column(name = "PRT_GOL")
    public Integer getPrtGol() {
        return this.prtGol;
    }

    public void setPrtGol(Integer prtGol) {
        this.prtGol = prtGol;
    }

    @Column(name = "PRT_GOL_RIVAL")
    public Integer getPrtGolRival() {
        return this.prtGolRival;
    }

    public void setPrtGolRival(Integer prtGolRival) {
        this.prtGolRival = prtGolRival;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partido", cascade = CascadeType.ALL)
    public Set<PartidoPrecio> getPartidoPrecios() {
        return this.partidoPrecios;
    }

    public void setPartidoPrecios(Set<PartidoPrecio> partidoPrecios) {
        this.partidoPrecios = partidoPrecios;
    }

}
