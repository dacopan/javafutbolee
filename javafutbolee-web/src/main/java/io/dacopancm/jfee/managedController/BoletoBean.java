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
import io.dacopancm.jfee.sp.model.Boleto;
import io.dacopancm.jfee.sp.model.Partido;
import io.dacopancm.jfee.sp.model.PartidoPrecio;
import io.dacopancm.jfee.sp.model.Socio;
import io.dacopancm.jfee.sp.service.BoletoService;
import io.dacopancm.jfee.sp.service.PartidoService;
import io.dacopancm.jfee.sp.service.SocioService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author dacopan
 */
@ManagedBean(name = "boletoBean")
@ViewScoped
public class BoletoBean implements Serializable {

    private final Log log = LogFactory.getLog(this.getClass());

    @ManagedProperty(value = "#{PartidoService}")
    PartidoService partidoService;

    @ManagedProperty(value = "#{BoletoService}")
    BoletoService boletoService;

    @ManagedProperty(value = "#{SocioService}")
    SocioService socioService;

    Socio selectedSocio;
    Partido selectedPartido;
    PartidoPrecio selectedPrecio;

    @PostConstruct
    public void postConstruct() {

    }

    public void reservarAction() {
        try {

            Boleto b = new Boleto();
            b.setSocio(selectedSocio);
            b.setPartidoPrecio(selectedPrecio);
            boletoService.addBoleto(b);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reservación", "Reservación exitosa!"));

        } catch (JfeeCustomException fex) {
            log.error("jfee: " + fex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", fex.getMessage()));
        } catch (Exception ex) {
            log.error("jfee: " + ex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo Reservar."));
        }

    }

    public void cancelarAction() {
        try {

            Boleto b = getBoletoReservado();
            boletoService.deleteBoleto(b);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reservación", "Reservación cancelada!"));

        } catch (JfeeCustomException fex) {
            log.error("jfee: " + fex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", fex.getMessage()));
        } catch (Exception ex) {
            log.error("jfee: " + ex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo Cancelar."));
        }
    }

    public void printAction() {
    }

    public Boleto getBoletoReservado() {
        try {
            User userDetails
                    = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return boletoService.getBoletoByPartidoIdAndSocCi(selectedPartido.getPrtId(), userDetails.getUsername());

        } catch (Exception ex) {
            log.error("jfee: " + ex,ex);
        }
        return null;
    }

    public boolean isPartidoReservado() {
        return getBoletoReservado() != null;
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

    public Socio getSelectedSocio() {
        if (selectedSocio == null) {
            User userDetails
                    = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            selectedSocio = socioService.getSocioByCi(userDetails.getUsername());
        }
        return selectedSocio;
    }

    public void setSelectedSocio(Socio selectedSocio) {
        this.selectedSocio = selectedSocio;
    }

    public PartidoPrecio getSelectedPrecio() {
        if (selectedPrecio == null) {
            int locId = getSelectedSocio().getPlan().getLocalidad().getLocId();
            for (PartidoPrecio pp : getSelectedPartido().getPartidoPrecios()) {
                if (pp.getLocalidad().getLocId() == locId) {
                    selectedPrecio = pp;
                    break;
                }
            }

        }
        return selectedPrecio;
    }

    public void setSelectedPrecio(PartidoPrecio selectedPrecio) {
        this.selectedPrecio = selectedPrecio;
    }

    //services
    public PartidoService getPartidoService() {
        return partidoService;
    }

    public void setPartidoService(PartidoService partidoService) {
        this.partidoService = partidoService;
    }

    public BoletoService getBoletoService() {
        return boletoService;
    }

    public void setBoletoService(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    public SocioService getSocioService() {
        return socioService;
    }

    public void setSocioService(SocioService socioService) {
        this.socioService = socioService;
    }

}
