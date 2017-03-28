/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.db.dao;

import com.mycompany.db.dao.exceptions.NonexistentEntityException;
import com.mycompany.db.entity.Profile;
import com.mycompany.db.entity.Profile_;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author roman
 */
public class ProfileJpaController implements Serializable {

    public ProfileJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public boolean create(Profile profile) {
        boolean isCreateProfile = false;
        EntityManager em = null;
        try {
            em = getEntityManager();
            if (!findByLoginPasswordAndNotEmpty(em, profile.getLogin(), profile.getPassword())) {
                em.getTransaction().begin();
                em.persist(profile);
                em.getTransaction().commit();
                isCreateProfile = true;
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return isCreateProfile;
    }

    public void edit(Profile profile) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            profile = em.merge(profile);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = profile.getId();
                if (findProfile(id) == null) {
                    throw new NonexistentEntityException("The profile with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Profile profile;
            try {
                profile = em.getReference(Profile.class, id);
                profile.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The profile with id " + id + " no longer exists.", enfe);
            }
            em.remove(profile);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Profile> findProfileEntities() {
        return findProfileEntities(true, -1, -1);
    }

    public List<Profile> findProfileEntities(int maxResults, int firstResult) {
        return findProfileEntities(false, maxResults, firstResult);
    }

    private List<Profile> findProfileEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Profile.class));
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

    public Profile findProfile(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Profile.class, id);
        } finally {
            em.close();
        }
    }

    public int getProfileCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Profile> rt = cq.from(Profile.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public boolean findByLoginPasswordAndNotEmpty(EntityManager manager, String login, String password) {
        boolean isFind = false;
        EntityManager em = manager;
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();

            CriteriaQuery cq = builder.createQuery();
            Root<Profile> rt = cq.from(Profile.class);
            cq.where(builder.and(
                    builder.equal(rt.get(Profile_.login), login),
                    builder.equal(rt.get(Profile_.password), password)
            ));
            List q = (List) em.createQuery(cq).getResultList();
            if (!q.isEmpty()) {
                isFind = true;
            }
        } catch (Exception e) {
        }

        return isFind;
    }

    public Profile findNameByLoginAndPassword(EntityManager manager, String login, String password) {
        String name = "";
        EntityManager em = manager;
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();

            CriteriaQuery cq = builder.createQuery();
            Root<Profile> rt = cq.from(Profile.class);
            cq.where(builder.and(
                    builder.equal(rt.get(Profile_.login), login),
                    builder.equal(rt.get(Profile_.password), password)
            ));
            List q = (List) em.createQuery(cq).getResultList();
            if (!q.isEmpty()) {
                return (Profile) q.get(0);
            }
        } catch (Exception e) {
        }

        return null;
    }

}
