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

import io.dacopancm.jfee.sp.model.Personal;
import io.dacopancm.jfee.sp.model.Usuario;
import io.dacopancm.jfee.sp.service.PersonalService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author dacopan
 */
@ManagedBean(name = "funcionariosBean")
@ViewScoped 
public class FuncionariosBean implements Serializable{ 

    //private Funcionario
    @ManagedProperty(value = "#{PersonalService}")
    PersonalService personalService;
    
    List<Personal> personalList;
    Personal selectedPersonal;
    
    public void resetAddFuncionario(ActionEvent actionEvent) {
        selectedPersonal = new Personal();
        selectedPersonal.setUsuario(new Usuario());
        selectedPersonal.setPsnNombre("aaa");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "xxxx", null);
        //FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void addFuncionarioAction() {
        //selectedPersonal.getUsuario()
        personalService.addPersonal(selectedPersonal);
    }
    
    public PersonalService getPersonalService() {
        return personalService;
    }
    
    public void setPersonalService(PersonalService personalService) {
        this.personalService = personalService;
    }
    
    public List<Personal> getPersonalList() {
        if (personalList == null) {
            personalList = new ArrayList<Personal>();
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
    
    
    
    String psnNombre;

    public String getPsnNombre() {
        return psnNombre;
    }

    public void setPsnNombre(String psnNombre) {
        this.psnNombre = psnNombre;
    }
    
}
