package com.y4d3.domain.roles;

import com.y4d3.domain.ADomainObject;
import com.y4d3.domain.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by semo on 04.01.17.
 */
@Entity
public class Role extends ADomainObject {

    private String role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (name = "USER_ROLE", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    // ~ defaults to @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "role_id"),
    //     inverseJoinColumns = @joinColumn(name = "user_id"))
    private List<User> users = new ArrayList<User>();

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        if (!this.users.contains(user)) {
            this.users.add(user);
        }

        if (!user.getRoles().contains(this)) {
            user.getRoles().add(this);
        }
    }

    public void removeUser(User user) {
        this.users.remove(user);
        user.getRoles().remove(this);
    }

}
