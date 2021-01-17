package rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.Sport;
import entities.SportsTeam;
import errorhandling.API_Exception;
import facades.AdminFacade;
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

@Path("admin")
public class AdminResource {

    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    public static final SportsTeamFacade USER_FACADE = SportsTeamFacade.getSportsTeamFacade(EMF);
    public static final AdminFacade ADMIN_FACADE = AdminFacade.getAdminFacade(EMF);

    @Context
    private UriInfo context;

    
    public AdminResource() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSport(String jsonString) throws AuthenticationException, API_Exception {
       
        try {
           
           JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
           String sportName = json.get("sportName").getAsString();
           String description = json.get("description").getAsString();

        
           

        Sport sport = ADMIN_FACADE.addSport(sportName, description);
          
          
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("sportName", sportName);
        responseJson.addProperty("msg", "Added sport: ");
        return Response.ok(new Gson().toJson(responseJson)).build();
        
        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Suplied", 400, e);
        }

        
       

    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(String jsonString) throws AuthenticationException, API_Exception {
        
        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            Long sportsTeamId = json.get("id").getAsLong();
            SportsTeam team = USER_FACADE.findSportsTeam(sportsTeamId);
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
            Long userId = json.get("id").getAsLong();
            SportsTeam user = USER_FACADE.deleteTeam(userId);
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("message", String.format("Successfully deleted user %d", userId));
            return Response.ok(new Gson().toJson(responseJson)).build();
        }
        catch (Exception e) {
            return Response.status(400, "Malformed JSON supplied").build();
        }
    }
    
 
    
    
    
    }
    
    
