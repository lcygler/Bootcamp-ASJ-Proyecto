package com.asj.api.services.address;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asj.api.models.address.AddressModel;
import com.asj.api.repositories.address.AddressRepository;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;

	public List<AddressModel> getAllAddresses() {
		return addressRepository.findAll();
	}

	public Optional<AddressModel> getAddressById(Integer id) {
		return addressRepository.findById(id);
	}

	public AddressModel createAddress(AddressModel address) {
		LocalDateTime currentTimestamp = LocalDateTime.now();
		address.setCreatedAt(currentTimestamp);
		address.setUpdatedAt(currentTimestamp);

		return addressRepository.save(address);
	}

	public Optional<AddressModel> updateAddress(Integer id, AddressModel address) {
		Optional<AddressModel> optionalAddress = Optional.empty();

		if (addressRepository.existsById(id)) {
			address.setId(id);
			address.setUpdatedAt(LocalDateTime.now());
			optionalAddress = Optional.of(addressRepository.save(address));
		}

		return optionalAddress;
	}

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

			return Optional.of(addressRepository.save(existingAddress));
		} else {
			return Optional.empty();
		}
	}

	public Optional<AddressModel> deleteAddress(Integer id) {
		Optional<AddressModel> optionalAddress = getAddressById(id);

		if (optionalAddress.isPresent()) {
			addressRepository.deleteById(id);
		}

		return optionalAddress;
	}

}