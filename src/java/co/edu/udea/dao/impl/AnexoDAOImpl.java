/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.dao.impl;

import co.edu.udea.dao.AnexoDAO;
import co.edu.udea.model.Anexo;
import co.edu.udea.model.AnexoPK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author wondercode
 */
@Stateless
public class AnexoDAOImpl implements AnexoDAO{
    @PersistenceContext(unitName="PQRSPU")
    private EntityManager em;
    @Override
    public Anexo get(AnexoPK id) {
        return em.find(Anexo.class, id);
    }

    @Override
    public List<Anexo> getAll() {
        return em.createNamedQuery("Anexo.findAll").getResultList();
    }

    @Override
    public void save(Anexo a) {
        em.persist(a);
    }
    
}
