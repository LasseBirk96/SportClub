package facades;

import entities.Sport;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author lassebirk
 */
public class SportsFacade {
    
        private static EntityManagerFactory emf;
        private static SportsFacade instance;

        
        
        
    public static SportsFacade getSportsFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new SportsFacade();
        }
        return instance;
    }
    
    
     public List<Sport> getAllSports() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Sport> allSports = em.createQuery("SELECT u.sportName, u.description from Sport u", Sport.class)
            .getResultList();
            return allSports;
        } finally {
            em.close();
        }

    }
    
    
    public String getAmountOfSports() {
       EntityManager em = emf.createEntityManager();;
        try {
            TypedQuery<Sport> query = em.createQuery ("select u from Sport u", Sport.class);
            List<Sport> sports = query.getResultList();
            return "[" + sports.size() + "]";
        } finally {
            em.close();
    
        
    }
    

   
}
}