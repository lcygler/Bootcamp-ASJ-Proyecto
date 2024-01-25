package com.asj.api.services.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asj.api.models.user.UserCredentialModel;
import com.asj.api.repositories.user.UserCredentialRepository;

@Service
public class UserCredentialService {

	@Autowired
	UserCredentialRepository userCredentialRepository;

	public List<UserCredentialModel> getAllUserCredentials() {
		return userCredentialRepository.findAll();
	}

	public Optional<UserCredentialModel> getUserCredentialById(Integer id) {
		return userCredentialRepository.findById(id);
	}

	public UserCredentialModel createUserCredential(UserCredentialModel userCredential) {
		LocalDateTime currentTimestamp = LocalDateTime.now();
		userCredential.setCreatedAt(currentTimestamp);
		userCredential.setUpdatedAt(currentTimestamp);

		return userCredentialRepository.save(userCredential);
	}

	public Optional<UserCredentialModel> updateUserCredential(Integer id, UserCredentialModel userCredential) {
		Optional<UserCredentialModel> optionalUserCredential = Optional.empty();

		if (userCredentialRepository.existsById(id)) {
			userCredential.setId(id);
			userCredential.setUpdatedAt(LocalDateTime.now());
			optionalUserCredential = Optional.of(userCredentialRepository.save(userCredential));
		}

		return optionalUserCredential;
	}

	public Optional<UserCredentialModel> patchUserCredential(Integer id, UserCredentialModel userCredential) {
		Optional<UserCredentialModel> optionalUserCredential = getUserCredentialById(id);

		if (optionalUserCredential.isPresent()) {
			UserCredentialModel existingUserCredential = optionalUserCredential.get();

			if (userCredential.getUser() != null) {
				existingUserCredential.setUser(userCredential.getUser());
			}

			if (userCredential.getPassword() != null) {
				existingUserCredential.setPassword(userCredential.getPassword());
			}

			existingUserCredential.setUpdatedAt(LocalDateTime.now());

			return Optional.of(userCredentialRepository.save(existingUserCredential));
		} else {
			return Optional.empty();
		}
	}

	public Optional<UserCredentialModel> deleteUserCredential(Integer id) {
		Optional<UserCredentialModel> optionalUserCredential = getUserCredentialById(id);

		if (optionalUserCredential.isPresent()) {
			userCredentialRepository.deleteById(id);
		}

		return optionalUserCredential;
	}

}