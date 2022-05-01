package npscommunity.admin.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import npscommunity.admin.Entity.Admin;


@Repository
public interface AdminRepositoty extends CrudRepository<Admin, Integer> {
	
	@Query("select * from admin where username = ?1")
	Admin findUserByUsername(String username);
}
