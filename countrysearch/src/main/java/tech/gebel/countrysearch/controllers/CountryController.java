package tech.gebel.countrysearch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tech.gebel.countrysearch.models.Country;
import tech.gebel.countrysearch.repositories.CountryRepository;

import java.util.ArrayList;
import java.util.Hashtable;
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

    @GetMapping(value = "/names/start/{letter}", produces = {"application/json"})
    private ResponseEntity<?> getCountriesByLetter(@PathVariable char letter) {
        ArrayList<Country> countries = new ArrayList<>();
        countryRepository.findAll().iterator().forEachRemaining(countries::add);
        List<Country> returnValue =
                getCountriesByLetter(
                        countries, country ->
                                country.getName().toLowerCase().charAt(0) == Character.toLowerCase(letter));
        returnValue.sort((country1, country2) -> country1.getName().compareToIgnoreCase(country2.getName()));
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @GetMapping(value = "/population/total", produces = {"application/json"})
    private ResponseEntity<?> getTotalPopulation() {
        response = new Hashtable<>();
        List<Country> countries = new ArrayList<>();
        countryRepository.findAll().iterator().forEachRemaining(countries::add);
        long totalPopulation = 0;
        for (Country country : countries) {
            totalPopulation += country.getPopulation();
        }
        System.out.println("The total population is " + totalPopulation);
        response.put("Total Population", totalPopulation);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/population/min", produces = {"application/json"})
    private ResponseEntity<?> getMinPopulation() {
        response = new Hashtable<>();
        ArrayList<Country> countries = new ArrayList<>();
        countryRepository.findAll().iterator().forEachRemaining(countries::add);
        countries.sort((country1, country2) -> (int) (country1.getPopulation() - country2.getPopulation()));
        response.put("lowestPopulation", countries.get(0));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
