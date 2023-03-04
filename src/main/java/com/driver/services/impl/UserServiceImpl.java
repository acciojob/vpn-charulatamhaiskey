package com.driver.services.impl;

import com.driver.model.Country;
import com.driver.model.CountryName;
import com.driver.model.ServiceProvider;
import com.driver.model.User;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;
import com.driver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository3;
    @Autowired
    ServiceProviderRepository serviceProviderRepository3;
    @Autowired
    CountryRepository countryRepository3;

    @Override
    public User register(String username, String password, String countryName) throws Exception{
        //create a user of given country. The originalIp of the user should be "countryCode.userId" and return the user. Note that right now user is not connected and thus connected would be false and maskedIp would be null
        //Note that the userId is created automatically by the repository layer
        if(countryName.equalsIgnoreCase("IND") ||
                countryName.equalsIgnoreCase("USA") ||
                countryName.equalsIgnoreCase("AUS")||
                countryName.equalsIgnoreCase("JPN")||
                countryName.equalsIgnoreCase("CHI")) {

            //User Created of given country
            User user = new User();
            user.setPassword(password);
            user.setUsername(username);
            //Note that the userId is created automatically by the repository layer

    // Each time you register a user, you should create a new Country object based on the given country name
    // and assign it as the original country of the user.

            Country country = new Country();

            if (countryName.equalsIgnoreCase("ind")) {
                country.setCountryName(CountryName.IND);
                country.setCode(CountryName.IND.toCode());
            }
            if (countryName.equalsIgnoreCase("usa")) {
                country.setCountryName(CountryName.USA);
                country.setCode(CountryName.USA.toCode());
            }
            if (countryName.equalsIgnoreCase("aus")) {
                country.setCountryName(CountryName.AUS);
                country.setCode(CountryName.AUS.toCode());
            }
            if (countryName.equalsIgnoreCase("jpn")) {
                country.setCountryName(CountryName.JPN);
                country.setCode(CountryName.JPN.toCode());
            }
            if (countryName.equalsIgnoreCase("chi")) {
                country.setCountryName(CountryName.CHI);
                country.setCode(CountryName.CHI.toCode());
            }

            country.setUser(user);
            user.setOriginalCountry(country);
            user.setConnected(false);


            //IP=CountryCode.UserID
            String IP = country.getCode() +"."+ userRepository3.save(user).getId();
            user.setOriginalIp(IP);


            // Note that the service provider attribute of the country in this case would be null.

            userRepository3.save(user);
            return user;
        }
        else
            throw new Exception("Country not found");
    }

    @Override
    public User subscribe(Integer userId, Integer serviceProviderId) {

        //find User by userId
        User user = userRepository3.findById(userId).get();

        //Find service Provider using serviceProviderId
        ServiceProvider serviceProvider = serviceProviderRepository3.findById(serviceProviderId).get();

        //subscribe to the serviceProvider by adding it to the list of providers and return updated User
        user.getServiceProviderList().add(serviceProvider);
        serviceProvider.getUsers().add(user);

        serviceProviderRepository3.save(serviceProvider);
        return user;
    }
}
