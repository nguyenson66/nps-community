package npscommunity.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import npscommunity.mapper.UserMapper;
import npscommunity.model.User;

@Repository
@Transactional
public class UserDAO extends JdbcDaoSupport {

	@Autowired
	public UserDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public User findUserAccount(String username) {
		// Select .. from App_User u WHERE username = ?
		String sql = UserMapper.BASE_SQL + " WHERE U.username = ?";

		Object[] params = new Object[] { username };
		UserMapper mapper = new UserMapper();
		try {
			User userInfo = this.getJdbcTemplate().queryForObject(sql, mapper, params);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public long findIdByUsername(String username) {
		String sql = UserMapper.BASE_SQL + " WHERE U.username = ?";

		Object[] params = new Object[] { username };
		UserMapper mapper = new UserMapper();
		
		try {
			User userInfo = this.getJdbcTemplate().queryForObject(sql, mapper, params);
			return userInfo.getId();
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}

	public User findUserByID(String id) {
		String sql = UserMapper.BASE_SQL + " WHERE U.id = ?";

		Object[] params = new Object[] { id };
		UserMapper mapper = new UserMapper();
		
		try {
			User userInfo = this.getJdbcTemplate().queryForObject(sql, mapper, params);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public void save(User user) {
		this.getJdbcTemplate().update("INSERT INTO USER (username, password, email) VALUES (?, ?, ?)",
				user.getUsername(), user.getPassword(), user.getEmail());
	}

}