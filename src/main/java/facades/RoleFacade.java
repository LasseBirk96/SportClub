package facades;

import entities.Sport;
import entities.SportsTeam;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;


public class RoleFacade {

    private static EntityManagerFactory emf;
    private static RoleFacade instance;

    private RoleFacade() {
    }

    public static RoleFacade getRoleFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RoleFacade();
        }
        return instance;
    }
 
    public List<Sport> getAllRoles() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Sport> allRoles = em.createQuery("SELECT u.roleName from Role u", Sport.class)
            .getResultList();
            return allRoles;
        } finally {
            em.close();
        }

    }
}