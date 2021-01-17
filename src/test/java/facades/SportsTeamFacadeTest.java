package facades;

import utils.EMF_Creator;
import entities.SportsTeam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
@Disabled
public class SportsTeamFacadeTest {

    private static EntityManagerFactory emf;
    private static SportsTeamFacade facade;

    public SportsTeamFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = SportsTeamFacade.getSportsTeamFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//       
    }


    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("SportsTeam.deleteAllRows").executeUpdate();
            em.persist(new SportsTeam("Ravens",5,10,500));
               em.persist(new SportsTeam("Bills",5,10,500));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        
    }


    @Test
    public void testAFacadeMethod() {
        assertEquals(2, facade.getAmountOfTeams(), "Expects two rows in the database");
    }

}