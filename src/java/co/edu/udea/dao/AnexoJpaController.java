/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.dao;

import co.edu.udea.dao.exceptions.NonexistentEntityException;
import co.edu.udea.dao.exceptions.PreexistingEntityException;
import co.edu.udea.dao.exceptions.RollbackFailureException;
import co.edu.udea.model.Anexo;
import co.edu.udea.model.AnexoPK;
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
public class AnexoJpaController implements Serializable {

    public AnexoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Anexo anexo) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (anexo.getAnexoPK() == null) {
            anexo.setAnexoPK(new AnexoPK());
        }
        anexo.getAnexoPK().setCodigoRelacion(anexo.getPqrs().getCodigo());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Pqrs pqrs = anexo.getPqrs();
            if (pqrs != null) {
                pqrs = em.getReference(pqrs.getClass(), pqrs.getCodigo());
                anexo.setPqrs(pqrs);
            }
            em.persist(anexo);
            if (pqrs != null) {
                pqrs.getAnexoList().add(anexo);
                pqrs = em.merge(pqrs);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findAnexo(anexo.getAnexoPK()) != null) {
                throw new PreexistingEntityException("Anexo " + anexo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Anexo anexo) throws NonexistentEntityException, RollbackFailureException, Exception {
        anexo.getAnexoPK().setCodigoRelacion(anexo.getPqrs().getCodigo());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Anexo persistentAnexo = em.find(Anexo.class, anexo.getAnexoPK());
            Pqrs pqrsOld = persistentAnexo.getPqrs();
            Pqrs pqrsNew = anexo.getPqrs();
            if (pqrsNew != null) {
                pqrsNew = em.getReference(pqrsNew.getClass(), pqrsNew.getCodigo());
                anexo.setPqrs(pqrsNew);
            }
            anexo = em.merge(anexo);
            if (pqrsOld != null && !pqrsOld.equals(pqrsNew)) {
                pqrsOld.getAnexoList().remove(anexo);
                pqrsOld = em.merge(pqrsOld);
            }
            if (pqrsNew != null && !pqrsNew.equals(pqrsOld)) {
                pqrsNew.getAnexoList().add(anexo);
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
                AnexoPK id = anexo.getAnexoPK();
                if (findAnexo(id) == null) {
                    throw new NonexistentEntityException("The anexo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AnexoPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Anexo anexo;
            try {
                anexo = em.getReference(Anexo.class, id);
                anexo.getAnexoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The anexo with id " + id + " no longer exists.", enfe);
            }
            Pqrs pqrs = anexo.getPqrs();
            if (pqrs != null) {
                pqrs.getAnexoList().remove(anexo);
                pqrs = em.merge(pqrs);
            }
            em.remove(anexo);
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

    public List<Anexo> findAnexoEntities() {
        return findAnexoEntities(true, -1, -1);
    }

    public List<Anexo> findAnexoEntities(int maxResults, int firstResult) {
        return findAnexoEntities(false, maxResults, firstResult);
    }

    private List<Anexo> findAnexoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Anexo.class));
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

    public Anexo findAnexo(AnexoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Anexo.class, id);
        } finally {
            em.close();
        }
    }

    public int getAnexoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Anexo> rt = cq.from(Anexo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
