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
package io.dacopancm.jfee.sp.service;

import io.dacopancm.jfee.exceptions.JfeeCustomException;
import io.dacopancm.jfee.sp.dao.EstadioDAO;
import io.dacopancm.jfee.sp.model.Estadio;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dacopan
 */
@Service("EstadioService")
@Transactional(readOnly = true)
public class EstadioService implements Serializable {

    @Autowired
    EstadioDAO estadioDao;

    public EstadioService() {
    }

    //estadioDao
    @Transactional(readOnly = false)
    public void addEstadio(Estadio eq) {
        estadioDao.save(eq);
    }

    @Transactional(readOnly = false)
    public void updateEstadio(Estadio eq) throws JfeeCustomException {
        estadioDao.update(eq);
    }

    @Transactional(readOnly = false)
    public void deleteEstadio(Estadio eq) {
        estadioDao.delete(eq);
    }

    public void evictEstadio(Estadio eq) {
        estadioDao.evict(eq);
    }

    public Estadio getEstadioById(int id) {
        return estadioDao.getById(id);
    }

    public List<Estadio> getEstadioAll() {
        return estadioDao.getAll();
    }

    public Estadio getEstadioByNombre(String nombre) {
        return estadioDao.getByNombre(nombre); 
    }

    public EstadioDAO getEstadioDao() {
        return estadioDao;
    }

    public void setEstadioDao(EstadioDAO estadioDao) {
        this.estadioDao = estadioDao;
    }

}
