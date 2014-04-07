/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import beans.Championship;
import beans.Team;
import beans.User;
import beans.UserGroup;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author matthieu.rossier
 */
@ManagedBean
@SessionScoped
public class TeamBean {

    @NotNull
    private int championshipId;
    @Size(min = 2, max = 50)
    private String teamName;
    @Size(min = 2, max = 50)
    private String leaderName;
    
    @ManagedProperty(value = "#{mainController}")
    private MainController mainController;

    public int getChampionshipId() {
        return championshipId;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setChampionshipId(int championshipId) {
        this.championshipId = championshipId;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }
    
    
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Creates a new instance of TeamBean
     */
    public TeamBean() {
    }

    public Map<String, Object> getAllChampionshipsId() {
        Map<String, Object> championshipsId = new LinkedHashMap<>();
        for (Championship c : mainController.getAllChampionships()) {
            if (c.getMatchdays().isEmpty()) championshipsId.put(c.getName(), c.getId());
        }
        return championshipsId;
    }

    public void persist() {
        Championship championship = mainController.findChampionshipById(championshipId);
        
        // Team team = new Team();
        // team.setName(teamName);
        // team.setChampionship(championship);
        // team.setLeader(null);
        
        User leader = new User();
        leader.setUsername(leaderName);
        leader.setPassword("password");
        // leader.setTeamOwn(team);
        
        // team.setLeader(leader);
        
        UserGroup userGroup = new UserGroup();
        userGroup.setGroupName("LEADER");
        userGroup.setUser(leader);
        
        mainController.persistUser(leader);
        mainController.persistUserGroup(userGroup);
        // mainController.persistTeam(team);
        
        User newLeader = mainController.getUserByUsernameAndPassword(leaderName, "password").get(0);
        Team team = new Team();
        team.setName(teamName);
        team.setChampionship(championship);
        team.setLeader(newLeader);
        
        mainController.persistTeam(team);
        
        Team newTeam = mainController.findTeamByName(teamName);
        newLeader.setTeamOwn(newTeam);
        mainController.updateUser(newLeader);
        
        FacesMessage msg = new FacesMessage("Team " + teamName + " added !", "Team " + teamName + " added !");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage("msg:info", msg);
    }
}
