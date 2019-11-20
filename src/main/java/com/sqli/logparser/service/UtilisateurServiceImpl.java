package com.sqli.logparser.service;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqli.logparser.dao.UtilisateurDao;
import com.sqli.logparser.model.Requete;
import com.sqli.logparser.model.Utilisateur;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	private UtilisateurDao daoUtilisateurDao;
	@PersistenceContext
	private EntityManager em;

	@Override
	public void ajouter(List<Utilisateur> u) {

		Iterator<Utilisateur> iterator = u.iterator();

		while (iterator.hasNext()) {
			em.persist(iterator.next());

		}

	}

	@Override
	public Utilisateur getUtilisateur(int code) {
		return daoUtilisateurDao.getUtilisateur(code);
	}

	@Override
	public Utilisateur ajouter(Utilisateur u) {
		// TODO Auto-generated method stub
		return daoUtilisateurDao.save(u);
	}

}
