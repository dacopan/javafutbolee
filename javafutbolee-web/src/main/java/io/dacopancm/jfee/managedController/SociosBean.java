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
import io.dacopancm.jfee.sp.model.FormaPago;
import io.dacopancm.jfee.sp.model.Localidad;
import io.dacopancm.jfee.sp.model.Pais;
import io.dacopancm.jfee.sp.model.Plan;
import io.dacopancm.jfee.sp.model.Rol;
import io.dacopancm.jfee.sp.model.Socio;
import io.dacopancm.jfee.sp.model.Usuario;
import io.dacopancm.jfee.sp.service.CiudadService;
import io.dacopancm.jfee.sp.service.FormaPagoService;
import io.dacopancm.jfee.sp.service.LocalidadService;
import io.dacopancm.jfee.sp.service.PaisService;
import io.dacopancm.jfee.sp.service.PlanService;
import io.dacopancm.jfee.sp.service.SocioService;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.util.UriUtils;

/**
 *
 * @author dacopan
 */
@ManagedBean(name = "sociosBean")
@ViewScoped
public class SociosBean implements Serializable {

    private final Log log = LogFactory.getLog(AdminPortalBean.class);

    @ManagedProperty(value = "#{SocioService}")
    SocioService socioService;

    @ManagedProperty(value = "#{PaisService}")
    PaisService paisService;

    @ManagedProperty(value = "#{CiudadService}")
    CiudadService ciudadService;

    @ManagedProperty(value = "#{PlanService}")
    PlanService planService;

    @ManagedProperty(value = "#{LocalidadService}")
    LocalidadService localidadService;

    @ManagedProperty(value = "#{FormaPagoService}")
    FormaPagoService formaPagoService;

    List<Socio> socioList;
    Socio selectedSocio;
    List<Socio> filteredSocioList;

    List<Pais> paislList;
    List<Ciudad> ciudadlList;
    List<Plan> planList;

    Plan selectedPlan;

    List<Localidad> localidadlList;
    List<FormaPago> formaPagolList;

    double selectedPrecio;
    double selectedPago;

    String returnPage = "index";

    @PostConstruct
    public void postConstruct() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (params.containsKey("r")) {
            returnPage = params.get("r");
        } else {
            returnPage = "index";
        }

        resetAddPlan(null);
        resetAddSocio(null);
    }

    public void resetAddSocio(ActionEvent actionEvent) {

        selectedSocio = new Socio();
        Usuario u = new Usuario();
        Rol r = new Rol();
        r.setRolId(4);
        u.setRole(r);
        selectedSocio.setUsuario(u);

        Ciudad c = new Ciudad();
        c.setPais(new Pais());
        c.getPais().setPaiId(1);
        selectedSocio.setCiudad(c);
        selectedSocio.setPlan(new Plan());

        selectedSocio.setFormaPago(new FormaPago());

        //sample data
        selectedSocio.setSocNombre("darwin");
        selectedSocio.setSocApellido("correa");
        selectedSocio.setSocFechaNac(new Date());
        selectedSocio.setSocTelefono("4986489");
        selectedSocio.setSocCelular("4986489");

        selectedSocio.getUsuario().setUsrEmail("dacopan.bsc@gmail.com");
        selectedSocio.getUsuario().setUsrCi("1719871327");
        selectedSocio.setSocDireccion("lorem ipsum");

        selectedSocio.setSocTipoSangre("ORH+");

        selectedSocio.setSocNumHijos(5);
        selectedSocio.setSocGenero(0);
    }

    public void onPaisChange() {
        log.info("jfee: selected pais: " + selectedSocio.getCiudad().getPais().getPaiNombre());
        ciudadlList = new ArrayList<>();
        ciudadlList.addAll(ciudadService.getByPaisId(selectedSocio.getCiudad().getPais().getPaiId()));
    }

    public void resetAddPlan(ActionEvent actionEvent) {
        selectedPlan = new Plan();
        selectedPlan.setLocalidad(new Localidad());
    }

    public void addPlanAction() {
        try {
            Plan p = planService.getPlanByNombre(selectedPlan.getPlnNombre());
            if (p != null) {
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Agregar Estadio!", "No se pudo agregar estdio, ya existe un Plan con este nombre!"));
            } else {

                planService.addPlan(selectedPlan);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregar Plan", "Plan Agregado con éxito!"));
                RequestContext.getCurrentInstance().execute("PF('dlgAddPlan').hide()");
                planList = null;
                resetAddPlan(null);
            }
        } catch (Exception ex) {
            log.error("jfee: " + ex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo agregar Plan."));
        }
    }

    public void editPlanAction() {
        try {
            planService.updatePlan(selectedPlan);
            planList = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Editar Plan", "Plan editado con éxito!"));

        } catch (JfeeCustomException fex) {
            log.error("jfee: " + fex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", fex.getMessage()));
        } catch (Exception ex) {
            log.error("jfee: " + ex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo editar Plan."));
        }
    }

    public String addSocioAction() {
        try {
            socioService.addSocio(selectedSocio);
            planList = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Afiliación", "Afiliación exitosa!"));

            return "/views/s/adminSocios/facturaAfiliacion.xhtml?faces-redirect=true&socCi=" + selectedSocio.getUsuario().getUsrCi() + "&h=" + UriUtils.encode(new BCryptPasswordEncoder().encode(selectedSocio.getUsuario().getUsrCi()), "UTF-8")
                    + "&r=" + returnPage;

        } catch (JfeeCustomException fex) {
            log.error("jfee: " + fex, fex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", fex.getMessage()));
        } catch (UnsupportedEncodingException ex) {
            log.error("jfee: " + ex, ex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo Afiliar."));
        }
        return null;
    }

    public void deleteSocioAction() {
        try {
            socioService.deleteSocio(selectedSocio);
            socioList = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar Socio", "Socio eliminado!"));

        } catch (JfeeCustomException fex) {
            log.error("jfee: " + fex, fex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", fex.getMessage()));
        } catch (Exception ex) {
            log.error("jfee: " + ex, ex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo Eliminar."));
        }
    }

    public String editSocioRequestPageAction() {
        try {
            return "/views/s/adminSocios/editarSocio.xhtml?faces-redirect=true&socCi=" + selectedSocio.getUsuario().getUsrCi() + "&h=" + UriUtils.encode(new BCryptPasswordEncoder().encode(selectedSocio.getUsuario().getUsrCi()), "UTF-8")
                    + "&r=adminSocios";
        } catch (UnsupportedEncodingException ex) {
            log.error("jfee: " + ex, ex);
        }
        return null;
    }

    public void toggleEstadoSocio() {
        try {
            boolean estado = !selectedSocio.getSocEstado();

            selectedSocio.setSocEstado(estado);
            socioService.updateSocio(selectedSocio);
            planList = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Editar Socio", "Socio Editado!"));

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

    public String getReturnPage() {
        return returnPage;
    }

    public void setReturnPage(String returnPage) {
        this.returnPage = returnPage;
    }

    //gets sets    
    public List<Socio> getSocioList() {
        if (socioList == null) {
            socioList = new ArrayList<>();
            socioList.addAll(socioService.getSocios());
        }
        return socioList;
    }

    public void setSocioList(List<Socio> socioList) {
        this.socioList = socioList;
    }

    public Socio getSelectedSocio() {
        return selectedSocio;
    }

    public void setSelectedSocio(Socio selectedSocio) {
        this.selectedSocio = selectedSocio;
    }

    public List<Socio> getFilteredSocioList() {
        return filteredSocioList;
    }

    public void setFilteredSocioList(List<Socio> filteredSocioList) {
        this.filteredSocioList = filteredSocioList;
    }

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
        } else {
            if (ciudadlList.isEmpty() || (ciudadlList.size() > 0 && selectedSocio != null && selectedSocio.getCiudad() != null
                    && ciudadlList.get(0).getPais().getPaiId().intValue() != selectedSocio.getCiudad().getPais().getPaiId())) {
                ciudadlList = new ArrayList<>();
                ciudadlList.addAll(ciudadService.getByPaisId(selectedSocio.getCiudad().getPais().getPaiId()));
            }
        }
        return ciudadlList;
    }

    public void setCiudadlList(List<Ciudad> ciudadlList) {
        this.ciudadlList = ciudadlList;
    }

    public List<Plan> getPlanList() {
        if (planList == null) {
            planList = new ArrayList<>();
            planList.addAll(planService.getPlanAll());
        }
        return planList;
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
    }

    public Plan getSelectedPlan() {
        return selectedPlan;
    }

    public void setSelectedPlan(Plan selectedPlan) {
        this.selectedPlan = selectedPlan;
    }

    public List<Localidad> getLocalidadlList() {
        if (localidadlList == null) {
            localidadlList = new ArrayList<>();
            localidadlList.addAll(localidadService.getLocalidadAll());
        }
        return localidadlList;
    }

    public void setLocalidadlList(List<Localidad> localidadlList) {
        this.localidadlList = localidadlList;
    }

    public List<FormaPago> getFormaPagolList() {
        if (formaPagolList == null) {
            formaPagolList = new ArrayList<>();
            formaPagolList.addAll(formaPagoService.getFormaPagoAll());
        }
        return formaPagolList;
    }

    public void setFormaPagolList(List<FormaPago> formaPagolList) {
        this.formaPagolList = formaPagolList;
    }

    public double getSelectedPrecio() {
        return selectedPrecio;
    }

    public void setSelectedPrecio(double selectedPrecio) {
        this.selectedPrecio = selectedPrecio;
    }

    public double getSelectedPago() {
        return selectedPago;
    }

    public void setSelectedPago(double selectedPago) {
        this.selectedPago = selectedPago;
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

    public PlanService getPlanService() {
        return planService;
    }

    public void setPlanService(PlanService planService) {
        this.planService = planService;
    }

    public LocalidadService getLocalidadService() {
        return localidadService;
    }

    public void setLocalidadService(LocalidadService localidadService) {
        this.localidadService = localidadService;
    }

    public FormaPagoService getFormaPagoService() {
        return formaPagoService;
    }

    public void setFormaPagoService(FormaPagoService formaPagoService) {
        this.formaPagoService = formaPagoService;
    }

}
