package com.asj.api.services.address;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.models.address.AddressModel;
import com.asj.api.repositories.address.AddressRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;

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
		address.setCreatedAt(LocalDateTime.now());
		address.setUpdatedAt(LocalDateTime.now());

		AddressModel createdAddress = addressRepository.save(address);
		entityManager.refresh(createdAddress);

		return createdAddress;
	}

	@Transactional
	public Optional<AddressModel> updateAddress(Integer id, AddressModel address) {
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

			if (address.getCity() != null) {
				existingAddress.setCity(address.getCity());
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
		Optional<AddressModel> optionalAddress = getAddressById(id);

		if (optionalAddress.isPresent()) {
			addressRepository.deleteById(id);
		}

		return optionalAddress;
	}

}