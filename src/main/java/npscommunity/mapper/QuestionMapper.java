package npscommunity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import npscommunity.model.Question;

public class QuestionMapper implements RowMapper<Question>{

	public static final String BASE_SQL =  "SELECT * FROM QUESTION Q";
	
	@Override
	public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
        
        long id = rs.getLong("id");
    	long user_id = rs.getLong("user_id");
    	long category_id = rs.getLong("category_id");
    	String title = rs.getString("title");
    	String content = rs.getString("content");
    	long viewed = rs.getLong("viewed");
    	long vote_up = rs.getLong("vote_up");
    	long vote_down = rs.getLong("vote_down");
    	Timestamp created_at = rs.getTimestamp("created_at");
    	Timestamp updated_at = rs.getTimestamp("updated_at");
        
        return new Question(id, user_id, category_id, title, content, viewed, vote_up, vote_down, created_at, updated_at);
	}

}
