package tech.gebel.countrysearch.controllers;

import tech.gebel.countrysearch.models.Country;

public interface CountryFilter {
    boolean test(Country country);
}
