/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.dao;

import co.edu.udea.dao.exceptions.IllegalOrphanException;
import co.edu.udea.dao.exceptions.NonexistentEntityException;
import co.edu.udea.dao.exceptions.PreexistingEntityException;
import co.edu.udea.dao.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.udea.model.Cliente;
import co.edu.udea.model.Estado;
import java.util.ArrayList;
import java.util.List;
import co.edu.udea.model.Anexo;
import co.edu.udea.model.Pqrs;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 *
 * @author LEONDAVID
 */
@Stateless
public class PqrsJpaController implements Serializable {

    public PqrsJpaController() {
        Context jndiCntx;
        try {
            jndiCntx = new InitialContext();
            this.utx = (UserTransaction) jndiCntx.lookup("java:comp/UserTransaction");
        } catch (NamingException ex) {
            Logger.getLogger(PqrsJpaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private UserTransaction utx = null;
    @PersistenceContext(unitName = "PQRSPU")
    private EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    public void create(Pqrs pqrs) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (pqrs.getEstadoList() == null) {
            pqrs.setEstadoList(new ArrayList<Estado>());
        }
        if (pqrs.getAnexoList() == null) {
            pqrs.setAnexoList(new ArrayList<Anexo>());
        }

        try {
            utx.begin();
            em = getEntityManager();
            Cliente cliente = pqrs.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getClientePK());
                pqrs.setCliente(cliente);
            }
            List<Estado> attachedEstadoList = new ArrayList<Estado>();
            for (Estado estadoListEstadoToAttach : pqrs.getEstadoList()) {
                estadoListEstadoToAttach = em.getReference(estadoListEstadoToAttach.getClass(), estadoListEstadoToAttach.getEstadoPK());
                attachedEstadoList.add(estadoListEstadoToAttach);
            }
            pqrs.setEstadoList(attachedEstadoList);
            List<Anexo> attachedAnexoList = new ArrayList<Anexo>();
            for (Anexo anexoListAnexoToAttach : pqrs.getAnexoList()) {
                anexoListAnexoToAttach = em.getReference(anexoListAnexoToAttach.getClass(), anexoListAnexoToAttach.getAnexoPK());
                attachedAnexoList.add(anexoListAnexoToAttach);
            }
            pqrs.setAnexoList(attachedAnexoList);
            em.persist(pqrs);
            if (cliente != null) {
                cliente.getPqrsList().add(pqrs);
                cliente = em.merge(cliente);
            }
            for (Estado estadoListEstado : pqrs.getEstadoList()) {
                Pqrs oldPqrsOfEstadoListEstado = estadoListEstado.getPqrs();
                estadoListEstado.setPqrs(pqrs);
                estadoListEstado = em.merge(estadoListEstado);
                if (oldPqrsOfEstadoListEstado != null) {
                    oldPqrsOfEstadoListEstado.getEstadoList().remove(estadoListEstado);
                    oldPqrsOfEstadoListEstado = em.merge(oldPqrsOfEstadoListEstado);
                }
            }
            for (Anexo anexoListAnexo : pqrs.getAnexoList()) {
                Pqrs oldPqrsOfAnexoListAnexo = anexoListAnexo.getPqrs();
                anexoListAnexo.setPqrs(pqrs);
                anexoListAnexo = em.merge(anexoListAnexo);
                if (oldPqrsOfAnexoListAnexo != null) {
                    oldPqrsOfAnexoListAnexo.getAnexoList().remove(anexoListAnexo);
                    oldPqrsOfAnexoListAnexo = em.merge(oldPqrsOfAnexoListAnexo);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPqrs(pqrs.getCodigo()) != null) {
                throw new PreexistingEntityException("Pqrs " + pqrs + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pqrs pqrs) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {

        try {
            utx.begin();
            em = getEntityManager();
            Pqrs persistentPqrs = em.find(Pqrs.class, pqrs.getCodigo());
            Cliente clienteOld = persistentPqrs.getCliente();
            Cliente clienteNew = pqrs.getCliente();
            List<Estado> estadoListOld = persistentPqrs.getEstadoList();
            List<Estado> estadoListNew = pqrs.getEstadoList();
            List<Anexo> anexoListOld = persistentPqrs.getAnexoList();
            List<Anexo> anexoListNew = pqrs.getAnexoList();
            List<String> illegalOrphanMessages = null;
            for (Estado estadoListOldEstado : estadoListOld) {
                if (!estadoListNew.contains(estadoListOldEstado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estado " + estadoListOldEstado + " since its pqrs field is not nullable.");
                }
            }
            for (Anexo anexoListOldAnexo : anexoListOld) {
                if (!anexoListNew.contains(anexoListOldAnexo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Anexo " + anexoListOldAnexo + " since its pqrs field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getClientePK());
                pqrs.setCliente(clienteNew);
            }
            List<Estado> attachedEstadoListNew = new ArrayList<Estado>();
            for (Estado estadoListNewEstadoToAttach : estadoListNew) {
                estadoListNewEstadoToAttach = em.getReference(estadoListNewEstadoToAttach.getClass(), estadoListNewEstadoToAttach.getEstadoPK());
                attachedEstadoListNew.add(estadoListNewEstadoToAttach);
            }
            estadoListNew = attachedEstadoListNew;
            pqrs.setEstadoList(estadoListNew);
            List<Anexo> attachedAnexoListNew = new ArrayList<Anexo>();
            for (Anexo anexoListNewAnexoToAttach : anexoListNew) {
                anexoListNewAnexoToAttach = em.getReference(anexoListNewAnexoToAttach.getClass(), anexoListNewAnexoToAttach.getAnexoPK());
                attachedAnexoListNew.add(anexoListNewAnexoToAttach);
            }
            anexoListNew = attachedAnexoListNew;
            pqrs.setAnexoList(anexoListNew);
            pqrs = em.merge(pqrs);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getPqrsList().remove(pqrs);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getPqrsList().add(pqrs);
                clienteNew = em.merge(clienteNew);
            }
            for (Estado estadoListNewEstado : estadoListNew) {
                if (!estadoListOld.contains(estadoListNewEstado)) {
                    Pqrs oldPqrsOfEstadoListNewEstado = estadoListNewEstado.getPqrs();
                    estadoListNewEstado.setPqrs(pqrs);
                    estadoListNewEstado = em.merge(estadoListNewEstado);
                    if (oldPqrsOfEstadoListNewEstado != null && !oldPqrsOfEstadoListNewEstado.equals(pqrs)) {
                        oldPqrsOfEstadoListNewEstado.getEstadoList().remove(estadoListNewEstado);
                        oldPqrsOfEstadoListNewEstado = em.merge(oldPqrsOfEstadoListNewEstado);
                    }
                }
            }
            for (Anexo anexoListNewAnexo : anexoListNew) {
                if (!anexoListOld.contains(anexoListNewAnexo)) {
                    Pqrs oldPqrsOfAnexoListNewAnexo = anexoListNewAnexo.getPqrs();
                    anexoListNewAnexo.setPqrs(pqrs);
                    anexoListNewAnexo = em.merge(anexoListNewAnexo);
                    if (oldPqrsOfAnexoListNewAnexo != null && !oldPqrsOfAnexoListNewAnexo.equals(pqrs)) {
                        oldPqrsOfAnexoListNewAnexo.getAnexoList().remove(anexoListNewAnexo);
                        oldPqrsOfAnexoListNewAnexo = em.merge(oldPqrsOfAnexoListNewAnexo);
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
                String id = pqrs.getCodigo();
                if (findPqrs(id) == null) {
                    throw new NonexistentEntityException("The pqrs with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            utx.begin();
            em = getEntityManager();
            Pqrs pqrs;
            try {
                pqrs = em.getReference(Pqrs.class, id);
                pqrs.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pqrs with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Estado> estadoListOrphanCheck = pqrs.getEstadoList();
            for (Estado estadoListOrphanCheckEstado : estadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pqrs (" + pqrs + ") cannot be destroyed since the Estado " + estadoListOrphanCheckEstado + " in its estadoList field has a non-nullable pqrs field.");
            }
            List<Anexo> anexoListOrphanCheck = pqrs.getAnexoList();
            for (Anexo anexoListOrphanCheckAnexo : anexoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pqrs (" + pqrs + ") cannot be destroyed since the Anexo " + anexoListOrphanCheckAnexo + " in its anexoList field has a non-nullable pqrs field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente cliente = pqrs.getCliente();
            if (cliente != null) {
                cliente.getPqrsList().remove(pqrs);
                cliente = em.merge(cliente);
            }
            em.remove(pqrs);
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

    public List<Pqrs> findPqrsEntities() {
        return findPqrsEntities(true, -1, -1);
    }

    public List<Pqrs> findPqrsEntities(int maxResults, int firstResult) {
        return findPqrsEntities(false, maxResults, firstResult);
    }

    private List<Pqrs> findPqrsEntities(boolean all, int maxResults, int firstResult) {
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pqrs.class));
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

    public Pqrs findPqrs(String id) {
        
        try {
            return em.find(Pqrs.class, id);
        } finally {
            em.close();
        }
    }

    public int getPqrsCount() {
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pqrs> rt = cq.from(Pqrs.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
