/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Sport;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import security.errorhandling.AuthenticationException;

/**
 *
 * @author lassebirk
 */
public class AdminFacade {
    
        private static EntityManagerFactory emf;
        private static AdminFacade instance;

        
        
        
    public static AdminFacade getAdminFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AdminFacade();
        }
        return instance;
    }

    
      public Sport addSport(String sportName, String description) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        Sport sport;
        try {
                sport = new Sport(sportName, description);
                em.getTransaction().begin();
                em.persist(sport);
                em.getTransaction().commit();
            
        } finally {
            em.close();
        }
        return sport;
    }
    
}
