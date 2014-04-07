/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import beans.Championship;
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
public class ChampionshipBean {

    @Size(min = 2, max = 50)
    private String championshipName;
    
    @ManagedProperty(value = "#{mainController}")
    private MainController mainController;

    public String getChampionshipName() {
        return championshipName;
    }

    public void setChampionshipName(String championshipName) {
        this.championshipName = championshipName;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public ChampionshipBean() {
    }

    public void persist() {
        Championship championship = new Championship();
        championship.setName(championshipName);
        mainController.persistChampionshipObject(championship);

        FacesMessage msg = new FacesMessage("Championship " + championshipName + " added !", "Championship " + championshipName + " added !");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage("msg:info", msg);
    }
}
