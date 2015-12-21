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

import io.dacopancm.jfee.exceptions.JfeeCustomException;
import io.dacopancm.jfee.sp.model.Equipo;
import io.dacopancm.jfee.sp.model.Estadio;
import io.dacopancm.jfee.sp.model.Localidad;
import io.dacopancm.jfee.sp.model.Partido;
import io.dacopancm.jfee.sp.model.PartidoPrecio;
import io.dacopancm.jfee.sp.model.Temporada;
import io.dacopancm.jfee.sp.service.LocalidadService;
import io.dacopancm.jfee.sp.service.PartidoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;

/**
 *
 * @author dacopan
 */
@ManagedBean(name = "adminPartidosBean")
@ViewScoped
public class AdminPartidosBean implements Serializable {

    private Log log = LogFactory.getLog(this.getClass());

    @ManagedProperty(value = "#{LocalidadService}")
    LocalidadService localidadService;
    @ManagedProperty(value = "#{PartidoService}")
    PartidoService partidoService;

    List<Localidad> localidadList;
    List<Partido> partidoList;
    List<Partido> filteredPartidoList;

    Partido selectedPartido;

    public AdminPartidosBean() {
    }

    @PostConstruct
    public void postConstruct() {
        resetAddPartido(null);
    }

    public void resetAddPartido(ActionEvent actionEvent) {
        selectedPartido = new Partido();
        selectedPartido.setPartidoPrecios(new HashSet<PartidoPrecio>());
        int i = 0;
        for (Localidad l : getLocalidadList()) {
            PartidoPrecio pp = new PartidoPrecio();
            pp.setLocalidad(l);
            pp.setPrtPreMonto(1.99 + i * 3);
            pp.setPartido(selectedPartido);
            selectedPartido.getPartidoPrecios().add(pp);
            i++;
        }
        selectedPartido.setEquipo(new Equipo());
        selectedPartido.setEstadio(new Estadio());
        selectedPartido.setTemporada(new Temporada());

    }

    public void addPartidoAction() {
        try {
            partidoService.addPartido(selectedPartido);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Añadir Partido", "Partido agregado con éxito!"));
            RequestContext.getCurrentInstance().execute("PF('dlgAddPartido').hide()");
            partidoList = null;
            getPartidoList();

            resetAddPartido(null);

        } catch (JfeeCustomException fex) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", fex.getMessage()));
        } catch (Exception ex) {
            log.error(ex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo agregar partido."));
        }

    }

    public void editPartidoAction() {
        try {
            partidoService.updatePartido(selectedPartido);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Editar Partido", "Partido editar con éxito!"));
            RequestContext.getCurrentInstance().execute("PF('dlgEditPartido').hide()");
            partidoList = null;
            getPartidoList();

            resetAddPartido(null);

        } catch (JfeeCustomException fex) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", fex.getMessage()));
        } catch (Exception ex) {
            log.error(ex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo editar partido."));
        }

    }

    //get and set
    public LocalidadService getLocalidadService() {
        return localidadService;
    }

    public void setLocalidadService(LocalidadService localidadService) {
        this.localidadService = localidadService;
    }

    public PartidoService getPartidoService() {
        return partidoService;
    }

    public void setPartidoService(PartidoService partidoService) {
        this.partidoService = partidoService;
    }

    public List<Localidad> getLocalidadList() {
        if (localidadList == null) {
            localidadList = new ArrayList<>();
            localidadList.addAll(localidadService.getLocalidadAll());

        }
        return localidadList;
    }

    public void setLocalidadList(List<Localidad> localidadList) {
        this.localidadList = localidadList;
    }

    public List<Partido> getPartidoList() {
        if (partidoList == null) {
            partidoList = new ArrayList<>();
            partidoList.addAll(partidoService.getPartidoAll());
        }
        return partidoList;
    }

    public void setPartidoList(List<Partido> partidoList) {
        this.partidoList = partidoList;
    }

    public List<Partido> getFilteredPartidoList() {
        return filteredPartidoList;
    }

    public void setFilteredPartidoList(List<Partido> filteredPartidoList) {
        this.filteredPartidoList = filteredPartidoList;
    }

    public Partido getSelectedPartido() {
        return selectedPartido;
    }

    public void setSelectedPartido(Partido selectedPartido) {
        this.selectedPartido = selectedPartido;
    }

}
