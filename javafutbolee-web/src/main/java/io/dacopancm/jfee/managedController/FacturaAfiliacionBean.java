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

import io.dacopancm.jfee.sp.model.Socio;
import io.dacopancm.jfee.sp.service.SocioService;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.util.UriUtils;

/**
 *
 * @author dacopan
 */
@ManagedBean(name = "facturaAfiliacionBean")
@ViewScoped
public class FacturaAfiliacionBean implements Serializable {

    private final Log log = LogFactory.getLog(AdminPortalBean.class);

    @ManagedProperty(value = "#{SocioService}")
    SocioService socioService;

    Socio selectedSocio;

    String returnPage = "index";

    public FacturaAfiliacionBean() {
    }

    @PostConstruct
    public void postConstruct() {

        try {
            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            if (params.containsKey("r")) {
                returnPage = params.get("r");
            }
            if (params.containsKey("socCi") && params.containsKey("h")) {
                String hash = UriUtils.decode(params.get("h"), "UTF-8");
                String ci = params.get("socCi");
                if (BCrypt.checkpw(ci, hash)) {
                    selectedSocio = socioService.getSocioByCi(ci);
                }
            } else {
                //redirect to index
            }
        } catch (UnsupportedEncodingException ex) {
            log.error(ex.getMessage());
            //redirect to index
        }

    }
    

    public String getReturnPage() {
        return returnPage;
    }

    public void setReturnPage(String returnPage) {
        this.returnPage = returnPage;
    }

    //get set
    public Socio getSelectedSocio() {
        return selectedSocio;
    }

    public void setSelectedSocio(Socio selectedSocio) {
        this.selectedSocio = selectedSocio;
    }

    //services
    public SocioService getSocioService() {
        return socioService;
    }

    public void setSocioService(SocioService socioService) {
        this.socioService = socioService;
    }

}
