package tech.gebel.countrysearch.models;

import javax.persistence.*;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long countryid;
    private String name;
    private long population;
    private long landmasskm2;
    private int medianage;

    public Country(String name, long population, long landmasskm2, int medianage) {
        this.name = name;
        this.population = population;
        this.landmasskm2 = landmasskm2;
        this.medianage = medianage;
    }

    public Country() {
    }
}
