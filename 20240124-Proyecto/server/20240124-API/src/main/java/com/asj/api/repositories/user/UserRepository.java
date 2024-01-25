package com.asj.api.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asj.api.models.user.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer>{

}
