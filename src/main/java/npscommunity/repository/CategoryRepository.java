package npscommunity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import npscommunity.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, String>{

	@Query(nativeQuery = true, value = "select c.id, c.name from category c, question_categories qc where c.id = qc.categories_id group by c.id order by(count(c.name)) desc")
	List<Category> findHotCategory();

}
