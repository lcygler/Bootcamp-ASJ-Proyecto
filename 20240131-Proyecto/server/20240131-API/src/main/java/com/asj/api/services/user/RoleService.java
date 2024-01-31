package com.asj.api.services.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.models.user.RoleModel;
import com.asj.api.repositories.user.RoleRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
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
		role.setCreatedAt(LocalDateTime.now());
		role.setUpdatedAt(LocalDateTime.now());

		RoleModel createdRole = roleRepository.save(role);
		entityManager.refresh(createdRole);

		return createdRole;
	}

	@Transactional
	public Optional<RoleModel> updateRole(Integer id, RoleModel role) {
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
		Optional<RoleModel> optionalRole = getRoleById(id);

		if (optionalRole.isPresent()) {
			roleRepository.deleteById(id);
		}

		return optionalRole;
	}

}