/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.dacopancm.jfee.sp.service;

import io.dacopancm.jfee.exceptions.JfeeCustomException;
import io.dacopancm.jfee.sp.dao.UsuarioDAO;
import io.dacopancm.jfee.sp.model.Personal;
import io.dacopancm.jfee.sp.model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author anunaki
 */
@Service("UsuarioService")
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;
    @Autowired
    EmailService emailService;

    @Override
    public Usuario getUsuario(String usr_ci) {
        return usuarioDAO.getUsuario(usr_ci);
    }

    @Override
    public void updateUsuario(Usuario u) {
        Usuario old = usuarioDAO.getUsuario(u.getUsrCi());

        if (!old.getUsrEmail().equalsIgnoreCase(u.getUsrEmail())) {
            u.setUsrActivationHash(java.util.UUID.randomUUID().toString().replace("-", ""));

            //confirm email url
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String confirmUrl = "confirmEmail.xhtml?h=" + u.getUsrActivationHash() + "&c=" + passwordEncoder.encode(u.getUsrCi());
            emailService.sendConfirmationEmail(u, "usuario", "", confirmUrl);
        }

        //TODO: verify if email already in use
        usuarioDAO.evictUsuario(old);
        usuarioDAO.updateUsuario(u);
    }

    @Override
    public void failLoginUser(String ci) {
        Usuario old = usuarioDAO.getUsuario(ci);
        if (old != null) {
            int loginFails = old.getUsrFailedLogin() == null ? 0 : old.getUsrFailedLogin();

            old.setUsrFailedLogin(loginFails + 1);
            if (old.getUsrFailedLogin() >= 3) {
                old.setUsrActive(false);
                emailService.sendAccountLockedEmail(old, "usuario");
            }
            usuarioDAO.updateUsuario(old);
        }
    }

    @Override
    public void requestPassword(String ci) {
        Usuario old = usuarioDAO.getUsuario(ci);
        if (old != null) {
            old.setUsrFailedLogin(0);
            old.setUsrActive(true);

            String tmpPassword = org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(12);
            old.setUsrPassword(new BCryptPasswordEncoder().encode(tmpPassword));

            emailService.sendNewPasswordEmail(old, "usuario", tmpPassword);
            usuarioDAO.updateUsuario(old);
        }
    }

}
