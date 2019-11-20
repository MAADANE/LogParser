package com.sqli.logparser;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sqli.logparser.dto.LigneDatatable;
import com.sqli.logparser.model.Demande;
import com.sqli.logparser.service.DemandeService;
import com.sqli.logparser.service.UtilisateurService;
import com.sqli.logparser.utilitaire.UtilitaireImpl;

/**
 * 
 * 
 * @author sqli
 *
 */

@Controller
@CrossOrigin("*")
public class DemandeController {

	@Autowired
	DemandeService demService;

	@Autowired
	UtilisateurService serviceUtilisatuer;

	/**
	 * 
	 * Méthode capable d'accepter un id en paramètre et de récupérer la demande
	 * correspendante
	 * 
	 * @param id id de la demande
	 * @return une page jsp fiche.jsp
	 */
	@RequestMapping("/details")
	public ModelAndView details(@PathParam("id") String id) {
		ModelAndView m = new ModelAndView("pages/fiche");
		Optional<Demande> d = demService.getDemandeById(Integer.parseInt(id));
		m.addObject("demande", d.get());

		return m;

	}

	/**
	 * Méthode qui permet de récupérer les listess des status, et des méthodes
	 * stockés dans la base de données
	 * 
	 * @return une page jsq rechercher.jsp
	 */
	@RequestMapping("/")
	public ModelAndView PageFiltrer() {
		ModelAndView m = new ModelAndView();
		m.addObject("status", demService.getStatus());
		m.addObject("methode", demService.getMethode());
		m.setViewName("pages/rechercher");
		return m;

	}

	/**
	 * 
	 * Méthode qui permet de réaliser les différents filtres, et d'afficher les
	 * résultats obtenus dans une datatable
	 * 
	 * @param du          date de début
	 * @param juquau      date de fin
	 * @param status      status de la demande
	 * @param user        code utilisateur
	 * @param uri         uri de la demande
	 * @param methode     méhtode de la demande
	 * @param draw        paramètre du datatable
	 * @param lengh       nombre de lignes sélectionnées
	 * @param start       indice de la première ligne de la page sélectionnée
	 * @param indexColumn indice de la colonne du tableau
	 * @param order       type de l'ordre
	 * @return object ligneDatatable de type json qui contient les informations sur
	 *         les demandes sélectionnées
	 */
	@RequestMapping("/rechercher")
	public @ResponseBody LigneDatatable rechercher(@RequestParam("du") String du, @RequestParam("juquau") String juquau,
			@RequestParam("status") String status, @RequestParam("user") String user, @PathParam("uri") String uri,
			@RequestParam("methode") String methode, @RequestParam("draw") String draw,
			@RequestParam("length") String lengh, @RequestParam("start") String start,
			@RequestParam("order[0][column]") int indexColumn, @RequestParam("order[0][dir]") String order) {
		LigneDatatable objLigne = new LigneDatatable();
		List<Demande> listDemande = null;
		String column = null;

		switch (indexColumn) {

		case 0:

			column = "d.date";
			break;
		case 1:
			column = "u.code";
			break;
		case 2:

			column = "d.uri";
			break;
		case 3:
			column = "d.methode";
			break;

		case 4:
			column = "d.remote";
			break;

		case 5:
			column = "d.thread";
			break;
		case 6:
			column = "d.correlation";
			break;
		case 7:
			column = "d.duree";
			break;
		case 8:
			column = "d.status";
			break;

		}

		String sql = "SELECT * FROM demande d inner join utilisateur u  on d.utilisateur_id=u.id where 1=1";
		String sqlcount = "SELECT count(*) FROM demande  where 1=1";
		if (du.equals("") && juquau.equals("") && status.equals("") && user.equals("") && uri.equals("")
				&& methode.equals("")) {

			sql += " " + "order by " + column + " " + order;
			sql += " " + "LIMIT" + " " + Integer.parseInt(start) + "," + Integer.parseInt(lengh);
			listDemande = demService.getDemandeByCondition(sql);
			for (Demande d : listDemande) {
				objLigne.data.add(d);
			}
			objLigne.draw = draw;
			objLigne.recordsFiltered = demService.DemandeCount(sqlcount);

			return objLigne;

		}

		if (!status.equals("")) {

			sql += " " + "and status=" + "'" + status + "'";
			sqlcount += " " + "and status=" + "'" + status + "'";
		}
		if (!uri.equals("")) {

			sql += " " + "and uri like" + "'%" + uri + "%'";
			sqlcount += " " + "and uri like" + "'%" + uri + "%'";

		}
		if (!methode.equals("")) {

			sql += " " + "and methode=" + "'" + methode + "'";
			sqlcount += " " + "and methode=" + "'" + methode + "'";
		}

		if (!du.equals("") && !juquau.equals("")) {

			sql += " " + "and date between" + "'" + du + ":00'" + "and" + "'" + juquau + ":59'";
			sqlcount += " " + "and date between" + "'" + du + ":00'" + "and" + "'" + juquau + ":59'";

		}
		if (!du.equals("") && juquau.equals("")) {
			sql += " " + "and date between" + "'" + du + ":00'" + "and" + "'" + new UtilitaireImpl().getDate(new Date())
					+ "'";
			sqlcount += " " + "and date between" + "'" + du + ":00'" + "and" + "'"
					+ new UtilitaireImpl().getDate(new Date()) + "'";
		}
		if (du.equals("") && !juquau.equals("")) {
			sql += " " + "and date between" + "'2000-01-01 00:00:00'" + "and" + "'" + juquau + ":59'";
			sqlcount += " " + "and date between" + "'2000-01-01 00:00:00'" + "and" + "'" + juquau + ":59'";
		}
		if (!user.equals("")) {
			sql += " " + "and (select id from Utilisateur where code ='" + user + "') = utilisateur_id";
			sqlcount += " " + "and (select id from Utilisateur where code ='" + user + "') = utilisateur_id";

		}

		sql += " " + "order by " + column + " " + order;
		sql += " " + "LIMIT" + " " + Integer.parseInt(start) + "," + Integer.parseInt(lengh);
		listDemande = demService.getDemandeByCondition(sql);

		for (Demande d : listDemande) {
			objLigne.data.add(d);
		}

		objLigne.draw = draw;

		objLigne.recordsFiltered = demService.DemandeCount(sqlcount);
		return objLigne;

	}

	/**
	 * Méthode permettant d'initialiser la datatable pour la première fois
	 * 
	 * @param du      date de début
	 * @param juquau  date de fin
	 * @param status  status de la demande
	 * @param user    code utilisateur
	 * @param uri     uri de la demande
	 * @param methode méhtode de la demande
	 * @param draw    paramètre du datatable
	 * @param lengh   nombre de lignes sélectionnées
	 * @param start   indice de la première ligne de la page sélectionnée
	 * 
	 * @return object ligneDatatable de type json qui contient des information sur
	 *         les demandes sélectionnées
	 */
	@RequestMapping("/r")
	public @ResponseBody LigneDatatable initDatatable() {
		LigneDatatable objLigne = new LigneDatatable();
		List<Demande> listDemande = null;

		listDemande = demService.getDemandeByCondition("SELECT * FROM Demande  where 1=2");

		for (Demande d : listDemande) {
			objLigne.data.add(d);
		}

		objLigne.recordsTotal = listDemande.size();
		objLigne.recordsFiltered = listDemande.size();

		return objLigne;

	}

}
