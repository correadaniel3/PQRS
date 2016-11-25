/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.dao;

import co.edu.udea.dao.exceptions.NonexistentEntityException;
import co.edu.udea.dao.exceptions.PreexistingEntityException;
import co.edu.udea.dao.exceptions.RollbackFailureException;
import co.edu.udea.model.Estado;
import co.edu.udea.model.EstadoPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.udea.model.Pqrs;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author LEONDAVID
 */
public class EstadoJpaController implements Serializable {

    public EstadoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estado estado) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (estado.getEstadoPK() == null) {
            estado.setEstadoPK(new EstadoPK());
        }
        estado.getEstadoPK().setCodigoPQRS(estado.getPqrs().getCodigo());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Pqrs pqrs = estado.getPqrs();
            if (pqrs != null) {
                pqrs = em.getReference(pqrs.getClass(), pqrs.getCodigo());
                estado.setPqrs(pqrs);
            }
            em.persist(estado);
            if (pqrs != null) {
                pqrs.getEstadoList().add(estado);
                pqrs = em.merge(pqrs);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findEstado(estado.getEstadoPK()) != null) {
                throw new PreexistingEntityException("Estado " + estado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estado estado) throws NonexistentEntityException, RollbackFailureException, Exception {
        estado.getEstadoPK().setCodigoPQRS(estado.getPqrs().getCodigo());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Estado persistentEstado = em.find(Estado.class, estado.getEstadoPK());
            Pqrs pqrsOld = persistentEstado.getPqrs();
            Pqrs pqrsNew = estado.getPqrs();
            if (pqrsNew != null) {
                pqrsNew = em.getReference(pqrsNew.getClass(), pqrsNew.getCodigo());
                estado.setPqrs(pqrsNew);
            }
            estado = em.merge(estado);
            if (pqrsOld != null && !pqrsOld.equals(pqrsNew)) {
                pqrsOld.getEstadoList().remove(estado);
                pqrsOld = em.merge(pqrsOld);
            }
            if (pqrsNew != null && !pqrsNew.equals(pqrsOld)) {
                pqrsNew.getEstadoList().add(estado);
                pqrsNew = em.merge(pqrsNew);
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
                EstadoPK id = estado.getEstadoPK();
                if (findEstado(id) == null) {
                    throw new NonexistentEntityException("The estado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(EstadoPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Estado estado;
            try {
                estado = em.getReference(Estado.class, id);
                estado.getEstadoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estado with id " + id + " no longer exists.", enfe);
            }
            Pqrs pqrs = estado.getPqrs();
            if (pqrs != null) {
                pqrs.getEstadoList().remove(estado);
                pqrs = em.merge(pqrs);
            }
            em.remove(estado);
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

    public List<Estado> findEstadoEntities() {
        return findEstadoEntities(true, -1, -1);
    }

    public List<Estado> findEstadoEntities(int maxResults, int firstResult) {
        return findEstadoEntities(false, maxResults, firstResult);
    }

    private List<Estado> findEstadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estado.class));
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

    public Estado findEstado(EstadoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estado> rt = cq.from(Estado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
