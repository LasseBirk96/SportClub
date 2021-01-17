package facades;

import entities.Sport;
import entities.SportsTeam;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;


public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    private UserFacade() {
    }

    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

 
    
    
    public SportsTeam findSportsTeam(Long id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            TypedQuery<SportsTeam> query = em.createQuery("SELECT u FROM SportsTeam u WHERE u.id = :id ", SportsTeam.class);
            query.setParameter("id", id);
            SportsTeam team = query.getSingleResult();
            
            return team;
        } 
        finally {
            em.close();
        }
    }
    
    

    public SportsTeam addSportsTeam(String teamName, int minAge, int maxAge, double price) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        SportsTeam team;
        try {
                team = new SportsTeam(teamName, minAge, maxAge, price);
                team.addSport(em.find(Sport.class, 1));
                em.getTransaction().begin();
                em.persist(team);
                em.getTransaction().commit();
            
        } finally {
            em.close();
        }
        return team;
    }
    
    public SportsTeam updateUser(SportsTeam user) {
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
            
            return user;
        }
        finally {
            em.close();
        }
    }
    
    
    public SportsTeam deleteUser(Long userId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<SportsTeam> query = em.createQuery("SELECT u FROM User u WHERE u.id = :id ", SportsTeam.class);
            query.setParameter("id", userId);
            SportsTeam p = query.getSingleResult();
            em.remove(p);
            em.getTransaction().commit();
            return p; 
        }
        finally{
            em.close();
        }
        
    }
     
    public List<SportsTeam> getAllUsers() {
        EntityManager em = emf.createEntityManager();
        try {
            List<SportsTeam> allUsers = em.createQuery("SELECT u.userName from User u", SportsTeam.class)
            .getResultList();
            return allUsers;
        } finally {
            em.close();
        }

    }
    
    
    public String getUserAmount() {
       EntityManager em = emf.createEntityManager();;
        try {
            TypedQuery<SportsTeam> query = em.createQuery ("select u from User u",entities.SportsTeam.class);
            List<SportsTeam> users = query.getResultList();
            return "[" + users.size() + "]";
        } finally {
            em.close();
    
        
    }
    
    
    
    
   
}
    
}