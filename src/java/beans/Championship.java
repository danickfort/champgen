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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author matthieu.rossier
 */
@Entity
@Table(name = "championship")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Championship.findAll", query = "SELECT c FROM Championship c"),
    @NamedQuery(name = "Championship.findById", query = "SELECT c FROM Championship c WHERE c.id = :id")
})
public class Championship implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    
    @OneToMany(mappedBy = "championship")
    private List<MatchDay> matchdays;
    
    @OneToMany(mappedBy = "championship")
    private List<Team> teams;
    
    public Championship() {
        
    }

    public Championship(Integer id, String name, List<MatchDay> matchdays) {
        this.id = id;
        this.name = name;
        this.matchdays = matchdays;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public List<Team> getTeams() {
        return teams;
    }

    public Collection getMatchdays() {
        return matchdays;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMatchdays(List<MatchDay> matchdays) {
        this.matchdays = matchdays;
    }
    
    public void setTeams(List<Team> teams) {
        this.teams = teams;
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
        if (!(object instanceof Championship)) {
            return false;
        }
        Championship other = (Championship) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Championship[ id=" + id + " ]";
    }
    
}
