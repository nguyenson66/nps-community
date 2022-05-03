package npscommunity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import npscommunity.model.User;

public class UserMapper implements RowMapper<User>{

	public static final String BASE_SQL =  "SELECT * FROM USER U";

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO convert row result to object
		long id = rs.getLong("id");
        String username = rs.getString("username");
        String password = rs.getString("password");
        String email = rs.getString("email");
        String name = rs.getString("name");
        String address = rs.getString("address");
        Timestamp birthday = rs.getTimestamp("birthday");
        Timestamp created_at = rs.getTimestamp("created_at");
        Timestamp updated_at = rs.getTimestamp("updated_at");
        

        return new User(id, username, password, email, email, name, address, birthday, created_at, updated_at);
	}
	
}
