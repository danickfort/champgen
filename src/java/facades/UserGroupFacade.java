/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import beans.User;
import beans.UserGroup;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author matthieu.rossier
 */
@Stateless
public class UserGroupFacade extends AbstractFacade<UserGroup> {
    @PersistenceContext(unitName = "champgenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserGroupFacade() {
        super(UserGroup.class);
    }
    
    public List<UserGroup> getRoleByUser(User user) {
        return em.createNamedQuery("UserGroup.findRoleByUserId").setParameter("user", user).getResultList();
    }
}
