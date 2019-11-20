package com.sqli.logparser.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqli.logparser.dao.FichierDao;
import com.sqli.logparser.model.Demande;
import com.sqli.logparser.model.Fichier;

@Service
@Transactional
public class FichierServiceImpl implements FichierService {

	@Autowired
	private FichierDao fichierDao;
	@PersistenceContext
	private EntityManager em;

	@Override
	public void ajouter(List<Fichier> d) {

		Iterator<Fichier> iterator = d.iterator();

		while (iterator.hasNext()) {
			em.persist(iterator.next());

		}

	}

	@Override
	public Fichier ajouter(Fichier f) {

		return fichierDao.save(f);

	}

	@Override
	public Fichier fichierexiste(String nom) {
		return fichierDao.getFichier(nom);
	}

	@Override
	public void modifier(Date dateModification) {
		// TODO Auto-generated method stub
		fichierDao.modifier(dateModification);
	}
}
