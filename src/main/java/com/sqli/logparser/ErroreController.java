package com.sqli.logparser;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 
 * 
 * 
 * @author sqli
 *
 */
@Controller
public class ErroreController implements ErrorController {

	/**
	 * 
	 * une methode qui permet de manipuler les erreurs et de retourner une page jsp pour fournir une 
	 * description de l'erreur avec un style customisé
	 * 
	 * @param request une requête
	 * @return page jsp 404
	 */
	@RequestMapping("/error")
	public String manipulerError(HttpServletRequest request) {

		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				return "pages/404";
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "pages/500";
			}
		}

		// do something like logging
		return "pages/404";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

}
