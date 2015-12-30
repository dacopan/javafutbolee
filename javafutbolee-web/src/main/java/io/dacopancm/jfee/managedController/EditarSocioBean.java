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
import io.dacopancm.jfee.sp.model.Ciudad;
import io.dacopancm.jfee.sp.model.Pais;
import io.dacopancm.jfee.sp.model.Socio;
import io.dacopancm.jfee.sp.service.CiudadService;
import io.dacopancm.jfee.sp.service.PaisService;
import io.dacopancm.jfee.sp.service.SocioService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.util.UriUtils;

/**
 *
 * @author dacopan
 */
@ManagedBean(name = "editarSocioBean")
@ViewScoped
public class EditarSocioBean implements Serializable{

    private final Log log = LogFactory.getLog(AdminPortalBean.class);

    @ManagedProperty(value = "#{SocioService}")
    SocioService socioService;

    @ManagedProperty(value = "#{PaisService}")
    PaisService paisService;

    @ManagedProperty(value = "#{CiudadService}")
    CiudadService ciudadService;

    List<Pais> paislList;
    List<Ciudad> ciudadlList;
    Socio selectedSocio;

    String returnPage = "home";

    public EditarSocioBean() {
    }

    @PostConstruct
    public void postConstruct() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (params.containsKey("r")) {
            returnPage = params.get("r");
        } else {
            returnPage = "home";
        }
        try {
            HttpServletRequest htpr = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            if (htpr.getRequestURL().toString().contains("editarSocio")) {
                if (params.containsKey("socCi") && params.containsKey("h")) {
                    String hash = UriUtils.decode(params.get("h"), "UTF-8");
                    String ci = params.get("socCi");
                    if (BCrypt.checkpw(ci, hash)) {
                        //TODO si es administrador no pasa nada pero
                        //si es socio probar q current socio login sea el mismo a modificar
                        selectedSocio = socioService.getSocioByCi(ci);
                    }
                } else {

                    User userDetails
                            = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                    selectedSocio = socioService.getSocioByCi(userDetails.getUsername());

                }
            } else {
                User userDetails
                        = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                selectedSocio = socioService.getSocioByCi(userDetails.getUsername());
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

    }

    @Async
    public void editSocioAction() {
        try {
            socioService.updateSocio(selectedSocio);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Editar Socio", "Sus Datos se han actualizado!"));

        } catch (JfeeCustomException fex) {
            log.error("jfee: " + fex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", fex.getMessage()));
        } catch (Exception ex) {
            log.error("jfee: " + ex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo Editar."));
        }

    }

    //get set
    public List<Pais> getPaislList() {
        if (paislList == null) {
            paislList = new ArrayList<>();
            paislList.addAll(paisService.getPaisAll());
        }
        return paislList;
    }

    public void setPaislList(List<Pais> paislList) {
        this.paislList = paislList;
    }

    public List<Ciudad> getCiudadlList() {
        if (ciudadlList == null) {
            ciudadlList = new ArrayList<>();
            if (selectedSocio != null && selectedSocio.getCiudad() != null) {
                ciudadlList.addAll(ciudadService.getByPaisId(selectedSocio.getCiudad().getPais().getPaiId()));
            }
        }
        return ciudadlList;
    }

    public void setCiudadlList(List<Ciudad> ciudadlList) {
        this.ciudadlList = ciudadlList;
    }

    public Socio getSelectedSocio() {
        return selectedSocio;
    }

    public void setSelectedSocio(Socio selectedSocio) {
        this.selectedSocio = selectedSocio;
    }

    public String getReturnPage() {
        return returnPage;
    }

    public void setReturnPage(String returnPage) {
        this.returnPage = returnPage;
    }

    //services

    public SocioService getSocioService() {
        return socioService;
    }

    public void setSocioService(SocioService socioService) {
        this.socioService = socioService;
    }

    public PaisService getPaisService() {
        return paisService;
    }

    public void setPaisService(PaisService paisService) {
        this.paisService = paisService;
    }

    public CiudadService getCiudadService() {
        return ciudadService;
    }

    public void setCiudadService(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

}
