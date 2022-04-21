package acme.features.authenticated.announcement;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Announcement;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAnnouncementRepository extends AbstractRepository {

	@Query("select a from Announcement a where a.creationMoment > :deadline")
	Collection<Announcement> findRecentAnnouncements(Date deadline);
	
	@Query("select a from Announcement a where a.id = :id")
	Announcement findOneAnnouncementById(int id);

}