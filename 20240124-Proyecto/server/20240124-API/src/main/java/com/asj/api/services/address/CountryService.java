package com.asj.api.services.address;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asj.api.models.address.CountryModel;
import com.asj.api.repositories.address.CountryRepository;

@Service
public class CountryService {

	@Autowired
	CountryRepository countryRepository;

	public List<CountryModel> getAllCountries() {
		return countryRepository.findAll();
	}

	public Optional<CountryModel> getCountryById(Integer id) {
		return countryRepository.findById(id);
	}

	public CountryModel createCountry(CountryModel country) {
		LocalDateTime currentTimestamp = LocalDateTime.now();
		country.setCreatedAt(currentTimestamp);
		country.setUpdatedAt(currentTimestamp);

		return countryRepository.save(country);
	}

	public Optional<CountryModel> updateCountry(Integer id, CountryModel country) {
		Optional<CountryModel> optionalCountry = Optional.empty();

		if (countryRepository.existsById(id)) {
			country.setId(id);
			country.setUpdatedAt(LocalDateTime.now());
			optionalCountry = Optional.of(countryRepository.save(country));
		}

		return optionalCountry;
	}

	public Optional<CountryModel> patchCountry(Integer id, CountryModel country) {
		Optional<CountryModel> optionalCountry = getCountryById(id);

		if (optionalCountry.isPresent()) {
			CountryModel existingCountry = optionalCountry.get();

			if (country.getName() != null) {
				existingCountry.setName(country.getName());
			}
			
			existingCountry.setUpdatedAt(LocalDateTime.now());

			return Optional.of(countryRepository.save(existingCountry));
		} else {
			return Optional.empty();
		}
	}

	public Optional<CountryModel> deleteCountry(Integer id) {
		Optional<CountryModel> optionalCountry = getCountryById(id);

		if (optionalCountry.isPresent()) {
			countryRepository.deleteById(id);
		}

		return optionalCountry;
	}

}