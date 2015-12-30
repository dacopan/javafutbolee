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
@Table(name = "forma_pago")
public class FormaPago implements java.io.Serializable {

    private Integer frmPagId;
    private String frmPagNombre;
    private Double frmPagPorcentaje;

    public FormaPago() {
    }

    public FormaPago(Integer frmPagId, String frmPagNombre, Double frmPagPorcentaje) {
        this.frmPagId = frmPagId;
        this.frmPagNombre = frmPagNombre;
        this.frmPagPorcentaje = frmPagPorcentaje;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "FRM_PAG_ID", unique = true, nullable = false)
    public Integer getFrmPagId() {
        return this.frmPagId;
    }

    public void setFrmPagId(Integer frmPagId) {
        this.frmPagId = frmPagId;
    }

    @Column(name = "FRM_PAG_NOMBRE", length = 65535)
    public String getFrmPagNombre() {
        return this.frmPagNombre;
    }

    public void setFrmPagNombre(String frmPagNombre) {
        this.frmPagNombre = frmPagNombre;
    }

    @Column(name = "FRM_PAG_PORCENTAJE", precision = 2, scale = 0)
    public Double getFrmPagPorcentaje() {
        return this.frmPagPorcentaje;
    }

    public void setFrmPagPorcentaje(Double frmPagPorcentaje) {
        this.frmPagPorcentaje = frmPagPorcentaje;
    }
}
