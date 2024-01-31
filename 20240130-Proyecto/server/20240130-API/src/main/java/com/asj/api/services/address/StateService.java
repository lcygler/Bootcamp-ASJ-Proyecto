package com.asj.api.services.address;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.models.address.StateModel;
import com.asj.api.repositories.address.StateRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class StateService {

	@Autowired
	StateRepository stateRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<StateModel> getAllStates() {
		return stateRepository.findAll();
	}

	public Optional<StateModel> getStateById(Integer id) {
		return stateRepository.findById(id);
	}

	public List<StateModel> getStatesByCountry(Integer countryId) {
		return stateRepository.findByCountryId(countryId);
	}

	@Transactional
	public StateModel createState(StateModel state) {
		state.setCreatedAt(LocalDateTime.now());
		state.setUpdatedAt(LocalDateTime.now());

		StateModel createdState = stateRepository.save(state);
		entityManager.refresh(createdState);

		return createdState;
	}

	@Transactional
	public Optional<StateModel> updateState(Integer id, StateModel state) {
		Optional<StateModel> optionalState = Optional.empty();

		if (stateRepository.existsById(id)) {
			state.setUpdatedAt(LocalDateTime.now());

			StateModel updatedState = stateRepository.save(state);
			entityManager.refresh(updatedState);

			optionalState = Optional.of(updatedState);
		}

		return optionalState;
	}

	@Transactional
	public Optional<StateModel> patchState(Integer id, StateModel state) {
		Optional<StateModel> optionalState = getStateById(id);

		if (optionalState.isPresent()) {
			StateModel existingState = optionalState.get();

			if (state.getName() != null) {
				existingState.setName(state.getName());
			}

			if (state.getCountry() != null) {
				existingState.setCountry(state.getCountry());
			}

			existingState.setUpdatedAt(LocalDateTime.now());

			stateRepository.save(existingState);
			entityManager.flush();
			entityManager.refresh(existingState);

			return Optional.of(existingState);
		} else {
			return Optional.empty();
		}
	}

	@Transactional
	public Optional<StateModel> deleteState(Integer id) {
		Optional<StateModel> optionalState = getStateById(id);

		if (optionalState.isPresent()) {
			stateRepository.deleteById(id);
		}

		return optionalState;
	}

}