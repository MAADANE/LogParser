package com.sqli.logparser.service;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqli.logparser.dao.ReponseDao;
import com.sqli.logparser.model.Demande;
import com.sqli.logparser.model.Reponse;
import com.sqli.logparser.model.Requete;

@Service
@Transactional
public class ReponseServiceImpl implements ReponseService {

	@Autowired
	private ReponseDao daoreponse;
	@PersistenceContext
	private EntityManager em;
	@Override
	public void ajouter(List<Reponse> r) {

		
		Iterator<Reponse> iterator = r.iterator();
		
		int cont = 0;
		while (iterator.hasNext()) {
		    em.persist(iterator.next());
		   
		}
		
		//return daoreponse.saveAll(r);
	}

	@Override
	public Reponse ajouter(Reponse r) {
		// TODO Auto-generated method stub
		return daoreponse.save(r);
	}



}
