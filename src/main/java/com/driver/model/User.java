package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String originalIp;
    private String maskedIp;
    private boolean connected;


    //Mapping for ServiceProvider(user<--serviceProvider)
    @ManyToMany
    @JoinColumn
    private List<ServiceProvider> serviceProviderList =new ArrayList<>();


    //Mapping for Connection(user-->connection)
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Connection> connectionList=new ArrayList<>();



    //Mapping for Country(country-->user)
    @OneToOne(mappedBy = "user" ,cascade = CascadeType.ALL)
    private  Country originalCountry;


    //NoArgsConstructor

    public User() {
    }

    //ParameterizedConstructor

    public User(String username, String password, Country originalCountry) {
        this.username = username;
        this.password = password;
        this.originalCountry = originalCountry;
    }


    //Getters

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getOriginalIp() {
        return originalIp;
    }

    public String getMaskedIp() {
        return maskedIp;
    }

    public boolean getConnected() {
        return connected;
    }

    public List<ServiceProvider> getServiceProviderList() {
        return serviceProviderList;
    }

    public List<Connection> getConnectionList() {
        return connectionList;
    }

    public Country getOriginalCountry() {
        return originalCountry;
    }

    //Setters


    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOriginalIp(String originalIp) {
        this.originalIp = originalIp;
    }

    public void setMaskedIp(String maskedIp) {
        this.maskedIp = maskedIp;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public void setServiceProviderList(List<ServiceProvider> serviceProviderList) {
        this.serviceProviderList = serviceProviderList;
    }

    public void setConnectionList(List<Connection> connectionList) {
        this.connectionList = connectionList;
    }

    public void setOriginalCountry(Country originalCountry) {
        this.originalCountry = originalCountry;
    }
}
