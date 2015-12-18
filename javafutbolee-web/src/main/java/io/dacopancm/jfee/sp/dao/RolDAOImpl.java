/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.dacopancm.jfee.sp.dao;

import io.dacopancm.jfee.sp.model.Rol;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author anunaki
 */
@Repository
public class RolDAOImpl implements RolDAO {

    @Override
    public Rol getRol(int id) {
        Rol role = (Rol) getCurrentSession().load(Rol.class, id);
        return role;
    }

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Rol> getRoles() {
        return getCurrentSession().createQuery("from Rol").list();
    }

}
