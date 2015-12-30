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

import io.dacopancm.jfee.sp.model.Ciudad;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dacopan
 */
@Repository
public class CiudadDAO implements java.io.Serializable {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Ciudad eq) {
        getSessionFactory().getCurrentSession().save(eq);
    }

    public void update(Ciudad eq) {
        getSessionFactory().getCurrentSession().update(eq);
    }

    public void delete(Ciudad eq) {
        getSessionFactory().getCurrentSession().delete(eq);
    }

    public void evict(Ciudad eq) {
        getSessionFactory().getCurrentSession().evict(eq);
    }

    public Ciudad getById(int id) {
        List list = getSessionFactory().getCurrentSession().createQuery("from Ciudad where ciuId=:ciuId").setParameter("ciuId", id).list();
        return list.size() < 1 ? null : (Ciudad) list.get(0);
    }

    public List<Ciudad> getAll() {
        return getSessionFactory().getCurrentSession().createQuery("from Ciudad").list();
    }

    public List<Ciudad> getByPaisId(int id) {
        return getSessionFactory().getCurrentSession().createQuery("from Ciudad where pais.paiId=:paiId").setParameter("paiId", id).list();
    }

}
