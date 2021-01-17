package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.mindrot.jbcrypt.BCrypt;


@Entity
@Table(name = "teams")
public class SportsTeam implements Serializable {
    
    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teamId")
    private long id;
   
    @Column(name = "teamName")
    private String teamName;
   
    @Column(name = "minimumAge")
    private int minAge;
    
    
    @Column(name = "maximumAge")
    private int maxAge;
    
    
      @Column(name = "pricePerYear")
    private double price;

    
    
    @JoinTable(name = "registeredTeams", joinColumns = {
    @JoinColumn(name = "id", referencedColumnName = "teamId")}, inverseJoinColumns = {
    @JoinColumn(name = "teamSport", referencedColumnName = "sportName")})
    @ManyToMany
    private List<Sport> sportList = new ArrayList<>();
     
     

    public List<String> getSportsAsStrings() {
        if (sportList.isEmpty()) {
            return null;
        }
        
        List<String> sportsAsStrings = new ArrayList<>();
        sportList.forEach((role) -> {
            sportsAsStrings.add(role.getSportName());
        });
        return sportsAsStrings;
    }
    
    
    
    public SportsTeam() {
    }

    public SportsTeam(String teamName, int minAge, int maxAge, double price) {
        
        this.teamName = teamName;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.price = price;
    }

 
  

    

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public List<Sport> getSportList() {
        return sportList;
    }

    public void setSportList(List<Sport> sportList) {
        this.sportList = sportList;
    }
    
    
    
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

   

    

}
