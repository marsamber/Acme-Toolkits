
package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item.Type;
import acme.entities.Toolkit;
import acme.entities.ToolkitItem;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitPublishService implements AbstractUpdateService<Inventor, Toolkit> {

	@Autowired
	protected InventorToolkitRepository repository;


	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		boolean result = false;
		int toolkitId;
		Toolkit toolkit;
		int inventorId;

		toolkitId = request.getModel().getInteger("id");
		toolkit = this.repository.findById(toolkitId);
		final Collection<ToolkitItem> toolkitItems = this.repository.findItemsByToolkit(toolkitId);

		inventorId = request.getPrincipal().getActiveRoleId();
		for (final ToolkitItem toolkitItem : toolkitItems) {
			result = toolkitItems != null && toolkitItem.getItem().getInventor().getId() == inventorId;
			if (result && Boolean.FALSE.equals(toolkit.getDraftMode()))
				return true;
		}
		
		if(toolkitItems == null || toolkitItems.isEmpty()) return true;

		return result;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findById(id);

		return result;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "title", "code", "description", "assemblyNotes", "link");

	}
	
	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		Collection<ToolkitItem> toolkitItems;
		boolean itemsPublished;
		boolean containsTool;
		
		toolkitItems = this.repository.findItemsByToolkit(entity.getId());
		itemsPublished = toolkitItems.stream().allMatch(x->x.getItem().isPublished());
		containsTool = toolkitItems.stream().anyMatch(x->x.getItem().getType() == Type.TOOL);
		
		errors.state(request, !toolkitItems.isEmpty(), "*", "inventor.toolkit.form.error.no-items");
		errors.state(request, itemsPublished, "*", "inventor.toolkit.form.error.not-all-published");
		errors.state(request, containsTool, "*", "inventor.toolkit.form.error.no-tools");
		

	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "code", "description", "assemblyNotes", "link");

	}

	

	@Override
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		
		entity.setDraftMode(false);
		this.repository.save(entity);
	}

}
