package com.asj.api.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asj.api.models.user.UserCredentialModel;

public interface UserCredentialRepository extends JpaRepository<UserCredentialModel, Integer>{

}
