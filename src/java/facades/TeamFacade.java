/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import beans.Team;
import beans.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author matthieu.rossier
 */
@Stateless
public class TeamFacade extends AbstractFacade<Team> {
    @PersistenceContext(unitName = "champgenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TeamFacade() {
        super(Team.class);
    }
    
    public Team findTeamByName(String teamName) {
        return (Team)em.createNamedQuery("Team.findByName").setParameter("name", teamName).getSingleResult();
    }
    
    public Team findByLeader(User leader) {
        return (Team)em.createNamedQuery("Team.findByLeader").setParameter("leader", leader).getSingleResult();
    }
    
}
