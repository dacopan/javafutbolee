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

import io.dacopancm.jfee.sp.model.Socio;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dacopan
 */
@Repository
public class SocioDAO implements java.io.Serializable {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addSocio(Socio p) {
        getSessionFactory().getCurrentSession().save(p);
    }

    public void deleteSocio(Socio p) {
        getSessionFactory().getCurrentSession().createQuery("delete from Boleto where socio.socId=:socId").setParameter("socId", p.getSocId())
                .executeUpdate();
        getSessionFactory().getCurrentSession().delete(p);
    }

    public void updateSocio(Socio p) {
        getSessionFactory().getCurrentSession().update(p);
    }

    public void evictSocio(Socio p) {
        getSessionFactory().getCurrentSession().evict(p);
    }

    public Socio getSocioById(int id) {
        List list = getSessionFactory().getCurrentSession().createQuery("from Socio where socId=:socId").setParameter("socId", id).list();
        return list.size() < 1 ? null : (Socio) list.get(0);
    }

    public Socio getSocioByCi(String ci) {
        List list = getSessionFactory().getCurrentSession().createQuery("from Socio where usuario.usrCi=:usrCi").setParameter("usrCi", ci).list();
        return list.size() < 1 ? null : (Socio) list.get(0);
    }

    public List<Socio> getSocios() {
        return getSessionFactory().getCurrentSession().createQuery("from Socio").list();
    }

}
