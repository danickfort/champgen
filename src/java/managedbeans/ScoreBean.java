/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import beans.Championship;
import beans.Match;
import beans.MatchDay;
import beans.Team;
import beans.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author matthieu.rossier
 */
@ManagedBean
@SessionScoped
public class ScoreBean {

    private int scoreTeam1;
    private int scoreTeam2;
    private Date date;
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    @ManagedProperty(value = "#{mainController}")
    private MainController mainController;

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public int getScoreTeam1() {
        return scoreTeam1;
    }

    public int getScoreTeam2() {
        return scoreTeam2;
    }

    public Date getDate() {
        return date;
    }

    public void setScoreTeam1(int scoreTeam1) {
        this.scoreTeam1 = scoreTeam1;
    }

    public void setScoreTeam2(int scoreTeam2) {
        this.scoreTeam2 = scoreTeam2;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Creates a new instance of ScoreBean
     */
    public ScoreBean() {
        this.scoreTeam1 = 0;
        this.scoreTeam2 = 0;
        this.date = new Date();
    }

    public List<Match> searchChampionship() {
        int idLeader = this.loginBean.getId();
        User leader = this.mainController.findUserById(idLeader);
        Championship championship = leader.getTeamOwn().getChampionship();

        List<Match> matchList = new ArrayList<>();

        List<MatchDay> matchDayList = this.mainController.findMatchDayByChampionship(championship);
        for (MatchDay matchDay : matchDayList) {
            matchList.add(null);
            matchList.addAll(matchDay.getMatches());
        }

        return matchList;
    }

    public void setScoreAndDate(Match match) {
        match.setTeam1_score(this.scoreTeam1);
        match.setTeam2_score(this.scoreTeam2);
        match.setDate(new java.sql.Date(this.date.getTime()));

        this.mainController.updateMatch(match);

        this.scoreTeam1 = 0;
        this.scoreTeam2 = 0;
        this.date = new Date();

        FacesMessage msg = new FacesMessage("Match with match id : " + match.getId() + " edit successfull !", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public boolean isMatchLeaderConnected(Team team1, Team team2) {
        int idLeader = this.loginBean.getId();
        User leader = this.mainController.findUserById(idLeader);
        
        if (team1.getLeader().getId() == leader.getId()) {
            return true;
        }
        
        if (team2.getLeader().getId() == leader.getId()) {
            return true;
        }
        
        return false;
    }
}
