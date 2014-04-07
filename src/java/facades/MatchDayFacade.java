/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import beans.Championship;
import beans.Match;
import beans.MatchDay;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author matthieu.rossier
 */
@Stateless
public class MatchDayFacade extends AbstractFacade<MatchDay> {
    @PersistenceContext(unitName = "champgenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MatchDayFacade() {
        super(MatchDay.class);
    }

    public List<MatchDay> findByChampionship(Championship championship) {
        return em.createNamedQuery("MatchDay.findByChampionship").setParameter("championship", championship).getResultList();
    }
    
}
