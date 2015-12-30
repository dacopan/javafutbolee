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

import io.dacopancm.jfee.sp.model.Plan;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dacopan
 */
@Repository
public class PlanDAO implements java.io.Serializable {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Plan eq) {
        getSessionFactory().getCurrentSession().save(eq);
    }

    public void update(Plan eq) {
        getSessionFactory().getCurrentSession().update(eq);
    }

    public void delete(Plan eq) {
        getSessionFactory().getCurrentSession().delete(eq);
    }

    public void evict(Plan eq) {
        getSessionFactory().getCurrentSession().evict(eq);
    }

    public Plan getById(int id) {
        List list = getSessionFactory().getCurrentSession().createQuery("from Plan where plnId=:plnId").setParameter("plnId", id).list();
        return list.size() < 1 ? null : (Plan) list.get(0);
    }

    public List<Plan> getAll() {
        return getSessionFactory().getCurrentSession().createQuery("from Plan").list();
    }

    public Plan getByNombre(String nombre) {
        List list = getSessionFactory().getCurrentSession().createQuery("from Plan where plnNombre=:plnNombre").setParameter("plnNombre", nombre).list();
        return list.size() < 1 ? null : (Plan) list.get(0);
    }

}
