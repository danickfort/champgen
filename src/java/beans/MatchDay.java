/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author matthieu.rossier
 */
@Entity
@Table(name = "matchday")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MatchDay.findAll", query = "SELECT c FROM MatchDay c"),
    @NamedQuery(name = "MatchDay.findById", query = "SELECT c FROM MatchDay c WHERE c.id = :id"),
    @NamedQuery(name = "MatchDay.findByChampionship", query = "SELECT m FROM MatchDay m WHERE m.championship = :championship")})
public class MatchDay implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @OneToMany(mappedBy = "matchday", cascade = CascadeType.PERSIST)
    private List<Match> matches;
    @ManyToOne
    @JoinColumn(name = "championship_id")
    private Championship championship;
    
    public MatchDay() {
        
    }

    public MatchDay(Integer id, List<Match> matches, Championship championship) {
        this.id = id;
        this.matches = matches;
        this.championship = championship;
    }

    public Integer getId() {
        return id;
    }

    public Collection getMatches() {
        return matches;
    }

    public Championship getChampionship() {
        return championship;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
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
        if (!(object instanceof MatchDay)) {
            return false;
        }
        MatchDay other = (MatchDay) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.MatchDay[ id=" + id + " ]";
    }
    
}
