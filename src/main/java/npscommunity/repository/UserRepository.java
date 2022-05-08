package npscommunity.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import npscommunity.entity.AppUser;

public interface UserRepository extends CrudRepository<AppUser, String> {
	@Query("select u from user u where u.username = ?1")
	AppUser findByUsername(String username);
}
