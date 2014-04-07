/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author matthieu.rossier
 */
@Entity
@Table(name = "user_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserGroup.findAll", query = "SELECT c FROM UserGroup c"),
    @NamedQuery(name = "UserGroup.findById", query = "SELECT c FROM UserGroup c WHERE c.id = :id"),
    @NamedQuery(name = "UserGroup.findRoleByUserId", query = "SELECT g FROM UserGroup g WHERE g.user = :user")})
public class UserGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "groupName")
    private String groupName;
    
    public UserGroup() {
        
    }

    public UserGroup(Integer id, User user, String groupName) {
        this.id = id;
        this.user = user;
        this.groupName = groupName;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserGroup)) {
            return false;
        }
        UserGroup other = (UserGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.UserGroup[ id=" + id + " ]";
    }
    
}
