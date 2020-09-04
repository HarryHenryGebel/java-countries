package tech.gebel.countrysearch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tech.gebel.countrysearch.repositories.CountryRepository;
import java.util.ArrayList;
@RestController
public class CountryController {
    @SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "SpringJavaAutowiredFieldsWarningInspection"})
    @Autowired
    private CountryRepository countryRepository;
    private Hashtable<String, Object> response;

    private ArrayList<Country> getCountriesByLetter(ArrayList<Country> listToTest, CountryFilter test) {
        ArrayList<Country> returnValue = new ArrayList<>();
        for (Country country : listToTest) {
            if (test.test(country)) {
                returnValue.add(country);
            }
        }
        return returnValue;
    }
}
