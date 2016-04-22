package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.Manager;

public class ManagerDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper managerMapper = new RowMapper() {
		public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
			Manager manager = new Manager();
			manager.setUserId(rs.getString("user_id"));
			manager.setPassword(rs.getString("password"));
			manager.setUserType(rs.getString("user_type"));
			manager.setUserName(rs.getString("user_name"));
			manager.setServerId(rs.getString("server_id"));
			return manager;
		}
	};

	public void addManager(final Manager manager) {
		String query = "" +
				"INSERT INTO T_NF_MANAGER " +
				"	(user_id, password, user_type, user_name, server_id) " +
				"VALUES " +
				"	(?, ?, ?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			manager.getUserId(),
			manager.getPassword(),
			manager.getUserType(),
			manager.getUserName(),
			manager.getServerId()
		});
	}

	public void deleteManager(String user_id) {
		String query = "DELETE FROM T_NF_MANAGER WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] { user_id });
	}

	public void updateManager(Manager manager) { 
		String query = "" + 
				"UPDATE T_NF_MANAGER SET " +
				"	user_id = ?, " +
				"	password = ?, " +
				"	user_type = ?, " +
				"	user_name = ?, " +
				"	server_id = ? " +
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			manager.getUserId(),
			manager.getPassword(),
			manager.getUserType(),
			manager.getUserName(),
			manager.getServerId()
		});
	}

	public Manager getManager(String user_id) {
		String query = "" + 
				"SELECT user_id, password, user_type, user_name, server_id " +
				"FROM T_NF_MANAGER " +
				"WHERE user_id = ? ";
		return (Manager)this.jdbcTemplate.queryForObject(query, new Object[] { user_id }, this.managerMapper);
	}

	public List<Manager> getManagerList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" user_id, password, user_type, user_name, server_id " +
				"FROM T_NF_MANAGER " +
				"WHERE user_id <= ( " +
				"	SELECT MIN(user_id) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" user_id " +
				"		FROM T_NF_MANAGER " +
				"		ORDER BY user_id DESC " +
				"	) AS A) " +
				"ORDER BY user_id DESC";
		return (List<Manager>)this.jdbcTemplate.query(query, this.managerMapper);
	}
}	
