/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.dao.impl;

import co.edu.udea.dao.PQRSDAO;
import co.edu.udea.model.Pqrs;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author wondercode
 */
@Stateless
public class PQRSDAOImpl implements PQRSDAO {

    @PersistenceContext(unitName = "PQRSPU")
    private EntityManager em;

    @Override
    public void addPqrs(Pqrs pqrs) {
        
            em.persist(pqrs);
        
    }

    @Override
    public void editPqrs(Pqrs pqrs) {
        em.merge(pqrs);
    }

    @Override
    public void deletePqrs(String id) {
        em.remove(getPqrs(id));
    }

    @Override
    public Pqrs getPqrs(String id) {
        return em.find(Pqrs.class, id);
    }

    @Override
    public List<Pqrs> getAllPqrs() {
        return em.createNamedQuery("Pqrs.findAll").getResultList();
    }

}
