package com.asj.api.services.supplier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesException;
import com.asj.api.exceptions.InvalidIdentifierException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.supplier.TaxInformationModel;
import com.asj.api.repositories.supplier.SupplierRepository;
import com.asj.api.repositories.supplier.TaxInformationRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class TaxInformationService {

	@Autowired
	TaxInformationRepository taxInformationRepository;
	
	@Autowired
	VatConditionService vatConditionService;
	
	@Autowired
	SupplierRepository supplierRepository;
	
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
		if (!isCuitUnique(taxInformation.getCuit())) {
			throw new UniqueViolationException("CUIT must be unique");
		}
		
		validateVatCondition(taxInformation.getVatCondition().getId());
		
		taxInformation.setCreatedAt(LocalDateTime.now());
		taxInformation.setUpdatedAt(LocalDateTime.now());

		TaxInformationModel createdTaxInformation = taxInformationRepository.save(taxInformation);
		entityManager.refresh(createdTaxInformation);

		return createdTaxInformation;
	}
	
	@Transactional
	public Optional<TaxInformationModel> updateTaxInformation(Integer id, TaxInformationModel taxInformation) {
		if (!isCuitUniqueAndIdNot(taxInformation.getCuit(), id)) {
			throw new UniqueViolationException("CUIT must be unique");
		}
		
		validateVatCondition(taxInformation.getVatCondition().getId());
		
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
				if (!isCuitUniqueAndIdNot(taxInformation.getCuit(), id)) {
					throw new UniqueViolationException("CUIT must be unique");
				}
				existingTaxInformation.setCuit(taxInformation.getCuit());
			}
			
			if (taxInformation.getVatCondition() != null) {
				validateVatCondition(taxInformation.getVatCondition().getId());
				existingTaxInformation.setVatCondition(taxInformation.getVatCondition());
			}
			
			if (taxInformation.getIsDeleted() != null) {
				existingTaxInformation.setIsDeleted(taxInformation.getIsDeleted());
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
		if (supplierRepository.countByTaxInformation(id) > 0) {
			throw new AssociatedEntitiesException("Deletion failed: Associated entities found");
		}
		
		Optional<TaxInformationModel> optionalTaxInformation = getTaxInformationById(id);

		if (optionalTaxInformation.isPresent()) {
			taxInformationRepository.deleteById(id);
		}

		return optionalTaxInformation;
	}
	
	private boolean isCuitUnique(String cuit) {
		return !taxInformationRepository.existsByCuit(cuit);
	}

	private boolean isCuitUniqueAndIdNot(String cuit, Integer id) {
		return !taxInformationRepository.existsByCuitAndIdNot(cuit, id);
	}
	
	public boolean isIdValid(Integer id) {
		return taxInformationRepository.existsById(id);
	}
	
	private void validateVatCondition(Integer id) {
		if (!vatConditionService.isIdValid(id)) {
			throw new InvalidIdentifierException("Vat condition is not valid");
		}
	}

}
