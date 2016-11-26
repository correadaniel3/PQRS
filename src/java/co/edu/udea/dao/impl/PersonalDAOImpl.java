/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.dao.impl;

import co.edu.udea.dao.PersonalDAO;
import co.edu.udea.model.Personal;
import co.edu.udea.model.PersonalPK;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author corre
 */
public class PersonalDAOImpl implements PersonalDAO {

    @PersistenceContext(unitName="PQRSPU")
    private EntityManager em;
    @Override
    public Personal get(PersonalPK pk) {
        return em.find(Personal.class, pk);
    }
    
}
