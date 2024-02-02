package com.asj.api.services.supplier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesExistException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.supplier.IndustryModel;
import com.asj.api.repositories.supplier.IndustryRepository;
import com.asj.api.repositories.supplier.SupplierRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class IndustryService {

	@Autowired
	IndustryRepository industryRepository;

	@Autowired
	SupplierRepository supplierRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<IndustryModel> getAllIndustries() {
		return industryRepository.findAll();
	}

	public Optional<IndustryModel> getIndustryById(Integer id) {
		return industryRepository.findById(id);
	}

	@Transactional
	public IndustryModel createIndustry(IndustryModel industry) {
		if (!isNameUnique(industry.getName())) {
			throw new UniqueViolationException("Name must be unique");
		}

		industry.setCreatedAt(LocalDateTime.now());
		industry.setUpdatedAt(LocalDateTime.now());

		IndustryModel createdIndustry = industryRepository.save(industry);
		entityManager.refresh(createdIndustry);

		return createdIndustry;
	}

	@Transactional
	public Optional<IndustryModel> updateIndustry(Integer id, IndustryModel industry) {
		if (!isNameUniqueAndIdNot(industry.getName(), id)) {
			throw new UniqueViolationException("Name must be unique");
		}

		Optional<IndustryModel> optionalIndustry = Optional.empty();

		if (industryRepository.existsById(id)) {
			industry.setUpdatedAt(LocalDateTime.now());

			IndustryModel updatedIndustry = industryRepository.save(industry);
			entityManager.refresh(updatedIndustry);

			optionalIndustry = Optional.of(updatedIndustry);
		}

		return optionalIndustry;
	}

	@Transactional
	public Optional<IndustryModel> patchIndustry(Integer id, IndustryModel industry) {
		Optional<IndustryModel> optionalIndustry = getIndustryById(id);

		if (optionalIndustry.isPresent()) {
			IndustryModel existingIndustry = optionalIndustry.get();

			if (industry.getName() != null) {
				if (!isNameUniqueAndIdNot(industry.getName(), id)) {
					throw new UniqueViolationException("Name must be unique");
				}
				existingIndustry.setName(industry.getName());
			}

			existingIndustry.setUpdatedAt(LocalDateTime.now());

			industryRepository.save(existingIndustry);
			entityManager.flush();
			entityManager.refresh(existingIndustry);

			return Optional.of(existingIndustry);
		} else {
			return Optional.empty();
		}
	}

	@Transactional
	public Optional<IndustryModel> deleteIndustry(Integer id) {
		if (supplierRepository.countByIndustry(id) > 0) {
			throw new AssociatedEntitiesExistException("Deletion failed: Associated entities found");
		}

		Optional<IndustryModel> optionalIndustry = getIndustryById(id);

		if (optionalIndustry.isPresent()) {
			industryRepository.deleteById(id);
		}

		return optionalIndustry;
	}

	private boolean isNameUnique(String name) {
		return !industryRepository.existsByName(name);
	}

	private boolean isNameUniqueAndIdNot(String name, Integer id) {
		return !industryRepository.existsByNameAndIdNot(name, id);
	}

	public boolean isIdValid(Integer id) {
		return industryRepository.existsById(id);
	}

}
