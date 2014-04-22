/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import beans.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author matthieu.rossier
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "champgenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    public List<User> getUserByUsernameAndPassword(String username, String password) {
        return em.createNamedQuery("User.findByUsernameAndPassord").setParameter("username", username).setParameter("password", password).getResultList();
    }

    public int updatePassword(int userId, String password) {
        Query query = em.createQuery("UPDATE User u SET u.password = :password WHERE u.id = :userId");
        int updateCount = query.setParameter("userId", userId).setParameter("password", password).executeUpdate();
        return updateCount;
    }
}
