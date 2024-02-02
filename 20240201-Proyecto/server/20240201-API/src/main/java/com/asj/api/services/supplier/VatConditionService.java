package com.asj.api.services.supplier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesExistException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.supplier.VatConditionModel;
import com.asj.api.repositories.supplier.TaxInformationRepository;
import com.asj.api.repositories.supplier.VatConditionRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class VatConditionService {

	@Autowired
	VatConditionRepository vatConditionRepository;
	
	@Autowired
	TaxInformationRepository taxInformationRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<VatConditionModel> getAllVatConditions() {
		return vatConditionRepository.findAll();
	}

	public Optional<VatConditionModel> getVatConditionById(Integer id) {
		return vatConditionRepository.findById(id);
	}

	@Transactional
	public VatConditionModel createVatCondition(VatConditionModel vatCondition) {
		if (!isNameUnique(vatCondition.getName())) {
			throw new UniqueViolationException("Name must be unique");
		}

		vatCondition.setCreatedAt(LocalDateTime.now());
		vatCondition.setUpdatedAt(LocalDateTime.now());

		VatConditionModel createdVatCondition = vatConditionRepository.save(vatCondition);
		entityManager.refresh(createdVatCondition);

		return createdVatCondition;
	}

	@Transactional
	public Optional<VatConditionModel> updateVatCondition(Integer id, VatConditionModel vatCondition) {
		if (!isNameUniqueAndIdNot(vatCondition.getName(), id)) {
			throw new UniqueViolationException("Name must be unique");
		}

		Optional<VatConditionModel> optionalVatCondition = Optional.empty();

		if (vatConditionRepository.existsById(id)) {
			vatCondition.setUpdatedAt(LocalDateTime.now());

			VatConditionModel updatedVatCondition = vatConditionRepository.save(vatCondition);
			entityManager.refresh(updatedVatCondition);

			optionalVatCondition = Optional.of(updatedVatCondition);
		}

		return optionalVatCondition;
	}

	@Transactional
	public Optional<VatConditionModel> patchVatCondition(Integer id, VatConditionModel vatCondition) {
		Optional<VatConditionModel> optionalVatCondition = getVatConditionById(id);

		if (optionalVatCondition.isPresent()) {
			VatConditionModel existingVatCondition = optionalVatCondition.get();

			if (vatCondition.getName() != null) {
				if (!isNameUniqueAndIdNot(vatCondition.getName(), id)) {
					throw new UniqueViolationException("Name must be unique");
				}
				existingVatCondition.setName(vatCondition.getName());
			}

			existingVatCondition.setUpdatedAt(LocalDateTime.now());

			vatConditionRepository.save(existingVatCondition);
			entityManager.flush();
			entityManager.refresh(existingVatCondition);

			return Optional.of(existingVatCondition);
		} else {
			return Optional.empty();
		}
	}

	@Transactional
	public Optional<VatConditionModel> deleteVatCondition(Integer id) {
		if (taxInformationRepository.countByVatCondition(id) > 0) {
			throw new AssociatedEntitiesExistException("Deletion failed: Associated entities found");
		}
		
		Optional<VatConditionModel> optionalVatCondition = getVatConditionById(id);

		if (optionalVatCondition.isPresent()) {
			vatConditionRepository.deleteById(id);
		}

		return optionalVatCondition;
	}

	private boolean isNameUnique(String name) {
		return !vatConditionRepository.existsByName(name);
	}

	private boolean isNameUniqueAndIdNot(String name, Integer id) {
		return !vatConditionRepository.existsByNameAndIdNot(name, id);
	}
	
	public boolean isIdValid(Integer id) {
		return vatConditionRepository.existsById(id);
	}

}
