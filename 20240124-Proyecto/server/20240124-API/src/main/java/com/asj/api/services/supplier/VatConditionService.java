package com.asj.api.services.supplier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asj.api.models.supplier.VatConditionModel;
import com.asj.api.repositories.supplier.VatConditionRepository;

@Service
public class VatConditionService {

	@Autowired
	VatConditionRepository vatConditionRepository;

	public List<VatConditionModel> getAllVatConditions() {
		return vatConditionRepository.findAll();
	}

	public Optional<VatConditionModel> getVatConditionById(Integer id) {
		return vatConditionRepository.findById(id);
	}

	public VatConditionModel createVatCondition(VatConditionModel vatCondition) {
		LocalDateTime currentTimestamp = LocalDateTime.now();
		vatCondition.setCreatedAt(currentTimestamp);
		vatCondition.setUpdatedAt(currentTimestamp);

		return vatConditionRepository.save(vatCondition);
	}

	public Optional<VatConditionModel> updateVatCondition(Integer id, VatConditionModel vatCondition) {
		Optional<VatConditionModel> optionalVatCondition = Optional.empty();

		if (vatConditionRepository.existsById(id)) {
			vatCondition.setId(id);
			vatCondition.setUpdatedAt(LocalDateTime.now());
			optionalVatCondition = Optional.of(vatConditionRepository.save(vatCondition));
		}

		return optionalVatCondition;
	}

	public Optional<VatConditionModel> patchVatCondition(Integer id, VatConditionModel vatCondition) {
		Optional<VatConditionModel> optionalVatCondition = getVatConditionById(id);

		if (optionalVatCondition.isPresent()) {
			VatConditionModel existingVatCondition = optionalVatCondition.get();

			if (vatCondition.getName() != null) {
				existingVatCondition.setName(vatCondition.getName());
			}
			
			existingVatCondition.setUpdatedAt(LocalDateTime.now());

			return Optional.of(vatConditionRepository.save(existingVatCondition));
		} else {
			return Optional.empty();
		}
	}

	public Optional<VatConditionModel> deleteVatCondition(Integer id) {
		Optional<VatConditionModel> optionalVatCondition = getVatConditionById(id);

		if (optionalVatCondition.isPresent()) {
			vatConditionRepository.deleteById(id);
		}

		return optionalVatCondition;
	}

}