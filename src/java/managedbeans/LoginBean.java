/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import beans.User;
import beans.UserGroup;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import utils.Route;

/**
 *
 * @author matthieu.rossier
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private int id;
    private String username;
    private String password;
    private String usernamePrev;
    private String passwordPrev;
    private boolean loggedIn;
    private String role;
    
    @ManagedProperty(value = "#{mainController}")
    private MainController mainController;
    
    public LoginBean() {
        this.id = -1;
        this.username = "";
        this.password = "";
        this.loggedIn = false;
        this.role = "";
    }

    /**
     * Getters and setters
     */
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return this.loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    
    public boolean isAdminLoggedIn() {
        if (this.isLoggedIn() && this.role.equals("ADMIN")) {
            return true;
        }
        return false;
    }

    /**
     * Methods
     */
    public String doLogin() {
        List<User> user = this.mainController.getUserByUsernameAndPassword(this.username, this.password);
        if (!user.isEmpty()) {
            this.loggedIn = true;
            
            User userLogged = user.get(0);
            
            List<UserGroup> userGroup = this.mainController.getRoleByUser(userLogged);
            
            UserGroup userGroupLogged = userGroup.get(0);
            
            this.id = userLogged.getId();
            this.role = userGroupLogged.getGroupName();
            
            this.usernamePrev = this.username;
            this.passwordPrev = this.password;

            FacesMessage msg = new FacesMessage("Login sucess ! Role: " + this.role, "INFO MSG");
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
            return "login.xhtml";
        } else {
            this.username = this.usernamePrev;
            this.password = this.passwordPrev;
            
            FacesMessage msg = new FacesMessage("Login failed !", "INFO MSG");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);

            return "login.xhtml";
        }
    }

    public String doLogout() {
        this.username = "";
        this.password = "";
        this.loggedIn = false;
        this.role = "";

        FacesMessage msg = new FacesMessage("Logout success !", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return "login.xhtml";
    }
    
    private boolean currentRoleAllowed(List<String> roles) {
        if (isLoggedIn()) {
            for (String r : roles) {
                if (this.role.equals(r)) {
                    return true;
                }
            }
        } else {
            return false;
        }
        
        return false;
    }
    
    public boolean isAccessAllowed(String page) {
        if (page.endsWith("newchampionship.xhtml")) {
            List<String> roles = new ArrayList<>();
            roles.add("ADMIN");
            return currentRoleAllowed(roles);
        } else if (page.endsWith("newteam.xhtml")) {
            List<String> roles = new ArrayList<>();
            roles.add("ADMIN");
            return currentRoleAllowed(roles);
        } else if (page.endsWith("newplayer.xhtml")) {
            List<String> roles = new ArrayList<>();
            roles.add("LEADER");
            return currentRoleAllowed(roles);
        } else if (page.endsWith("mychampionship.xhtml")) {
            List<String> roles = new ArrayList<>();
            roles.add("LEADER");
            return currentRoleAllowed(roles);
        } else if (page.endsWith("player.xhtml")) {
            List<String> roles = new ArrayList<>();
            roles.add("PLAYER");
            return currentRoleAllowed(roles);
        } else {
            return true;
        }
    }
    
    public List<Route> getRoutesByRole() {
        if (role.equals("ADMIN")) {
            List<Route> routes = new ArrayList<>();
            routes.add(new Route("New Championship", "newchampionship"));
            routes.add(new Route("New Team", "newteam"));
            return routes;
        } else if (role.equals("LEADER")) {
            List<Route> routes = new ArrayList<>();
            routes.add(new Route("New Player", "newplayer"));
            routes.add(new Route("Championship", "mychampionship"));
            return routes;
        } else if (role.equals("PLAYER")) {
            List<Route> routes = new ArrayList<>();
            routes.add(new Route("Me", "player"));
            return routes;
        } else {
            List<Route> routes = new ArrayList<>();
            return routes;
        }
    }
}
