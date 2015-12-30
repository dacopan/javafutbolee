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
import io.dacopancm.jfee.sp.dao.CiudadDAO;
import io.dacopancm.jfee.sp.model.Ciudad;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dacopan
 */
@Service("CiudadService")
@Transactional(readOnly = true)
public class CiudadService implements Serializable {
    
    @Autowired
    CiudadDAO ciudadDAO;
    
    public CiudadService() {
    }

    //ciudad
    @Transactional(readOnly = false)
    public void addCiudad(Ciudad eq) {
        //TODO comprobar q no existe ya uno así
        ciudadDAO.save(eq);
    }
    
    @Transactional(readOnly = false)
    public void updateCiudad(Ciudad eq) throws JfeeCustomException {
        //TODO comprobar q no existe ya uno así
        ciudadDAO.update(eq);
    }
    
    @Transactional(readOnly = false)
    public void deleteCiudad(Ciudad eq) {
        ciudadDAO.delete(eq);
    }
    
    public void evictCiudad(Ciudad eq) {
        ciudadDAO.evict(eq);
    }
    
    public Ciudad getCiudadById(int id) {
        return ciudadDAO.getById(id);
    }
    
    public List<Ciudad> getCiudadAll() {
        return ciudadDAO.getAll();
    }

    public List<Ciudad> getByPaisId(int id) {
        return ciudadDAO.getByPaisId(id);
    }
    
    public CiudadDAO getCiudadDAO() {
        return ciudadDAO;
    }
    
    public void setCiudadDAO(CiudadDAO ciudadDAO) {
        this.ciudadDAO = ciudadDAO;
    }
    
}
