package com.asj.api.services.supplier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.models.supplier.VatConditionModel;
import com.asj.api.repositories.supplier.VatConditionRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class VatConditionService {

	@Autowired
	VatConditionRepository vatConditionRepository;
	
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
		vatCondition.setCreatedAt(LocalDateTime.now());
		vatCondition.setUpdatedAt(LocalDateTime.now());

		VatConditionModel createdVatCondition = vatConditionRepository.save(vatCondition);
		entityManager.refresh(createdVatCondition);

		return createdVatCondition;
	}

	@Transactional
	public Optional<VatConditionModel> updateVatCondition(Integer id, VatConditionModel vatCondition) {
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
		Optional<VatConditionModel> optionalVatCondition = getVatConditionById(id);

		if (optionalVatCondition.isPresent()) {
			vatConditionRepository.deleteById(id);
		}

		return optionalVatCondition;
	}

}