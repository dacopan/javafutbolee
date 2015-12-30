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
import io.dacopancm.jfee.sp.dao.FormaPagoDAO;
import io.dacopancm.jfee.sp.model.FormaPago;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dacopan
 */
@Service("FormaPagoService")
@Transactional(readOnly = true)
public class FormaPagoService implements Serializable {

    @Autowired
    FormaPagoDAO formaPagoDAO;

    public FormaPagoService() {
    }

    //formaPago
    @Transactional(readOnly = false)
    public void addFormaPago(FormaPago eq) {
        //TODO comprobar q no existe ya uno así
        formaPagoDAO.save(eq);
    }

    @Transactional(readOnly = false)
    public void updateFormaPago(FormaPago eq) throws JfeeCustomException {
        //TODO comprobar q no existe ya uno así
        formaPagoDAO.update(eq);
    }

    @Transactional(readOnly = false)
    public void deleteFormaPago(FormaPago eq) {
        formaPagoDAO.delete(eq);
    }

    public void evictFormaPago(FormaPago eq) {
        formaPagoDAO.evict(eq);
    }

    public FormaPago getFormaPagoById(int id) {
        return formaPagoDAO.getById(id);
    }

    public List<FormaPago> getFormaPagoAll() {
        return formaPagoDAO.getAll();
    }

    public FormaPagoDAO getFormaPagoDAO() {
        return formaPagoDAO;
    }

    public void setFormaPagoDAO(FormaPagoDAO formaPagoDAO) {
        this.formaPagoDAO = formaPagoDAO;
    }

}
