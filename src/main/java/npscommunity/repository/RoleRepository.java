package npscommunity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import npscommunity.entity.AppRole;

public interface RoleRepository extends CrudRepository<AppRole, String>{

	@Query("select r.name from role r, role.users ur where ur.users_id = ?1 and ur.roles_id = r.id")
	List<String> findAllRoleByUserId(long id);
}
