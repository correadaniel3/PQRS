/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.dao.impl;

import co.edu.udea.dao.AeropuertoDAO;
import co.edu.udea.model.Aeropuerto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author wondercode
 */
@Stateless
public class AeropuertoDAOImpl implements AeropuertoDAO {
    @PersistenceContext(unitName="PQRSPU")
    private EntityManager em;
    @Override
    public Aeropuerto get(String codigo) {
        return em.find(Aeropuerto.class,codigo);
    }

    @Override
    public List<Aeropuerto> getAll() {
        return em.createNamedQuery("Aeropuerto.findAll").getResultList();
    }
    
}
