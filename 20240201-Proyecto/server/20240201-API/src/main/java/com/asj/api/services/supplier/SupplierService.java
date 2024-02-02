package com.asj.api.services.supplier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesExistException;
import com.asj.api.exceptions.InvalidIdentifierException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.supplier.SupplierModel;
import com.asj.api.repositories.order.OrderRepository;
import com.asj.api.repositories.product.ProductRepository;
import com.asj.api.repositories.supplier.SupplierRepository;
import com.asj.api.services.address.AddressService;
import com.asj.api.services.common.ImageService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class SupplierService {

	@Autowired
	SupplierRepository supplierRepository;

	@Autowired
	IndustryService industryService;

	@Autowired
	ImageService imageService;

	@Autowired
	AddressService addressService;

	@Autowired
	TaxInformationService taxInformationService;

	@Autowired
	ContactDetailService contactDetailService;

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderRepository orderRepository;

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
		if (!isCodeUnique(supplier.getCode())) {
			throw new UniqueViolationException("Supplier code must be unique");
		}

		validateIndustry(supplier.getIndustry().getId());
		validateAddress(supplier.getAddress().getId());
		validateTaxInformation(supplier.getTaxInformation().getId());
		validateContactDetails(supplier.getContactDetails().getId());

		if (supplier.getImage() != null) {
			validateImage(supplier.getImage().getId());
		}

		supplier.setCreatedAt(LocalDateTime.now());
		supplier.setUpdatedAt(LocalDateTime.now());
		supplier.setIsDeleted(false);

		SupplierModel createdSupplier = supplierRepository.save(supplier);
		entityManager.refresh(createdSupplier);

		return createdSupplier;
	}

	@Transactional
	public Optional<SupplierModel> updateSupplier(Integer id, SupplierModel supplier) {
		if (!isCodeUniqueAndIdNot(supplier.getCode(), id)) {
			throw new UniqueViolationException("Supplier code must be unique");
		}

		validateIndustry(supplier.getIndustry().getId());
		validateAddress(supplier.getAddress().getId());
		validateTaxInformation(supplier.getTaxInformation().getId());
		validateContactDetails(supplier.getContactDetails().getId());

		if (supplier.getImage() != null) {
			validateImage(supplier.getImage().getId());
		}

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

			if (supplier.getCode() != null) {
				if (!isCodeUniqueAndIdNot(supplier.getCode(), id)) {
					throw new UniqueViolationException("Supplier code must be unique");
				}
				existingSupplier.setCode(supplier.getCode());
			}

			if (supplier.getBusinessName() != null) {
				existingSupplier.setBusinessName(supplier.getBusinessName());
			}

			if (supplier.getIndustry() != null) {
				validateIndustry(supplier.getIndustry().getId());
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
				validateAddress(supplier.getAddress().getId());
				existingSupplier.setAddress(supplier.getAddress());
			}

			if (supplier.getTaxInformation() != null) {
				validateTaxInformation(supplier.getTaxInformation().getId());
				existingSupplier.setTaxInformation(supplier.getTaxInformation());
			}

			if (supplier.getContactDetails() != null) {
				validateContactDetails(supplier.getContactDetails().getId());
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
		if (productRepository.countBySupplier(id) > 0 || orderRepository.countBySupplier(id) > 0) {
			throw new AssociatedEntitiesExistException("Deletion failed: Associated entities found");
		}

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

	private boolean isCodeUnique(String code) {
		return !supplierRepository.existsByCode(code);
	}

	private boolean isCodeUniqueAndIdNot(String code, Integer id) {
		return !supplierRepository.existsByCodeAndIdNot(code, id);
	}

	public boolean isIdValid(Integer id) {
		return supplierRepository.existsById(id);
	}

	private void validateIndustry(Integer id) {
		if (!industryService.isIdValid(id)) {
			throw new InvalidIdentifierException("Industry is not valid");
		}
	}

	private void validateImage(Integer id) {
		if (!imageService.isIdValid(id)) {
			throw new InvalidIdentifierException("Image is not valid");
		}
	}

	private void validateAddress(Integer id) {
		if (!addressService.isIdValid(id)) {
			throw new InvalidIdentifierException("Address is not valid");
		}
	}

	private void validateTaxInformation(Integer id) {
		if (!taxInformationService.isIdValid(id)) {
			throw new InvalidIdentifierException("Tax information is not valid");
		}
	}

	private void validateContactDetails(Integer id) {
		if (!contactDetailService.isIdValid(id)) {
			throw new InvalidIdentifierException("Contact detail is not valid");
		}
	}

}