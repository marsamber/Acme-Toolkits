package acme.features.inventor.patronageReport;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.PatronageReport;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorPatronageReportController extends AbstractController<Inventor, PatronageReport> {
	
	// Internal state
	
	@Autowired
	protected InventorPatronageReportListMineService listMineService;
	
	@Autowired
	protected InventorPatronageReportShowService showService;
	
	@Autowired
	protected InventorPatronageReportCreateService createService;

	// Constructors 

	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list-mine", "list", this.listMineService);
		super.addCommand("create", this.createService);
	}

}
