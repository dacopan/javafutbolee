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
import io.dacopancm.jfee.sp.dao.BoletoDAO;
import io.dacopancm.jfee.sp.dao.SocioDAO;
import io.dacopancm.jfee.sp.model.Boleto;
import io.dacopancm.jfee.sp.model.Socio;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dacopan
 */
@Service("BoletoService")
@Transactional(readOnly = true)
public class BoletoService implements Serializable {

    @Autowired
    BoletoDAO boletoDAO;
    @Autowired
    SocioDAO socioDAO;

    public BoletoService() {
    }

    //boleto
    @Transactional(readOnly = false)
    public void addBoleto(Boleto eq) throws JfeeCustomException {
        boletoDAO.save(eq);
    }

    public void addBoleto(Boleto b, String username) throws JfeeCustomException {
        Socio s = socioDAO.getSocioByCi(username);
        if (s == null) {
            throw new JfeeCustomException("No es un socio que pueda reservar boletos.");
        }
        b.setSocio(s);
        socioDAO.evictSocio(s);
        addBoleto(b);
    }

    @Transactional(readOnly = false)
    public void updateBoleto(Boleto eq) throws JfeeCustomException {
        //TODO comprobar q no existe ya uno así
        boletoDAO.update(eq);
    }

    @Transactional(readOnly = false)
    public void deleteBoleto(Boleto eq) throws JfeeCustomException{
        boletoDAO.delete(eq);
    }

    public void evictBoleto(Boleto eq) {
        boletoDAO.evict(eq);
    }

    public Boleto getBoletoById(int id) {
        return boletoDAO.getById(id);
    }

    public List<Boleto> getBoletoAll() {
        return boletoDAO.getAll();
    }

    public List<Boleto> getBoletoBySocId(int socId) {
        return boletoDAO.getBySocId(socId);
    }

    public List<Boleto> getBoletoByPartidoId(int prtId) {
        return boletoDAO.getByPartidoId(prtId);
    }

    public Boleto getBoletoByPartidoIdAndSocCi(int prtId, String socCi) {
        return boletoDAO.getByPartidoIdAndSocCi(prtId, socCi);
    }

    public BoletoDAO getBoletoDAO() {
        return boletoDAO;
    }

    public void setBoletoDAO(BoletoDAO boletoDAO) {
        this.boletoDAO = boletoDAO;
    }

}
