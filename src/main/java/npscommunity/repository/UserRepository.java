package npscommunity.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import npscommunity.entity.AppUser;
import npscommunity.dto.ManagerUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {
	// @Query("select u from user u where u.username = ?1")
	AppUser findByUsername(String username);

	// @Query("select r.name from user u join u.roles r where u.username = ?1")
	// List<String> findRolesByUsername(String username);

	@Query("select count(u) as count from User u where u.createdAt >= ?1 and u.createdAt < ?2")
	long countUserByDate(Date from, Date to);

	@Query(name = "get_manager_user", nativeQuery = true)
	List<ManagerUser> getListTopUser();

	@Query(name = "find_user_by_username_email", nativeQuery = true)
	List<ManagerUser> findUserByUsernameOrEmail(String usernameOrEmail);
}
