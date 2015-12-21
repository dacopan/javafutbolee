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
import io.dacopancm.jfee.sp.dao.LocalidadDAO;
import io.dacopancm.jfee.sp.model.Localidad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dacopan
 */
@Service("LocalidadService")
@Transactional(readOnly = true)
public class LocalidadService implements java.io.Serializable {

    @Autowired
    LocalidadDAO localidadDAO;

    public LocalidadService() {
    }

    @Transactional(readOnly = false)
    public void addEquipo(Localidad eq) {
        localidadDAO.save(eq);
    }

    @Transactional(readOnly = false)
    public void updateEquipo(Localidad eq) throws JfeeCustomException {
        //TODO comprobar q no existe ya uno así
        localidadDAO.update(eq);
    }

    @Transactional(readOnly = false)
    public void deleteEquipo(Localidad eq) {
        localidadDAO.delete(eq);
    }

    public void evictEquipo(Localidad eq) {
        localidadDAO.evict(eq);
    }

    public Localidad getEquipoById(int id) {
        return localidadDAO.getById(id);
    }

    public List<Localidad> getLocalidadAll() {
        return localidadDAO.getAll();
    }

    public LocalidadDAO getLocalidadDAO() {
        return localidadDAO;
    }

    public void setLocalidadDAO(LocalidadDAO localidadDAO) {
        this.localidadDAO = localidadDAO;
    }

}
