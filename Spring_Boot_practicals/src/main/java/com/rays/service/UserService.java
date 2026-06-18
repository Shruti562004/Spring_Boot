package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.UserDAO;
import com.rays.dto.UserDTO;

@Service
@Transactional
public class UserService {

	@Autowired
	UserDAO dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public Long add(UserDTO dto) {
		
		UserDTO existDto = dao.findByUniqueKey("loginId", dto.getLoginId());

		if (existDto != null) {
			throw new RuntimeException("login Id already exist");
		}

		long pk = dao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(UserDTO dto) {
		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Long save(UserDTO dto) {

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
	public UserDTO findById(Long id) {

		return dao.findByPk(id);

	}
	
	@Transactional(readOnly = true)
	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {
		return dao.search(dto, pageNo, pageSize);
	}
	
	
	@Transactional(readOnly = true)
	public UserDTO authenticate(String login, String password) {

		UserDTO dto = dao.findByUniqueKey("loginId", login);

		if (dto != null) {
			if (dto.getPassword().equals(password)) {
				return dto;
			}
		}

		return null;

	}

}