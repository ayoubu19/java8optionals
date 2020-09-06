package fr.norsys;

import java.util.Optional;

public class Address {
    private Country country;

    public Address(Country country) {
        this.country = country;
    }

    public Optional<Country> getCountry() {
        return Optional.ofNullable(this.country);
    }
}
