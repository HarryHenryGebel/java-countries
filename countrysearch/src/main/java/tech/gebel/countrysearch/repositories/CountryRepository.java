package tech.gebel.countrysearch.repositories;

import org.springframework.data.repository.CrudRepository;
import tech.gebel.countrysearch.models.Country;

public interface CountryRepository extends CrudRepository<Country, Long> {
}
