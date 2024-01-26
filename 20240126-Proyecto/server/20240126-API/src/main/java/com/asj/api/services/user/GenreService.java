package com.asj.api.services.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asj.api.models.user.GenreModel;
import com.asj.api.repositories.user.GenreRepository;

@Service
public class GenreService {

	@Autowired
	GenreRepository genreRepository;

	public List<GenreModel> getAllGenres() {
		return genreRepository.findAll();
	}

	public Optional<GenreModel> getGenreById(Integer id) {
		return genreRepository.findById(id);
	}

	public GenreModel createGenre(GenreModel genre) {
		genre.setCreatedAt(LocalDateTime.now());
		genre.setUpdatedAt(LocalDateTime.now());

		return genreRepository.save(genre);
	}

	public Optional<GenreModel> updateGenre(Integer id, GenreModel genre) {
		Optional<GenreModel> optionalGenre = Optional.empty();

		if (genreRepository.existsById(id)) {
			genre.setUpdatedAt(LocalDateTime.now());
			optionalGenre = Optional.of(genreRepository.save(genre));
		}

		return optionalGenre;
	}

	public Optional<GenreModel> patchGenre(Integer id, GenreModel genre) {
		Optional<GenreModel> optionalGenre = getGenreById(id);

		if (optionalGenre.isPresent()) {
			GenreModel existingGenre = optionalGenre.get();

			if (genre.getName() != null) {
				existingGenre.setName(genre.getName());
			}
			
			existingGenre.setUpdatedAt(LocalDateTime.now());

			return Optional.of(genreRepository.save(existingGenre));
		} else {
			return Optional.empty();
		}
	}

	public Optional<GenreModel> deleteGenre(Integer id) {
		Optional<GenreModel> optionalGenre = getGenreById(id);

		if (optionalGenre.isPresent()) {
			genreRepository.deleteById(id);
		}

		return optionalGenre;
	}

}