package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.entities.Item.Type;
import acme.entities.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorItemRepository extends AbstractRepository {

	@Query("select i from Item i where i.id = :id")
	Item findById(int id);

	@Query("select t from Toolkit t where t.id = :toolkitId")
	Toolkit findOneToolkitById(int toolkitId);
	
	@Query("select ti.item from ToolkitItem ti where ti.toolkit.id=:toolkitId")
	Collection<Item> findManyItemsByToolkitId(int toolkitId);
	
	@Query("select i from Item i where i.inventor.id = :inventorId and i.type = :type")
	Collection<Item> findItemsByInventorIdAndType(int inventorId, Type type);

}