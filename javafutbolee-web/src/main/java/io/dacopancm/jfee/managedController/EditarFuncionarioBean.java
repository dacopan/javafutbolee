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
import io.dacopancm.jfee.sp.service.PersonalService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author dacopan
 */
@ManagedBean(name = "editarFuncionarioBean")
@ViewScoped
public class EditarFuncionarioBean implements Serializable {
//private Funcionario

    @ManagedProperty(value = "#{PersonalService}")
    PersonalService personalService;

    Personal selectedPersonal;

    public EditarFuncionarioBean() {
    }

    @PostConstruct
    public void postConstruct() {
        try {
            User userDetails
                    = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            selectedPersonal = personalService.getPersonalByCi(userDetails.getUsername());
        } catch (Exception ex) {

        }
    }

    public void editFuncionarioAction() {

        try {
            personalService.updatePersonal(selectedPersonal);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Editar Personal", "Personal Editado con éxito!"));            

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

    public Personal getSelectedPersonal() {
        return selectedPersonal;
    }

    public void setSelectedPersonal(Personal selectedPersonal) {
        this.selectedPersonal = selectedPersonal;
    }

}
