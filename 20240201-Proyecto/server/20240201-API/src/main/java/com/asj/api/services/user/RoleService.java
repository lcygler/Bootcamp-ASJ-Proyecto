package com.asj.api.services.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesExistException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.user.RoleModel;
import com.asj.api.repositories.user.RoleRepository;
import com.asj.api.repositories.user.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<RoleModel> getAllRoles() {
		return roleRepository.findAll();
	}

	public Optional<RoleModel> getRoleById(Integer id) {
		return roleRepository.findById(id);
	}

	@Transactional
	public RoleModel createRole(RoleModel role) {
		if (!isNameUnique(role.getName())) {
			throw new UniqueViolationException("Name must be unique");
		}

		role.setCreatedAt(LocalDateTime.now());
		role.setUpdatedAt(LocalDateTime.now());

		RoleModel createdRole = roleRepository.save(role);
		entityManager.refresh(createdRole);

		return createdRole;
	}

	@Transactional
	public Optional<RoleModel> updateRole(Integer id, RoleModel role) {
		if (!isNameUniqueAndIdNot(role.getName(), id)) {
			throw new UniqueViolationException("Name must be unique");
		}

		Optional<RoleModel> optionalRole = Optional.empty();

		if (roleRepository.existsById(id)) {
			role.setUpdatedAt(LocalDateTime.now());

			RoleModel updatedRole = roleRepository.save(role);
			entityManager.refresh(updatedRole);

			optionalRole = Optional.of(updatedRole);
		}

		return optionalRole;
	}

	@Transactional
	public Optional<RoleModel> patchRole(Integer id, RoleModel role) {
		Optional<RoleModel> optionalRole = getRoleById(id);

		if (optionalRole.isPresent()) {
			RoleModel existingRole = optionalRole.get();

			if (role.getName() != null) {
				if (!isNameUniqueAndIdNot(role.getName(), id)) {
					throw new UniqueViolationException("Name must be unique");
				}
				existingRole.setName(role.getName());
			}

			existingRole.setUpdatedAt(LocalDateTime.now());

			roleRepository.save(existingRole);
			entityManager.flush();
			entityManager.refresh(existingRole);

			return Optional.of(existingRole);
		} else {
			return Optional.empty();
		}
	}

	@Transactional
	public Optional<RoleModel> deleteRole(Integer id) {
		if (userRepository.countByRole(id) > 0) {
			throw new AssociatedEntitiesExistException("Deletion failed: Associated entities found");
		}
		
		Optional<RoleModel> optionalRole = getRoleById(id);

		if (optionalRole.isPresent()) {
			roleRepository.deleteById(id);
		}

		return optionalRole;
	}

	private boolean isNameUnique(String name) {
		return !roleRepository.existsByName(name);
	}

	private boolean isNameUniqueAndIdNot(String name, Integer id) {
		return !roleRepository.existsByNameAndIdNot(name, id);
	}
	
	public boolean isIdValid(Integer id) {
		return roleRepository.existsById(id);
	}

}
