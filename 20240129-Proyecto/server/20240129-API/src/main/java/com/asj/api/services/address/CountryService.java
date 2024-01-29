package com.asj.api.services.address;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.models.address.CountryModel;
import com.asj.api.repositories.address.CountryRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class CountryService {

	@Autowired
	CountryRepository countryRepository;
	
	@PersistenceContext
    private EntityManager entityManager;

	public List<CountryModel> getAllCountries() {
		return countryRepository.findAll();
	}

	public Optional<CountryModel> getCountryById(Integer id) {
		return countryRepository.findById(id);
	}

	@Transactional
	public CountryModel createCountry(CountryModel country) {
		country.setCreatedAt(LocalDateTime.now());
		country.setUpdatedAt(LocalDateTime.now());

		CountryModel createdCountry = countryRepository.save(country);
		entityManager.refresh(createdCountry);
		
		return createdCountry;
	}

	@Transactional
	public Optional<CountryModel> updateCountry(Integer id, CountryModel country) {
		Optional<CountryModel> optionalCountry = Optional.empty();

		if (countryRepository.existsById(id)) {
			country.setUpdatedAt(LocalDateTime.now());
			
			CountryModel updatedCountry = countryRepository.save(country);
			entityManager.refresh(updatedCountry);
			
			optionalCountry = Optional.of(updatedCountry);
		}

		return optionalCountry;
	}

	@Transactional
	public Optional<CountryModel> patchCountry(Integer id, CountryModel country) {
		Optional<CountryModel> optionalCountry = getCountryById(id);

		if (optionalCountry.isPresent()) {
			CountryModel existingCountry = optionalCountry.get();

			if (country.getName() != null) {
				existingCountry.setName(country.getName());
			}
			
			existingCountry.setUpdatedAt(LocalDateTime.now());
			
			countryRepository.save(existingCountry);
			entityManager.flush();
			entityManager.refresh(existingCountry);

			return Optional.of(existingCountry);
		} else {
			return Optional.empty();
		}
	}

	@Transactional
	public Optional<CountryModel> deleteCountry(Integer id) {
		Optional<CountryModel> optionalCountry = getCountryById(id);

		if (optionalCountry.isPresent()) {
			countryRepository.deleteById(id);
		}

		return optionalCountry;
	}

}