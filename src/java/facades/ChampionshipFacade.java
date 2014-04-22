/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import beans.Championship;
import beans.Team;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author matthieu.rossier
 */
@Stateless
public class ChampionshipFacade extends AbstractFacade<Championship> {

    @PersistenceContext(unitName = "champgenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChampionshipFacade() {
        super(Championship.class);
    }

    public List<Team> getTeams(Championship championship) {
        return em.createNamedQuery("Team.findTeams").setParameter("championship", championship).getResultList();
    }

    public List<Championship> paginate(int pageIndex) {
        if (pageIndex > 0) {
            pageIndex = pageIndex - 1;
        }
        return em.createNamedQuery("Championship.findAll")
                .setMaxResults(3)
                .setFirstResult(pageIndex * 3).getResultList();
    }
}
