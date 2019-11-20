
package com.sqli.logparser.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.sqli.logparser.dto.Ligne;
import com.sqli.logparser.dto.LigneReponse;
import com.sqli.logparser.dto.LigneRequete;
import com.sqli.logparser.model.Demande;
import com.sqli.logparser.model.Fichier;
import com.sqli.logparser.model.Reponse;
import com.sqli.logparser.model.Requete;
import com.sqli.logparser.model.Utilisateur;
import com.sqli.logparser.utilitaire.Utilitaire;

@Service
public class LogParserImpl implements LogParser {

	private static final Logger logger = LoggerFactory.getLogger(LogParserImpl.class);
	private static final Logger loggerStat = LoggerFactory.getLogger("Console");

	@Autowired
	Utilitaire utilitaire;
	@Autowired
	FichierService fichierService;
	@Autowired
	UtilisateurService utilisateurService;
	@Autowired
	DemandeService demandeService;
	@Autowired
	ReponseService reponseService;
	@Autowired
	RequeteService requeteService;

	private List<Demande> listdemandes = new ArrayList<Demande>();
	private List<Requete> listRequettes = new ArrayList<Requete>();
	private List<Reponse> listReponse = new ArrayList<Reponse>();
	private List<String> lignes = new ArrayList<String>();
	private int taille = 0;

	@Override
	public void parser(File f, Fichier fichier) throws IOException, ParseException, URISyntaxException {
		Scanner scanner = new Scanner(f);
		while (scanner.hasNextLine()) {

			Ligne ligne = null;

			ligne = parser(scanner.nextLine(), fichier);

			if (ligne == null) {
				logger.warn("Ligne vide !");

			} else {
				Demande d = demandeService.getDemande(ligne.getCorrelation());
				Utilisateur utilisateur = utilisateurService.getUtilisateur(ligne.getUtilisateur());
				String[] date = ligne.getDate().split(" ");
				Demande demande = new Demande();
				demande.setFichier(fichier);
				if (utilisateur == null) {
					utilisateur = new Utilisateur();
					utilisateur.setCode(ligne.getUtilisateur());
					Utilisateur u = utilisateurService.ajouter(utilisateur);
					demande.setUtilisateur(utilisateur);
				}
				if (ligne instanceof LigneRequete && d == null) {
					Requete requete = new Requete();
					requete.setHeaders(ligne.getHeaders());
					requete.setOrigine(ligne.getOrigine());
					requete.setBody(((LigneRequete) ligne).getBody());
					requete.setCorrelation(ligne.getCorrelation());
					listRequettes.add(requete);

					demande.setDate(utilitaire.getDate(ligne.getDate()));
					demande.setThread(ligne.getThread());
					demande.setCorrelation(ligne.getCorrelation());
					demande.setProtocole(ligne.getProtocole());
					demande.setMethode(HttpMethod.valueOf(((LigneRequete) ligne).getMethode()));
					demande.setRemote(((LigneRequete) ligne).getRemote());
					demande.setDateCreation(new Date());
					demande.setUri(utilitaire.getURI(((LigneRequete) ligne).getUri()));
					demande.setRequete(requete);
					demande.setUtilisateur(utilisateur);
					listdemandes.add(demande);

				} else if (ligne instanceof LigneReponse) {
					Reponse reponse = new Reponse();
					reponse.setBody(((LigneReponse) ligne).getBody());
					reponse.setOrigine(ligne.getOrigine());
					reponse.setHeaders(ligne.getHeaders());
					reponse.setCorrelation(ligne.getCorrelation());
					reponse.setDate(utilitaire.getDate(((LigneReponse) ligne).getDaterep()));
					listReponse.add(reponse);

					Demande dd = demandeService.getDemande(listdemandes, ligne.getCorrelation());
					dd.setStatus(HttpStatus.valueOf(((LigneReponse) ligne).getStatus()));
					dd.setDuree(((LigneReponse) ligne).getDuree());
					dd.setDateModification(new Date());
					dd.setReponse(reponse);
				}

			}
		}
		loggerStat.info(listdemandes.size() - taille + " demandes traitées avec succèes !");
		taille = listdemandes.size();
	}

	@Override
	public Ligne parser(String ligne, Fichier fichier) throws IOException {

		String[] arrLign = ligne.split(" - ", 4);

		JsonObject json = null;
		try {
			json = new JsonParser().parse(arrLign[3]).getAsJsonObject();

			if (arrLign.length != 4 || json == null) {
				lignes.add(ligne);
				logger.error("| Fichier : \" " + fichier.getNom() + "\" | Split error : " + ligne);
				return null;
			} else {

				String idUser = arrLign[2].trim();
				if (json.get("type").getAsString().equals("request")) {
					LigneRequete l = new LigneRequete();
					l.setDate(arrLign[0]);
					l.setThread(arrLign[1].trim());

					if (idUser.equals("")) {
						l.setUtilisateur(0);
					} else {
						l.setUtilisateur(Integer.parseInt(idUser));
					}

					l.setCorrelation(json.get("correlation").getAsString());
					l.setProtocole(json.get("protocol").getAsString());
					l.setMethode(json.get("method").getAsString());
					l.setOrigine(json.get("origin").getAsString());
					l.setRemote(json.get("remote").getAsString());
					l.setUri(json.get("uri").getAsString());
					l.setHeaders(json.get("headers").getAsJsonObject().toString());
					if (json.get("body") == null) {
						l.setBody("");
					} else {
						if (json.get("body").isJsonObject()) {
							l.setBody(json.get("body").getAsJsonObject().toString());
						} else if (json.get("body").isJsonArray()) {
							l.setBody(json.get("body").getAsJsonArray().toString());
						} else {
							l.setBody(json.get("body").getAsString());
						}
					}
					if (!l.validerLigne()) {

						lignes.add(ligne);
						logger.error("| Fichier : \"" + fichier.getNom() + "\" | valider ligne : " + ligne);
						return null;
					}

					return l;

				} else {

					LigneReponse r = new LigneReponse();

					r.setDate(arrLign[0]);
					r.setDaterep(arrLign[0]);
					r.setThread(arrLign[1].trim());

					if (idUser.equals("")) {
						r.setUtilisateur(0);
					} else {

						r.setUtilisateur(Integer.parseInt(idUser));
					}
					r.setCorrelation(json.get("correlation").getAsString());
					r.setDuree(json.get("duration").getAsInt());
					r.setOrigine(json.get("origin").getAsString());
					r.setProtocole(json.get("protocol").getAsString());
					r.setStatus(json.get("status").getAsInt());
					r.setHeaders(json.get("headers").getAsJsonObject().toString());

					if (json.get("body") == null) {
						r.setBody("");
					} else {
						if (json.get("body").isJsonObject()) {
							r.setBody(json.get("body").getAsJsonObject().toString());
						} else if (json.get("body").isJsonArray()) {
							r.setBody(json.get("body").getAsJsonArray().toString());
						} else {
							r.setBody(json.get("body").getAsString());
						}
					}
					if (!r.validerLigne()) {

						lignes.add(ligne);
						logger.error("| Fichier : \"" + fichier.getNom() + "\" | valider ligne : " + ligne);
						return null;
					}

					return r;

				}
			}

		} catch (JsonSyntaxException e) {

			lignes.add(ligne);
			logger.error("| Fichier : \"" + fichier.getNom() + "\" | " + e.getClass().getSimpleName() + " : " + ligne);
			return null;

		} catch (Exception e) {

			lignes.add(ligne);
			logger.error("| Fichier : \"" + fichier.getNom() + "\" | " + e.getClass().getSimpleName() + " : " + ligne);
			return null;
		}

	}

	@Override
	public void parserDossier(String path) throws IOException, ParseException, URISyntaxException {
		// TODO Auto-generated method stub

		lignes.clear();
		listdemandes.clear();
		listReponse.clear();
		listRequettes.clear();
		List<File> files = utilitaire.extraireFichiers(path);
		Fichier fich = null;
		for (File f : files) {
			if (!utilitaire.verifierExtension(f)) {
				fichierLogging(f.getName());
			} else
				try {
					{
						Fichier fichier = fichierService.fichierexiste(f.getName());
						if (fichier == null) {
							fichier = new Fichier();
							fichier.setNom(f.getName());
							fichier.setDateCreation(new Date());
							fich = fichierService.ajouter(fichier);
							loggerStat.info("Fichier : \"" + fichier.getNom() + "\" ajouté avec succès !");
							parser(f, fich);
						} else {

							fichier.setDateModification(new Date());
							fichierService.modifier(fichier.getDateModification());
							parser(f, fich);
						}

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		demandeService.verifierDemande(listdemandes);
		requeteService.ajouter(listRequettes);
		reponseService.ajouter(listReponse);
		demandeService.ajouter(listdemandes);
		getInformations();
	}

	private void fichierLogging(String nom) {

		logger.warn("Le fichier : " + nom + "n'est pas un fichier .log");
	}

	private void getInformations() {
		String message = "\n******************************************************************************"
				+ "\nNombre de demandes : " + listdemandes.size() + "\nNombre de requêtes : " + listRequettes.size()
				+ "\nNombre de réponses : " + listReponse.size() + "\nNombre demandes échouées : " + lignes.size();
		loggerStat.info(message);
	}

}
