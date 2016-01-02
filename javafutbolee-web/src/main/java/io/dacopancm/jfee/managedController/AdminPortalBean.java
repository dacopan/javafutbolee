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
import io.dacopancm.jfee.sp.model.Temporada;
import io.dacopancm.jfee.sp.service.EquipoService;
import io.dacopancm.jfee.sp.service.EstadioService;
import io.dacopancm.jfee.sp.service.TemporadaService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
@ManagedBean(name = "adminPortalBean")
@ViewScoped
public class AdminPortalBean implements Serializable {

    private final Log log = LogFactory.getLog(getClass());

    @ManagedProperty(value = "#{TemporadaService}")
    TemporadaService temporadaService;
    @ManagedProperty(value = "#{EquipoService}")
    EquipoService equipoService;
    @ManagedProperty(value = "#{EstadioService}")
    EstadioService estadioService;

    List<Temporada> temporadaList;
    List<Equipo> equipoList;
    List<Estadio> estadioList;

    List<Temporada> filteredTemporadaList;
    List<Equipo> filteredEquipoList;
    List<Estadio> filteredEstadioList;

    Temporada selectedTemporada;
    Equipo selectedEquipo;
    Estadio selectedEstadio;

    public AdminPortalBean() {
    }

    public void resetAddTemporada(ActionEvent actionEvent) {
        selectedTemporada = new Temporada();
    }

    public void resetAddEquipo(ActionEvent actionEvent) {
        selectedEquipo = new Equipo();
    }

    public void resetAddEstadio(ActionEvent actionEvent) {
        selectedEstadio = new Estadio();
    }

    public void addTemporadaAction() {
        try {
            Temporada p = temporadaService.getTemporadaByNombre(selectedTemporada.getTpdNombre());
            if (p != null) {
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Agregar Temporada!", "No se pudo agregar temporada, ya existe una temporada con este nombre!"));
            } else {

                temporadaService.addTemporada(selectedTemporada);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregar Temporada", "Temporada Agregada con éxito!"));
                RequestContext.getCurrentInstance().execute("PF('dlgAddTemporada').hide()");
                temporadaList = null;
                resetAddTemporada(null);
            }
        } catch (Exception ex) {
            log.error("jfee: " + ex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo agregar personal."));
        }
    }

    public void editTemporadaAction() {

        try {
            temporadaService.updateTemporada(selectedTemporada);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Editar Temporada", "Temporada Editada con éxito!"));

        } catch (JfeeCustomException fex) {
            log.error("jfee: " + fex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", fex.getMessage()));
        } catch (Exception ex) {
            log.error("jfee: " + ex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo editar Temporada."));
        }
    }

    public void addEquipoAction() {
        try {
            Equipo p = equipoService.getEquipoByNombre(selectedEquipo.getEqpNombre());
            if (p != null) {
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Agregar Equipo!", "No se pudo agregar equipo, ya existe un equipo con este nombre!"));
            } else {

                equipoService.addEquipo(selectedEquipo);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregar Equipo", "Equipo Agregada con éxito!"));
                RequestContext.getCurrentInstance().execute("PF('dlgAddEquipo').hide()");
                equipoList = null;
                resetAddTemporada(null);
            }
        } catch (Exception ex) {
            log.error("jfee: " + ex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo agregar Equipo."));
        }
    }

    public void editEquipoAction() {

        try {
            equipoService.updateEquipo(selectedEquipo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Editar Equipo", "Equipo editado con éxito!"));
            equipoList = null;
        } catch (JfeeCustomException fex) {
            log.error("jfee: " + fex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", fex.getMessage()));
        } catch (Exception ex) {
            log.error("jfee: " + ex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo editar Equipo."));
        }
    }

    public void addEstadioAction() {
        try {
            Estadio p = estadioService.getEstadioByNombre(selectedEstadio.getEstNombre());
            if (p != null) {
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Agregar Estadio!", "No se pudo agregar estdio, ya existe un Estadio con este nombre!"));
            } else {

                estadioService.addEstadio(selectedEstadio);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregar Estadio", "Estadio Agregado con éxito!"));
                RequestContext.getCurrentInstance().execute("PF('dlgAddEstadio').hide()");
                estadioList = null;
                resetAddTemporada(null);
            }
        } catch (Exception ex) {
            log.error("jfee: " + ex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo agregar Estadio."));
        }
    }

    public void editEstadioAction() { 

        try {
            estadioService.updateEstadio(selectedEstadio);
            estadioList = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Editar Estadio", "Estadio editado con éxito!"));

        } catch (JfeeCustomException fex) {
            log.error("jfee: " + fex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", fex.getMessage()));
        } catch (Exception ex) {
            log.error("jfee: " + ex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo editar Estadio."));
        }
    }

    public List<Temporada> getTemporadaList() {
        if (temporadaList == null) {
            temporadaList = new ArrayList<>();
            temporadaList.addAll(temporadaService.getTemporadaAll());
        }
        return temporadaList;
    }

    public void setTemporadaList(List<Temporada> temporadaList) {
        this.temporadaList = temporadaList;
    }

    public List<Equipo> getEquipoList() {
        if (equipoList == null) {
            equipoList = new ArrayList<>();
            equipoList.addAll(equipoService.getEquipoAll());
        }
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }

    public List<Estadio> getEstadioList() {
        if (estadioList == null) {
            estadioList = new ArrayList<>();
            estadioList.addAll(estadioService.getEstadioAll());
        }
        return estadioList;
    }

    public void setEstadioList(List<Estadio> estadioList) {
        this.estadioList = estadioList;
    }

    public List<Temporada> getFilteredTemporadaList() {
        return filteredTemporadaList;
    }

    public void setFilteredTemporadaList(List<Temporada> filteredTemporadaList) {
        this.filteredTemporadaList = filteredTemporadaList;
    }

    public List<Equipo> getFilteredEquipoList() {
        return filteredEquipoList;
    }

    public void setFilteredEquipoList(List<Equipo> filteredEquipoList) {
        this.filteredEquipoList = filteredEquipoList;
    }

    public List<Estadio> getFilteredEstadioList() {
        return filteredEstadioList;
    }

    public void setFilteredEstadioList(List<Estadio> filteredEstadioList) {
        this.filteredEstadioList = filteredEstadioList;
    }

    public Temporada getSelectedTemporada() {
        return selectedTemporada;
    }

    public void setSelectedTemporada(Temporada selectedTemporada) {
        this.selectedTemporada = selectedTemporada;
    }

    public Equipo getSelectedEquipo() {
        return selectedEquipo;
    }

    public void setSelectedEquipo(Equipo selectedEquipo) {
        this.selectedEquipo = selectedEquipo;
    }

    public Estadio getSelectedEstadio() {
        return selectedEstadio;
    }

    public void setSelectedEstadio(Estadio selectedEstadio) {
        this.selectedEstadio = selectedEstadio;
    }

//service get set
    public TemporadaService getTemporadaService() {
        return temporadaService;
    }

    public void setTemporadaService(TemporadaService temporadaService) {
        this.temporadaService = temporadaService;
    }

    public EquipoService getEquipoService() {
        return equipoService;
    }

    public void setEquipoService(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    public EstadioService getEstadioService() {
        return estadioService;
    }

    public void setEstadioService(EstadioService estadioService) {
        this.estadioService = estadioService;
    }

}
