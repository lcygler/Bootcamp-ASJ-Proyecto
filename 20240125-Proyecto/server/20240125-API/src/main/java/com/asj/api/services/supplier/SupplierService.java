package com.asj.api.services.supplier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asj.api.models.supplier.SupplierModel;
import com.asj.api.repositories.supplier.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
	SupplierRepository supplierRepository;

	public List<SupplierModel> getAllSuppliers() {
		return supplierRepository.findAll();
	}

	public Optional<SupplierModel> getSupplierById(Integer id) {
		return supplierRepository.findById(id);
	}

	public SupplierModel createSupplier(SupplierModel supplier) {
		supplier.setCreatedAt(LocalDateTime.now());
		supplier.setUpdatedAt(LocalDateTime.now());
		supplier.setIsDeleted(false);
		
		return supplierRepository.save(supplier);
	}

	public Optional<SupplierModel> updateSupplier(Integer id, SupplierModel supplier) {
		Optional<SupplierModel> optionalSupplier = Optional.empty();

		if (supplierRepository.existsById(id)) {
			supplier.setUpdatedAt(LocalDateTime.now());
			optionalSupplier = Optional.of(supplierRepository.save(supplier));
		}

		return optionalSupplier;
	}

	public Optional<SupplierModel> patchSupplier(Integer id, SupplierModel supplier) {
		Optional<SupplierModel> optionalSupplier = getSupplierById(id);

		if (optionalSupplier.isPresent()) {
			SupplierModel existingSupplier = optionalSupplier.get();

			if (supplier.getCode() != null) {
				existingSupplier.setCode(supplier.getCode());
			}

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

			// Utils.updateObject(existingSupplier, supplier);
			existingSupplier.setUpdatedAt(LocalDateTime.now());

			return Optional.of(supplierRepository.save(existingSupplier));
		} else {
			return Optional.empty();
		}
	}

	public Optional<SupplierModel> deleteSupplier(Integer id) {
		Optional<SupplierModel> optionalSupplier = getSupplierById(id);

		if (optionalSupplier.isPresent()) {
			supplierRepository.deleteById(id);
		}

		return optionalSupplier;
	}

}