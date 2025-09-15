package com.iso.ecommerce.model;

import com.iso.ecommerce.model.enums.Role;

public class User extends BaseModel {
    private String username;
    private String passwrd;
    private Role role;
    private Boolean active;

    public User() {
    }

    public User(String username, String passwrd) {
        this.username = username;
        this.passwrd = passwrd;
        this.role = Role.SUPPORT;
        this.active = true;
    }

    public User(String username, String passwrd, Role role) {
        this.username = username;
        this.passwrd = passwrd;
        this.role = role;
        this.active = true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswrd() {
        return passwrd;
    }

    public void setPasswrd(String passwrd) {
        this.passwrd = passwrd;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", passwrd='" + passwrd + '\'' +
                ", role=" + role +
                ", active=" + active +
                '}';
    }
}
