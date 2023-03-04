package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "serviceProvider")
public class ServiceProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    //Mapping for Admin(Admin[Parent]--->serviceProvide[Child])
    @ManyToOne
    @JoinColumn
    private Admin admin;



    //Mapping for user(serviceProvider[parent]-->user[child])
    @ManyToMany(mappedBy = "serviceProviderList",cascade = CascadeType.ALL)
    private List<User> users=new ArrayList<>();


    //Mapping for connection(serviceProvider-->connection)
    @OneToMany(mappedBy = "serviceProvider",cascade = CascadeType.ALL)
    private List<Connection> connectionList=new ArrayList<>();



    //Mapping for country(serviceProvider-->country)
    @OneToMany(mappedBy = "serviceProvider",cascade = CascadeType.ALL)
    private List<Country> countryList=new ArrayList<>();



    //NoArgsConstructor
    public ServiceProvider() {
    }
    //parameterized Constructor

    public ServiceProvider(String name) {
        this.name = name;
    }

    //getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Admin getAdmin() {
        return admin;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Connection> getConnectionList() {
        return connectionList;
    }

    public List<Country> getCountryList() {
        return countryList;
    }
    //setters

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setConnectionList(List<Connection> connectionList) {
        this.connectionList = connectionList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }
}
