package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.Loginlog;
import kr.nomad.util.T;

public class LoginlogDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper loginlogMapper = new RowMapper() {
		public Loginlog mapRow(ResultSet rs, int rowNum) throws SQLException {
			Loginlog loginlog = new Loginlog();
			loginlog.setLogSeq(rs.getInt("log_seq"));
			loginlog.setUserId(rs.getString("user_id"));
			loginlog.setLogDate(rs.getString("log_date"));
			loginlog.setLogCount(rs.getInt("log_count"));
			loginlog.setRegDate(rs.getString("reg_date"));
			return loginlog;
		}
	};
	//로그인시 판별
	public Loginlog getLoginlog(String userId,String time) {
		String query = "" + 
				"SELECT *" +
				"FROM T_NF_LOGIN_LOG " +
				"WHERE user_id = ? and log_date = ? ";
		try{
			return (Loginlog)this.jdbcTemplate.queryForObject(query, new Object[] { userId,time }, this.loginlogMapper);
		}catch(Exception e){
			return null;
		}
	}
	//카운트 업데이트
	public void updateLoginlog(int cnt,String userId,String time) { 
		String query = "" + 
				"UPDATE T_NF_LOGIN_LOG SET " +
				"	log_count = ? " +
				"WHERE user_id = ? and log_date = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			cnt,userId,time
		});
	}
	
	//로그 추가
	public void addLoginlog(final Loginlog loginlog) {
		String query = "" +
				"INSERT INTO T_NF_LOGIN_LOG " +
				"	(user_id, log_date, log_count, reg_date) " +
				"VALUES " +
				"	(?, ?, ?,getDate()) ";
		this.jdbcTemplate.update(query, new Object[] {
			loginlog.getUserId(),
			loginlog.getLogDate(),
			loginlog.getLogCount()
		});
	}
	public int getLoginlog(String userId, String today , String before) {
		String query = ""+
				"select count(*) from t_nf_login_log " +
				"where user_id = ? and log_date between ? and  ? ";
		
		return this.jdbcTemplate.queryForInt(query, new Object[] { userId , before , today  });
	}
	//탈퇴시 삭제
	public void deleteLoginlog(String userId) {
		String query = "DELETE FROM T_NF_LOGIN_LOG WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] { userId });
	}

	public void updateLoginlog(Loginlog loginlog) { 
		String query = "" + 
				"UPDATE T_NF_LOGIN_LOG SET " +
				"	log_seq = ?, " +
				"	user_id = ?, " +
				"	log_date = ?, " +
				"	log_count = ?, " +
				"	reg_date = ? " +
				"WHERE log_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			loginlog.getLogSeq(),
			loginlog.getUserId(),
			loginlog.getLogDate(),
			loginlog.getLogCount(),
			loginlog.getRegDate()
		});
	}

	

	public List<Loginlog> getLoginlogList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" log_seq, user_id, log_date, log_count, reg_date " +
				"FROM T_NF_LOGIN_LOG " +
				"WHERE log_seq <= ( " +
				"	SELECT MIN(log_seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" log_seq " +
				"		FROM T_NF_LOGIN_LOG " +
				"		ORDER BY log_seq DESC " +
				"	) AS A) " +
				"ORDER BY log_seq DESC";
		return (List<Loginlog>)this.jdbcTemplate.query(query, this.loginlogMapper);
	}

}
