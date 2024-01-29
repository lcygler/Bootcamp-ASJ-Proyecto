package com.asj.api.services.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asj.api.models.user.UserModel;
import com.asj.api.repositories.user.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<UserModel> getAllUsers() {
		return userRepository.findAll();
	}

	public Optional<UserModel> getUserById(Integer id) {
		return userRepository.findById(id);
	}

	public UserModel createUser(UserModel user) {
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());

		return userRepository.save(user);
	}

	public Optional<UserModel> updateUser(Integer id, UserModel user) {
		Optional<UserModel> optionalUser = Optional.empty();

		if (userRepository.existsById(id)) {
			user.setUpdatedAt(LocalDateTime.now());
			optionalUser = Optional.of(userRepository.save(user));
		}

		return optionalUser;
	}

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
				existingUser.setDni(user.getDni());
			}
			
			if (user.getEmail() != null) {
				existingUser.setEmail(user.getEmail());
			}
			
			if (user.getPhone() != null) {
				existingUser.setPhone(user.getPhone());
			}
			
			if (user.getGenre() != null) {
				existingUser.setGenre(user.getGenre());
			}
			
			if (user.getAddress() != null) {
				existingUser.setAddress(user.getAddress());
			}
			
			if (user.getRole() != null) {
				existingUser.setRole(user.getRole());
			}

			if (user.getIsDeleted() != null) {
				existingUser.setIsDeleted(user.getIsDeleted());
			}
			
			existingUser.setUpdatedAt(LocalDateTime.now());

			return Optional.of(userRepository.save(existingUser));
		} else {
			return Optional.empty();
		}
	}
	
	public Optional<UserModel> deleteUser(Integer id) {
		Optional<UserModel> optionalUser = getUserById(id);

		if (optionalUser.isPresent()) {
			userRepository.deleteById(id);
		}

		return optionalUser;
	}

}