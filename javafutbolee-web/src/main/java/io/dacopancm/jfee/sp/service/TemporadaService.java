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
import io.dacopancm.jfee.sp.dao.TemporadaDAO;
import io.dacopancm.jfee.sp.model.Temporada;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dacopan
 */
@Service("TemporadaService")
@Transactional(readOnly = true)
public class TemporadaService implements Serializable {

    @Autowired
    TemporadaDAO temporadaDAO;

    public TemporadaService() {
    }

    //temporadaDAO
    @Transactional(readOnly = false)
    public void addTemporada(Temporada eq) {
        temporadaDAO.save(eq);
    }

    @Transactional(readOnly = false)
    public void updateTemporada(Temporada eq) throws JfeeCustomException {
        temporadaDAO.update(eq);
    }

    @Transactional(readOnly = false)
    public void deleteTemporada(Temporada eq) {
        temporadaDAO.delete(eq);
    }

    public void evictTemporada(Temporada eq) {
        temporadaDAO.evict(eq);
    }

    public Temporada getTemporadaById(int id) {

        return temporadaDAO.getById(id);
    }

    public List<Temporada> getTemporadaAll() {
        return temporadaDAO.getAll();
    }

    public Temporada getTemporadaByNombre(String nombre) {
        return temporadaDAO.getByNombre(nombre);
    }

    public TemporadaDAO getTemporadaDAO() {
        return temporadaDAO;
    }

    public void setTemporadaDAO(TemporadaDAO temporadaDAO) {
        this.temporadaDAO = temporadaDAO;
    }

}
