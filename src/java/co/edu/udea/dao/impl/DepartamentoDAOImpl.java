/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.dao.impl;

import co.edu.udea.dao.DepartamentoDAO;
import co.edu.udea.model.Departamento;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author corre
 */
public class DepartamentoDAOImpl implements DepartamentoDAO {
    @PersistenceContext(unitName="PQRSPU")
    private EntityManager em;
    @Override
    public Departamento get(String codigo) {
        return em.find(Departamento.class, codigo);
    }
    
}
