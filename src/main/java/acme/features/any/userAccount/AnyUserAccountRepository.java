
package acme.features.any.userAccount;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;
import acme.roles.Patron;

@Repository
public interface AnyUserAccountRepository extends AbstractRepository {

	@Query("select  ua from    UserAccount ua where ua.id = :id")
	UserAccount findById(int id);

	@Query("select  ua from UserAccount ua join ua.roles r where ua.enabled = true and (type(r) = Inventor or type(r) = Patron) and Administrator not in ( select type(r) from UserAccount ua2 join ua2.roles r where ua2.id = ua.id)")
	Collection<UserAccount> findAllUserAccounts();

	@Query("select i from Inventor i where i.userAccount.id= :userAccountId")
	Collection<Inventor> findInventorByUserAccount(int userAccountId);

	@Query("select p from Patron p where p.userAccount.id=:userAccountId")
	Collection<Patron> findPatronByUserAccount(int userAccountId);

}
