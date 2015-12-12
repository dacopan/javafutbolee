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
package io.dacopancm.jfee.sp.dao;

import io.dacopancm.jfee.sp.model.Personal;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dacopan
 */
@Repository
public class PersonalDAO implements java.io.Serializable{

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    public void addPersonal(Personal p) {
        getSessionFactory().getCurrentSession().save(p);
    }

    
    public void deletePersonal(Personal p) {
        getSessionFactory().getCurrentSession().delete(p);
    }

    
    public void updatePersonal(Personal p) {
        getSessionFactory().getCurrentSession().update(p);
    }

    
    public Personal getPersonalById(int id) {
        List list = getSessionFactory().getCurrentSession().createQuery("from Personal where psnId=?").setParameter(0, id).list();
        return list.size() < 1 ? null : (Personal) list.get(0);
    }

    
    public Personal getPersonalByCi(String ci) {
        List list = getSessionFactory().getCurrentSession().createQuery("from Personal where usuario.usr_ci=?").setParameter(0, ci).list();
        return list.size() < 1 ? null : (Personal) list.get(0);
    }

    
    public List<Personal> getPersonals() {
        return getSessionFactory().getCurrentSession().createQuery("from Personal").list();
    }

}
