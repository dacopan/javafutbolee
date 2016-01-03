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
import io.dacopancm.jfee.sp.model.Personal;
import io.dacopancm.jfee.sp.model.Rol;
import io.dacopancm.jfee.sp.model.Usuario;
import io.dacopancm.jfee.sp.service.PersonalService;
import io.dacopancm.jfee.sp.service.RolService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
@ManagedBean(name = "funcionariosBean")
@ViewScoped
public class FuncionariosBean implements Serializable {

    private final Log log = LogFactory.getLog(getClass());

    String stage;

    //private Funcionario
    @ManagedProperty(value = "#{PersonalService}")
    PersonalService personalService;

    @ManagedProperty(value = "#{RolService}")
    RolService rolService;

    @ManagedProperty(value = "#{PropertyHolder}")
    PropertyHolder props;

    List<Personal> personalList;
    Personal selectedPersonal;
    List<Personal> filteredPersonalList;

    List<Rol> rolList;

    @PostConstruct
    public void postConstruct() {
        log.info("construct FuncionariosBean");
        resetAddFuncionario(null);
    }

    public void resetAddFuncionario(ActionEvent actionEvent) {

        selectedPersonal = new Personal();
        Usuario u = new Usuario();
        Rol r = new Rol();
        r.setRolId(4);
        u.setRole(r);
        selectedPersonal.setUsuario(u);

        //sample data
        if (props
                .getStage()
                .equalsIgnoreCase("dev")) {
            selectedPersonal.setPsnNombre("darwin");
            selectedPersonal.setPsnApellido("correa");
            selectedPersonal.setPsnFechaNac(new Date());
            selectedPersonal.setPsnTelefono("4986489");
            selectedPersonal.setPsnCelular("4986489");

            selectedPersonal.getUsuario().setUsrEmail("dacopan.bsc@gmail.com");
            selectedPersonal.getUsuario().setUsrCi("1719871327");
        }

    }

    public void addFuncionarioAction() {

        try {
            log.info("jfee: start addFuncionarioAction");
            Personal p = personalService.getPersonalByCi(selectedPersonal.getUsuario().getUsrCi());
            if (p != null) {
                log.info("jfee: " + p.getUsuario().getUsrCi() + " " + selectedPersonal.getUsuario().getUsrCi());
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Agregar Personal!", "No se pudo agregar personal, ya existe un funcionario con este CI o email!"));
            } else {

                personalService.addPersonal(selectedPersonal);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregar Personal", "Personal Agregado con éxito!"));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregar Personal", "Se ha enviado un email de confirmación."));
                RequestContext.getCurrentInstance().execute("PF('dlgAddFuncionario').hide()");
                personalList = null;
                getPersonalList();
                resetAddFuncionario(null);
            }
        } catch (Exception ex) {
            log.error("jfee: " + ex, ex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo agregar personal."));
        }
        log.info("jfee: end addFuncionarioAction");
    }

    public void editFuncionarioAction() {

        try {
            personalService.updatePersonal(selectedPersonal);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Editar Personal", "Personal Editado con éxito!"));
            RequestContext.getCurrentInstance().execute("PF('dlgEditFuncionario').hide()");
            personalList = null;
            getPersonalList();
            resetAddFuncionario(null);

        } catch (JfeeCustomException fex) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", fex.getMessage()));
        } catch (Exception ex) {
            log.error("jfee: " + ex, ex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo editar personal."));
        }
    }

    public void deleteFuncionarioAction() {

        try {
            personalService.deletePersonal(selectedPersonal);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar Personal", "Personal eliminado con éxito!"));
            RequestContext.getCurrentInstance().execute("PF('dlgDeleteFuncionario').hide()");
            personalList = null;
            getPersonalList();
            resetAddFuncionario(null);

        } catch (Exception ex) {
            log.error("jfee: " + ex, ex);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo eliminar personal."));
        }
        resetAddFuncionario(null);
    }

    //get set
    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public List<Personal> getPersonalList() {
        if (personalList == null) {
            personalList = new ArrayList<>();
            personalList.addAll(getPersonalService().getPersonals());
        }
        return personalList;
    }

    public void setPersonalList(List<Personal> personalList) {
        this.personalList = personalList;
    }

    public Personal getSelectedPersonal() {
        return selectedPersonal;
    }

    public void setSelectedPersonal(Personal selectedPersonal) {
        this.selectedPersonal = selectedPersonal;
    }

    public List<Personal> getFilteredPersonalList() {
        return filteredPersonalList;
    }

    public void setFilteredPersonalList(List<Personal> filteredPersonalList) {
        this.filteredPersonalList = filteredPersonalList;
    }

    public List<Rol> getRolList() {

        if (rolList == null) {
            rolList = new ArrayList<>();
            /*getRolService().getRoles();
             rolList.addAll(getRolService().getRoles());*/
            rolList.add(new Rol(2, "ADMIN SOCIOS", null));
            rolList.add(new Rol(3, "RRPP", null));

        }
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    //services
    public PersonalService getPersonalService() {
        return personalService;
    }

    public void setPersonalService(PersonalService personalService) {
        this.personalService = personalService;
    }

    public RolService getRolService() {
        return rolService;
    }

    public void setRolService(RolService rolService) {
        this.rolService = rolService;
    }

    public PropertyHolder getProps() {
        return props;
    }

    public void setProps(PropertyHolder props) {
        this.props = props;
    }

}
