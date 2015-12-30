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

import io.dacopancm.jfee.sp.model.Pais;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dacopan
 */
@Repository
public class PaisDAO implements java.io.Serializable {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Pais eq) {
        getSessionFactory().getCurrentSession().save(eq);
    }

    public void update(Pais eq) {
        getSessionFactory().getCurrentSession().update(eq);
    }

    public void delete(Pais eq) {
        getSessionFactory().getCurrentSession().delete(eq);
    }

    public void evict(Pais eq) {
        getSessionFactory().getCurrentSession().evict(eq);
    }

    public Pais getById(int id) {
        List list = getSessionFactory().getCurrentSession().createQuery("from Pais where paiId=:paiId").setParameter("paiId", id).list();
        return list.size() < 1 ? null : (Pais) list.get(0);
    }

    public List<Pais> getAll() {
        return getSessionFactory().getCurrentSession().createQuery("from Pais").list();
    }

}
