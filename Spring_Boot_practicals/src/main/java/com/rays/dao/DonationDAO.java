package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.dto.DonationDTO;


@Repository
public class DonationDAO {

	EntityManager entityManager;
	
	public Long add(DonationDTO dto){
		entityManager.persist(dto);
		return dto.getId();
	}
	
	public void update(DonationDTO dto) {
		entityManager.merge(dto);
	}
	
	
	
	public void delete(DonationDTO dto) {
		entityManager.remove(dto);
	}
	
	public DonationDTO findByPk(Long pk) {
		DonationDTO dto=entityManager.find(DonationDTO.class,pk);
		return dto;
		
	}
	
	public List search(DonationDTO dto,int pageNo,int pageSize) {
		List list=new ArrayList();
		CriteriaBuilder  builder=entityManager.getCriteriaBuilder();
		CriteriaQuery cq=builder.createQuery(DonationDTO.class);
		Root qRoot=cq.from(DonationDTO.class);
		List<Predicate> predicateList = new ArrayList<Predicate>();
		if(dto!=null) {
			if(dto.getName()!=null && dto.getName().length()>0) {
				predicateList.add(builder.like(qRoot.get("name"),dto.getName()+"%"));
				
				
			}
			
			if(dto.getOrganizer()!=null && dto.getOrganizer().length()>0) {
				predicateList.add(builder.like(qRoot.get("organizer"),dto.getOrganizer()+"%"));
				
				
			}
		}
		
		cq.where(predicateList.toArray(new Predicate[predicateList.size()]));
		TypedQuery typedQuery=entityManager.createQuery(cq);
		
		if(pageSize>0) {
			typedQuery.setFirstResult(pageNo*pageSize);
			typedQuery.setMaxResults(pageSize);
			
		}
		
		list=typedQuery.getResultList();
		return list;
	}
	
	
}
