package com.asj.api.services.address;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesException;
import com.asj.api.exceptions.InvalidIdentifierException;
import com.asj.api.models.address.AddressModel;
import com.asj.api.repositories.address.AddressRepository;
import com.asj.api.repositories.user.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	StateService stateService;
	
	@Autowired
	UserRepository userRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<AddressModel> getAllAddresses() {
		return addressRepository.findAll();
	}

	public Optional<AddressModel> getAddressById(Integer id) {
		return addressRepository.findById(id);
	}

	@Transactional
	public AddressModel createAddress(AddressModel address) {
		validateState(address.getState().getId());
		
		address.setCreatedAt(LocalDateTime.now());
		address.setUpdatedAt(LocalDateTime.now());

		AddressModel createdAddress = addressRepository.save(address);
		entityManager.refresh(createdAddress);

		return createdAddress;
	}

	@Transactional
	public Optional<AddressModel> updateAddress(Integer id, AddressModel address) {
		validateState(address.getState().getId());
		
		Optional<AddressModel> optionalAddress = Optional.empty();

		if (addressRepository.existsById(id)) {
			address.setUpdatedAt(LocalDateTime.now());

			AddressModel updatedAddress = addressRepository.save(address);
			entityManager.refresh(updatedAddress);

			optionalAddress = Optional.of(updatedAddress);
		}

		return optionalAddress;
	}

	@Transactional
	public Optional<AddressModel> patchAddress(Integer id, AddressModel address) {
		Optional<AddressModel> optionalAddress = getAddressById(id);

		if (optionalAddress.isPresent()) {
			AddressModel existingAddress = optionalAddress.get();

			if (address.getStreet() != null) {
				existingAddress.setStreet(address.getStreet());
			}

			if (address.getNumber() != null) {
				existingAddress.setNumber(address.getNumber());
			}

			if (address.getPostalCode() != null) {
				existingAddress.setPostalCode(address.getPostalCode());
			}

			if (address.getCity() != null) {
				existingAddress.setCity(address.getCity());
			}

			if (address.getState() != null) {
				validateState(address.getState().getId());
				existingAddress.setState(address.getState());
			}
			
			if (address.getIsDeleted() != null) {
				existingAddress.setIsDeleted(address.getIsDeleted());
			}

			existingAddress.setUpdatedAt(LocalDateTime.now());

			addressRepository.save(existingAddress);
			entityManager.flush();
			entityManager.refresh(existingAddress);

			return Optional.of(existingAddress);
		} else {
			return Optional.empty();
		}
	}

	@Transactional
	public Optional<AddressModel> deleteAddress(Integer id) {
		if (userRepository.countByAddress(id) > 0) {
			throw new AssociatedEntitiesException("Deletion failed: Associated entities found");
		}
		
		Optional<AddressModel> optionalAddress = getAddressById(id);

		if (optionalAddress.isPresent()) {
			addressRepository.deleteById(id);
		}

		return optionalAddress;
	}
	
	public boolean isIdValid(Integer id) {
		return addressRepository.existsById(id);
	}
	
	private void validateState(Integer id) {
		if (!stateService.isIdValid(id)) {
			throw new InvalidIdentifierException("State is not valid");
		}
	}
	
}
