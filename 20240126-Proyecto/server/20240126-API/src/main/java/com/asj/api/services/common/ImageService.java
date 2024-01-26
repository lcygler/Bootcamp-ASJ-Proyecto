package com.asj.api.services.common;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asj.api.models.common.ImageModel;
import com.asj.api.repositories.common.ImageRepository;

@Service
public class ImageService {

	@Autowired
	ImageRepository imageRepository;

	public List<ImageModel> getAllImages() {
		return imageRepository.findAll();
	}

	public Optional<ImageModel> getImageById(Integer id) {
		return imageRepository.findById(id);
	}

	public ImageModel createImage(ImageModel image) {
		image.setCreatedAt(LocalDateTime.now());
		image.setUpdatedAt(LocalDateTime.now());

		return imageRepository.save(image);
	}

	public Optional<ImageModel> updateImage(Integer id, ImageModel image) {
		Optional<ImageModel> optionalImage = Optional.empty();

		if (imageRepository.existsById(id)) {
			image.setUpdatedAt(LocalDateTime.now());
			optionalImage = Optional.of(imageRepository.save(image));
		}

		return optionalImage;
	}

	public Optional<ImageModel> patchImage(Integer id, ImageModel image) {
		Optional<ImageModel> optionalImage = getImageById(id);

		if (optionalImage.isPresent()) {
			ImageModel existingImage = optionalImage.get();

			if (image.getUrl() != null) {
				existingImage.setUrl(image.getUrl());
			}

			existingImage.setUpdatedAt(LocalDateTime.now());

			return Optional.of(imageRepository.save(existingImage));
		} else {
			return Optional.empty();
		}
	}

	public Optional<ImageModel> deleteImage(Integer id) {
		Optional<ImageModel> optionalImage = getImageById(id);

		if (optionalImage.isPresent()) {
			imageRepository.deleteById(id);
		}

		return optionalImage;
	}

}