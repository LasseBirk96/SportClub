package rest;


import entities.Sport;

import facades.SportsFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import static rest.SportsTeamsResource.SPORTSTEAM_FACADE;

import utils.EMF_Creator;

@Path("Sports")
public class SportsResource {

   
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
     public static final SportsFacade SPORTS_FACADE = SportsFacade.getSportsFacade(EMF);

    @Context
    private UriInfo context;

    
    public SportsResource() {
    }

   
  
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Sport> getAllSports() {
        List<Sport> allSports = SPORTS_FACADE.getAllSports();
        return allSports;
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("amount")
    public String amountOfSports() {
        String sportsAmount = SPORTS_FACADE.getAmountOfSports();
        return sportsAmount;
    }
    
    
    
    }
    