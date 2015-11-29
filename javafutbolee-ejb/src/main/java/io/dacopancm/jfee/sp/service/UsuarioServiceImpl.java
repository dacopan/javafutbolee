/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.dacopancm.jfee.sp.service;

import io.dacopancm.jfee.sp.dao.UsuarioDAO;
import io.dacopancm.jfee.sp.model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author anunaki
 */
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public Usuario getUsuario(String usr_ci) {
        return usuarioDAO.getUsuario(usr_ci);
    }

}
