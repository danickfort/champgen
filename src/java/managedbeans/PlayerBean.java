/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import beans.Team;
import beans.User;
import beans.UserGroup;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Size;

/**
 *
 * @author matthieu.rossier
 */
@ManagedBean
@SessionScoped
public class PlayerBean {
    
    @Size(min = 2, max = 50)
    private String name;
    
    @ManagedProperty(value = "#{mainController}")
    private MainController mainController;
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    
    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
    
    /**
     * Creates a new instance of playerBean
     */
    public PlayerBean() {
    }
    
    public void persist() {
        // get leader id
        int idLeader = loginBean.getId();
        User leader = mainController.findUserById(idLeader);
        Team team = mainController.findTeamByLeader(leader);
        
        User newplayer = new User();
        newplayer.setUsername(name);
        newplayer.setPassword("password");
        newplayer.setTeam(team);
        
        UserGroup newUserGroup = new UserGroup();
        newUserGroup.setGroupName("PLAYER");
        newUserGroup.setUser(newplayer);
        
        mainController.persistUser(newplayer);
        mainController.persistUserGroup(newUserGroup);
        
        FacesMessage msg = new FacesMessage("New player added !", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
