package com.asj.api.services.supplier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asj.api.models.supplier.IndustryModel;
import com.asj.api.repositories.supplier.IndustryRepository;

@Service
public class IndustryService {

	@Autowired
	IndustryRepository industryRepository;

	public List<IndustryModel> getAllIndustries() {
		return industryRepository.findAll();
	}

	public Optional<IndustryModel> getIndustryById(Integer id) {
		return industryRepository.findById(id);
	}

	public IndustryModel createIndustry(IndustryModel industry) {
		industry.setCreatedAt(LocalDateTime.now());
		industry.setUpdatedAt(LocalDateTime.now());

		return industryRepository.save(industry);
	}

	public Optional<IndustryModel> updateIndustry(Integer id, IndustryModel industry) {
		Optional<IndustryModel> optionalIndustry = Optional.empty();

		if (industryRepository.existsById(id)) {
			industry.setUpdatedAt(LocalDateTime.now());
			optionalIndustry = Optional.of(industryRepository.save(industry));
		}

		return optionalIndustry;
	}

	public Optional<IndustryModel> patchIndustry(Integer id, IndustryModel industry) {
		Optional<IndustryModel> optionalIndustry = getIndustryById(id);

		if (optionalIndustry.isPresent()) {
			IndustryModel existingIndustry = optionalIndustry.get();

			if (industry.getName() != null) {
				existingIndustry.setName(industry.getName());
			}
			
			existingIndustry.setUpdatedAt(LocalDateTime.now());

			return Optional.of(industryRepository.save(existingIndustry));
		} else {
			return Optional.empty();
		}
	}

	public Optional<IndustryModel> deleteIndustry(Integer id) {
		Optional<IndustryModel> optionalIndustry = getIndustryById(id);

		if (optionalIndustry.isPresent()) {
			industryRepository.deleteById(id);
		}

		return optionalIndustry;
	}

}