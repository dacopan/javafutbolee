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
import io.dacopancm.jfee.sp.dao.EquipoDAO;
import io.dacopancm.jfee.sp.model.Equipo;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dacopan
 */
@Service("EquipoService")
@Transactional(readOnly = true)
public class EquipoService implements Serializable {

    @Autowired
    EquipoDAO equipoDAO;

    public EquipoService() {
    }

    //equipo
    @Transactional(readOnly = false)
    public void addEquipo(Equipo eq) {
        equipoDAO.save(eq);
    }

    @Transactional(readOnly = false)
    public void updateEquipo(Equipo eq) throws JfeeCustomException{
        //TODO comprobar q no existe ya uno así
        equipoDAO.update(eq);
    }

    @Transactional(readOnly = false)
    public void deleteEquipo(Equipo eq) {
        equipoDAO.delete(eq);
    }

    public void evictEquipo(Equipo eq) {
        equipoDAO.evict(eq);
    }

    public Equipo getEquipoById(int id) {
        return equipoDAO.getById(id);
    }

    public List<Equipo> getEquipoAll() {
        return equipoDAO.getAll();
    }
    public Equipo getEquipoByNombre(String nombre) {
        return equipoDAO.getByNombre(nombre);
    }

    public EquipoDAO getEquipoDAO() {
        return equipoDAO;
    }

    public void setEquipoDAO(EquipoDAO equipoDAO) {
        this.equipoDAO = equipoDAO;
    }

}
