package com.asj.api.services.address;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.address.CountryModel;
import com.asj.api.repositories.address.CountryRepository;
import com.asj.api.repositories.address.StateRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class CountryService {

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	StateRepository stateRepository;

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
		if (!isNameUnique(country.getName())) {
			throw new UniqueViolationException("Name must be unique");
		}

		country.setCreatedAt(LocalDateTime.now());
		country.setUpdatedAt(LocalDateTime.now());

		CountryModel createdCountry = countryRepository.save(country);
		entityManager.refresh(createdCountry);

		return createdCountry;
	}

	@Transactional
	public Optional<CountryModel> updateCountry(Integer id, CountryModel country) {
		if (!isNameUniqueAndIdNot(country.getName(), id)) {
			throw new UniqueViolationException("Name must be unique");
		}

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
				if (!isNameUniqueAndIdNot(country.getName(), id)) {
					throw new UniqueViolationException("Name must be unique");
				}
				existingCountry.setName(country.getName());
			}
			
			if (country.getIsDeleted() != null) {
				existingCountry.setIsDeleted(country.getIsDeleted());
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
		if (stateRepository.countByCountry(id) > 0) {
			throw new AssociatedEntitiesException("Deletion failed: Associated entities found");
		}

		Optional<CountryModel> optionalCountry = getCountryById(id);

		if (optionalCountry.isPresent()) {
			countryRepository.deleteById(id);
		}

		return optionalCountry;
	}

	private boolean isNameUnique(String name) {
		return !countryRepository.existsByName(name);
	}

	private boolean isNameUniqueAndIdNot(String name, Integer id) {
		return !countryRepository.existsByNameAndIdNot(name, id);
	}

	public boolean isIdValid(Integer id) {
		return countryRepository.existsById(id);
	}

}
