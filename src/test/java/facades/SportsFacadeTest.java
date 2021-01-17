package facades;

import utils.EMF_Creator;
import entities.Sport;
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
public class SportsFacadeTest {

    private static EntityManagerFactory emf;
    private static SportsFacade facade;

    public SportsFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = SportsFacade.getSportsFacade(emf);
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
            em.createNamedQuery("Sport.deleteAllRows").executeUpdate();
            em.persist(new Sport("Basketball", "Hoops"));
            em.persist(new Sport("Baseball", "hardbal"));

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
        assertEquals(2, facade.getAmountOfSports(), "Expects two rows in the database");
    }

}