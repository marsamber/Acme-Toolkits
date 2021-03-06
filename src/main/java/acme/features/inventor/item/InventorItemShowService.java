package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorItemShowService implements AbstractShowService<Inventor, Item> {


	@Autowired
	protected InventorItemRepository repository;

	// Interface 


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		int id;
		int principalId;
		Item it;
		boolean result;
		
		id = request.getModel().getInteger("id");
		it = this.repository.findById(id);
		
		principalId = request.getPrincipal().getActiveRoleId();
		result = it != null && it.getInventor().getId() == principalId;
		
		return result;
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		Item result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"name", "code", "technology", "description", "retailPrice","published", "link","type");
		final AuthenticatedMoneyExchangePerformService moneyExchange= new AuthenticatedMoneyExchangePerformService();
		
		final Money money =entity.getRetailPrice();
		final Money moneyEUR = moneyExchange.computeMoneyExchange(money, "EUR").getTarget();
		final Money moneyUSD = moneyExchange.computeMoneyExchange(money, "USD").getTarget();
		final Money moneyGBP = moneyExchange.computeMoneyExchange(money, "GBP").getTarget();
		
		model.setAttribute("retailPriceEUR", moneyEUR);
		model.setAttribute("retailPriceUSD", moneyUSD);
		model.setAttribute("retailPriceGBP", moneyGBP);
	}
	
}
