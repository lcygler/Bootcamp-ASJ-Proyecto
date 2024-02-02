package com.asj.api.services.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.order.StatusModel;
import com.asj.api.repositories.order.OrderRepository;
import com.asj.api.repositories.order.StatusRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class StatusService {

	@Autowired
	StatusRepository statusRepository;

	@Autowired
	OrderRepository orderRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<StatusModel> getAllStatuses() {
		return statusRepository.findAll();
	}

	public Optional<StatusModel> getStatusById(Integer id) {
		return statusRepository.findById(id);
	}

	@Transactional
	public StatusModel createStatus(StatusModel status) {
		if (!isNameUnique(status.getName())) {
			throw new UniqueViolationException("Name must be unique");
		}

		status.setCreatedAt(LocalDateTime.now());
		status.setUpdatedAt(LocalDateTime.now());

		StatusModel createdStatus = statusRepository.save(status);
		entityManager.refresh(createdStatus);

		return createdStatus;
	}

	@Transactional
	public Optional<StatusModel> updateStatus(Integer id, StatusModel status) {
		if (!isNameUniqueAndIdNot(status.getName(), id)) {
			throw new UniqueViolationException("Name must be unique");
		}

		Optional<StatusModel> optionalStatus = Optional.empty();

		if (statusRepository.existsById(id)) {
			status.setUpdatedAt(LocalDateTime.now());

			StatusModel updatedStatus = statusRepository.save(status);
			entityManager.refresh(updatedStatus);

			optionalStatus = Optional.of(updatedStatus);
		}

		return optionalStatus;
	}

	@Transactional
	public Optional<StatusModel> patchStatus(Integer id, StatusModel status) {
		Optional<StatusModel> optionalStatus = getStatusById(id);

		if (optionalStatus.isPresent()) {
			StatusModel existingStatus = optionalStatus.get();

			if (status.getName() != null) {
				if (!isNameUniqueAndIdNot(status.getName(), id)) {
					throw new UniqueViolationException("Name must be unique");
				}
				existingStatus.setName(status.getName());
			}
			
			if (status.getIsDeleted() != null) {
				existingStatus.setIsDeleted(status.getIsDeleted());
			}

			existingStatus.setUpdatedAt(LocalDateTime.now());

			statusRepository.save(existingStatus);
			entityManager.flush();
			entityManager.refresh(existingStatus);

			return Optional.of(existingStatus);
		} else {
			return Optional.empty();
		}
	}

	@Transactional
	public Optional<StatusModel> deleteStatus(Integer id) {
		if (orderRepository.countByStatus(id) > 0) {
			throw new AssociatedEntitiesException("Deletion failed: Associated entities found");
		}

		Optional<StatusModel> optionalStatus = getStatusById(id);

		if (optionalStatus.isPresent()) {
			statusRepository.deleteById(id);
		}

		return optionalStatus;
	}

	private boolean isNameUnique(String name) {
		return !statusRepository.existsByName(name);
	}

	private boolean isNameUniqueAndIdNot(String name, Integer id) {
		return !statusRepository.existsByNameAndIdNot(name, id);
	}

	public boolean isIdValid(Integer id) {
		return statusRepository.existsById(id);
	}

}
