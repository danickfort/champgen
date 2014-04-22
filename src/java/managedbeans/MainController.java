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
import beans.UserGroup;
import facades.ChampionshipFacade;
import facades.MatchDayFacade;
import facades.MatchFacade;
import facades.TeamFacade;
import facades.UserFacade;
import facades.UserGroupFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.persistence.Query;

/**
 *
 * @author matthieu.rossier
 */
@Named(value = "mainController")
@Dependent
public class MainController implements Serializable {

    @EJB
    private UserFacade userFacade;
    @EJB
    private UserGroupFacade userGroupFacade;
    @EJB
    private ChampionshipFacade championshipFacade;
    @EJB
    private TeamFacade teamFacade;
    @EJB
    private MatchDayFacade matchdayFacade;
    @EJB
    private MatchFacade matchFacade;
    
    /*
    private int pageIndex;

    public MainController() {
        this.pageIndex = 0;
    }
    
    public int getPageIndex() {
        return this.pageIndex;
    }
    
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
    */
    
    public List<Championship> paginate(int pageIndex) {
        return this.championshipFacade.paginate(pageIndex);
    }
    
    public List<Integer> countChampionship() {
        int numberOfChampionships = championshipFacade.count();
        if (numberOfChampionships % 3 != 0) {
            numberOfChampionships = numberOfChampionships / 3 + 1;
        } else {
            numberOfChampionships = numberOfChampionships / 3;
        }
        List<Integer> temp = new ArrayList<>();
        for (int i = 1; i <= numberOfChampionships; i++) {
            temp.add(i);
        }
        return temp;
    }
    
    public int updatePassword(int userId, String password) {
        return userFacade.updatePassword(userId, password);
    }

    public List<User> getAllUser() {
        return userFacade.findAll();
    }

    public List<User> getUserByUsernameAndPassword(String username, String password) {
        return userFacade.getUserByUsernameAndPassword(username, password);
    }

    public List<UserGroup> getRoleByUser(User user) {
        return userGroupFacade.getRoleByUser(user);
    }

    public void persistChampionshipObject(Championship championship) {
        championshipFacade.create(championship);
    }

    public Championship findChampionshipById(int id) {
        return championshipFacade.find(id);
    }

    public List<Championship> getAllChampionships() {
        return championshipFacade.findAll();
    }

    public void persistUser(User user) {
        userFacade.create(user);
    }

    public void persistUserGroup(UserGroup userGroup) {
        userGroupFacade.create(userGroup);
    }

    public void persistTeam(Team team) {
        teamFacade.create(team);
    }

    public void updateUser(User user) {
        userFacade.edit(user);
    }

    public Team findTeamByName(String teamName) {
        return teamFacade.findTeamByName(teamName);
    }

    public User findUserById(int id) {
        return userFacade.find(id);
    }

    public Team findTeamByLeader(User leader) {
        return teamFacade.findByLeader(leader);
    }

    public List<Team> getChampionshipTeams(Championship championship) {
        return championshipFacade.getTeams(championship);
    }
    
    public List<Team> getOrderedChampionshipTeams(Championship championship) {
        List<Team> teams = championshipFacade.getTeams(championship);
        List<Team> orderedTeams = new ArrayList<>();
        Team worstTeam;
        int size = teams.size();
        
        for(int i = 0; i<size; i++)
        {
            worstTeam = teams.get(0);
            
            for(Team team: teams)
            {
                if(getScoreByTeamAndChampionship(team, championship) > getScoreByTeamAndChampionship(worstTeam, championship))
                {
                    worstTeam = team;
                }
            }
            orderedTeams.add(worstTeam);
            teams.remove(worstTeam);
        }
        
        return orderedTeams;
    }

    void persistMatchday(MatchDay matchDay) {
        matchdayFacade.create(matchDay);
    }

    public List<MatchDay> findMatchDayByChampionship(Championship championship) {
        return matchdayFacade.findByChampionship(championship);
    }

    void updateMatch(Match match) {
        matchFacade.edit(match);
    }
    
    public int getScoreByTeamAndChampionship(Team team, Championship championship)
    {
        List<MatchDay> matchdays = (List<MatchDay>) championship.getMatchdays();
        List<Match> matches = new ArrayList<Match>();
        
        for(MatchDay matchday: matchdays)
        {
            matches.addAll(matchday.getMatches());
        }
        
        int score = 0;
        
        for(Match match: matches)
        {
            if(match.getDate() != null)
            {
                if(match.getTeam1().getId() == team.getId())
                {
                    if(match.getTeam1_score()>match.getTeam2_score())
                    {
                        score += 3;
                    }
                    else if(match.getTeam1_score() == match.getTeam2_score())
                    {
                        score += 1;
                    }
                }
                if(match.getTeam2().getId() == team.getId())
                {
                    if(match.getTeam1_score()<match.getTeam2_score())
                    {
                        score += 3;
                    }
                    else if(match.getTeam1_score() == match.getTeam2_score())
                    {
                        score += 1;
                    }
                }
            }
        }
        
        return score;
    }
    
    public int getWinsByTeamAndChampionship(Team team, Championship championship)
    {
        List<MatchDay> matchdays = (List<MatchDay>) championship.getMatchdays();
        List<Match> matches = new ArrayList<Match>();
        
        for(MatchDay matchday: matchdays)
        {
            matches.addAll(matchday.getMatches());
        }
        
        int score = 0;
        
        for(Match match: matches)
        {
            if(match.getDate() != null)
            {
                if(match.getTeam1().getId() == team.getId())
                {
                    if(match.getTeam1_score()>match.getTeam2_score())
                    {
                        score ++;
                    }
                }
                if(match.getTeam2().getId() == team.getId())
                {
                    if(match.getTeam1_score()<match.getTeam2_score())
                    {
                        score ++;
                    }
                }
            }
        }
        
        return score;
    }
    
    public int getLossesByTeamAndChampionship(Team team, Championship championship)
    {
        List<MatchDay> matchdays = (List<MatchDay>) championship.getMatchdays();
        List<Match> matches = new ArrayList<Match>();
        
        for(MatchDay matchday: matchdays)
        {
            matches.addAll(matchday.getMatches());
        }
        
        int score = 0;
        
        for(Match match: matches)
        {
            if(match.getDate() != null)
            {
                if(match.getTeam1().getId() == team.getId())
                {
                    if(match.getTeam1_score()<match.getTeam2_score())
                    {
                        score ++;
                    }
                }
                if(match.getTeam2().getId() == team.getId())
                {
                    if(match.getTeam1_score()>match.getTeam2_score())
                    {
                        score ++;
                    }
                }
            }
        }
        
        return score;
    }
    
        public int getDrawsByTeamAndChampionship(Team team, Championship championship)
    {
        List<MatchDay> matchdays = (List<MatchDay>) championship.getMatchdays();
        List<Match> matches = new ArrayList<Match>();
        
        for(MatchDay matchday: matchdays)
        {
            matches.addAll(matchday.getMatches());
        }
        
        int score = 0;
        
        for(Match match: matches)
        {
            if(match.getDate() != null)
            {
                if(match.getTeam1().getId() == team.getId())
                {
                    if(match.getTeam1_score()==match.getTeam2_score())
                    {
                        score ++;
                    }
                }
                if(match.getTeam2().getId() == team.getId())
                {
                    if(match.getTeam1_score()==match.getTeam2_score())
                    {
                        score ++;
                    }
                }
            }
        }
        
        return score;
    }
}
