/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.dacopancm.jfee.sp.dao;

import io.dacopancm.jfee.sp.model.Rol;
import java.util.List;

/**
 *
 * @author anunaki
 */
public interface RolDAO {

    public Rol getRol(int id);
    public List<Rol> getRoles();
}
