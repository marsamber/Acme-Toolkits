package acme.features.anonymous.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.entities.Item.Type;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousItemRepository extends AbstractRepository {

	@Query("select i from Item i where i.id = :id")
	Item findById(int id);
	
	@Query("select i from Item i where i.type = 'TOOL'")
	Collection<Item> findAllTools();
	
	@Query("select i from Item i where i.type = :type")
	Collection<Item> findAllComponents(Type type);


}