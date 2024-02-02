package com.asj.api.services.supplier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesException;
import com.asj.api.models.supplier.ContactDetailModel;
import com.asj.api.repositories.supplier.ContactDetailRepository;
import com.asj.api.repositories.supplier.SupplierRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ContactDetailService {

	@Autowired
	ContactDetailRepository contactDetailRepository;
	
	@Autowired
	SupplierRepository supplierRepository;
	
	@PersistenceContext
    private EntityManager entityManager;

	public List<ContactDetailModel> getAllContactDetails() {
		return contactDetailRepository.findAll();
	}

	public Optional<ContactDetailModel> getContactDetailById(Integer id) {
		return contactDetailRepository.findById(id);
	}

	@Transactional
	public ContactDetailModel createContactDetail(ContactDetailModel contactDetail) {
		contactDetail.setCreatedAt(LocalDateTime.now());
		contactDetail.setUpdatedAt(LocalDateTime.now());

		ContactDetailModel createdContactDetail = contactDetailRepository.save(contactDetail);
		entityManager.refresh(createdContactDetail);

		return createdContactDetail;
	}

	@Transactional
	public Optional<ContactDetailModel> updateContactDetail(Integer id, ContactDetailModel contactDetail) {
		Optional<ContactDetailModel> optionalContactDetail = Optional.empty();

		if (contactDetailRepository.existsById(id)) {
			contactDetail.setUpdatedAt(LocalDateTime.now());
			
			ContactDetailModel updatedContactDetail = contactDetailRepository.save(contactDetail);
			entityManager.refresh(updatedContactDetail);

			optionalContactDetail = Optional.of(updatedContactDetail);
		}

		return optionalContactDetail;
	}

	@Transactional
	public Optional<ContactDetailModel> patchContactDetail(Integer id, ContactDetailModel contactDetail) {
		Optional<ContactDetailModel> optionalContactDetail = getContactDetailById(id);

		if (optionalContactDetail.isPresent()) {
			ContactDetailModel existingContactDetail = optionalContactDetail.get();

			if (contactDetail.getFirstName() != null) {
				existingContactDetail.setFirstName(contactDetail.getFirstName());
			}

			if (contactDetail.getLastName() != null) {
				existingContactDetail.setLastName(contactDetail.getLastName());
			}

			if (contactDetail.getPhone() != null) {
				existingContactDetail.setPhone(contactDetail.getPhone());
			}

			if (contactDetail.getEmail() != null) {
				existingContactDetail.setEmail(contactDetail.getEmail());
			}

			if (contactDetail.getRole() != null) {
				existingContactDetail.setRole(contactDetail.getRole());
			}
			
			if (contactDetail.getIsDeleted() != null) {
				existingContactDetail.setIsDeleted(contactDetail.getIsDeleted());
			}

			existingContactDetail.setUpdatedAt(LocalDateTime.now());

			contactDetailRepository.save(existingContactDetail);
			entityManager.flush();
			entityManager.refresh(existingContactDetail);

			return Optional.of(existingContactDetail);
		} else {
			return Optional.empty();
		}
	}

	@Transactional
	public Optional<ContactDetailModel> deleteContactDetail(Integer id) {
		if (supplierRepository.countByContactDetail(id) > 0) {
			throw new AssociatedEntitiesException("Deletion failed: Associated entities found");
		}
		
		Optional<ContactDetailModel> optionalContactDetail = getContactDetailById(id);

		if (optionalContactDetail.isPresent()) {
			contactDetailRepository.deleteById(id);
		}

		return optionalContactDetail;
	}
	
	public boolean isIdValid(Integer id) {
		return contactDetailRepository.existsById(id);
	}

}
