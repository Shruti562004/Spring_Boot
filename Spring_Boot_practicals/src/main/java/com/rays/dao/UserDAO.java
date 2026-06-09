package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.dto.RoleDTO;
import com.rays.dto.UserDTO;



@Repository
public class UserDAO {

	@PersistenceContext
	EntityManager entityManager;
	
	
	@Autowired
	public RoleDAO roleDao;
	
	public void populate(UserDTO dto) {

		

		RoleDTO roleDto = roleDao.findByPk(dto.getRoleId());

		dto.setRoleName(roleDto.getName());

	}

	public Long add(UserDTO dto) {
		populate(dto);

		entityManager.persist(dto);

		return dto.getId();
		

	}

	public void update(UserDTO dto) {
		populate(dto);

		entityManager.merge(dto);
	

	}

	public void delete(UserDTO dto) {

		entityManager.remove(dto);
		

	}

	public UserDTO findByPk(Long pk) {

		UserDTO dto = entityManager.find(UserDTO.class, pk);

		return dto;
	}

	
	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {

		List<UserDTO> list = new ArrayList<>();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<UserDTO> cq = builder.createQuery(UserDTO.class);

		Root<UserDTO> qRoot = cq.from(UserDTO.class);

		List<Predicate> predicateList = new ArrayList<Predicate>();

		if (dto != null) {
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("firstName"), dto.getFirstName() + "%"));
			}
			if (dto.getLastName() != null && dto.getLastName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("lastName"), dto.getLastName() + "%"));
			}
		}

		cq.where(predicateList.toArray(new Predicate[predicateList.size()]));

		TypedQuery<UserDTO> typedQuery = entityManager.createQuery(cq);

		if (pageSize > 0) {
			typedQuery.setFirstResult(pageNo * pageSize);
			typedQuery.setMaxResults(pageSize);
		}

		list = typedQuery.getResultList();

		return list;

	}
	
	public UserDTO findByUniqueKey(String attribute, String value) {

		UserDTO dto = null;

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<UserDTO> cq = builder.createQuery(UserDTO.class);

		Root<UserDTO> qRoot = cq.from(UserDTO.class);

		Predicate condition = builder.equal(qRoot.get(attribute), value);

		cq.where(condition);

		TypedQuery<UserDTO> typedQuery = entityManager.createQuery(cq);

		List<UserDTO> list = typedQuery.getResultList();

		if (list.size() == 1) {
			dto = new UserDTO();
			dto = list.get(0);
		}

		return dto;

	}
	
	
	

}