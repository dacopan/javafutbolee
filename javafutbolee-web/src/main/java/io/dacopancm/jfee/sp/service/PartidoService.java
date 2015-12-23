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
import io.dacopancm.jfee.sp.dao.PartidoDAO;
import io.dacopancm.jfee.sp.model.Partido;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dacopan
 */
@Service("PartidoService")
@Transactional(readOnly = true)
public class PartidoService implements java.io.Serializable {

    @Autowired
    PartidoDAO partidoDAO;

    public PartidoService() {
    }

    @Transactional(readOnly = false)
    public void addPartido(Partido eq) throws JfeeCustomException {
        //TODO comprobar q no existe ya uno así
        partidoDAO.save(eq);
    }

    @Transactional(readOnly = false)
    public void updatePartido(Partido eq) throws JfeeCustomException {
        //TODO comprobar q no existe ya uno así
        partidoDAO.update(eq);
    }

    @Transactional(readOnly = false)
    public void deletePartido(Partido eq) {
        partidoDAO.delete(eq);
    }

    public void evictPartido(Partido eq) {
        partidoDAO.evict(eq);
    }

    public Partido getPartidoById(int id) {
        return partidoDAO.getById(id);
    }

    public List<Partido> getPartidoAll() {
        return partidoDAO.getAll();
    }

    public List<Partido> getPartidoByTemporadaID(Integer tpdId) {
        return partidoDAO.getByTemporadaID(tpdId);
    }

    public PartidoDAO getPartidoDAO() {
        return partidoDAO;
    }

    public void setPartidoDAO(PartidoDAO partidoDAO) {
        this.partidoDAO = partidoDAO;
    }

}
