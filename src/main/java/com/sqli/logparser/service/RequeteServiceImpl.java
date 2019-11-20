package com.sqli.logparser.service;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqli.logparser.dao.RequeteDao;
import com.sqli.logparser.model.Reponse;
import com.sqli.logparser.model.Requete;

@Service
@Transactional
public class RequeteServiceImpl implements RequeteService {

	@Autowired
	private RequeteDao daoRequete;
	@PersistenceContext
	private EntityManager em;

	@Override
	public void ajouter(List<Requete> r) {

		Iterator<Requete> iterator = r.iterator();

		int cont = 0;
		while (iterator.hasNext()) {
			em.persist(iterator.next());

		}

	}

	@Override
	public Requete ajouter(Requete r) {
		// TODO Auto-generated method stub
		return daoRequete.save(r);
	}

}
