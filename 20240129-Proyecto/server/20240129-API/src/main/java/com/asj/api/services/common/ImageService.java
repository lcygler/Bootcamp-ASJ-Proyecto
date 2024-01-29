package com.asj.api.services.common;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.models.common.ImageModel;
import com.asj.api.repositories.common.ImageRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ImageService {

	@Autowired
	ImageRepository imageRepository;
	
	@PersistenceContext
    private EntityManager entityManager;

	public List<ImageModel> getAllImages() {
		return imageRepository.findAll();
	}

	public Optional<ImageModel> getImageById(Integer id) {
		return imageRepository.findById(id);
	}

	@Transactional
	public ImageModel createImage(ImageModel image) {
		image.setCreatedAt(LocalDateTime.now());
		image.setUpdatedAt(LocalDateTime.now());

		ImageModel createdImage = imageRepository.save(image);
		entityManager.refresh(createdImage);

		return createdImage;

	}

	@Transactional
	public Optional<ImageModel> updateImage(Integer id, ImageModel image) {
		Optional<ImageModel> optionalImage = Optional.empty();

		if (imageRepository.existsById(id)) {
			image.setUpdatedAt(LocalDateTime.now());
			
			ImageModel updatedImage = imageRepository.save(image);
			entityManager.refresh(updatedImage);

			optionalImage = Optional.of(updatedImage);

		}

		return optionalImage;
	}

	@Transactional
	public Optional<ImageModel> patchImage(Integer id, ImageModel image) {
		Optional<ImageModel> optionalImage = getImageById(id);

		if (optionalImage.isPresent()) {
			ImageModel existingImage = optionalImage.get();

			if (image.getUrl() != null) {
				existingImage.setUrl(image.getUrl());
			}

			existingImage.setUpdatedAt(LocalDateTime.now());

			imageRepository.save(existingImage);
			entityManager.flush();
			entityManager.refresh(existingImage);

			return Optional.of(existingImage);

		} else {
			return Optional.empty();
		}
	}

	@Transactional
	public Optional<ImageModel> deleteImage(Integer id) {
		Optional<ImageModel> optionalImage = getImageById(id);

		if (optionalImage.isPresent()) {
			imageRepository.deleteById(id);
		}

		return optionalImage;
	}

}