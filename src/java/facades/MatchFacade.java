/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import beans.Championship;
import beans.Match;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author matthieu.rossier
 */
@Stateless
public class MatchFacade extends AbstractFacade<Match> {
    @PersistenceContext(unitName = "champgenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MatchFacade() {
        super(Match.class);
    }
    
}
