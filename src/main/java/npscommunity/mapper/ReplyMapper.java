package npscommunity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import npscommunity.model.Reply;

public class ReplyMapper implements RowMapper<Reply>{

	public static final String BASE_SQL =  "SELECT * FROM REPLY R";

	@Override
	public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		long id = rs.getLong("id");
		long user_id = rs.getLong("user_id");
		long question_id = rs.getLong("question_id");
		String content = rs.getString("content");
		long vote_up = rs.getLong("vote_up");
		long vote_down = rs.getLong("vote_down");
		
		return new Reply(id, user_id, question_id, content, vote_up, vote_down);
	}
}
