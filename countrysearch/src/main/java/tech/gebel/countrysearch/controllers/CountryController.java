package tech.gebel.countrysearch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tech.gebel.countrysearch.repositories.CountryRepository;
@RestController
public class CountryController {
    @SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "SpringJavaAutowiredFieldsWarningInspection"})
    @Autowired
    private CountryRepository countryRepository;
    private Hashtable<String, Object> response;
}
