package com.asj.api.services.supplier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asj.api.models.supplier.TaxInformationModel;
import com.asj.api.repositories.supplier.TaxInformationRepository;

@Service
public class TaxInformationService {

	@Autowired
	TaxInformationRepository taxInformationRepository;

	public List<TaxInformationModel> getAllTaxInformation() {
		return taxInformationRepository.findAll();
	}

	public Optional<TaxInformationModel> getTaxInformationById(Integer id) {
		return taxInformationRepository.findById(id);
	}

	public TaxInformationModel createTaxInformation(TaxInformationModel taxInformation) {
		LocalDateTime currentTimestamp = LocalDateTime.now();
		taxInformation.setCreatedAt(currentTimestamp);
		taxInformation.setUpdatedAt(currentTimestamp);

		return taxInformationRepository.save(taxInformation);
	}

	public Optional<TaxInformationModel> updateTaxInformation(Integer id, TaxInformationModel taxInformation) {
		Optional<TaxInformationModel> optionalTaxInformation = Optional.empty();

		if (taxInformationRepository.existsById(id)) {
			taxInformation.setId(id);
			taxInformation.setUpdatedAt(LocalDateTime.now());
			optionalTaxInformation = Optional.of(taxInformationRepository.save(taxInformation));
		}

		return optionalTaxInformation;
	}

	public Optional<TaxInformationModel> patchTaxInformation(Integer id, TaxInformationModel taxInformation) {
		Optional<TaxInformationModel> optionalTaxInformation = getTaxInformationById(id);

		if (optionalTaxInformation.isPresent()) {
			TaxInformationModel existingTaxInformation = optionalTaxInformation.get();

			if (taxInformation.getCuit() != null) {
				existingTaxInformation.setCuit(taxInformation.getCuit());
			}
			
			if (taxInformation.getVatCondition() != null) {
				existingTaxInformation.setVatCondition(taxInformation.getVatCondition());
			}
			
			existingTaxInformation.setUpdatedAt(LocalDateTime.now());

			return Optional.of(taxInformationRepository.save(existingTaxInformation));
		} else {
			return Optional.empty();
		}
	}

	public Optional<TaxInformationModel> deleteTaxInformation(Integer id) {
		Optional<TaxInformationModel> optionalTaxInformation = getTaxInformationById(id);

		if (optionalTaxInformation.isPresent()) {
			taxInformationRepository.deleteById(id);
		}

		return optionalTaxInformation;
	}

}