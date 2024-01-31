package com.asj.api.services.supplier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.models.supplier.TaxInformationModel;
import com.asj.api.repositories.supplier.TaxInformationRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class TaxInformationService {

	@Autowired
	TaxInformationRepository taxInformationRepository;
	
	@PersistenceContext
    private EntityManager entityManager;

	public List<TaxInformationModel> getAllTaxInformation() {
		return taxInformationRepository.findAll();
	}

	public Optional<TaxInformationModel> getTaxInformationById(Integer id) {
		return taxInformationRepository.findById(id);
	}

	@Transactional
	public TaxInformationModel createTaxInformation(TaxInformationModel taxInformation) {
		taxInformation.setCreatedAt(LocalDateTime.now());
		taxInformation.setUpdatedAt(LocalDateTime.now());

		TaxInformationModel createdTaxInformation = taxInformationRepository.save(taxInformation);
		entityManager.refresh(createdTaxInformation);

		return createdTaxInformation;
	}
	
	@Transactional
	public Optional<TaxInformationModel> updateTaxInformation(Integer id, TaxInformationModel taxInformation) {
		Optional<TaxInformationModel> optionalTaxInformation = Optional.empty();

		if (taxInformationRepository.existsById(id)) {
			taxInformation.setUpdatedAt(LocalDateTime.now());

			TaxInformationModel updatedTaxInformation = taxInformationRepository.save(taxInformation);
			entityManager.refresh(updatedTaxInformation);

			optionalTaxInformation = Optional.of(updatedTaxInformation);
		}

		return optionalTaxInformation;
	}

	@Transactional
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

			taxInformationRepository.save(existingTaxInformation);
			entityManager.flush();
			entityManager.refresh(existingTaxInformation);

			return Optional.of(existingTaxInformation);
		} else {
			return Optional.empty();
		}
	}

	@Transactional
	public Optional<TaxInformationModel> deleteTaxInformation(Integer id) {
		Optional<TaxInformationModel> optionalTaxInformation = getTaxInformationById(id);

		if (optionalTaxInformation.isPresent()) {
			taxInformationRepository.deleteById(id);
		}

		return optionalTaxInformation;
	}

}