package com.asj.api.services.address;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asj.api.models.address.StateModel;
import com.asj.api.repositories.address.StateRepository;

@Service
public class StateService {

	@Autowired
	StateRepository stateRepository;

	public List<StateModel> getAllStates() {
		return stateRepository.findAll();
	}

	public Optional<StateModel> getStateById(Integer id) {
		return stateRepository.findById(id);
	}

	public List<StateModel> getStatesByCountry(Integer countryId) {
		return stateRepository.findByCountryId(countryId);
	}
	
	public StateModel createState(StateModel state) {
		state.setCreatedAt(LocalDateTime.now());
		state.setUpdatedAt(LocalDateTime.now());

		return stateRepository.save(state);
	}

	public Optional<StateModel> updateState(Integer id, StateModel state) {
		Optional<StateModel> optionalState = Optional.empty();

		if (stateRepository.existsById(id)) {
			state.setUpdatedAt(LocalDateTime.now());
			optionalState = Optional.of(stateRepository.save(state));
		}

		return optionalState;
	}

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

			return Optional.of(stateRepository.save(existingState));
		} else {
			return Optional.empty();
		}
	}

	public Optional<StateModel> deleteState(Integer id) {
		Optional<StateModel> optionalState = getStateById(id);

		if (optionalState.isPresent()) {
			stateRepository.deleteById(id);
		}

		return optionalState;
	}

}