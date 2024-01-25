package com.asj.api.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asj.api.models.user.GenreModel;

public interface GenreRepository extends JpaRepository<GenreModel, Integer>{

}
