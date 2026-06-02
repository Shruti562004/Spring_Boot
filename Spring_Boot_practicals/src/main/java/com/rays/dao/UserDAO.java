package com.rays.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.rays.dto.UserDTO;

@Repository
public class UserDAO {

	@PersistenceContext
	EntityManager entityManager;

	public Long add(UserDTO dto) {

		entityManager.persist(dto);

		return dto.getId();
	}

	public void update(UserDTO dto) {

		entityManager.merge(dto);

	}

	public void delete(UserDTO dto) {

		entityManager.remove(dto);

	}

	public UserDTO findByPk(Long pk) {

		UserDTO dto = entityManager.find(UserDTO.class, pk);

		return dto;
	}

}