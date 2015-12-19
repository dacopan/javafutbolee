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
import io.dacopancm.jfee.sp.dao.PersonalDAO;
import io.dacopancm.jfee.sp.dao.RolDAO;
import io.dacopancm.jfee.sp.model.Personal;
import io.dacopancm.jfee.sp.model.Usuario;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dacopan
 */
@Service("PersonalService")
@Transactional(readOnly = true)
public class PersonalService implements java.io.Serializable {

    @Autowired
    PersonalDAO personalDAO;
    @Autowired
    RolDAO rolDAO;
    @Autowired
    EmailService emailService;

    @Transactional(readOnly = false)
    public void addPersonal(Personal p) {
        //temp password
        String tmpPassword = org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(12);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(tmpPassword);
        //String hashedPassword = passwordEncoder.encode("bsc");

        //user
        Usuario u = p.getUsuario();

        u.setUsrActivationHash(java.util.UUID.randomUUID().toString().replace("-", ""));
        u.setUsrActive(false);
        u.setUsrCreationTimestamp(new Date());
        u.setUsrPassword(hashedPassword);

        u.setRole(rolDAO.getRol(p.getUsuario().getRol().getRolId()));

        //confirm email url
        String confirmUrl = "confirmEmail.xhtml?h=" + u.getUsrActivationHash() + "&c=" + passwordEncoder.encode(u.getUsrCi());

        personalDAO.addPersonal(p);
        emailService.sendAccountEmail(p.getUsuario(), p.getPsnNombre(), p.getPsnApellido(), tmpPassword, confirmUrl);
    }

    @Transactional(readOnly = false)
    public void deletePersonal(Personal p) {
        personalDAO.deletePersonal(p);
    }

    @Transactional(readOnly = false)
    public void updatePersonal(Personal p) throws JfeeCustomException {
        Personal old = personalDAO.getPersonalById(p.getPsnId());

        if (!old.getUsuario().getUsrCi().equalsIgnoreCase(p.getUsuario().getUsrCi())) {
            //intenta actualizar cedula
            Personal old2 = personalDAO.getPersonalByCi(p.getUsuario().getUsrCi());
            if (old2.getPsnId().intValue() != p.getPsnId()) {
                //traemos usuario por CI, si ya existe quiere decir q el usuario q se esta actualizando
                //quiere usar una CI q ya esta registrada
                throw new io.dacopancm.jfee.exceptions.JfeeCustomException("Ya existe usuario con esa CI");
            }
            personalDAO.evictPersonal(old2);
        }

        if (!old.getUsuario().getUsrEmail().equalsIgnoreCase(p.getUsuario().getUsrEmail())) {
            p.getUsuario().setUsrActivationHash(java.util.UUID.randomUUID().toString().replace("-", ""));

            //confirm email url
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String confirmUrl = "confirmEmail.xhtml?h=" + p.getUsuario().getUsrActivationHash() + "&c=" + passwordEncoder.encode(p.getUsuario().getUsrCi());
            emailService.sendConfirmationEmail(p.getUsuario(), p.getPsnNombre(), p.getPsnApellido(), confirmUrl);
        }

        //TODO: verify if email already in use
        p.getUsuario().setRole(rolDAO.getRol(p.getUsuario().getRol().getRolId()));
        personalDAO.evictPersonal(old);
        personalDAO.updatePersonal(p);
    }

    public Personal getPersonalById(int id) {
        return personalDAO.getPersonalById(id);
    }

    public Personal getPersonalByCi(String ci) {
        return personalDAO.getPersonalByCi(ci);
    }

    public List<Personal> getPersonals() {
        return personalDAO.getPersonals();
    }

    public PersonalDAO getPersonalDAO() {
        return personalDAO;
    }

    public void setPersonalDAO(PersonalDAO personalDAO) {
        this.personalDAO = personalDAO;
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
