/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author matthieu.rossier
 */
public class Route {
    private String title;
    private String routeName;
    
    public Route() {
    }

    public Route(String title, String routeName) {
        this.title = title;
        this.routeName = routeName;
    }

    public String getTitle() {
        return title;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }
}
