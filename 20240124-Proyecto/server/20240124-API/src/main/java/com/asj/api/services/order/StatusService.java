package com.asj.api.services.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asj.api.models.order.StatusModel;
import com.asj.api.repositories.order.StatusRepository;

@Service
public class StatusService {

	@Autowired
	StatusRepository statusRepository;

	public List<StatusModel> getAllStatuses() {
		return statusRepository.findAll();
	}

	public Optional<StatusModel> getStatusById(Integer id) {
		return statusRepository.findById(id);
	}

	public StatusModel createStatus(StatusModel status) {
		LocalDateTime currentTimestamp = LocalDateTime.now();
		status.setCreatedAt(currentTimestamp);
		status.setUpdatedAt(currentTimestamp);

		return statusRepository.save(status);
	}

	public Optional<StatusModel> updateStatus(Integer id, StatusModel status) {
		Optional<StatusModel> optionalStatus = Optional.empty();

		if (statusRepository.existsById(id)) {
			status.setId(id);
			status.setUpdatedAt(LocalDateTime.now());
			optionalStatus = Optional.of(statusRepository.save(status));
		}

		return optionalStatus;
	}

	public Optional<StatusModel> patchStatus(Integer id, StatusModel status) {
		Optional<StatusModel> optionalStatus = getStatusById(id);

		if (optionalStatus.isPresent()) {
			StatusModel existingStatus = optionalStatus.get();

			if (status.getName() != null) {
				existingStatus.setName(status.getName());
			}
			
			existingStatus.setUpdatedAt(LocalDateTime.now());

			return Optional.of(statusRepository.save(existingStatus));
		} else {
			return Optional.empty();
		}
	}

	public Optional<StatusModel> deleteStatus(Integer id) {
		Optional<StatusModel> optionalStatus = getStatusById(id);

		if (optionalStatus.isPresent()) {
			statusRepository.deleteById(id);
		}

		return optionalStatus;
	}

}