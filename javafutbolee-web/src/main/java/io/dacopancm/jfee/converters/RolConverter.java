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
package io.dacopancm.jfee.converters;

import io.dacopancm.jfee.sp.model.Rol;
import io.dacopancm.jfee.sp.service.RolService;
import io.dacopancm.jfee.sp.service.RolServiceImpl;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author dacopan
 */
@FacesConverter("RolConverter")
@ApplicationScoped
public class RolConverter implements Converter {

    @ManagedProperty(value = "#{RolService}")
    RolService rolService;

    public RolConverter() {
    }

    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                System.out.println("valuex: "+value+ " rol:serv: "+rolService);
                RolService service = (RolService) context.getExternalContext().getApplicationMap().get("RolService");
                
                System.out.println("valuex2: "+value+ " rol:serv: "+service);
                return service.getRol(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof Rol) {
            System.out.println(value);
            return String.valueOf(((Rol) value).getRolId());
        } else {
            return null;
        }
    }

    public RolService getRolService() {
        return rolService;
    }

    public void setRolService(RolService rolService) {
        this.rolService = rolService;
    }

}
