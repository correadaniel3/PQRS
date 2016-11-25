/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.dao;

import co.edu.udea.dao.exceptions.NonexistentEntityException;
import co.edu.udea.dao.exceptions.PreexistingEntityException;
import co.edu.udea.dao.exceptions.RollbackFailureException;
import co.edu.udea.model.Cliente;
import co.edu.udea.model.ClientePK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.udea.model.Pqrs;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author LEONDAVID
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (cliente.getClientePK() == null) {
            cliente.setClientePK(new ClientePK());
        }
        if (cliente.getPqrsList() == null) {
            cliente.setPqrsList(new ArrayList<Pqrs>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Pqrs> attachedPqrsList = new ArrayList<Pqrs>();
            for (Pqrs pqrsListPqrsToAttach : cliente.getPqrsList()) {
                pqrsListPqrsToAttach = em.getReference(pqrsListPqrsToAttach.getClass(), pqrsListPqrsToAttach.getCodigo());
                attachedPqrsList.add(pqrsListPqrsToAttach);
            }
            cliente.setPqrsList(attachedPqrsList);
            em.persist(cliente);
            for (Pqrs pqrsListPqrs : cliente.getPqrsList()) {
                Cliente oldClienteOfPqrsListPqrs = pqrsListPqrs.getCliente();
                pqrsListPqrs.setCliente(cliente);
                pqrsListPqrs = em.merge(pqrsListPqrs);
                if (oldClienteOfPqrsListPqrs != null) {
                    oldClienteOfPqrsListPqrs.getPqrsList().remove(pqrsListPqrs);
                    oldClienteOfPqrsListPqrs = em.merge(oldClienteOfPqrsListPqrs);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCliente(cliente.getClientePK()) != null) {
                throw new PreexistingEntityException("Cliente " + cliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getClientePK());
            List<Pqrs> pqrsListOld = persistentCliente.getPqrsList();
            List<Pqrs> pqrsListNew = cliente.getPqrsList();
            List<Pqrs> attachedPqrsListNew = new ArrayList<Pqrs>();
            for (Pqrs pqrsListNewPqrsToAttach : pqrsListNew) {
                pqrsListNewPqrsToAttach = em.getReference(pqrsListNewPqrsToAttach.getClass(), pqrsListNewPqrsToAttach.getCodigo());
                attachedPqrsListNew.add(pqrsListNewPqrsToAttach);
            }
            pqrsListNew = attachedPqrsListNew;
            cliente.setPqrsList(pqrsListNew);
            cliente = em.merge(cliente);
            for (Pqrs pqrsListOldPqrs : pqrsListOld) {
                if (!pqrsListNew.contains(pqrsListOldPqrs)) {
                    pqrsListOldPqrs.setCliente(null);
                    pqrsListOldPqrs = em.merge(pqrsListOldPqrs);
                }
            }
            for (Pqrs pqrsListNewPqrs : pqrsListNew) {
                if (!pqrsListOld.contains(pqrsListNewPqrs)) {
                    Cliente oldClienteOfPqrsListNewPqrs = pqrsListNewPqrs.getCliente();
                    pqrsListNewPqrs.setCliente(cliente);
                    pqrsListNewPqrs = em.merge(pqrsListNewPqrs);
                    if (oldClienteOfPqrsListNewPqrs != null && !oldClienteOfPqrsListNewPqrs.equals(cliente)) {
                        oldClienteOfPqrsListNewPqrs.getPqrsList().remove(pqrsListNewPqrs);
                        oldClienteOfPqrsListNewPqrs = em.merge(oldClienteOfPqrsListNewPqrs);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ClientePK id = cliente.getClientePK();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ClientePK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getClientePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<Pqrs> pqrsList = cliente.getPqrsList();
            for (Pqrs pqrsListPqrs : pqrsList) {
                pqrsListPqrs.setCliente(null);
                pqrsListPqrs = em.merge(pqrsListPqrs);
            }
            em.remove(cliente);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cliente findCliente(ClientePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
