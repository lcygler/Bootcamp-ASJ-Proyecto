package com.asj.api.services.user;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.EntityNotFoundException;
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

	public UserCredentialModel getUserCredentialById(Integer id) {
		return userCredentialRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User credential not found"));
	}

	@Transactional
	public UserCredentialModel createUserCredential(UserCredentialModel userCredential) {
		validateUser(userCredential.getUser().getId());

		userCredential.setCreatedAt(LocalDateTime.now());
		userCredential.setUpdatedAt(LocalDateTime.now());
		userCredential.setIsDeleted(false);

		UserCredentialModel createdUserCredential = userCredentialRepository.save(userCredential);
		entityManager.refresh(createdUserCredential);

		return createdUserCredential;
	}

	@Transactional
	public UserCredentialModel updateUserCredential(Integer id, UserCredentialModel userCredential) {
		if (!userCredentialRepository.existsById(id)) {
			throw new EntityNotFoundException("User credential not found");
		}

		validateUser(userCredential.getUser().getId());

		userCredential.setUpdatedAt(LocalDateTime.now());

		UserCredentialModel updatedUserCredential = userCredentialRepository.save(userCredential);
		entityManager.refresh(updatedUserCredential);

		return updatedUserCredential;
	}

	@Transactional
	public UserCredentialModel patchUserCredential(Integer id, UserCredentialModel userCredential) {
		UserCredentialModel existingUserCredential = getUserCredentialById(id);

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

		return existingUserCredential;
	}

	@Transactional
	public UserCredentialModel deleteUserCredential(Integer id) {
		UserCredentialModel deletedUserCredential = getUserCredentialById(id);
		userCredentialRepository.deleteById(id);

		return deletedUserCredential;
	}

	private void validateUser(Integer id) {
		if (!userService.isIdValid(id)) {
			throw new InvalidIdentifierException("User is not valid");
		}
	}

}
