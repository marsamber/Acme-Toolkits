package acme.features.anonymous.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Item.Type;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousItemListAllComponentsService implements AbstractListService<Anonymous, Item> {


	@Autowired
	protected AnonymousItemRepository repository;

	// Interface 


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Item> findMany(final Request<Item> request) {
		assert request != null;

		Collection<Item> result;
		final Type type = Type.COMPONENT;
		
		result = this.repository.findAllComponents(type);
		
		return result;
	}
	
	public Collection<Item> findItemsByType(Type type) {
		return this.repository.findAllComponents(type);
	}
	
	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "type");
	}

}