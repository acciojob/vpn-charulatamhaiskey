package com.driver.services.impl;

import com.driver.model.Admin;
import com.driver.model.Country;
import com.driver.model.CountryName;
import com.driver.model.ServiceProvider;
import com.driver.repository.AdminRepository;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository1;

    @Autowired
    ServiceProviderRepository serviceProviderRepository1;

    @Autowired
    CountryRepository countryRepository1;

    @Override
    public Admin register(String username, String password) {
        //create admin Object and set the values and save admin to db
        Admin admin=new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        adminRepository1.save(admin);
        return admin;
    }

    @Override
    public Admin addServiceProvider(int adminId, String providerName) {
        //fetch admin from db
        //add a country under the serviceProvider and return respective service provider
        //country name would be a 3-character string out of ind, aus, usa, chi, jpn. Each character can be in uppercase or lowercase. You should create a new Country object based on the given country name and add it to the country list of the service provider. Note that the user attribute of the country in this case would be null.
        //In case country name is not amongst the above mentioned strings, throw "Country not found" exception
        Admin admin=adminRepository1.findById(adminId).get();


        //create service provider object and set the values(adminId and providerName)
        ServiceProvider serviceProvider=new ServiceProvider();
        serviceProvider.setAdmin(admin);
        serviceProvider.setName(providerName);

        //add service provider to admin
        admin.getServiceProviders().add(serviceProvider);
        //save the admin
        adminRepository1.save(admin);
        return admin;
    }

    @Override
    public ServiceProvider addCountry(int serviceProviderId, String countryName) throws Exception{

        if(countryName.equalsIgnoreCase("ind")||countryName.equalsIgnoreCase("jpn")||
        countryName.equalsIgnoreCase("aus")||countryName.equalsIgnoreCase("usa")||
        countryName.equalsIgnoreCase("chi")){


            //create Country objcet
            Country country=new Country();

            //each service provider can be their in many country
            ServiceProvider serviceProvider=serviceProviderRepository1.findById(serviceProviderId).get();

            if(countryName.equalsIgnoreCase("IND")){
                country.setCountryName(CountryName.IND);
                country.setCode(CountryName.IND.toCode());
            }

            if(countryName.equalsIgnoreCase("CHI")){
                country.setCountryName(CountryName.CHI);
                country.setCode(CountryName.CHI.toCode());
            }

            if(countryName.equalsIgnoreCase("AUS")){
                country.setCountryName(CountryName.AUS);
                country.setCode(CountryName.AUS.toCode());
            }
            if(countryName.equalsIgnoreCase("JPN")){
                country.setCountryName(CountryName.JPN);
                country.setCode(CountryName.JPN.toCode());
            }
            if(countryName.equalsIgnoreCase("USA")){
                country.setCountryName(CountryName.USA);
                country.setCode(CountryName.USA.toCode());
            }
            //manytoone
            country.setServiceProvider(serviceProvider);
            serviceProvider.getCountryList().add(country);
            serviceProviderRepository1.save(serviceProvider);

            return serviceProvider;

        }else
            throw new Exception("country not found");
    }
}
