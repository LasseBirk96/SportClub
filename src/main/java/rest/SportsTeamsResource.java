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

@Path("SportsTeams")
public class SportsTeamsResource {

    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    public static final SportsTeamFacade SPORTSTEAM_FACADE = SportsTeamFacade.getSportsTeamFacade(EMF);

    @Context
    private UriInfo context;

    
    public SportsTeamsResource() {
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

           
           SportsTeam team = SPORTSTEAM_FACADE.addSportsTeam(teamName, minAge,maxAge, price);
          

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
            
            if (json.has("price")) {
                team.setPrice(json.get("price").getAsDouble());
            }
            
            if (json.has("teamName")) {
                team.setTeamName(json.get("teamName").getAsString());
            }
            
             if (json.has("minAge")) {
                team.setMinAge(json.get("minAge").getAsInt());
            }
            
            if (json.has("maxAge")) {
                team.setMaxAge(json.get("maxAge").getAsInt());
            }
            
            
            
            SPORTSTEAM_FACADE.updateTeam(team);

            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("message", String.format("Successfully updated team %d", sportsTeamId));
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
    public Response removeSportsTeam(String jsonString) throws AuthenticationException, API_Exception {
        
        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            Long teamId = json.get("id").getAsLong();
            SportsTeam user = SPORTSTEAM_FACADE.deleteTeam(teamId);
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("message", String.format("Successfully deleted team %d", teamId));
            return Response.ok(new Gson().toJson(responseJson)).build();
        }
        catch (Exception e) {
            return Response.status(400, "Malformed JSON supplied").build();
        }
    }
    
    
    @Path("addSportToTeam")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSport(String jsonString) throws AuthenticationException, API_Exception {
        
        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            String sname = json.get("sname").getAsString();
            String name = json.get("name").getAsString();
            SportsTeam user = SPORTSTEAM_FACADE.addSportToTeam(sname, name);
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("message", String.format("Successfully added sport to %d"));
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
    
    
