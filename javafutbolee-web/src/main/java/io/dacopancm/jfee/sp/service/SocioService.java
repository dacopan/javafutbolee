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

import io.dacopancm.jfee.exceptions.JfeeCustomException;
import io.dacopancm.jfee.sp.dao.RolDAO;
import io.dacopancm.jfee.sp.dao.SocioDAO;
import io.dacopancm.jfee.sp.model.Socio;
import io.dacopancm.jfee.sp.model.Usuario;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dacopan
 */
@Service("SocioService")
@Transactional(readOnly = true)
public class SocioService {

    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    SocioDAO socioDAO;
    @Autowired
    RolDAO rolDAO;
    @Autowired
    EmailService emailService;

    @Transactional(readOnly = false)
    public void addSocio(Socio s) throws JfeeCustomException {

        Socio old = socioDAO.getSocioByCi(s.getUsuario().getUsrCi());
        if (old != null) {
            socioDAO.evictSocio(old);
            throw new JfeeCustomException("Ya existe un socio con esa CI");
        }

        //temp password
        String tmpPassword = org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(12);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(tmpPassword);

        //user
        Usuario u = s.getUsuario();

        u.setUsrActivationHash(java.util.UUID.randomUUID().toString().replace("-", ""));
        u.setUsrActive(false);
        u.setUsrCreationTimestamp(new Date());
        u.setUsrPassword(hashedPassword);

        //confirm email url
        String confirmUrl = "confirmEmail.xhtml?h=" + u.getUsrActivationHash() + "&c=" + passwordEncoder.encode(u.getUsrCi());

        socioDAO.addSocio(s);
        emailService.sendAccountEmail(s.getUsuario(), s.getSocNombre(), s.getSocApellido(), tmpPassword, confirmUrl, FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
    }

    @Transactional(readOnly = false)
    public void deleteSocio(Socio p) throws JfeeCustomException {
        socioDAO.deleteSocio(p);
    }

    @Transactional(readOnly = false)
    public void updateSocio(Socio p) throws JfeeCustomException {
        Socio old = socioDAO.getSocioById(p.getSocId());

        if (!old.getUsuario().getUsrCi().equalsIgnoreCase(p.getUsuario().getUsrCi())) {
            //intenta actualizar cedula
            Socio old2 = socioDAO.getSocioByCi(p.getUsuario().getUsrCi());
            if (old2.getSocId().intValue() != p.getSocId()) {
                //traemos usuario por CI, si ya existe quiere decir q el usuario q se esta actualizando
                //quiere usar una CI q ya esta registrada
                throw new io.dacopancm.jfee.exceptions.JfeeCustomException("Ya existe usuario con esa CI");
            }
            socioDAO.evictSocio(old2);
        }

        if (!old.getUsuario().getUsrEmail().equalsIgnoreCase(p.getUsuario().getUsrEmail())) {
            p.getUsuario().setUsrActivationHash(java.util.UUID.randomUUID().toString().replace("-", ""));

            //confirm email url
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String confirmUrl = "confirmEmail.xhtml?h=" + p.getUsuario().getUsrActivationHash() + "&c=" + passwordEncoder.encode(p.getUsuario().getUsrCi());
            emailService.sendConfirmationEmail(p.getUsuario(), p.getSocNombre(), p.getSocApellido(), confirmUrl, FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
        }

        //TODO: verify if email already in use
        p.getUsuario().setRole(rolDAO.getRol(p.getUsuario().getRol().getRolId()));
        socioDAO.evictSocio(old);
        socioDAO.updateSocio(p);
    }

    public Socio getSocioById(int id) {
        return socioDAO.getSocioById(id);
    }

    public Socio getSocioByCi(String ci) {
        return socioDAO.getSocioByCi(ci);
    }

    public List<Socio> getSocios() {
        return socioDAO.getSocios();
    }

    //gets sets
    public SocioDAO getSocioDAO() {
        return socioDAO;
    }

    public void setSocioDAO(SocioDAO socioDAO) {
        this.socioDAO = socioDAO;
    }

    public RolDAO getRolDAO() {
        return rolDAO;
    }

    public void setRolDAO(RolDAO rolDAO) {
        this.rolDAO = rolDAO;
    }

    public EmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

}
