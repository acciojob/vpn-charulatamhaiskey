package com.driver.model;

import javax.persistence.*;

@Entity
@Table(name = "connection")
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //Mapping for User(user-->connection)
    @ManyToOne
    @JoinColumn
    private User user;

    //Mapping for ServiceProvider(serviceProvider-->connection)
    @ManyToOne
    @JoinColumn
    private ServiceProvider serviceProvider;

    //NoArgsConstructor

    public Connection() {
    }

    //Getters

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    //Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
}
