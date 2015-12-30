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
import io.dacopancm.jfee.sp.dao.PaisDAO;
import io.dacopancm.jfee.sp.model.Pais;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dacopan
 */
@Service("PaisService")
@Transactional(readOnly = true)
public class PaisService implements Serializable {

    @Autowired
    PaisDAO paisDAO;

    public PaisService() {
    }

    //pais
    @Transactional(readOnly = false)
    public void addPais(Pais eq) {
        //TODO comprobar q no existe ya uno así
        paisDAO.save(eq);
    }

    @Transactional(readOnly = false)
    public void updatePais(Pais eq) throws JfeeCustomException {
        //TODO comprobar q no existe ya uno así
        paisDAO.update(eq);
    }

    @Transactional(readOnly = false)
    public void deletePais(Pais eq) {
        paisDAO.delete(eq);
    }

    public void evictPais(Pais eq) {
        paisDAO.evict(eq);
    }

    public Pais getPaisById(int id) {
        return paisDAO.getById(id);
    }

    public List<Pais> getPaisAll() {
        return paisDAO.getAll();
    }

    public PaisDAO getPaisDAO() {
        return paisDAO;
    }

    public void setPaisDAO(PaisDAO paisDAO) {
        this.paisDAO = paisDAO;
    }

}
