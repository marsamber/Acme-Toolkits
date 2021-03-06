
package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Toolkit;
import acme.entities.ToolkitItem;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyToolkitShowService implements AbstractShowService<Any, Toolkit> {

	@Autowired
	protected AnyToolkitRepository repository;

	// Interface 


	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		boolean result;
		int id;
		Toolkit toolkit;

		id = request.getModel().getInteger("id");
		toolkit = this.repository.findById(id);
		result = !toolkit.getDraftMode();

		return result;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findById(id);

		final Collection<ToolkitItem> toolkitItems = this.repository.findItemsByToolkit(result.getId());
		double price = 0;
		String currency = "";
		for (final ToolkitItem toolkitItem : toolkitItems) {
			currency = toolkitItem.getItem().getRetailPrice().getCurrency();
			price = price + toolkitItem.getItem().getRetailPrice().getAmount()*toolkitItem.getUnits();
		}
		final Money totalPrice = new Money();
		totalPrice.setAmount(price);
		totalPrice.setCurrency(currency);
		result.setTotalPrice(totalPrice);

		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		AuthenticatedMoneyExchangePerformService moneyExchange= new AuthenticatedMoneyExchangePerformService();

		request.unbind(entity, model, "title", "code", "description", "assemblyNotes", "link", "totalPrice");
		
		Money money =entity.getTotalPrice();
		Money moneyEUR = moneyExchange.computeMoneyExchange(money, "EUR").getTarget();
		Money moneyUSD = moneyExchange.computeMoneyExchange(money, "USD").getTarget();
		Money moneyGBP = moneyExchange.computeMoneyExchange(money, "GBP").getTarget();
		
		model.setAttribute("totalPriceEUR", moneyEUR);
		model.setAttribute("totalPriceUSD", moneyUSD);
		model.setAttribute("totalPriceGBP", moneyGBP);
		model.setAttribute("readonly", true);
	}

}
