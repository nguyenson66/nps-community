package npscommunity.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import npscommunity.entity.AppUser;

public interface UserRepository extends CrudRepository<AppUser, Long> {
//	@Query("select u from user u where u.username = ?1")
	AppUser findByUsername(String username);
	
//	@Query("select r.name from user u join u.roles r where u.username = ?1")
//	List<String> findRolesByUsername(String username);
}
