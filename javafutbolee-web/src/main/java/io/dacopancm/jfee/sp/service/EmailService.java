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
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
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
    
    @Async
    void sendAccountEmail(final Personal p, final String password) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(p.getUsuario().getUsrEmail());
                message.setFrom(getFromAddress()); // could be parameterized...
                message.setSubject("Registro en JavaFutbolEE: #BSC-SOC10S");
                Map model = new HashMap();
                model.put("user", p.getUsuario());
                model.put("name", p.getPsnNombre() + " " + p.getPsnApellido());
                model.put("password", password);
                model.put("email", p.getUsuario().getUsrEmail());
                
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine,
                        "email/ConfirmationEmailTemplate.vm", "UTF-8", model);
                
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
        sendConfirmationEmail(p);
    }
    
    @Async
    void sendConfirmationEmail(final Personal p) {
        
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
