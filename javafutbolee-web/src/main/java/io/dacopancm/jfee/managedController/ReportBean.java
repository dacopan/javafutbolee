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

import io.dacopancm.jfee.sp.model.Boleto;
import io.dacopancm.jfee.sp.model.Socio;
import io.dacopancm.jfee.sp.service.SocioService;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author dacopan
 */
@ManagedBean(name = "reportBean")
@RequestScoped
public class ReportBean {

    private final Log log = LogFactory.getLog(AdminPortalBean.class);

    @ManagedProperty(value = "#{SocioService}")
    SocioService socioService;

    public ReportBean() {
    }

    public void reportPadronSocios() {
        try {
            sendPdf(init("reportPadronSocios.jasper", socioService.getSocios()), "Reporte_Padron-Socios");
        } catch (JRException | IOException ex) {
            log.error("jfee: " + ex, ex);
        }
    }

    public void reportReservaBoleto(Boleto b) {
        try {
            ArrayList<Boleto> data = new ArrayList<>();
            data.add(b);
            sendPdf(init("reportReservaBoleto.jasper", data), "Reserva-Boleto_" + b.getSocio().getUsuario().getUsrCi() + "_" + b.getBolId());
        } catch (JRException | IOException ex) {
            log.error("jfee: " + ex, ex);
        }
    }

    public void reportAfiliacion(Socio s) {
        try {
            ArrayList<Socio> data = new ArrayList<>();
            data.add(s);
            sendPdf(init("reportAfiliacion.jasper", data), "Afiliacion_" + s.getUsuario().getUsrCi());
        } catch (JRException | IOException ex) {
            log.error("jfee: " + ex, ex);
        }
    }

    public JasperPrint init(String reportName, Collection<?> data) throws JRException {
        try {
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(data);
            //String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/report.jasper");
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream("report/" + reportName);
            log.info("jfee:" + stream.available());
            return JasperFillManager.fillReport(stream, new HashMap(), beanCollectionDataSource);
        } catch (IOException ex) {
            log.error("jfee: " + ex, ex);
        }
        return null;
    }

    public void sendPdf(JasperPrint jasperPrint, String filename) throws JRException, IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + filename + ".pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();

    }

    //get set
    //serivces
    public SocioService getSocioService() {
        return socioService;
    }

    public void setSocioService(SocioService socioService) {
        this.socioService = socioService;
    }

}
