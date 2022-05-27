package npscommunity.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import npscommunity.entity.AppRole;

public interface RoleRepository extends CrudRepository<AppRole, Long> {

  @Query("select r from Role r where r.name = ?1")
  public AppRole findByName(String role);
}
