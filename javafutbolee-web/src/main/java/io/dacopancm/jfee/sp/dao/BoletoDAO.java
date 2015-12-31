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

import io.dacopancm.jfee.sp.model.Boleto;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dacopan
 */
@Repository
public class BoletoDAO implements java.io.Serializable {

    @Autowired
    private SessionFactory sessionFactory;

    public BoletoDAO() {
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Boleto eq) {
        getSessionFactory().getCurrentSession().save(eq);
    }

    public void update(Boleto eq) {
        getSessionFactory().getCurrentSession().update(eq);
    }

    public void delete(Boleto eq) {
        getSessionFactory().getCurrentSession().delete(eq);
    }

    public void evict(Boleto eq) {
        getSessionFactory().getCurrentSession().evict(eq);
    }

    public Boleto getById(int id) {
        List list = getSessionFactory().getCurrentSession().createQuery("from Boleto where bolId=:bolId").setParameter("bolId", id).list();
        return list.size() < 1 ? null : (Boleto) list.get(0);
    }

    public List<Boleto> getAll() {
        return getSessionFactory().getCurrentSession().createQuery("from Boleto").list();
    }

    public List<Boleto> getBySocId(int socId) {
        return getSessionFactory().getCurrentSession().createQuery("from Boleto where socio.socId=:socId").setParameter("socId", socId).list();
    }

    public List<Boleto> getByPartidoId(int prtId) {
        return getSessionFactory().getCurrentSession().createQuery("from Boleto where partidoPrecio.partido.prtId=:prtId").setParameter("prtId", prtId).list();
    }

    public Boleto getByPartidoIdAndSocCi(int prtId, String socCi) {
        List list = getSessionFactory().getCurrentSession().createQuery("from Boleto where (socio.usuario.usrCi=:socCi and partidoPrecio.partido.prtId=:prtId)").setParameter("socCi", socCi).setParameter("prtId", prtId).list();
        return list.size() < 1 ? null : (Boleto) list.get(0);
    }
}
