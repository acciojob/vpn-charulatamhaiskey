package com.driver.model;

import javax.persistence.*;

// Note: Do not write @Enumerated annotation above CountryName in this model.
@Entity
@Table(name = "country")
public class Country{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //Enum
    private CountryName countryName;

    private String code;

    //Mapping for user(user[parent]-->country[child]
    @OneToOne
    @JoinColumn
    private User user;

    //Mapping for service-provider(serviceProvider[parent]-->country[child])
    @ManyToOne
    @JoinColumn
    private ServiceProvider serviceProvider;

    //NoArgsConstructor
    public Country() {
    }

    //parameterized Constructor

    public Country(CountryName countryName, String code) {
        this.countryName = countryName;
        this.code = code;
    }

    //Getters

    public int getId() {
        return id;
    }

    public CountryName getCountryName() {
        return countryName;
    }

    public String getCode() {
        return code;
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

    public void setCountryName(CountryName countryName) {
        this.countryName = countryName;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
}
