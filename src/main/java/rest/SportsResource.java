package rest;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.Sport;
import errorhandling.API_Exception;

import facades.SportsFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static rest.SportsTeamResource.SPORTSTEAM_FACADE;
import security.errorhandling.AuthenticationException;

import utils.EMF_Creator;

@Path("Sports")
public class SportsResource {

   
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
     public static final SportsFacade SPORTS_FACADE = SportsFacade.getSportsFacade(EMF);

    @Context
    private UriInfo context;

    
    public SportsResource() {
    }
    
    
       @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSport(String jsonString) throws AuthenticationException, API_Exception {
       
        try {
           
           JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
           String sportName = json.get("sportName").getAsString();
           String description = json.get("description").getAsString();

        
           

        Sport sport = SPORTS_FACADE.addSport(sportName, description);
          
          
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("sportName", sportName);
        responseJson.addProperty("msg", "Added sport: ");
        return Response.ok(new Gson().toJson(responseJson)).build();
        
        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Suplied", 400, e);
        }

        
       

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
    