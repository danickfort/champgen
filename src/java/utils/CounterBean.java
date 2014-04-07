/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author matthieu.rossier
 */
@ManagedBean
@RequestScoped
public class CounterBean {

    private int counter;

    /**
     * Creates a new instance of CounterBean
     */
    public CounterBean() {
        this.counter = 0;
    }

    public int getCounter() {
        return this.counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void inc() {
        this.counter++;
    }

    public boolean isNewLine() {
        if (this.counter % 3 == 0) {
            this.inc();

            return true;
        } else {
            this.inc();

            return false;
        }
    }
}
