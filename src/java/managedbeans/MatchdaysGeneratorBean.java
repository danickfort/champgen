/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import beans.Championship;
import beans.Match;
import beans.MatchDay;
import beans.Team;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author garynietlispach
 */
@ManagedBean
@SessionScoped
public class MatchdaysGeneratorBean {

    @ManagedProperty(value = "#{mainController}")
    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Creates a new instance of MatchdaysGeneratorBean
     */
    public MatchdaysGeneratorBean() {
    }

    public void generateMatchdays(Championship championship) {
        List<MatchDay> matchdayList = new ArrayList<>();
        List<Team> teamList = mainController.getChampionshipTeams(championship);
        String message = "";

        if (teamList.size() % 2 == 0) {
            work(matchdayList, teamList, message, championship);
        } else {
            teamList.add(new Team(-1, message, null, null));
            work(matchdayList, teamList, message, championship);
        }
        FacesMessage msg = new FacesMessage(message, "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void work(List<MatchDay> matchdayList, List<Team> teamList, String message, Championship championship) {
        List<Team> firstList = new ArrayList<>();
        List<Team> reverseList = new ArrayList<>();

        for (int k = 1; k < teamList.size(); k++) {
            if (k < teamList.size() / 2) {
                firstList.add(teamList.get(k));
            } else {
                reverseList.add(teamList.get(k));
            }
        }

        for (int i = 0; i < teamList.size() - 1; i++) {
            MatchDay matchDay = new MatchDay();
            matchDay.setChampionship(championship);
            List<Match> matches = new ArrayList<>();

            message += " ||| MatchDay : ";
            Collections.rotate(firstList, 1);
            Collections.rotate(reverseList, -1);
            Team temp = firstList.get(0);
            firstList.set(0, reverseList.get(reverseList.size() - 1));
            reverseList.set(reverseList.size() - 1, temp);

            if (teamList.get(0).getId() != -1 && reverseList.get(0).getId() != -1) {
                Match match1 = new Match();
                match1.setTeam1(teamList.get(0));
                match1.setTeam2(reverseList.get(0));
                match1.setMatchDay(matchDay);
                matches.add(match1);
            }            

            message += teamList.get(0).getName() + " vs ";
            message += reverseList.get(0).getName();
            message += " /// ";

            for (int j = 0; j < teamList.size() / 2 - 1; j++) {
                if (firstList.get(j).getId() == -1 || reverseList.get(j + 1).getId() == -1) {
                    continue;
                }
                Match match = new Match();
                match.setTeam1(firstList.get(j));
                match.setTeam2(reverseList.get(j + 1));
                match.setMatchDay(matchDay);

                // match.setMatchDay(matchday);
                matches.add(match);

                message += firstList.get(j).getName() + " vs ";
                message += reverseList.get(j + 1).getName();
                message += " /// ";
            }

            // save the matchday
            matchDay.setMatches(matches);
            mainController.persistMatchday(matchDay);
        }
    }
}
