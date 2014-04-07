/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author matthieu.rossier
 */
@Entity
@Table(name = "team")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Team.findAll", query = "SELECT c FROM Team c"),
    @NamedQuery(name = "Team.findById", query = "SELECT c FROM Team c WHERE c.id = :id"),
    @NamedQuery(name = "Team.findByName", query = "SELECT c FROM Team c WHERE c.name = :name"),
    @NamedQuery(name = "Team.findTeams", query = "SELECT c FROM Team c WHERE c.championship = :championship"),
    @NamedQuery(name = "Team.findByLeader", query = "SELECT t FROM Team t WHERE t.leader = :leader")
    })
public class Team implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    
    // relation player-team
    @OneToMany(mappedBy = "team")
    private List<User> players;
    
    // relation leader-player
    @OneToOne
    @JoinColumn(name = "leader_id")
    private User leader;
    
    @ManyToOne
    @JoinColumn(name = "championship_id")
    private Championship championship;
    
    public Team() {
        
    }

    public Team(Integer id, String name, List<User> players, User leader) {
        this.id = id;
        this.name = name;
        this.players = players;
        this.leader = leader;
    }

    public String getName() {
        return name;
    }

    public Collection getPlayers() {
        return players;
    }

    public User getLeader() {
        return leader;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayers(List<User> players) {
        this.players = players;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setChampionship(Championship championship) {
        this.championship = championship;
    } 
	
    public Championship getChampionship() {
        return championship;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Team)) {
            return false;
        }
        Team other = (Team) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Team[ id=" + id + " ]";
    }
    
}
