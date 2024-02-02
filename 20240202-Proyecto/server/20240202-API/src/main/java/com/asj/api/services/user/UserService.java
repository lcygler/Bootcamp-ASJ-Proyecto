package com.asj.api.services.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesException;
import com.asj.api.exceptions.InvalidIdentifierException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.user.UserModel;
import com.asj.api.repositories.user.UserCredentialRepository;
import com.asj.api.repositories.user.UserRepository;
import com.asj.api.services.address.AddressService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	GenreService genreService;

	@Autowired
	AddressService addressService;

	@Autowired
	RoleService roleService;

	@Autowired
	UserCredentialRepository userCredentialRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<UserModel> getAllUsers() {
		return userRepository.findAll();
	}

	public Optional<UserModel> getUserById(Integer id) {
		return userRepository.findById(id);
	}

	@Transactional
	public UserModel createUser(UserModel user) {
		if (!isDniUnique(user.getDni())) {
			throw new UniqueViolationException("DNI must be unique");
		}

		if (!isEmailUnique(user.getEmail())) {
			throw new UniqueViolationException("Email must be unique");
		}

		validateGenre(user.getGenre().getId());
		validateAddress(user.getAddress().getId());
		validateRole(user.getRole().getId());

		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());

		UserModel createdUser = userRepository.save(user);
		entityManager.refresh(createdUser);

		return createdUser;
	}

	@Transactional
	public Optional<UserModel> updateUser(Integer id, UserModel user) {
		if (!isDniUniqueAndIdNot(user.getDni(), id)) {
			throw new UniqueViolationException("DNI must be unique");
		}

		if (!isEmailUniqueAndIdNot(user.getEmail(), id)) {
			throw new UniqueViolationException("Email must be unique");
		}

		validateGenre(user.getGenre().getId());
		validateAddress(user.getAddress().getId());
		validateRole(user.getRole().getId());

		Optional<UserModel> optionalUser = Optional.empty();

		if (userRepository.existsById(id)) {
			user.setUpdatedAt(LocalDateTime.now());

			UserModel updatedUser = userRepository.save(user);
			entityManager.refresh(updatedUser);

			optionalUser = Optional.of(updatedUser);
		}

		return optionalUser;
	}

	@Transactional
	public Optional<UserModel> patchUser(Integer id, UserModel user) {
		Optional<UserModel> optionalUser = getUserById(id);

		if (optionalUser.isPresent()) {
			UserModel existingUser = optionalUser.get();

			if (user.getFirstName() != null) {
				existingUser.setFirstName(user.getFirstName());
			}

			if (user.getLastName() != null) {
				existingUser.setLastName(user.getLastName());
			}

			if (user.getDni() != null) {
				if (!isDniUniqueAndIdNot(user.getDni(), id)) {
					throw new UniqueViolationException("DNI must be unique");
				}
				existingUser.setDni(user.getDni());
			}

			if (user.getEmail() != null) {
				if (!isEmailUniqueAndIdNot(user.getEmail(), id)) {
					throw new UniqueViolationException("Email must be unique");
				}
				existingUser.setEmail(user.getEmail());
			}

			if (user.getPhone() != null) {
				existingUser.setPhone(user.getPhone());
			}

			if (user.getGenre() != null) {
				validateGenre(user.getGenre().getId());
				existingUser.setGenre(user.getGenre());
			}

			if (user.getAddress() != null) {
				validateAddress(user.getAddress().getId());
				existingUser.setAddress(user.getAddress());
			}

			if (user.getRole() != null) {
				validateRole(user.getRole().getId());
				existingUser.setRole(user.getRole());
			}

			if (user.getIsDeleted() != null) {
				existingUser.setIsDeleted(user.getIsDeleted());
			}

			existingUser.setUpdatedAt(LocalDateTime.now());

			userRepository.save(existingUser);
			entityManager.flush();
			entityManager.refresh(existingUser);

			return Optional.of(existingUser);
		} else {
			return Optional.empty();
		}
	}

	@Transactional
	public Optional<UserModel> deleteUser(Integer id) {
		if (userCredentialRepository.countByUser(id) > 0) {
			throw new AssociatedEntitiesException("Deletion failed: Associated entities found");
		}

		Optional<UserModel> optionalUser = getUserById(id);

		if (optionalUser.isPresent()) {
			userRepository.deleteById(id);
		}

		return optionalUser;
	}

	private boolean isDniUnique(String dni) {
		return !userRepository.existsByDni(dni);
	}

	private boolean isDniUniqueAndIdNot(String dni, Integer id) {
		return !userRepository.existsByDniAndIdNot(dni, id);
	}

	private boolean isEmailUnique(String email) {
		return !userRepository.existsByEmail(email);
	}

	private boolean isEmailUniqueAndIdNot(String email, Integer id) {
		return !userRepository.existsByEmailAndIdNot(email, id);
	}

	public boolean isIdValid(Integer id) {
		return userRepository.existsById(id);
	}

	private void validateGenre(Integer id) {
		if (!genreService.isIdValid(id)) {
			throw new InvalidIdentifierException("Genre is not valid");
		}
	}

	private void validateAddress(Integer id) {
		if (!addressService.isIdValid(id)) {
			throw new InvalidIdentifierException("Address is not valid");
		}
	}

	private void validateRole(Integer id) {
		if (!roleService.isIdValid(id)) {
			throw new InvalidIdentifierException("Role is not valid");
		}
	}

}
