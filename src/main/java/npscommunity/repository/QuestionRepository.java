package npscommunity.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import npscommunity.entity.Question;

public interface QuestionRepository extends CrudRepository<Question, Long>{

}
