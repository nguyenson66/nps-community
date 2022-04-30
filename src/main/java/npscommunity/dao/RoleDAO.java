package npscommunity.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import npscommunity.model.User;

@Repository
@Transactional
public class RoleDAO extends JdbcDaoSupport{
	
	@Autowired
	public RoleDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
	public List<String> getRoleNames(Long id) {
        String sql = "SELECT R.name FROM USER_ROLE UR, ROLE R WHERE UR.role_id = R.id AND UR.user_id = ?";

        Object[] params = new Object[] { id };

        List<String> roles = this.getJdbcTemplate().queryForList(sql, String.class, params);

        return roles;
    }

	public void initRoleForNewUser(User user) {
		// TODO Init role "USER" for new user
		this.getJdbcTemplate().update("INSERT INTO USER_ROLE (user_id, role_id) VALUES (?, ?)", user.getId(), 2);
	}
}
