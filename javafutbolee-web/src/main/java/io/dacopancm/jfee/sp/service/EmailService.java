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
package io.dacopancm.jfee.sp.service;

import io.dacopancm.jfee.sp.model.Personal;
import io.dacopancm.jfee.sp.model.Usuario;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.mail.internet.MimeMessage;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 *
 * @author dacopan
 */
@Service
public class EmailService implements Serializable {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VelocityEngine velocityEngine;

    @Value("${jfee.mail.from}")
    String fromAddress;
    @Value("${jfee.portal.url}")
    String urlx;

    @Async
    void sendAccountEmail(final Usuario u, final String nombre, final String apellido, final String password, final String confirmUrl) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage,"UTF-8");
                message.setTo(u.getUsrEmail());
                message.setFrom(getFromAddress()); // could be parameterized...
                message.setSubject("Registro #BSC SOC10S | JavaFutbolEE");
                Map model = new HashMap();

                model.put("subject", "Registro #BSC SOC10S | JavaFutbolEE");
                model.put("nombre", nombre + " " + apellido);
                model.put("email", u.getUsrEmail());
                model.put("url", (urlx + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()));

                model.put("user", u);
                model.put("password", password);

                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine,
                        "email/JFEE_register.html", "UTF-8", model);

                mimeMessage.setHeader("Content-Type", "text/plain; charset=UTF-8");
                message.setText(text, true);
            }
        };

        this.mailSender.send(preparator);
        sendConfirmationEmail(u, nombre, apellido, confirmUrl);
    }

    @Async
    void sendConfirmationEmail(final Usuario u, final String nombre, final String apellido, final String confirmUrl) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage,"UTF-8");
                message.setTo(u.getUsrEmail());
                message.setFrom(getFromAddress()); // could be parameterized...
                message.setSubject("Confirmación #BSC SOC10S | JavaFutbolEE");
                Map model = new HashMap();

                model.put("subject", "Confirmación #BSC SOC10S | JavaFutbolEE");
                model.put("nombre", nombre + " " + apellido);
                model.put("email", u.getUsrEmail());
                model.put("url", (urlx + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()));

                model.put("user", u);
                model.put("confirmUrl", confirmUrl);

                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine,
                        "email/JFEE_confirmEmail.html", "UTF-8", model);

                mimeMessage.setHeader("Content-Type", "text/plain; charset=UTF-8");
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
    }

    public EmailService() {
    }

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

}
