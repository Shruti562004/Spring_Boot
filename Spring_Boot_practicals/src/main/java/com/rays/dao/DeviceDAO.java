package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.dto.DeviceDTO;

@Repository
public class DeviceDAO {
	@PersistenceContext
	EntityManager entityManager;

	
	public Long add(DeviceDTO dto) {
		entityManager.persist(dto);
		return dto.getId();
	}
	
	public  void update(DeviceDTO dto) {
		entityManager.merge(dto);
		
		
	}
	
	public void delete(DeviceDTO dto) {
		entityManager.remove(dto);
	}
	
	
	public DeviceDTO findByPk(long pk) {
		DeviceDTO dto= entityManager.find(DeviceDTO.class, pk);
		return dto;
	}
	
	
	public List search(DeviceDTO dto,int pageNo,int pageSize) {
		
List list=new ArrayList();	

CriteriaBuilder builder=entityManager.getCriteriaBuilder();
CriteriaQuery<DeviceDTO> cq=builder.createQuery(DeviceDTO.class);
Root qRoot=cq.from(DeviceDTO.class);
List predicateList=new ArrayList();

if(dto!=null) {
	if(dto.getName()!=null && dto.getName().length()>0) {
		predicateList.add(builder.like(qRoot.get("name"), dto.getName()+"%"));
		
	}
	
	if(dto.getRoom()!=null&& dto.getRoom().length()>0) {
	predicateList.add(builder.like(qRoot.get("room"), dto.getRoom() +"%"));
	}
	

	if (dto.getUsage()!= 0) {
	    predicateList.add(
	        builder.equal(qRoot.get("usage"), dto.getUsage())
	    );
	}
	
}

	}
}
