package npscommunity.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import npscommunity.mapper.QuestionMapper;
import npscommunity.model.Question;

@Repository
@Transactional
public class QuestionDAO extends JdbcDaoSupport{

	@Autowired
	public QuestionDAO(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		this.setDataSource(dataSource);
	}
	
	public Question findQuestionByID(String id) {
		// TODO Auto-generated method stub
		String sql = QuestionMapper.BASE_SQL + " WHERE Q.question_id = ?";

		Object[] params = new Object[] { id };
		QuestionMapper mapper = new QuestionMapper();
		try {
			Question questionInfo = this.getJdbcTemplate().queryForObject(sql, mapper, params);
			return questionInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
