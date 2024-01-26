package com.asj.api.services.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asj.api.models.user.RoleModel;
import com.asj.api.repositories.user.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	public List<RoleModel> getAllRoles() {
		return roleRepository.findAll();
	}

	public Optional<RoleModel> getRoleById(Integer id) {
		return roleRepository.findById(id);
	}

	public RoleModel createRole(RoleModel role) {
		role.setCreatedAt(LocalDateTime.now());
		role.setUpdatedAt(LocalDateTime.now());

		return roleRepository.save(role);
	}

	public Optional<RoleModel> updateRole(Integer id, RoleModel role) {
		Optional<RoleModel> optionalRole = Optional.empty();

		if (roleRepository.existsById(id)) {
			role.setUpdatedAt(LocalDateTime.now());
			optionalRole = Optional.of(roleRepository.save(role));
		}

		return optionalRole;
	}

	public Optional<RoleModel> patchRole(Integer id, RoleModel role) {
		Optional<RoleModel> optionalRole = getRoleById(id);

		if (optionalRole.isPresent()) {
			RoleModel existingRole = optionalRole.get();

			if (role.getName() != null) {
				existingRole.setName(role.getName());
			}
			
			existingRole.setUpdatedAt(LocalDateTime.now());

			return Optional.of(roleRepository.save(existingRole));
		} else {
			return Optional.empty();
		}
	}

	public Optional<RoleModel> deleteRole(Integer id) {
		Optional<RoleModel> optionalRole = getRoleById(id);

		if (optionalRole.isPresent()) {
			roleRepository.deleteById(id);
		}

		return optionalRole;
	}

}