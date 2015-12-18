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
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author dacopan
 */
@ManagedBean(name = "funcionariosBean")
@ViewScoped
public class FuncionariosBean implements Serializable {

    //private Funcionario
    @ManagedProperty(value = "#{PersonalService}")
    PersonalService personalService;

    @ManagedProperty(value = "#{RolService}")
    RolService rolService;

    List<Personal> personalList;
    Personal selectedPersonal;
    List<Personal> filteredPersonalList;

    List<Rol> rolList;

    @PostConstruct
    public void postConstruct() {
        resetAddFuncionario(null);
    }

    public void resetAddFuncionario(ActionEvent actionEvent) {
        selectedPersonal = new Personal();
        Usuario u = new Usuario();
        Rol r = new Rol();
        r.setRolId(4);
        u.setRole(r);
        selectedPersonal.setUsuario(u);

        selectedPersonal.setPsnNombre("darwin");
        selectedPersonal.setPsnApellido("correa");
        selectedPersonal.setPsnFechaNac(new Date());
        selectedPersonal.setPsnTelefono("4986489");
        selectedPersonal.setPsnCelular("4986489");

        selectedPersonal.getUsuario().setUsrEmail("dacopan.bsc@gmail.com");
        selectedPersonal.getUsuario().setUsrCi("1719871327");
    }

    public void addFuncionarioAction() {

        try {
            Personal p = personalService.getPersonalByCi(selectedPersonal.getUsuario().getUsrCi());
            if (p != null) {
                System.out.println(p.getUsuario().getUsrCi() + " " + selectedPersonal.getUsuario().getUsrCi());
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
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo agregar personal."));
        }
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
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo editar personal."));
        }
    }

    public PersonalService getPersonalService() {
        return personalService;
    }

    public void setPersonalService(PersonalService personalService) {
        this.personalService = personalService;
    }

    /*public HelperBean getHelperBean() {
     return helperBean;
     }

     public void setHelperBean(HelperBean helperBean) {
     this.helperBean = helperBean;
     }
     */
    public RolService getRolService() {
        return rolService;
    }

    public void setRolService(RolService rolService) {
        this.rolService = rolService;
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
            getRolService().getRoles();
            rolList.addAll(getRolService().getRoles());
        }
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

}
