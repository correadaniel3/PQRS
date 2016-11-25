/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.dao;

import co.edu.udea.dao.exceptions.NonexistentEntityException;
import co.edu.udea.dao.exceptions.PreexistingEntityException;
import co.edu.udea.dao.exceptions.RollbackFailureException;
import co.edu.udea.model.Aeropuerto;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author LEONDAVID
 */
public class AeropuertoJpaController implements Serializable {

    public AeropuertoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Aeropuerto aeropuerto) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(aeropuerto);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findAeropuerto(aeropuerto.getCodigo()) != null) {
                throw new PreexistingEntityException("Aeropuerto " + aeropuerto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Aeropuerto aeropuerto) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            aeropuerto = em.merge(aeropuerto);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = aeropuerto.getCodigo();
                if (findAeropuerto(id) == null) {
                    throw new NonexistentEntityException("The aeropuerto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Aeropuerto aeropuerto;
            try {
                aeropuerto = em.getReference(Aeropuerto.class, id);
                aeropuerto.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aeropuerto with id " + id + " no longer exists.", enfe);
            }
            em.remove(aeropuerto);
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

    public List<Aeropuerto> findAeropuertoEntities() {
        return findAeropuertoEntities(true, -1, -1);
    }

    public List<Aeropuerto> findAeropuertoEntities(int maxResults, int firstResult) {
        return findAeropuertoEntities(false, maxResults, firstResult);
    }

    private List<Aeropuerto> findAeropuertoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Aeropuerto.class));
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

    public Aeropuerto findAeropuerto(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Aeropuerto.class, id);
        } finally {
            em.close();
        }
    }

    public int getAeropuertoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Aeropuerto> rt = cq.from(Aeropuerto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
