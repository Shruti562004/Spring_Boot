package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.RoleDAO;
import com.rays.dto.RoleDTO;

@Service
@Transactional
public class RoleService {

	@Autowired
	RoleDAO dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public Long add(RoleDTO dto) {
		Long id = dao.add(dto);
		return id;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(RoleDTO dto) {
		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Long save(RoleDTO dto) {

		Long id = dto.getId();

		if (id != null && id > 0) {

			dao.update(dto);

		} else {

			id = dao.add(dto);
		}

		return id;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Long pk) {

		dao.delete(findById(pk));

	}

	@Transactional(readOnly = true)
	public RoleDTO findById(Long id) {

		return dao.findByPk(id);

	}

}