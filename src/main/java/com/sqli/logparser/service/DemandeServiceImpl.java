package com.sqli.logparser.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sqli.logparser.dao.DemandeDao;
import com.sqli.logparser.model.Demande;
import com.sqli.logparser.model.Reponse;

@Service
@Transactional
public class DemandeServiceImpl implements DemandeService {

	@Autowired
	private DemandeDao daodemande;

	@PersistenceContext
	private EntityManager em;

	@Override
	public void ajouter(List<Demande> d) {

		Iterator<Demande> iterator = d.iterator();

		while (iterator.hasNext()) {
			em.persist(iterator.next());
		}

	}

	@Override
	public void modifier(HttpStatus status, int duree, Date dateModification, Reponse reponse, String correlation) {
		// TODO Auto-generated method stub
		daodemande.modifier(status, duree, dateModification, reponse, correlation);
	}

	@Override
	public List<Demande> getAll() {
		// TODO Auto-generated method stub
		return (List<Demande>) daodemande.findAll();
	}

	@SuppressWarnings("unchecked")
	@Override

	public List<Demande> getDemandeByCondition(String sql) {
		// TODO Auto-generated method stub
		Query query = em.createNativeQuery(sql, Demande.class);

		return query.getResultList();
	}

	public List<String> getStatus() {
		// TODO Auto-generated method stub
		return daodemande.getStatus();
	}

	@Override
	public List<String> getMethode() {
		// TODO Auto-generated method stub
		return daodemande.getMethode();

	}

	@Override
	public Demande getDemande(List<Demande> list, String correlation) {
		// TODO Auto-generated method stub
		for (Demande d : list) {
			if (d.getCorrelation().equals(correlation)) {
				return d;
			}
		}
		return null;
	}

	@Override
	public Demande getDemande(String correlation) {
		// TODO Auto-generated method stub
		return daodemande.getDemande(correlation);
	}

	@Override
	public Optional<Demande> getDemandeById(int id) {
		// TODO Auto-generated method stub
		return daodemande.findById(id);
	}

	@Override
	public void ajouter(Demande d) {
		// TODO Auto-generated method stub

	}

	@Override
	public int DemandeCount(String sql) {
		// TODO Auto-generated method stub
		int num = ((BigInteger) em.createNativeQuery(sql).getSingleResult()).intValue();
		return num;

	}

	@Override
	public void verifierDemande(List<Demande> d) {
		// TODO Auto-generated method stub

		Iterator<Demande> iterator = d.iterator();
		Demande demande = null;
		while (iterator.hasNext()) {
			demande = iterator.next();
			if (demande.getReponse() == null || demande.getRequete() == null) {
				iterator.remove();
			}
		}

	}

}
