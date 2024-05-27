package com.example.uchebka1;

public class User {
    private String idevent;
    private String typeOfEvent;
    private String date;
    private String nameOfEvent;
    private String countPeople;
    private String idusers;
    private String login;
    private String password;
    private String rol;
    private String phone;
    private Boolean verified;

    public User(String login, String password, String phone, String rol) {
        this.login = login;
        this.password = password;
        this.rol = rol;
        this.phone = phone;
    }

    public User(String idusers, String login, String phone) {
        this.idusers = idusers;
        this.login = login;
        this.phone = phone;
    }
    public User() {
    }
    public User(String idevent, String ignoredType_of_event, String date, String name_of_event, String count_people, String login, String phone, Boolean verified) {

        this.idevent = idevent;
        this.typeOfEvent = ignoredType_of_event;
        this.date = date;
        this.nameOfEvent = name_of_event;
        this.countPeople = count_people;
        this.login = login;
        this.phone = phone;
        this.verified = verified;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean isVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getIdevent() {
        return idevent;
    }

    public void setIdevent(String idevent) {
        this.idevent = idevent;
    }

    public String getTypeOfEvent() {
        return typeOfEvent;
    }

    public void setTypeOfEvent(String typeOfEvent) {
        this.typeOfEvent = typeOfEvent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNameOfEvent() {
        return nameOfEvent;
    }

    public void setNameOfEvent(String nameOfEvent) {
        this.nameOfEvent = nameOfEvent;
    }

    public String getCountPeople() {
        return countPeople;
    }

    public void setCountPeople(String countPeople) {
        this.countPeople = countPeople;
    }
    public void ToString(){
        System.out.println(idevent+", "+typeOfEvent +", "+date +", "+nameOfEvent +", "+countPeople +", "+idusers +", "+login +", "+phone +", "+verified);
    }
}
