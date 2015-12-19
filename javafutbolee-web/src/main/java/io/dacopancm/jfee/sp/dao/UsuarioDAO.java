/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.dacopancm.jfee.sp.dao;

import io.dacopancm.jfee.sp.model.Usuario;

/**
 *
 * @author anunaki
 */
public interface UsuarioDAO {

    public Usuario getUsuario(String usrCi);

    public void updateUsuario(Usuario u);
    public void evictUsuario(Usuario u);

}
