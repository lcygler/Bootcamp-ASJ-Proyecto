package com.asj.api.services.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.InvalidIdentifierException;
import com.asj.api.models.user.UserCredentialModel;
import com.asj.api.repositories.user.UserCredentialRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class UserCredentialService {

	@Autowired
	UserCredentialRepository userCredentialRepository;
	
	@Autowired
	UserService userService;
	
	@PersistenceContext
    private EntityManager entityManager;

	public List<UserCredentialModel> getAllUserCredentials() {
		return userCredentialRepository.findAll();
	}

	public Optional<UserCredentialModel> getUserCredentialById(Integer id) {
		return userCredentialRepository.findById(id);
	}

	@Transactional
	public UserCredentialModel createUserCredential(UserCredentialModel userCredential) {
		validateUser(userCredential.getUser().getId());
		
		userCredential.setCreatedAt(LocalDateTime.now());
		userCredential.setUpdatedAt(LocalDateTime.now());

		UserCredentialModel createdUserCredential = userCredentialRepository.save(userCredential);
		entityManager.refresh(createdUserCredential);

		return createdUserCredential;
	}

	@Transactional
	public Optional<UserCredentialModel> updateUserCredential(Integer id, UserCredentialModel userCredential) {
		validateUser(userCredential.getUser().getId());
		
		Optional<UserCredentialModel> optionalUserCredential = Optional.empty();

		if (userCredentialRepository.existsById(id)) {
			userCredential.setUpdatedAt(LocalDateTime.now());
			
			UserCredentialModel updatedUserCredential = userCredentialRepository.save(userCredential);
			entityManager.refresh(updatedUserCredential);

			optionalUserCredential = Optional.of(updatedUserCredential);
		}

		return optionalUserCredential;
	}

	@Transactional
	public Optional<UserCredentialModel> patchUserCredential(Integer id, UserCredentialModel userCredential) {
		Optional<UserCredentialModel> optionalUserCredential = getUserCredentialById(id);

		if (optionalUserCredential.isPresent()) {
			UserCredentialModel existingUserCredential = optionalUserCredential.get();

			if (userCredential.getUser() != null) {
				validateUser(userCredential.getUser().getId());
				existingUserCredential.setUser(userCredential.getUser());
			}

			if (userCredential.getPassword() != null) {
				existingUserCredential.setPassword(userCredential.getPassword());
			}
			
			if (userCredential.getIsDeleted() != null) {
				existingUserCredential.setIsDeleted(userCredential.getIsDeleted());
			}

			existingUserCredential.setUpdatedAt(LocalDateTime.now());

			userCredentialRepository.save(existingUserCredential);
			entityManager.flush();
			entityManager.refresh(existingUserCredential);

			return Optional.of(existingUserCredential);
		} else {
			return Optional.empty();
		}
	}

	@Transactional
	public Optional<UserCredentialModel> deleteUserCredential(Integer id) {
		Optional<UserCredentialModel> optionalUserCredential = getUserCredentialById(id);

		if (optionalUserCredential.isPresent()) {
			userCredentialRepository.deleteById(id);
		}

		return optionalUserCredential;
	}
	
	private void validateUser(Integer id) {
		if (!userService.isIdValid(id)) {
			throw new InvalidIdentifierException("User is not valid");
		}
	}

}