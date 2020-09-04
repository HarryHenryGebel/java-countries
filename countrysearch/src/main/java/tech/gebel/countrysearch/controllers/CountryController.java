package tech.gebel.countrysearch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.gebel.countrysearch.models.Country;
import tech.gebel.countrysearch.repositories.CountryRepository;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping(value = "/names/all", produces = {"application/json"})
    private ResponseEntity<?> getAllCountries() {
        List<Country> countries = new ArrayList<>();
        countryRepository.findAll().iterator().forEachRemaining(countries::add);
        countries.sort((country1, country2) -> country1.getName().compareToIgnoreCase(country2.getName()));
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }
}
