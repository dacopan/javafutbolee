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
package io.dacopancm.jfee.managedController;

import io.dacopancm.jfee.sp.model.Partido;
import io.dacopancm.jfee.sp.service.PartidoService;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author dacopan
 */
@ManagedBean(name = "boletoBean")
@ViewScoped
public class BoletoBean {

    private Log log = LogFactory.getLog(this.getClass());

    @ManagedProperty(value = "#{PartidoService}")
    PartidoService partidoService;

    Partido selectedPartido;

    @PostConstruct
    public void postConstruct() {

    }

    //get set

    public Partido getSelectedPartido() {
        if (selectedPartido == null) {
            selectedPartido = partidoService.getNext();
        }
        return selectedPartido;
    }

    public void setSelectedPartido(Partido selectedPartido) {
        this.selectedPartido = selectedPartido;
    }

    public PartidoService getPartidoService() {
        return partidoService;
    }

    public void setPartidoService(PartidoService partidoService) {
        this.partidoService = partidoService;
    }

}
