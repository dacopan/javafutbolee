/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.dacopancm.jfee.sp.dao;

import io.dacopancm.jfee.sp.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author anunaki
 */
@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Usuario getUsuario(String usr_ci) {
        List<Usuario> userList = new ArrayList<Usuario>();
        Query query = openSession().createQuery("from Usuario u where u.usrCi = :usrCi");
        query.setParameter("usrCi", usr_ci);
        userList = query.list();
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

}
