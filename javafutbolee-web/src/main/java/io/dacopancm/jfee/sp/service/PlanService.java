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
import io.dacopancm.jfee.sp.dao.PlanDAO;
import io.dacopancm.jfee.sp.model.Plan;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dacopan
 */
@Service("PlanService")
@Transactional(readOnly = true)
public class PlanService implements Serializable {

    @Autowired
    PlanDAO planDAO;

    public PlanService() {
    }

    //plan
    @Transactional(readOnly = false)
    public void addPlan(Plan eq) {
        //TODO comprobar q no existe ya uno así
        planDAO.save(eq);
    }

    @Transactional(readOnly = false)
    public void updatePlan(Plan eq) throws JfeeCustomException {
        //TODO comprobar q no existe ya uno así
        planDAO.update(eq);
    }

    @Transactional(readOnly = false)
    public void deletePlan(Plan eq) {
        planDAO.delete(eq);
    }

    public void evictPlan(Plan eq) {
        planDAO.evict(eq);
    }

    public Plan getPlanById(int id) {
        return planDAO.getById(id);
    }

    public Plan getPlanByNombre(String nombre) {
        return planDAO.getByNombre(nombre);
    }

    public List<Plan> getPlanAll() {
        return planDAO.getAll();
    }

    public PlanDAO getPlanDAO() {
        return planDAO;
    }

    public void setPlanDAO(PlanDAO planDAO) {
        this.planDAO = planDAO;
    }

}
