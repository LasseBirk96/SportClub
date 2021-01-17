package utils;

/*
import entities.Sport;
import entities.SportsTeam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    
  

    SportsTeam user = new SportsTeam("user", "test1","email");
    SportsTeam admin = new SportsTeam("admin", "test1","email");
    SportsTeam both = new SportsTeam("user_admin", "test1","email");

    if(admin.getUserPass().equals("test")||user.getUserPass().equals("test")||both.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");

    em.getTransaction().begin();
    //em.createQuery("DELETE FROM Role").executeUpdate();
    //em.createQuery("DELETE FROM User").executeUpdate();
    Sport userRole = new Sport("user");
    Sport adminRole = new Sport("admin");
    user.addRole(userRole);
    admin.addRole(adminRole);
    both.addRole(userRole);
    both.addRole(adminRole);
    em.persist(userRole);
    em.persist(adminRole);
    em.persist(user);
    em.persist(admin);
    em.persist(both);
    em.getTransaction().commit();
    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
    System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
    System.out.println("Created TEST Users");
   
  }



}
*/