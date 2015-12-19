/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.dacopancm.jfee.sp.service;

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

}
