package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.DonationDAO;
import com.rays.dto.DonationDTO;


public class DonationService {
	@Autowired
	DonationDAO dao;
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Long add(DonationDTO dto) {
		Long id=dao.add(dto);
		return id;
	}
	
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(DonationDTO dto) {
		dao.update(dto);
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public Long save(DonationDTO dto) {

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
	public DonationDTO findById(Long id) {

		return dao.findByPk(id);

	}
	
	
	@Transactional(readOnly = true)
	public List<DonationDTO> search(DonationDTO dto, int pageNo, int pageSize) {
		return dao.search(dto, pageNo, pageSize);
	}
	

}
