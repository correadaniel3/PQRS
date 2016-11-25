/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.dao;

import co.edu.udea.dao.exceptions.NonexistentEntityException;
import co.edu.udea.dao.exceptions.PreexistingEntityException;
import co.edu.udea.dao.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.udea.model.Departamento;
import co.edu.udea.model.Personal;
import co.edu.udea.model.PersonalPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author LEONDAVID
 */
public class PersonalJpaController implements Serializable {

    public PersonalJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Personal personal) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (personal.getPersonalPK() == null) {
            personal.setPersonalPK(new PersonalPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Departamento codigoDepartamento = personal.getCodigoDepartamento();
            if (codigoDepartamento != null) {
                codigoDepartamento = em.getReference(codigoDepartamento.getClass(), codigoDepartamento.getCodigo());
                personal.setCodigoDepartamento(codigoDepartamento);
            }
            em.persist(personal);
            if (codigoDepartamento != null) {
                codigoDepartamento.getPersonalList().add(personal);
                codigoDepartamento = em.merge(codigoDepartamento);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPersonal(personal.getPersonalPK()) != null) {
                throw new PreexistingEntityException("Personal " + personal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Personal personal) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Personal persistentPersonal = em.find(Personal.class, personal.getPersonalPK());
            Departamento codigoDepartamentoOld = persistentPersonal.getCodigoDepartamento();
            Departamento codigoDepartamentoNew = personal.getCodigoDepartamento();
            if (codigoDepartamentoNew != null) {
                codigoDepartamentoNew = em.getReference(codigoDepartamentoNew.getClass(), codigoDepartamentoNew.getCodigo());
                personal.setCodigoDepartamento(codigoDepartamentoNew);
            }
            personal = em.merge(personal);
            if (codigoDepartamentoOld != null && !codigoDepartamentoOld.equals(codigoDepartamentoNew)) {
                codigoDepartamentoOld.getPersonalList().remove(personal);
                codigoDepartamentoOld = em.merge(codigoDepartamentoOld);
            }
            if (codigoDepartamentoNew != null && !codigoDepartamentoNew.equals(codigoDepartamentoOld)) {
                codigoDepartamentoNew.getPersonalList().add(personal);
                codigoDepartamentoNew = em.merge(codigoDepartamentoNew);
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
                PersonalPK id = personal.getPersonalPK();
                if (findPersonal(id) == null) {
                    throw new NonexistentEntityException("The personal with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PersonalPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Personal personal;
            try {
                personal = em.getReference(Personal.class, id);
                personal.getPersonalPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personal with id " + id + " no longer exists.", enfe);
            }
            Departamento codigoDepartamento = personal.getCodigoDepartamento();
            if (codigoDepartamento != null) {
                codigoDepartamento.getPersonalList().remove(personal);
                codigoDepartamento = em.merge(codigoDepartamento);
            }
            em.remove(personal);
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

    public List<Personal> findPersonalEntities() {
        return findPersonalEntities(true, -1, -1);
    }

    public List<Personal> findPersonalEntities(int maxResults, int firstResult) {
        return findPersonalEntities(false, maxResults, firstResult);
    }

    private List<Personal> findPersonalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Personal.class));
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

    public Personal findPersonal(PersonalPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Personal.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Personal> rt = cq.from(Personal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
