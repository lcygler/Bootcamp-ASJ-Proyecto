package com.asj.api.services.supplier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asj.api.models.supplier.ContactDetailModel;
import com.asj.api.repositories.supplier.ContactDetailRepository;

@Service
public class ContactDetailService {

	@Autowired
	ContactDetailRepository contactDetailRepository;

	public List<ContactDetailModel> getAllContactDetails() {
		return contactDetailRepository.findAll();
	}

	public Optional<ContactDetailModel> getContactDetailById(Integer id) {
		return contactDetailRepository.findById(id);
	}

	public ContactDetailModel createContactDetail(ContactDetailModel contactDetail) {
		LocalDateTime currentTimestamp = LocalDateTime.now();
		contactDetail.setCreatedAt(currentTimestamp);
		contactDetail.setUpdatedAt(currentTimestamp);

		return contactDetailRepository.save(contactDetail);
	}

	public Optional<ContactDetailModel> updateContactDetail(Integer id, ContactDetailModel contactDetail) {
		Optional<ContactDetailModel> optionalContactDetail = Optional.empty();

		if (contactDetailRepository.existsById(id)) {
			contactDetail.setId(id);
			contactDetail.setUpdatedAt(LocalDateTime.now());
			optionalContactDetail = Optional.of(contactDetailRepository.save(contactDetail));
		}

		return optionalContactDetail;
	}

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

			existingContactDetail.setUpdatedAt(LocalDateTime.now());

			return Optional.of(contactDetailRepository.save(existingContactDetail));
		} else {
			return Optional.empty();
		}
	}

	public Optional<ContactDetailModel> deleteContactDetail(Integer id) {
		Optional<ContactDetailModel> optionalContactDetail = getContactDetailById(id);

		if (optionalContactDetail.isPresent()) {
			contactDetailRepository.deleteById(id);
		}

		return optionalContactDetail;
	}

}