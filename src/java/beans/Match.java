/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author matthieu.rossier
 */
@Entity
@Table(name = "game")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Match.findAll", query = "SELECT c FROM Match c"),
    @NamedQuery(name = "Match.findById", query = "SELECT c FROM Match c WHERE c.id = :id")})
public class Match implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "team1")
    @OneToOne
    private Team team1;
    @JoinColumn(name = "team2")
    @OneToOne
    private Team team2;
    @Column(name = "team1_score")
    private int team1_score;
    @Column(name = "team2_score")
    private int team2_score;
    @Column(name = "date_played")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "matchday_id")
    private MatchDay matchday;
    
    public Match() {
        
    }

    public Match(Integer id, Team team1, Team team2, int team1_score, int team2_score, Date date) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.team1_score = team1_score;
        this.team2_score = team2_score;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public int getTeam1_score() {
        return team1_score;
    }

    public int getTeam2_score() {
        return team2_score;
    }

    public Date getDate() {
        return date;
    }
    
    public MatchDay getMatchDay() {
        return matchday;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public void setTeam1_score(int team1_score) {
        this.team1_score = team1_score;
    }

    public void setTeam2_score(int team2_score) {
        this.team2_score = team2_score;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public void setMatchDay(MatchDay matchday) {
        this.matchday = matchday;
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
        if (!(object instanceof Match)) {
            return false;
        }
        Match other = (Match) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Match[ id=" + id + " ]";
    }
    
}
