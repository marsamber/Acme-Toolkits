/*
 * AuthenticatedProviderController.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.patron;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;
import acme.roles.Patron;

@Controller
@RequestMapping("/authenticated/patron")
public class AuthenticatedPatronController extends AbstractController<Authenticated, Patron> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedPatronCreateService	createService;

	@Autowired
	protected AuthenticatedPatronUpdateService	updateService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
	}
	
	@GetMapping("/dashboard")
	public ModelAndView patronDashboardController() {
		ModelAndView result;

		result = new ModelAndView();
		result.setViewName("authenticated/patron/patron-dashboard");

		return result;
	}

}
