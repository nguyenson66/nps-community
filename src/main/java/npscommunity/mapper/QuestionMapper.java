package npscommunity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import npscommunity.model.Question;

public class QuestionMapper implements RowMapper<Question>{

	public static final String BASE_SQL =  "SELECT * FROM QUESTION Q";
	
	@Override
	public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		long question_id = rs.getLong("question_id");
		long user_id = rs.getLong("user_id");
        String title = rs.getString("title");
        String description = rs.getString("description");
        return new Question(question_id, user_id, title, description);
	}

}
