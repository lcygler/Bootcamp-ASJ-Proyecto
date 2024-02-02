package com.asj.api.services.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesExistException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.user.GenreModel;
import com.asj.api.repositories.user.GenreRepository;
import com.asj.api.repositories.user.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class GenreService {

	@Autowired
	GenreRepository genreRepository;
	
	@Autowired
	UserRepository userRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<GenreModel> getAllGenres() {
		return genreRepository.findAll();
	}

	public Optional<GenreModel> getGenreById(Integer id) {
		return genreRepository.findById(id);
	}

	@Transactional
	public GenreModel createGenre(GenreModel genre) {
		if (!isNameUnique(genre.getName())) {
			throw new UniqueViolationException("Name must be unique");
		}

		genre.setCreatedAt(LocalDateTime.now());
		genre.setUpdatedAt(LocalDateTime.now());

		GenreModel createdGenre = genreRepository.save(genre);
		entityManager.refresh(createdGenre);

		return createdGenre;
	}

	@Transactional
	public Optional<GenreModel> updateGenre(Integer id, GenreModel genre) {
		if (!isNameUniqueAndIdNot(genre.getName(), id)) {
			throw new UniqueViolationException("Name must be unique");
		}

		Optional<GenreModel> optionalGenre = Optional.empty();

		if (genreRepository.existsById(id)) {
			genre.setUpdatedAt(LocalDateTime.now());

			GenreModel updatedGenre = genreRepository.save(genre);
			entityManager.refresh(updatedGenre);

			optionalGenre = Optional.of(updatedGenre);
		}

		return optionalGenre;
	}

	@Transactional
	public Optional<GenreModel> patchGenre(Integer id, GenreModel genre) {
		Optional<GenreModel> optionalGenre = getGenreById(id);

		if (optionalGenre.isPresent()) {
			GenreModel existingGenre = optionalGenre.get();

			if (genre.getName() != null) {
				if (!isNameUniqueAndIdNot(genre.getName(), id)) {
					throw new UniqueViolationException("Name must be unique");
				}
				existingGenre.setName(genre.getName());
			}

			existingGenre.setUpdatedAt(LocalDateTime.now());

			genreRepository.save(existingGenre);
			entityManager.flush();
			entityManager.refresh(existingGenre);

			return Optional.of(existingGenre);
		} else {
			return Optional.empty();
		}
	}

	@Transactional
	public Optional<GenreModel> deleteGenre(Integer id) {
		if (userRepository.countByGenre(id) > 0) {
			throw new AssociatedEntitiesExistException("Deletion failed: Associated entities found");
		}
		
		Optional<GenreModel> optionalGenre = getGenreById(id);

		if (optionalGenre.isPresent()) {
			genreRepository.deleteById(id);
		}

		return optionalGenre;
	}

	private boolean isNameUnique(String name) {
		return !genreRepository.existsByName(name);
	}

	private boolean isNameUniqueAndIdNot(String name, Integer id) {
		return !genreRepository.existsByNameAndIdNot(name, id);
	}
	
	public boolean isIdValid(Integer id) {
		return genreRepository.existsById(id);
	}

}
