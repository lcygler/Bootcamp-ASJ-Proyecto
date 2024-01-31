package com.asj.api.services.supplier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.models.supplier.SupplierModel;
import com.asj.api.repositories.supplier.SupplierRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class SupplierService {

	@Autowired
	SupplierRepository supplierRepository;
	
	@PersistenceContext
    private EntityManager entityManager;

	public List<SupplierModel> getAllSuppliers() {
		return supplierRepository.findAll();
	}

	public Optional<SupplierModel> getSupplierById(Integer id) {
		return supplierRepository.findById(id);
	}

	@Transactional
	public SupplierModel createSupplier(SupplierModel supplier) {
		supplier.setCode(generateSupplierCode());
		supplier.setCreatedAt(LocalDateTime.now());
		supplier.setUpdatedAt(LocalDateTime.now());
		supplier.setIsDeleted(false);

		SupplierModel createdSupplier = supplierRepository.save(supplier);
		entityManager.refresh(createdSupplier);

		return createdSupplier;
	}

	@Transactional
	public Optional<SupplierModel> updateSupplier(Integer id, SupplierModel supplier) {
		Optional<SupplierModel> optionalSupplier = Optional.empty();

		if (supplierRepository.existsById(id)) {
			supplier.setUpdatedAt(LocalDateTime.now());

			SupplierModel updatedSupplier = supplierRepository.save(supplier);
			entityManager.refresh(updatedSupplier);

			optionalSupplier = Optional.of(updatedSupplier);
		}

		return optionalSupplier;
	}

	@Transactional
	public Optional<SupplierModel> patchSupplier(Integer id, SupplierModel supplier) {
		Optional<SupplierModel> optionalSupplier = getSupplierById(id);

		if (optionalSupplier.isPresent()) {
			SupplierModel existingSupplier = optionalSupplier.get();

			if (supplier.getBusinessName() != null) {
				existingSupplier.setBusinessName(supplier.getBusinessName());
			}

			if (supplier.getIndustry() != null) {
				existingSupplier.setIndustry(supplier.getIndustry());
			}

			if (supplier.getWebsite() != null) {
				existingSupplier.setWebsite(supplier.getWebsite());
			}

			if (supplier.getEmail() != null) {
				existingSupplier.setEmail(supplier.getEmail());
			}

			if (supplier.getPhone() != null) {
				existingSupplier.setPhone(supplier.getPhone());
			}

			if (supplier.getImage() != null) {
				existingSupplier.setImage(supplier.getImage());
			}

			if (supplier.getAddress() != null) {
				existingSupplier.setAddress(supplier.getAddress());
			}

			if (supplier.getTaxInformation() != null) {
				existingSupplier.setTaxInformation(supplier.getTaxInformation());
			}

			if (supplier.getContactDetails() != null) {
				existingSupplier.setContactDetails(supplier.getContactDetails());
			}

			if (supplier.getIsDeleted() != null) {
				existingSupplier.setIsDeleted(supplier.getIsDeleted());
			}

			existingSupplier.setUpdatedAt(LocalDateTime.now());

			supplierRepository.save(existingSupplier);
			entityManager.flush();
			entityManager.refresh(existingSupplier);

			return Optional.of(existingSupplier);
		} else {
			return Optional.empty();
		}
	}

	@Transactional
	public Optional<SupplierModel> deleteSupplier(Integer id) {
		Optional<SupplierModel> optionalSupplier = getSupplierById(id);

		if (optionalSupplier.isPresent()) {
			supplierRepository.deleteById(id);
		}

		return optionalSupplier;
	}

	public Integer getNextSupplierId() {
		Integer maxSupplierId = supplierRepository.getMaxSupplierId();

		if (maxSupplierId != null) {
			return maxSupplierId + 1;
		} else {
			return 1;
		}
	}

	private String generateSupplierCode() {
		return "PROV-" + getNextSupplierId();
	}

}