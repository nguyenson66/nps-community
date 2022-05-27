package npscommunity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import npscommunity.dto.ManagerCategory;
import npscommunity.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query(name = "get_list_category", nativeQuery = true)
    List<ManagerCategory> getListCategory();

    @Query(nativeQuery = true, value = "select c.id, c.name from category c, question_categories qc where c.id = qc.categories_id group by c.id order by(count(c.name)) desc")
    List<Category> findHotCategory();

    @Query("select distinct q.name from Category q")
    List<String> findDistinctCategory();

    Category findByName(String name);
}
