package com.example.uchebka1;

public class User {
    private String idusers;
    private String login;
    private String password;
    private String rol;

    public User(String idusers, String login, String password, String rol) {
        this.idusers = idusers;
        this.login = login;
        this.password = password;
        this.rol = rol;
    }

    public User() {

    }


    public String getIdusers() {
        return idusers;
    }

    public void setIdusers(String idusers) {
        this.idusers = idusers;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
