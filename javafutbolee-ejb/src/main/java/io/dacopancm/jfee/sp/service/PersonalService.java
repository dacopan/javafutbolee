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

        u.setRole(rolDAO.getRol(4));

        personalDAO.addPersonal(p); 
        emailService.sendConfirmationEmail(p, tmpPassword);
    }

    @Transactional(readOnly = false)
    public void deletePersonal(Personal p) {
        personalDAO.deletePersonal(p);
    }

    @Transactional(readOnly = false)
    public void updatePersonal(Personal p) {
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
