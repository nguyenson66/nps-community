package npscommunity.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import npscommunity.dto.ManagerQuestionDto;
import npscommunity.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	@Query("select sum(viewed) as view from Question")
	long countView();

	@Query("select count(q) as count from Question q where q.createdAt >= ?1 and q.createdAt < ?2")
	long countQuestionByDate(Date from, Date to);

	@Query(name = "get_list_question", nativeQuery = true)
	List<ManagerQuestionDto> getListQuestion();

	@Query(name = "get_list_question_by_key", nativeQuery = true)
	List<ManagerQuestionDto> getQuestionByKey(String s);

	@Query(name = "get_list_question_by_username", nativeQuery = true)
	List<ManagerQuestionDto> getListQuestionByUserId(Long userId);

	@Query("select q from Question q where q.id = ?1")
	Question findById(long id);

	@Query("select q from Question q where q.title = ?1")
	List<Question> findQuestionbyTitle();
}
