package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.SmsAuth;

public class SmsAuthDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper smsauthMapper = new RowMapper() {
		public SmsAuth mapRow(ResultSet rs, int rowNum) throws SQLException {
			SmsAuth smsauth = new SmsAuth();
			smsauth.setAuthSeq(rs.getInt("auth_seq"));
			smsauth.setUserId(rs.getString("user_id"));
			smsauth.setAuthCode(rs.getString("auth_code"));
			smsauth.setPhoneNumber(rs.getString("phone_number"));
			smsauth.setStatus(rs.getInt("status"));
			smsauth.setRegDate(rs.getString("reg_date"));
			return smsauth;
		}
	};

	public void addSmsAuth(final SmsAuth smsauth) {
		String query = "" +
				"INSERT INTO T_NF_SMS_AUTH " +
				"	(auth_seq, user_id, auth_code, phone_number, status, reg_date) " +
				"VALUES " +
				"	(?, ?, ?, ?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			smsauth.getAuthSeq(),
			smsauth.getUserId(),
			smsauth.getAuthCode(),
			smsauth.getPhoneNumber(),
			smsauth.getStatus(),
			smsauth.getRegDate()
		});
	}

	public void deleteSmsAuth(int auth_seq) {
		String query = "DELETE FROM T_NF_SMS_AUTH WHERE auth_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { auth_seq });
	}

	public void updateSmsAuth(SmsAuth smsauth) { 
		String query = "" + 
				"UPDATE T_NF_SMS_AUTH SET " +
				"	auth_seq = ?, " +
				"	user_id = ?, " +
				"	auth_code = ?, " +
				"	phone_number = ?, " +
				"	status = ?, " +
				"	reg_date = ? " +
				"WHERE auth_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			smsauth.getAuthSeq(),
			smsauth.getUserId(),
			smsauth.getAuthCode(),
			smsauth.getPhoneNumber(),
			smsauth.getStatus(),
			smsauth.getRegDate()
		});
	}

	public SmsAuth getSmsAuth(int auth_seq) {
		String query = "" + 
				"SELECT auth_seq, user_id, auth_code, phone_number, status, reg_date " +
				"FROM T_NF_SMS_AUTH " +
				"WHERE auth_seq = ? ";
		return (SmsAuth)this.jdbcTemplate.queryForObject(query, new Object[] { auth_seq }, this.smsauthMapper);
	}

	public List<SmsAuth> getSmsAuthList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" auth_seq, user_id, auth_code, phone_number, status, reg_date " +
				"FROM T_NF_SMS_AUTH " +
				"WHERE auth_seq <= ( " +
				"	SELECT MIN(auth_seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" auth_seq " +
				"		FROM T_NF_SMS_AUTH " +
				"		ORDER BY auth_seq DESC " +
				"	) AS A) " +
				"ORDER BY auth_seq DESC";
		return (List<SmsAuth>)this.jdbcTemplate.query(query, this.smsauthMapper);
	}
}
