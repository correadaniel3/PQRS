/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.dao.impl;

import co.edu.udea.dao.ClienteDAO;
import co.edu.udea.model.Cliente;
import co.edu.udea.model.ClientePK;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author wondercode
 */
@Stateless
public class ClienteDAOImpl implements ClienteDAO{
    @PersistenceContext(unitName="PQRSPU")
    private EntityManager em;
    @Override
    public Cliente get(ClientePK pk) {
        return em.find(Cliente.class, pk);
    }
    
}
