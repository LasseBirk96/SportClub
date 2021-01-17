package rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.Sport;
import entities.SportsTeam;
import errorhandling.API_Exception;
import facades.SportsTeamFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

@Path("team")
public class UserResource {

    public static final int TOKEN_EXPIRE_TIME = 1000 * 60 * 30; //30 min
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    public static final SportsTeamFacade SPORTSTEAM_FACADE = SportsTeamFacade.getSportsTeamFacade(EMF);

    @Context
    private UriInfo context;

    
    public UserResource() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSportsTeam(String jsonString) throws AuthenticationException, API_Exception {
       
        try {
           
           JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
           String teamName = json.get("teamName").getAsString();
           int minAge = json.get("minAge").getAsInt();
           int maxAge = json.get("maxAge").getAsInt();
           double price =json.get("price").getAsDouble();
           Long id =json.get("id").getAsLong();
           
           
         
           
           
          SportsTeam team = SPORTSTEAM_FACADE.addSportsTeam(teamName, minAge,maxAge, price, id);
          
          
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("teamName", teamName);
        responseJson.addProperty("msg", "Welcome on board!");
        return Response.ok(new Gson().toJson(responseJson)).build();
        
        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Suplied", 400, e);
        }

        
       

    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTeam(String jsonString) throws AuthenticationException, API_Exception {
        
        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            Long sportsTeamId = json.get("id").getAsLong();
            SportsTeam team = SPORTSTEAM_FACADE.findSportsTeam(sportsTeamId);
            /*
            if (json.has("email")) {
                team.setEmail(json.get("email").getAsString());
            }
            
            if (json.has("username")) {
                team.setUserName(json.get("username").getAsString());
            }
            
            USER_FACADE.updateUser(user);
                   */ 
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("message", String.format("Successfully updated user %d", sportsTeamId));
            return Response.ok(new Gson().toJson(responseJson)).build();
        }
        catch (Exception e) {
            return Response.status(400, "Malformed JSON supplied").build();
        }
        
    }
    
    // GET /users => find all users
    // GET /users/:id => find user by supplied id
    // POST /users => create new user
    // DELETE /users/:id => delete user by supplied id
    // PUT /users/:id => update user by supplied id, send json object with updates
    
    @Path("delete")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeUser(String jsonString) throws AuthenticationException, API_Exception {
        
        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            Long teamId = json.get("id").getAsLong();
            SportsTeam user = SPORTSTEAM_FACADE.deleteTeam(teamId);
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("message", String.format("Successfully deleted user %d", teamId));
            return Response.ok(new Gson().toJson(responseJson)).build();
        }
        catch (Exception e) {
            return Response.status(400, "Malformed JSON supplied").build();
        }
    }
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<SportsTeam> getAllTeams() {
        List<SportsTeam> allTeams = SPORTSTEAM_FACADE.getAllTeams();
        return allTeams;
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("amount")
    public String amountTeams() {
        String userAmount = SPORTSTEAM_FACADE.getAmountOfTeams();
        return userAmount;
    }
    
    
    
    }
    
    
