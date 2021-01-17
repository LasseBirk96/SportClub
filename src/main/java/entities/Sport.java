package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "sports")
public class Sport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sportId")
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "sportName")
    private String sportName;
    
    
    @Column(name = "description")
    private String description;

    
    @ManyToMany(mappedBy = "sportList")
    private List<SportsTeam> sportsTeamList;

    public Sport() {
    }
    
    
    public Sport(String sportName, String description) {
        this.sportName = sportName;
        this.description = description;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }
    
        public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
     public List<SportsTeam> getSportTeamsList() {
        return sportsTeamList;
    }

    public void setSportsTeamList(List<SportsTeam> sportsTeamList) {
        this.sportsTeamList = sportsTeamList;
    }   

  
}
