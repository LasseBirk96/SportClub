package facades;

import entities.Sport;
import entities.SportsTeam;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;


public class SportsTeamFacade {

    private static EntityManagerFactory emf;
    private static SportsTeamFacade instance;

    private SportsTeamFacade() {
    }

    public static SportsTeamFacade getSportsTeamFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new SportsTeamFacade();
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
    
    public SportsTeam addSportToTeam(String sname, String name) {
        EntityManager em = emf.createEntityManager();
        
        try{
           em.getTransaction().begin();

        TypedQuery<SportsTeam> query = em.createQuery("SELECT u FROM SportsTeam u WHERE u.teamName = :name", SportsTeam.class);
        query.setParameter("name", name);
        SportsTeam team = query.getSingleResult();
        
        TypedQuery<Sport> otherQuery = em.createQuery("SELECT s FROM Sport s WHERE s.sportName = :sname", Sport.class);
        query.setParameter("sname", sname);
        Sport sport = otherQuery.getSingleResult();
        
        team.addSport(sport);
                em.persist(team);
        em.getTransaction().commit();
        
        return team;
          
        }
        finally {
            em.close();
        }
  
    }
    
    

    public SportsTeam addSportsTeam(String teamName, int minAge, int maxAge, double price) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        
        try {
          SportsTeam team = new SportsTeam(teamName, minAge, maxAge, price);
         
                em.getTransaction().begin();
                em.persist(team);
                em.getTransaction().commit();
                  return team;
            
        } finally {
            em.close();
        }
      
    }
    
    public SportsTeam updateTeam(SportsTeam team) {
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.merge(team);
            em.getTransaction().commit();
            
            return team;
        }
        finally {
            em.close();
        }
    }
    
    
    public SportsTeam deleteTeam(Long teamId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<SportsTeam> query = em.createQuery("SELECT u FROM SportsTeam u WHERE u.id = :id ", SportsTeam.class);
            query.setParameter("id", teamId);
            SportsTeam p = query.getSingleResult();
            em.remove(p);
            em.getTransaction().commit();
            return p; 
        }
        finally{
            em.close();
        }
        
    }
     
    public List<SportsTeam> getAllTeams() {
        EntityManager em = emf.createEntityManager();
        try {
            List<SportsTeam> allTeams = em.createQuery("SELECT u.teamName, u.minAge, u.maxAge, u.price from SportsTeam u", SportsTeam.class)
            .getResultList();
            return allTeams;
        } finally {
            em.close();
        }

    }
    
    
    public String getAmountOfTeams() {
       EntityManager em = emf.createEntityManager();;
        try {
            TypedQuery<SportsTeam> query = em.createQuery ("select u from SportsTeam u",entities.SportsTeam.class);
            List<SportsTeam> teams = query.getResultList();
            return "[" + teams.size() + "]";
        } finally {
            em.close();
    
        
    }
    
    
    
    
   
}
    
}