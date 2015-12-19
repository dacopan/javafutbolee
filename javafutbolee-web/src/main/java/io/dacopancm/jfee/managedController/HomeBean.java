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

import io.dacopancm.jfee.sp.model.Usuario;
import io.dacopancm.jfee.sp.service.UsuarioService;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author dacopan
 */
@ManagedBean(name = "homeBean")
@ViewScoped
public class HomeBean implements Serializable {
    
    Usuario currentUsuario;
    
    @ManagedProperty(value = "#{UsuarioService}")
    UsuarioService usuarioService;
    
    String hash;
    String ciHash;
    String ci;
    
    @PostConstruct
    public void postConstruct() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        if (params.containsKey("h")) {
            hash = params.get("h");
            ciHash = params.get("c");
            
        } else {
            
            try {
                User userDetails
                        = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                
                setCurrentUsuario(usuarioService.getUsuario(userDetails.getUsername()));
            } catch (Exception ex) {
                
            }
        }
    }
    
    public String confirmEmailAction() {
        
        if (BCrypt.checkpw(ci, ciHash)) {
            Usuario u = usuarioService.getUsuario(ci);
            if (u.getUsrActivationHash() == null) {
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Activado!", "Cuenta confirmada."));                
                return "login";
            }
            if (u.getUsrActivationHash().equalsIgnoreCase(hash)) {
                u.setUsrActivationHash(null);
                u.setUsrActive(true);
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Activado!", "Cuenta confirmada."));
                usuarioService.updateUsuario(u);
                return "login";
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Token incorrecto."));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Usuario no corresponde al token."));
        }
        return null;
    }
    
    public void editEmailAction() {
        try {
            usuarioService.updateUsuario(currentUsuario);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado!", ""));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo editar email."));
        }
    }
    
    public Usuario getCurrentUsuario() {
        return currentUsuario;
    }
    
    public void setCurrentUsuario(Usuario currentUsuario) {
        this.currentUsuario = currentUsuario;
    }
    
    public UsuarioService getUsuarioService() {
        return usuarioService;
    }
    
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    public String getHash() {
        return hash;
    }
    
    public void setHash(String hash) {
        this.hash = hash;
    }
    
    public String getCiHash() {
        return ciHash;
    }
    
    public void setCiHash(String ciHash) {
        this.ciHash = ciHash;
    }
    
    public String getCi() {
        return ci;
    }
    
    public void setCi(String ci) {
        this.ci = ci;
    }
    
}
