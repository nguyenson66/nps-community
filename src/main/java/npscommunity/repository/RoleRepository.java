package npscommunity.repository;

import org.springframework.data.repository.CrudRepository;

import npscommunity.entity.AppRole;

public interface RoleRepository extends CrudRepository<AppRole, String>{
}
