package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Patronage;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronPatronageRepository extends AbstractRepository {

	@Query("select p from Patronage p where p.id = :id")
	Patronage findById(int id);
	
	@Query("select p from Patronage p where p.patron.id = :id")
	Collection<Patronage> findPatronagesByPatronId(int id);

	@Query("select p from Patronage p where p.code = :code")
	Patronage findByCode(String code);
	
	@Query("SELECT acceptedCurrencies FROM SystemConfiguration")
	String getAcceptedCurrencies();
}
